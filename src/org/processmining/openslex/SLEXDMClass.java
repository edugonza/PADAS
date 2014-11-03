package org.processmining.openslex;

import java.util.List;

public class SLEXDMClass extends SLEXAbstractDatabaseObject {

	private int id = -1;
	private String name = null;
	private boolean common = false;
	private int datamodelId = -1;
	private List<SLEXDMAttribute> attributes = null;
	private List<SLEXDMKey> keys = null;
	
	protected SLEXDMClass(SLEXStorage storage, String name, boolean common,int data_modelId) {
		super(storage);
		this.name = name;
		this.common = common;
		this.datamodelId = data_modelId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getDataModelId() {
		return this.datamodelId;
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
	
	protected void setDataModelId(int datamodelId) {
		this.datamodelId = datamodelId;
		setDirty(true);
	}
	
	protected void retrieveAttributesAndKeys() {
		getAttributes();
		getKeys();
	}
	
	protected void retrieveAttributes() {		
		this.attributes = storage.getAttributesForDMClass(this);
	}
	
	protected void retrieveKeys() {
		this.keys = storage.getKeysForDMClass(this);
	}
	
	public List<SLEXDMKey> getKeys() {
		if (this.keys == null) {
			retrieveKeys();
		}
		
		return this.keys;
	} 
	
	public List<SLEXDMAttribute> getAttributes() {
		if (this.attributes == null) {
			retrieveAttributes();
		}
		
		return this.attributes;
	}
	
	@Override
	boolean insert(SLEXAbstractDatabaseObject cl) {
		return storage.insert((SLEXDMClass) cl);
	}

	@Override
	boolean update(SLEXAbstractDatabaseObject cl) {
		return storage.update((SLEXDMClass) cl);
	}

}
