package org.processmining.openslex;
/*
 * 
 */


import java.sql.ResultSet;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXDMDataModelResultSet.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXDMDataModelResultSet extends SLEXAbstractResultSetObject {
	
	/**
	 * Instantiates a new SLEXDM data model result set.
	 *
	 * @param storage the storage
	 * @param rset the rset
	 */
	protected SLEXDMDataModelResultSet(SLEXStorage storage, ResultSet rset) {
		super(storage, rset);
	}
	
	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public SLEXDMDataModel getNext() {
		SLEXDMDataModel dm = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				String name = this.rset.getString("name");
				int id = this.rset.getInt("id");
				dm = new SLEXDMDataModel(storage);
				dm.setName(name);
				dm.setId(id);
				dm.setDirty(false);
				dm.setInserted(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dm == null) {
				close();
			}
		}
		return dm;
	}
	
}
