package org.processmining.redologs.common;

import java.io.Serializable;

public class Column extends GraphNode implements Comparable<Column>,Serializable {
	
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Column) {
			return (this.compareTo((Column) obj) == 0);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
}