package org.processmining.redologs.miner;

import java.util.HashSet;
import java.util.Map.Entry;

public class HeuristicModel {
	
	private DGraph depGraph;
	private HashSet<Entry<String,String>> specialEdges;
	
	public HeuristicModel(DGraph dgraph) {
		this.depGraph = dgraph;
		this.specialEdges = new HashSet<>();
	}
	
	public DGraph getDependencyGraph() {
		return this.depGraph;
	}
	
	public void setSpecialEdges(HashSet<Entry<String,String>> specEdges) {
		this.specialEdges = specEdges;
	}
	
	public HashSet<Entry<String,String>> getSpecialEdges() {
		return this.specialEdges;
	}
}
