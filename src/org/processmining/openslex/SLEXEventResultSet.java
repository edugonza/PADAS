package org.processmining.openslex;
/*
 * 
 */


import java.sql.ResultSet;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXEventResultSet.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXEventResultSet extends SLEXAbstractResultSetObject {
	
	
	
	/**
	 * Instantiates a new SLEX event result set.
	 *
	 * @param storage the storage
	 * @param rset the rset
	 */
	protected SLEXEventResultSet(SLEXStorageCollection storage, ResultSet rset) {
		super(storage, rset);
	}
	
	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public SLEXEvent getNext() {
		SLEXEvent ev = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				int id = this.rset.getInt("id");
				int collectionId = this.rset.getInt("collectionID");
				ev = new SLEXEvent((SLEXStorageCollection) storage);
				ev.setId(id);
				ev.setCollectionId(collectionId);
				ev.retrieveAttributeValues();
				ev.setDirty(false);
				ev.setInserted(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ev == null) {
				close();
			}
		}
		return ev;
	}
	
}
