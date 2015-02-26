package org.processmining.redologs.miner.visualizer;

import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.DirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraphEdge;
import org.processmining.models.graphbased.directed.DirectedGraphNode;

public class HeuristicModelEdge implements DirectedGraphEdge<DirectedGraphNode,DirectedGraphNode> {

	private String label = "";
	private DirectedGraph<?,?> graph;
	private AttributeMap attMap;
	private DirectedGraphNode source;
	private DirectedGraphNode target;
	
	public HeuristicModelEdge(String label, DirectedGraph<?,?> graph, DirectedGraphNode source, DirectedGraphNode target, AttributeMap attMap) {
		this.label = label;
		this.graph = graph;
		this.attMap = attMap;
		this.source = source;
		this.target = target;
	}
	
	public void setAttributeMap(AttributeMap attMap) {
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
	public DirectedGraphNode getSource() {
		return this.source;
	}

	@Override
	public DirectedGraphNode getTarget() {
		return this.target;
	}

}
