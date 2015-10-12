package org.processmining.redologs.ui;

import org.processmining.database.metamodel.poql.POQLRunner;
import org.processmining.database.metamodel.poql.QueryResult;
import org.processmining.openslex.metamodel.SLEXMMActivity;
import org.processmining.openslex.metamodel.SLEXMMAttribute;
import org.processmining.openslex.metamodel.SLEXMMAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMCase;
import org.processmining.openslex.metamodel.SLEXMMCaseResultSet;
import org.processmining.openslex.metamodel.SLEXMMClass;
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
import org.processmining.redologs.ui.components.Autocomplete;
import org.processmining.redologs.ui.components.CustomInternalFrame;
import org.processmining.redologs.ui.components.DiagramComponent;
import org.processmining.redologs.ui.components.NodeSelectionHandler;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class FrameMetaModelInspect extends CustomInternalFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3775031378258022448L;
	
	private SLEXMMStorageMetaModel mmstrg;
	
	private JTable tableObjectsAll;
	private JTable tableObjectsPerClass;
	private JTable tableObjectsFiltered;
	
	private JTable tableObjectVersions;
	private JTable tableObjectRelations;
	
	private JTable tableCasesAll;
	private JTable tableCasesFiltered;
	
	private JTable tableEventsAll;
	private JTable tableEventsPerCase;
	private JTable tableEventsFiltered;
	
	private JTable tableEventAttributes;
	
	private JTable tableObjectVersionAttributes;
	
	private JProgressBar topProgressBar;
	private DiagramComponent datamodelPanel;
	private JPanel processModelPanel;
	private JTable processActivitiesTable;
	
	private JTabbedPane casesTabbedPane;
		
	public FrameMetaModelInspect(SLEXMMStorageMetaModel mmstrg) {
		super("MetaModel Inspector");
		this.mmstrg = mmstrg;
		
		setBounds(715, 30, 1207, 669);
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
		
		JScrollPane scrollPaneProcessModel = new JScrollPane();
		processModelPanel.add(scrollPaneProcessModel, BorderLayout.CENTER);
		processActivitiesTable = new JTable();
		processActivitiesTable.setFillsViewportHeight(true);
		processActivitiesTable.setModel(new ActivitiesTableModel());
		scrollPaneProcessModel.setViewportView(processActivitiesTable);
		
		JPanel rightTopPanel = new JPanel();
		splitPane_1.setRightComponent(rightTopPanel);
		rightTopPanel.setLayout(new BorderLayout(0, 0));
		
		topProgressBar = new JProgressBar();
		rightTopPanel.add(topProgressBar,BorderLayout.NORTH);
		
		JSplitPane splitPanelTopLeft = new JSplitPane();
		splitPanelTopLeft.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		rightTopPanel.add(splitPanelTopLeft, BorderLayout.CENTER);
		
		casesTabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		
		tableCasesAll = new JTable();
		tableCasesAll.setFillsViewportHeight(true);
		tableCasesAll.setModel(new CasesTableModel());
		
		tableCasesAll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCasesAll.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer selected = getSelectedCase(tableCasesAll);
				if (selected != null) {
					setEventsTableContentFromCase(selected);
				}
			}
		});
		
		tableCasesFiltered = new JTable();
		tableCasesFiltered.setFillsViewportHeight(true);
		tableCasesFiltered.setModel(new CasesTableModel());
		
		tableCasesFiltered.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCasesFiltered.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer selected = getSelectedCase(tableCasesFiltered);
				if (selected != null) {
					setEventsTableContentFromCase(selected);
				}
			}
		});
		
		JScrollPane casesScrollPane_all = new JScrollPane(tableCasesAll);
		JScrollPane casesScrollPane_filtered = new JScrollPane(tableCasesFiltered);
		casesTabbedPane.addTab("All Cases", null, casesScrollPane_all, null);
		casesTabbedPane.addTab("Filtered", null, casesScrollPane_filtered, null);
		casesScrollPane_all.setMinimumSize(new Dimension(220, 0));
		casesScrollPane_filtered.setMinimumSize(new Dimension(220, 0));
		
		tableEventsAll = new JTable();
		tableEventsAll.setFillsViewportHeight(true);
		tableEventsAll.setModel(new EventsTableModel());
		
		tableEventsAll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEventsAll.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer selected = getSelectedEvent(tableEventsAll);
				if (selected != null) {
					setEventAttributesTableContent(selected);
				}
			}
		});
		
		tableEventsPerCase = new JTable();
		tableEventsPerCase.setFillsViewportHeight(true);
		tableEventsPerCase.setModel(new EventsTableModel());
		
		tableEventsPerCase.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEventsPerCase.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer selected = getSelectedEvent(tableEventsPerCase);
				if (selected != null) {
					setEventAttributesTableContent(selected);
				}
			}
		});
		
		tableEventsFiltered = new JTable();
		tableEventsFiltered.setFillsViewportHeight(true);
		tableEventsFiltered.setModel(new EventsTableModel());
		
		tableEventsFiltered.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEventsFiltered.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer selected = getSelectedEvent(tableEventsFiltered);
				if (selected != null) {
					setEventAttributesTableContent(selected);
				}
			}
		});
		
		JSplitPane splitPanelTopRight = new JSplitPane();
		splitPanelTopRight.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		
		splitPanelTopLeft.setRightComponent(splitPanelTopRight);
		splitPanelTopLeft.setLeftComponent(casesTabbedPane);
		
		
		JTabbedPane eventsTabbedPane = new JTabbedPane();
		eventsTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		
		JScrollPane scrollPaneEventsAll = new JScrollPane(tableEventsAll);
		scrollPaneEventsAll.setMinimumSize(new Dimension(270, 0));
		eventsTabbedPane.addTab("All Events", null, scrollPaneEventsAll, null);
		
		JScrollPane scrollPaneEventsPerCase = new JScrollPane(tableEventsPerCase);
		scrollPaneEventsPerCase.setMinimumSize(new Dimension(270, 0));
		eventsTabbedPane.addTab("Per Case", null, scrollPaneEventsPerCase, null);
		
		JScrollPane scrollPaneEventsFiltered = new JScrollPane(tableEventsFiltered);
		scrollPaneEventsFiltered.setMinimumSize(new Dimension(270, 0));
		eventsTabbedPane.addTab("Filtered", null, scrollPaneEventsFiltered, null);
		
		splitPanelTopRight.setLeftComponent(eventsTabbedPane);
		
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
		
		NodeSelectionHandler classSelectionHandler = new NodeSelectionHandler() {
			
			@Override
			public void run(SLEXMMClass c) {
				setObjectsTableContentPerClass(c.getId());
			}
		};
		
		datamodelPanel = new DiagramComponent(classSelectionHandler);
		datamodelPanel.setMinimumSize(new Dimension(300,200));
		leftBottomPanel.add(datamodelPanel, BorderLayout.CENTER);
		
		
		tableObjectsAll = new JTable();
		tableObjectsAll.setFillsViewportHeight(true);
		tableObjectsAll.setModel(new ObjectsTableModel());
		
		tableObjectsAll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableObjectsAll.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer[] selected = getSelectedObject(tableObjectsAll);
				if (selected != null) {
					setObjectVersionsTableContent(selected[0]);
					setObjectRelationsTableContent(selected[0]);
				}
			}
		});
		
		tableObjectsPerClass = new JTable();
		tableObjectsPerClass.setFillsViewportHeight(true);
		tableObjectsPerClass.setModel(new ObjectsTableModel());
		
		tableObjectsPerClass.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableObjectsPerClass.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer[] selected = getSelectedObject(tableObjectsPerClass);
				if (selected != null) {
					setObjectVersionsTableContent(selected[0]);
					setObjectRelationsTableContent(selected[0]);
				}
			}
		});
		
		tableObjectsFiltered = new JTable();
		tableObjectsFiltered.setFillsViewportHeight(true);
		tableObjectsFiltered.setModel(new ObjectsTableModel());
		
		tableObjectsFiltered.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableObjectsFiltered.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer[] selected = getSelectedObject(tableObjectsFiltered);
				if (selected != null) {
					setObjectVersionsTableContent(selected[0]);
					setObjectRelationsTableContent(selected[0]);
				}
			}
		});
		
		JTabbedPane objectsTabbedPane = new JTabbedPane();
		objectsTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		JScrollPane scrollPaneTableObjectsAll = new JScrollPane(tableObjectsAll);
		objectsTabbedPane.addTab("All Objects", null, scrollPaneTableObjectsAll, null);
		JScrollPane scrollPaneTableObjectsPerClass = new JScrollPane(tableObjectsPerClass);
		objectsTabbedPane.addTab("Per Class", null, scrollPaneTableObjectsPerClass, null);
		JScrollPane scrollPaneTableObjectsFiltered = new JScrollPane(tableObjectsFiltered);
		objectsTabbedPane.addTab("Filtered", null, scrollPaneTableObjectsFiltered, null);
		
		JPanel rightBottomPanel = new JPanel();
		JSplitPane splitPane_22 = new JSplitPane();
		splitPane_2.setRightComponent(splitPane_22);
		splitPane_22.setLeftComponent(objectsTabbedPane);
		splitPane_22.setRightComponent(rightBottomPanel);
		rightBottomPanel.setLayout(new BoxLayout(rightBottomPanel, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		rightBottomPanel.add(tabbedPane);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setResizeWeight(0.5);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		tabbedPane.addTab("Per Object", null, splitPane_3, null);
		
		JPanel topObjectVersionsPanel = new JPanel();
		topObjectVersionsPanel.setLayout(new BorderLayout(0, 0));
		
		tableObjectVersionAttributes = new JTable();
		tableObjectVersionAttributes.setFillsViewportHeight(true);
		tableObjectVersionAttributes.setModel(new ObjectVersionAttributesTableModel());
		
		JScrollPane objectVersionDetailsPanel = new JScrollPane(tableObjectVersionAttributes);
		
		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane_3.setTopComponent(splitPane_4);
		splitPane_4.setLeftComponent(topObjectVersionsPanel);
		splitPane_4.setRightComponent(objectVersionDetailsPanel);
		
		tableObjectVersions = new JTable();
		tableObjectVersions.setFillsViewportHeight(true);
		tableObjectVersions.setModel(new ObjectVersionsTableModel());
		tableObjectVersions.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer selected = getSelectedEvent(tableObjectVersions);
				if (selected != null) {
					setObjectVersionAttributesTableContent(selected);
				}
			}
		});
		
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
		
		setActivitiesTableContent();
		setCasesTableContent(tableCasesAll);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		final JTextField textQueryField = new JTextField();
		/**/
		final String COMMIT_ACTION = "commit";

		// Without this, cursor always leaves text field
		textQueryField.setFocusTraversalKeysEnabled(false);
		
		// Our words to complete
		ArrayList<String>keywords = new ArrayList<String>(5);
		        keywords.add("allEvents");
		        keywords.add("allObjects");
		        keywords.add("where");
		        keywords.add("id");
		Autocomplete autoComplete = new Autocomplete(textQueryField, keywords);
		textQueryField.getDocument().addDocumentListener(autoComplete);

		// Maps the tab key to the commit action, which finishes the autocomplete
		// when given a suggestion
		textQueryField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
		textQueryField.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());
		/**/
		scrollPane.setViewportView(textQueryField);
		
		JButton btnExecuteQuery = new JButton("Execute Query");
		btnExecuteQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread queryPOQLThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						String query = textQueryField.getText();
						POQLRunner runner = new POQLRunner();
						QueryResult qr = runner.executeQuery(FrameMetaModelInspect.this.mmstrg, query);
						
						if (qr.type == SLEXMMObject.class) {
							setObjectsTableContentFiltered(qr.result);
						} else if (qr.type == SLEXMMObjectVersion.class) {
							setObjectVersionsTableContent(qr.result); //FIXME
						} else if (qr.type == SLEXMMEvent.class) {
							setEventsTableContentFiltered(qr.result);
						} else {
							System.err.println("Unknown type of result");
						}
					}
				});
				queryPOQLThread.start();
			}
		});
		panel.add(btnExecuteQuery, BorderLayout.EAST);
		setObjectsTableContentAll();
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
	
	private Integer[] getSelectedObject(JTable table) {
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
	
	private Integer getSelectedCase(JTable table) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			return (int) table.getModel().getValueAt(selectedRow, 0);
		} else {
			return null;
		}
	}
	
	private Integer getSelectedEvent(JTable table) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			return (int) table.getModel().getValueAt(selectedRow, 0);
		} else {
			return null;
		}
	}
	
	private class ActivitiesTableModel extends DefaultTableModel {
		
		Class[] columnTypes = new Class[] {	Integer.class, String.class, Integer.class };
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
	
	public void setActivitiesTableContent() {
		ActivitiesTableModel model = (ActivitiesTableModel) processActivitiesTable.getModel();
		processActivitiesTable.getColumnModel().getColumn(0).setMinWidth(75);
		processActivitiesTable.getColumnModel().getColumn(1).setMinWidth(75);
		processActivitiesTable.getColumnModel().getColumn(2).setMinWidth(75);
		
		List<SLEXMMActivity> activitiesList = mmstrg.getActivities();
		
		for (SLEXMMActivity act: activitiesList) {
			model.addRow(new Object[] {act.getId(),act.getName(),act.getProcessId()});
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
	
	public void setObjectsTableContentAll() {
		ObjectsTableModel model = (ObjectsTableModel) tableObjectsAll.getModel();
		tableObjectsAll.getColumnModel().getColumn(0).setMinWidth(75);
		tableObjectsAll.getColumnModel().getColumn(1).setMinWidth(75);
		
		SLEXMMObjectResultSet orset = mmstrg.getObjects();
		SLEXMMObject obj = null;
		
		while ((obj = orset.getNext()) != null) {
			model.addRow(new Object[] {obj.getId(),obj.getClassId()});
		}
		
	}
	
	public void setObjectsTableContentPerClass(int classId) {
		ObjectsTableModel model = new ObjectsTableModel();
		tableObjectsPerClass.setModel(model);
		tableObjectsPerClass.getColumnModel().getColumn(0).setMinWidth(75);
		tableObjectsPerClass.getColumnModel().getColumn(1).setMinWidth(75);
		
		SLEXMMObjectResultSet orset = mmstrg.getObjectsPerClass(classId);
		SLEXMMObject obj = null;
		
		while ((obj = orset.getNext()) != null) {
			model.addRow(new Object[] {obj.getId(),obj.getClassId()});
		}
		
	}
	
	public void setObjectsTableContentFiltered(SLEXMMObjectResultSet orset) {
		ObjectsTableModel model = (ObjectsTableModel) tableObjectsFiltered.getModel();
		tableObjectsFiltered.getColumnModel().getColumn(0).setMinWidth(75);
		tableObjectsFiltered.getColumnModel().getColumn(1).setMinWidth(75);
		
		SLEXMMObject obj = null;
		
		while ((obj = orset.getNext()) != null) {
			model.addRow(new Object[] {obj.getId(),obj.getClassId()});
		}
		
	}
	
	public void setObjectsTableContentFiltered(List<Object> list) {
		ObjectsTableModel model = new ObjectsTableModel();
		tableObjectsFiltered.setModel(model);
		tableObjectsFiltered.getColumnModel().getColumn(0).setMinWidth(75);
		tableObjectsFiltered.getColumnModel().getColumn(1).setMinWidth(75);
		
		SLEXMMObject obj = null;
		for (Object o: list) {
			obj = (SLEXMMObject) o;
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
			super(new String[] { "Version Id", "Object Id",
					"Start Timestamp", "End Timestamp"}, 0);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Long.class);
			columnTypes.add(Long.class);
		}
		
	}
	
	public void setObjectVersionsTableContent(int objectId) {
		ObjectVersionsTableModel model = new ObjectVersionsTableModel();
		
		tableObjectVersions.setModel(model);
		
		SLEXMMObjectVersionResultSet orset = mmstrg.getObjectVersionsForObjectOrdered(objectId);
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
	
	public void setObjectVersionsTableContent(List<Object> list) {
		ObjectVersionsTableModel model = new ObjectVersionsTableModel();
		
		tableObjectVersions.setModel(model);
		
		for (Object o: list) {
			SLEXMMObjectVersion objv = (SLEXMMObjectVersion) o;
			
			Object[] row = new Object[model.getColumnCount()];
			
			row[0] = Integer.valueOf(objv.getId());
			row[1] = Integer.valueOf(objv.getObjectId());
			row[2] = Long.valueOf(objv.getStartTimestamp());
			row[3] = Long.valueOf(objv.getEndTimestamp());
			
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
					"Source Object Version Id", "Target Object Version Id",
					"Start Timestamp", "End Timestamp"}, 0);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Integer.class);
			columnTypes.add(Long.class);
			columnTypes.add(Long.class);
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
			row[2] = Integer.valueOf(rel.getSourceObjectVersionId());
			row[3] = Integer.valueOf(rel.getTargetObjectVersionId());
			row[4] = Long.valueOf(rel.getStartTimestamp());
			row[5] = Long.valueOf(rel.getEndTimestamp());
			
			model.addRow(row);
		}
		
		orrset = mmstrg.getRelationsForTargetObjectOrdered(objectId);
		rel = null;
		
		while ((rel = orrset.getNext()) != null) {
			
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
	
	public void setCasesTableContent(JTable table) {
		CasesTableModel model = (CasesTableModel) table.getModel();
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(75);
		
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
					
					tableEventsAll.setModel(model);
					
					tableEventsAll.getColumnModel().getColumn(0).setMinWidth(75);
					tableEventsAll.getColumnModel().getColumn(1).setMinWidth(75);
				
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
					casesTabbedPane.setEnabled(false);
					
					EventsTableModel model = new EventsTableModel();
					
					tableEventsPerCase.setModel(model);
					
					tableEventsPerCase.getColumnModel().getColumn(0).setMinWidth(75);
					tableEventsPerCase.getColumnModel().getColumn(1).setMinWidth(75);
				
					SLEXMMEventResultSet orset = mmstrg.getEventsForCaseOrdered(caseId);
					SLEXMMEvent ev = null;
				
					while ((ev = orset.getNext()) != null) {
						model.addRow(new Object[] {ev.getId(), ev.getOrder()});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				topProgressBar.setIndeterminate(false);
				casesTabbedPane.setEnabled(true);
			}
		});
		
		thread.start();
		
	}
	
	public void setEventsTableContentFiltered(final SLEXMMEventResultSet erset) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					topProgressBar.setIndeterminate(true);
					casesTabbedPane.setEnabled(false);
					
					EventsTableModel model = new EventsTableModel();
					
					tableEventsFiltered.setModel(model);
					
					tableEventsFiltered.getColumnModel().getColumn(0).setMinWidth(75);
					tableEventsFiltered.getColumnModel().getColumn(1).setMinWidth(75);
				
					SLEXMMEvent ev = null;
				
					while ((ev = erset.getNext()) != null) {
						model.addRow(new Object[] {ev.getId(), ev.getOrder()});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				topProgressBar.setIndeterminate(false);
				casesTabbedPane.setEnabled(true);
			}
		});
		
		thread.start();
		
	}
	
	public void setEventsTableContentFiltered(final List<Object> list) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					topProgressBar.setIndeterminate(true);
					casesTabbedPane.setEnabled(false);
					
					EventsTableModel model = new EventsTableModel();
					
					tableEventsFiltered.setModel(model);
					
					tableEventsFiltered.getColumnModel().getColumn(0).setMinWidth(75);
					tableEventsFiltered.getColumnModel().getColumn(1).setMinWidth(75);
				
					for (Object o: list) {
						SLEXMMEvent ev = (SLEXMMEvent) o;
						model.addRow(new Object[] {ev.getId(), ev.getOrder()});
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				topProgressBar.setIndeterminate(false);
				casesTabbedPane.setEnabled(true);
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
		
		SLEXMMEvent e = mmstrg.getEventForId(evId);
		model.addRow(new Object[] {"Event Lifecycle", e.getLifecycle(), "STRING"});
		model.addRow(new Object[] {"Event Resource", e.getResource(), "STRING"});
		model.addRow(new Object[] {"Event Timestamp", e.getTimestamp(), "LONG"});
		
	}
	
	private class ObjectVersionAttributesTableModel extends DefaultTableModel {
		
		Class[] columnTypes = new Class[] {	String.class, String.class, String.class };
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
	
	public void setObjectVersionAttributesTableContent(int ovId) {
		
		ObjectVersionAttributesTableModel model = new ObjectVersionAttributesTableModel();
		
		tableObjectVersionAttributes.setModel(model);
		
		tableObjectVersionAttributes.getColumnModel().getColumn(0).setMinWidth(75);
		tableObjectVersionAttributes.getColumnModel().getColumn(1).setMinWidth(75);
		tableObjectVersionAttributes.getColumnModel().getColumn(1).setMinWidth(75);
		
		HashMap<SLEXMMAttribute, SLEXMMAttributeValue> attrs = mmstrg.getAttributeValuesForObjectVersion(ovId);
		
		for (SLEXMMAttribute at: attrs.keySet()) {
			SLEXMMAttributeValue attV = attrs.get(at);
			model.addRow(new Object[] {at.getName(),attV.getValue(),attV.getType()});
		}
		
	}
}