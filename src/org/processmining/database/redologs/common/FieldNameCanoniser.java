package org.processmining.database.redologs.common;

import java.util.Hashtable;

public class FieldNameCanoniser {

	Hashtable<String, RelationsGraphNode> relations;
	
	public FieldNameCanoniser() {
		
	}
	
	public void setRelations(Hashtable<String, RelationsGraphNode> relations) {
		this.relations = relations;
	}
	
	public String get(String tableName, String name) {
		String canonized = null;
		String fullName = tableName+":"+name;
		
		if (relations.containsKey(fullName)) {
			RelationsGraphNode node = relations.get(fullName);
			if (node.to.isEmpty()) {
				canonized = fullName;
			} else {
				canonized = node.to.get(0).name;
			}
		} else {
			canonized = fullName;
		}
		
		return canonized;
	}
}
