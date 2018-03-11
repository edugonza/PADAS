package org.processmining.openslex;
/*
 * 
 */


// TODO: Auto-generated Javadoc
/**
 * The Interface SLEXStorage.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public interface SLEXStorage {

	/** The Constant TYPE_COLLECTION. */
	public static final int TYPE_COLLECTION = 1;
	
	/** The Constant TYPE_PERSPECTIVE. */
	public static final int TYPE_PERSPECTIVE = 2;
	
	/** The Constant TYPE_DATAMODEL. */
	public static final int TYPE_DATAMODEL = 3;
	
	/** The Constant COMMON_CLASS_NAME. */
	public static final String COMMON_CLASS_NAME = "COMMON";
	
	/** The Constant COLLECTION_FILE_EXTENSION. */
	public static final String COLLECTION_FILE_EXTENSION = ".slexcol";
	
	/** The Constant PERSPECTIVE_FILE_EXTENSION. */
	public static final String PERSPECTIVE_FILE_EXTENSION = ".slexper";
	
	/** The Constant DATAMODEL_FILE_EXTENSION. */
	public static final String DATAMODEL_FILE_EXTENSION = ".slexdm";

	/** The Constant PATH. */
	public static final String PATH = "data";
	
	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public abstract String getFilename();

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public abstract String getPath();

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public abstract int getType();

	/**
	 * Sets the auto commit.
	 *
	 * @param autoCommit the new auto commit
	 */
	public abstract void setAutoCommit(boolean autoCommit);

	/**
	 * Commit.
	 */
	public abstract void commit();

	/**
	 * Disconnect.
	 */
	public abstract void disconnect();

	/**
	 * Sets the auto commit on creation.
	 *
	 * @param flag the new auto commit on creation
	 */
	public abstract void setAutoCommitOnCreation(boolean flag);

	/**
	 * Checks if is auto commit on creation enabled.
	 *
	 * @return true, if is auto commit on creation enabled
	 */
	public abstract boolean isAutoCommitOnCreationEnabled();

}