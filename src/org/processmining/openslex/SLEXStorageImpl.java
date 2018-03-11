package org.processmining.openslex;
/*
 * 
 */


import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.processmining.openslex.utils.ScriptRunner;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXStorageImpl.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXStorageImpl implements SLEXStorageCollection, SLEXStorageDataModel, SLEXStoragePerspective {
	
	/** The Constant STORAGE_COLLECTION. */
	//private static final String STORAGE_COLLECTION = PATH+File.separator+"collections.db";
	
	/** The Constant STORAGE_PERSPECTIVE. */
	//private static final String STORAGE_PERSPECTIVE = PATH+File.separator+"perspectives.db";
	
	/** The Constant STORAGE_DATAMODEL. */
	//private static final String STORAGE_DATAMODEL = PATH+File.separator+"datamodels.db";
	
	/** The Constant COLLECTION_ALIAS. */
	private static final String COLLECTION_ALIAS = "collectionsdb";
	
	/** The Constant PERSPECTIVE_ALIAS. */
	private static final String PERSPECTIVE_ALIAS = "perspectivesdb";
	
	/** The Constant DATAMODEL_ALIAS. */
	private static final String DATAMODEL_ALIAS = "datamodelsdb";
	
	/** The collection schema in. */
	private InputStream COLLECTION_SCHEMA_IN = SLEXStorage.class.getResourceAsStream("/org/processmining/openslex/resources/collections.sql");
	
	/** The perspective schema in. */
	private InputStream PERSPECTIVE_SCHEMA_IN = SLEXStorage.class.getResourceAsStream("/org/processmining/openslex/resources/perspectives.sql");
	
	/** The datamodel schema in. */
	private InputStream DATAMODEL_SCHEMA_IN = SLEXStorage.class.getResourceAsStream("/org/processmining/openslex/resources/datamodels.sql");
	
	/** The Constant JOURNAL_MODE. */
	private static final String JOURNAL_MODE = "OFF";
	
	/** The Constant PRAGMA_SYNCHRONOUS_MODE. */
	private static final String PRAGMA_SYNCHRONOUS_MODE = "OFF";
	
	/** The collection_attached. */
	private boolean collection_attached = false;
	
	/** The perspective_attached. */
	private boolean perspective_attached = false;
	
	/** The datamodel_attached. */
	private boolean datamodel_attached = false;
	
	/** The auto commit on creation. */
	private boolean autoCommitOnCreation = true;
	
	/** The type. */
	private int type = 0;
	
	/** The filename. */
	private String filename = null;
	
	/** The path. */
	private String path = null;	
	
	/** The connection. */
	private Connection connection = null;
	
//	private static SLEXStorage _instance = null;
	
//	public static SLEXStorage getInstance() throws ClassNotFoundException {
//		if (_instance == null) {
//			_instance = new SLEXStorage();
//		}
//		
//		return _instance;
//	}
	
	/**
 * Instantiates a new SLEX storage impl.
 *
 * @param path the path
 * @param filename the filename
 * @param type the type
 * @throws Exception the exception
 */
public SLEXStorageImpl(String path, String filename, int type) throws Exception {
		init();
		this.filename = filename;
		this.path = path;
		this.type = type;
		if (type == TYPE_COLLECTION) {
			openCollectionStorage(path,filename);
		} else if (type == TYPE_PERSPECTIVE) {
			openPerspectiveStorage(path,filename);
		} else if (type == TYPE_DATAMODEL) {
			openDataModelStorage(path,filename);
		} else {
			throw new Exception("Type invalid");
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		disconnect();
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorage#getFilename()
	 */
	@Override
	public String getFilename() {
		return filename;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorage#getPath()
	 */
	@Override
	public String getPath() {
		return path;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorage#getType()
	 */
	@Override
	public int getType() {
		return type;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorage#setAutoCommit(boolean)
	 */
	@Override
	public void setAutoCommit(boolean autoCommit) {
		try {
			this.connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorage#commit()
	 */
	@Override
	public void commit() {
		try {
			this.connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the current working dir.
	 *
	 * @return the current working dir
	 */
	public static String getCurrentWorkingDir() {
		return System.getProperty("user.dir");
	}
	
	/**
	 * Open collection storage.
	 *
	 * @param path the path
	 * @param filename the filename
	 * @return true, if successful
	 */
	public boolean openCollectionStorage(String path, String filename) {
		if (collection_attached) {
			collection_attached = !detachDatabase(COLLECTION_ALIAS);
		}
		if (!collection_attached) {
			if (filename == null) {
				return false;
			}
			String fname = path+File.separator+filename;
			if (attachDatabaseFile(fname,COLLECTION_ALIAS,COLLECTION_SCHEMA_IN)) {
				collection_attached = true;
			}
			return collection_attached;
		} else {
			return false;
		}
	}
	
	/**
	 * Open perspective storage.
	 *
	 * @param path the path
	 * @param filename the filename
	 * @return true, if successful
	 */
	public boolean openPerspectiveStorage(String path, String filename) {
		if (perspective_attached) {
			perspective_attached = !detachDatabase(PERSPECTIVE_ALIAS);
		}
		if (!perspective_attached) {
			if (filename == null) {
				return false;
			}
			String fname = path+File.separator+filename;
			if (attachDatabaseFile(fname,PERSPECTIVE_ALIAS,PERSPECTIVE_SCHEMA_IN)) {
				perspective_attached = true;
			}
			return perspective_attached;
		} else {
			return false;
		}
	}
	
	/**
	 * Open data model storage.
	 *
	 * @param path the path
	 * @param filename the filename
	 * @return true, if successful
	 */
	public boolean openDataModelStorage(String path, String filename) {
		if (datamodel_attached) {
			datamodel_attached = !detachDatabase(DATAMODEL_ALIAS);
		}
		if (!datamodel_attached) {
			if (filename == null) {
				return false;
			}
			String fname = path+File.separator+filename;
			if (attachDatabaseFile(fname,DATAMODEL_ALIAS,DATAMODEL_SCHEMA_IN)) {
				datamodel_attached = true;
			}
			return datamodel_attached;
		} else {
			return false;
		}
	}
	
	/**
	 * Check schema.
	 *
	 * @param filename the filename
	 * @param alias the alias
	 * @param schemaIn the schema in
	 * @return true, if successful
	 */
	private boolean checkSchema(String filename, String alias, InputStream schemaIn) {
		boolean result = false;
		Connection connAux = null;
		try {
			connAux = DriverManager.getConnection("jdbc:sqlite:"+filename);
			ScriptRunner scriptRunner = new ScriptRunner(connAux, false, false);
			scriptRunner.runScript(new InputStreamReader(schemaIn));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			if (connAux != null) {
				try {
					connAux.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Close statement.
	 *
	 * @param statement the statement
	 */
	private void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close result set.
	 *
	 * @param rset the rset
	 */
	private void closeResultSet(ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Attach database file.
	 *
	 * @param filename the filename
	 * @param alias the alias
	 * @param schemaIn the schema in
	 * @return true, if successful
	 */
	private boolean attachDatabaseFile(String filename, String alias, InputStream schemaIn) {
		Statement statement = null;
		boolean result = false;
		try {
			File f = new File(filename);
			if (!f.exists()) {
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			
			if (checkSchema(filename,alias,schemaIn)) {
				statement = connection.createStatement();
				statement.setQueryTimeout(30);
				statement.execute("ATTACH DATABASE 'file:"+filename+"' AS "+alias);
				result = true;
			} else {
				result = false;
			}
			
		} catch (Exception e) {
			result = false;
		} finally {
			closeStatement(statement);
			setJournalMode(JOURNAL_MODE);
			setSynchronousMode(PRAGMA_SYNCHRONOUS_MODE);
		}
		return result;
	}
	
	/**
	 * Detach database.
	 *
	 * @param alias the alias
	 * @return true, if successful
	 */
	private boolean detachDatabase(String alias) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("DETACH DATABASE "+alias);
			result = true;
		} catch (Exception e) {
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/**
	 * Sets the journal mode.
	 *
	 * @param mode the mode
	 * @return true, if successful
	 */
	private boolean setJournalMode(String mode) {
		if (mode != null) {
			Statement statement = null;
			boolean result = false;
			try {
				statement = connection.createStatement();
				statement.setQueryTimeout(30);
				statement.execute("pragma journal_mode = "+mode+";");
				result = true;
			} catch (Exception e) {
				result = false;
			} finally {
				closeStatement(statement);
			}
			
			return result;
		}
		return false;
	}
	
	/**
	 * Sets the synchronous mode.
	 *
	 * @param mode the mode
	 * @return true, if successful
	 */
	private boolean setSynchronousMode(String mode) {
		if (mode != null) {
			Statement statement = null;
			boolean result = false;
			try {
				statement = connection.createStatement();
				statement.setQueryTimeout(30);
				statement.execute("pragma synchronous = "+mode+";");
				result = true;
			} catch (Exception e) {
				result = false;
			} finally {
				closeStatement(statement);
			}
			
			return result;
		}
		return false;
	}
	
	/**
	 * Query pragma.
	 *
	 * @param pragma the pragma
	 * @return the string
	 */
//	private String queryPragma(String pragma) {
//		String result = "";
//		if (pragma != null) {
//			Statement statement = null;
//			try {
//				statement = connection.createStatement();
//				statement.setQueryTimeout(30);
//				ResultSet r = statement.executeQuery("pragma "+pragma);
//				if (r.next()) {
//					result = r.getString(1);
//				}
//			} catch (Exception e) {
//				result = e.getLocalizedMessage();
//			} finally {
//				closeStatement(statement);
//			}
//		}
//		return result;
//	}
	
	/**
	 * Inits the.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 */
	private void init() throws ClassNotFoundException {
		
		Class.forName("org.sqlite.JDBC");
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite::memory:");
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorage#disconnect()
	 */
	@Override
	public void disconnect() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorage#setAutoCommitOnCreation(boolean)
	 */
	@Override
	public void setAutoCommitOnCreation(boolean flag) {
		this.autoCommitOnCreation = flag;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorage#isAutoCommitOnCreationEnabled()
	 */
	@Override
	public boolean isAutoCommitOnCreationEnabled() {
		return this.autoCommitOnCreation;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#createEventCollection(java.lang.String)
	 */
	@Override
	public SLEXEventCollection createEventCollection(String name) {
		SLEXEventCollection ec = new SLEXEventCollection(this, name);
		if (isAutoCommitOnCreationEnabled()) {
			ec.commit();
		}
		return ec;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#createEvent(int)
	 */
	@Override
	public SLEXEvent createEvent(int collectionId) {
		SLEXEvent ev = new SLEXEvent(this);
		ev.setCollectionId(collectionId);
		if (isAutoCommitOnCreationEnabled()) {
			ev.commit();
		}
		return ev;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#findClass(java.lang.String)
	 */
	@Override
	public SLEXClass findClass(String name) {
		SLEXClass cl = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rset = statement.executeQuery("SELECT id,name,common FROM "+COLLECTION_ALIAS+".class WHERE name = '"+name+"'");
			
			if (rset.next()) {
				boolean common = rset.getBoolean("common");
				int id = rset.getInt("id");
				cl = new SLEXClass(this, name, common);
				cl.setId(id);
				cl.setDirty(false);
				cl.setInserted(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			cl = null;
		} finally {
			closeStatement(statement);
		}
		
		return cl;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#findAttribute(java.lang.String, java.lang.String)
	 */
	@Override
	public SLEXAttribute findAttribute(String className, String name) {
		SLEXAttribute at = null;
		Statement statement = null;
		String classNameC = className.trim();
		String nameC = name.trim();
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rset = statement.executeQuery("SELECT AT.id,AT.name,AT.classID FROM "
														+COLLECTION_ALIAS+".attribute AS AT, "
														+COLLECTION_ALIAS+".class as CL WHERE "
															+" AT.name = '"+nameC+"' AND "
															+" AT.classID = CL.id AND "
															+" CL.name = '"+classNameC+"'");
			
			if (rset.next()) {
				int classId = rset.getInt("classID");
				int id = rset.getInt("id");
				at = new SLEXAttribute(this);
				at.setId(id);
				at.setClassId(classId);
				at.setName(name);
				at.setDirty(false);
				at.setInserted(false);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			at = null;
		} finally {
			closeStatement(statement);
		}
		
		return at;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#findOrCreateAttribute(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public SLEXAttribute findOrCreateAttribute(String className, String name, boolean common) {
		
		SLEXAttribute at = findAttribute(className, name);
		
		if (at == null) {
			at = createAttribute(className, name, common);
		}
		
		return at;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#createAttribute(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public SLEXAttribute createAttribute(String className, String name, boolean common) {
		SLEXAttribute at = new SLEXAttribute(this);
		at.setName(name);
		SLEXClass clazz = findClass(className);
		
		if (clazz == null) {
			clazz = createClass(className,common);
		}
		
		at.setClassId(clazz.getId());
		if (isAutoCommitOnCreationEnabled()) {
			at.commit();
		}
		return at;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#createAttributeValue(int, int, java.lang.String)
	 */
	@Override
	public SLEXAttributeValue createAttributeValue(int attributeId, int eventId, String value) {
		SLEXAttributeValue av = new SLEXAttributeValue(this,attributeId,eventId);
		av.setValue(value);
		if (isAutoCommitOnCreationEnabled()) {
			av.commit();
		}
		return av;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#createClass(java.lang.String, boolean)
	 */
	@Override
	public SLEXClass createClass(String name, boolean common) {
		SLEXClass cl = new SLEXClass(this,name,common);
		if (isAutoCommitOnCreationEnabled()) {
			cl.commit();
		}
		return cl;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#createDMDataModel(java.lang.String)
	 */
	@Override
	public SLEXDMDataModel createDMDataModel(String name) {
		SLEXDMDataModel dm = new SLEXDMDataModel(this);
		dm.setName(name);
		if (isAutoCommitOnCreationEnabled()) {
			dm.commit();
		}
		return dm;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#createDMClass(int, java.lang.String, boolean)
	 */
	@Override
	public SLEXDMClass createDMClass(int data_modelId, String name, boolean common) {
		SLEXDMClass cl = new SLEXDMClass(this, name, common, data_modelId);
		if (isAutoCommitOnCreationEnabled()) {
			cl.commit();
		}
		return cl;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#createDMAttribute(int, java.lang.String, boolean)
	 */
	@Override
	public SLEXDMAttribute createDMAttribute(int classId, String name, boolean common) {
		SLEXDMAttribute at = new SLEXDMAttribute(this);
		at.setName(name);
		at.setClassId(classId);
		if (isAutoCommitOnCreationEnabled()) {
			at.commit();
		}
		return at;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#createDMKey(int, java.lang.String, int, int)
	 */
	@Override
	public SLEXDMKey createDMKey(int classId, String name, int type, int refers_to_key) {
		SLEXDMKey k = new SLEXDMKey(this);
		k.setName(name);
		k.setClassId(classId);
		k.setType(type);
		if (type == SLEXDMKey.FOREIGN_KEY) {
			k.setRefersToKey(refers_to_key);
		}
		if (isAutoCommitOnCreationEnabled()) {
			k.commit();
		}
		return k;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#createDMKeyAttribute(int, int, int, int)
	 */
	@Override
	public SLEXDMKeyAttribute createDMKeyAttribute(int keyId, int attributeId, int refersToAttributeId, int position) {
		SLEXDMKeyAttribute kat = new SLEXDMKeyAttribute(this, keyId, attributeId);
		kat.setRefersToId(refersToAttributeId);
		kat.setPosition(position);
		if (isAutoCommitOnCreationEnabled()) {
			kat.commit();
		}
		return kat;
	}
	
	/**
	 * Gets the last inserted row id.
	 *
	 * @param stmnt the stmnt
	 * @return the last inserted row id
	 */
	private int getLastInsertedRowId(Statement stmnt) {
		int id = -1;
		ResultSet res = null;
		try {
			res = stmnt.getGeneratedKeys();
			if (res.next()) {
				id = res.getInt("last_insert_rowid()");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResultSet(res);
		}
		return id;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#insert(org.processmining.openslex.SLEXEventCollection)
	 */
	public /*protected*/ synchronized boolean insert(SLEXEventCollection ec) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+COLLECTION_ALIAS+".collection (name) VALUES ('"+ec.getName()+"')");
			ec.setId(getLastInsertedRowId(statement));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#update(org.processmining.openslex.SLEXEventCollection)
	 */
	public /*protected*/ boolean update(SLEXEventCollection ec) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+COLLECTION_ALIAS+".collection SET name =  '"+ec.getName()+"' WHERE id = '"+ec.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#insert(org.processmining.openslex.SLEXEvent)
	 */
	public /*protected*/ synchronized boolean insert(SLEXEvent ev) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+COLLECTION_ALIAS+".event (collectionID) VALUES ('"+ev.getCollectionId()+"')");
			ev.setId(getLastInsertedRowId(statement));
			// TODO Insert all the attributes and attribute-values in the event, if any
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#update(org.processmining.openslex.SLEXEvent)
	 */
	public /*protected*/ boolean update(SLEXEvent ev) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+COLLECTION_ALIAS+".event SET collectionID = '"+ev.getCollectionId()+"' WHERE id = '"+ev.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#insert(org.processmining.openslex.SLEXAttribute)
	 */
	public /*protected*/ synchronized boolean insert(SLEXAttribute at) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+COLLECTION_ALIAS+".attribute (name,classID) VALUES ('"+at.getName()+"','"+at.getClassId()+"')");
			at.setId(getLastInsertedRowId(statement));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#update(org.processmining.openslex.SLEXAttribute)
	 */
	public /*protected*/ boolean update(SLEXAttribute at) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+COLLECTION_ALIAS+".attribute SET classID = '"+at.getClassId()+"',name = '"+at.getName()+"' WHERE id = '"+at.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#insert(org.processmining.openslex.SLEXDMAttribute)
	 */
	public /*protected*/ synchronized boolean insert(SLEXDMAttribute at) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+DATAMODEL_ALIAS+".attribute (name,classID) VALUES ('"+at.getName()+"','"+at.getClassId()+"')");
			at.setId(getLastInsertedRowId(statement));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#update(org.processmining.openslex.SLEXDMAttribute)
	 */
	public /*protected*/ boolean update(SLEXDMAttribute at) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+DATAMODEL_ALIAS+".attribute SET classID = '"+at.getClassId()+"',name = '"+at.getName()+"' WHERE id = '"+at.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#insert(org.processmining.openslex.SLEXDMKey)
	 */
	public /*protected*/ synchronized boolean insert(SLEXDMKey k) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+DATAMODEL_ALIAS+".key (name,classID,type,refers_to_key) VALUES ('"+k.getName()+"','"+k.getClassId()+"','"+k.getType()+"','"+k.getRefersToKey()+"')");
			k.setId(getLastInsertedRowId(statement));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#update(org.processmining.openslex.SLEXDMKey)
	 */
	public /*protected*/ boolean update(SLEXDMKey k) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+DATAMODEL_ALIAS+".key SET classID = '"+k.getClassId()+"',name = '"+k.getName()+"',type = '"+k.getType()+"',refers_to_key = '"+k.getRefersToKey()+"' WHERE id = '"+k.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#insert(org.processmining.openslex.SLEXDMKeyAttribute)
	 */
	public /*protected*/ boolean insert(SLEXDMKeyAttribute ka) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			String refersTo = null;
			if (ka.getRefersToId() == SLEXDMKeyAttribute.REFERS_TO_NULL) {
				refersTo = "NULL";
			} else {
				refersTo = ka.getRefersToId()+"";
			}
			statement.execute("INSERT INTO "+DATAMODEL_ALIAS+".key_attribute (keyID,attributeID,refersTo,position) VALUES ('"+ka.getKeyId()+"','"+ka.getAttributeId()+"','"+refersTo+"','"+ka.getPosition()+"')");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#update(org.processmining.openslex.SLEXDMKeyAttribute)
	 */
	public /*protected*/ boolean update(SLEXDMKeyAttribute ka) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			String refersTo = null;
			if (ka.getRefersToId() == SLEXDMKeyAttribute.REFERS_TO_NULL) {
				refersTo = "NULL";
			} else {
				refersTo = ka.getRefersToId()+"";
			}
			statement.execute("UPDATE "+DATAMODEL_ALIAS+".key_attribute SET refersTo = '"+refersTo+"', position = '"+ka.getPosition()+"' WHERE keyID = '"+ka.getKeyId()+"' AND attributeID = '"+ka.getAttributeId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#insert(org.processmining.openslex.SLEXAttributeValue)
	 */
	public /*protected*/ boolean insert(SLEXAttributeValue av) {
		PreparedStatement statement = null;
		boolean result = false;
		try {
			statement = connection.prepareStatement("INSERT INTO "+COLLECTION_ALIAS+".attribute_value (eventID,attributeID,value) VALUES (?,?,?)");
			statement.setQueryTimeout(30);
			statement.setInt(1, av.getEventId());
			statement.setInt(2, av.getAttributeId());
			statement.setString(3, av.getValue());
			statement.execute();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#update(org.processmining.openslex.SLEXAttributeValue)
	 */
	public /*protected*/ boolean update(SLEXAttributeValue av) {
		PreparedStatement statement = null;
		boolean result = false;
		try {
			statement = connection.prepareStatement("UPDATE "+COLLECTION_ALIAS+".attribute_value SET value = ? WHERE eventID = ? AND attributeID = ?");
			statement.setQueryTimeout(30);
			statement.setString(1, av.getValue());
			statement.setInt(2, av.getEventId());
			statement.setInt(3, av.getAttributeId());
			statement.execute();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#insert(org.processmining.openslex.SLEXClass)
	 */
	public /*protected*/ synchronized boolean insert(SLEXClass cl) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+COLLECTION_ALIAS+".class (name,common) VALUES ('"+cl.getName()+"','"+cl.isCommon()+"')");
			cl.setId(getLastInsertedRowId(statement));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#update(org.processmining.openslex.SLEXClass)
	 */
	public /*protected*/ boolean update(SLEXClass cl) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+COLLECTION_ALIAS+".class SET name = '"+cl.getName()+"', common = '"+cl.isCommon()+"' WHERE id = '"+cl.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#insert(org.processmining.openslex.SLEXDMClass)
	 */
	public /*protected*/ synchronized boolean insert(SLEXDMClass cl) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+DATAMODEL_ALIAS+".class (name,common,data_modelID) VALUES ('"+cl.getName()+"','"+cl.isCommon()+"','"+cl.getDataModelId()+"')");
			cl.setId(getLastInsertedRowId(statement));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#update(org.processmining.openslex.SLEXDMClass)
	 */
	public /*protected*/ boolean update(SLEXDMClass cl) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+DATAMODEL_ALIAS+".class SET name = '"+cl.getName()+"', common = '"+cl.isCommon()+"', data_modelID = '"+cl.getDataModelId()+"' WHERE id = '"+cl.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#insert(org.processmining.openslex.SLEXPerspective)
	 */
	public /*protected*/ synchronized boolean insert(SLEXPerspective p) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+PERSPECTIVE_ALIAS+".perspective (name,collectionID,collectionFileName) VALUES ('"+p.getName()+"','"+p.getCollectionId()+"','"+p.getCollectionFileName()+"')");
			p.setId(getLastInsertedRowId(statement));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#update(org.processmining.openslex.SLEXPerspective)
	 */
	public /*protected*/ boolean update(SLEXPerspective p) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+PERSPECTIVE_ALIAS+".perspective SET name = '"+p.getName()+"', collectionID = '"+p.getCollectionId()+"', collectionFileName = '"+p.getCollectionFileName()+"' WHERE id = '"+p.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#insert(org.processmining.openslex.SLEXDMDataModel)
	 */
	public /*protected*/ synchronized boolean insert(SLEXDMDataModel dm) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+DATAMODEL_ALIAS+".data_model (name) VALUES ('"+dm.getName()+"')");
			dm.setId(getLastInsertedRowId(statement));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#update(org.processmining.openslex.SLEXDMDataModel)
	 */
	public /*protected*/ boolean update(SLEXDMDataModel dm) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+DATAMODEL_ALIAS+".data_model SET name = '"+dm.getName()+"' WHERE id = '"+dm.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#insert(org.processmining.openslex.SLEXTrace)
	 */
	public /*protected*/ /*synchronized*/ boolean insert(SLEXTrace t) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+PERSPECTIVE_ALIAS+".trace (id,caseID,perspectiveID) VALUES ('"+t.getId()+"','"+t.getCaseId()+"','"+t.getPerspectiveId()+"')");
			//t.setId(getLastInsertedRowId(statement));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#update(org.processmining.openslex.SLEXTrace)
	 */
	public /*protected*/ boolean update(SLEXTrace t) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+PERSPECTIVE_ALIAS+".trace SET caseID = '"+t.getCaseId()+"', perspectiveID = '"+t.getPerspectiveId()+"' WHERE id = '"+t.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#getEventCollections()
	 */
	@Override
	public SLEXEventCollectionResultSet getEventCollections() {
		SLEXEventCollectionResultSet ecrset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+COLLECTION_ALIAS+".collection");
			ecrset = new SLEXEventCollectionResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return ecrset; 
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#getPerspectives()
	 */
	@Override
	public SLEXPerspectiveResultSet getPerspectives() {
		SLEXPerspectiveResultSet ecrset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+PERSPECTIVE_ALIAS+".perspective");
			ecrset = new SLEXPerspectiveResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return ecrset; 
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#getPerspectivesOfCollection(org.processmining.openslex.SLEXEventCollection)
	 */
	@Override
	public SLEXPerspectiveResultSet getPerspectivesOfCollection(SLEXEventCollection ec) {
		SLEXPerspectiveResultSet ecrset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+PERSPECTIVE_ALIAS+".perspective WHERE collectionID = "+ec.getId());
			ecrset = new SLEXPerspectiveResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return ecrset;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#getEventsOfCollection(org.processmining.openslex.SLEXEventCollection)
	 */
	public /*protected*/ SLEXEventResultSet getEventsOfCollection(
			SLEXEventCollection ec) {
		SLEXEventResultSet erset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+COLLECTION_ALIAS+".event WHERE collectionID = "+ec.getId());
			erset = new SLEXEventResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return erset; 
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#getEventsOfCollectionOrderedBy(org.processmining.openslex.SLEXEventCollection, java.util.List)
	 */
	@Override
	public /*protected*/ SLEXEventResultSet getEventsOfCollectionOrderedBy(
			SLEXEventCollection ec,
			List<SLEXAttribute> orderAttributes) {
		SLEXEventResultSet erset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String wherequery = " WHERE E.collectionID = "+ec.getId();
			String orderquery = " ";
			String query = 			
					" SELECT * FROM "+COLLECTION_ALIAS+".event AS E ";
			
			if (orderAttributes != null) {
				orderquery = " ORDER BY ";
				for (int i=0;i<orderAttributes.size();i++) {
					query += " ,"+COLLECTION_ALIAS+".attribute_value AS ATV"+i+" ";
					wherequery += " AND ATV"+i+".eventID = E.id "+
							" AND ATV"+i+".attributeID = "+orderAttributes.get(i).getId()+" ";
					orderquery += " CAST(ATV"+i+".value AS INTEGER) ASC ";
					if (i < orderAttributes.size()-1) {
						orderquery += ", ";
					}
				}
			}
			query += wherequery + orderquery;
			ResultSet rset = statement.executeQuery(query);
			erset = new SLEXEventResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return erset;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#getEventsOfCollectionBetweenDatesOrderedBy(org.processmining.openslex.SLEXEventCollection, java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public /*protected*/ SLEXEventResultSet getEventsOfCollectionBetweenDatesOrderedBy( 
			SLEXEventCollection ec, List<SLEXAttribute> orderAttributes,
			String startDate, String endDate) {
		SLEXEventResultSet erset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String wherequery = " WHERE E.collectionID = " + ec.getId();
			String orderquery = " ";
			String query = " SELECT * FROM " + COLLECTION_ALIAS
					+ ".event AS E ";
			int i = 0;
			
			if (orderAttributes != null) {
				orderquery = " ORDER BY ";
				for (i = 0; i < orderAttributes.size(); i++) {
					query += " ," + COLLECTION_ALIAS + ".attribute_value AS ATV"
							+ i + " ";
					wherequery += " AND ATV" + i + ".eventID = E.id " + " AND ATV"
							+ i + ".attributeID = "
							+ orderAttributes.get(i).getId() + " ";
					orderquery += " CAST(ATV" + i + ".value AS INTEGER) ";
					if (i < orderAttributes.size() - 1) {
						orderquery += ", ";
					}
				}
			}
			
			SLEXAttribute timestampAttr = findAttribute(COMMON_CLASS_NAME, "TIMESTAMP");
			
			if (timestampAttr != null) {
				i++;
				query += " , "+COLLECTION_ALIAS +".attribute_value AS ATV"+i+" ";
				wherequery += " AND ATV"+i+".eventID = E.id " +
							  " AND ATV"+i+".attributeID = "+timestampAttr.getId()+" " +
							  " AND CAST(julianday(ATV"+i+".value) AS FLOAT) BETWEEN julianday('"+startDate+"') AND julianday('"+endDate+"') ";
			}
			
			query += wherequery + orderquery;
			ResultSet rset = statement.executeQuery(query);
			erset = new SLEXEventResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}

		return erset;
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#getAttributeValuesForEvent(org.processmining.openslex.SLEXEvent)
	 */
	public /*protected*/ Hashtable<SLEXAttribute, SLEXAttributeValue> getAttributeValuesForEvent(
			SLEXEvent ev) {
		Hashtable<SLEXAttribute, SLEXAttributeValue> attributeValues = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rset = statement.executeQuery("SELECT AT.classID, AT.id, AT.name, ATV.value FROM "+
														COLLECTION_ALIAS+".attribute AS AT, "+
														COLLECTION_ALIAS+".attribute_value AS ATV, "+
														COLLECTION_ALIAS+".event AS EV "+
														"WHERE EV.id = "+ev.getId()+" AND ATV.eventID = EV.id AND "
																+ " ATV.attributeID = AT.id ");
			attributeValues = new Hashtable<>();
			
			while (rset.next()) {
				SLEXAttribute at = new SLEXAttribute(this);
				at.setClassId(rset.getInt(1));
				at.setId(rset.getInt(2));
				at.setName(rset.getString(3));
				at.setDirty(false);
				at.setInserted(true);
				SLEXAttributeValue atv = new SLEXAttributeValue(this, at.getId(), ev.getId());
				atv.setValue(rset.getString(4));
				atv.setDirty(false);
				atv.setInserted(true);
				attributeValues.put(at, atv);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributeValues = null;
		} finally {
			closeStatement(statement);
		}
		return attributeValues;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#getClassesForDataModel(org.processmining.openslex.SLEXDMDataModel)
	 */
	@Override
	public SLEXDMClassResultSet getClassesForDataModel(SLEXDMDataModel dm) {
		SLEXDMClassResultSet crset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+DATAMODEL_ALIAS+".class WHERE data_modelID = "+dm.getId());
			crset = new SLEXDMClassResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return crset;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#getDataModels()
	 */
	@Override
	public SLEXDMDataModelResultSet getDataModels() {
		SLEXDMDataModelResultSet dmrset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+DATAMODEL_ALIAS+".data_model");
			dmrset = new SLEXDMDataModelResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return dmrset;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#getAttributesForDMClass(org.processmining.openslex.SLEXDMClass)
	 */
	@Override
	public List<SLEXDMAttribute> getAttributesForDMClass(SLEXDMClass cl) {
		List<SLEXDMAttribute> atList = new Vector<>();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+DATAMODEL_ALIAS+".attribute WHERE classID = "+cl.getId());
			while (rset.next()) {
				int id = rset.getInt("id");
				String name = rset.getString("name");
				int classId = rset.getInt("classID");
				SLEXDMAttribute at = new SLEXDMAttribute(this);
				at.setId(id);
				at.setName(name);
				at.setClassId(classId);
				at.setDirty(false);
				at.setInserted(true);
				atList.add(at);
			}
		} catch (Exception e) {
			e.printStackTrace();
			atList = null;
		} finally {
			closeStatement(statement);
		}
		return atList;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#getKeysForDMClass(org.processmining.openslex.SLEXDMClass)
	 */
	@Override
	public List<SLEXDMKey> getKeysForDMClass(SLEXDMClass cl) {
		List<SLEXDMKey> kList = new Vector<>();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+DATAMODEL_ALIAS+".key WHERE classID = "+cl.getId());
			while (rset.next()) {
				int id = rset.getInt("id");
				String name = rset.getString("name");
				int classId = rset.getInt("classID");
				int type = rset.getInt("type");
				int refers_to_key = rset.getInt("refers_to_key");
				SLEXDMKey k = new SLEXDMKey(this);
				k.setId(id);
				k.setName(name);
				k.setClassId(classId);
				k.setType(type);
				k.setRefersToKey(refers_to_key);
				k.setDirty(false);
				k.setInserted(true);
				kList.add(k);
			}
		} catch (Exception e) {
			e.printStackTrace();
			kList = null;
		} finally {
			closeStatement(statement);
		}
		return kList;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageDataModel#getKeyAttributesForDMKey(org.processmining.openslex.SLEXDMKey)
	 */
	@Override
	public List<SLEXDMKeyAttribute> getKeyAttributesForDMKey(SLEXDMKey k) {
		List<SLEXDMKeyAttribute> katList = new Vector<>();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+DATAMODEL_ALIAS+".key_attribute WHERE keyID = "+k.getId());
			while (rset.next()) {
				int keyId = rset.getInt("keyID");
				int attributeId = rset.getInt("attributeID");
				int refersTo = rset.getInt("refersTo");
				int position = rset.getInt("position");
				SLEXDMKeyAttribute kat = new SLEXDMKeyAttribute(this,keyId,attributeId);
				kat.setRefersToId(refersTo);
				kat.setPosition(position);
				kat.setDirty(false);
				kat.setInserted(true);
				katList.add(kat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			katList = null;
		} finally {
			closeStatement(statement);
		}
		return katList;
	}
	
	/**
	 * Gets the perspective.
	 *
	 * @param id the id
	 * @return the perspective
	 */
	protected SLEXPerspective getPerspective(int id) {
		SLEXPerspective p = null;
		SLEXPerspectiveResultSet ecrset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+PERSPECTIVE_ALIAS+".perspective WHERE id = '"+id+"'");
			ecrset = new SLEXPerspectiveResultSet(this, rset);
			p = ecrset.getNext();
			closeStatement(statement);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return p; 
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#getEventsOfTrace(org.processmining.openslex.SLEXTrace)
	 */
	public /*protected*/ SLEXEventResultSet getEventsOfTrace(SLEXTrace t) {
		if (!collection_attached) {
			SLEXPerspective p = getPerspective(t.getPerspectiveId());
			File f = new File(p.getStorage().getPath()+File.separator+p.getCollectionFileName());
			if (!f.exists()) {
				f = new File(p.getCollectionFileName());
				if (!f.exists()) {
					System.err.println("Error: collection file "+p.getCollectionFileName()+" not found");
					return null;
				}
			}
			openCollectionStorage(f.getParent(),f.getName());
		}
		SLEXEventResultSet erset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+COLLECTION_ALIAS+".event AS E, "+PERSPECTIVE_ALIAS+".trace_has_event AS TE WHERE E.id = TE.eventID AND TE.traceID='"+t.getId()+"' ORDER BY TE.ordering");
			erset = new SLEXEventResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return erset; 
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#getNumberEventsOfTrace(org.processmining.openslex.SLEXTrace)
	 */
	public /*protected*/ int getNumberEventsOfTrace(SLEXTrace t) {
		// TEST
		int events = 0;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT count(TE.eventID) FROM "+PERSPECTIVE_ALIAS+".trace_has_event AS TE WHERE TE.traceID='"+t.getId()+"'");
			if (rset.next()) {
				events = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		return events;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#createTrace(int, int, java.lang.String)
	 */
	@Override
	public SLEXTrace createTrace(int traceId, int perspectiveId, String caseId) {
		SLEXTrace t = new SLEXTrace(this);
		t.setId(traceId);
		t.setPerspectiveId(perspectiveId);
		t.setCaseId(caseId);
		if (isAutoCommitOnCreationEnabled()) {
			t.commit();
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#createPerspective(org.processmining.openslex.SLEXEventCollection, java.lang.String)
	 */
	@Override
	public SLEXPerspective createPerspective(SLEXEventCollection evCol,
			String name) {
		SLEXPerspective p = new SLEXPerspective(this);
		p.setCollectionId(evCol.getId());
		p.setName(name);
		p.setCollectionFileName(evCol.getStorage().getFilename());
		if (isAutoCommitOnCreationEnabled()) {
			p.commit();
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#cloneTrace(org.processmining.openslex.SLEXTrace, int)
	 */
	@Override
	public SLEXTrace cloneTrace(SLEXTrace t, int idNewTrace) {
		SLEXTrace ct = this.createTrace(idNewTrace,t.getPerspectiveId(), t.getCaseId());		
		Statement statement = null;
		try {
			statement = connection.createStatement();
			//statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+PERSPECTIVE_ALIAS+".trace_has_event (traceID,eventID,ordering) "+
							" SELECT '"+ct.getId()+"', eventID, ordering FROM "+PERSPECTIVE_ALIAS+".trace_has_event "+
							" WHERE traceID = '"+t.getId()+"' ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
		}
		return ct;
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#addEventToTrace(int, int)
	 */
	public /*protected*/ boolean addEventToTrace(int traceId, int eventId) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+PERSPECTIVE_ALIAS+".trace_has_event (traceID,eventID,ordering) VALUES ('"+traceId+"','"+eventId+"',(SELECT IFNULL(MAX(ordering), 0) + 1 FROM "+PERSPECTIVE_ALIAS+".trace_has_event))");
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#addEventToTrace(org.processmining.openslex.SLEXTrace, org.processmining.openslex.SLEXEvent)
	 */
	public /*protected*/ boolean addEventToTrace(SLEXTrace t, SLEXEvent e) {
		return addEventToTrace(t.getId(),e.getId());
	}

	/**
	 * Removes the events from trace.
	 *
	 * @param t the t
	 * @return true, if successful
	 */
	private boolean removeEventsFromTrace(SLEXTrace t) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("DELETE FROM "+PERSPECTIVE_ALIAS+".trace_has_event WHERE traceID = '"+t.getId()+"'");
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#removeTraceFromPerspective(org.processmining.openslex.SLEXPerspective, org.processmining.openslex.SLEXTrace)
	 */
	public /*protected*/ boolean removeTraceFromPerspective(SLEXPerspective p,
			SLEXTrace t) {
		Statement statement = null;
		boolean result = false;
		try {
			result = removeEventsFromTrace(t);
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("DELETE FROM "+PERSPECTIVE_ALIAS+".trace WHERE id = '"+t.getId()+"'");
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#getTracesOfPerspective(org.processmining.openslex.SLEXPerspective)
	 */
	public /*protected*/ SLEXTraceResultSet getTracesOfPerspective(
			SLEXPerspective p) {
		SLEXTraceResultSet trset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+PERSPECTIVE_ALIAS+".trace WHERE perspectiveID = "+p.getId());
			trset = new SLEXTraceResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return trset; 
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStorageCollection#getEventCollection(int)
	 */
	@Override
	public SLEXEventCollection getEventCollection(int id) {
		SLEXEventCollectionResultSet ecrset = null;
		SLEXEventCollection ec = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+COLLECTION_ALIAS+".collection WHERE id = '"+id+"'");
			ecrset = new SLEXEventCollectionResultSet(this, rset);
			ec = ecrset.getNext();
			ecrset.close();
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return ec; 
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXStoragePerspective#getMaxTraceId()
	 */
	@Override
	public int getMaxTraceId() {
		int maxId = 0;
		
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT IFNULL(MAX(id),0) + 1 FROM "+PERSPECTIVE_ALIAS+".trace");
			rset.next();
			maxId = rset.getInt(1);
			rset.close();
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
				
		return maxId;
	}
	
}
