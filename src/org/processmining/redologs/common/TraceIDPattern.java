package org.processmining.redologs.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class TraceIDPattern {
	
	private DataModel dm = null;
	private List<TraceIDPatternElement> pe = new Vector<TraceIDPatternElement>();
	private List<Column> pa = new Vector<>();
	private HashMap<Column,Column> canonicalCache = new HashMap<>();
	private HashMap<Column,List<Column>> inverseCanonicalCache = new HashMap<>();
	private boolean dirty = false;
	private TraceIDPatternElement root = null;
	
	public TraceIDPattern(DataModel dm) {
		this.dm = dm;
	}
	
	public void add(Column c) {
		add(new TraceIDPatternElement(c));
	}
	
	public void add(Key k) {
		add(new TraceIDPatternElement(k));
	}
	
	public void add(TraceIDPatternElement e) {
		pe.add(e);
		setDirty(true);
	}
	
	public List<TraceIDPatternElement> getPEList() {
		return pe;
	}
	
	public List<Column> getPAList() {
		if (isDirty()) {
			precomputeCanonicalization();
		}
		
		return pa;
	}
	
	public void setRoot(Column c) {
		root = new TraceIDPatternElement(c);
		setDirty(true);
	}
	
	public void setRoot(Key k) {
		root = new TraceIDPatternElement(k);
		setDirty(true);
	}
	
	public TraceIDPatternElement getRoot() {
		return root;
	}
	
	public boolean containsColumn(Column c) {
		for (TraceIDPatternElement e: pe) {
			if (e.isColumn()) {
				if (e.getColumn().equals(c)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean containsKey(Key k) {
		for (TraceIDPatternElement e: pe) {
			if (e.isKey()) {
				if (e.getKey().equals(k)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isDirty() {
		return this.dirty;
	}
	
	private void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	public Column getCanonical(Column c) {
		if (isDirty()) {
			precomputeCanonicalization();
		}
		
		Column cn = canonicalCache.get(c);
		if (cn == null) {
			return c;
		} else {
			return cn;
		}
	}
	
	public List<Column> getInverseCanonical(Column cn) {
		if (isDirty()) {
			precomputeCanonicalization();
		}
		
		return inverseCanonicalCache.get(cn);
	}
	
	public void precomputeCanonicalization() {
		canonicalCache = new HashMap<>();
		inverseCanonicalCache = new HashMap<>();
		pa = new Vector<>();
		for (TraceIDPatternElement e: pe) {
			List<Column> clist;
			if (e.isKey()) {
				clist = e.getKey().fields;
			} else {
				clist = new Vector<>();
				clist.add(e.getColumn());
			}
			
			for (Column c: clist) {
				List<Column> cnlist = LogTraceSplitter.canonicalize(dm, this, new TraceIDPatternElement(c));
				for (Column cn: cnlist) {
					if (!canonicalCache.containsKey(c)) {
						canonicalCache.put(c, cn);
					}
					if (inverseCanonicalCache.containsKey(cn)) {
						inverseCanonicalCache.get(cn).add(c);
					} else {
						inverseCanonicalCache.put(cn, new Vector<Column>(Arrays.asList(c)));
						pa.add(cn);
					}
				}
			}
		}
	}
	
}
