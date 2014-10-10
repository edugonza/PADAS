package org.processmining.openslex;

public class SLEXTrace extends SLEXAbstractDatabaseObject {

	private int id = -1;
	private String caseId = null;
	private int perspectiveId = -1;
	
	protected SLEXTrace(SLEXStorage storage) {
		super(storage);
	}

	public int getId() {
		return this.id;
	}
	
	public String getCaseId() {
		return this.caseId;
	}
	
	public int getPerspectiveId() {
		return this.perspectiveId;
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	protected void setPerspectiveId(String caseId) {
		this.caseId = caseId;
		setDirty(true);
	}
	
	protected void setPerspectiveId(int perspectiveId) {
		this.perspectiveId = perspectiveId;
		setDirty(true);
	}
	
	@Override
	boolean insert(SLEXAbstractDatabaseObject t) {
		return storage.insert((SLEXTrace) t);
	}

	@Override
	boolean update(SLEXAbstractDatabaseObject t) {
		return storage.update((SLEXTrace) t);
	}

}
