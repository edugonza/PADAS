package org.processmining.database.metamodel;

import java.util.HashMap;

import org.processmining.openslex.SLEXEvent;
import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.TraceID;

public class ObjectVersion {
	private TraceID objectId;
	private SLEXEvent event;
	private HashMap<Column,String> attributeValues;
	private long order;
	
	public ObjectVersion(TraceID objID, SLEXEvent event, long order) {
		this.objectId = objID;
		this.event = event;
		this.attributeValues = new HashMap<>();
		this.order = order;
	}
	
	public void addAttributeValue(Column c, String v) {
		this.attributeValues.put(c,v);
	}
	
	public HashMap<Column,String> getAttributeValues() {
		return this.attributeValues;
	}
	
	public TraceID getObjectID() {
		return this.objectId;
	}
	
	public SLEXEvent getEvent() {
		return this.event;
	}
	
	public long getOrder() {
		return order;
	}
}
