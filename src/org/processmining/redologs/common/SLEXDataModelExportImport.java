package org.processmining.redologs.common;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Map.Entry;

import org.processmining.openslex.SLEXDMAttribute;
import org.processmining.openslex.SLEXDMClass;
import org.processmining.openslex.SLEXDMClassResultSet;
import org.processmining.openslex.SLEXDMDataModel;
import org.processmining.openslex.SLEXDMKey;
import org.processmining.openslex.SLEXDMKeyAttribute;
import org.processmining.openslex.SLEXStorage;

public class SLEXDataModelExportImport {

	public static SLEXDMDataModel saveDataModelToSLEXDM(DataModel model) {
		SLEXDMDataModel dm = null;
		try {
			dm = SLEXStorage.getInstance().createDMDataModel(model.getName());
			
			HashMap<TableInfo,SLEXDMClass> classesTable = new HashMap<>();
			HashMap<Column,SLEXDMAttribute> attributesTable = new HashMap<>();
			HashMap<Key,SLEXDMKey> keysTable = new HashMap<>();
			
			if (model != null) {
				for (TableInfo t : model.getTables()) {
					// For every t create a Class and link to dm
					SLEXDMClass cl = SLEXStorage.getInstance().createDMClass(dm.getId(), t.name, false);
					classesTable.put(t, cl);
					
					for (Column c : t.columns) {
						// For every c create an Attribute linked to Class
						SLEXDMAttribute at = SLEXStorage.getInstance().createDMAttribute(cl.getId(), c.name, false);
						attributesTable.put(c, at);
					}
				}

				for (Key k : model.getPrimaryKeys().values()) {
					// For every PK k create a Key
					SLEXDMClass cl = classesTable.get(k.table);
					SLEXDMKey key = SLEXStorage.getInstance().createDMKey(cl.getId(),k.name,SLEXDMKey.PRIMARY_KEY,SLEXDMKey.REFERS_TO_NULL);
					keysTable.put(k, key);
					
					for (Column c : k.fields) {
						// For every c create a KeyAttribute linked to attribute
						SLEXDMAttribute at = attributesTable.get(c);
						
						int position = 0;
						if (k.fields_ordered.containsValue(c)) {
							Iterator<Entry<Integer,Column>> it = k.fields_ordered.entrySet().iterator();
							boolean found = false;
							while (it.hasNext() && !found) {
								Entry<Integer,Column> entry = it.next();
								if (entry.getValue().equals(c)) {
									found = true;
									position = entry.getKey();
								}
							}
						}
						
						SLEXStorage.getInstance().createDMKeyAttribute(key.getId(),at.getId(),SLEXDMKeyAttribute.REFERS_TO_NULL,position);
					}
				}
				
				for (Key k : model.getUniqueKeys().values()) {
					// For every UK k create a Key
					SLEXDMClass cl = classesTable.get(k.table);
					SLEXDMKey key = SLEXStorage.getInstance().createDMKey(cl.getId(),k.name,SLEXDMKey.UNIQUE_KEY,SLEXDMKey.REFERS_TO_NULL);
					keysTable.put(k, key);
					
					for (Column c : k.fields) {
						// For every c create a KeyAttribute linked to attribute
						SLEXDMAttribute at = attributesTable.get(c);
						
						int position = 0;
						if (k.fields_ordered.containsValue(c)) {
							Iterator<Entry<Integer,Column>> it = k.fields_ordered.entrySet().iterator();
							boolean found = false;
							while (it.hasNext() && !found) {
								Entry<Integer,Column> entry = it.next();
								if (entry.getValue().equals(c)) {
									found = true;
									position = entry.getKey();
								}
							}
						}
						
						SLEXStorage.getInstance().createDMKeyAttribute(key.getId(),at.getId(),SLEXDMKeyAttribute.REFERS_TO_NULL,position);
						
					}
				}

				for (Key k : model.getForeignKeys().values()) {
					// For every FK k create a Key
					SLEXDMClass cl = classesTable.get(k.table);
					SLEXDMKey refers_to_key = keysTable.get(k.refers_to);
					SLEXDMKey key = SLEXStorage.getInstance().createDMKey(cl.getId(),k.name,SLEXDMKey.FOREIGN_KEY,refers_to_key.getId());
					keysTable.put(k, key);
					
					for (Column c : k.fields) {
						// For every c create a KeyAttribute linked to attribute
						SLEXDMAttribute at = attributesTable.get(c);
						Column c2 = k.refers_to_column.get(c);
						SLEXDMAttribute refers_to_at = attributesTable.get(c2);
						
						int position = 0;
						if (k.fields_ordered.containsValue(c)) {
							Iterator<Entry<Integer,Column>> it = k.fields_ordered.entrySet().iterator();
							boolean found = false;
							while (it.hasNext() && !found) {
								Entry<Integer,Column> entry = it.next();
								if (entry.getValue().equals(c)) {
									found = true;
									position = entry.getKey();
								}
							}
						}
						
						SLEXStorage.getInstance().createDMKeyAttribute(key.getId(),at.getId(),refers_to_at.getId(),position);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dm;
	}
	
	
	public static DataModel loadDataModelFromSLEXDM(SLEXDMDataModel dm) {
		SLEXStorage storage = null;
		try {
			storage = SLEXStorage.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		DataModel model = new DataModel();
		model.setName(dm.getName());
		
		HashMap<Integer,Column> attributesToColumnMap = new HashMap<>();
		HashMap<Integer,Key> keysMap = new HashMap<>();
		List<TableInfo> tables = new Vector<>();
		
		Hashtable<String, Key> primaryKeys = new Hashtable<>();
		Hashtable<String, Key> uniqueKeys = new Hashtable<>();
		Hashtable<String, Key> foreignKeys = new Hashtable<>();
		
		model.setPrimaryKeys(primaryKeys);
		model.setUniqueKeys(uniqueKeys);
		model.setForeignKeys(foreignKeys);
		
		Hashtable<TableInfo, List<Key>> keysPerTable = new Hashtable<TableInfo, List<Key>>();
		
		model.setKeysPerTable(keysPerTable);
		//model.setColumns(columns);
		
		SLEXDMClassResultSet crset = storage.getClassesForDataModel(dm);
		
		SLEXDMClass cl = null;
		
		while ((cl = crset.getNext()) != null) {
			TableInfo t = new TableInfo();
			t.name = cl.getName();
			tables.add(t);
			t.columns = new Vector<>();
			for (SLEXDMAttribute at: cl.getAttributes()) {
				Column c = null;
				if (attributesToColumnMap.containsKey(at.getId())) {
					c = attributesToColumnMap.get(at.getId());
				} else {
					c = new Column();
					attributesToColumnMap.put(at.getId(), c);
				}
				c.name = at.getName();
				c.table = t;
				t.columns.add(c);
			}
			
			List<Key> keysT = new Vector<>();
			keysPerTable.put(t, keysT);
			
			for (SLEXDMKey kdm: cl.getKeys()) {
				Key k = null;
				if (keysMap.containsKey(kdm.getId())) {
					k = keysMap.get(kdm.getId());
				} else {
					k = new Key();
					keysMap.put(kdm.getId(), k);
				}
				k.name = kdm.getName();
				k.table = t;
				keysT.add(k);
				if (kdm.getType() == SLEXDMKey.PRIMARY_KEY) {
					k.type = Key.PRIMARY_KEY;
					primaryKeys.put(k.name, k);
				} else if (kdm.getType() == SLEXDMKey.FOREIGN_KEY) {
					k.type = Key.FOREIGN_KEY;
					foreignKeys.put(k.name, k);
				} else if (kdm.getType() == SLEXDMKey.UNIQUE_KEY) {
					k.type = Key.UNIQUE_KEY;
					uniqueKeys.put(k.name, k);
				}
				//
				if (kdm.getType() == SLEXDMKey.FOREIGN_KEY) {
					Key refers_to_key = null;
					if (keysMap.containsKey(kdm.getRefersToKey())) {
						refers_to_key = keysMap.get(kdm.getRefersToKey());
					} else {
						refers_to_key = new Key();
						keysMap.put(kdm.getRefersToKey(), refers_to_key);
					}
					k.refers_to = refers_to_key;
				}
				//
				
				k.fields = new Vector<>();
				k.fields_ordered = new HashMap<>();
				k.refers_to_column = new HashMap<>();
				for (SLEXDMKeyAttribute kat : storage.getKeyAttributesForDMKey(kdm)) {
					Column c = attributesToColumnMap.get(kat.getAttributeId());
					k.fields.add(c);
					k.fields_ordered.put(kat.getPosition(), c);
					if (kdm.getType() == SLEXDMKey.FOREIGN_KEY) {
						int refers_to = kat.getRefersToId();
						Column refers_to_c = null;
						if (attributesToColumnMap.containsKey(refers_to)) {
							refers_to_c = attributesToColumnMap.get(refers_to);
						} else {
							refers_to_c = new Column();
							attributesToColumnMap.put(refers_to, refers_to_c);
						}
						k.refers_to_column.put(c, refers_to_c);
					}
				}	
			}
		}
		
		model.setTables(tables);
		
		return model;
	}
}
