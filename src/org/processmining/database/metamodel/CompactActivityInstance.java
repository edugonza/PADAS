package org.processmining.database.metamodel;

import java.io.Serializable;

public class CompactActivityInstance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1732580014775227247L;
	public int id;
	public String activityName;
	
	@Override
	public int hashCode() {
		String strhash = id+"#"+activityName;
		return strhash.hashCode();
	}
}
