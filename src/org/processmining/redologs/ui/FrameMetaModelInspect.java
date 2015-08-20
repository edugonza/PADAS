package org.processmining.redologs.ui;

import org.processmining.openslex.metamodel.SLEXMMAttribute;
import org.processmining.openslex.metamodel.SLEXMMAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMDataModel;
import org.processmining.openslex.metamodel.SLEXMMDataModelResultSet;
import org.processmining.openslex.metamodel.SLEXMMEvent;
import org.processmining.openslex.metamodel.SLEXMMEventAttribute;
import org.processmining.openslex.metamodel.SLEXMMEventAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMEventCollection;
import org.processmining.openslex.metamodel.SLEXMMEventCollectionResultSet;
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
	private JTable tableCollections;
	private JTable tableEvents;
	private JTable tableEventAttributes;
	private JProgressBar topProgressBar;
	private DiagramComponent datamodelPanel;
		
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
		
		datamodelPanel = new DiagramComponent();
		datamodelPanel.setMinimumSize(new Dimension(300,200));
		leftTopPanel.add(datamodelPanel, BorderLayout.CENTER);
		
		JPanel rightTopPanel = new JPanel();
		splitPane_1.setRightComponent(rightTopPanel);
		rightTopPanel.setLayout(new BorderLayout(0, 0));
		
		topProgressBar = new JProgressBar();
		rightTopPanel.add(topProgressBar,BorderLayout.NORTH);
		
		tableCollections = new JTable();
		tableCollections.setFillsViewportHeight(true);
		tableCollections.setModel(new CollectionsTableModel());
		
		tableCollections.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCollections.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer selected = getSelectedCollection();
				if (selected != null) {
					setEventsTableContentFromCollection(selected);
				}
			}
		});
		
		JSplitPane splitPanelTopLeft = new JSplitPane();
		splitPanelTopLeft.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		JScrollPane scrollPane_left = new JScrollPane(tableCollections);
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
		
		setCollectionsTableContent();
		setObjectsTableContent();
		
		setDataModel();
	}
	
	private void setDataModel() {
		SLEXMMDataModelResultSet dmrset = mmstrg.getDataModels();
		
		SLEXMMDataModel dm = dmrset.getNext();
		
		if (dm != null) {
			datamodelPanel.setDataModel(dm);
		}
	}
	
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
	
	private Integer getSelectedCollection() {
		int selectedRow = tableCollections.getSelectedRow();
		if (selectedRow >= 0) {
			return (int) tableCollections.getModel().getValueAt(selectedRow, 0);
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
			super(new String[] { "Relation Id", "Relationship Id",
					"Source Object Id", "Target Object Id",
					"Start Source Object Version Id", "End Source Object Version Id",
					"Start Target Object Version Id", "End Target Object Version Id",
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
		
		SLEXMMRelationResultSet orrset = mmstrg.getRelationsForSourceObjectOrdered(objectId);
		SLEXMMRelation rel = null;
						
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
						
			model.addRow(row);
		}
		
	}
	
	private class CollectionsTableModel extends DefaultTableModel {
		
		Class[] columnTypes = new Class[] {	Integer.class, String.class	};
		boolean[] columnEditables = new boolean[] { false, false };
		
		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
		
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
			
		public CollectionsTableModel() {
			super(new String[] { "Collection Id", "Name" }, 0);
		}
		
	}
	
	public void setCollectionsTableContent() {
		CollectionsTableModel model = (CollectionsTableModel) tableCollections.getModel();
		tableCollections.getColumnModel().getColumn(0).setMinWidth(75);
		tableCollections.getColumnModel().getColumn(1).setMinWidth(75);
		
		SLEXMMEventCollectionResultSet orset = mmstrg.getEventCollections();
		SLEXMMEventCollection evCol = null;
		
		while ((evCol = orset.getNext()) != null) {
			model.addRow(new Object[] {evCol.getId(),evCol.getName()});
		}
		
	}
	
	private class EventsTableModel extends DefaultTableModel {
		
		Class[] columnTypes = new Class[] {	Integer.class, Integer.class, Integer.class };
		boolean[] columnEditables = new boolean[] { false, false, false };
		
		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
		
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
			
		public EventsTableModel() {
			super(new String[] { "Event Id", "Collection Id", "Ordering" }, 0);
		}
		
	}
	
	public void setEventsTableContentFromCollection(final int colId) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					topProgressBar.setIndeterminate(true);
					tableCollections.setEnabled(false);
					
					EventsTableModel model = new EventsTableModel();
					
					tableEvents.setModel(model);
					
					tableEvents.getColumnModel().getColumn(0).setMinWidth(75);
					tableEvents.getColumnModel().getColumn(1).setMinWidth(75);
					tableEvents.getColumnModel().getColumn(2).setMinWidth(75);
				
					SLEXMMEventResultSet orset = mmstrg.getEventsOfCollection(colId);
					SLEXMMEvent ev = null;
				
					while ((ev = orset.getNext()) != null) {
						model.addRow(new Object[] {ev.getId(),ev.getCollectionId(), ev.getOrder()});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				topProgressBar.setIndeterminate(false);
				tableCollections.setEnabled(true);
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