package org.processmining.openslex;
/*
 * 
 */


import java.util.Hashtable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface SLEXStorageCollection.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public interface SLEXStorageCollection extends SLEXStorage {

	/**
	 * Creates the event collection.
	 *
	 * @param name the name
	 * @return the SLEX event collection
	 */
	public abstract SLEXEventCollection createEventCollection(String name);
	
	/**
	 * Creates the event.
	 *
	 * @param collectionId the collection id
	 * @return the SLEX event
	 */
	public abstract SLEXEvent createEvent(int collectionId);

	/**
	 * Find class.
	 *
	 * @param name the name
	 * @return the SLEX class
	 */
	public abstract SLEXClass findClass(String name);

	/**
	 * Find attribute.
	 *
	 * @param className the class name
	 * @param name the name
	 * @return the SLEX attribute
	 */
	public abstract SLEXAttribute findAttribute(String className, String name);

	/**
	 * Find or create attribute.
	 *
	 * @param className the class name
	 * @param name the name
	 * @param common the common
	 * @return the SLEX attribute
	 */
	public abstract SLEXAttribute findOrCreateAttribute(String className,
			String name, boolean common);

	/**
	 * Creates the attribute.
	 *
	 * @param className the class name
	 * @param name the name
	 * @param common the common
	 * @return the SLEX attribute
	 */
	public abstract SLEXAttribute createAttribute(String className,
			String name, boolean common);

	/**
	 * Creates the attribute value.
	 *
	 * @param attributeId the attribute id
	 * @param eventId the event id
	 * @param value the value
	 * @return the SLEX attribute value
	 */
	public abstract SLEXAttributeValue createAttributeValue(int attributeId,
			int eventId, String value);

	/**
	 * Creates the class.
	 *
	 * @param name the name
	 * @param common the common
	 * @return the SLEX class
	 */
	public abstract SLEXClass createClass(String name, boolean common);

	/**
	 * Gets the event collections.
	 *
	 * @return the event collections
	 */
	public abstract SLEXEventCollectionResultSet getEventCollections();

	/**
	 * Gets the events of collection between dates ordered by.
	 *
	 * @param ec the ec
	 * @param orderAttributes the order attributes
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the events of collection between dates ordered by
	 */
	public abstract SLEXEventResultSet getEventsOfCollectionBetweenDatesOrderedBy(
			SLEXEventCollection ec, List<SLEXAttribute> orderAttributes,
			String startDate, String endDate);

	/**
	 * Gets the event collection.
	 *
	 * @param id the id
	 * @return the event collection
	 */
	public abstract SLEXEventCollection getEventCollection(int id);
	
	/**
	 * Gets the attribute values for event.
	 *
	 * @param slexEvent the slex event
	 * @return the attribute values for event
	 */
	public abstract Hashtable<SLEXAttribute, SLEXAttributeValue> getAttributeValuesForEvent(
			SLEXEvent slexEvent);

	/**
	 * Insert.
	 *
	 * @param ec the ec
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXEventCollection ec);

	/**
	 * Update.
	 *
	 * @param ec the ec
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXEventCollection ec);

	/**
	 * Gets the events of collection.
	 *
	 * @param slexEventCollection the slex event collection
	 * @return the events of collection
	 */
	public abstract SLEXEventResultSet getEventsOfCollection(
			SLEXEventCollection slexEventCollection);

	/**
	 * Gets the events of collection ordered by.
	 *
	 * @param slexEventCollection the slex event collection
	 * @param orderAttributes the order attributes
	 * @return the events of collection ordered by
	 */
	public abstract SLEXEventResultSet getEventsOfCollectionOrderedBy(
			SLEXEventCollection slexEventCollection,
			List<SLEXAttribute> orderAttributes);

	/**
	 * Insert.
	 *
	 * @param cl the cl
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXClass cl);

	/**
	 * Update.
	 *
	 * @param cl the cl
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXClass cl);

	/**
	 * Insert.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXEvent e);

	/**
	 * Update.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXEvent e);

	/**
	 * Insert.
	 *
	 * @param at the at
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXAttribute at);

	/**
	 * Update.
	 *
	 * @param at the at
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXAttribute at);

	/**
	 * Insert.
	 *
	 * @param at the at
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXAttributeValue at);

	/**
	 * Update.
	 *
	 * @param at the at
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXAttributeValue at);

}
