package org.processmining.openslex;
/*
 * 
 */


import java.sql.ResultSet;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXPerspectiveResultSet.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXPerspectiveResultSet extends SLEXAbstractResultSetObject {
	
	/**
	 * Instantiates a new SLEX perspective result set.
	 *
	 * @param storage the storage
	 * @param rset the rset
	 */
	protected SLEXPerspectiveResultSet(SLEXStoragePerspective storage, ResultSet rset) {
		super(storage, rset);
	}
	
	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public SLEXPerspective getNext() {
		SLEXPerspective p = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				String name = this.rset.getString("name");
				int id = this.rset.getInt("id");
				int collectionId = this.rset.getInt("collectionID");
				String collectionFileName = this.rset.getString("collectionFileName");
				p = new SLEXPerspective((SLEXStoragePerspective) storage);
				p.setCollectionId(collectionId);
				p.setId(id);
				p.setName(name);
				p.setCollectionFileName(collectionFileName);
				p.setDirty(false);
				p.setInserted(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (p == null) {
				close();
			}
		}
		return p;
	}
	
}
