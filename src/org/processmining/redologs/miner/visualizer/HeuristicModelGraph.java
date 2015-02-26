package org.processmining.redologs.miner.visualizer;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.DirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraphEdge;
import org.processmining.models.graphbased.directed.DirectedGraphNode;
import org.processmining.redologs.miner.DGraph;
import org.processmining.redologs.miner.HeuristicModel;

public class HeuristicModelGraph implements DirectedGraph<DirectedGraphNode, DirectedGraphEdge<? extends DirectedGraphNode, ? extends DirectedGraphNode>> {

	private HeuristicModel hm;
	private Set<DirectedGraphNode> nodes;
	private Set<DirectedGraphEdge<? extends DirectedGraphNode, ? extends DirectedGraphNode>> edges;
	private HashMap<DirectedGraphNode,Set<DirectedGraphEdge<? extends DirectedGraphNode, ? extends DirectedGraphNode>>> edgesIn;
	private HashMap<DirectedGraphNode,Set<DirectedGraphEdge<? extends DirectedGraphNode, ? extends DirectedGraphNode>>> edgesOut;
	
	private AttributeMap attMap;
	private AttributeMap specialAttMap;
	
	public HeuristicModelGraph(HeuristicModel hm, AttributeMap attMap, AttributeMap specialAttMap, HashSet<Entry<String,String>> specialEdges) {
		this.hm = hm;
		this.attMap = attMap;
		this.specialAttMap = specialAttMap;
		nodes = new HashSet<>();
		HashMap<String,DirectedGraphNode> nodesMap = new HashMap<>();
		for (String activity: hm.getDependencyGraph().getActivities()) {
			HeuristicModelNode n = new HeuristicModelNode(activity, this, attMap);
			nodesMap.put(activity, n);
			nodes.add(n);
		}
		
		edges = new HashSet<>();
		edgesIn = new HashMap<>();
		edgesOut = new HashMap<>();
		for (Entry<String,String> entry: hm.getDependencyGraph().getEntries()) {
			String a = entry.getKey();
			String b = entry.getValue();
			
			HeuristicModelEdge edge;
			if (specialEdges.contains(entry)) {
				edge = new HeuristicModelEdge("", this, nodesMap.get(a), nodesMap.get(b),specialAttMap);
			} else {
				edge = new HeuristicModelEdge("", this, nodesMap.get(a), nodesMap.get(b),attMap);
			}
			
			edges.add(edge);
			Set<DirectedGraphEdge<? extends DirectedGraphNode, ? extends DirectedGraphNode>> edgesInB = edgesIn.get(nodesMap.get(b));
			Set<DirectedGraphEdge<? extends DirectedGraphNode, ? extends DirectedGraphNode>> edgesOutA = edgesOut.get(nodesMap.get(a));
			
			if (edgesInB == null) {
				edgesInB = new HashSet<>();
			}
			
			if (edgesOutA == null) {
				edgesOutA = new HashSet<>();
			}
			
			edgesInB.add(edge);
			edgesOutA.add(edge);
		}
		
	}
	
	@Override
	public String getLabel() {
		return "Model";
	}

	@Override
	public DirectedGraph<?, ?> getGraph() {
		return this;
	}

	@Override
	public AttributeMap getAttributeMap() {
		return attMap;
	}

	@Override
	public int compareTo(
			DirectedGraph<DirectedGraphNode, DirectedGraphEdge<? extends DirectedGraphNode, ? extends DirectedGraphNode>> o) {
		if (nodes.equals(o.getNodes())) {
			if (edges.equals(o.getEdges())) {
				return 0;
			}
		}
		
		return 1;
	}

	@Override
	public Set<DirectedGraphNode> getNodes() {
		return nodes;
	}

	@Override
	public Set<DirectedGraphEdge<? extends DirectedGraphNode, ? extends DirectedGraphNode>> getEdges() {
		return edges;
	}
	
	@Override
	public Collection<DirectedGraphEdge<? extends DirectedGraphNode, ? extends DirectedGraphNode>> getInEdges(
			DirectedGraphNode node) {
		return edgesIn.get(node);
	}

	@Override
	public Collection<DirectedGraphEdge<? extends DirectedGraphNode, ? extends DirectedGraphNode>> getOutEdges(
			DirectedGraphNode node) {
		return edgesOut.get(node);
	}

	@Override
	public void removeEdge(DirectedGraphEdge edge) {
		edges.remove(edge);
		edgesIn.get(edge.getTarget()).remove(edge);
		edgesOut.get(edge.getSource()).remove(edge);
	}

	@Override
	public void removeNode(DirectedGraphNode cell) {
		nodes.remove(cell);
	}
	
}
