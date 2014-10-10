package org.processmining.openslex;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SLEXDMClassResultSet extends SLEXAbstractResultSetObject {
	
	protected SLEXDMClassResultSet(SLEXStorage storage, ResultSet rset) {
		super(storage, rset);
	}
	
	public SLEXDMClass getNext() {
		SLEXDMClass cl = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				int id = this.rset.getInt("id");
				int dataModelId = this.rset.getInt("data_modelID");
				String name = this.rset.getString("name");
				boolean common = this.rset.getBoolean("common");
				cl = new SLEXDMClass(storage,name,common,dataModelId);
				cl.setId(id);
				cl.retrieveAttributesAndKeys();
				cl.setDirty(false);
				cl.setInserted(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cl == null) {
				close();
			}
		}
		return cl;
	}
	
}
