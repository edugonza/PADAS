package org.processmining.openslex;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SLEXEventResultSet extends SLEXResultSetObject {
	
	protected SLEXEventResultSet(LogStorage storage, ResultSet rset) {
		super(storage, rset);
	}
	
	public SLEXEvent getNext() {
		SLEXEvent ev = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				int id = this.rset.getInt("id");
				int collectionId = this.rset.getInt("collectionID");
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
