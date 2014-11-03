package org.processmining.openslex;

import java.sql.ResultSet;

public class SLEXEventResultSet extends SLEXAbstractResultSetObject {
	
	
	
	protected SLEXEventResultSet(SLEXStorage storage, ResultSet rset, String alias) {
		super(storage, rset, alias);
	}
	
	public SLEXEvent getNext() {
		SLEXEvent ev = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				int id = this.rset.getInt(alias+"id");
				int collectionId = this.rset.getInt(alias+"collectionID");
				ev = new SLEXEvent(storage);
				ev.setId(id);
				ev.setCollectionId(collectionId);
				ev.retrieveAttributeValues();
				ev.setDirty(false);
				ev.setInserted(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ev == null) {
				close();
			}
		}
		return ev;
	}
	
}
