package org.processmining.redologs.ui;

import org.apache.commons.collections15.Predicate;
import org.processmining.redologs.common.GraphNode;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;

public class VertexDisplayPredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
	
	protected boolean filter_small;
	protected final static int MIN_DEGREE = 4;

	public VertexDisplayPredicate(boolean filter) {
		this.filter_small = filter;
	}

	public void filterSmall(boolean b) {
		filter_small = b;
	}

	@Override
	public boolean evaluate(Context<Graph<V, E>, V> context) {
		Graph<V,E> graph = context.graph;
		V node = context.element;
		if (filter_small) {
			if (node instanceof GraphNode) {
				return ((GraphNode) node).filter;
			} else {
				return graph.degree(node) >= MIN_DEGREE;
			}
		} else {
			return true;
		}
	}
}