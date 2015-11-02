package org.processmining.database.redologs.common;

import java.util.List;
import java.util.Vector;

public class RelationsGraphNode {
	public String category;
	public String name;
	public List<RelationsGraphNode> to = new Vector<>();
	public List<RelationsGraphNode> from = new Vector<>();
}
