package org.processmining.database.metamodel;

import java.io.Serializable;

public class ActivityInstance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1732580014775227247L;
	private String activityName;
	
	public ActivityInstance(String activityName) {
		this.activityName = activityName;
	}
	
	public String getActivityName() {
		return this.activityName;
	}
	
}
