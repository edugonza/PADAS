package org.processmining.openslex;

public class SLEXDMKey extends SLEXAbstractDatabaseObject {

	private int id = -1;
	private String name = null;
	private int classId = -1;
	private int type = -1;
	private int refers_to_keyId = -1;
	
	public static final int PRIMARY_KEY = 0;
	public static final int FOREIGN_KEY = 1;
	public static final int UNIQUE_KEY = 2;
	
	public static final int REFERS_TO_NULL = -1;
	
	protected SLEXDMKey(SLEXStorage storage) {
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
	
	public int getType() {
		return this.type;
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
	
	protected void setType(int type) {
		this.type = type;
		setDirty(true);
	}
	
	public int getRefersToKey() {
		return this.refers_to_keyId;
	}
	
	protected void setRefersToKey(int refers_to_key) {
		this.refers_to_keyId = refers_to_key;
		setDirty(true);
	}
	
	@Override
	boolean insert(SLEXAbstractDatabaseObject at) {
		return storage.insert((SLEXDMKey) at);
	}

	@Override
	boolean update(SLEXAbstractDatabaseObject at) {
		return storage.update((SLEXDMKey) at);
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
			if (obj instanceof SLEXDMKey) {
				SLEXDMKey objat = (SLEXDMKey) obj;
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
