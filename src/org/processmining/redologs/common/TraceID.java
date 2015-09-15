package org.processmining.redologs.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

public class TraceID implements Serializable, Comparable {
	
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
	
	@Override
	public boolean equals(Object obj) {
		TraceID tid = null;
		if (obj instanceof TraceID) {
			tid = (TraceID) obj;
		} else {
			return false;
		}
		
		if (super.equals(tid)) {
			return true;
		}
		
		if (tp.equals(tid.tp)) {
			if (tval.size() == tid.tval.size()) {
				for (Entry<Column,String> e: tval.entrySet()) {
					String val1 = tid.tval.get(e.getKey());
					String val2 = e.getValue();
					if (val1 == null || val2 == null) {
						if (val1 != val2) {
							return false;
						}
					} else {
						if (!val1.equals(val2)) {
							return false;
						}
					}
				}
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		String aux = tp.hashCode()+"#"+tval.hashCode();
		return aux.hashCode();
	}
	
	public String serialize() {
		List<String> tidValues = new ArrayList<String>();
		for (Column c: tp.getPAList()) {
			tidValues.add(getValue(c));
		}
		
		return tidValues.toString();
	}

	@Override
	public int compareTo(Object o) {
		if (this.equals(o)) {
			return 0;
		} else {
			if (this.hashCode() > o.hashCode()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
