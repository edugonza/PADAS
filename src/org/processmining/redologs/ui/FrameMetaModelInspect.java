package org.processmining.redologs.ui;

import org.processmining.openslex.metamodel.SLEXMMAttribute;
import org.processmining.openslex.metamodel.SLEXMMAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMCase;
import org.processmining.openslex.metamodel.SLEXMMCaseResultSet;
import org.processmining.openslex.metamodel.SLEXMMDataModel;
import org.processmining.openslex.metamodel.SLEXMMDataModelResultSet;
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
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
import org.processmining.redologs.ui.components.CustomInternalFrame;
import org.processmining.redologs.ui.components.DiagramComponent;

import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
	private JTable tableCases;
	private JTable tableEvents;
	private JTable tableEventAttributes;
	private JProgressBar topProgressBar;
	private DiagramComponent datamodelPanel;
	private JPanel processModelPanel;
		
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
		
		JPanel leftTopPanel = new JPanel(new BorderLayout(0,0));
		splitPane_1.setLeftComponent(leftTopPanel);
		
		processModelPanel = new JPanel(new BorderLayout(0,0));
		processModelPanel.setMinimumSize(new Dimension(300,200));
		leftTopPanel.add(processModelPanel, BorderLayout.CENTER);
		
		JPanel rightTopPanel = new JPanel();
		splitPane_1.setRightComponent(rightTopPanel);
		rightTopPanel.setLayout(new BorderLayout(0, 0));
		
		topProgressBar = new JProgressBar();
		rightTopPanel.add(topProgressBar,BorderLayout.NORTH);
		
		tableCases = new JTable();
		tableCases.setFillsViewportHeight(true);
		tableCases.setModel(new CasesTableModel());
		
		tableCases.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCases.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer selected = getSelectedCase();
				if (selected != null) {
					setEventsTableContentFromCase(selected);
				}
			}
		});
		
		JSplitPane splitPanelTopLeft = new JSplitPane();
		splitPanelTopLeft.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		JScrollPane scrollPane_left = new JScrollPane(tableCases);
		scrollPane_left.setMinimumSize(new Dimension(220, 0));
		splitPanelTopLeft.setLeftComponent(scrollPane_left);
		rightTopPanel.add(splitPanelTopLeft, BorderLayout.CENTER);
		
		tableEvents = new JTable();
		tableEvents.setFillsViewportHeight(true);
		tableEvents.setModel(new EventsTableModel());
		
		tableEvents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEvents.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer selected = getSelectedEvent();
				if (selected != null) {
					setEventAttributesTableContent(selected);
				}
			}
		});
		
		JSplitPane splitPanelTopRight = new JSplitPane();
		splitPanelTopLeft.setRightComponent(splitPanelTopRight);
		splitPanelTopRight.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		JScrollPane scrollPane_center = new JScrollPane(tableEvents);
		scrollPane_center.setMinimumSize(new Dimension(270, 0));
		splitPanelTopRight.setLeftComponent(scrollPane_center);
		
		tableEventAttributes = new JTable();
		tableEventAttributes.setFillsViewportHeight(true);
		tableEventAttributes.setModel(new EventAttributesTableModel());
		
		JScrollPane scrollPane_right = new JScrollPane(tableEventAttributes);
		//tableEventAttributes.setMinimumSize(new Dimension(300, 0));
		splitPanelTopRight.setRightComponent(scrollPane_right);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane.setRightComponent(splitPane_2);
		
		JPanel leftBottomPanel = new JPanel();
		leftBottomPanel.setMinimumSize(new Dimension(180, 0));
		splitPane_2.setLeftComponent(leftBottomPanel);
		leftBottomPanel.setLayout(new BorderLayout(0, 0));
		
		datamodelPanel = new DiagramComponent();
		datamodelPanel.setMinimumSize(new Dimension(300,200));
		leftBottomPanel.add(datamodelPanel, BorderLayout.CENTER);
		
		tableObjects = new JTable();
		tableObjects.setFillsViewportHeight(true);
		tableObjects.setModel(new ObjectsTableModel());
		
		tableObjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableObjects.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer[] selected = getSelectedObject();
				if (selected != null) {
					setObjectVersionsTableContent(selected[0],selected[1]);
					setObjectRelationsTableContent(selected[0]);
				}
			}
		});
		
		JScrollPane scrollPaneTable = new JScrollPane(tableObjects);
		//leftBottomPanel.add(scrollPaneTable);
		
		JPanel rightBottomPanel = new JPanel();
		JSplitPane splitPane_22 = new JSplitPane();
		splitPane_2.setRightComponent(splitPane_22);
		splitPane_22.setLeftComponent(scrollPaneTable);
		splitPane_22.setRightComponent(rightBottomPanel);
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
		
		setCasesTableContent();
		setObjectsTableContent();
		setEventsTableContent();
		setDataModel();
	}
	
	private void setDataModel() {
		SLEXMMDataModelResultSet dmrset = mmstrg.getDataModels();
		
		SLEXMMDataModel dm = dmrset.getNext();
		
		if (dm != null) {
			datamodelPanel.setDataModel(dm);
		}
	}
	
