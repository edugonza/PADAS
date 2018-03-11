package org.processmining.database.redologs.common;

import com.jcabi.manifests.Manifests;

public class Constants {
    private static final String VERSION_ROW = "PADAS-Version";
    private static final String AUTHOR_ROW = "PADAS-Author";
    private static final String APP_NAME_ROW = "PADAS-App-Name";
    private static final String EMAIL_ROW = "PADAS-Email";
        
    public static String getAtt(String name) {
    	String value = null;
    	try {
    		value = Manifests.read(name);
    	} catch (Exception e) {
    		value = "Unknown";
    	}
    	
    	return value;
    }
    
	public static String getVersion() {
		return getAtt(VERSION_ROW);
	}
	
	public static String getAppName() {
		return getAtt(APP_NAME_ROW);
	}
	
	public static String getAuthor() {
		return getAtt(AUTHOR_ROW);
	}
	
	public static String getEmail() {
		return getAtt(EMAIL_ROW);
	}
}
