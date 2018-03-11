package org.processmining.openslex;
/*
 * 
 */


import java.sql.ResultSet;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXTraceResultSet.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXTraceResultSet extends SLEXAbstractResultSetObject {
	
	/**
	 * Instantiates a new SLEX trace result set.
	 *
	 * @param storage the storage
	 * @param rset the rset
	 */
	protected SLEXTraceResultSet(SLEXStoragePerspective storage, ResultSet rset) {
		super(storage, rset);
	}
	
	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public SLEXTrace getNext() {
		SLEXTrace t = null;
		try {
			if (this.rset != null && this.rset.next()) {
				
				String caseId = this.rset.getString("caseID");
				int id = this.rset.getInt("id");
				int perspectiveId = this.rset.getInt("perspectiveID");
				t = new SLEXTrace((SLEXStoragePerspective) storage);
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
