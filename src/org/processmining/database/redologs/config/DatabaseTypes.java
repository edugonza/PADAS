package org.processmining.database.redologs.config;

public enum DatabaseTypes {
	ORACLE,
	MYSQL;
	
	public String toString() {
		if (this == ORACLE) {
			return "Oracle";
		} else if (this == MYSQL) {
			return "MySQL";
		} else {
			return "";
		}
	}
}
