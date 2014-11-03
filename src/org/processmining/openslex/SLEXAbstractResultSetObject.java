package org.processmining.openslex;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract class SLEXAbstractResultSetObject {
	
	protected ResultSet rset = null;
	protected SLEXStorage storage = null;
	protected String alias = null;
	
	protected SLEXAbstractResultSetObject(SLEXStorage storage, ResultSet rset, String alias) {
		this.storage = storage;
		this.rset = rset;
		if (alias == null) {
			this.alias = "";
		} else if (!alias.endsWith(".")) {
			this.alias = alias+".";
		} else {
			this.alias = alias;
		}
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
