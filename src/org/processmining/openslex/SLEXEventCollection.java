package org.processmining.openslex;
/*
 * 
 */


import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXEventCollection.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXEventCollection extends SLEXAbstractDatabaseObject {
	
	/** The id. */
	private int id = -1;
	
	/** The name. */
	private String name = null;
	
	/**
	 * Instantiates a new SLEX event collection.
	 *
	 * @param storage the storage
	 * @param name the name
	 */
	protected SLEXEventCollection(SLEXStorageCollection storage, String name) {
		super(storage);
		this.name = name;
	}
	
	/**
	 * Gets the storage.
	 *
	 * @return the storage
	 */
	public SLEXStorageCollection getStorage() {
		return (SLEXStorageCollection) super.storage;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
		setDirty(true);
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
	 * Adds the.
	 *
	 * @param event the event
	 * @return true, if successful
	 */
	public boolean add(SLEXEvent event) {
		event.setCollectionId(this.id);
		return event.commit();
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject ec) {
		return getStorage().insert((SLEXEventCollection) ec);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject ec) {
		return getStorage().update((SLEXEventCollection) ec);
	}
	
	/**
	 * Gets the events result set.
	 *
	 * @return the events result set
	 */
	public SLEXEventResultSet getEventsResultSet() {
		return getStorage().getEventsOfCollection(this);
	}
	
	/**
	 * Gets the events result set ordered by.
	 *
	 * @param orderAttributes the order attributes
	 * @return the events result set ordered by
	 */
	public SLEXEventResultSet getEventsResultSetOrderedBy(List<SLEXAttribute> orderAttributes) {
		return getStorage().getEventsOfCollectionOrderedBy(this,orderAttributes);
	}
	
	/**
	 * Gets the events result set between dates ordered by.
	 *
	 * @param orderAtts the order atts
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the events result set between dates ordered by
	 */
	public SLEXEventResultSet getEventsResultSetBetweenDatesOrderedBy(
			List<SLEXAttribute> orderAtts, String startDate, String endDate) {
		return getStorage().getEventsOfCollectionBetweenDatesOrderedBy(this,orderAtts,startDate,endDate);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getId()+"";
	}

}
