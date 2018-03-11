package org.processmining.openslex;
/*
 * 
 */


// TODO: Auto-generated Javadoc
/**
 * The Class SLEXDMKeyAttribute.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXDMKeyAttribute extends SLEXAbstractDatabaseObject {

	/** The key id. */
	private int keyId = -1;
	
	/** The attribute id. */
	private int attributeId = -1;
	
	/** The refers to id. */
	private int refersToId = -1;
	
	/** The position. */
	private int position = 0;
	
	/** The Constant REFERS_TO_NULL. */
	public static final int REFERS_TO_NULL = -1;
	
	/**
	 * Instantiates a new SLEXDM key attribute.
	 *
	 * @param storage the storage
	 * @param keyId the key id
	 * @param attributeId the attribute id
	 */
	protected SLEXDMKeyAttribute(SLEXStorageDataModel storage, int keyId, int attributeId) {
		super(storage);
		this.keyId = keyId;
		this.attributeId = attributeId;
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
	 * Gets the key id.
	 *
	 * @return the key id
	 */
	public int getKeyId() {
		return this.keyId;
	}
	
	/**
	 * Gets the attribute id.
	 *
	 * @return the attribute id
	 */
	public int getAttributeId() {
		return this.attributeId;
	}
	
	/**
	 * Gets the refers to id.
	 *
	 * @return the refers to id
	 */
	public int getRefersToId() {
		return this.refersToId;
	}
		
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(int position) {
		this.position = position;
		setDirty(true);
	}
	
	/**
	 * Sets the refers to id.
	 *
	 * @param refersToId the new refers to id
	 */
	protected void setRefersToId(int refersToId) {
		this.refersToId = refersToId;
		setDirty(true);
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject at) {
		return getStorage().insert((SLEXDMKeyAttribute) at);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject at) {
		return getStorage().update((SLEXDMKeyAttribute) at);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getKeyId()+":"+getAttributeId()+":"+getRefersToId();
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
			if (obj instanceof SLEXDMKeyAttribute) {
				SLEXDMKeyAttribute objat = (SLEXDMKeyAttribute) obj;
				if (this.getKeyId() == objat.getKeyId() &&
						this.getAttributeId() == objat.getAttributeId() &&
						this.getRefersToId() ==objat.getRefersToId()) {
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
