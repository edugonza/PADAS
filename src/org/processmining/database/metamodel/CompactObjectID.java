package org.processmining.database.metamodel;

import java.io.Serializable;
import java.util.HashMap;

public class CompactObjectID extends CompactObject implements Serializable, Comparable {
	public int tableId = -1;
	public HashMap<CompactColumn,String> valuesId = new HashMap<>();
	
	@Override
	public int hashCode() {
		String hashStr = tableId + "#" + valuesId.hashCode();
		return hashStr.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.compareTo(obj) == 0);
	}
	
	@Override
	public int compareTo(Object o) {
		
		if (o instanceof CompactObjectID) {
			CompactObjectID coid = (CompactObjectID) o;
			
			if (this.tableId < coid.tableId) {
				return -1;
			} else if (this.tableId > coid.tableId) {
				return 1;
			} else {
				if (this.valuesId.hashCode() < coid.valuesId.hashCode()) {
					return -1;
				} else if (this.valuesId.hashCode() > coid.valuesId.hashCode()) {
					return 1;
				} else {
					return 0;
				}
			}
		} else {
			if (this.hashCode() <= o.hashCode()) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