//	private void setProcessModel() {
//		SLEXMMDataModelResultSet dmrset = mmstrg.getDataModels();
//		
//		SLEXMMDataModel dm = dmrset.getNext();
//		
//		if (dm != null) {
//			processModelPanel.setProcessModel(dm);
//		}
//	}
	
	private Integer[] getSelectedObject() {
		Integer[] selected = new Integer[2];
		
		int selectedRow = tableObjects.getSelectedRow();
		if (selectedRow >= 0) {
			selected[0] = (int) tableObjects.getModel().getValueAt(selectedRow, 0);
			selected[1] = (int) tableObjects.getModel().getValueAt(selectedRow, 1);
		
			return selected;
		} else {
			return null;
		}
	}
	
	private Integer getSelectedCase() {
		int selectedRow = tableCases.getSelectedRow();
		if (selectedRow >= 0) {
			return (int) tableCases.getModel().getValueAt(selectedRow, 0);
		} else {
			return null;
		}
	}
	
	private Integer getSelectedEvent() {
		int selectedRow = tableEvents.getSelectedRow();
		if (selectedRow >= 0) {
			return (int) tableEvents.getModel().getValueAt(selectedRow, 0);
		} else {
			return null;
		}
	}
	
	private class ObjectsTableModel extends DefaultTableModel {
	
		Class[] columnTypes = new Class[] {	Integer.class, Integer.class	};
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
	
	public void setObjectsTableContent() {
		ObjectsTableModel model = (ObjectsTableModel) tableObjects.getModel();
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
			super(new String[] { "Relation Id", "Source Object Version Id", "Target Object Version Id"}, 0);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
		}
		
	}
	
	public void setObjectRelationsTableContent(int objectId) {
		ObjectRelationsTableModel model = new ObjectRelationsTableModel();
		
		tableObjectRelations.setModel(model);
		
		SLEXMMRelationResultSet orrset = mmstrg.getRelationsForSourceObjectOrdered(objectId);
		SLEXMMRelation rel = null;
						
		while ((rel = orrset.getNext()) != null) {
			
			Object[] row = new Object[model.getColumnCount()];
			
			row[0] = Integer.valueOf(rel.getId());
			row[1] = Integer.valueOf(rel.getSourceObjectVersionId());
			row[2] = Integer.valueOf(rel.getTargetObjectVersionId());
						
			model.addRow(row);
		}
		
		orrset = mmstrg.getRelationsForTargetObjectOrdered(objectId);
		rel = null;
		
		while ((rel = orrset.getNext()) != null) {
			
			Object[] row = new Object[model.getColumnCount()];
			
			row[0] = Integer.valueOf(rel.getId());
			row[1] = Integer.valueOf(rel.getSourceObjectVersionId());
			row[2] = Integer.valueOf(rel.getTargetObjectVersionId());
						
			model.addRow(row);
		}
		
	}
	
	private class CasesTableModel extends DefaultTableModel {
		
		Class[] columnTypes = new Class[] {	Integer.class, String.class	};
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
	
	public void setCasesTableContent() {
		CasesTableModel model = (CasesTableModel) tableCases.getModel();
		tableCases.getColumnModel().getColumn(0).setMinWidth(75);
		tableCases.getColumnModel().getColumn(1).setMinWidth(75);
		
		SLEXMMCaseResultSet orset = mmstrg.getCases();
		SLEXMMCase c = null;
		
		while ((c = orset.getNext()) != null) {
			model.addRow(new Object[] {c.getId(),c.getName()});
		}
		
	}
	
	private class EventsTableModel extends DefaultTableModel {
		
		Class[] columnTypes = new Class[] {	Integer.class, Integer.class };
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
	
	public void setEventsTableContent() {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					topProgressBar.setIndeterminate(true);
					//tableCollections.setEnabled(false);
					
					EventsTableModel model = new EventsTableModel();
					
					tableEvents.setModel(model);
					
					tableEvents.getColumnModel().getColumn(0).setMinWidth(75);
					tableEvents.getColumnModel().getColumn(1).setMinWidth(75);
				
					SLEXMMEventResultSet orset = mmstrg.getEventsOrdered();
					SLEXMMEvent ev = null;
				
					while ((ev = orset.getNext()) != null) {
						model.addRow(new Object[] {ev.getId(), ev.getOrder()});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				topProgressBar.setIndeterminate(false);
				//tableCollections.setEnabled(true);
			}
		});
		
		thread.start();
		
	}
	
	public void setEventsTableContentFromCase(final int caseId) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					topProgressBar.setIndeterminate(true);
					tableCases.setEnabled(false);
					
					EventsTableModel model = new EventsTableModel();
					
					tableEvents.setModel(model);
					
					tableEvents.getColumnModel().getColumn(0).setMinWidth(75);
					tableEvents.getColumnModel().getColumn(1).setMinWidth(75);
				
					SLEXMMEventResultSet orset = mmstrg.getEventsForCaseOrdered(caseId);
					SLEXMMEvent ev = null;
				
					while ((ev = orset.getNext()) != null) {
						model.addRow(new Object[] {ev.getId(), ev.getOrder()});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				topProgressBar.setIndeterminate(false);
				tableCases.setEnabled(true);
			}
		});
		
		thread.start();
		
	}
	
	private class EventAttributesTableModel extends DefaultTableModel {
		
		Class[] columnTypes = new Class[] {	String.class, String.class, String.class };
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
	
	public void setEventAttributesTableContent(int evId) {
		
		EventAttributesTableModel model = new EventAttributesTableModel();
		
		tableEventAttributes.setModel(model);
		
		tableEventAttributes.getColumnModel().getColumn(0).setMinWidth(75);
		tableEventAttributes.getColumnModel().getColumn(1).setMinWidth(75);
		tableEventAttributes.getColumnModel().getColumn(1).setMinWidth(75);
		
		HashMap<SLEXMMEventAttribute, SLEXMMEventAttributeValue> attrs = mmstrg.getAttributeValuesForEvent(evId);
		
		for (SLEXMMEventAttribute at: attrs.keySet()) {
			SLEXMMEventAttributeValue attV = attrs.get(at);
			model.addRow(new Object[] {at.getName(),attV.getValue(),attV.getType()});
		}
		
	}
}