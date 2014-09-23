package org.processmining.openslex;

import java.sql.ResultSet;
import java.sql.Statement;

public class SLEXEventCollection extends SLEXDatabaseObject {
	
	private int id = -1;
	private String name = null;
	
	protected SLEXEventCollection(LogStorage storage, String name) {
		super(storage);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
		setDirty(true);
	}
	
	protected void setId(int id) {
		this.id = id;
	}

	public boolean add(SLEXEvent event) {
		event.setCollectionId(this.id);
		return event.commit();
	}
	
	@Override
	boolean insert(SLEXDatabaseObject ec) {
		return storage.insert((SLEXEventCollection) ec);
	}

	@Override
	boolean update(SLEXDatabaseObject ec) {
		return storage.update((SLEXEventCollection) ec);
	}
	
	public SLEXEventResultSet getEventsResultSet() {
		return storage.getEventsOfCollection(this);
	}
	
	@Override
	public String toString() {
		return getId()+"";
	}
}
