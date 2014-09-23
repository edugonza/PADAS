package org.processmining.openslex;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract class SLEXResultSetObject {
	
	protected ResultSet rset = null;
	protected LogStorage storage = null;
	
	protected SLEXResultSetObject(LogStorage storage, ResultSet rset) {
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
