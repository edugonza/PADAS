package org.processmining.openslex;
/*
 * 
 */



// TODO: Auto-generated Javadoc
/**
 * The Class SLEXDMDataModel.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXDMDataModel extends SLEXAbstractDatabaseObject {

	/** The id. */
	private int id = -1;
	
	/** The name. */
	private String name = null;
	
	/**
	 * Instantiates a new SLEXDM data model.
	 *
	 * @param storage the storage
	 */
	public SLEXDMDataModel(SLEXStorage storage) {
		super(storage);
	}
	
	/**
	 * Gets the storage.
	 *
	 * @return the storage
	 */
	public SLEXStorageDataModel getStorage() {
		return (SLEXStorageDataModel) super.storage;
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject dm) {
		return getStorage().insert((SLEXDMDataModel) dm);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject dm) {
		return getStorage().update((SLEXDMDataModel) dm);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	protected void setName(String name) {
		this.name = name;
		setDirty(true);
	}

}
