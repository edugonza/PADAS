package org.processmining.openslex;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SLEXPerspectiveResultSet extends SLEXAbstractResultSetObject {
	
	protected SLEXPerspectiveResultSet(SLEXStorage storage, ResultSet rset) {
		super(storage, rset);
	}
	
	public SLEXPerspective getNext() {
		SLEXPerspective p = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				String name = this.rset.getString("name");
				int id = this.rset.getInt("id");
				int collectionId = this.rset.getInt("collectionID");
				p = new SLEXPerspective(storage);
				p.setCollectionId(collectionId);
				p.setId(id);
				p.setName(name);
				p.setDirty(false);
				p.setInserted(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (p == null) {
				close();
			}
		}
		return p;
	}
	
}
