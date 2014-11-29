package org.processmining.redologs.ui;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXPerspective;
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
import org.processmining.redologs.oracle.OracleRelationsExplorer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.rmi.server.LogStream;
import java.text.AttributedCharacterIterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JProgressBar;

import java.awt.Component;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ListSelectionModel;

public class FrameLogSplitter extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7078418810402851925L;
	
	//private static FrameLogSplitter _instance;
	private JTree tree;
	private DefaultTreeModel tree_model;
	private DefaultMutableTreeNode root;
	private DataModel model = null;
	private JTextField textFieldTimestamp;
	private JList<Object> listSortField;
	private JList<Object> listActivityNameFields;
	private JList<Object> listTraceIdFields;
	
	private Object timeStampSelected = null;
	private List<Object> activityNamesSelected = new Vector<>();
	private List<Object> orderNamesSelected = new Vector<>();
	private List<Object> traceIdNamesSelected = new Vector<>();

	private List<GraphNode> listTraceIdNodes = new Vector<>();
	
	private Object selectedRoot = null;
	private JTextField txtRootelement;
	private TableInfo common_table = null;
	private JLabel processedEventsLabel;
	private JLabel generatedTracesLabel;
	private JLabel removedTracesLabel;
	
//	public static FrameLogSplitter getInstance() {
//		if (_instance == null) {
//			_instance = new FrameLogSplitter();
//		}
//		return _instance;
//	}
	
	private void setDataModel(DataModel model) {
		if (model != this.model) {
			textFieldTimestamp.setText("");
			DefaultListModel listModel = (DefaultListModel) listActivityNameFields.getModel();
			listModel.removeAllElements();
			DefaultListModel list2Model = (DefaultListModel) listTraceIdFields.getModel();
			list2Model.removeAllElements();
			DefaultListModel list3Model = (DefaultListModel) listSortField.getModel();
			list3Model.removeAllElements();
			timeStampSelected = null;
			traceIdNamesSelected = new Vector<>();
			activityNamesSelected = new Vector<>();
			orderNamesSelected = new Vector<>();
		}
		this.model = model;
		String title = "Log Splitter - Data Model: "+model.getName();
		root.setUserObject("Fields: "+model.getName());
		root.removeAllChildren();
		this.setTitle(title);
		
		/**/
		common_table = new TableInfo();
		common_table.columns = new Vector<>();
		common_table.name = "COMMON";
		
		EventAttributeColumn ec = new EventAttributeColumn();
		ec.c = new Column();
		ec.c.name = OracleLogMinerExtractor.COLUMN_CHANGES;
		ec.c.table = common_table;
		common_table.columns.add(ec.c);
		DefaultMutableTreeNode nodeC = new DefaultMutableTreeNode(ec);
		root.add(nodeC);
		
		
		for (String c: OracleLogMinerExtractor.LOG_MINER_BASIC_FIELDS) {
			EventAttributeColumn eac = new EventAttributeColumn();
			eac.c = new Column();
			eac.c.name = c;
			eac.c.table = common_table;
			common_table.columns.add(eac.c);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(eac);
			root.add(node);
		}
		
		/**/
		
		DefaultMutableTreeNode tablesFolderNode = new DefaultMutableTreeNode("Tables");
		root.add(tablesFolderNode);
		for (TableInfo t: model.getTables()) {
			DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode(t);
			tablesFolderNode.add(tableNode);
			
			if (!model.getKeysPerTable(t).isEmpty()) {
				DefaultMutableTreeNode keysNode = new DefaultMutableTreeNode("Keys");
				tableNode.add(keysNode);
				for (Key k: model.getKeysPerTable(t)) {
					keysNode.add(new DefaultMutableTreeNode(k));
				}
			}
			
			for (Column c: t.columns) {
//				EventAttributeColumn ecN = new EventAttributeColumn();
//				ecN.c = new Column();
//				ecN.c.name = c;
//				ecN.c.table = t;
//				ecN.type = EventAttributeColumn.VALUE_NEW;
//				
//				EventAttributeColumn ecO = new EventAttributeColumn();
//				ecO.c = new Column();
//				ecO.c.name = c;
//				ecO.c.table = t;
//				ecO.type = EventAttributeColumn.VALUE_OLD;				
//				
//				tableNode.add(new DefaultMutableTreeNode(ecN));
//				tableNode.add(new DefaultMutableTreeNode(ecO));
				
				EventAttributeColumn ect = new EventAttributeColumn();
				ect.c = c;
				ect.type = 0;
				
				tableNode.add(new DefaultMutableTreeNode(ect));
			}
			
		}
		tree.repaint();
	}
	
