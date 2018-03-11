package org.processmining.openslex;
/*
 * 
 */


// TODO: Auto-generated Javadoc
/**
 * The Class SLEXPerspective.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXPerspective extends SLEXAbstractDatabaseObject {

	/** The id. */
	private int id = -1;
	
	/** The collection id. */
	private int collectionId = -1;
	
	/** The name. */
	private String name = null;
	
	/** The collection file name. */
	private String collectionFileName = null;
	
	/**
	 * Instantiates a new SLEX perspective.
	 *
	 * @param storage the storage
	 */
	protected SLEXPerspective(SLEXStoragePerspective storage) {
		super(storage);
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
	 * Gets the collection id.
	 *
	 * @return the collection id
	 */
	public int getCollectionId() {
		return this.collectionId;
	}
	
	/**
	 * Sets the collection file name.
	 *
	 * @param filename the new collection file name
	 */
	public void setCollectionFileName(String filename) {
		this.collectionFileName = filename;
	}
	
	/**
	 * Gets the collection file name.
	 *
	 * @return the collection file name
	 */
	public String getCollectionFileName() {
		return this.collectionFileName;
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
	 * Sets the collection id.
	 *
	 * @param collectionId the new collection id
	 */
	protected void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
		setDirty(true);
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
	
	/**
	 * Gets the traces result set.
	 *
	 * @return the traces result set
	 */
	public SLEXTraceResultSet getTracesResultSet() {
		return getStorage().getTracesOfPerspective(this);
	}
	
	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#insert(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean insert(SLEXAbstractDatabaseObject p) {
		return getStorage().insert((SLEXPerspective) p);
	}

	/* (non-Javadoc)
	 * @see org.processmining.openslex.SLEXAbstractDatabaseObject#update(org.processmining.openslex.SLEXAbstractDatabaseObject)
	 */
	@Override
	boolean update(SLEXAbstractDatabaseObject p) {
		return getStorage().update((SLEXPerspective) p);
	}

	/**
	 * Removes the.
	 *
	 * @param t the t
	 * @return true, if successful
	 */
	public boolean remove(SLEXTrace t) {
		return getStorage().removeTraceFromPerspective(this,t);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(this.getId());
	}
}
