package org.processmining.database.metamodel;

import java.io.Serializable;

public class CompactRelation implements Serializable {
	public int sourceObjectVersionId = -1;
	public int targetObjectVersionId = -1;
	public long startTimestamp = -1L;
	public long endTimestamp = -1L;
	public int relationshipId = -1;
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CompactRelation) {
			CompactRelation cr = (CompactRelation) obj;
			if (this.sourceObjectVersionId == cr.sourceObjectVersionId
					&& this.targetObjectVersionId == cr.targetObjectVersionId
					&& this.startTimestamp == cr.startTimestamp
					&& this.endTimestamp == cr.endTimestamp
					&& this.relationshipId == cr.relationshipId) {
				return true;
			}
		}
		
		return false;
	}
}
