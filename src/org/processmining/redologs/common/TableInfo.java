package org.processmining.redologs.common;

import java.util.List;

public class TableInfo extends GraphNode {
	public String db;
	public String name;
	public List<Column> columns;
	
	@Override
	public String toString() {
		return db+"@"+name;
	}
}
