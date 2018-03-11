package org.processmining.openslex;
/*
 * 
 */


import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXAttributeValue.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXAttributeValue extends SLEXAbstractDatabaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6248377820595332542L;

	/** The attribute id. */
	private int attributeId = -1;
	
	/** The event id. */
	private int eventId = -1;
	
	/** The value. */
	private String value = null;
	
	/**
	 * Instantiates a new SLEX attribute value.
	 *
	 * @param storage the storage
	 * @param attributeId the attribute id
	 * @param eventId the event id
	 */
	protected SLEXAttributeValue(SLEXStorageCollection storage,int attributeId, int eventId) {
		super(storage);
		this.attributeId = attributeId;
		this.eventId = eventId;
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
	 * Gets the event id.
	 *
	 * @return the event id
	 */
	public int getEventId() {
		return this.eventId;
	}
	
	/**
	 * Gets the attribute id.
	 *
	 * @return the attribute id
	 */
	public int getAttributeId() {
		return this.attributeId;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	protected void setValue(String value) {
		this.value = value;
		setDirty(true);
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject at) {
		return getStorage().insert((SLEXAttributeValue) at);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject at) {
		return getStorage().update((SLEXAttributeValue) at);
	}

}
