package org.processmining.redologs.ui;

import org.processmining.openslex.metamodel.SLEXMMAttribute;
import org.processmining.openslex.metamodel.SLEXMMAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMObject;
import org.processmining.openslex.metamodel.SLEXMMObjectResultSet;
import org.processmining.openslex.metamodel.SLEXMMObjectVersion;
import org.processmining.openslex.metamodel.SLEXMMObjectVersionResultSet;
import org.processmining.openslex.metamodel.SLEXMMRelation;
import org.processmining.openslex.metamodel.SLEXMMRelationResultSet;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
import org.processmining.redologs.ui.components.CustomInternalFrame;

import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;

public class FrameMetaModelInspect extends CustomInternalFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3775031378258022448L;
	
	private SLEXMMStorageMetaModel mmstrg;
	private JTable tableObjects;
	private JTable tableObjectVersions;
	private JTable tableObjectRelations;
		
	public FrameMetaModelInspect(SLEXMMStorageMetaModel mmstrg) {
		super("MetaModel Inspector");
		this.mmstrg = mmstrg;
		
		//BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		setBounds(715, 30, 820, 670);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane.setLeftComponent(splitPane_1);
		
		JPanel leftTopPanel = new JPanel();
		splitPane_1.setLeftComponent(leftTopPanel);
		
		JPanel rightTopPanel = new JPanel();
		splitPane_1.setRightComponent(rightTopPanel);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane.setRightComponent(splitPane_2);
		
		JPanel leftBottomPanel = new JPanel();
		leftBottomPanel.setMinimumSize(new Dimension(180, 0));
		splitPane_2.setLeftComponent(leftBottomPanel);
		leftBottomPanel.setLayout(new BorderLayout(0, 0));
		
		tableObjects = new JTable();
		tableObjects.setFillsViewportHeight(true);
		tableObjects.setModel(new CustomTableModel());
		
		tableObjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableObjects.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int[] selected = getSelectedObject();
				setObjectVersionsTableContent(selected[0],selected[1]);
				setObjectRelationsTableContent(selected[0]);
			}
		});
		
		JScrollPane scrollPaneTable = new JScrollPane(tableObjects);
		leftBottomPanel.add(scrollPaneTable);
		
		JPanel rightBottomPanel = new JPanel();
		splitPane_2.setRightComponent(rightBottomPanel);
		rightBottomPanel.setLayout(new BoxLayout(rightBottomPanel, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		rightBottomPanel.add(tabbedPane);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setResizeWeight(0.5);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		tabbedPane.addTab("New tab", null, splitPane_3, null);
		
		JPanel topObjectVersionsPanel = new JPanel();
		splitPane_3.setTopComponent(topObjectVersionsPanel);
		topObjectVersionsPanel.setLayout(new BorderLayout(0, 0));
		
		tableObjectVersions = new JTable();
		tableObjectVersions.setFillsViewportHeight(true);
		tableObjectVersions.setModel(new ObjectVersionsTableModel());
		
		JScrollPane scrollPaneTableObjectVersions = new JScrollPane(tableObjectVersions);
		topObjectVersionsPanel.add(scrollPaneTableObjectVersions);
		
		JPanel bottomObjectVersionsPanel = new JPanel();
		splitPane_3.setBottomComponent(bottomObjectVersionsPanel);
		bottomObjectVersionsPanel.setLayout(new BorderLayout(0, 0));
		
		tableObjectRelations = new JTable();
		tableObjectRelations.setFillsViewportHeight(true);
		tableObjectRelations.setModel(new ObjectRelationsTableModel());
		
		JScrollPane scrollPaneTableObjectRelations = new JScrollPane(tableObjectRelations);
		bottomObjectVersionsPanel.add(scrollPaneTableObjectRelations);
		
		setObjectsTableContent();
	}
	
	private int[] getSelectedObject() {
		int[] selected = new int[2];
		
		int selectedRow = tableObjects.getSelectedRow();
		
		selected[0] = (int) tableObjects.getModel().getValueAt(selectedRow, 0);
		selected[1] = (int) tableObjects.getModel().getValueAt(selectedRow, 1);
		
		return selected;
	}
	
	private class CustomTableModel extends DefaultTableModel {
	
		Class[] columnTypes = new Class[] {	Integer.class, Integer.class	};
		boolean[] columnEditables = new boolean[] { false, false };
		
		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
		
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
			
		public CustomTableModel() {
			super(new String[] { "Object Id", "Class Id" }, 0);
		}
		
	}
	
	public void setObjectsTableContent() {
		CustomTableModel model = (CustomTableModel) tableObjects.getModel();
		tableObjects.getColumnModel().getColumn(0).setMinWidth(75);
		tableObjects.getColumnModel().getColumn(1).setMinWidth(75);
		
		SLEXMMObjectResultSet orset = mmstrg.getObjects();
		SLEXMMObject obj = null;
		
		while ((obj = orset.getNext()) != null) {
			model.addRow(new Object[] {obj.getId(),obj.getClassId()});
		}
		
	}
	
	private class ObjectVersionsTableModel extends DefaultTableModel {
		
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
			super(new String[] { "Version Id", "Object Id", "Event Id" }, 0);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
		}
		
	}
	
	public void setObjectVersionsTableContent(int objectId, int classId) {
		ObjectVersionsTableModel model = new ObjectVersionsTableModel();
		
		tableObjectVersions.setModel(model);
		
		SLEXMMObjectVersionResultSet orset = mmstrg.getObjectVersionsForObjectOrdered(objectId);
		SLEXMMObjectVersion objv = null;
		
		List<SLEXMMAttribute> attrs = mmstrg.getAttributesForClass(classId);
		List<String> attrsName = new ArrayList<>();
		
		for (SLEXMMAttribute at: attrs) {
			attrsName.add(at.getName());
		}
		Collections.sort(attrsName);
		
		for (String name: attrsName) {
			model.addColumn(name);
			model.addColumnClass(String.class);
		}
				
		while ((objv = orset.getNext()) != null) {
			
			Object[] row = new Object[model.getColumnCount()];
			
			row[0] = Integer.valueOf(objv.getId());
			row[1] = Integer.valueOf(objv.getObjectId());
			row[2] = Integer.valueOf(objv.getEventId());
			
			for (SLEXMMAttribute at: objv.getAttributeValues().keySet()) {
				String colName = at.getName();
				SLEXMMAttributeValue attrVal = objv.getAttributeValues().get(at);
				String value = attrVal.getValue();
				row[3+attrsName.indexOf(colName)] = value;
			}
			
			model.addRow(row);
		}
		
	}
	
	private class ObjectRelationsTableModel extends DefaultTableModel {
		
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
					"Source Object Id", "Target Object Id",
					"Start Source Object Version Id", "End Source Object Id",
					"Start Target Object Version Id", "End Target Object Id",
					"Event Id",}, 0);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
		}
		
	}
	
	public void setObjectRelationsTableContent(int objectId) {
		ObjectRelationsTableModel model = new ObjectRelationsTableModel();
		
		tableObjectRelations.setModel(model);
		
		SLEXMMRelationResultSet orrset = mmstrg.getRelationsForSourceObject(objectId);
		SLEXMMRelation rel = null;
		
//		List<SLEXMMAttribute> attrs = mmstrg.getAttributesForClass(classId);
//		List<String> attrsName = new ArrayList<>();
//		
//		for (SLEXMMAttribute at: attrs) {
//			attrsName.add(at.getName());
//		}
//		Collections.sort(attrsName);
//		
//		for (String name: attrsName) {
//			model.addColumn(name);
//			model.addColumnClass(String.class);
//		}
				
		while ((rel = orrset.getNext()) != null) {
			
			Object[] row = new Object[model.getColumnCount()];
			
			row[0] = Integer.valueOf(rel.getId());
			row[1] = Integer.valueOf(rel.getRelationshipId());
			row[2] = Integer.valueOf(rel.getSourceObjectId());
			row[3] = Integer.valueOf(rel.getTargetObjectId());
			row[4] = Integer.valueOf(rel.getStartSourceObjectVersionId());
			row[5] = Integer.valueOf(rel.getEndSourceObjectVersionId());
			row[6] = Integer.valueOf(rel.getStartTargetObjectVersionId());
			row[7] = Integer.valueOf(rel.getEndTargetObjectVersionId());
			row[8] = Integer.valueOf(rel.getEventId());
			
//			for (SLEXMMAttribute at: objv.getAttributeValues().keySet()) {
//				String colName = at.getName();
//				SLEXMMAttributeValue attrVal = objv.getAttributeValues().get(at);
//				String value = attrVal.getValue();
//				row[3+attrsName.indexOf(colName)] = value;
//			}
			
			model.addRow(row);
		}
		
	}
}