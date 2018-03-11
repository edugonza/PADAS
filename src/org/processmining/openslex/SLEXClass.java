package org.processmining.openslex;
/*
 * 
 */


// TODO: Auto-generated Javadoc
/**
 * The Class SLEXClass.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXClass extends SLEXAbstractDatabaseObject {

	/** The id. */
	private int id = -1;
	
	/** The name. */
	private String name = null;
	
	/** The common. */
	private boolean common = false;
	
	/**
	 * Instantiates a new SLEX class.
	 *
	 * @param storage the storage
	 * @param name the name
	 * @param common the common
	 */
	protected SLEXClass(SLEXStorageCollection storage, String name, boolean common) {
		super(storage);
		this.name = name;
		this.common = common;
	}
	
	/**
	 * Gets the storage.
	 *
	 * @return the storage
	 */
	public SLEXStorageCollection getStorage() {
		return (SLEXStorageCollection) super.storage;
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
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject cl) {
		return getStorage().insert((SLEXClass) cl);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject cl) {
		return getStorage().update((SLEXClass) cl);
	}

}
