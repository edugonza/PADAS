package org.processmining.redologs.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

public class TraceID {
	
	private TraceIDPattern tp = null;
	private HashMap<Column,String> tval = new HashMap<>();
	
	public TraceID(TraceIDPattern tp) {
		this.tp = tp;
	}
	
	public void setValue(Column c, String v) {
		this.tval.put(c, v);
	}
	
	public String getValue(Column c) {
		return this.tval.get(c);
	}
	
	public List<Column> getPA() {
		return this.tp.getPAList();
	}
	
	public TraceID clone() {
		TraceID newTID = new TraceID(this.tp);
		newTID.tval = (HashMap<Column, String>) this.tval.clone();
		return newTID;
	}
	
	public boolean equals(TraceID tid) {
		
		if (tp.equals(tid.tp)) {
			if (tval.size() == tid.tval.size()) {
				for (Entry<Column,String> e: tval.entrySet()) {
					if (!tid.tval.get(e.getKey()).equals(e.getValue())) {
						return false;
					}
				}
				return true;
			}
		}
		
		return false;
	}

	public String serialize() {
		List<String> tidValues = new Vector<String>();
		for (Column c: tp.getPAList()) {
			tidValues.add(getValue(c));
		}
		
		return tidValues.toString();
	}
}
