package org.processmining.redologs.common;

import java.util.List;

public class Key implements GraphNode {
	
	public static final int UNIQUE_KEY = 1;
	public static final int PRIMARY_KEY = 2;
	public static final int FOREIGN_KEY = 3;
	
	public String name;
	public List<Column> fields;
	public int type;
	public Key refers_to;
	
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
