package org.processmining.redologs.common;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class DataModel {

	private String name;
	private List<TableInfo> tables;
	private Hashtable<String, TableInfo> tablesPerName = new Hashtable<>();
	
	private Hashtable<String, Key> primaryKeys = new Hashtable<>();
	private Hashtable<String, Key> foreignKeys = new Hashtable<>();
	private Hashtable<String, Key> uniqueKeys = new Hashtable<>();
	//private Hashtable<String, Column> columns = new Hashtable<>();
	private Hashtable<TableInfo, List<Key>> keysPerTable = new Hashtable<>();
	
	public DataModel() {
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTables(List<TableInfo> tables) {
		this.tables = tables;
		for (TableInfo t: tables) {
			tablesPerName.put(t.toString(), t);
		}
	}
	
	public List<TableInfo> getTables() {
		return this.tables;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}

	public Hashtable<String, Key> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(Hashtable<String, Key> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public Hashtable<String, Key> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(Hashtable<String, Key> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	public Hashtable<String, Key> getUniqueKeys() {
		return uniqueKeys;
	}

	public void setUniqueKeys(Hashtable<String, Key> uniqueKeys) {
		this.uniqueKeys = uniqueKeys;
	}

//	public Hashtable<String, Column> getColumns() {
//		return columns;
//	}
//
//	public void setColumns(Hashtable<String, Column> columns) {
//		this.columns = columns;
//	}

	public List<Key> getKeysPerTable(TableInfo table) {
		List<Key> keys = null;
		if (table != null) {
			keys = this.keysPerTable.get(table);
		}
		if (keys == null) {
			keys = new Vector<>();
		}
		return keys;
	}
	
	public List<Key> getKeysPerTable(String dbName, String tableName) {
		TableInfo t = new TableInfo();
		t.db = dbName;
		t.name = tableName;
		TableInfo tN = tablesPerName.get(t.toString());
		return getKeysPerTable(tN);
	}

	public void setKeysPerTable(Hashtable<TableInfo, List<Key>> keysPerTable) {
		this.keysPerTable = keysPerTable;
	}
	
}
