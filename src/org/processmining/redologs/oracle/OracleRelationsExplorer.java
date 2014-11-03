package org.processmining.redologs.oracle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JFrame;

import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.Transformer;
import org.processmining.openslex.SLEXDMAttribute;
import org.processmining.openslex.SLEXDMClass;
import org.processmining.openslex.SLEXDMDataModel;
import org.processmining.openslex.SLEXDMKey;
import org.processmining.openslex.SLEXDMKeyAttribute;
import org.processmining.openslex.SLEXStorage;
import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.GraphEdge;
import org.processmining.redologs.common.GraphNode;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.RelationResult;
import org.processmining.redologs.common.RelationsGraphNode;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.config.DatabaseConnectionData;
import org.processmining.redologs.ui.VertexDisplayPredicate;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import oracle.ucp.UniversalConnectionPoolAdapter;
import oracle.ucp.UniversalConnectionPoolException;
import oracle.ucp.admin.UniversalConnectionPoolManager;
import oracle.ucp.admin.UniversalConnectionPoolManagerImpl;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class OracleRelationsExplorer {

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
	private Hashtable<String,TableInfo> targetTablesMap;
	
	public OracleRelationsExplorer() {
		init(CONFIG_FILE);
	}
	
	public OracleRelationsExplorer(String config_file) {
		init(config_file);
	}
	
	private OracleRelationsExplorer(DatabaseConnectionData connectionData) {
		CONNECTION_POOL_NAME = "pool_"+this.hashCode();
		CONNECTION_USER = connectionData.username;
		CONNECTION_PASS = connectionData.password;
		CONNECTION_HOST = connectionData.hostname;
		CONNECTION_PORT = String.valueOf(connectionData.port);
		CONNECTION_SERVICE = connectionData.service;
	}
	
	public OracleRelationsExplorer(DatabaseConnectionData connectionData, List<TableInfo> tables) {
		CONNECTION_POOL_NAME = "pool_"+this.hashCode();
		CONNECTION_USER = connectionData.username;
		CONNECTION_PASS = connectionData.password;
		CONNECTION_HOST = connectionData.hostname;
		CONNECTION_PORT = String.valueOf(connectionData.port);
		CONNECTION_SERVICE = connectionData.service;
		targetTables = tables;
		targetTablesMap = new Hashtable<>();
		for (TableInfo t: targetTables) {
			targetTablesMap.put(t.name, t);
		}
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
				targetTablesMap = new Hashtable<>();
				
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
					targetTablesMap.put(ti.name, ti);
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
	
	public void disconnect() {
		try {
			mgr.destroyConnectionPool(CONNECTION_POOL_NAME);
		} catch (UniversalConnectionPoolException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQueryGetResultSet(String query) {
		ResultSet res = null;
		try {

			Statement stm = con.createStatement();
			res = stm.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return res;
	}
	
	public static DataModel loadDataModelFromSLEXDM(SLEXDMDataModel dm) {
		DataModel model = new DataModel();
		// TODO implement loading datamodel from slexdm
		return model;
	}
	
	public DataModel extractRelations() {
		DataModel model = new DataModel();
		
		Hashtable<String, Key> primaryKeys = new Hashtable<>();
		Hashtable<String, Key> foreignKeys = new Hashtable<>();
		Hashtable<String, Key> uniqueKeys = new Hashtable<>();
		Hashtable<String, Column> columns = new Hashtable<>();
		Hashtable<TableInfo, List<Key>> keysPerTable = new Hashtable<>();
		
		try {
			String queryStr = "SELECT PF.constraint_name,PF.table_name,PF.column_name,C.constraint_type,C.r_constraint_name,PF.position "+
							  "FROM all_cons_columns PF, all_constraints C "+
							  "WHERE C.constraint_type IN ('U','P','R') "+
							  "AND PF.constraint_name = C.constraint_name "+
							  "AND C.table_name IN (";
			
			String sep="";
			for (TableInfo t: targetTables) {
				if (t.columns == null) {
					getTableColumns(t);
				}
				queryStr += sep+"'"+t.name+"'";
				sep=", ";
			}
			queryStr += ") ORDER BY C.constraint_type";
			
			ResultSet res = executeQueryGetResultSet(queryStr);
			
			while (res.next()) {
				String constraint_name = res.getString(1);
				String table_name = res.getString(2);
				String column_name = res.getString(3);
				String constraint_type = res.getString(4);
				String r_constraint_name = res.getString(5);
				int column_position = res.getInt(6);
				
				if (constraint_type.equalsIgnoreCase("R")) {
					// Foreign Key
					Key k = null;
					if (foreignKeys.containsKey(constraint_name)) {
						k = foreignKeys.get(constraint_name);
					} else {
						k = new Key();
						k.name = constraint_name;
						k.type = Key.FOREIGN_KEY;
						k.table = targetTablesMap.get(table_name);
						k.fields = new Vector<>();
						k.fields_ordered = new HashMap<>();
						k.refers_to_column = new HashMap<>();
						foreignKeys.put(k.name, k);
						List<Key> kpt = keysPerTable.get(k.table);
						if (kpt == null) {
							kpt = new Vector<>();
						}
						kpt.add(k);
						keysPerTable.put(k.table,kpt); 
					}
					
					Column c = new Column();
					c.name = column_name;
					c.table = targetTablesMap.get(table_name);
					if (columns.containsKey(c.toString())) {
						c = columns.get(c.toString());
					} else {
						columns.put(c.toString(), c);
					}
					
					if (primaryKeys.containsKey(r_constraint_name)) {
						k.refers_to = primaryKeys.get(r_constraint_name);
					} else if (uniqueKeys.containsKey(r_constraint_name)) {
						k.refers_to = uniqueKeys.get(r_constraint_name);
					}
					
					k.fields.add(c);
					k.fields_ordered.put(column_position,c);
					k.refers_to_column.put(c,k.refers_to.fields_ordered.get(column_position));
					
				} else if (constraint_type.equalsIgnoreCase("P")) {
					// Primary Key
					Key k = null;
					if (primaryKeys.containsKey(constraint_name)) {
						k = primaryKeys.get(constraint_name);
					} else {
						k = new Key();
						k.name = constraint_name;
						k.type = Key.PRIMARY_KEY;
						k.table = targetTablesMap.get(table_name);
						k.fields = new Vector<>();
						k.fields_ordered = new HashMap<>();
						primaryKeys.put(k.name, k);
						List<Key> kpt = keysPerTable.get(k.table);
						if (kpt == null) {
							kpt = new Vector<>();
						}
						kpt.add(k);
						keysPerTable.put(k.table,kpt);
					}
					
					Column c = new Column();
					c.name = column_name;
					c.table = targetTablesMap.get(table_name);
					if (columns.containsKey(c.toString())) {
						c = columns.get(c.toString());
					} else {
						columns.put(c.toString(), c);
					}
					
					k.fields.add(c);
					k.fields_ordered.put(column_position,c);
					
				} else if (constraint_type.equalsIgnoreCase("U")) {
					// Unique
					Key k = null;
					if (uniqueKeys.containsKey(constraint_name)) {
						k = uniqueKeys.get(constraint_name);
					} else {
						k = new Key();
						k.name = constraint_name;
						k.type = Key.UNIQUE_KEY;
						k.table = targetTablesMap.get(table_name);
						k.fields = new Vector<>();
						k.fields_ordered = new HashMap<>();
						uniqueKeys.put(k.name, k);
						List<Key> kpt = keysPerTable.get(k.table);
						if (kpt == null) {
							kpt = new Vector<>();
						}
						kpt.add(k);
						keysPerTable.put(k.table,kpt);
					}
					
					Column c = new Column();
					c.name = column_name;
					c.table = targetTablesMap.get(table_name);
					if (columns.containsKey(c.toString())) {
						c = columns.get(c.toString());
					} else {
						columns.put(c.toString(), c);
					}
					
					k.fields.add(c);
					k.fields_ordered.put(column_position,c);
				}

			}
			
			model.setPrimaryKeys(primaryKeys);
			model.setUniqueKeys(uniqueKeys);
			model.setForeignKeys(foreignKeys);
			model.setTables(targetTables);
			model.setKeysPerTable(keysPerTable);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
	public static Graph<GraphNode, GraphEdge> generateRelationsGraphPKAndFK(DataModel model) {
		Graph<GraphNode, GraphEdge> graph = new SparseGraph<>();
		
		Hashtable<String,List<Key>> comparableKeys = new Hashtable<>();
		
		for (Entry<String, Key> entry: model.getPrimaryKeys().entrySet()) {
			Key k = entry.getValue();
			graph.addVertex(k);
			Collections.sort(k.fields);
			String collectionID = "";
			for (Column c: k.fields) {
				graph.addVertex(c);
				graph.addVertex(c.table);
				graph.addEdge(new GraphEdge(),c,c.table);
				GraphEdge e = new GraphEdge();
				graph.addEdge(e,k,c);
				collectionID += c.toString()+"#";
			}
			
			if (comparableKeys.containsKey(collectionID)) {
				List<Key> keysList = comparableKeys.get(collectionID);
				keysList.add(k);
			} else {
				List<Key> keysList = new Vector<>();
				keysList.add(k);
				comparableKeys.put(collectionID, keysList);
			}
		}
		
		for (Entry<String, Key> entry: model.getUniqueKeys().entrySet()) {
			Key k = entry.getValue();
			graph.addVertex(k);
			String collectionID = "";
			for (Column c: k.fields) {
				graph.addVertex(c);
				graph.addVertex(c.table);
				graph.addEdge(new GraphEdge(),c,c.table);
				GraphEdge e = new GraphEdge();
				graph.addEdge(e,k,c);
				collectionID += c.toString()+"#";
			}
			
			if (comparableKeys.containsKey(collectionID)) {
				List<Key> keysList = comparableKeys.get(collectionID);
				keysList.add(k);
			} else {
				List<Key> keysList = new Vector<>();
				keysList.add(k);
				comparableKeys.put(collectionID, keysList);
			}
		}
		
		for (Entry<String, Key> entry: model.getForeignKeys().entrySet()) {
			Key k = entry.getValue();
			graph.addVertex(k);
			String collectionID = "";
			for (Column c: k.fields) {
				graph.addVertex(c);
				graph.addVertex(c.table);
				graph.addEdge(new GraphEdge(),c,c.table);
				GraphEdge e = new GraphEdge();
				graph.addEdge(e,k,c);
				collectionID += c.toString()+"#";
			}
			
			List<Key> keysList = null;
			if (comparableKeys.containsKey(collectionID)) {
				keysList = comparableKeys.get(collectionID);
				keysList.add(k);
			} else {
				keysList = new Vector<>();
				keysList.add(k);
				comparableKeys.put(collectionID, keysList);
			}
			
			if (k.refers_to != null) {
				graph.addVertex(k.refers_to);
				GraphEdge e = new GraphEdge();
				if (keysList.size() > 1) {
					e.label = k.toString() + " 1 - 1 " + k.refers_to.toString();
				} else {
					e.label = k.toString() + " N - 1 " + k.refers_to.toString();
				}
				graph.addEdge(e, k, k.refers_to);
			}
		}
		
		for (TableInfo t: model.getTables()) {
			graph.addVertex(t);
			for (Column c: t.columns) {
				if (!graph.containsVertex(c)) {
					c.filter = true;
					graph.addVertex(c);
					graph.addEdge(new GraphEdge(), c,c.table);
				}
			}
		}
		
		return graph;
		
	}
	
	public Graph<GraphNode, GraphEdge> generateRelationsGraphPKAndFK() {
		
		Graph<GraphNode, GraphEdge> graph = new SparseGraph<>();
		
		Hashtable<String, Key> primaryKeys = new Hashtable<>();
		Hashtable<String, Key> foreignKeys = new Hashtable<>();
		Hashtable<String, Key> uniqueKeys = new Hashtable<>();
		Hashtable<String, Column> columns = new Hashtable<>();
		
		try {
			String queryStr = "SELECT PF.constraint_name,PF.table_name,PF.column_name,C.constraint_type,C.r_constraint_name "+
							  "FROM all_cons_columns PF, all_constraints C "+
							  "WHERE C.constraint_type IN ('U','P','R') "+
							  "AND PF.constraint_name = C.constraint_name "+
							  "AND C.table_name IN (";
			
			String sep="";
			for (TableInfo t: targetTables) {
				queryStr += sep+"'"+t.name+"'";
				sep=", ";
			}
			queryStr += ") ORDER BY C.constraint_type";
			
			ResultSet res = executeQueryGetResultSet(queryStr);
			
			while (res.next()) {
				String constraint_name = res.getString(1);
				String table_name = res.getString(2);
				String column_name = res.getString(3);
				String constraint_type = res.getString(4);
				String r_constraint_name = res.getString(5);
				
				if (constraint_type.equalsIgnoreCase("R")) {
					// Foreign Key
					Key k = null;
					if (foreignKeys.containsKey(constraint_name)) {
						k = foreignKeys.get(constraint_name);
					} else {
						k = new Key();
						k.name = constraint_name;
						k.type = Key.FOREIGN_KEY;
						k.fields = new Vector<>();
						foreignKeys.put(k.name, k);
					}
					
					Column c = new Column();
					c.name = column_name;
					c.table = targetTablesMap.get(table_name);
					if (columns.containsKey(c.toString())) {
						c = columns.get(c.toString());
					} else {
						columns.put(c.toString(), c);
					}
					
					if (primaryKeys.containsKey(r_constraint_name)) {
						k.refers_to = primaryKeys.get(r_constraint_name);
					} else if (uniqueKeys.containsKey(r_constraint_name)) {
						k.refers_to = uniqueKeys.get(r_constraint_name);
					}
					
					k.fields.add(c);
					
				} else if (constraint_type.equalsIgnoreCase("P")) {
					// Primary Key
					Key k = null;
					if (primaryKeys.containsKey(constraint_name)) {
						k = primaryKeys.get(constraint_name);
					} else {
						k = new Key();
						k.name = constraint_name;
						k.type = Key.PRIMARY_KEY;
						k.fields = new Vector<>();
						primaryKeys.put(k.name, k);
					}
					
					Column c = new Column();
					c.name = column_name;
					c.table = targetTablesMap.get(table_name);
					if (columns.containsKey(c.toString())) {
						c = columns.get(c.toString());
					} else {
						columns.put(c.toString(), c);
					}
					
					k.fields.add(c);
					
				} else if (constraint_type.equalsIgnoreCase("U")) {
					// Unique
					Key k = null;
					if (uniqueKeys.containsKey(constraint_name)) {
						k = uniqueKeys.get(constraint_name);
					} else {
						k = new Key();
						k.name = constraint_name;
						k.type = Key.UNIQUE_KEY;
						k.fields = new Vector<>();
						uniqueKeys.put(k.name, k);
					}
					
					Column c = new Column();
					c.name = column_name;
					c.table = targetTablesMap.get(table_name);
					if (columns.containsKey(c.toString())) {
						c = columns.get(c.toString());
					} else {
						columns.put(c.toString(), c);
					}
					
					k.fields.add(c);
				}
				
				
			}
			
			Hashtable<String,List<Key>> comparableKeys = new Hashtable<>();
			
			for (Entry<String, Key> entry: primaryKeys.entrySet()) {
				Key k = entry.getValue();
				graph.addVertex(k);
				Collections.sort(k.fields);
				String collectionID = "";
				for (Column c: k.fields) {
					graph.addVertex(c);
					graph.addVertex(c.table);
					graph.addEdge(new GraphEdge(),c,c.table);
					GraphEdge e = new GraphEdge();
					graph.addEdge(e,k,c);
					collectionID += c.toString()+"#";
				}
				
				if (comparableKeys.containsKey(collectionID)) {
					List<Key> keysList = comparableKeys.get(collectionID);
					keysList.add(k);
				} else {
					List<Key> keysList = new Vector<>();
					keysList.add(k);
					comparableKeys.put(collectionID, keysList);
				}
			}
			
			for (Entry<String, Key> entry: uniqueKeys.entrySet()) {
				Key k = entry.getValue();
				graph.addVertex(k);
				String collectionID = "";
				for (Column c: k.fields) {
					graph.addVertex(c);
					graph.addVertex(c.table);
					graph.addEdge(new GraphEdge(),c,c.table);
					GraphEdge e = new GraphEdge();
					graph.addEdge(e,k,c);
					collectionID += c.toString()+"#";
				}
				
				if (comparableKeys.containsKey(collectionID)) {
					List<Key> keysList = comparableKeys.get(collectionID);
					keysList.add(k);
				} else {
					List<Key> keysList = new Vector<>();
					keysList.add(k);
					comparableKeys.put(collectionID, keysList);
				}
			}
			
			for (Entry<String, Key> entry: foreignKeys.entrySet()) {
				Key k = entry.getValue();
				graph.addVertex(k);
				String collectionID = "";
				for (Column c: k.fields) {
					graph.addVertex(c);
					graph.addVertex(c.table);
					graph.addEdge(new GraphEdge(),c,c.table);
					GraphEdge e = new GraphEdge();
					graph.addEdge(e,k,c);
					collectionID += c.toString()+"#";
				}
				
				List<Key> keysList = null;
				if (comparableKeys.containsKey(collectionID)) {
					keysList = comparableKeys.get(collectionID);
					keysList.add(k);
				} else {
					keysList = new Vector<>();
					keysList.add(k);
					comparableKeys.put(collectionID, keysList);
				}
				
				graph.addVertex(k.refers_to);
				GraphEdge e = new GraphEdge();
				if (keysList.size() > 1) {
					e.label = k.toString()+" 1 - 1 "+k.refers_to.toString();
				} else {
					e.label = k.toString()+" N - 1 "+k.refers_to.toString();
				}
				graph.addEdge(e, k,k.refers_to);
			}
			
		} catch (Exception e) {
			
		}
		
		return graph;
	}
	
	public RelationResult generateRelationsGraphFromForeignKeys(boolean addTableFieldsRelations) {
		
		Graph<String,String> graph = new SparseMultigraph<>();
		
		Hashtable<String,RelationsGraphNode> relations = new Hashtable<>();
		Hashtable<String,List<String>> tableRelations = new Hashtable<>();
		
		for (TableInfo t: targetTables) {
			try {
				
				ResultSet res = executeQueryGetResultSet(
						" SELECT A.table_name, A.column_name, B.column_name FROM all_cons_columns A, all_cons_columns B, all_constraints C "+
							" WHERE C.constraint_type='R' AND "+
							" A.constraint_name = C.constraint_name AND "+
							" B.constraint_name = C.r_constraint_name AND "+
							" C.r_constraint_name IN "+
								" (SELECT constraint_name FROM all_constraints "+
								"  WHERE constraint_type IN ('P','U') AND "+
								" table_name='"+t.name+"')");
				
				while (res.next()) {
					String fromTableName = res.getString(1);
					String fromFieldName = res.getString(2);
					String toFieldName = res.getString(3);
					String from = fromTableName+":"+fromFieldName;
					String to = t.name+":"+toFieldName;
					RelationsGraphNode toNode = null;
					RelationsGraphNode fromNode = null;
					
					if (relations.containsKey(from)) {
						fromNode = relations.get(from);
					} else {
						fromNode = new RelationsGraphNode();
						fromNode.name = from;
						fromNode.category = fromTableName;
						relations.put(fromNode.name, fromNode);
						
						if (tableRelations.containsKey(fromTableName)) {
							List<String> fields = tableRelations.get(fromTableName);
							if (!fields.contains(from)) {
								fields.add(from);
							}
						} else {
							List<String> fields = new Vector<>();
							fields.add(from);
							tableRelations.put(fromTableName, fields);
						}
						
						graph.addVertex(fromNode.name);
					}
					
					if (relations.containsKey(to)) {
						toNode = relations.get(to);
					} else {
						toNode = new RelationsGraphNode();
						toNode.name = to;
						toNode.category = t.name;
						relations.put(toNode.name, toNode);
						
						if (tableRelations.containsKey(t.name)) {
							List<String> fields = tableRelations.get(t.name);
							if (!fields.contains(to)) {
								fields.add(to);
							}
						} else {
							List<String> fields = new Vector<>();
							fields.add(to);
							tableRelations.put(t.name, fields);
						}
						
						graph.addVertex(toNode.name);
					}
					
					fromNode.to.add(toNode);
					toNode.from.add(fromNode);
					
					graph.addEdge(from+" -> "+to,fromNode.name, toNode.name, EdgeType.DIRECTED);
					
					if (addTableFieldsRelations) {
						List<String> fieldsFrom = tableRelations.get(fromTableName);
						List<String> fieldsTo = tableRelations.get(t.name);
						
						for (String f: fieldsFrom) {
							if (f != from) {
								graph.addEdge("-- "+from+" -- "+f+" --",fromNode.name, f, EdgeType.UNDIRECTED);
							}
						}
						
						for (String f: fieldsTo) {
							if (f != to) {
								graph.addEdge("-- "+to+" -- "+f+" --",toNode.name, f, EdgeType.UNDIRECTED);
							}
						}
					}
					
				}
				
				res.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(graph.toString());
		
		RelationResult result = new RelationResult();
		result.graph = graph;
		result.relations = relations;
		
		return result;
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
	
//	public Graph<String,String> generateRelationsGraphFromFieldNames() {
//		
//		Graph<String,String> graph = new SparseMultigraph<>();
//		
//		Hashtable<String,List<String>> relations = new Hashtable<>();
//		
//		for (TableInfo t: targetTables) {
//			try {
//				
//				List<String> columns = getTableColumns(t);
//				
//				for (String c: columns) {
//					String vertexName = t.name+":"+c;
//					List<String> list = null;
//					
//					if (relations.containsKey(c)) {
//						list = relations.get(c);
//						for (String v: list) {
//							graph.addEdge(v+" - "+vertexName, v, vertexName);
//						}
//						list.add(vertexName);
//					} else {
//						list = new Vector<>();
//						list.add(vertexName);
//						relations.put(c, list);
//						graph.addVertex(vertexName);
//					}
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println(graph.toString());
//		
//		return graph;
//	}
	
	public void showGraph(Graph<String,String> graph, String title) {
		Layout<String, String> layout = new CircleLayout<>(graph);
		layout.setSize(new Dimension(300,300)); // sets the initial size of the space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		VisualizationViewer<String,String> vv = new VisualizationViewer<String,String>(layout);
		
		vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
		
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		//vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());
		
		// Set up a new stroke Transformer for the edges
		float dash[] = { 10.0f };
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
		Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
			public Stroke transform(String s) {
				if (s.startsWith("-- ")) {
					return edgeStroke;
				} else {
					return new BasicStroke();
				}
			}
		};
		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		gm.setMode(Mode.PICKING);
		vv.setGraphMouse(gm);

		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public static VisualizationViewer<GraphNode,GraphEdge> getViewer(final Graph<GraphNode,GraphEdge> graph, String title) {
		//Layout<GraphNode, GraphEdge> layout = new CircleLayout<>(graph);
		//Layout<GraphNode, GraphEdge> layout = new SpringLayout2<>(graph);
		Layout<GraphNode, GraphEdge> layout = new FRLayout2<>(graph);
		//layout.setSize(new Dimension(600,600)); // sets the initial size of the space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		VisualizationViewer<GraphNode,GraphEdge> vv = new VisualizationViewer<GraphNode,GraphEdge>(layout);
		
		//vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
		
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<GraphNode>());

		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<GraphEdge>());

		Predicate<Context<Graph<GraphNode, GraphEdge>, GraphNode>> show_vertex = new VertexDisplayPredicate<>(false);
		vv.getRenderContext().setVertexIncludePredicate(show_vertex);
		
		vv.getRenderContext().setVertexFillPaintTransformer(new Transformer<GraphNode, Paint>() {
			
			@Override
			public Paint transform(GraphNode v) {
				if (v instanceof Column) {
					return Color.green;
				} else if (v instanceof TableInfo) {
					return Color.yellow;
				} else {
					return Color.red;
				}
			}
		});
		
		
		
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		gm.setMode(Mode.PICKING);
		vv.setGraphMouse(gm);	
		vv.addKeyListener(gm.getModeKeyListener());
		
		return vv;
	}
	
	public void showGraph2(final Graph<GraphNode,GraphEdge> graph, String title) {
		VisualizationViewer<GraphNode,GraphEdge> vv = getViewer(graph, title);
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		OracleRelationsExplorer explorer = new OracleRelationsExplorer();
		if (explorer.connect()) {
			
//			RelationResult result = explorer.generateRelationsGraphFromForeignKeys(true);
//			Graph<String,String> graphForeignKeys = result.graph;
//			
//			explorer.showGraph(graphForeignKeys,"Relations from Foreign Keys");
//			
//			Graph<String,String> graphFieldNames = explorer.generateRelationsGraphFromFieldNames();
//			
//			explorer.showGraph(graphFieldNames,"Relations from fields' names");
			
			Graph<GraphNode, GraphEdge> graph = explorer.generateRelationsGraphPKAndFK();
			
			explorer.showGraph2(graph,"Relations from Foreign and Primary Keys");
			
			explorer.disconnect();
			
		} else {
			System.err.println("ERROR: connection failed");
		}
	}

	public List<TableInfo> getAllTables(String db) {
		List<TableInfo> tables = new Vector<>();
		try {
			String query = "SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER='"+db+"'";
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			
			while(res.next()) {
				TableInfo t = new TableInfo();
				t.db = db;
				t.name = res.getString(1);
				tables.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tables;
	}
	
	public static List<TableInfo> getTables(
			DatabaseConnectionData connectionData,boolean getColumns) throws Exception {
		OracleRelationsExplorer explorer = new OracleRelationsExplorer(connectionData);
		if (explorer.connect()) {
			List<TableInfo> tables = new Vector<>();
			for (String db: connectionData.dbname) {
				List<TableInfo> tablesForDB = explorer.getAllTables(db);
				if (getColumns) {
					for (TableInfo t: tablesForDB) {
						explorer.getTableColumns(t);
					}
				}
				tables.addAll(tablesForDB);
			}
			explorer.disconnect();
			return tables;
		} else {
			throw new Exception("Failure connecting to Database");
		}
	}

}
