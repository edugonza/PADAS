package org.processmining.openslex;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SLEXEventCollectionResultSet extends SLEXResultSetObject {
	
	protected SLEXEventCollectionResultSet(LogStorage storage, ResultSet rset) {
		super(storage, rset);
	}
	
	public SLEXEventCollection getNext() {
		SLEXEventCollection ec = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				String name = this.rset.getString("name");
				int id = this.rset.getInt("id");
				ec = new SLEXEventCollection(storage, name);
				ec.setId(id);
				ec.setDirty(false);
				ec.setInserted(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ec == null) {
				close();
			}
		}
		return ec;
	}
	
}
