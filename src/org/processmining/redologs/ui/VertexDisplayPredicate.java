package org.processmining.redologs.ui;

import org.apache.commons.collections15.Predicate;
import org.processmining.redologs.common.GraphNode;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;

public class VertexDisplayPredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
	
	protected boolean filter_small;
	protected boolean filter_traceId;
	protected final static int MIN_DEGREE = 4;

	public VertexDisplayPredicate(boolean filter) {
		this.filter_small = filter;
		this.filter_traceId = filter;
	}

	public void filterExtraFields(boolean b) {
		filter_small = b;
	}
	
	public void filterTraceId(boolean b) {
		filter_traceId = b;
	}

	@Override
	public boolean evaluate(Context<Graph<V, E>, V> context) {
		Graph<V,E> graph = context.graph;
		V node = context.element;
		
		boolean filter = false;
		
		if (filter_small) {
			if (node instanceof GraphNode) {
				filter = !((GraphNode) node).filter;
			} else {
				filter = graph.degree(node) >= MIN_DEGREE;
			}
		} else {
			filter = true;
		}
		
		if (!filter) {
			return filter;
		}
		
		if (filter_traceId) {
			if (node instanceof GraphNode) {
				filter = ((GraphNode) node).traceIdSet;
			}
		}
		
		return filter;
	}
}