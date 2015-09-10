package org.processmining.redologs.ui;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.processmining.database.exploration.PerspectiveDetector;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXPerspective;
import org.processmining.openslex.SLEXStorage;
import org.processmining.openslex.SLEXStorageCollection;
import org.processmining.openslex.SLEXStorageImpl;
import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.EventAttributeColumn;
import org.processmining.redologs.common.GraphNode;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.LogTraceSplitter;
import org.processmining.redologs.common.ProgressHandler;
import org.processmining.redologs.common.SLEXAttributeMapper;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.common.TraceIDPattern;
import org.processmining.redologs.oracle.OracleLogMinerExtractor;
import org.processmining.redologs.ui.components.AskNameDialog;
import org.processmining.redologs.ui.components.AskYesNoDialog;
import org.processmining.redologs.ui.components.CustomInternalFrame;
import org.processmining.redologs.ui.components.DataModelList;
import org.processmining.redologs.ui.components.DataModelTree;
import org.processmining.redologs.ui.components.HistogramPanel;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JProgressBar;

import java.awt.Component;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableColumnModelListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ListSelectionModel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class FramePerspectivesDetector extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7078418810402851925L;
	
	private DataModel model = null;
	
	private DataModelList listTraceIdFields = new DataModelList();
	private DataModelList listSortField = new DataModelList();
	
	private Object selectedRoot = null;
	private JTextField txtRootelement;
	private JLabel processedEventsLabel;
	private JLabel generatedTracesLabel;
	private JLabel removedTracesLabel;
	private HistogramPanel histogramPanel;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	
	private JLabel lblStartdatevalue = null;
	private JLabel lblEnddatevalue = null;
	private SLEXEventCollection eventCollection;
	private SLEXStorageCollection storage;
	
	DataModelTree tree = new DataModelTree();
	JTree resultTree = new JTree();
	private JTable resultTable;
	
	private void setDataModel(DataModel model) {
		if (model != this.model) {
			listTraceIdFields.clear();
			listSortField.clear();
		}
		this.model = model;
		tree.setDataModel(model);
	}
	
	public FramePerspectivesDetector() {
		super("Log Splitter");
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		setBounds(715, 30, 820, 670);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(tree);
		splitPane.setTopComponent(scrollPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		splitPane.setBottomComponent(tabbedPane);
		
		resultTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Subgraphs") {
				{
				}
			}
		));
		
		JScrollPane scrollPaneResult = new JScrollPane(resultTree);
		tabbedPane.addTab("Subgraphs Tree", null, scrollPaneResult, null);
		
		resultTable = new JTable();
		resultTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
				new String[] { "Subgraph", "Table Name", "In arcs", "Out arcs",
						"# Events", "Start Date", "End Date" }
		));
		resultTable.getColumnModel().getColumn(5).setPreferredWidth(103);
		resultTable.setFillsViewportHeight(true);
		JScrollPane scrollPaneResultTable = new JScrollPane(resultTable);
		
		tabbedPane.addTab("Subgraphs Table", null, scrollPaneResultTable, null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		final JButton btnLoad = new JButton("Load Data Model & Event Collection");
		panel_1.add(btnLoad);
		
		JButton btnSplitLog = new JButton("Compute Subgraphs");
		panel_1.add(btnSplitLog);
		
//		JButton btnVisualizeDataModel = new JButton("Visualize Data Model");
//		btnVisualizeDataModel.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (model != null) {
//					FrameRelationsGraph relations = new FrameRelationsGraph(true,FramePerspectivesDetector.this);
//					RedoLogInspector.getInstance().addFrame(relations);
//					relations.setVisible(true);
//					relations.setFocusable(true);
//					relations.reloadGraph(model);
//				}
//			}
//		});
		
//		panel_1.add(btnVisualizeDataModel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.add(progressBar);
		
		
		btnSplitLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				final List<Column> sortFields = new Vector<>();
				List<Object> orderNamesSelected = listSortField.getElementsList(); 
				for (int i = 0; i < orderNamesSelected.size(); i++) {
					if (orderNamesSelected.get(i) instanceof EventAttributeColumn) {
						sortFields.add(((EventAttributeColumn) orderNamesSelected.get(i)).c);
					} else if (orderNamesSelected.get(i) instanceof Column) {
						sortFields.add((Column) orderNamesSelected.get(i));
					}
				}
				
				//String msg = "<html>Do you want to compute a Matrix<br>instead of the whole log?</html>";
				//AskYesNoDialog askMatrixDiag = new AskYesNoDialog(FramePerspectivesDetector.this,msg);
				//final boolean computeMatrix = askMatrixDiag.showDialog();
//				String outputName = "";
//				File outputFileDfg = null;
//				if (computeMatrix) {
//					// Show dialog to pick location to save Dfg
//					final JFileChooser fc = new JFileChooser();
//					int returnVal = fc.showSaveDialog(FramePerspectivesDetector.this);
//					
//					if (returnVal == JFileChooser.APPROVE_OPTION) {
//						outputFileDfg = fc.getSelectedFile();
//						outputName = outputFileDfg.getName(); 
//					} else {
//						return;
//					}
//				} else {
//					AskNameDialog askDiag = new AskNameDialog(FramePerspectivesDetector.this);
//					outputName = askDiag.showDialog();
//					if (outputName == null) {
//						return;
//					}
//				}
//				final String outputLogName = outputName;
//				final File outputFile = outputFileDfg;
				
				final SLEXEventCollection evCol = eventCollection;
				
				List<TableInfo> tables = new Vector<>();
				tables.addAll(model.getTables());
				tables.add(tree.getCommonTable());
				
				final SLEXAttributeMapper mapper = LogTraceSplitter.computeMapping(evCol, tables);
//				final TraceIDPattern tp = new TraceIDPattern(model);
				
//				if (selectedRoot instanceof EventAttributeColumn) {
//					tp.setRoot(((EventAttributeColumn) selectedRoot).c);
//				} else if (selectedRoot instanceof Column) {
//					tp.setRoot((Column) selectedRoot);
//				} else if (selectedRoot instanceof Key) {
//					tp.setRoot((Key) selectedRoot);
//				}
				
//				for (GraphNode n: getTraceIdHierarchy()) {
//					if (n instanceof Column) {
//						tp.add((Column) n);
//					} else if (n instanceof Key) {
//						tp.add((Key) n);
//					}
//				}
				
				final ProgressHandler progressHandler = new ProgressHandler() {
					public void refreshValue(final String key, final String value) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {

								if (key != null) {
									if (key.compareTo("Traces") == 0) {
										generatedTracesLabel.setText(value);
									} else if (key.compareTo("Events") == 0) {
										processedEventsLabel.setText(value);
									} else if (key.compareTo("RemovedTraces") == 0) {
										removedTracesLabel.setText(value);
									} else if (key.compareTo("DAGTasks") == 0) {
										//dagTasksLabel.setText(value);
									}
								}
							}
						});
					}
				};
				
				final String startDate = lblStartdatevalue.getText();
				final String endDate = lblEnddatevalue.getText();
				
				Thread detectionThread = new Thread(new Runnable() {

					@Override
					public void run() {
						progressBar.setIndeterminate(true);
						progressBar.setStringPainted(true);
						progressBar.setString("Detecting...");
						
						PerspectiveDetector dec = new PerspectiveDetector(evCol, model, mapper);
						
						List<Set<TableInfo>> subgraphs = dec.getSubgraphs();
						HashMap<TableInfo, Integer> eventsPerTable = dec.getEventsPerTable();
						HashMap<TableInfo, Integer> inArcs = dec.getInArcs();
						HashMap<TableInfo, Integer> outArcs = dec.getOutArcs();
						//startDates = dec.getStartDates();
						//endDates = dec.getEndDates();
						
						DefaultTreeModel treeModel = (DefaultTreeModel) resultTree.getModel();
						MutableTreeNode root = (MutableTreeNode) treeModel.getRoot();
						
						resultTable.setModel(new DefaultTableModel(
								new Object[][] {
								},
									new String[] { "Subgraph", "Table Name", "In arcs", "Out arcs",
											"# Events", "Start Date", "End Date" }
							));
							resultTable.getColumnModel().getColumn(5).setPreferredWidth(103);
						DefaultTableModel tabModel = (DefaultTableModel) resultTable.getModel();
						
						int i = 0;
						for (Set<TableInfo> s: subgraphs) {
							MutableTreeNode n = new DefaultMutableTreeNode("Subgraph "+i, true);
							treeModel.insertNodeInto(n, root, i);
							
							int j = 0;
							for (TableInfo t: s) {
								MutableTreeNode c = new DefaultMutableTreeNode(t, true);
								Integer amount = eventsPerTable.get(t);
								MutableTreeNode c1 = new DefaultMutableTreeNode("# Events: "+amount, false);
								treeModel.insertNodeInto(c,n,j);
								treeModel.insertNodeInto(c1, c, 0);
								
								/* Table */
								tabModel.addRow(new Object[] {i,t.name,inArcs.get(t),outArcs.get(t),amount,0,0});
								
								j++;
							}
							
							i++;
						}
						
//						if (computeMatrix) {
//							
//							LogTraceSplitter.computeMatrix(outputFile, model, evCol, mapper, tp, sortFields, startDate, endDate, progressHandler);
//							
//						} else {
//						
//							SLEXPerspective perspective = LogTraceSplitter.splitLog(outputLogName, model, evCol, mapper, tp, sortFields, startDate, endDate, progressHandler);
//						
//							FramePerspectives.getInstance().addPerspective(perspective);
//						}
						
						progressBar.setIndeterminate(false);
						progressBar.setString("Detection completed");
					}
				},"PerspectivesDetectionThread");
				
				detectionThread.start();
			}
		});
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final DataModel model = FrameDataModels.getInstance().getSelectedDataModel();
				final SLEXEventCollection evcol = FrameEventCollections.getInstance().getEventCollectionFromSelector();
				
				if (model != null && evcol != null) {
					Thread loadThread = new Thread(new Runnable() {
						
						@Override
						public void run() {
							
							try {
								storage = new SLEXStorageImpl(evcol.getStorage().getPath(),evcol.getStorage().getFilename(),evcol.getStorage().getType());
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							eventCollection = storage.getEventCollection(evcol.getId());
							progressBar.setStringPainted(true);
							progressBar.setString("Loading...");
							progressBar.setIndeterminate(true);
							String title = "Log Splitter - Event Collection: "+eventCollection.getName()+" - Data Model: "+model.getName();
							FramePerspectivesDetector.this.setTitle(title);
							FramePerspectivesDetector.this.setDataModel(model);
							//histogramPanel.setData(eventCollection,dateFormat); FIXME
							progressBar.setIndeterminate(false);
							progressBar.setString("Loaded");
						}
					});
					loadThread.start();
					
				} else {
					JOptionPane.showMessageDialog(FramePerspectivesDetector.this, "Data Model or Event Collection not selected");
				}
			}
		});
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_side = new JPanel();
		panel_3.add(panel_side);
		GridBagLayout gbl_panel_side = new GridBagLayout();
		gbl_panel_side.columnWidths = new int[] {100, 200};
		gbl_panel_side.rowHeights = new int[] {60, 100, 40, 30, 30, 30, 30, 30, 30, 0};
		gbl_panel_side.columnWeights = new double[]{1.0, 1.0};
		gbl_panel_side.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		panel_side.setLayout(gbl_panel_side);
		
		JLabel lblNewLabel_1 = new JLabel("Order");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_side.add(lblNewLabel_1, gbc_lblNewLabel_1);
		listSortField.setModel(new DefaultListModel<>());
		listSortField.setDropMode(DropMode.INSERT);
		
		JScrollPane scrollPane_0 = new JScrollPane(listSortField);
		GridBagConstraints gbc_scrollPane_0 = new GridBagConstraints();
		gbc_scrollPane_0.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_0.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_0.gridx = 1;
		gbc_scrollPane_0.gridy = 0;
		panel_side.add(scrollPane_0, gbc_scrollPane_0);
		GridBagConstraints gbc_listActivityNameFields = new GridBagConstraints();
		gbc_listActivityNameFields.insets = new Insets(0, 0, 5, 0);
		gbc_listActivityNameFields.gridx = 1;
		gbc_listActivityNameFields.gridy = 2;
		
		JLabel lblNewLabel_2 = new JLabel("TraceId");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel_side.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JScrollPane scrollPane_2 = new JScrollPane(listTraceIdFields);
		GridBagConstraints gbc_textFieldTraceId = new GridBagConstraints();
		gbc_textFieldTraceId.fill = GridBagConstraints.BOTH;
		gbc_textFieldTraceId.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldTraceId.gridx = 1;
		gbc_textFieldTraceId.gridy = 1;
		panel_side.add(scrollPane_2, gbc_textFieldTraceId);		
		
		JButton btnSelectRootTraceid = new JButton("Select Root");
		btnSelectRootTraceid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRoot = getSelectedTraceIdElement();
				txtRootelement.setText(selectedRoot.toString());
			}
		});
		GridBagConstraints gbc_btnSelectRootTraceid = new GridBagConstraints();
		gbc_btnSelectRootTraceid.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectRootTraceid.gridx = 0;
		gbc_btnSelectRootTraceid.gridy = 2;
		panel_side.add(btnSelectRootTraceid, gbc_btnSelectRootTraceid);
		
		txtRootelement = new JTextField();
		txtRootelement.setEditable(false);
		GridBagConstraints gbc_txtRootelement = new GridBagConstraints();
		gbc_txtRootelement.insets = new Insets(0, 0, 5, 0);
		gbc_txtRootelement.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRootelement.gridx = 1;
		gbc_txtRootelement.gridy = 2;
		panel_side.add(txtRootelement, gbc_txtRootelement);
		txtRootelement.setColumns(10);
		
		JLabel lblProcessedEvents = new JLabel("Processed Events");
		GridBagConstraints gbc_lblProcessedEvents = new GridBagConstraints();
		gbc_lblProcessedEvents.insets = new Insets(0, 0, 5, 5);
		gbc_lblProcessedEvents.gridx = 0;
		gbc_lblProcessedEvents.gridy = 3;
		panel_side.add(lblProcessedEvents, gbc_lblProcessedEvents);
		
		processedEventsLabel = new JLabel("0");
		GridBagConstraints gbc_processedEventsLabel = new GridBagConstraints();
		gbc_processedEventsLabel.insets = new Insets(0, 0, 5, 0);
		gbc_processedEventsLabel.gridx = 1;
		gbc_processedEventsLabel.gridy = 3;
		panel_side.add(processedEventsLabel, gbc_processedEventsLabel);
		
		JLabel lblGeneratedTraces = new JLabel("Generated Traces");
		GridBagConstraints gbc_lblGeneratedTraces = new GridBagConstraints();
		gbc_lblGeneratedTraces.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneratedTraces.gridx = 0;
		gbc_lblGeneratedTraces.gridy = 4;
		panel_side.add(lblGeneratedTraces, gbc_lblGeneratedTraces);
		
		generatedTracesLabel = new JLabel("0");
		GridBagConstraints gbc_generatedTracesLabel = new GridBagConstraints();
		gbc_generatedTracesLabel.insets = new Insets(0, 0, 5, 0);
		gbc_generatedTracesLabel.gridx = 1;
		gbc_generatedTracesLabel.gridy = 4;
		panel_side.add(generatedTracesLabel, gbc_generatedTracesLabel);
		
		JLabel lblRemovedTraces = new JLabel("Removed Traces");
		GridBagConstraints gbc_lblRemovedTraces = new GridBagConstraints();
		gbc_lblRemovedTraces.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemovedTraces.gridx = 0;
		gbc_lblRemovedTraces.gridy = 5;
		panel_side.add(lblRemovedTraces, gbc_lblRemovedTraces);
		
		removedTracesLabel = new JLabel("0");
		GridBagConstraints gbc_removedTracesLabel = new GridBagConstraints();
		gbc_removedTracesLabel.insets = new Insets(0, 0, 5, 0);
		gbc_removedTracesLabel.gridx = 1;
		gbc_removedTracesLabel.gridy = 5;
		panel_side.add(removedTracesLabel, gbc_removedTracesLabel);
		
		JLabel lblStartDate = new JLabel("Start Date");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 0;
		gbc_lblStartDate.gridy = 6;
		panel_side.add(lblStartDate, gbc_lblStartDate);
		
		lblStartdatevalue = new JLabel("");
		GridBagConstraints gbc_lblStartdatevalue = new GridBagConstraints();
		gbc_lblStartdatevalue.insets = new Insets(0, 0, 5, 0);
		gbc_lblStartdatevalue.gridx = 1;
		gbc_lblStartdatevalue.gridy = 6;
		panel_side.add(lblStartdatevalue, gbc_lblStartdatevalue);
		
		JLabel lblEndDate = new JLabel("End Date");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 0;
		gbc_lblEndDate.gridy = 7;
		panel_side.add(lblEndDate, gbc_lblEndDate);
		
		lblEnddatevalue = new JLabel("");
		GridBagConstraints gbc_lblEnddatevalue = new GridBagConstraints();
		gbc_lblEnddatevalue.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnddatevalue.gridx = 1;
		gbc_lblEnddatevalue.gridy = 7;
		panel_side.add(lblEnddatevalue, gbc_lblEnddatevalue);
		
		JPanel panel_4 = new JPanel();
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] {0};
		gbl_panel_4.rowHeights = new int[] {5, 60, 0};
		gbl_panel_4.columnWeights = new double[]{1.0};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel_4.setLayout(gbl_panel_4);
		getContentPane().add(panel_4, BorderLayout.SOUTH);
		histogramPanel = new HistogramPanel();
		GridBagLayout gridBagLayout = (GridBagLayout) histogramPanel.getLayout();
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		GridBagConstraints gbc_histogramPanel = new GridBagConstraints();
		gbc_histogramPanel.anchor = GridBagConstraints.NORTH;
		gbc_histogramPanel.fill = GridBagConstraints.BOTH;
		gbc_histogramPanel.gridx = 0;
		gbc_histogramPanel.gridy = 1;
		panel_4.add(histogramPanel, gbc_histogramPanel);
		
		histogramPanel.addDateRangeChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Date[] dates = histogramPanel.getSelectedRange();
				lblStartdatevalue.setText(dateFormat.format(dates[0]));
				lblEnddatevalue.setText(dateFormat.format(dates[1]));
			}
			
		});
		
	}

	private Object getSelectedTraceIdElement() {
		return listTraceIdFields.getSelectedValue();
	}
	
	public void setTraceIdHierarchy(List<GraphNode> listSelectedNodes) {
		listTraceIdFields.clear();
		for (GraphNode g: listSelectedNodes) {
			if ((g instanceof Key) || (g instanceof Column)) {
				listTraceIdFields.addElement(g);
			}
		}
	}

	public List<GraphNode> getTraceIdHierarchy() {
		return listTraceIdFields.getNodesList();
	}	
}
