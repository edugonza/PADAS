package org.processmining.database.metamodel;

import java.io.Serializable;
import java.util.HashMap;

public class CompactObjectVersion extends CompactObject implements Serializable, Comparable {
	public int id = -1;
	public int eventId = -1;
	public int order = -1;
	public String label = null;
	public long startDate = -1L;
	public long endDate = -1L;
	public HashMap<CompactColumn,String> attributeValues = new HashMap<>();
	
	@Override
	public boolean equals(Object obj) {
		return (this.compareTo(obj) == 0);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof CompactObjectVersion) {
			CompactObjectVersion cov = (CompactObjectVersion) o;
			
			if (this.id < cov.id) {
				return -1;
			} else if (this.id > cov.id) {
				return 1;
			} else {
				return 0;
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
