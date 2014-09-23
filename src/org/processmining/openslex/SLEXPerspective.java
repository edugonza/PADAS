package org.processmining.openslex;

public class SLEXPerspective extends SLEXDatabaseObject {

	private int id = -1;
	private int collectionId = -1;
	private String name = null;
	
	protected SLEXPerspective(LogStorage storage) {
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
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	@Override
	boolean insert(SLEXDatabaseObject p) {
		return storage.insert((SLEXPerspective) p);
	}

	@Override
	boolean update(SLEXDatabaseObject p) {
		return storage.update((SLEXPerspective) p);
	}

}
