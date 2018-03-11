package org.processmining.openslex;
/*
 * 
 */


import java.sql.ResultSet;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXEventCollectionResultSet.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXEventCollectionResultSet extends SLEXAbstractResultSetObject {
	
	/**
	 * Instantiates a new SLEX event collection result set.
	 *
	 * @param storage the storage
	 * @param rset the rset
	 */
	protected SLEXEventCollectionResultSet(SLEXStorageCollection storage, ResultSet rset) {
		super(storage, rset);
	}
	
	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public SLEXEventCollection getNext() {
		SLEXEventCollection ec = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				String name = this.rset.getString("name");
				int id = this.rset.getInt("id");
				ec = new SLEXEventCollection((SLEXStorageCollection) storage, name);
				ec.setId(id);
				ec.setDirty(false);
				ec.setInserted(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ec == null) {
				close();
			}
		}
		return ec;
	}
	
}
