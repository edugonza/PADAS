package org.processmining.openslex;

public class SLEXClass extends SLEXAbstractDatabaseObject {

	private int id = -1;
	private String name = null;
	private boolean common = false;
	
	protected SLEXClass(SLEXStorage storage, String name, boolean common) {
		super(storage);
		this.name = name;
		this.common = common;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean isCommon() {
		return this.common;
	}
	
	protected void setName(String name) {
		this.name = name;
		setDirty(true);
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	protected void setCommon(boolean common) {
		this.common = common;
		setDirty(true);
	}
	
	@Override
	boolean insert(SLEXAbstractDatabaseObject cl) {
		return storage.insert((SLEXClass) cl);
	}

	@Override
	boolean update(SLEXAbstractDatabaseObject cl) {
		return storage.update((SLEXClass) cl);
	}

}
