package org.processmining.database.metamodel;

import org.processmining.openslex.SLEXEvent;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.common.TraceID;

public class Relation {
	
	private TableInfo from_table;
	private TableInfo to_table;
	private TraceID from_object;
	private TraceID to_object;
	private Key fk;
	private SLEXEvent event;
	private ObjectVersion start_source_obj_version;
	private ObjectVersion end_source_obj_version;
	private ObjectVersion start_target_obj_version;
	private ObjectVersion end_target_obj_version;
	
	public Relation(Key k, TableInfo from_t, TableInfo to_t, TraceID from_obj, TraceID to_obj) {
		this.fk = k;
		this.from_table = from_t;
		this.to_table = to_t;
		this.from_object = from_obj;
		this.to_object = to_obj;
	}

	public void setFromEvent(SLEXEvent e) {
		this.event = e;
	}
	
	public SLEXEvent getEvent() {
		return this.event;
	}
	
	public TableInfo getFromTable() {
		return this.from_table;
	}
	
	public TableInfo getToTable() {
		return this.to_table;
	}
	
	public TraceID getFromObject() {
		return this.from_object;
	}
	
	public TraceID getToObject() {
		return this.to_object;
	}
	
	public Key getRelationshipKey() {
		return this.fk;
	}
	
	public void setStartSourceObjectVersion(ObjectVersion v) {
		this.start_source_obj_version = v;
	}
	
	public void setEndSourceObjectVersion(ObjectVersion v) {
		this.end_source_obj_version = v;
	}
	
	public void setStartTargetObjectVersion(ObjectVersion v) {
		this.start_source_obj_version = v;
	}
	
	public void setEndTargetObjectVersion(ObjectVersion v) {
		this.end_source_obj_version = v;
	}
	
	public ObjectVersion getStartSourceObjectVersion() {
		return this.start_source_obj_version;
	}
	
	public ObjectVersion getEndSourceObjectVersion() {
		return this.end_source_obj_version;
	}
	
	public ObjectVersion getStartTargetObjectVersion() {
		return this.start_target_obj_version;
	}
	
	public ObjectVersion getEndTargetObjectVersion() {
		return this.end_target_obj_version;
	}
}
