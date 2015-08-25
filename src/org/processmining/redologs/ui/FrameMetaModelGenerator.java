package org.processmining.redologs.ui;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.processmining.database.metamodel.MetaModel;
import org.processmining.database.metamodel.MetaModelPopulator;
import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXPerspective;
import org.processmining.openslex.SLEXStorage;
import org.processmining.openslex.SLEXStorageCollection;
import org.processmining.openslex.SLEXStorageImpl;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
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
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JProgressBar;

import java.awt.Component;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ListSelectionModel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.GridLayout;

public class FrameMetaModelGenerator extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7078418810402851925L;
	
	private DataModel model = null;
	private DataModelList listSortField = new DataModelList();
	
	private Object selectedRoot = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	
	private JLabel lblStartdatevalue = null;
	private JLabel lblEnddatevalue = null;
	private SLEXEventCollection eventCollection;
	private SLEXStorageCollection storage;
	private SLEXPerspective perspective;
	
	DataModelTree tree = new DataModelTree();
	
	private void setDataModel(DataModel model) {
		if (model != this.model) {
			listSortField.clear();
		}
		this.model = model;
		tree.setDataModel(model);
	}
	
	public FrameMetaModelGenerator() {
		super("MetaModel Computer");
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setHgap(5);
		borderLayout.setVgap(5);
		setBounds(715, 30, 361, 371);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		
		JScrollPane scrollPane = new JScrollPane(tree);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 2, 3));
		
		final JButton btnLoad = new JButton("Load Data Model & Event Collection");
		btnLoad.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(btnLoad);
		
		JButton btnComputeMetaModel = new JButton("Compute MetaModel");
		btnComputeMetaModel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(btnComputeMetaModel);
		
		final JProgressBar progressBar = new JProgressBar();
		panel_1.add(progressBar);
		progressBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		btnComputeMetaModel.addActionListener(new ActionListener() {
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
				//AskYesNoDialog askMatrixDiag = new AskYesNoDialog(FrameMetaModel.this,msg);
				//final boolean computeMatrix = askMatrixDiag.showDialog();
				//String outputName = "";
//				File outputFileDfg = null;
//				if (computeMatrix) {
//					// Show dialog to pick location to save Dfg
//					final JFileChooser fc = new JFileChooser();
//					int returnVal = fc.showSaveDialog(FrameMetaModel.this);
//					
//					if (returnVal == JFileChooser.APPROVE_OPTION) {
//						outputFileDfg = fc.getSelectedFile();
//						outputName = outputFileDfg.getName(); 
//					} else {
//						return;
//					}
//				} else {
//					AskNameDialog askDiag = new AskNameDialog(FrameMetaModel.this);
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
//				
//				for (GraphNode n: getTraceIdHierarchy()) {
//					if (n instanceof Column) {
//						tp.add((Column) n);
//					} else if (n instanceof Key) {
//						tp.add((Key) n);
//					}
//				}
//				
//				final ProgressHandler progressHandler = new ProgressHandler() {
//					public void refreshValue(final String key, final String value) {
//						SwingUtilities.invokeLater(new Runnable() {
//							public void run() {
//
//								if (key != null) {
//									if (key.compareTo("Traces") == 0) {
//										generatedTracesLabel.setText(value);
//									} else if (key.compareTo("Events") == 0) {
//										processedEventsLabel.setText(value);
//									} else if (key.compareTo("RemovedTraces") == 0) {
//										removedTracesLabel.setText(value);
//									} else if (key.compareTo("DAGTasks") == 0) {
//										//dagTasksLabel.setText(value);
//									}
//								}
//							}
//						});
//					}
//				};
//				
//				final String startDate = lblStartdatevalue.getText();
//				final String endDate = lblEnddatevalue.getText();
//				
				Thread logSplitThread = new Thread(new Runnable() {
					@Override
					public void run() {
						progressBar.setIndeterminate(true);
						progressBar.setStringPainted(true);
						progressBar.setString("Computing...");
						
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
//						
						/**/
						List<Column> activityColumns = new ArrayList<>();
						Column c1 = null, c2 = null, c3 = null;
						for (Column c: tree.getCommonTable().columns) {
							if (c.name.equals("TABLE_NAME")) {
								c1 = c;
							} else if (c.name.equals("OPERATION")) {
								c2 = c;
							} else if (c.name.equals("COLUMN_CHANGES")) {
								c3 = c;
							}
						}
						if (c1 != null) {
							activityColumns.add(c1);
						}
						if (c2 != null) {
							activityColumns.add(c2);
						}
						if (c3 != null) {
							activityColumns.add(c3);
						}
						
						MetaModelPopulator mmp = new MetaModelPopulator(tree.getCommonTable(),
								model, evCol, sortFields, mapper, activityColumns,perspective);
					
						mmp.computeMetaModel();
						
						MetaModel mm = mmp.getMetaModel();
						
						SLEXMMStorageMetaModel mmstrg = mmp.saveMetaModelToDisk("metamodel-"+evCol.getName());
						
						if (mmstrg != null) {
							FrameMetaModels.getInstance().addMetaModel(mmstrg);
						}
						/**/
						
						
						progressBar.setIndeterminate(false);
						progressBar.setString("MetaModel computed");
					}
				},"LogSplitThread");
				
				logSplitThread.start();
			}
		});
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final DataModel model = FrameDataModels.getInstance().getSelectedDataModel();
				final SLEXEventCollection evcol = FrameEventCollections.getInstance().getEventCollectionFromSelector();
				perspective = FramePerspectives.getInstance().getPerspectiveFromSelector();
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
							String title = "Meta Model Generator - Event Collection: "+eventCollection.getName()+" - Data Model: "+model.getName();
							FrameMetaModelGenerator.this.setTitle(title);
							FrameMetaModelGenerator.this.setDataModel(model);
//							histogramPanel.setData(eventCollection,dateFormat);
							progressBar.setIndeterminate(false);
							progressBar.setString("Loaded");
						}
					});
					loadThread.start();
					
				} else {
					JOptionPane.showMessageDialog(FrameMetaModelGenerator.this, "Data Model or Event Collection not selected");
				}
			}
		});
		
		JPanel panel_side = new JPanel();
		getContentPane().add(panel_side, BorderLayout.SOUTH);
		panel_side.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Order");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_side.add(lblNewLabel_1, BorderLayout.NORTH);
		listSortField.setVisibleRowCount(4);
		listSortField.setModel(new DefaultListModel<>());
		listSortField.setDropMode(DropMode.INSERT);
		
		JScrollPane scrollPane_0 = new JScrollPane(listSortField);
		panel_side.add(scrollPane_0);
		GridBagConstraints gbc_listActivityNameFields = new GridBagConstraints();
		gbc_listActivityNameFields.insets = new Insets(0, 0, 5, 0);
		gbc_listActivityNameFields.gridx = 1;
		gbc_listActivityNameFields.gridy = 2;
		
	}
}
