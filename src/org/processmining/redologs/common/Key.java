package org.processmining.redologs.common;

import java.util.HashMap;
import java.util.List;

public class Key extends GraphNode {
	
	public static final int UNIQUE_KEY = 1;
	public static final int PRIMARY_KEY = 2;
	public static final int FOREIGN_KEY = 3;
	
	public String name;
	public TableInfo table;
	//public List<Column> fields;
	public HashMap<Integer,Column> fields_ordered;
	public List<Column> fields;
	public int type;
	public Key refers_to;
	public HashMap<Column,Column> refers_to_column;
	
	public String typeToString() {
		switch (type) {
		case FOREIGN_KEY:
			return "FK";
		case PRIMARY_KEY:
			return "PK";
		case UNIQUE_KEY:
			return "U";
		default:
			return "?";
		}
	}
	
	@Override
	public String toString() {
		return "("+typeToString()+") "+name;
	}
}
