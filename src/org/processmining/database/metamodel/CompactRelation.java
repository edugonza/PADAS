package org.processmining.database.metamodel;

import java.io.Serializable;

public class CompactRelation implements Serializable {
	public int sourceObjectVersionId = -1;
	public int targetObjectVersionId = -1;
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CompactRelation) {
			CompactRelation cr = (CompactRelation) obj;
			if (this.sourceObjectVersionId == cr.sourceObjectVersionId
					&& this.targetObjectVersionId == cr.targetObjectVersionId) {
				return true;
			}
		}
		
		return false;
	}
}
