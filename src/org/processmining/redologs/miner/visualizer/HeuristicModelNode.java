package org.processmining.redologs.miner.visualizer;

import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.NodeID;
import org.processmining.models.graphbased.directed.DirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraphNode;

public class HeuristicModelNode implements DirectedGraphNode {

	private String label = "";
	private NodeID nid;
	private DirectedGraph<?,?> graph;
	private AttributeMap attMap;
	
	public HeuristicModelNode(String label, DirectedGraph<?,?> graph, AttributeMap attMap) {
		this.nid = new NodeID();
		this.label = label;
		this.graph = graph;
		this.attMap = attMap;
	}
	
	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public DirectedGraph<?, ?> getGraph() {
		return this.graph;
	}

	@Override
	public AttributeMap getAttributeMap() {
		return this.attMap;
	}

	@Override
	public int compareTo(DirectedGraphNode o) {
		if (getId().compareTo(o.getId()) != 0) {
			return getLabel().compareTo(o.getLabel());
		} else {
			return 0;
		}
	}

	@Override
	public NodeID getId() {
		return nid;
	}

}
