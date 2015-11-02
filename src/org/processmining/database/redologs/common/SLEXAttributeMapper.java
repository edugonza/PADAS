package org.processmining.database.redologs.common;

import java.util.HashMap;

import org.processmining.openslex.SLEXAttribute;

public class SLEXAttributeMapper {
	
	//private SLEXStorage storage = null;
	private HashMap<SLEXAttribute,Column> attributesMap = new HashMap<>();
	private HashMap<Column,SLEXAttribute> columnsMap = new HashMap<>();
	
	public SLEXAttributeMapper(/*SLEXStorage storage*/) {
		//this.storage = storage;
	}

	public Column map(SLEXAttribute a) {
		return attributesMap.get(a);
	}
	
	public SLEXAttribute map(Column c) {
		return columnsMap.get(c);
	}
	
	public void put(SLEXAttribute a, Column c) {
		attributesMap.put(a, c);
		columnsMap.put(c, a);
	}

}
