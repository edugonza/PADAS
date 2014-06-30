package org.processmining.redologs.common;

public class Column implements Comparable<Column>, GraphNode {
	
	public TableInfo table;
	public String name;

	@Override
	public String toString() {
		return table.name + ":" + name;
	}

	@Override
	public int compareTo(Column o) {
		return this.toString().compareTo(o.toString());
	}
}