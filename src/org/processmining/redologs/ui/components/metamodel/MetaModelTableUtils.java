package org.processmining.redologs.ui.components.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.processmining.openslex.metamodel.SLEXMMActivity;
import org.processmining.openslex.metamodel.SLEXMMActivityInstance;
import org.processmining.openslex.metamodel.SLEXMMActivityInstanceResultSet;
import org.processmining.openslex.metamodel.SLEXMMAttribute;
import org.processmining.openslex.metamodel.SLEXMMAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMCase;
import org.processmining.openslex.metamodel.SLEXMMCaseResultSet;
import org.processmining.openslex.metamodel.SLEXMMClass;
import org.processmining.openslex.metamodel.SLEXMMEvent;
import org.processmining.openslex.metamodel.SLEXMMEventAttribute;
import org.processmining.openslex.metamodel.SLEXMMEventAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMEventResultSet;
import org.processmining.openslex.metamodel.SLEXMMObject;
import org.processmining.openslex.metamodel.SLEXMMObjectResultSet;
import org.processmining.openslex.metamodel.SLEXMMObjectVersion;
import org.processmining.openslex.metamodel.SLEXMMObjectVersionResultSet;
import org.processmining.openslex.metamodel.SLEXMMRelation;
import org.processmining.openslex.metamodel.SLEXMMRelationResultSet;
import org.processmining.openslex.metamodel.SLEXMMRelationship;

public class MetaModelTableUtils {

