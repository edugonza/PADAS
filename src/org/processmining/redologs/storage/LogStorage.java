package org.processmining.redologs.storage;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LogStorage {
	
	private static final String STORAGE_COLLECTION = "data"+File.separator+"collections.db";
	private static final String STORAGE_DATAMODEL = "data"+File.separator+"datamodels.db";
	
	private static final String COLLECTION_ALIAS = "collectionsdb";
	private static final String DATAMODEL_ALIAS = "datamodelsdb";
	
	private static final String COLLECTION_SCHEMA = "schemas"+File.separator+"collections.sql";
	private static final String DATAMODEL_SCHEMA = "schemas"+File.separator+"datamodels.sql"; 
	
	private boolean collection_attached = false;
	private boolean datamodel_attached = false;
	
	private Connection connection = null;
	
	private static LogStorage _instance = null;
	
	public static LogStorage getInstance() throws ClassNotFoundException {
		if (_instance == null) {
			_instance = new LogStorage();
		}
		
		return _instance;
	}
	
	private LogStorage() throws ClassNotFoundException {
		init();
		openCollectionStorage(null);
		openDataModelStorage(null);
	}
	
	public static String getCurrentWorkingDir() {
		return System.getProperty("user.dir");
	}
	
	public boolean openCollectionStorage(String filename) {
		if (collection_attached) {
			collection_attached = !detachDatabase(COLLECTION_ALIAS);
		}
		if (!collection_attached) {
			String fname = filename;
			if (fname == null) {
				fname = STORAGE_COLLECTION;
			}
			if (attachDatabaseFile(fname,COLLECTION_ALIAS,COLLECTION_SCHEMA)) {
				collection_attached = true;
			}
			return collection_attached;
		} else {
			return false;
		}
	}
	
	public boolean openDataModelStorage(String filename) {
		if (datamodel_attached) {
			datamodel_attached = !detachDatabase(DATAMODEL_ALIAS);
		}
		if (!datamodel_attached) {
			String fname = filename;
			if (fname == null) {
				fname = STORAGE_DATAMODEL;
			}
			if (attachDatabaseFile(fname,DATAMODEL_ALIAS,DATAMODEL_SCHEMA)) {
				datamodel_attached = true;
			}
			return datamodel_attached;
		} else {
			return false;
		}
	}
	
	private boolean checkSchema(String filename, String alias, String schema) {
		
		boolean result = false;
		Connection connAux = null;
		try {
			connAux = DriverManager.getConnection("jdbc:sqlite:"+filename);
			ScriptRunner scriptRunner = new ScriptRunner(connAux, false, false);
			scriptRunner.runScript(new FileReader(schema));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			if (connAux == null) {
				try {
					connAux.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	private boolean attachDatabaseFile(String filename, String alias, String schema) {
		try {
			File f = new File(filename);
			if (!f.exists()) {
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			
			if (checkSchema(filename,alias,schema)) {
			
				Statement statement = connection.createStatement();
				statement.setQueryTimeout(30);
				statement.execute("ATTACH DATABASE 'file:"+filename+"' AS "+alias);
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	private boolean detachDatabase(String alias) {
		try {
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("DETACH DATABASE "+alias);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private void init() throws ClassNotFoundException {
		
		Class.forName("org.sqlite.JDBC");
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite::memory:");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
