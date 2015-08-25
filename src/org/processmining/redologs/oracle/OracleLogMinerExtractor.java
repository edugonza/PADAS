package org.processmining.redologs.oracle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXAttributeValue;
import org.processmining.openslex.SLEXEvent;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXStorageCollection;
import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.config.DatabaseConnectionData;

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
	public static final String[] LOG_MINER_BASIC_FIELDS = {"SCN", "TIMESTAMP","SEG_OWNER","TABLE_NAME","OPERATION","SQL_REDO","SQL_UNDO","ROW_ID","RS_ID","SSN"};
	public static final String COLUMN_CHANGES = "COLUMN_CHANGES";
	public static final String COLUMN_ORDER = "ORDER";
	public static final String COLUMN_OPERATION = "OPERATION";
	public static final String COLUMN_TABLE_NAME = "TABLE_NAME";
	public static final String COLUMN_TIMESTAMP = "TIMESTAMP";
	public static final String COLUMN_CHANGE_NONE = "1";
	public static final String COLUMN_CHANGE_UPDATED = "4";
	public static final String COLUMN_CHANGE_TO_NULL = "2";
	public static final String COLUMN_CHANGE_FROM_NULL = "3";
	public static final String COLUMN_CHANGE_NONE_STILL_NULL = "5"; // FIXME add this to the extraction algorithm
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
	
	private boolean stopExtraction = false;
	
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
	
	public void setExtractionFlag(boolean enabled) {
		this.stopExtraction = !enabled;
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
			
			stm.close();
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
					+" DBMS_LOGMNR.DICT_FROM_ONLINE_CATALOG "
//					+" + DBMS_LOGMNR.COMMITTED_DATA_ONLY  "
					+" ); end;");
			
			stm.close();
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
			
			stm.close();
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
			
			stm.close();
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
	
	public void saveResultSet(TableInfo t,Hashtable<String,AliasColumnNameType> aliasTable, ResultSet res, File outCSVFile, SLEXEventCollection eventCollection, boolean computeEventClasses, HashMap<String,Integer> orderIds) {
			saveResultSetNewToOld(t, aliasTable, res, eventCollection, computeEventClasses,orderIds);
	}
	
	private void saveResultSetNewToOld(TableInfo t, Hashtable<String,AliasColumnNameType> aliasTable, ResultSet res, SLEXEventCollection eventCollection, boolean computeEventClasses, HashMap<String,Integer> orderIds) {
		SLEXStorageCollection storage = eventCollection.getStorage();
		try {
			storage.setAutoCommit(false);
			int i = 0;
			int MAX_ITERATIONS_PER_COMMIT = 100;
			
			Hashtable<String,Map<String, String>> records = new Hashtable<>();
			
			ResultSetMetaData meta = res.getMetaData();
			
			int rowIdColNum = res.findColumn("ROW_ID");
			int operationColNum = res.findColumn("OPERATION");
			int scnTimestampColNum = res.findColumn("SCN_TIMESTAMP");
			int ssnColNum = res.findColumn("SSN");
			int rs_idColNum = res.findColumn("RS_ID");
			
			int numAttributes = (aliasTable.size() / 4) + scnTimestampColNum + 1;
			
			SLEXAttribute[] attributeNames = new SLEXAttribute[numAttributes];
			
			String[] headers = new String[meta.getColumnCount()+1];
			String[] line = new String[meta.getColumnCount()+1];
			
			//headers[0] = COLUMN_CHANGES;
			attributeNames[0] = storage.findOrCreateAttribute(SLEXStorageCollection.COMMON_CLASS_NAME,COLUMN_CHANGES,true);
			SLEXAttribute orderAttribute = storage.findOrCreateAttribute(SLEXStorageCollection.COMMON_CLASS_NAME, COLUMN_ORDER, true);
			
			for (int j = 1; j <= meta.getColumnCount(); j++) {
				String colName = meta.getColumnName(j);
				if (aliasTable.containsKey(colName)) {
					AliasColumnNameType ac = aliasTable.get(colName);
					headers[j] = ac.name;
					//attributeNames[j] = LogStorage.getInstance().createAttribute(t.name,ac.name,false);
				} else {
					//headers[j] = meta.getColumnName(j);
					attributeNames[j] = storage.findOrCreateAttribute(SLEXStorageCollection.COMMON_CLASS_NAME,meta.getColumnName(j),true);
				}
			}
			
			//csvWriter.writeNext(headers);
			
			String column_changes = "";
			String new_value = "";
			String old_value = "";
			String new_present = "";
			String old_present = "";
			String rowid = "";
			String operation = "";
			String ssn = "";
			String rs_id = "";
			
			while (res.next() && !stopExtraction) {
				SLEXEvent event = storage.createEvent(eventCollection.getId());
				
				SLEXAttributeValue[] attributeValues = new SLEXAttributeValue[numAttributes];
				
				for (int c = 1; c <= meta.getColumnCount(); c++) {
					line[c] = res.getString(c);
					if (c <= scnTimestampColNum) {
						attributeValues[c] = storage.createAttributeValue(attributeNames[c].getId(), event.getId(), line[c]);
					}
				}
				
				/**/
				rowid = line[rowIdColNum];
				operation = line[operationColNum];
				ssn = line[ssnColNum];
				rs_id = line[rs_idColNum];
				
				storage.createAttributeValue(orderAttribute.getId(), event.getId(), String.valueOf(orderIds.get(ssn+"#"+rs_id)));
				
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
						
						/**/
						attributeNames[c+scnTimestampColNum+1] = storage.findOrCreateAttribute(t.name,headers[(4*c)+scnTimestampColNum+1],false);
						attributeValues[c+scnTimestampColNum+1] = 
								storage.createAttributeValue(attributeNames[c+scnTimestampColNum+1].getId(), event.getId(), line[(4*c)+scnTimestampColNum+1]);
						/**/
					}

					line[0] = column_changes;
					attributeValues[0] = storage.createAttributeValue(attributeNames[0].getId(), event.getId(), column_changes);

					if (operation.equalsIgnoreCase("INSERT")) {
						records.get(rowid).clear();
					}

					i++;
					if (i >= MAX_ITERATIONS_PER_COMMIT) {
						storage.commit();
						i=0;
					}

				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			storage.setAutoCommit(true);
		}
	}
	
	private Map<String, String> getFieldsForRowId(TableInfo t, String rowid) {
		Map<String,String> result = new HashMap<>();
		String query = "SELECT * FROM "+t.db+"."+t.name+" WHERE ROWID='"+rowid+"'";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			if (res.next()) {
				for (int i = 0; i < t.columns.size(); i++) {
					result.put(t.columns.get(i).name,res.getString(t.columns.get(i).name));
				}
			}
			stm.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public class AliasColumnNameType {
		public boolean isCP = false;
		public boolean isNewValue = false;
		public String name = null;
	}
	
	public HashMap<String,Integer> getOrderOfLogs(List<TableInfo> tables) {
		
		HashMap<String,Integer> idsOrderMap = new HashMap<>();
		
		String query = "SELECT SSN,RS_ID FROM V$LOGMNR_CONTENTS WHERE TABLE_NAME IN (";
		
		String comma = "";
		for (TableInfo t: tables) {
			query += comma+"'"+t.name+"'";
			comma = ",";
		}
		
		query += ")";
		
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);

			int order = 0;
			while (res.next()) {
				String ssn = res.getString(1);
				String rs_id = res.getString(2);
				order++;
				idsOrderMap.put(ssn+"#"+rs_id, order);
			}

			stm.close();
		} catch (SQLException e) {
			System.out.println("Error: "+query);
			e.printStackTrace();
		}
		
		return idsOrderMap;
		
	}
	
	public void getLogsForTableWithColumns(TableInfo t, File outputCSVFile, SLEXEventCollection eventCollection, boolean allRedoFields, boolean computeEventClasses, HashMap<String,Integer> orderIds, long scn_limit) {
		String query = "SELECT ";
		
		if (allRedoFields) {
			query += " V$LOGMNR_CONTENTS.*, ";
		} else {
			for (String f: LOG_MINER_BASIC_FIELDS) {
				query += " V$LOGMNR_CONTENTS."+f+", ";
			}
		}
		
		//query += " SCN_TO_TIMESTAMP(V$LOGMNR_CONTENTS.SCN) AS SCN_TIMESTAMP ";
		query += " V$LOGMNR_CONTENTS.TIMESTAMP AS SCN_TIMESTAMP ";
		
		Hashtable<String,AliasColumnNameType> aliasTable = new Hashtable<>();
		
		int i = 0;
		for (Column c: t.columns) {
			
			AliasColumnNameType ac1 = new AliasColumnNameType();
			AliasColumnNameType ac2 = new AliasColumnNameType();
			AliasColumnNameType ac3 = new AliasColumnNameType();
			AliasColumnNameType ac4 = new AliasColumnNameType();
			
			query += ", ";
			query += " (DBMS_LOGMNR.MINE_VALUE(REDO_VALUE,'"+t.db+"."+t.name+"."+c.name+"')) AS ALIAS_"+i+", ";
			
			ac1.isCP = false;
			ac1.isNewValue = true;
			ac1.name = c.name;
			aliasTable.put("ALIAS_"+i, ac1);
			i++;
			
			query += " (DBMS_LOGMNR.COLUMN_PRESENT(REDO_VALUE,'"+t.db+"."+t.name+"."+c.name+"')) AS ALIAS_"+i+", ";
			
			ac2.isCP = true;
			ac2.isNewValue = true;
			ac2.name = c.name;
			aliasTable.put("ALIAS_"+i, ac2);
			i++;
			
			query += " (DBMS_LOGMNR.MINE_VALUE(UNDO_VALUE,'"+t.db+"."+t.name+"."+c.name+"')) AS ALIAS_"+i+", ";
			
			ac3.isCP = false;
			ac3.isNewValue = false;
			ac3.name = c.name;
			aliasTable.put("ALIAS_"+i, ac3);
			i++;
			
			query += " (DBMS_LOGMNR.COLUMN_PRESENT(UNDO_VALUE,'"+t.db+"."+t.name+"."+c.name+"')) AS ALIAS_"+i;
			
			ac4.isCP = true;
			ac4.isNewValue = false;
			ac4.name = c.name;
			aliasTable.put("ALIAS_"+i, ac4);
			i++;
		}
		
		query +=" FROM V$LOGMNR_CONTENTS WHERE SEG_OWNER='"+t.db+"' AND TABLE_NAME='"+t.name+"' AND SCN <= '"+scn_limit+"' ORDER BY SCN DESC";
		
		
		
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);

			saveResultSet(t,aliasTable,res,outputCSVFile,eventCollection,computeEventClasses,orderIds);

			
			stm.close();
		} catch (SQLException e) {
			System.out.println("Error: "+query);
			e.printStackTrace();
		}
		
	}
	
	public void disconnect() {
		try {
			mgr.destroyConnectionPool(CONNECTION_POOL_NAME);
		} catch (UniversalConnectionPoolException e) {
			e.printStackTrace();
		}
	}

	public long getSCNLastCheckpoint() {
		long scn = 0L;
		
		try {
			
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select checkpoint_change# from v$database");
			
			res.next();
			scn = res.getLong(1);
			
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return scn;
	}
	
}
