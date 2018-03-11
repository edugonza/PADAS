package org.processmining.openslex;
/*
 * 
 */


import java.sql.ResultSet;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXDMClassResultSet.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXDMClassResultSet extends SLEXAbstractResultSetObject {
	
	/**
	 * Instantiates a new SLEXDM class result set.
	 *
	 * @param storage the storage
	 * @param rset the rset
	 */
	protected SLEXDMClassResultSet(SLEXStorageDataModel storage, ResultSet rset) {
		super(storage, rset);
	}
	
	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public SLEXDMClass getNext() {
		SLEXDMClass cl = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				int id = this.rset.getInt("id");
				int dataModelId = this.rset.getInt("data_modelID");
				String name = this.rset.getString("name");
				boolean common = this.rset.getBoolean("common");
				cl = new SLEXDMClass((SLEXStorageDataModel)storage,name,common,dataModelId);
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
