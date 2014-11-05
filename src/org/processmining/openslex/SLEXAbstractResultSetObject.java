package org.processmining.openslex;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract class SLEXAbstractResultSetObject {
	
	protected ResultSet rset = null;
	protected SLEXStorage storage = null;
	
	protected SLEXAbstractResultSetObject(SLEXStorage storage, ResultSet rset) {
		this.storage = storage;
		this.rset = rset;
	}
	
	public void close() {
		if (rset != null) {
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
