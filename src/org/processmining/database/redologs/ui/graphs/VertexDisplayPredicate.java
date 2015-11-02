package org.processmining.database.redologs.ui.graphs;

import org.apache.commons.collections15.Predicate;
import org.processmining.database.redologs.common.GraphNode;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;

public class VertexDisplayPredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
	
	private boolean filter_small;
	
	public boolean isFilter_small() {
		return filter_small;
	}

	public void setFilter_small(boolean filter_small) {
		this.filter_small = filter_small;
	}

	private boolean filter_traceId;
	
	public boolean isFilter_traceId() {
		return filter_traceId;
	}

	public void setFilter_traceId(boolean filter_traceId) {
		this.filter_traceId = filter_traceId;
	}

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