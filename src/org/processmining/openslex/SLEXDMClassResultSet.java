package org.processmining.openslex;

import java.sql.ResultSet;

public class SLEXDMClassResultSet extends SLEXAbstractResultSetObject {
	
	protected SLEXDMClassResultSet(SLEXStorage storage, ResultSet rset, String alias) {
		super(storage, rset, alias);
	}
	
	public SLEXDMClass getNext() {
		SLEXDMClass cl = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				int id = this.rset.getInt(alias+"id");
				int dataModelId = this.rset.getInt(alias+"data_modelID");
				String name = this.rset.getString(alias+"name");
				boolean common = this.rset.getBoolean(alias+"common");
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
