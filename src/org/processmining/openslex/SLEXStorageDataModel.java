package org.processmining.openslex;
/*
 * 
 */


import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface SLEXStorageDataModel.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public interface SLEXStorageDataModel extends SLEXStorage {

	/**
	 * Creates the dm data model.
	 *
	 * @param name the name
	 * @return the SLEXDM data model
	 */
	public abstract SLEXDMDataModel createDMDataModel(String name);

	/**
	 * Creates the dm class.
	 *
	 * @param data_modelId the data_model id
	 * @param name the name
	 * @param common the common
	 * @return the SLEXDM class
	 */
	public abstract SLEXDMClass createDMClass(int data_modelId, String name,
			boolean common);

	/**
	 * Creates the dm attribute.
	 *
	 * @param classId the class id
	 * @param name the name
	 * @param common the common
	 * @return the SLEXDM attribute
	 */
	public abstract SLEXDMAttribute createDMAttribute(int classId, String name,
			boolean common);

	/**
	 * Creates the dm key.
	 *
	 * @param classId the class id
	 * @param name the name
	 * @param type the type
	 * @param refers_to_key the refers_to_key
	 * @return the SLEXDM key
	 */
	public abstract SLEXDMKey createDMKey(int classId, String name, int type,
			int refers_to_key);

	/**
	 * Creates the dm key attribute.
	 *
	 * @param keyId the key id
	 * @param attributeId the attribute id
	 * @param refersToAttributeId the refers to attribute id
	 * @param position the position
	 * @return the SLEXDM key attribute
	 */
	public abstract SLEXDMKeyAttribute createDMKeyAttribute(int keyId,
			int attributeId, int refersToAttributeId, int position);


	/**
	 * Gets the classes for data model.
	 *
	 * @param dm the dm
	 * @return the classes for data model
	 */
	public abstract SLEXDMClassResultSet getClassesForDataModel(
			SLEXDMDataModel dm);

	/**
	 * Gets the data models.
	 *
	 * @return the data models
	 */
	public abstract SLEXDMDataModelResultSet getDataModels();

	/**
	 * Gets the attributes for dm class.
	 *
	 * @param cl the cl
	 * @return the attributes for dm class
	 */
	public abstract List<SLEXDMAttribute> getAttributesForDMClass(SLEXDMClass cl);

	/**
	 * Gets the keys for dm class.
	 *
	 * @param cl the cl
	 * @return the keys for dm class
	 */
	public abstract List<SLEXDMKey> getKeysForDMClass(SLEXDMClass cl);

	/**
	 * Gets the key attributes for dm key.
	 *
	 * @param k the k
	 * @return the key attributes for dm key
	 */
	public abstract List<SLEXDMKeyAttribute> getKeyAttributesForDMKey(
			SLEXDMKey k);

	/**
	 * Insert.
	 *
	 * @param at the at
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXDMAttribute at);

	/**
	 * Update.
	 *
	 * @param at the at
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXDMAttribute at);

	/**
	 * Insert.
	 *
	 * @param cl the cl
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXDMClass cl);

	/**
	 * Update.
	 *
	 * @param cl the cl
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXDMClass cl);

	/**
	 * Insert.
	 *
	 * @param dm the dm
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXDMDataModel dm);

	/**
	 * Update.
	 *
	 * @param dm the dm
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXDMDataModel dm);

	/**
	 * Insert.
	 *
	 * @param at the at
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXDMKey at);

	/**
	 * Update.
	 *
	 * @param at the at
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXDMKey at);

	/**
	 * Insert.
	 *
	 * @param at the at
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXDMKeyAttribute at);

	/**
	 * Update.
	 *
	 * @param at the at
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXDMKeyAttribute at);
	
}
