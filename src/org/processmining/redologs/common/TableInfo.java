package org.processmining.redologs.common;

import java.util.List;

public class TableInfo extends GraphNode {
	public String db;
	public String name;
	public List<String> columns;
	
	@Override
	public String toString() {
		return db+"@"+name;
	}
}
