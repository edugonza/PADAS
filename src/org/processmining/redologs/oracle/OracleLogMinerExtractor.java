package org.processmining.redologs.oracle;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.deckfour.xes.in.XParser;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeLiteralImpl;
import org.deckfour.xes.model.impl.XAttributeMapImpl;
import org.deckfour.xes.model.impl.XEventImpl;
import org.deckfour.xes.model.impl.XLogImpl;
import org.deckfour.xes.model.impl.XTraceImpl;
import org.deckfour.xes.out.XSerializer;
import org.deckfour.xes.out.XesXmlSerializer;
import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.config.DatabaseConnectionData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import au.com.bytecode.opencsv.CSVWriter;
import oracle.ucp.UniversalConnectionPoolAdapter;
import oracle.ucp.UniversalConnectionPoolException;
import oracle.ucp.admin.UniversalConnectionPoolManager;
import oracle.ucp.admin.UniversalConnectionPoolManagerImpl;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class OracleLogMinerExtractor {
	
	private String CONNECTION_POOL_NAME = "extractor_pool";
	private String CONNECTION_USER = "sys";
	private String CONNECTION_PASS = "";
	private String CONNECTION_HOST = "localhost";
	private String CONNECTION_PORT = "1521";
	private String CONNECTION_SERVICE = "XE";
	private static final String CONFIG_FILE = "extractor.config";
	private UniversalConnectionPoolManager mgr;
	private PoolDataSource pds;
	private Connection con;
	private Properties prop;
	private List<TableInfo> targetTables;
	public static final String[] LOG_MINER_BASIC_FIELDS = {"SCN", "TIMESTAMP","SEG_OWNER","TABLE_NAME","OPERATION","SQL_REDO","SQL_UNDO","ROW_ID"};
	public static final String COLUMN_CHANGES = "COLUMN_CHANGES";
	private static final String COLUMN_CHANGE_NONE = "1";
	private static final String COLUMN_CHANGE_UPDATED = "4";
	private static final String COLUMN_CHANGE_TO_NULL = "2";
	private static final String COLUMN_CHANGE_FROM_NULL = "3";
	private static final String COLUMN_PRESENT = "1";
	private static final String COLUMN_MISSING = "0";
	public static final String NEW_VALUES_PREFIX = "V_NEW_";
	public static final String NEW_VALUES_CP_PREFIX = "CP_NEW_";
	public static final String OLD_VALUES_PREFIX = "V_OLD_";
	public static final String OLD_VALUES_CP_PREFIX = "CP_OLD_";
	private static final String NEW_VALUES_PREFIX_KEY = "V_NEW";
	private static final String NEW_VALUES_CP_PREFIX_KEY = "CP_NEW";
	private static final String OLD_VALUES_PREFIX_KEY = "V_OLD";
	private static final String OLD_VALUES_CP_PREFIX_KEY = "CP_OLD";
	
	public OracleLogMinerExtractor() {
		init(CONFIG_FILE);
	}
	
	public OracleLogMinerExtractor(String config_file) {
		init(config_file);
	}
	
	private OracleLogMinerExtractor(DatabaseConnectionData connectionData) {
		CONNECTION_POOL_NAME = "pool_"+this.hashCode();
		CONNECTION_USER = connectionData.username;
		CONNECTION_PASS = connectionData.password;
		CONNECTION_HOST = connectionData.hostname;
		CONNECTION_PORT = String.valueOf(connectionData.port);
		CONNECTION_SERVICE = connectionData.service;
	}
	
	public OracleLogMinerExtractor(DatabaseConnectionData connectionData, List<TableInfo> tables) {
		CONNECTION_POOL_NAME = "pool_"+this.hashCode();
		CONNECTION_USER = connectionData.username;
		CONNECTION_PASS = connectionData.password;
		CONNECTION_HOST = connectionData.hostname;
		CONNECTION_PORT = String.valueOf(connectionData.port);
		CONNECTION_SERVICE = connectionData.service;
		targetTables = tables;
	}
	
	private void init(String config_file) {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(config_file));
			CONNECTION_USER = prop.getProperty("db-user", CONNECTION_USER);
			CONNECTION_PASS = prop.getProperty("db-pass", CONNECTION_PASS);
			CONNECTION_HOST = prop.getProperty("db-host", CONNECTION_HOST);
			CONNECTION_PORT = prop.getProperty("db-port", CONNECTION_PORT);
			String dbname = prop.getProperty("db-name", "");
			String tables = prop.getProperty("tables", "");
			if (!tables.isEmpty()) {
				targetTables = new Vector<>();	
				
				String[] tableNames = tables.split(",");
				
				for (String t: tableNames) {
					TableInfo ti = new TableInfo();
					
					String[] t_s = t.split(":");
					if (t_s.length == 2) {
						ti.db = t_s[0];
						ti.name = t_s[1];
					} else {
						ti.db = dbname;
						ti.name = t;
					}
					
					targetTables.add(ti);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("No config file found in: "+config_file);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean connect() {
		boolean success = false;
		try {
			mgr = UniversalConnectionPoolManagerImpl.getUniversalConnectionPoolManager();
			
			pds = PoolDataSourceFactory.getPoolDataSource();
			pds.setConnectionPoolName(CONNECTION_POOL_NAME);
			pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
			pds.setURL("jdbc:oracle:thin:@//"+CONNECTION_HOST+":"+CONNECTION_PORT+"/"+CONNECTION_SERVICE);
			pds.setUser(CONNECTION_USER);
			pds.setPassword(CONNECTION_PASS);

			mgr.createConnectionPool((UniversalConnectionPoolAdapter)pds);
			
			mgr.startConnectionPool(CONNECTION_POOL_NAME);
			
			con = pds.getConnection();
			
			success=true;
		} catch (Exception e) {
			e.printStackTrace();
			success=false;
		}
		
		return success;
	}
	
	public List<String> getRedoLogFiles() {
		List<String> result = new Vector<>();
		
		try {
			
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select MEMBER from v$logfile");
			
			while(res.next()) {
				result.add(res.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean startLogMiner(List<String> logs) {
		
		if (logs == null || logs.size() == 0) { 
			return false;
		}
		
		try {
			
			Statement stm = con.createStatement();
			
			Iterator<String> it = logs.iterator();
			
			boolean first = true;
			while (it.hasNext()) {
				String name = it.next();
				if (first) {
					first = false;
					stm.execute("begin DBMS_LOGMNR.ADD_LOGFILE(LOGFILENAME => '"
									+ name + "',OPTIONS => DBMS_LOGMNR.NEW); end;");
				} else {
					stm.execute("begin DBMS_LOGMNR.ADD_LOGFILE(LOGFILENAME => '"
									+ name
									+ "',OPTIONS => DBMS_LOGMNR.ADDFILE); end;");
				}

			}
			
			stm.execute("begin DBMS_LOGMNR.START_LOGMNR("
					+" OPTIONS => "
					+" DBMS_LOGMNR.DICT_FROM_ONLINE_CATALOG + "
					+" DBMS_LOGMNR.COMMITTED_DATA_ONLY  "
					+" ); end;");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void executeQuery(String query) {
		
		try {
			
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			
			printResultSet(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<TableInfo> getTargetTables() {
		return targetTables;
	}
	
	public List<Column> getTableColumns(TableInfo t) {
		List<Column> columns = new Vector<>();
		try {
			String query = "SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE TABLE_NAME='"+
							t.name+"' AND OWNER='"+t.db+"'";
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			
			while(res.next()) {
				Column c = new Column();
				c.name = res.getString(1);
				c.table = t;
				columns.add(c);
			}
			
			t.columns = columns;
			
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return columns;
	}
	
	public void printResultSet(ResultSet res) {
		try {
		ResultSetMetaData meta = res.getMetaData();
		
		System.out.print("\n(");
		for (int j = 1; j <= meta.getColumnCount(); j++) {
			System.out.print(meta.getColumnName(j));
			if (j < meta.getColumnCount()) {
				System.out.print(", ");
			}
		}
		System.out.print(")\n");
		
		while(res.next()) {
			System.out.print("\n[");
			for (int c = 1; c <= meta.getColumnCount(); c++) {
				System.out.print(res.getString(c));
				if (c < meta.getColumnCount()) {
					System.out.print(", ");
				}
			}
			System.out.print("]\n");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveResultSet(TableInfo t,Hashtable<String,String> aliasTable, ResultSet res, File outCSVFile, XTrace trace, boolean computeEventClasses, boolean oldToNew) {
		if (oldToNew) {
			saveResultSetOldToNew(t, res, outCSVFile, computeEventClasses);
		} else {
			saveResultSetNewToOld(t, aliasTable, res,/* outCSVFile,*/ trace, computeEventClasses);
		}
	}
	
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static File serializedEventsFile;
	private static FileOutputStream outSerializedEvents;
	private static BufferedOutputStream bufferedOutSerializedEvents;
	private static void serializeEvent(XEvent event) {
		try {
			if (serializedEventsFile == null) {
				serializedEventsFile = new File(System.currentTimeMillis()+"_serialized-events.json");
				outSerializedEvents = new FileOutputStream(serializedEventsFile);
				bufferedOutSerializedEvents = new BufferedOutputStream(outSerializedEvents);
			}
			String eventStr = gson.toJson(event);
			bufferedOutSerializedEvents.write(eventStr.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveResultSetNewToOld(TableInfo t, Hashtable<String,String> aliasTable, ResultSet res,/*File outCSVFile,*/ XTrace trace, boolean computeEventClasses) {
		try {
			
			Hashtable<String,Map<String, String>> records = new Hashtable<>();
			
			
			ResultSetMetaData meta = res.getMetaData();
			
			//FileWriter fwriter = new FileWriter(outCSVFile);

			//CSVWriter csvWriter = new CSVWriter(fwriter);
			
			String[] headers = new String[meta.getColumnCount()+1];
			String[] line = new String[meta.getColumnCount()+1];
			
			headers[0] = COLUMN_CHANGES;
			
			for (int j = 1; j <= meta.getColumnCount(); j++) {
				String colName = meta.getColumnName(j);
				if (aliasTable.containsKey(colName)) {
					headers[j] = aliasTable.get(colName);
				} else {
					headers[j] = meta.getColumnName(j);
				}
			}
			
			int rowIdColNum = res.findColumn("ROW_ID");
			int operationColNum = res.findColumn("OPERATION");
			int scnTimestampColNum = res.findColumn("SCN_TIMESTAMP");
			
			//csvWriter.writeNext(headers);
			
			String column_changes = "";
			String new_value = "";
			String old_value = "";
			String new_present = "";
			String old_present = "";
			String rowid = "";
			String operation = "";
			
			while (res.next()) {
				for (int c = 1; c <= meta.getColumnCount(); c++) {
					line[c] = res.getString(c);
				}
				
				/**/
				rowid = line[rowIdColNum];
				operation = line[operationColNum];
				/**/
				
				if (operation.equalsIgnoreCase("INSERT")
						|| operation.equalsIgnoreCase("UPDATE")
						|| operation.equalsIgnoreCase("DELETE")) {

					Map<String, String> fields = null;

					if (!records.containsKey(rowid)) {
						// Get fields from current DB
						fields = getFieldsForRowId(t, rowid);
						records.put(rowid, fields);
					} else {
						fields = records.get(rowid);
					}

					column_changes = "";
					for (int c = 0; c < t.columns.size(); c++) {
						new_value = line[(4 * c) + scnTimestampColNum + 1];
						new_present = line[(4 * c) + scnTimestampColNum + 2];
						old_value = line[(4 * c) + scnTimestampColNum + 3];
						old_present = line[(4 * c) + scnTimestampColNum + 4];

						if ((new_present.equals(COLUMN_PRESENT))) {
							if (old_present.equals(COLUMN_PRESENT)) {
								if ((new_value != null) && (old_value != null)) {
									if (new_value.equals(old_value)) {
										column_changes += COLUMN_CHANGE_NONE;
									} else {
										column_changes += COLUMN_CHANGE_UPDATED;
									}
								} else if ((new_value == null)
										&& (old_value == null)) {
									column_changes += COLUMN_CHANGE_NONE;
								} else if (new_value == null) {
									column_changes += COLUMN_CHANGE_TO_NULL;
								} else if (old_value == null) {
									column_changes += COLUMN_CHANGE_FROM_NULL;
								} else {
									// Impossible
									System.err.println("Impossible situation");
								}
							} else {
								if (new_value == null) {
									column_changes += COLUMN_CHANGE_NONE;
								} else {
									column_changes += COLUMN_CHANGE_UPDATED;
								}
							}
						} else {
							if (old_present.equals(COLUMN_PRESENT)) {
								column_changes += COLUMN_CHANGE_UPDATED;
							} else {
								column_changes += COLUMN_CHANGE_NONE;
							}
						}

						if ((new_present.equals(COLUMN_MISSING))) {
							line[(4 * c) + scnTimestampColNum + 1] = fields
									.get(t.columns.get(c).name);
						}

						if ((old_present.equals(COLUMN_PRESENT))) {
							fields.put(t.columns.get(c).name, old_value);
						}

						if (operation.equalsIgnoreCase("DELETE")) {
							line[(4 * c) + scnTimestampColNum + 1] = old_value;
						}
					}

					line[0] = column_changes;

					if (operation.equalsIgnoreCase("INSERT")) {
						records.get(rowid).clear();
					}

					//csvWriter.writeNext(line);
					XAttributeMap attributesEvent = new XAttributeMapImpl();

					String key = "";
					//String subAttrKey = null;
					XAttributeLiteralImpl attribute = null;
					for (int i = 0; i < headers.length; i++) {
						if (line[i] != null) {
							key = headers[i];
//							if (key.startsWith(NEW_VALUES_PREFIX)) {
//								key = key.substring(NEW_VALUES_PREFIX.length());
//								//subAttrKey = NEW_VALUES_PREFIX_KEY;
//							} else if (key.startsWith(OLD_VALUES_PREFIX)) {
//								key = key.substring(OLD_VALUES_PREFIX.length());
//								//subAttrKey = OLD_VALUES_PREFIX_KEY;
//							} else if (key.startsWith(NEW_VALUES_CP_PREFIX)) {
//								key = key.substring(NEW_VALUES_CP_PREFIX.length());
//								//subAttrKey = NEW_VALUES_CP_PREFIX_KEY;
//							} else if (key.startsWith(OLD_VALUES_CP_PREFIX)) {
//								key = key.substring(OLD_VALUES_CP_PREFIX.length());
//								//subAttrKey = OLD_VALUES_CP_PREFIX_KEY;
//							} else {
								//subAttrKey = null;
//							}
							
							
							if (attributesEvent.containsKey(key)) {
								attribute = (XAttributeLiteralImpl) attributesEvent.get(key);
							} else {
								attribute = new XAttributeLiteralImpl(key, "");
								attributesEvent.put(attribute.getKey(), attribute);
							}
							
//							if (subAttrKey != null) {
//								XAttributeMap subAttributes = attribute.getAttributes();
//								if (subAttributes == null) {
//									subAttributes = new XAttributeMapImpl();
//									attribute.setAttributes(subAttributes);
//								}
//								subAttributes.put(subAttrKey, new XAttributeLiteralImpl(subAttrKey, line[i]));
//							} else {
								attribute.setValue(line[i]);
//							}
							
							attributesEvent.put(attribute.getKey(), attribute);
						}
					}

					XEvent event = new XEventImpl();
					event.setAttributes(attributesEvent);
					//serializeEvent(event);
					trace.add(event); // FIXME
				}
			}
			
			//csvWriter.close();
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
	
	private Map<String, String> getFieldsForRowId(TableInfo t, String rowid) {
		Map<String,String> result = new HashMap<>();
		String query = "SELECT * FROM "+t.db+"."+t.name+" WHERE ROWID='"+rowid+"'";
		Statement stm;
		try {
			stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			if (res.next()) {
				for (int i = 0; i < t.columns.size(); i++) {
					result.put(t.columns.get(i).name,res.getString(t.columns.get(i).name));
				}
			}
			res.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private void saveResultSetOldToNew(TableInfo t, ResultSet res,File outFile,boolean computeEventClasses) {
		try {
			Hashtable<String, Long> idsForRowId = new Hashtable<>();
			
			Hashtable<Long,Map<String, String>> records = new Hashtable<>();
			
			Long idsCount = new Long(0);
			
			ResultSetMetaData meta = res.getMetaData();
			
			FileWriter fwriter = new FileWriter(outFile);

			CSVWriter csvWriter = new CSVWriter(fwriter);

			String[] headers = new String[meta.getColumnCount()+2];
			String[] line = new String[meta.getColumnCount()+2];
			
			headers[0] = "UNIQUE_ID";
			headers[1] = "COLUMN_CHANGES";
			
			for (int j = 1; j <= meta.getColumnCount(); j++) {
				headers[j+1] = meta.getColumnName(j);
			}
			
			int rowIdColNum = res.findColumn("ROW_ID");
			int operationColNum = res.findColumn("OPERATION");
			int scnTimestampColNum = res.findColumn("SCN_TIMESTAMP");
			
			csvWriter.writeNext(headers);
			
			//csvWriter.writeAll(res, true);
			
			String column_changes = "";
			String new_value = "";
			String old_value = "";
			String new_present = "";
			String old_present = "";
			String rowid = "";
			String operation = "";
			String uniqueId = "";
			
			while (res.next()) {
				for (int c = 1; c <= meta.getColumnCount(); c++) {
					line[c+1] = res.getString(c);
				}
				
				/**/
				rowid = line[rowIdColNum+1];
				operation = line[operationColNum+1];
				
				if (idsForRowId.containsKey(rowid)) {
					uniqueId = idsForRowId.get(rowid).toString();
				} else {
					idsCount++;
					idsForRowId.put(rowid,idsCount);
					uniqueId = idsCount.toString();
				}
				
				line[0] = uniqueId;
				/**/
				
				Map<String, String> fields = null;
				
				if (!records.containsKey(Long.valueOf(uniqueId))) {
					fields = new HashMap<>();
					records.put(Long.valueOf(uniqueId), fields);
				} else {
					fields = records.get(Long.valueOf(uniqueId));
				}
				
				column_changes = "";
				for (int c = 0; c < t.columns.size(); c++) {
					new_value = line[(4*c)+scnTimestampColNum+2];
					new_present = line[(4*c)+scnTimestampColNum+3];
					old_value = line[(4*c)+scnTimestampColNum+4];
					old_present = line[(4*c)+scnTimestampColNum+5];
					
					if ((new_present.equals(COLUMN_PRESENT))) {
						if (old_present.equals(COLUMN_PRESENT)) {
							if ((new_value != null) && (old_value != null)) {
								if (new_value.equals(old_value)) {
									column_changes += COLUMN_CHANGE_NONE;
								} else {
									column_changes += COLUMN_CHANGE_UPDATED;
								}
							} else if ((new_value == null) && (old_value == null)) {
								column_changes += COLUMN_CHANGE_NONE;
							} else if (new_value == null) {
								column_changes += COLUMN_CHANGE_TO_NULL;
							} else if (old_value == null) {
								column_changes += COLUMN_CHANGE_FROM_NULL;
							} else {
								// Impossible
								System.err.println("Impossible situation");
							}
						} else {
							if (new_value == null) {
								column_changes += COLUMN_CHANGE_NONE;
							} else {
								column_changes += COLUMN_CHANGE_UPDATED;
							}
						}
					} else {
						if (old_present.equals(COLUMN_PRESENT)) {
							column_changes += COLUMN_CHANGE_UPDATED;
						} else {
							column_changes += COLUMN_CHANGE_NONE;
						}
					}
					
					if ((new_present.equals(COLUMN_PRESENT))) {
						fields.put(t.columns.get(c).name, new_value);
					} else {
						line[(4*c)+scnTimestampColNum+2] = fields.get(t.columns.get(c).name);
					}
					
				}
				
				line[1] = column_changes; 
				
				if (operation.equalsIgnoreCase("DELETE")) {
					idsForRowId.remove(rowid);
					records.remove(Long.valueOf(uniqueId));
				}
				
				csvWriter.writeNext(line);
			}
			
			csvWriter.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getLogsForTableWithColumns(TableInfo t, File outputCSVFile, XTrace trace, boolean allRedoFields, boolean computeEventClasses) {
		String query = "SELECT ";
		
		if (allRedoFields) {
			query += " V$LOGMNR_CONTENTS.*, ";
		} else {
			for (String f: LOG_MINER_BASIC_FIELDS) {
				query += " V$LOGMNR_CONTENTS."+f+", ";
			}
		}
		
		query += " SCN_TO_TIMESTAMP(V$LOGMNR_CONTENTS.SCN) AS SCN_TIMESTAMP ";
		
		Hashtable<String,String> aliasTable = new Hashtable<>();
		
		int i = 0;
		for (Column c: t.columns) {
			query += ", ";
			query += " (DBMS_LOGMNR.MINE_VALUE(REDO_VALUE,'"+t.db+"."+t.name+"."+c.name+"')) AS ALIAS_"+i+", ";
			
			aliasTable.put("ALIAS_"+i, NEW_VALUES_PREFIX+c.name);
			i++;
			
			query += " (DBMS_LOGMNR.COLUMN_PRESENT(REDO_VALUE,'"+t.db+"."+t.name+"."+c.name+"')) AS ALIAS_"+i+", ";
			
			aliasTable.put("ALIAS_"+i, NEW_VALUES_CP_PREFIX+c.name);
			i++;
			
			query += " (DBMS_LOGMNR.MINE_VALUE(UNDO_VALUE,'"+t.db+"."+t.name+"."+c.name+"')) AS ALIAS_"+i+", ";
			
			aliasTable.put("ALIAS_"+i, OLD_VALUES_PREFIX+c.name);
			i++;
			
			query += " (DBMS_LOGMNR.COLUMN_PRESENT(UNDO_VALUE,'"+t.db+"."+t.name+"."+c.name+"')) AS ALIAS_"+i;
			
			aliasTable.put("ALIAS_"+i, OLD_VALUES_CP_PREFIX+c.name);
			i++;
		}
		
		query +=" FROM V$LOGMNR_CONTENTS WHERE SEG_OWNER='"+t.db+"' AND TABLE_NAME='"+t.name+"' ORDER BY SCN DESC";
		
		ResultSet res = null;
		
		try {
			Statement stm = con.createStatement();
			res = stm.executeQuery(query);

//			if (outputCSVFile == null) {
//				printResultSet(res);
//			} else {
				
				saveResultSet(t,aliasTable,res,outputCSVFile,trace,computeEventClasses,false);
//			}
			
			//res.close();
		} catch (SQLException e) {
			System.out.println("Error: "+query);
			e.printStackTrace();
		}
		
		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		try {
			mgr.destroyConnectionPool(CONNECTION_POOL_NAME);
		} catch (UniversalConnectionPoolException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		OracleLogMinerExtractor extractor = new OracleLogMinerExtractor();
		if (extractor.connect()) {
			List<String> redoFiles = extractor.getRedoLogFiles();
			if (extractor.startLogMiner(redoFiles)) {
				List<TableInfo> tables = extractor.getTargetTables();
				
				XAttributeMap attributesLog = new XAttributeMapImpl();
				XAttributeMap attributesTrace = new XAttributeMapImpl();
				XLog log = new XLogImpl(attributesLog);
				XTrace trace = new XTraceImpl(attributesTrace);
				log.add(trace);
				
				for (TableInfo t: tables) {
					extractor.getTableColumns(t);
					extractor.getLogsForTableWithColumns(t,new File("result_"+t.name+".csv"),trace,false,true);
				}
				
				try {
					XSerializer serializer = new XesXmlSerializer();
					OutputStream xesOut = new FileOutputStream(new File("result"+".xes"));
					serializer.serialize(log, xesOut);
					xesOut.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//extractor.executeQuery("SELECT OPERATION, TIMESTAMP, SQL_REDO, SQL_UNDO FROM V$LOGMNR_CONTENTS WHERE OPERATION = 'INSERT' AND TABLE_NAME='CONCERT'");
				//extractor.executeQuery("SELECT (DBMS_LOGMNR.MINE_VALUE(REDO_VALUE,'SAMPLEDB.CONCERT.CONCERT_ID')) AS NEW_VALUE_CONCERT_ID, (DBMS_LOGMNR.COLUMN_PRESENT(REDO_VALUE,'SAMPLEDB.CONCERT.CONCERT_ID')) AS COLUMN_PRESENT_NEW_CONCERT_ID, (DBMS_LOGMNR.MINE_VALUE(UNDO_VALUE,'SAMPLEDB.CONCERT.CONCERT_ID')) AS OLD_VALUE_CONCERT_ID, (DBMS_LOGMNR.COLUMN_PRESENT(UNDO_VALUE,'SAMPLEDB.CONCERT.CONCERT_ID')) AS COLUMN_PRESENT_OLD_CONCERT_ID, V$LOGMNR_CONTENTS.* FROM V$LOGMNR_CONTENTS WHERE SEG_OWNER='SAMPLEDB' AND TABLE_NAME='CONCERT'");
			}
			extractor.disconnect();
		} else {
			System.err.println("ERROR: connection failed");
		}
	}
}
