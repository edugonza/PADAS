package org.processmining.openslex;

public class SLEXAttributeValue extends SLEXDatabaseObject {

	private int attributeId = -1;
	private int eventId = -1;
	private String value = null;
	
	protected SLEXAttributeValue(LogStorage storage,int attributeId, int eventId) {
		super(storage);
		this.attributeId = attributeId;
		this.eventId = eventId;
	}
	
	public int getEventId() {
		return this.eventId;
	}
	
	public int getAttributeId() {
		return this.attributeId;
	}
	
	public String getValue() {
		return this.value;
	}
	
	protected void setValue(String value) {
		this.value = value;
		setDirty(true);
	}
	
	@Override
	boolean insert(SLEXDatabaseObject at) {
		return storage.insert((SLEXAttributeValue) at);
	}

	@Override
	boolean update(SLEXDatabaseObject at) {
		return storage.update((SLEXAttributeValue) at);
	}

}
