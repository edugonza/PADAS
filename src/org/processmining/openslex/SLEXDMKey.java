package org.processmining.openslex;
/*
 * 
 */


// TODO: Auto-generated Javadoc
/**
 * The Class SLEXDMKey.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXDMKey extends SLEXAbstractDatabaseObject {

	/** The id. */
	private int id = -1;
	
	/** The name. */
	private String name = null;
	
	/** The class id. */
	private int classId = -1;
	
	/** The type. */
	private int type = -1;
	
	/** The refers_to_key id. */
	private int refers_to_keyId = -1;
	
	/** The Constant PRIMARY_KEY. */
	public static final int PRIMARY_KEY = 0;
	
	/** The Constant FOREIGN_KEY. */
	public static final int FOREIGN_KEY = 1;
	
	/** The Constant UNIQUE_KEY. */
	public static final int UNIQUE_KEY = 2;
	
	/** The Constant REFERS_TO_NULL. */
	public static final int REFERS_TO_NULL = -1;
	
	/**
	 * Instantiates a new SLEXDM key.
	 *
	 * @param storage the storage
	 */
	protected SLEXDMKey(SLEXStorageDataModel storage) {
		super(storage);
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Gets the class id.
	 *
	 * @return the class id
	 */
	public int getClassId() {
		return this.classId;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getType() {
		return this.type;
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
	 * Sets the class id.
	 *
	 * @param id the new class id
	 */
	protected void setClassId(int id) {
		this.classId = id;
		setDirty(true);
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
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	protected void setType(int type) {
		this.type = type;
		setDirty(true);
	}
	
	/**
	 * Gets the refers to key.
	 *
	 * @return the refers to key
	 */
	public int getRefersToKey() {
		return this.refers_to_keyId;
	}
	
	/**
	 * Sets the refers to key.
	 *
	 * @param refers_to_key the new refers to key
	 */
	protected void setRefersToKey(int refers_to_key) {
		this.refers_to_keyId = refers_to_key;
		setDirty(true);
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject at) {
		return getStorage().insert((SLEXDMKey) at);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject at) {
		return getStorage().update((SLEXDMKey) at);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClassId()+":"+getId()+":"+getName();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			if (obj instanceof SLEXDMKey) {
				SLEXDMKey objat = (SLEXDMKey) obj;
				if (this.getId() == objat.getId() &&
						this.getClassId() == objat.getClassId() &&
						this.getName().equals(objat.getName())) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
}
