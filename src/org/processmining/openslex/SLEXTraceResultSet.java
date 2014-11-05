package org.processmining.openslex;

import java.sql.ResultSet;

public class SLEXTraceResultSet extends SLEXAbstractResultSetObject {
	
	protected SLEXTraceResultSet(SLEXStorage storage, ResultSet rset) {
		super(storage, rset);
	}
	
	public SLEXTrace getNext() {
		SLEXTrace t = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				String caseId = this.rset.getString("caseID");
				int id = this.rset.getInt("id");
				int perspectiveId = this.rset.getInt("perspectiveID");
				t = new SLEXTrace(storage);
				t.setPerspectiveId(perspectiveId);
				t.setId(id);
				t.setCaseId(caseId);
				t.setDirty(false);
				t.setInserted(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (t == null) {
				close();
			}
		}
		return t;
	}
	
}
