package org.processmining.openslex;

public class SLEXPerspective extends SLEXAbstractDatabaseObject {

	private int id = -1;
	private int collectionId = -1;
	private String name = null;
	
	protected SLEXPerspective(SLEXStorage storage) {
		super(storage);
	}

	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getCollectionId() {
		return this.collectionId;
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	protected void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
		setDirty(true);
	}
	
	protected void setName(String name) {
		this.name = name;
		setDirty(true);
	}
	
	public SLEXTraceResultSet getTracesResultSet() {
		return storage.getTracesOfPerspective(this);
	}
	
	@Override
	boolean insert(SLEXAbstractDatabaseObject p) {
		return storage.insert((SLEXPerspective) p);
	}

	@Override
	boolean update(SLEXAbstractDatabaseObject p) {
		return storage.update((SLEXPerspective) p);
	}

	public boolean remove(SLEXTrace t) {
		return storage.removeTraceFromPerspective(this,t);
	}

	@Override
	public String toString() {
		return String.valueOf(this.getId());
	}
}
