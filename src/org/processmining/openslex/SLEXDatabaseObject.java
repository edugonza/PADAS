package org.processmining.openslex;


abstract class SLEXDatabaseObject {
	protected LogStorage storage = null;
	private boolean dirty = true;
	private boolean inserted = false;
	
	public SLEXDatabaseObject(LogStorage storage) {
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
	
	abstract boolean insert(SLEXDatabaseObject dbob);
	
	abstract boolean update(SLEXDatabaseObject dbob);
	
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
