package org.processmining.openslex;
/*
 * 
 */



// TODO: Auto-generated Javadoc
/**
 * The Class SLEXAbstractDatabaseObject.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
abstract class SLEXAbstractDatabaseObject {
	
	/** The storage. */
	protected SLEXStorage storage = null;
	
	/** The dirty. */
	private boolean dirty = true;
	
	/** The inserted. */
	private boolean inserted = false;
	
	/**
	 * Instantiates a new SLEX abstract database object.
	 */
	public SLEXAbstractDatabaseObject() {
	}
	
	/**
	 * Instantiates a new SLEX abstract database object.
	 *
	 * @param storage the storage
	 */
	public SLEXAbstractDatabaseObject(SLEXStorage storage) {
		this.storage = storage;
	}
	
	/**
	 * Sets the inserted.
	 *
	 * @param inserted the new inserted
	 */
	protected void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	
	/**
	 * Checks if is inserted.
	 *
	 * @return true, if is inserted
	 */
	protected boolean isInserted() {
		return this.inserted;
	}
	
	/**
	 * Checks if is dirty.
	 *
	 * @return true, if is dirty
	 */
	protected boolean isDirty() {
		return this.dirty;
	}
	
	/**
	 * Sets the dirty.
	 *
	 * @param dirty the new dirty
	 */
	protected void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	/**
	 * Insert.
	 *
	 * @param dbob the dbob
	 * @return true, if successful
	 */
	abstract boolean insert(SLEXAbstractDatabaseObject dbob);
	
	/**
	 * Update.
	 *
	 * @param dbob the dbob
	 * @return true, if successful
	 */
	abstract boolean update(SLEXAbstractDatabaseObject dbob);
	
	/**
	 * Commit.
	 *
	 * @return true, if successful
	 */
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
