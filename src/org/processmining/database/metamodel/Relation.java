package org.processmining.database.metamodel;

public class Relation {
	
	private ObjectVersion from_object_version;
	private ObjectVersion to_object_version;
	
	public Relation(ObjectVersion from, ObjectVersion to) {
		this.from_object_version = from;
		this.to_object_version = to;
	}
	
	public ObjectVersion getFromObjectVersion() {
		return this.from_object_version;
	}
	
	public ObjectVersion getToObjectVersion() {
		return this.to_object_version;
	}
	
	public void setFromObjectVersion(ObjectVersion v) {
		this.from_object_version = v;
	}
	
	public void setToObjectVersion(ObjectVersion v) {
		this.to_object_version = v;
	}
	
}
