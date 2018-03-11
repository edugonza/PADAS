package org.processmining.openslex;
/*
 * 
 */


import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating SLEX objects.
 */
public class SLEXFactory {
	
	/** The path. */
	//private String path = null;
	
	/**
	 * Instantiates a new SLEX factory.
	 *
	 * @param path the path
	 */
	public SLEXFactory(/*String path*/) {
	//	this.path = path;
	//	if (path == null) {
	//		this.path = SLEXStorage.PATH;
	//	}
	}
	
	/**
	 * Generate name file.
	 *
	 * @param path the path
	 * @param ext the ext
	 * @return the file
	 */
	private synchronized static File generateNameFile(String path, String ext) {
		File f = null;
		
		try {
			String millis = String.valueOf(System.currentTimeMillis());
		
			f = new File(path+File.separator+millis+ext);
		
			while (f.exists()) {
				millis = String.valueOf(System.currentTimeMillis());
				f = new File(path+File.separator+millis+ext);
			}
		
			f.createNewFile();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	/**
	 * Creates a new SLEX object.
	 *
	 * @return the SLEX storage collection
	 */
	public SLEXStorageCollection createStorageCollection() {
		File f = generateNameFile(SLEXStorage.PATH, SLEXStorage.COLLECTION_FILE_EXTENSION);
		SLEXStorageCollection st = null;
		try {
			st = new SLEXStorageImpl(f.getParent(), f.getName(), SLEXStorage.TYPE_COLLECTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}
	
	/**
	 * Creates a new SLEX object.
	 *
	 * @return the SLEX storage perspective
	 */
	public SLEXStoragePerspective createStoragePerspective() {
		File f = generateNameFile(SLEXStorage.PATH, SLEXStorage.PERSPECTIVE_FILE_EXTENSION);
		SLEXStoragePerspective st = null;
		try {
			st = new SLEXStorageImpl(f.getParent(), f.getName(), SLEXStorage.TYPE_PERSPECTIVE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}
	
	/**
	 * Creates a new SLEX object.
	 *
	 * @return the SLEX storage data model
	 */
	public SLEXStorageDataModel createStorageDataModel() {
		File f = generateNameFile(SLEXStorage.PATH, SLEXStorage.DATAMODEL_FILE_EXTENSION);
		SLEXStorageDataModel st = null;
		try {
			st = new SLEXStorageImpl(f.getParent(), f.getName(), SLEXStorage.TYPE_DATAMODEL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}
	
	/**
	 * Creates a new SLEX object.
	 *
	 * @param name the name
	 * @return the SLEX event collection
	 */
	public SLEXEventCollection createEventCollection(String name) {
		SLEXStorageCollection st = createStorageCollection();
		SLEXEventCollection ec = null;
		
		if (st != null) {
			ec = st.createEventCollection(name);
		}
		
		return ec;
	}
	
	/**
	 * Creates a new SLEX object.
	 *
	 * @param ec the ec
	 * @param name the name
	 * @return the SLEX perspective
	 */
	public SLEXPerspective createPerspective(SLEXEventCollection ec, String name) {
		SLEXStoragePerspective st = createStoragePerspective();
		SLEXPerspective p = null;
		
		if (st != null) {
			p = st.createPerspective(ec, name);
		}
		
		return p;
	}
	
}
