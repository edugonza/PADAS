package org.processmining.database.metamodel;

import java.io.Serializable;

public class CompactColumn implements Serializable {
	public int tableId = -1;
	public String name = null;
	
	@Override
	public int hashCode() {
		return (tableId+"#"+name).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CompactColumn) {
			CompactColumn cc = (CompactColumn) obj;
			if (this.tableId == cc.tableId
					&& this.name.equals(cc.name)) {
				return true;
			}
		}
		
		return false;
	}
}
