package org.processmining.openslex;

public class SLEXDMAttribute extends SLEXAbstractDatabaseObject {

	private int id = -1;
	private String name = null;
	private int classId = -1;
	
	protected SLEXDMAttribute(SLEXStorage storage) {
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
	boolean insert(SLEXAbstractDatabaseObject at) {
		return storage.insert((SLEXDMAttribute) at);
	}

	@Override
	boolean update(SLEXAbstractDatabaseObject at) {
		return storage.update((SLEXDMAttribute) at);
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
			if (obj instanceof SLEXDMAttribute) {
				SLEXDMAttribute objat = (SLEXDMAttribute) obj;
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
