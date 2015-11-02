package org.processmining.database.redologs.common;

import java.util.Hashtable;
import edu.uci.ics.jung.graph.Graph;

public class RelationResult {
	public Graph<String,String> graph;
	public Hashtable<String,RelationsGraphNode> relations;
}
