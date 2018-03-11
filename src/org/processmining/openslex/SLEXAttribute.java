package org.processmining.openslex;
/*
 * 
 */


import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXAttribute.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXAttribute extends SLEXAbstractDatabaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3385250181392750894L;

	/** The id. */
	private int id = -1;
	
	/** The name. */
	private String name = null;
	
	/** The class id. */
	private int classId = -1;
	
	/**
	 * Instantiates a new SLEX attribute.
	 *
	 * @param storage the storage
	 */
	protected SLEXAttribute(SLEXStorageCollection storage) {
		super(storage);
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
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject at) {
		return getStorage().insert((SLEXAttribute) at);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject at) {
		return getStorage().update((SLEXAttribute) at);
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
			if (obj instanceof SLEXAttribute) {
				SLEXAttribute objat = (SLEXAttribute) obj;
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
