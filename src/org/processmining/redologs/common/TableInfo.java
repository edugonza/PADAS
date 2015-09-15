package org.processmining.redologs.common;

import java.io.Serializable;
import java.util.List;

public class TableInfo extends GraphNode implements Serializable {
	public String db;
	public String name;
	public List<Column> columns;
	
	@Override
	public String toString() {
		//return db+"@"+name;
		return name;
	}
}
