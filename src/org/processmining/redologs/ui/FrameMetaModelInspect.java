package org.processmining.redologs.ui;

import org.processmining.openslex.metamodel.SLEXMMActivityInstanceResultSet;
import org.processmining.openslex.metamodel.SLEXMMAttribute;
import org.processmining.openslex.metamodel.SLEXMMAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMCaseResultSet;
import org.processmining.openslex.metamodel.SLEXMMClass;
import org.processmining.openslex.metamodel.SLEXMMDataModel;
import org.processmining.openslex.metamodel.SLEXMMDataModelResultSet;
import org.processmining.openslex.metamodel.SLEXMMEvent;
import org.processmining.openslex.metamodel.SLEXMMEventResultSet;
import org.processmining.openslex.metamodel.SLEXMMObjectResultSet;
import org.processmining.openslex.metamodel.SLEXMMObjectVersionResultSet;
import org.processmining.openslex.metamodel.SLEXMMRelationResultSet;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.ui.components.AskYesNoDialog;
import org.processmining.redologs.ui.components.CustomInternalFrame;
import org.processmining.redologs.ui.components.DiagramComponent;
import org.processmining.redologs.ui.components.NodeSelectionHandler;
import org.processmining.redologs.ui.components.metamodel.MetaModelTableUtils;
import org.processmining.redologs.ui.components.metamodel.POQLQueryPanel;
import org.processmining.redologs.ui.components.metamodel.SQLQueryPanel;

