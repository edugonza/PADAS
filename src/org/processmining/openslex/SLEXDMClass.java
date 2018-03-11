package org.processmining.openslex;
/*
 * 
 */


import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXDMClass.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXDMClass extends SLEXAbstractDatabaseObject {

	/** The id. */
	private int id = -1;
	
	/** The name. */
	private String name = null;
	
	/** The common. */
	private boolean common = false;
	
	/** The datamodel id. */
	private int datamodelId = -1;
	
	/** The attributes. */
	private List<SLEXDMAttribute> attributes = null;
	
	/** The keys. */
	private List<SLEXDMKey> keys = null;
	
	/**
	 * Instantiates a new SLEXDM class.
	 *
	 * @param storage the storage
	 * @param name the name
	 * @param common the common
	 * @param data_modelId the data_model id
	 */
	protected SLEXDMClass(SLEXStorageDataModel storage, String name, boolean common,int data_modelId) {
		super(storage);
		this.name = name;
		this.common = common;
		this.datamodelId = data_modelId;
	}
	
	/**
	 * Gets the storage.
	 *
	 * @return the storage
	 */
	public SLEXStorageDataModel getStorage() {
		return (SLEXStorageDataModel) super.storage;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Gets the data model id.
	 *
	 * @return the data model id
	 */
	public int getDataModelId() {
		return this.datamodelId;
	}
	
	/**
	 * Checks if is common.
	 *
	 * @return true, if is common
	 */
	public boolean isCommon() {
		return this.common;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	protected void setName(String name) {
		this.name = name;
		setDirty(true);
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	protected void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Sets the common.
	 *
	 * @param common the new common
	 */
	protected void setCommon(boolean common) {
		this.common = common;
		setDirty(true);
	}
	
	/**
	 * Sets the data model id.
	 *
	 * @param datamodelId the new data model id
	 */
	protected void setDataModelId(int datamodelId) {
		this.datamodelId = datamodelId;
		setDirty(true);
	}
	
	/**
	 * Retrieve attributes and keys.
	 */
	protected void retrieveAttributesAndKeys() {
		getAttributes();
		getKeys();
	}
	
	/**
	 * Retrieve attributes.
	 */
	protected void retrieveAttributes() {		
		this.attributes = getStorage().getAttributesForDMClass(this);
	}
	
	/**
	 * Retrieve keys.
	 */
	protected void retrieveKeys() {
		this.keys = getStorage().getKeysForDMClass(this);
	}
	
	/**
	 * Gets the keys.
	 *
	 * @return the keys
	 */
	public List<SLEXDMKey> getKeys() {
		if (this.keys == null) {
			retrieveKeys();
		}
		
		return this.keys;
	} 
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public List<SLEXDMAttribute> getAttributes() {
		if (this.attributes == null) {
			retrieveAttributes();
		}
		
		return this.attributes;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject cl) {
		return getStorage().insert((SLEXDMClass) cl);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject cl) {
		return getStorage().update((SLEXDMClass) cl);
	}

}
