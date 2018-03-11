package org.processmining.openslex;
/*
 * 
 */


import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXTrace.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXTrace extends SLEXAbstractDatabaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845173926695303296L;

	/** The id. */
	private int id = -1;
	
	/** The case id. */
	private String caseId = null;
	
	/** The perspective id. */
	private int perspectiveId = -1;
	
	/**
	 * Instantiates a new SLEX trace.
	 */
	public SLEXTrace() {
		super(null);
	}
	
	/**
	 * Instantiates a new SLEX trace.
	 *
	 * @param storage the storage
	 */
	protected SLEXTrace(SLEXStoragePerspective storage) {
		super(storage);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		String strHash = id+"#"+caseId+"#"+perspectiveId;
		return strHash.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SLEXTrace) {
			SLEXTrace oT = (SLEXTrace) obj;
			if (this.id == oT.id) {
				if (this.caseId.equals(oT.caseId)) {
					if (this.perspectiveId == oT.perspectiveId) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the storage.
	 *
	 * @return the storage
	 */
	public SLEXStoragePerspective getStorage() {
		return (SLEXStoragePerspective) super.storage;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Gets the case id.
	 *
	 * @return the case id
	 */
	public String getCaseId() {
		return this.caseId;
	}
	
	/**
	 * Gets the perspective id.
	 *
	 * @return the perspective id
	 */
	public int getPerspectiveId() {
		return this.perspectiveId;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	protected void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Sets the case id.
	 *
	 * @param caseId the new case id
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
		setDirty(true);
	}
	
	/**
	 * Sets the perspective id.
	 *
	 * @param perspectiveId the new perspective id
	 */
	protected void setPerspectiveId(int perspectiveId) {
		this.perspectiveId = perspectiveId;
		setDirty(true);
	}
	
	/**
	 * Adds the.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	public boolean add(SLEXEvent e) {
		return getStorage().addEventToTrace(this,e);
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject t) {
		return getStorage().insert((SLEXTrace) t);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject t) {
		return getStorage().update((SLEXTrace) t);
	}

	/**
	 * Gets the events result set.
	 *
	 * @return the events result set
	 */
	public SLEXEventResultSet getEventsResultSet() {
		return getStorage().getEventsOfTrace(this);
	}

	/**
	 * Gets the number events.
	 *
	 * @return the number events
	 */
	public int getNumberEvents() {
		return getStorage().getNumberEventsOfTrace(this);
	}
		
}