import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class FrameMetaModelInspect extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3775031378258022448L;

	private SLEXMMStorageMetaModel mmstrg;

	private JTable tableObjectsAll;
	private JTable tableObjectsPerClass;

	private JTable tableObjectVersions;
	private JTable tableObjectRelations;

	private JTable tableCasesAll;

	private JTable tableActivityInstancesAll;
	private JTable tableActivityInstancesPerCase;
	
	private JTable tableEventsAll;
	private JTable tableEventsPerCase;
	private JTable tableEventsPerActivityInstance;

	private JTable tableEventAttributes;

	private JTable tableObjectVersionAttributes;

	private JProgressBar topProgressBar;
	private DiagramComponent datamodelPanel;
	private JPanel processModelPanel;
	private JTable processActivitiesTable;
	
	private JButton refreshButton;
	
	private int sqlTabCounter = 0;
	private int poqlTabCounter = 0;

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
		
		tabbedPane_1.addTab("Inspector", null, createInspectorPanel(), null);

		tabbedPane_1.addTab("POQL", null, createPOQLPanel(), null);
		
		tabbedPane_1.addTab("SQL", null, createSQLPanel(), null);

	}
	
	private void fillWithData() {
		try {
			SLEXMMCaseResultSet crset = getMetaModel().getCases();
			MetaModelTableUtils.setCasesTableContent(tableCasesAll,crset);
				
			MetaModelTableUtils.setActivitiesTableContent(processActivitiesTable,getMetaModel().getActivities());
				
			SLEXMMObjectResultSet orset = getMetaModel().getObjects();
			MetaModelTableUtils.setObjectsTableContent(tableObjectsAll,orset);
				
			SLEXMMEventResultSet erset = getMetaModel().getEvents();
			MetaModelTableUtils.setEventsTableContent(tableEventsAll,erset,topProgressBar);
				
			SLEXMMActivityInstanceResultSet airset = getMetaModel().getActivityInstances();
			MetaModelTableUtils.setActivityInstancesTableContent(tableActivityInstancesAll,airset);
					
			datamodelPanel.setDataModel(getDataModel());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JPanel createInspectorPanel() {
		
		JPanel inspectorPanel = new JPanel();
		
		JSplitPane inspectorSplitPane = new JSplitPane();
		
		inspectorPanel.setLayout(new BorderLayout());
		inspectorPanel.add(inspectorSplitPane, BorderLayout.CENTER);
		
		inspectorSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		JSplitPane splitPane_1 = new JSplitPane();
		inspectorSplitPane.setLeftComponent(splitPane_1);

		JPanel leftTopPanel = new JPanel(new BorderLayout(0, 0));
		splitPane_1.setLeftComponent(leftTopPanel);

		processModelPanel = new JPanel(new BorderLayout(0, 0));
		processModelPanel.setMinimumSize(new Dimension(300, 200));
		leftTopPanel.add(processModelPanel, BorderLayout.CENTER);

		JScrollPane scrollPaneProcessModel = new JScrollPane();
		processModelPanel.add(scrollPaneProcessModel, BorderLayout.CENTER);
		processActivitiesTable = new JTable();
		processActivitiesTable.setFillsViewportHeight(true);
		processActivitiesTable.setModel(new MetaModelTableUtils.ActivitiesTableModel());
		scrollPaneProcessModel.setViewportView(processActivitiesTable);

		JPanel rightTopPanel = new JPanel();
		splitPane_1.setRightComponent(rightTopPanel);
		rightTopPanel.setLayout(new BorderLayout(0, 0));

		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						refreshButton.setEnabled(false);
						fillWithData();
						refreshButton.setEnabled(true);
					}
				}).start();
			}
		});
		topProgressBar = new JProgressBar();
		JPanel progressAndRefreshPanel = new JPanel(new BorderLayout());
		progressAndRefreshPanel.add(topProgressBar, BorderLayout.CENTER);
		progressAndRefreshPanel.add(refreshButton, BorderLayout.EAST);
		rightTopPanel.add(progressAndRefreshPanel, BorderLayout.NORTH);

		JSplitPane splitPanelTopLeft = new JSplitPane();
		splitPanelTopLeft.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		rightTopPanel.add(splitPanelTopLeft, BorderLayout.CENTER);

		tableCasesAll = new JTable();
		tableCasesAll.setFillsViewportHeight(true);
		tableCasesAll.setModel(new MetaModelTableUtils.CasesTableModel());

		tableCasesAll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCasesAll.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Integer selected = MetaModelTableUtils.getSelectedCase(tableCasesAll);
								if (selected != null) {
									SLEXMMEventResultSet erset = getMetaModel().getEventsForCase(selected);
									SLEXMMActivityInstanceResultSet airset = getMetaModel().getActivityInstancesForCase(selected);
									try {
										MetaModelTableUtils.setEventsTableContent(tableEventsPerCase,erset,topProgressBar);
										MetaModelTableUtils.setActivityInstancesTableContent(tableActivityInstancesPerCase, airset);
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}).start();
					}
				});
		
		JScrollPane casesScrollPane_all = new JScrollPane(tableCasesAll);
		casesScrollPane_all.setMinimumSize(new Dimension(220, 0));

		tableEventsAll = new JTable();
		tableEventsAll.setFillsViewportHeight(true);
		tableEventsAll.setModel(new MetaModelTableUtils.EventsTableModel());

		tableEventsAll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEventsAll.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Integer selected = MetaModelTableUtils.getSelectedEvent(tableEventsAll);
								if (selected != null) {
									SLEXMMEvent ev = getMetaModel().getEventForId(selected);
									try {
										MetaModelTableUtils.setEventAttributesTableContent(tableEventAttributes,
												ev.getAttributeValues(),ev.getLifecycle(),ev.getResource(),
												String.valueOf(ev.getTimestamp()));
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}).start();
								
					}
				});

		tableEventsPerCase = new JTable();
		tableEventsPerCase.setFillsViewportHeight(true);
		tableEventsPerCase.setModel(new MetaModelTableUtils.EventsTableModel());

		tableEventsPerCase
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEventsPerCase.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Integer selected = MetaModelTableUtils.getSelectedEvent(tableEventsPerCase);
								if (selected != null) {
									SLEXMMEvent ev = getMetaModel().getEventForId(selected);
									try {
										MetaModelTableUtils.setEventAttributesTableContent(tableEventAttributes,
												ev.getAttributeValues(),ev.getLifecycle(),ev.getResource(),
												String.valueOf(ev.getTimestamp()));
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}).start();
					}
				});
		
		tableEventsPerActivityInstance = new JTable();
		tableEventsPerActivityInstance.setFillsViewportHeight(true);
		tableEventsPerActivityInstance.setModel(new MetaModelTableUtils.EventsTableModel());

		tableEventsPerActivityInstance
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEventsPerActivityInstance.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Integer selected = MetaModelTableUtils.getSelectedEvent(tableEventsPerActivityInstance);
								if (selected != null) {
									SLEXMMEvent ev = getMetaModel().getEventForId(selected);
									try {
										MetaModelTableUtils.setEventAttributesTableContent(tableEventAttributes,
												ev.getAttributeValues(),ev.getLifecycle(),ev.getResource(),
												String.valueOf(ev.getTimestamp()));
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}).start();
						
					}
				});

		tableActivityInstancesAll = new JTable();
		tableActivityInstancesAll.setFillsViewportHeight(true);
		tableActivityInstancesAll.setModel(new MetaModelTableUtils.ActivityInstanceTableModel());

		tableActivityInstancesAll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableActivityInstancesAll.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Integer selected = MetaModelTableUtils.getSelectedActivityInstance(tableActivityInstancesAll);
								if (selected != null) {
									try {
										MetaModelTableUtils.setEventsTableContent(
												tableEventsPerActivityInstance,
												getMetaModel().getEventsForActivityInstance(selected),
												topProgressBar);
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}).start();
					}
				});

		tableActivityInstancesPerCase = new JTable();
		tableActivityInstancesPerCase.setFillsViewportHeight(true);
		tableActivityInstancesPerCase.setModel(new MetaModelTableUtils.ActivityInstanceTableModel());

		tableActivityInstancesPerCase
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableActivityInstancesPerCase.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Integer selected = MetaModelTableUtils.getSelectedActivityInstance(tableActivityInstancesPerCase);
								if (selected != null) {
									try {
										MetaModelTableUtils.setEventsTableContent(
												tableEventsPerActivityInstance,
												getMetaModel().getEventsForActivityInstance(selected),
												topProgressBar);
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}).start();
					}
				});
		
		JTabbedPane activityInstancesTabbedPane = new JTabbedPane();
		activityInstancesTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		
		JScrollPane scrollPaneActivityInstancesAll = new JScrollPane(tableActivityInstancesAll);
		scrollPaneActivityInstancesAll.setMinimumSize(new Dimension(180, 0));
		
		JScrollPane scrollPaneActivityInstancesPerCase = new JScrollPane(tableActivityInstancesPerCase);
		scrollPaneActivityInstancesPerCase.setMinimumSize(new Dimension(180, 0));
		
		activityInstancesTabbedPane.addTab("All Activity Instances", scrollPaneActivityInstancesAll);
		activityInstancesTabbedPane.addTab("Per Case", scrollPaneActivityInstancesPerCase);
		
		JSplitPane splitPanelTopRight = new JSplitPane();
		splitPanelTopRight.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

		JSplitPane splitPanelCasesActivityInstances = new JSplitPane();
		splitPanelCasesActivityInstances.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		
		splitPanelCasesActivityInstances.setLeftComponent(casesScrollPane_all);
		splitPanelCasesActivityInstances.setRightComponent(activityInstancesTabbedPane);
		
		splitPanelTopLeft.setRightComponent(splitPanelTopRight);
		splitPanelTopLeft.setLeftComponent(splitPanelCasesActivityInstances);

		JTabbedPane eventsTabbedPane = new JTabbedPane();
		eventsTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);

		JScrollPane scrollPaneEventsAll = new JScrollPane(tableEventsAll);
		scrollPaneEventsAll.setMinimumSize(new Dimension(180, 0));
		eventsTabbedPane.addTab("All Events", null, scrollPaneEventsAll, null);

		JScrollPane scrollPaneEventsPerActivityInstance = new JScrollPane(
				tableEventsPerActivityInstance);
		scrollPaneEventsPerActivityInstance.setMinimumSize(new Dimension(180, 0));
		eventsTabbedPane
				.addTab("Per Activity Instance", null, scrollPaneEventsPerActivityInstance, null);

		
		JScrollPane scrollPaneEventsPerCase = new JScrollPane(
				tableEventsPerCase);
		scrollPaneEventsPerCase.setMinimumSize(new Dimension(180, 0));
		eventsTabbedPane
				.addTab("Per Case", null, scrollPaneEventsPerCase, null);

		splitPanelTopRight.setLeftComponent(eventsTabbedPane);

		tableEventAttributes = new JTable();
		tableEventAttributes.setFillsViewportHeight(true);
		tableEventAttributes.setModel(new MetaModelTableUtils.EventAttributesTableModel());

		JScrollPane scrollPane_right = new JScrollPane(tableEventAttributes);
		// tableEventAttributes.setMinimumSize(new Dimension(300, 0));
		splitPanelTopRight.setRightComponent(scrollPane_right);

		JSplitPane splitPane_2 = new JSplitPane();
		inspectorSplitPane.setRightComponent(splitPane_2);

		JPanel leftBottomPanel = new JPanel();
		leftBottomPanel.setMinimumSize(new Dimension(180, 0));
		splitPane_2.setLeftComponent(leftBottomPanel);
		leftBottomPanel.setLayout(new BorderLayout(0, 0));

		NodeSelectionHandler classSelectionHandler = new NodeSelectionHandler() {

			@Override
			public void run(final SLEXMMClass c) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						SLEXMMObjectResultSet orset = getMetaModel().getObjectsForClass(c.getId());
						try {
							MetaModelTableUtils.setObjectsTableContent(tableObjectsPerClass, orset);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}).start();
			}
		};
		
		datamodelPanel = new DiagramComponent(classSelectionHandler);
		datamodelPanel.setMinimumSize(new Dimension(300, 200));
		leftBottomPanel.add(datamodelPanel, BorderLayout.CENTER);

		tableObjectsAll = new JTable();
		tableObjectsAll.setFillsViewportHeight(true);
		tableObjectsAll.setModel(new MetaModelTableUtils.ObjectsTableModel());

		tableObjectsAll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableObjectsAll.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Integer[] selected = MetaModelTableUtils.getSelectedObject(tableObjectsAll);
								if (selected != null) {
									try {
										SLEXMMObjectVersionResultSet ovrset = getMetaModel().
											getObjectVersionsForObject(selected[0]);
										MetaModelTableUtils.setObjectVersionsTableContent(tableObjectVersions,ovrset);
										SLEXMMRelationResultSet[] rrset = new SLEXMMRelationResultSet[2];
										rrset[0] = getMetaModel().getRelationsForSourceObject(selected[0]);
										rrset[1] = getMetaModel().getRelationsForTargetObject(selected[0]);
										MetaModelTableUtils.setObjectRelationsTableContent(tableObjectRelations,rrset);
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}).start();
						
					}
				});

		tableObjectsPerClass = new JTable();
		tableObjectsPerClass.setFillsViewportHeight(true);
		tableObjectsPerClass.setModel(new MetaModelTableUtils.ObjectsTableModel());

		tableObjectsPerClass
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableObjectsPerClass.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Integer[] selected = MetaModelTableUtils.getSelectedObject(tableObjectsPerClass);
								if (selected != null) {
									try {
										SLEXMMObjectVersionResultSet ovrset = getMetaModel().
											getObjectVersionsForObject(selected[0]);
										MetaModelTableUtils.setObjectVersionsTableContent(tableObjectVersions,ovrset);
										SLEXMMRelationResultSet[] rrset = new SLEXMMRelationResultSet[2];
										rrset[0] = getMetaModel().getRelationsForSourceObject(selected[0]);
										rrset[1] = getMetaModel().getRelationsForTargetObject(selected[0]);
										MetaModelTableUtils.setObjectRelationsTableContent(tableObjectRelations,rrset);
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}).start();
					}
				});

		JTabbedPane objectsTabbedPane = new JTabbedPane();
		objectsTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		JScrollPane scrollPaneTableObjectsAll = new JScrollPane(tableObjectsAll);
		scrollPaneTableObjectsAll.setMinimumSize(new Dimension(180, 0));
		
		objectsTabbedPane.addTab("All Objects", null,
				scrollPaneTableObjectsAll, null);
		JScrollPane scrollPaneTableObjectsPerClass = new JScrollPane(
				tableObjectsPerClass);
		scrollPaneTableObjectsPerClass.setMinimumSize(new Dimension(180, 0));
		objectsTabbedPane.addTab("Per Class", null,
				scrollPaneTableObjectsPerClass, null);

		JPanel rightBottomPanel = new JPanel();
		JSplitPane splitPane_22 = new JSplitPane();
		splitPane_2.setRightComponent(splitPane_22);
		splitPane_22.setLeftComponent(objectsTabbedPane);
		splitPane_22.setRightComponent(rightBottomPanel);
		rightBottomPanel.setLayout(new BoxLayout(rightBottomPanel,
				BoxLayout.X_AXIS));

		

		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setResizeWeight(0.5);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		rightBottomPanel.add(splitPane_3);

		JPanel topObjectVersionsPanel = new JPanel();
		topObjectVersionsPanel.setLayout(new BorderLayout(0, 0));

		tableObjectVersionAttributes = new JTable();
		tableObjectVersionAttributes.setFillsViewportHeight(true);
		tableObjectVersionAttributes
				.setModel(new MetaModelTableUtils.ObjectVersionAttributesTableModel());

		JScrollPane objectVersionDetailsPanel = new JScrollPane(
				tableObjectVersionAttributes);

		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane_3.setTopComponent(splitPane_4);
		splitPane_4.setLeftComponent(topObjectVersionsPanel);
		splitPane_4.setRightComponent(objectVersionDetailsPanel);

		tableObjectVersions = new JTable();
		tableObjectVersions.setFillsViewportHeight(true);
		tableObjectVersions.setModel(new MetaModelTableUtils.ObjectVersionsTableModel());
		tableObjectVersions.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Integer selected = MetaModelTableUtils.getSelectedEvent(tableObjectVersions);
								if (selected != null) {
									HashMap<SLEXMMAttribute, SLEXMMAttributeValue> atts =
											getMetaModel().getAttributeValuesForObjectVersion(selected);
									try {
										MetaModelTableUtils.setObjectVersionAttributesTableContent(tableObjectVersionAttributes,atts);
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}).start();
						
					}
				});

		JScrollPane scrollPaneTableObjectVersions = new JScrollPane(
				tableObjectVersions);
		scrollPaneTableObjectVersions.setMinimumSize(new Dimension(360, 0));
		topObjectVersionsPanel.add(scrollPaneTableObjectVersions);

		JPanel bottomObjectVersionsPanel = new JPanel();
		splitPane_3.setBottomComponent(bottomObjectVersionsPanel);
		bottomObjectVersionsPanel.setLayout(new BorderLayout(0, 0));

		tableObjectRelations = new JTable();
		tableObjectRelations.setFillsViewportHeight(true);
		tableObjectRelations.setModel(new MetaModelTableUtils.ObjectRelationsTableModel());

		JScrollPane scrollPaneTableObjectRelations = new JScrollPane(
				tableObjectRelations);
		bottomObjectVersionsPanel.add(scrollPaneTableObjectRelations);
		
		return inspectorPanel;
	}
	
	private JPanel createSQLPanel() {
		/* START SQL Tab */
		JPanel sqlTab = new JPanel();
		
		sqlTab.setLayout(new BorderLayout(0, 0));

		JSplitPane sqlSplitPane = new JSplitPane();
		sqlSplitPane.setResizeWeight(0.5);
		sqlTab.add(sqlSplitPane);

		final JTabbedPane sqlQueryTabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		sqlSplitPane.setRightComponent(sqlQueryTabbedPane);

		SQLQueryPanel sqlQueryRightPanel = new SQLQueryPanel(getMetaModel(),incSQLQueryTabCounter());
		
		sqlQueryTabbedPane.addTab("Query "+sqlQueryRightPanel.getId(), sqlQueryRightPanel);
		
		sqlQueryTabbedPane.addTab("+", new JLabel());
		sqlQueryTabbedPane.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (sqlQueryTabbedPane.getSelectedComponent() instanceof JLabel) {
					if (!e.isControlDown()) {
						int count = sqlQueryTabbedPane.getTabCount();
						SQLQueryPanel newTab = new SQLQueryPanel(getMetaModel(),incSQLQueryTabCounter());
						sqlQueryTabbedPane.add(newTab, count - 1);
						sqlQueryTabbedPane.setTitleAt(count - 1, "Query "
								+ newTab.getId());
						sqlQueryTabbedPane.setSelectedComponent(newTab);
					}
	            } else if (e.isControlDown()) {
	            	int selected = sqlQueryTabbedPane.getSelectedIndex();
	            	SQLQueryPanel sqlquerypanel = (SQLQueryPanel) sqlQueryTabbedPane.getComponentAt(selected);
	            	if (sqlquerypanel.isQueryRunning()) {
	            		AskYesNoDialog dialog = new AskYesNoDialog(sqlquerypanel, "A query is running in this Tab. Do you want to kill it?");
	            		if (dialog.showDialog()) {
	            			sqlquerypanel.killQuery();
	            			sqlQueryTabbedPane.remove(selected);
	            		}
	            	} else {
	            		sqlQueryTabbedPane.remove(selected);
	            	}
	            	
	            }
	        }
	        
	    });
		
		JTabbedPane diagramsTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		sqlSplitPane.setLeftComponent(diagramsTabbedPane);
		
		SLEXMMDataModel dm = getDataModel();
		DataModel mm = getMetaModelDataModel();
		
		DiagramComponent dmDiagram = new DiagramComponent(null);
		DiagramComponent mmDiagram = new DiagramComponent(null);
		dmDiagram.setDataModel(dm);
		mmDiagram.setDataModel(mm);
		
		diagramsTabbedPane.addTab("MetaModel", mmDiagram);
		diagramsTabbedPane.addTab("DataModel", dmDiagram);
		
		/* END SQL Tab */
		return sqlTab;
	}
	
	private JPanel createPOQLPanel() {
		/* START POQL Tab */
		JPanel poqlTab = new JPanel();
		
		poqlTab.setLayout(new BorderLayout(0, 0));

		JSplitPane poqlSplitPane = new JSplitPane();
		poqlSplitPane.setResizeWeight(0.5);
		poqlTab.add(poqlSplitPane);

		final JTabbedPane poqlQueryTabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		poqlSplitPane.setRightComponent(poqlQueryTabbedPane);

		POQLQueryPanel poqlQueryRightPanel = new POQLQueryPanel(getMetaModel(),incPOQLQueryTabCounter());
		
		poqlQueryTabbedPane.addTab("Query "+poqlQueryRightPanel.getId(), poqlQueryRightPanel);
		
		poqlQueryTabbedPane.addTab("+", new JLabel());
		poqlQueryTabbedPane.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (poqlQueryTabbedPane.getSelectedComponent() instanceof JLabel) {
					if (!e.isControlDown()) {
						int count = poqlQueryTabbedPane.getTabCount();
						POQLQueryPanel newTab = new POQLQueryPanel(getMetaModel(),incPOQLQueryTabCounter());
						poqlQueryTabbedPane.add(newTab, count - 1);
						poqlQueryTabbedPane.setTitleAt(count - 1, "Query "
								+ newTab.getId());
						poqlQueryTabbedPane.setSelectedComponent(newTab);
					}
	            } else if (e.isControlDown()) {
	            	int selected = poqlQueryTabbedPane.getSelectedIndex();
	            	POQLQueryPanel poqlquerypanel = (POQLQueryPanel) poqlQueryTabbedPane.getComponentAt(selected);
	            	if (poqlquerypanel.isQueryRunning()) {
	            		AskYesNoDialog dialog = new AskYesNoDialog(poqlquerypanel, "A query is running in this Tab. Do you want to kill it?");
	            		if (dialog.showDialog()) {
	            			poqlquerypanel.killQuery();
	            			poqlQueryTabbedPane.remove(selected);
	            		}
	            	} else {
	            		poqlQueryTabbedPane.remove(selected);
	            	}
	            }
	        }
	        
	    });
		
		JTabbedPane diagramsTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		poqlSplitPane.setLeftComponent(diagramsTabbedPane);
		
		SLEXMMDataModel dm = getDataModel();
		DataModel mm = getMetaModelDataModel();
		
		DiagramComponent dmDiagram = new DiagramComponent(null);
		DiagramComponent mmDiagram = new DiagramComponent(null);
		dmDiagram.setDataModel(dm);
		mmDiagram.setDataModel(mm);
		
		diagramsTabbedPane.addTab("MetaModel", mmDiagram);
		diagramsTabbedPane.addTab("DataModel", dmDiagram);
		/* END POQL Tab */
		
		return poqlTab;
	}
	
	private int incSQLQueryTabCounter() {
		sqlTabCounter++;
		return sqlTabCounter;
	}
	
	private int incPOQLQueryTabCounter() {
		poqlTabCounter++;
		return poqlTabCounter;
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
	
	private SLEXMMDataModel getDataModel() {
		SLEXMMDataModelResultSet dmrset = mmstrg.getDataModels();

		SLEXMMDataModel dm = dmrset.getNext();

		if (dm != null) {
			return dm;
		} else {
			return null;
		}
	}

}