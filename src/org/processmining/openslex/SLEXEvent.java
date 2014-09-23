package org.processmining.openslex;

import java.util.Hashtable;

public class SLEXEvent extends SLEXDatabaseObject {

	private int id = -1;
	private int collectionId = -1;
	private Hashtable<SLEXAttribute, SLEXAttributeValue> attributeValues = null;
	
	protected SLEXEvent(LogStorage storage) {
		super(storage);
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
	
	protected void setCollectionId(int id) {
		this.collectionId = id;
		setDirty(true);
	}
	
	@Override
	boolean insert(SLEXDatabaseObject e) {
		return storage.insert((SLEXEvent)e);
	}

	@Override
	boolean update(SLEXDatabaseObject e) {
		return storage.update((SLEXEvent)e);
	}

	protected void retrieveAttributeValues() {
		attributeValues = storage.getAttributeValuesForEvent(this);
	}
	
	public Hashtable<SLEXAttribute, SLEXAttributeValue> getAttributeValues() {
		if (attributeValues == null) {
			retrieveAttributeValues();
		}
		return attributeValues;
	}

}