	public static Integer[] getSelectedObject(JTable table) {
		Integer[] selected = new Integer[2];

		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			selected[0] = (int) table.getModel().getValueAt(selectedRow, 0);
			selected[1] = (int) table.getModel().getValueAt(selectedRow, 1);

			return selected;
		} else {
			return null;
		}
	}

	public static Integer getSelectedCase(JTable table) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			return (int) table.getModel().getValueAt(selectedRow, 0);
		} else {
			return null;
		}
	}

	public static Integer getSelectedEvent(JTable table) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			return (int) table.getModel().getValueAt(selectedRow, 0);
		} else {
			return null;
		}
	}
	
	
	public static class ActivitiesTableModel extends DefaultTableModel {

		Class[] columnTypes = new Class[] { Integer.class, String.class,
				Integer.class };
		boolean[] columnEditables = new boolean[] { false, false, false };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

		public ActivitiesTableModel() {
			super(new String[] { "Activity Id", "Name", "Process Id" }, 0);
		}

	}

	public static void setActivitiesTableContent(JTable table, List activities) throws Exception {
		ActivitiesTableModel model = new ActivitiesTableModel();
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);
		table.getColumnModel().getColumn(2).setMinWidth(75);

		for (Object o : activities) {
			SLEXMMActivity act = (SLEXMMActivity) o;
			model.addRow(new Object[] { act.getId(), act.getName(),
					act.getProcessId() });
		}

	}
	
	public static class ClassesTableModel extends DefaultTableModel {

		Class[] columnTypes = new Class[] { Integer.class, String.class,
				Integer.class };
		boolean[] columnEditables = new boolean[] { false, false, false };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

		public ClassesTableModel() {
			super(new String[] { "Class Id", "Name", "DataModel Id" }, 0);
		}

	}

	public static void setClassesTableContent(JTable table, List<Object> classes) throws Exception {
		ClassesTableModel model = new ClassesTableModel();
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);
		table.getColumnModel().getColumn(2).setMinWidth(75);

		for (Object o : classes) {
			SLEXMMClass c = (SLEXMMClass) o;
			model.addRow(new Object[] { c.getId(), c.getName(),
					c.getDataModelId() });
		}

	}
	
	public static class AttributesTableModel extends DefaultTableModel {

		Class[] columnTypes = new Class[] { Integer.class, Integer.class,
				String.class };
		boolean[] columnEditables = new boolean[] { false, false, false };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

		public AttributesTableModel() {
			super(new String[] { "Attribute Id", "Class Id", "Name" }, 0);
		}

	}

	public static void setAttributesTableContent(JTable table, List<Object> classes) throws Exception {
		AttributesTableModel model = new AttributesTableModel();
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);
		table.getColumnModel().getColumn(2).setMinWidth(75);

		for (Object o : classes) {
			SLEXMMAttribute c = (SLEXMMAttribute) o;
			model.addRow(new Object[] { c.getId(), c.getClassId(),
					c.getName() });
		}

	}

	public static class ObjectsTableModel extends DefaultTableModel {

		Class[] columnTypes = new Class[] { Integer.class, Integer.class };
		boolean[] columnEditables = new boolean[] { false, false };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

		public ObjectsTableModel() {
			super(new String[] { "Object Id", "Class Id" }, 0);
		}

	}

	public static void setObjectsTableContent(JTable table, SLEXMMObjectResultSet orset) throws Exception {
		ObjectsTableModel model = new ObjectsTableModel();
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);

		SLEXMMObject obj = null;

		while ((obj = orset.getNext()) != null) {
			model.addRow(new Object[] { obj.getId(), obj.getClassId() });
		}

	}

	public static void setObjectsTableContent(JTable table, List<Object> list) throws Exception {
		ObjectsTableModel model = new ObjectsTableModel();
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);

		SLEXMMObject obj = null;
		for (Object o : list) {
			obj = (SLEXMMObject) o;
			model.addRow(new Object[] { obj.getId(), obj.getClassId() });
		}

	}

	public static class ActivityInstanceTableModel extends DefaultTableModel {

		Class[] columnTypes = new Class[] { Integer.class, Integer.class };
		boolean[] columnEditables = new boolean[] { false, false };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

		public ActivityInstanceTableModel() {
			super(new String[] { "Activity Instance Id", "Activity Id" }, 0);
		}

	}

	public static void setActivityInstancesTableContent(JTable table, SLEXMMActivityInstanceResultSet airset) throws Exception {
		ActivityInstanceTableModel model = new ActivityInstanceTableModel();
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);

		SLEXMMActivityInstance ai = null;

		while ((ai = airset.getNext()) != null) {
			model.addRow(new Object[] { ai.getId(), ai.getActivityId() });
		}

	}

	public static void setActivityInstancesTableContent(JTable table, List<Object> list) throws Exception {
		ActivityInstanceTableModel model = new ActivityInstanceTableModel();
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);

		SLEXMMActivityInstance ai = null;
		for (Object o : list) {
			ai = (SLEXMMActivityInstance) o;
			model.addRow(new Object[] { ai.getId(), ai.getActivityId() });
		}

	}
	
	public static class ObjectVersionsTableModel extends DefaultTableModel {

		ArrayList<Class> columnTypes = new ArrayList<>();

		public Class getColumnClass(int columnIndex) {
			return columnTypes.get(columnIndex);
		}

		public void addColumnClass(Class c) {
			columnTypes.add(c);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public ObjectVersionsTableModel() {
			super(new String[] { "Version Id", "Object Id", "Start Timestamp",
					"End Timestamp" }, 0);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Long.class);
			columnTypes.add(Long.class);
		}

	}

	public static void setObjectVersionsTableContent(JTable table, SLEXMMObjectVersionResultSet orset) throws Exception {
		ObjectVersionsTableModel model = new ObjectVersionsTableModel();

		table.setModel(model);

		SLEXMMObjectVersion objv = null;

		while ((objv = orset.getNext()) != null) {

			Object[] row = new Object[model.getColumnCount()];

			row[0] = Integer.valueOf(objv.getId());
			row[1] = Integer.valueOf(objv.getObjectId());
			row[2] = Long.valueOf(objv.getStartTimestamp());
			row[3] = Long.valueOf(objv.getEndTimestamp());

			model.addRow(row);
		}

	}

	public static void setObjectVersionsTableContent(JTable table, List<Object> list) throws Exception {
		ObjectVersionsTableModel model = new ObjectVersionsTableModel();

		table.setModel(model);

		for (Object o : list) {
			SLEXMMObjectVersion objv = (SLEXMMObjectVersion) o;

			Object[] row = new Object[model.getColumnCount()];

			row[0] = Integer.valueOf(objv.getId());
			row[1] = Integer.valueOf(objv.getObjectId());
			row[2] = Long.valueOf(objv.getStartTimestamp());
			row[3] = Long.valueOf(objv.getEndTimestamp());

			model.addRow(row);
		}

	}
	
	public static class RelationshipTableModel extends DefaultTableModel {

		ArrayList<Class> columnTypes = new ArrayList<>();

		public Class getColumnClass(int columnIndex) {
			return columnTypes.get(columnIndex);
		}

		public void addColumnClass(Class c) {
			columnTypes.add(c);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public RelationshipTableModel() {
			super(new String[] { "Relationship Id", "Source Class Id", "Target Class Id",
					"Name" }, 0);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(String.class);
		}

	}

	public static void setRelationshipsTableContent(JTable table, List<Object> list) throws Exception {
		RelationshipTableModel model = new RelationshipTableModel();

		table.setModel(model);

		for (Object o : list) {
			SLEXMMRelationship rs = (SLEXMMRelationship) o;

			Object[] row = new Object[model.getColumnCount()];

			row[0] = Integer.valueOf(rs.getId());
			row[1] = Integer.valueOf(rs.getSourceClassId());
			row[2] = Integer.valueOf(rs.getTargetClassId());
			row[3] = String.valueOf(rs.getName());

			model.addRow(row);
		}

	}

	public static class ObjectRelationsTableModel extends DefaultTableModel {

		ArrayList<Class> columnTypes = new ArrayList<>();

		public Class getColumnClass(int columnIndex) {
			return columnTypes.get(columnIndex);
		}

		public void addColumnClass(Class c) {
			columnTypes.add(c);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public ObjectRelationsTableModel() {
			super(new String[] { "Relation Id", "Relationship Id",
					"Source Object Version Id", "Target Object Version Id",
					"Start Timestamp", "End Timestamp" }, 0);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Long.class);
			columnTypes.add(Long.class);
		}

	}

	public static void setObjectRelationsTableContent(JTable table, SLEXMMRelationResultSet[] orrset) throws Exception {
		ObjectRelationsTableModel model = new ObjectRelationsTableModel();

		table.setModel(model);

		for (int i = 0; i < orrset.length; i++) {
		
			SLEXMMRelation rel = null;
		
			while ((rel = orrset[i].getNext()) != null) {
				
				Object[] row = new Object[model.getColumnCount()];

				row[0] = Integer.valueOf(rel.getId());
				row[1] = Integer.valueOf(rel.getRelationshipId());
				row[2] = Integer.valueOf(rel.getSourceObjectVersionId());
				row[3] = Integer.valueOf(rel.getTargetObjectVersionId());
				row[4] = Long.valueOf(rel.getStartTimestamp());
				row[5] = Long.valueOf(rel.getEndTimestamp());

				model.addRow(row);
			}
		}

	}
	
	public static void setObjectRelationsTableContent(JTable table, List<Object> list) throws Exception {
		ObjectRelationsTableModel model = new ObjectRelationsTableModel();

		table.setModel(model);

		for  (Object o: list) {
				
			SLEXMMRelation rel = (SLEXMMRelation) o;
				
			Object[] row = new Object[model.getColumnCount()];

			row[0] = Integer.valueOf(rel.getId());
			row[1] = Integer.valueOf(rel.getRelationshipId());
			row[2] = Integer.valueOf(rel.getSourceObjectVersionId());
			row[3] = Integer.valueOf(rel.getTargetObjectVersionId());
			row[4] = Long.valueOf(rel.getStartTimestamp());
			row[5] = Long.valueOf(rel.getEndTimestamp());

			model.addRow(row);
		}

	}

	public static class CasesTableModel extends DefaultTableModel {

		Class[] columnTypes = new Class[] { Integer.class, String.class };
		boolean[] columnEditables = new boolean[] { false, false };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

		public CasesTableModel() {
			super(new String[] { "Case Id", "Name" }, 0);
		}

	}

	public static void setCasesTableContent(JTable table, SLEXMMCaseResultSet orset) throws Exception {
		CasesTableModel model = new CasesTableModel();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);

		SLEXMMCase c = null;

		while ((c = orset.getNext()) != null) {
			model.addRow(new Object[] { c.getId(), c.getName() });
		}

	}
	
	public static void setCasesTableContent(JTable table, List<Object> list) throws Exception {
		try {
			CasesTableModel model = new CasesTableModel();
			table.setModel(model);
			table.getColumnModel().getColumn(0).setMinWidth(75);
			table.getColumnModel().getColumn(1).setMinWidth(75);

			for (Object o : list) {
				SLEXMMCase c = (SLEXMMCase) o;

				model.addRow(new Object[] { c.getId(), c.getName() });
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	public static class EventsTableModel extends DefaultTableModel {

		Class[] columnTypes = new Class[] { Integer.class, Integer.class };
		boolean[] columnEditables = new boolean[] { false, false };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

		public EventsTableModel() {
			super(new String[] { "Event Id", "Ordering" }, 0);
		}

	}

	public static void setEventsTableContent(final JTable table, final SLEXMMEventResultSet orset, final JProgressBar progress) throws Exception {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					if (progress != null) {
						progress.setIndeterminate(true);
					}

					EventsTableModel model = new EventsTableModel();

					table.setModel(model);

					table.getColumnModel().getColumn(0)
							.setMinWidth(75);
					table.getColumnModel().getColumn(1)
							.setMinWidth(75);

					SLEXMMEvent ev = null;

					while ((ev = orset.getNext()) != null) {
						model.addRow(new Object[] { ev.getId(), ev.getOrder() });
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (progress != null) {
					progress.setIndeterminate(false);
				}
			}
		});

		thread.start();

	}
	
	public static void setEventsTableContent(final JTable table, final List<Object> list, final JProgressBar progress) throws Exception {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					if (progress != null) {
						progress.setIndeterminate(true);
					}

					EventsTableModel model = new EventsTableModel();

					table.setModel(model);

					table.getColumnModel().getColumn(0)
							.setMinWidth(75);
					table.getColumnModel().getColumn(1)
							.setMinWidth(75);

					SLEXMMEvent ev = null;

					for (Object o : list) {
						ev = (SLEXMMEvent) o;
						model.addRow(new Object[] { ev.getId(), ev.getOrder() });
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (progress != null) {
					progress.setIndeterminate(false);
				}
			}
		});

		thread.start();

	}

	public static class EventAttributesTableModel extends DefaultTableModel {

		Class[] columnTypes = new Class[] { String.class, String.class,
				String.class };
		boolean[] columnEditables = new boolean[] { false, false, false };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

		public EventAttributesTableModel() {
			super(new String[] { "Attribute Name", "Value", "Type" }, 0);
		}

	}

	public static void setEventAttributesTableContent(JTable table,
			HashMap<SLEXMMEventAttribute, SLEXMMEventAttributeValue> attrs,
			String lifecycle, String resource, String timestamp) throws Exception {

		EventAttributesTableModel model = new EventAttributesTableModel();

		table.setModel(model);

		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);

		for (SLEXMMEventAttribute at : attrs.keySet()) {
			SLEXMMEventAttributeValue attV = attrs.get(at);
			model.addRow(new Object[] { at.getName(), attV.getValue(),
					attV.getType() });
		}

		model.addRow(new Object[] { "Event Lifecycle", lifecycle,
				"STRING" });
		model.addRow(new Object[] { "Event Resource", resource, "STRING" });
		model.addRow(new Object[] { "Event Timestamp", timestamp, "LONG" });

	}

	public static class ObjectVersionAttributesTableModel extends DefaultTableModel {

		Class[] columnTypes = new Class[] { String.class, String.class,
				String.class };
		boolean[] columnEditables = new boolean[] { false, false, false };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

		public ObjectVersionAttributesTableModel() {
			super(new String[] { "Attribute Name", "Value", "Type" }, 0);
		}

	}

	public static void setObjectVersionAttributesTableContent(JTable table,
			HashMap<SLEXMMAttribute, SLEXMMAttributeValue> attrs) throws Exception {

		ObjectVersionAttributesTableModel model = new ObjectVersionAttributesTableModel();

		table.setModel(model);

		table.getColumnModel().getColumn(0)
				.setMinWidth(75);
		table.getColumnModel().getColumn(1)
				.setMinWidth(75);
		table.getColumnModel().getColumn(1)
				.setMinWidth(75);

		for (SLEXMMAttribute at : attrs.keySet()) {
			SLEXMMAttributeValue attV = attrs.get(at);
			model.addRow(new Object[] { at.getName(), attV.getValue(),
					attV.getType() });
		}

	}
}
