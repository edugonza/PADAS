package org.processmining.openslex;


abstract class SLEXAbstractDatabaseObject {
	protected SLEXStorage storage = null;
	private boolean dirty = true;
	private boolean inserted = false;
	
	public SLEXAbstractDatabaseObject(SLEXStorage storage) {
		this.storage = storage;
	}
	
	protected void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	
	protected boolean isInserted() {
		return this.inserted;
	}
	
	protected boolean isDirty() {
		return this.dirty;
	}
	
	protected void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	abstract boolean insert(SLEXAbstractDatabaseObject dbob);
	
	abstract boolean update(SLEXAbstractDatabaseObject dbob);
	
	public boolean commit() {
		
		if (!isInserted()) {
			if (insert(this)) {
				setInserted(true);
				setDirty(false);
			}
		} else if (isDirty()){
			if (update(this)) {
				setDirty(false);
			}
		}
		
		return !isDirty();
	}
}
