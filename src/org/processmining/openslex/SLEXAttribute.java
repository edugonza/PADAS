package org.processmining.openslex;

public class SLEXAttribute extends SLEXDatabaseObject {

	private int id = -1;
	private String name = null;
	private int classId = -1;
	
	protected SLEXAttribute(LogStorage storage) {
		super(storage);
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getClassId() {
		return this.classId;
	}
	
	public String getName() {
		return this.name;
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	protected void setClassId(int id) {
		this.classId = id;
		setDirty(true);
	}
	
	protected void setName(String name) {
		this.name = name;
		setDirty(true);
	}
	
	@Override
	boolean insert(SLEXDatabaseObject at) {
		return storage.insert((SLEXAttribute) at);
	}

	@Override
	boolean update(SLEXDatabaseObject at) {
		return storage.update((SLEXAttribute) at);
	}

	@Override
	public String toString() {
		return getClassId()+":"+getId()+":"+getName();
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			if (obj instanceof SLEXAttribute) {
				SLEXAttribute objat = (SLEXAttribute) obj;
				if (this.getId() == objat.getId() &&
						this.getClassId() == objat.getClassId() &&
						this.getName().equals(objat.getName())) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
}
