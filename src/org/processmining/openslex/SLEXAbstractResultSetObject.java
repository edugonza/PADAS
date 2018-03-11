package org.processmining.openslex;
/*
 * 
 */


import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXAbstractResultSetObject.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
abstract class SLEXAbstractResultSetObject {
	
	/** The rset. */
	protected ResultSet rset = null;
	
	/** The storage. */
	protected SLEXStorage storage = null;
	
	/**
	 * Instantiates a new SLEX abstract result set object.
	 *
	 * @param storage the storage
	 * @param rset the rset
	 */
	protected SLEXAbstractResultSetObject(SLEXStorage storage, ResultSet rset) {
		this.storage = storage;
		this.rset = rset;
	}
	
	/**
	 * Close.
	 */
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
