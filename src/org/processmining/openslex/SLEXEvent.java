package org.processmining.openslex;
/*
 * 
 */


import java.io.Serializable;
import java.util.Hashtable;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXEvent.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXEvent extends SLEXAbstractDatabaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9201601695781731851L;

	/** The id. */
	private int id = -1;
	
	/** The collection id. */
	private int collectionId = -1;
	
	/** The attribute values. */
	private Hashtable<SLEXAttribute, SLEXAttributeValue> attributeValues = null;
	
	/**
	 * Instantiates a new SLEX event.
	 *
	 * @param storage the storage
	 */
	protected SLEXEvent(SLEXStorageCollection storage) {
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
	 * Gets the collection id.
	 *
	 * @return the collection id
	 */
	public int getCollectionId() {
		return this.collectionId;
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
	 * Sets the collection id.
	 *
	 * @param id the new collection id
	 */
	protected void setCollectionId(int id) {
		this.collectionId = id;
		setDirty(true);
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject e) {
		return getStorage().insert((SLEXEvent)e);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject e) {
		return getStorage().update((SLEXEvent)e);
	}

	/**
	 * Retrieve attribute values.
	 */
	protected void retrieveAttributeValues() {
		attributeValues = getStorage().getAttributeValuesForEvent(this);
	}
	
	/**
	 * Gets the attribute values.
	 *
	 * @return the attribute values
	 */
	public Hashtable<SLEXAttribute, SLEXAttributeValue> getAttributeValues() {
		if (attributeValues == null) {
			retrieveAttributeValues();
		}
		return attributeValues;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getCollectionId()+":"+getId();
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
			if (obj instanceof SLEXEvent) {
				SLEXEvent objat = (SLEXEvent) obj;
				if (this.getId() == objat.getId() &&
						this.getCollectionId() == objat.getCollectionId()) {
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
