package org.processmining.redologs.config;

import java.io.Serializable;


public class DatabaseConnectionData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5712587551466284618L;
	
	public String nameConnection = "";
	public DatabaseTypes type = null;
	public String hostname  = "";
	public Integer port = 0;
	public String username = "";
	public String password = "";
	public String dbname = "";
	
	@Override
	public String toString() {
		return nameConnection;
	}
}