//	private DataModel getSelectedDataModel() {
//		Object selected = tree.getLastSelectedPathComponent();
//		
//		if (selected != null) {
//			if (selected instanceof DefaultMutableTreeNode) {
//				Object selectedObj = ((DefaultMutableTreeNode) selected).getUserObject();
//				if (selectedObj instanceof DataModel) {
//					return (DataModel) selectedObj;
//				}
//			}
//		}
//		return null;
//	}
	
	
	
	
	private static class TransferableDataModelElement implements Transferable {

		private Object data;
		private DataFlavor dataFlavor;
		public final static DataFlavor[] flavors = new DataFlavor[] {
				new DataFlavor(Key.class, "redolog-inspector-key"),
				new DataFlavor(EventAttributeColumn.class, "redolog-inspector-EventAttributeColumn")};
		
		public TransferableDataModelElement(Object data) {
			super();
			this.data = data;
			boolean found = false;
			int i = 0;
			while (!found && i < flavors.length) {
				if (flavors[i].getDefaultRepresentationClass().isInstance(data)) {
					dataFlavor = flavors[i];
					found = true;
				}
				i++;
			}
			if (!found) {
				dataFlavor = new DataFlavor(data.getClass(), data.getClass().toString());
			}
		}
		
		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] {dataFlavor};
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return (flavor.getRepresentationClass().equals(dataFlavor.getRepresentationClass()));
		}

		@Override
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {
			return data;
		}
		
	}
	
	private class TreeTransferHandler extends TransferHandler {
		
		@Override
		public int getSourceActions(JComponent c) {
			return TransferHandler.COPY;
		}
		
		@Override
		protected Transferable createTransferable(JComponent c) {
			JTree tree = (JTree) c;
			
			Object selected = tree.getLastSelectedPathComponent();
			
			if (selected != null) {
				if (selected instanceof DefaultMutableTreeNode) {
					Object selectedObj = ((DefaultMutableTreeNode) selected).getUserObject();
					return new TransferableDataModelElement(selectedObj);
				}
			}
			
			return null;
		}
	}
	
	private class ListTransferHandler extends TransferHandler {
		
		public static final int TRACEID_HANDLER = 1;
		public static final int ACTIVITY_HANDLER = 2;
		public static final int ORDER_HANDLER = 3;
		
		private int type = 0;
		
		public ListTransferHandler(int type) {
			this.type = type;
		}
		
		@Override
		public boolean canImport(TransferSupport info) {
			// Check for flavor
	        if (info.isDataFlavorSupported(new DataFlavor(Key.class, "")) ||
	        		info.isDataFlavorSupported(new DataFlavor(EventAttributeColumn.class, ""))) {
	            return true;
	        }
	        return false;
	   }
		
		@Override
		public boolean importData(TransferSupport info) {
			//return super.importData(support);
			if (!info.isDrop()) {
				return false;
			}
			JList<Object> list = (JList<Object>)info.getComponent();
			
			Transferable t = info.getTransferable();
	        Object data;
	        try {
	            data = t.getTransferData(new DataFlavor(Object.class,""));
	            DefaultListModel<Object> listModel = (DefaultListModel<Object>)list.getModel();
		        listModel.addElement(data);
		        if (type == TRACEID_HANDLER) {
		        	traceIdNamesSelected.add(data);
		        	if (listTraceIdNodes != null) {
		        		if (data instanceof GraphNode) {
		        			listTraceIdNodes.add((GraphNode) data);
		        		} else if (data instanceof EventAttributeColumn) {
		        			listTraceIdNodes.add(((EventAttributeColumn) data).c);
		        		}
		        	}
		        } else if (type == ACTIVITY_HANDLER) {
		        	activityNamesSelected.add(data);
		        } else if (type == ORDER_HANDLER) {
		        	orderNamesSelected.add(data);
		        }
		        return true;
	        } catch (Exception e) {
	        	return false;
	        }
		}
	}
	
	private class TextFieldTransferHandler extends TransferHandler {
		@Override
		public boolean canImport(TransferSupport info) {
			// Check for flavor
			if (info.isDataFlavorSupported(new DataFlavor(Key.class, "")) ||
	        		info.isDataFlavorSupported(new DataFlavor(EventAttributeColumn.class, ""))) {
	            return true;
	        }
	        return false;
	    }
		
		public void setData(Object data) {
			
		}
		
		@Override
		public boolean importData(TransferSupport info) {
			if (!info.isDrop()) {
				return false;
			}
			JTextField field = (JTextField) info.getComponent();
			
			Transferable t = info.getTransferable();
	        Object data;
	        try {
	            data = (Object)t.getTransferData(new DataFlavor(Object.class,""));
	            setData(data);
	        } 
	        catch (Exception e) { return false; }
			
	        field.setText(data.toString()); //FIXME proper field name transformation
			return true;
		}
	}
	
	public FrameLogSplitter() {
		super("Log Splitter");
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		setBounds(350, 280, 660, 486);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		
		tree = new JTree();
		root = new DefaultMutableTreeNode("Fields");
		tree_model = new DefaultTreeModel(root);
		tree.setModel(tree_model);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setTransferHandler(new TreeTransferHandler());
		tree.setDragEnabled(true);
		
		JScrollPane scrollPane = new JScrollPane(tree);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JButton btnLoad = new JButton("Load Data Model");
		panel_1.add(btnLoad);
		
		JButton btnSplitLog = new JButton("Split Log");
		panel_1.add(btnSplitLog);
		
		JButton btnVisualizeDataModel = new JButton("Visualize Data Model");
		btnVisualizeDataModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model != null) {
					FrameRelationsGraph relations = new FrameRelationsGraph(true,FrameLogSplitter.this);
					RedoLogInspector.getInstance().addFrame(relations);
					relations.setVisible(true);
					relations.setFocusable(true);
					relations.reloadGraph(model);
				}
			}
		});
		panel_1.add(btnVisualizeDataModel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.add(progressBar);
		
		
		btnSplitLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String aux = null;
				
				if (timeStampSelected != null) {
					if (timeStampSelected instanceof Key) {
						
					} else if (timeStampSelected instanceof EventAttributeColumn) {
						//aux = timeStampSelected.
					} else {
						aux = timeStampSelected.toString();
					}
				}
				
				//final String timestampFieldName = textFieldTimestamp.getText();
//				Column sortColumn = null; // FIXME what if sort column is a key?
//				if (sortSelected instanceof EventAttributeColumn) {
//					sortColumn = ((EventAttributeColumn) sortSelected).c;
//				} else {
//					sortColumn = new Column();
//				}
				
				final List<Column> sortFields = new Vector<>();
				for (int i = 0; i < orderNamesSelected.size(); i++) {
					if (orderNamesSelected.get(i) instanceof EventAttributeColumn) {
						sortFields.add(((EventAttributeColumn) orderNamesSelected.get(i)).c);
					} else if (orderNamesSelected.get(i) instanceof Column) {
						sortFields.add((Column) orderNamesSelected.get(i));
					}
				}
				
				
				//final String sortFieldName = textFieldSort.getText();
				
				//DefaultListModel<Object> listActivityModel = (DefaultListModel<Object>) listActivityNameFields.getModel();
				//final String[] activityFieldNames = new String[listActivityModel.getSize()];
				
				//for (int i = 0; i < listActivityModel.getSize(); i++) {
				//	activityFieldNames[i] = (String) listActivityModel.get(i);
				//}
				
				AskNameDialog askDiag = new AskNameDialog(FrameLogSplitter.this);
				final String outputLogName = askDiag.showDialog();
				
				final SLEXEventCollection evCol = FrameEventCollections.getInstance().getEventCollectionFromSelector();
				
				List<TableInfo> tables = new Vector<>();
				tables.addAll(model.getTables());
				tables.add(common_table);
				
				final SLEXAttributeMapper mapper = LogTraceSplitter.computeMapping(evCol, tables);
				final TraceIDPattern tp = new TraceIDPattern(model);
				
				if (selectedRoot instanceof EventAttributeColumn) {
					tp.setRoot(((EventAttributeColumn) selectedRoot).c);
				} else if (selectedRoot instanceof Column) {
					tp.setRoot((Column) selectedRoot);
				} else if (selectedRoot instanceof Key) {
					tp.setRoot((Key) selectedRoot);
				}
				
				for (GraphNode n: getTraceIdHierarchy()) {
					if (n instanceof Column) {
						tp.add((Column) n);
					} else if (n instanceof Key) {
						tp.add((Key) n);
					}
				}
				
				final ProgressHandler progressHandler = new ProgressHandler() {
					public void refreshValue(String key, String value) {
						if (key != null) {
							if (key.compareTo("Traces") == 0) {
								generatedTracesLabel.setText(value);
							} else if (key.compareTo("Events") == 0) {
								processedEventsLabel.setText(value);
							} else if (key.compareTo("RemovedTraces") == 0) {
								removedTracesLabel.setText(value);
							}
						}
					}
				};
				
				Thread logSplitThread = new Thread(new Runnable() {
					@Override
					public void run() {
						progressBar.setIndeterminate(true);
						progressBar.setStringPainted(true);
						progressBar.setString("Splitting...");
						
						SLEXPerspective perspective = LogTraceSplitter.splitLog(outputLogName, model, evCol, mapper, tp, sortFields,progressHandler);
						
						FramePerspectives.getInstance().addPerspective(perspective);
						
						progressBar.setIndeterminate(false);
						progressBar.setString("Log Split");
					}
				});
				
				logSplitThread.start();
			}
		});
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataModel model = FrameDataModels.getInstance().getSelectedDataModel();
				if (model != null) {
					FrameLogSplitter.this.setDataModel(model);
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
		gbl_panel_side.rowHeights = new int[] {40, 40, 100, 100, 40, 40, 40, 40, 30, 0};
		gbl_panel_side.columnWeights = new double[]{1.0, 1.0};
		gbl_panel_side.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel_side.setLayout(gbl_panel_side);
		
		JLabel lblNewLabel = new JLabel("Timestamp");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_side.add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldTimestamp = new JTextField();
		textFieldTimestamp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				textFieldTimestamp.setText("");
				timeStampSelected = null;
			}
		});
		textFieldTimestamp.setDropMode(DropMode.INSERT);
		textFieldTimestamp.setTransferHandler(new TextFieldTransferHandler() {
			@Override
			public void setData(Object data) {
				timeStampSelected = data;
			}
		});
		textFieldTimestamp.setEditable(false);
		GridBagConstraints gbc_textFieldTimestamp = new GridBagConstraints();
		gbc_textFieldTimestamp.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTimestamp.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldTimestamp.gridx = 1;
		gbc_textFieldTimestamp.gridy = 0;
		panel_side.add(textFieldTimestamp, gbc_textFieldTimestamp);
		textFieldTimestamp.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Order");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_side.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		listSortField = new JList<>();
		listSortField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_DELETE) {
					int selected = listSortField.getSelectedIndex();
					if (selected > -1) {
						DefaultListModel model = (DefaultListModel) listSortField.getModel();
						Object selectedObj = model.get(selected);
						orderNamesSelected.remove(selectedObj);
						model.remove(selected);
					}
				}
			}
		});
		listSortField.setModel(new DefaultListModel<>());
		listSortField.setDropMode(DropMode.INSERT);
		listSortField.setTransferHandler(new ListTransferHandler(ListTransferHandler.ORDER_HANDLER));
		
		JScrollPane scrollPane_0 = new JScrollPane(listSortField);
		GridBagConstraints gbc_scrollPane_0 = new GridBagConstraints();
		gbc_scrollPane_0.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_0.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_0.gridx = 1;
		gbc_scrollPane_0.gridy = 1;
		panel_side.add(scrollPane_0, gbc_scrollPane_0);
		
		JLabel lblActivityNames = new JLabel("Activity Names");
		GridBagConstraints gbc_lblActivityNames = new GridBagConstraints();
		gbc_lblActivityNames.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblActivityNames.insets = new Insets(0, 5, 5, 5);
		gbc_lblActivityNames.gridx = 0;
		gbc_lblActivityNames.gridy = 2;
		panel_side.add(lblActivityNames, gbc_lblActivityNames);
		GridBagConstraints gbc_listActivityNameFields = new GridBagConstraints();
		gbc_listActivityNameFields.insets = new Insets(0, 0, 5, 0);
		gbc_listActivityNameFields.gridx = 1;
		gbc_listActivityNameFields.gridy = 2;
		
		listActivityNameFields = new JList<>();
		
		listActivityNameFields.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_DELETE) {
					int selected = listActivityNameFields.getSelectedIndex();
					if (selected > -1) {
						DefaultListModel model = (DefaultListModel) listActivityNameFields.getModel();
						Object selectedObj = model.get(selected);
						activityNamesSelected.remove(selectedObj);
						model.remove(selected);
					}
				}
			}
		});
		listActivityNameFields.setModel(new DefaultListModel<>());
		listActivityNameFields.setDropMode(DropMode.INSERT);
		listActivityNameFields.setTransferHandler(new ListTransferHandler(ListTransferHandler.ACTIVITY_HANDLER));
		
		JScrollPane scrollPane_1 = new JScrollPane(listActivityNameFields);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 2;
		panel_side.add(scrollPane_1, gbc_scrollPane_1);
		
		JLabel lblNewLabel_2 = new JLabel("TraceId");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel_side.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		listTraceIdFields = new JList<>();
		listTraceIdFields.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTraceIdFields.setModel(new DefaultListModel<>());
		listTraceIdFields.setDropMode(DropMode.INSERT);
		listTraceIdFields.setTransferHandler(new ListTransferHandler(ListTransferHandler.TRACEID_HANDLER));
		listTraceIdFields.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_DELETE) {
					int selected = listTraceIdFields.getSelectedIndex();
					if (selected > -1) {
						DefaultListModel model = (DefaultListModel) listTraceIdFields.getModel();
						Object selectedObj = model.get(selected);
						traceIdNamesSelected.remove(selectedObj);
						if (listTraceIdNodes != null) {
							listTraceIdNodes.remove(selectedObj);
						}
						model.remove(selected);
					}
				}
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane(listTraceIdFields);
		GridBagConstraints gbc_textFieldTraceId = new GridBagConstraints();
		gbc_textFieldTraceId.fill = GridBagConstraints.BOTH;
		gbc_textFieldTraceId.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldTraceId.gridx = 1;
		gbc_textFieldTraceId.gridy = 3;
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
		gbc_btnSelectRootTraceid.gridy = 4;
		panel_side.add(btnSelectRootTraceid, gbc_btnSelectRootTraceid);
		
		txtRootelement = new JTextField();
		txtRootelement.setEditable(false);
		GridBagConstraints gbc_txtRootelement = new GridBagConstraints();
		gbc_txtRootelement.insets = new Insets(0, 0, 5, 0);
		gbc_txtRootelement.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRootelement.gridx = 1;
		gbc_txtRootelement.gridy = 4;
		panel_side.add(txtRootelement, gbc_txtRootelement);
		txtRootelement.setColumns(10);
		
		JLabel lblProcessedEvents = new JLabel("Processed Events");
		GridBagConstraints gbc_lblProcessedEvents = new GridBagConstraints();
		gbc_lblProcessedEvents.insets = new Insets(0, 0, 5, 5);
		gbc_lblProcessedEvents.gridx = 0;
		gbc_lblProcessedEvents.gridy = 5;
		panel_side.add(lblProcessedEvents, gbc_lblProcessedEvents);
		
		processedEventsLabel = new JLabel("0");
		GridBagConstraints gbc_processedEventsLabel = new GridBagConstraints();
		gbc_processedEventsLabel.insets = new Insets(0, 0, 5, 0);
		gbc_processedEventsLabel.gridx = 1;
		gbc_processedEventsLabel.gridy = 5;
		panel_side.add(processedEventsLabel, gbc_processedEventsLabel);
		
		JLabel lblGeneratedTraces = new JLabel("Generated Traces");
		GridBagConstraints gbc_lblGeneratedTraces = new GridBagConstraints();
		gbc_lblGeneratedTraces.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneratedTraces.gridx = 0;
		gbc_lblGeneratedTraces.gridy = 6;
		panel_side.add(lblGeneratedTraces, gbc_lblGeneratedTraces);
		
		generatedTracesLabel = new JLabel("0");
		GridBagConstraints gbc_generatedTracesLabel = new GridBagConstraints();
		gbc_generatedTracesLabel.insets = new Insets(0, 0, 5, 0);
		gbc_generatedTracesLabel.gridx = 1;
		gbc_generatedTracesLabel.gridy = 6;
		panel_side.add(generatedTracesLabel, gbc_generatedTracesLabel);
		
		JLabel lblRemovedTraces = new JLabel("Removed Traces");
		GridBagConstraints gbc_lblRemovedTraces = new GridBagConstraints();
		gbc_lblRemovedTraces.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemovedTraces.gridx = 0;
		gbc_lblRemovedTraces.gridy = 7;
		panel_side.add(lblRemovedTraces, gbc_lblRemovedTraces);
		
		removedTracesLabel = new JLabel("0");
		GridBagConstraints gbc_removedTracesLabel = new GridBagConstraints();
		gbc_removedTracesLabel.insets = new Insets(0, 0, 5, 0);
		gbc_removedTracesLabel.gridx = 1;
		gbc_removedTracesLabel.gridy = 7;
		panel_side.add(removedTracesLabel, gbc_removedTracesLabel);
		
	}

	private Object getSelectedTraceIdElement() {
		return listTraceIdFields.getSelectedValue();
	}
	
	public void setTraceIdHierarchy(List<GraphNode> listSelectedNodes) {
		DefaultListModel model = (DefaultListModel) listTraceIdFields.getModel();
		model.removeAllElements();
		listTraceIdNodes = new Vector<>();
		for (GraphNode g: listSelectedNodes) {
			if ((g instanceof Key) || (g instanceof Column)) {
				model.addElement(g);
				listTraceIdNodes.add(g);
			}			
		}
	}

	public List<GraphNode> getTraceIdHierarchy() {
		return listTraceIdNodes;
	}	
}
