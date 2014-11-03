package org.processmining.openslex;

import java.sql.ResultSet;

public class SLEXPerspectiveResultSet extends SLEXAbstractResultSetObject {
	
	protected SLEXPerspectiveResultSet(SLEXStorage storage, ResultSet rset, String alias) {
		super(storage, rset, alias);
	}
	
	public SLEXPerspective getNext() {
		SLEXPerspective p = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				String name = this.rset.getString(alias+"name");
				int id = this.rset.getInt(alias+"id");
				int collectionId = this.rset.getInt(alias+"collectionID");
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
