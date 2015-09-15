package org.processmining.redologs.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class TraceIDPattern implements Serializable {
	
	private DataModel dm = null;
	private List<TraceIDPatternElement> pe = new  ArrayList<TraceIDPatternElement>();
	private List<Column> pa = new  ArrayList<>();
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
		pa = new ArrayList<>();
		for (TraceIDPatternElement e: pe) {
			List<Column> clist;
			if (e.isKey()) {
				clist = e.getKey().fields;
			} else {
				clist = new  ArrayList<>();
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
						inverseCanonicalCache.put(cn, new  ArrayList<Column>(Arrays.asList(c)));
						pa.add(cn);
					}
				}
			}
		}
		Collections.sort(pa);
		setDirty(false);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TraceIDPattern) {
			TraceIDPattern tpaux = (TraceIDPattern) obj;
			if (super.equals(tpaux)) {
				return true;
			}
			
			if (tpaux.hashCode() == this.hashCode()) {
				if (tpaux.getPAList().size() == this.getPAList().size()) {
					for (Column caux: tpaux.getPAList()) {
						if (!this.getPAList().contains(caux)) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		String rootHashStr = "null";
		if (root != null) {
			rootHashStr = String.valueOf(root.hashCode());
		}
		String paHashStr = "null";
		if (pa != null) {
			paHashStr = String.valueOf(getPAList().hashCode());
		}
		String aux = paHashStr+"#"+rootHashStr;
		return aux.hashCode();
	}
}
