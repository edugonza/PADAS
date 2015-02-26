package org.processmining.redologs.miner;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class DGraph {

	private Set<String> activities;
	private HashSet<Entry<String,String>> entries;
	private HashMap<String,HashSet<Entry<String,String>>> successorsMap;
	private HashMap<String,HashSet<Entry<String,String>>> causesMap;
	
	public DGraph() {
		activities = new HashSet<>();
		entries = new HashSet<>();
		successorsMap = new HashMap<>();
		causesMap = new HashMap<>();
	}
	
	public Set<String> getActivities() {
		return activities;
	}
	
	public void add(String a, String b) {
		activities.add(a);
		activities.add(b);
		
		Entry<String,String> entry = new AbstractMap.SimpleEntry<>(a,b);
		entries.add(entry);
		
		HashSet<Entry<String, String>> rowS = successorsMap.get(a);
		
		if (rowS == null) {
			rowS = new HashSet<>();
			successorsMap.put(a, rowS);
		}
		
		rowS.add(entry);
		
		HashSet<Entry<String, String>> rowC = causesMap.get(b);
		
		if (rowC == null) {
			rowC = new HashSet<>();
			causesMap.put(b, rowC);
		}
		
		rowC.add(entry);
	}
	
	public void remove(String a, String b) {
		Entry<String,String> entry = new AbstractMap.SimpleEntry<>(a,b);
		
		if (entries.contains(entry)) {			
			entries.remove(entry);
			
			boolean noSuccA = false;
			boolean noCausA = false;
			boolean noSuccB = false;
			boolean noCausB = false;
			
			HashSet<Entry<String, String>> rowS = successorsMap.get(a);
			
			if (rowS != null) {
				rowS.remove(entry);
				
				if (rowS.isEmpty()) {
					noSuccA = true;
				}
				
			} else {
				noSuccA = true;
			}
			
			if (noSuccA) {
				// Check if A has Causes
				HashSet<Entry<String, String>> rowCausA = causesMap.get(a);
				if (rowCausA == null || rowCausA.isEmpty()) {
					noCausA = true;
				}
			}
			
			HashSet<Entry<String, String>> rowC = causesMap.get(b);
			
			if (rowC != null) {
				rowC.remove(entry);
				if (rowC.isEmpty()) {
					noCausB = true;
				}
				
			} else {
				noCausB = true;
			}
			
			if (noCausB) {
				// Check if B has Successors
				HashSet<Entry<String, String>> rowSuccB = successorsMap.get(b);
				if (rowSuccB == null || rowSuccB.isEmpty()) {
					noSuccB = true;
				}
			}
			
			if (noSuccA && noCausA) {
				activities.remove(a);
			}
			if (noSuccB && noCausB) {
				activities.remove(b);
			}
		}
	}
	
	public boolean contains(String a, String b) {
		Entry<String,String> entry = new AbstractMap.SimpleEntry<>(a,b);
		
		return entries.contains(entry);
	}
	
	public Set<String> getFollowers(String a) {
		HashSet<Entry<String, String>> row = successorsMap.get(a);
		HashSet<String> followers = new HashSet<>();
		if (row != null) {
			for (Entry<String,String> e: row) {
				followers.add(e.getValue());
			}
		}
		return followers;
	}
	
	public Set<String> getCauses(String a) {
		HashSet<Entry<String, String>> row = causesMap.get(a);
		HashSet<String> causes = new HashSet<>();
		if (row != null) {
			for (Entry<String,String> e: row) {
				causes.add(e.getKey());
			}
		}
		return causes;
	}
	
	public Set<Entry<String,String>> getEntries() {
		return entries;
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("\nDGraph\n");
		
		str.append("Inputs,Activity,Outputs\n");
		
		for (String act: getActivities()) {
			Set<String> causes = getCauses(act);
			str.append("["+causes.toString()+"],");
			str.append(act);
			Set<String> followers = getFollowers(act);
			str.append(",["+followers.toString()+"]\n");
		}
		
		return str.toString();
	}
}
