package org.processmining.redologs.miner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CountMatrix {

	private Set<String> activities;
	private HashMap<String,HashMap<String,Integer>> map;
	
	public CountMatrix() {
		map = new HashMap<>();
		activities = new HashSet<>();
	}
	
	public Set<String> getActivities() {
		return activities;
	}
	
	public void setCount(String a, String b, int count) {
		activities.add(a);
		activities.add(b);
		
		HashMap<String, Integer> aRow = map.get(a);
		if (aRow == null) {
			aRow = new HashMap<>();
			map.put(a, aRow);
		}
		
		aRow.put(b, count);
	}
	
	public void incCount(String a, String b) {
		activities.add(a);
		activities.add(b);
		
		HashMap<String, Integer> aRow = map.get(a);
		if (aRow == null) {
			aRow = new HashMap<>();
			map.put(a, aRow);
		}
		
		Integer count = aRow.get(b);
		if (count == null) {
			count = 0;
		}
		count++;
		
		aRow.put(b, count);
	}
	
	public int getCount(String a, String b) {
		HashMap<String, Integer> aRow = map.get(a);
		if (aRow == null) {
			aRow = new HashMap<>();
			map.put(a, aRow);
		}
		
		Integer count = aRow.get(b);
		if (count == null) {
			count = 0;
		}
		
		return count;
	}
	
}
