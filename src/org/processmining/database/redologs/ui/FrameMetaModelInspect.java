package org.processmining.database.redologs.ui;

import org.processmining.database.redologs.ui.components.CustomInternalFrame;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
import org.processmining.database.metamodel.dapoql.ui.components.MetaModelInspectorPanel;
import org.processmining.database.metamodel.dapoql.ui.components.DAPOQLPanel;
import org.processmining.database.metamodel.dapoql.ui.components.SQLPanel;
import org.processmining.database.redologs.common.Column;
import org.processmining.database.redologs.common.DataModel;
import org.processmining.database.redologs.common.Key;
import org.processmining.database.redologs.common.TableInfo;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JTabbedPane;

public class FrameMetaModelInspect extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3775031378258022448L;

	private SLEXMMStorageMetaModel mmstrg;



	public SLEXMMStorageMetaModel getMetaModel() {
		return mmstrg;
	}

	public FrameMetaModelInspect(SLEXMMStorageMetaModel mmstrg) {
		super("MetaModel Inspector");
		this.mmstrg = mmstrg;

		setBounds(715, 30, 1207, 669);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.BOTTOM);
		getContentPane().add(tabbedPane_1, BorderLayout.CENTER);
		
		tabbedPane_1.addTab("Inspector", null, new MetaModelInspectorPanel(getMetaModel(),true), null);

		tabbedPane_1.addTab("DAPOQ-Lang", null, new DAPOQLPanel(getMetaModel()), null);
		
		tabbedPane_1.addTab("SQL", null, new SQLPanel(getMetaModel()), null);

	}
	
	
	private DataModel getMetaModelDataModel() {
		
		DataModel dm = new DataModel();
		List<TableInfo> tables = new ArrayList<TableInfo>();
		Hashtable<String, Key> foreignKeys = new Hashtable<>();
		Hashtable<String, Key> primaryKeys = new Hashtable<>();
		Hashtable<TableInfo, List<Key>> keysPerTable = new Hashtable<>();
			
		/* DataModel Table */
		TableInfo t_datamodel = createTable("", "datamodel",
				new String[] {"id","name"},tables);
		
		// PK
		Key pk_datamodel = createKey("pk_datamodel", t_datamodel, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Class Table */
		TableInfo t_class = createTable("", "class",
				new String[] {"id","datamodel_id","name"},tables);
		
		// PK
		Key pk_class = createKey("pk_class", t_class, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		// FK
		createKey("fk_class_datamodel", t_class, Key.FOREIGN_KEY,
				pk_datamodel, new String[] {"datamodel_id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Object Table */
		TableInfo t_object = createTable("", "object",
				new String[] {"id","class_id"}, tables);
		
		// PK
		Key pk_object = createKey("pk_object", t_object, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		
		// FK
		createKey("k_o_c_id", t_object, Key.FOREIGN_KEY,
				pk_class, new String[] {"class_id"}, primaryKeys, foreignKeys);
		
		/**/
		
		/* Object Table */
		TableInfo t_attribute = createTable("", "attribute_name",
				new String[] {"id","class_id","name"}, tables);
		
		// PK
		Key pk_attribute = createKey("pk_object", t_attribute, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		
		// FK
		createKey("k_a_c_id", t_attribute, Key.FOREIGN_KEY,
				pk_class, new String[] {"class_id"}, primaryKeys, foreignKeys);
		
		/**/
		
		/* Relationship Table */
		TableInfo t_relationship = createTable("", "relationship",
				new String[] {"id","source",
				"target","name"},tables);
		
		// PK
		Key pk_relationship = createKey("pk_relationship", t_relationship, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		
		// FK
		createKey("k_rs_c_s_id", t_relationship, Key.FOREIGN_KEY,
				pk_class, new String[] {"source"}, primaryKeys, foreignKeys);
		
		createKey("k_rs_c_t_id", t_relationship, Key.FOREIGN_KEY,
				pk_class, new String[] {"target"}, primaryKeys, foreignKeys);
		
		/**/
		
		
		/* Object Version Table */
		TableInfo t_object_version = createTable("", "object_version",
				new String[] {"id","object_id","start_timestamp","end_timestamp"}, tables);
		
		// PK 
		Key pk_object_version = createKey("pk_object_version", t_object_version, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		// FK 
		createKey("k_ov_o_id", t_object_version, Key.FOREIGN_KEY,
				pk_object, new String[] {"object_id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Attribute value Table */
		TableInfo t_attribute_value = createTable("", "attribute_value",
				new String[] {"id","object_version_id","attribute_name_id","value","type"}, tables);
		
		// PK
		createKey("pk_attribute_value", t_attribute_value, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		
		// FK
		createKey("k_av_ov_id", t_attribute_value, Key.FOREIGN_KEY,
				pk_object_version, new String[] {"object_version_id"}, primaryKeys, foreignKeys);
		
		createKey("k_av_a_id", t_attribute_value, Key.FOREIGN_KEY,
				pk_attribute, new String[] {"attribute_name_id"}, primaryKeys, foreignKeys);
		
		/**/
		
		/* Relation Table */
		TableInfo t_relation = createTable("", "relation",
				new String[] {"id","source_object_version_id","target_object_version_id",
				"relationship_id","start_timestamp","end_timestamp"}, tables);
		
		// PK
		createKey("pk_relation", t_relation, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		
		// FK
		createKey("k_r_ov_s_id", t_relation, Key.FOREIGN_KEY,
				pk_object_version, new String[] {"source_object_version_id"}, primaryKeys, foreignKeys);
		
		createKey("k_r_ov_t_id", t_relation, Key.FOREIGN_KEY,
				pk_object_version, new String[] {"target_object_version_id"}, primaryKeys, foreignKeys);
		
		createKey("k_r_rs_id", t_relation, Key.FOREIGN_KEY,
				pk_relationship, new String[] {"relationship_id"}, primaryKeys, foreignKeys);
		
		/**/
		
		/* Process Table */
		TableInfo t_process = createTable("", "process",
				new String[] {"id","name"},tables);
		
		// PK
		Key pk_process = createKey("pk_process", t_process, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Activity Table */
		TableInfo t_activity = createTable("", "activity",
				new String[] {"id","process_id","name"},tables);
		
		// PK
		Key pk_activity = createKey("pk_activity", t_activity, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		// FK
		createKey("fk_activity_process", t_activity, Key.FOREIGN_KEY,
				pk_process, new String[] {"process_id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Activity Instance Table */
		TableInfo t_activity_instance = createTable("", "activity_instance",
				new String[] {"id","activity_id"},tables);
		
		// PK
		Key pk_activity_instance = createKey("pk_activity_instance", t_activity_instance, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		// FK
		createKey("fk_activity_instance_activity", t_activity_instance, Key.FOREIGN_KEY,
				pk_activity, new String[] {"activity_id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Case Table */
		TableInfo t_case = createTable("", "case",
				new String[] {"id","name"},tables);
		
		// PK
		Key pk_case = createKey("pk_case", t_case, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Activity Instance To Case Table */
		TableInfo t_activity_instance_to_case = createTable("", "activity_instance_to_case",
				new String[] {"case_id","activity_instance_id"},tables);
		
		// PK
		Key pk_activity_instance_to_case = createKey("pk_activity_instance_to_case", t_activity_instance_to_case, Key.PRIMARY_KEY,
				null, new String[] {"case_id","activity_instance_id"}, primaryKeys, foreignKeys);
		// FK
		createKey("fk_activity_instance_to_case_case", t_activity_instance_to_case, Key.FOREIGN_KEY,
				pk_case, new String[] {"case_id"}, primaryKeys, foreignKeys);
		createKey("fk_activity_instance_to_case_activity_instance", t_activity_instance_to_case, Key.FOREIGN_KEY,
				pk_activity_instance, new String[] {"activity_instance_id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Event Table */
		TableInfo t_event = createTable("", "event",
				new String[] {"id","activity_instance_id","ordering","timestamp","lifecycle","resource"},tables);
		
		// PK
		Key pk_event = createKey("pk_event", t_event, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		// FK
		createKey("fk_event_activity_instance", t_event, Key.FOREIGN_KEY,
				pk_activity_instance, new String[] {"activity_instance_id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Event Attribute Table */
		TableInfo t_event_attribute = createTable("", "event_attribute_name",
				new String[] {"id","name"},tables);
		
		// PK
		Key pk_event_attribute = createKey("pk_event_attribute", t_event_attribute, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Event_attribute_value Table */
		TableInfo t_event_attribute_value = createTable("", "event_attribute_value",
				new String[] {"id","event_id","event_attribute_name_id","value","type"},tables);
		
		// PK
		Key pk_event_attribute_value = createKey("pk_event_attribute_value", t_event_attribute_value, Key.PRIMARY_KEY,
				null, new String[] {"id"}, primaryKeys, foreignKeys);
		// FK
		createKey("fk_event_attribute_value_event", t_event_attribute_value, Key.FOREIGN_KEY,
				pk_event, new String[] {"event_id"}, primaryKeys, foreignKeys);
		createKey("fk_event_attribute_value_event_attribute", t_event_attribute_value, Key.FOREIGN_KEY,
				pk_event_attribute, new String[] {"event_attribute_name_id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Event_to_object_version Table */
		TableInfo t_event_to_object_version = createTable("", "event_to_object_version",
				new String[] {"event_id","object_version_id","label"},tables);
		
		// PK
		Key pk_event_to_object_version = createKey("pk_event_to_object_version", t_event_to_object_version, Key.PRIMARY_KEY,
				null, new String[] {"event_id","object_version_id"}, primaryKeys, foreignKeys);
		// FK
		createKey("fk_event_to_object_version_event", t_event_to_object_version, Key.FOREIGN_KEY,
				pk_event, new String[] {"event_id"}, primaryKeys, foreignKeys);
		createKey("fk_event_to_object_version_object_version", t_event_to_object_version, Key.FOREIGN_KEY,
				pk_object_version, new String[] {"object_version_id"}, primaryKeys, foreignKeys);
		/**/
		
		/* Building Keys Per Table HashMap */
		for (Key k: primaryKeys.values()) {
			TableInfo t = k.table;
			List<Key> keysForT = keysPerTable.get(t);
			if (keysForT == null) {
				keysForT = new ArrayList<>();
				keysPerTable.put(t, keysForT);
			}
			keysForT.add(k);
		}
		
		for (Key k: foreignKeys.values()) {
			TableInfo t = k.table;
			List<Key> keysForT = keysPerTable.get(t);
			if (keysForT == null) {
				keysForT = new ArrayList<>();
				keysPerTable.put(t, keysForT);
			}
			keysForT.add(k);
		}
		/**/
		
		dm.setTables(tables);
		dm.setForeignKeys(foreignKeys);
		dm.setPrimaryKeys(primaryKeys);
		dm.setKeysPerTable(keysPerTable);
		
		return dm;
	}
	
	private Key createKey(String name, TableInfo table, int type, Key refers_to_key,
			String[] colnames_source_ordered, Hashtable<String, Key> primaryKeys, Hashtable<String, Key> foreignKeys) {
		
		Key k = new Key();
		k.name = name;
		k.table = table;
		k.type = type;
		
		k.fields = new ArrayList<>();
		k.fields_ordered = new HashMap<>();
		
		int j = 0;
		for (String cn: colnames_source_ordered) {
			for (Column c: table.columns) {
				if (c.name.equals(cn)) {
					k.fields.add(c);
					k.fields_ordered.put(j, c);
					j++;
				}
			}
		}
				
		if (type == Key.FOREIGN_KEY) {
			k.refers_to = refers_to_key;
			k.refers_to_column = new HashMap<>();
				
			for (int i = 0; i < k.fields_ordered.size(); i++) {
				Column s = k.fields_ordered.get(i);
				Column t = k.refers_to.fields_ordered.get(i);
				k.refers_to_column.put(s, t);
			}
			foreignKeys.put(k.name, k);
		} else if (type == Key.PRIMARY_KEY) {
			primaryKeys.put(k.name, k);
		}
		
		return k;
	}
	
	private TableInfo createTable(String db, String name, String[] columnNames, List<TableInfo> tables) {
		TableInfo t = new TableInfo();
		t.db = db;
		t.name = name;
		
		for (String cn: columnNames) {
			createColumn(t,cn);
		}
		
		tables.add(t);
		return t;
	}
	
	private Column createColumn(TableInfo table, String name) {
		Column c = new Column();
		c.table = table;
		c.name = name;
		if (table.columns == null) {
			table.columns = new ArrayList<>();
		}
		table.columns.add(c);
		return c;
	}

}