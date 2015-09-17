package org.processmining.database.metamodel;

import java.io.Serializable;

public class CompactTableInfo implements Serializable {
	public int id = -1;
	public String db = null;
	public String name = null;
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CompactTableInfo) {
			CompactTableInfo ct = (CompactTableInfo) obj;
			if (this.id == ct.id
					&& this.db.equals(ct.db)
					&& this.name.equals(ct.name)) {
				return true;
			}
		}
		
		return false;
	}
}
