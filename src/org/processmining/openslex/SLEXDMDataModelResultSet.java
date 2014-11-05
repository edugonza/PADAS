package org.processmining.openslex;

import java.sql.ResultSet;

public class SLEXDMDataModelResultSet extends SLEXAbstractResultSetObject {
	
	protected SLEXDMDataModelResultSet(SLEXStorage storage, ResultSet rset) {
		super(storage, rset);
	}
	
	public SLEXDMDataModel getNext() {
		SLEXDMDataModel dm = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				String name = this.rset.getString("name");
				int id = this.rset.getInt("id");
				dm = new SLEXDMDataModel(storage);
				dm.setName(name);
				dm.setId(id);
				dm.setDirty(false);
				dm.setInserted(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dm == null) {
				close();
			}
		}
		return dm;
	}
	
}
