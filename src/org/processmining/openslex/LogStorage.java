package org.processmining.openslex;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class LogStorage {
	
	private static final String STORAGE_COLLECTION = "data"+File.separator+"collections.db";
	private static final String STORAGE_DATAMODEL = "data"+File.separator+"datamodels.db";
	
	private static final String COLLECTION_ALIAS = "collectionsdb";
	private static final String DATAMODEL_ALIAS = "datamodelsdb";
	
	private static final String COLLECTION_SCHEMA = "schemas"+File.separator+"collections.sql";
	private static final String DATAMODEL_SCHEMA = "schemas"+File.separator+"datamodels.sql"; 
	
	public static final String COMMON_CLASS_NAME = "COMMON";
	private static final String JOURNAL_MODE = "TRUNCATE";
	
	private boolean collection_attached = false;
	private boolean datamodel_attached = false;
	
	private boolean autoCommitOnCreation = true;
	
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
	
	private void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void closeResultSet(ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean attachDatabaseFile(String filename, String alias, String schema) {
		Statement statement = null;
		boolean result = false;
		try {
			File f = new File(filename);
			if (!f.exists()) {
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			
			if (checkSchema(filename,alias,schema)) {
			
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
		}
		return result;
	}
	
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
	
	private boolean setJournalMode(String mode) {
		if (mode != null) {
			Statement statement = null;
			boolean result = false;
			try {
				statement = connection.createStatement();
				statement.setQueryTimeout(30);
				statement.execute("PRAGMA journal_mode = "+mode);
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
	
	private void init() throws ClassNotFoundException {
		
		Class.forName("org.sqlite.JDBC");
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite::memory:");
			
			setJournalMode("OFF");
			
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
	
	public void setAutoCommitOnCreation(boolean flag) {
		this.autoCommitOnCreation = flag;
	}
	
	public boolean isAutoCommitOnCreationEnabled() {
		return this.autoCommitOnCreation;
	}
	
	public SLEXEventCollection createEventCollection(String name) {
		SLEXEventCollection ec = new SLEXEventCollection(this, name);
		if (isAutoCommitOnCreationEnabled()) {
			ec.commit();
		}
		return ec;
	}
	
	public SLEXEvent createEvent(int collectionId) {
		SLEXEvent ev = new SLEXEvent(this);
		ev.setCollectionId(collectionId);
		if (isAutoCommitOnCreationEnabled()) {
			ev.commit();
		}
		return ev;
	}
	
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
	
	public SLEXAttribute findAttribute(String className, String name) {
		SLEXAttribute at = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rset = statement.executeQuery("SELECT AT.id,AT.name,AT.classID FROM "
														+COLLECTION_ALIAS+".attribute AS AT, "
														+COLLECTION_ALIAS+".class as CL WHERE "
															+" AT.name = '"+name+"' AND "
															+" AT.classID = CL.id AND "
															+" CL.name = '"+className+"'");
			
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
	
	public SLEXAttribute findOrCreateAttribute(String className, String name, boolean common) {
		
		SLEXAttribute at = findAttribute(className, name);
		
		if (at == null) {
			at = createAttribute(className, name, common);
		}
		
		return at;
	}
	
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
	
	public SLEXAttributeValue createAttributeValue(int attributeId, int eventId, String value) {
		SLEXAttributeValue av = new SLEXAttributeValue(this,attributeId,eventId);
		av.setValue(value);
		if (isAutoCommitOnCreationEnabled()) {
			av.commit();
		}
		return av;
	}
	
	public SLEXClass createClass(String name, boolean common) {
		SLEXClass cl = new SLEXClass(this,name,common);
		if (isAutoCommitOnCreationEnabled()) {
			cl.commit();
		}
		return cl;
	}
	
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
	
	protected boolean insert(SLEXEventCollection ec) {
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
	
	protected boolean update(SLEXEventCollection ec) {
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
	
	protected boolean insert(SLEXEvent ev) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+COLLECTION_ALIAS+".event (collectionID) VALUES ('"+ev.getCollectionId()+"')");
			ev.setId(getLastInsertedRowId(statement));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
	protected boolean update(SLEXEvent ev) {
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
	
	protected boolean insert(SLEXAttribute at) {
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
	
	protected boolean update(SLEXAttribute at) {
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
	
	protected boolean insert(SLEXAttributeValue av) {
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
	
	protected boolean update(SLEXAttributeValue av) {
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
	
	protected boolean insert(SLEXClass cl) {
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
	
	protected boolean update(SLEXClass cl) {
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
	
	protected boolean insert(SLEXPerspective p) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO "+COLLECTION_ALIAS+".perspective (name,collectionID) VALUES ('"+p.getName()+"','"+p.getCollectionId()+"')");
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
	
	protected boolean update(SLEXPerspective p) {
		Statement statement = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("UPDATE "+COLLECTION_ALIAS+".perspective SET name = '"+p.getName()+"', collectionID = '"+p.getCollectionId()+"' WHERE id = '"+p.getId()+"'");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeStatement(statement);
		}
		
		return result;
	}
	
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

	public SLEXPerspectiveResultSet getPerspectives() {
		SLEXPerspectiveResultSet ecrset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+COLLECTION_ALIAS+".perspective");
			ecrset = new SLEXPerspectiveResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return ecrset; 
	}
	
	public SLEXPerspectiveResultSet getPerspectivesOfCollection(SLEXEventCollection ec) {
		SLEXPerspectiveResultSet ecrset = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("SELECT * FROM "+COLLECTION_ALIAS+".perspective WHERE collectionID = "+ec.getId());
			ecrset = new SLEXPerspectiveResultSet(this, rset);
		} catch (Exception e) {
			e.printStackTrace();
			closeStatement(statement);
		}
		
		return ecrset; 
	}
	
	protected SLEXEventResultSet getEventsOfCollection(
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

	protected Hashtable<SLEXAttribute, SLEXAttributeValue> getAttributeValuesForEvent(
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
}
