package org.processmining.openslex;

public class SLEXDMKeyAttribute extends SLEXAbstractDatabaseObject {

	private int keyId = -1;
	private int attributeId = -1;
	private int refersToId = -1;
	private int position = 0;
	
	public static final int REFERS_TO_NULL = -1;
	
	protected SLEXDMKeyAttribute(SLEXStorage storage, int keyId, int attributeId) {
		super(storage);
		this.keyId = keyId;
		this.attributeId = attributeId;
	}
	
	public int getKeyId() {
		return this.keyId;
	}
	
	public int getAttributeId() {
		return this.attributeId;
	}
	
	public int getRefersToId() {
		return this.refersToId;
	}
		
	public int getPosition() {
		return this.position;
	}
	
	public void setPosition(int position) {
		this.position = position;
		setDirty(true);
	}
	
	protected void setRefersToId(int refersToId) {
		this.refersToId = refersToId;
		setDirty(true);
	}
	
	@Override
	boolean insert(SLEXAbstractDatabaseObject at) {
		return storage.insert((SLEXDMKeyAttribute) at);
	}

	@Override
	boolean update(SLEXAbstractDatabaseObject at) {
		return storage.update((SLEXDMKeyAttribute) at);
	}

	@Override
	public String toString() {
		return getKeyId()+":"+getAttributeId()+":"+getRefersToId();
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
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
