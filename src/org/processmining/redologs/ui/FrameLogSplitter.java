package org.processmining.redologs.ui;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
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

import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.LogTraceSplitter;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.oracle.OracleLogMinerExtractor;
import org.processmining.redologs.oracle.OracleRelationsExplorer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
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

import javax.swing.JList;
import javax.swing.JProgressBar;
import java.awt.Component;

public class FrameLogSplitter extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7078418810402851925L;
	
	private static FrameLogSplitter _instance;
	private JTree tree;
	private DefaultTreeModel tree_model;
	private DefaultMutableTreeNode root;
	private DataModel model = null;
	private JTextField textFieldTimestamp;
	private JTextField textFieldSort;
	private JTextField textFieldTraceId;
	private JList<String> listActivityNameFields;
	
	public static FrameLogSplitter getInstance() {
		if (_instance == null) {
			_instance = new FrameLogSplitter();
		}
		return _instance;
	}
	
	private void setDataModel(DataModel model) {
		this.model = model;
		String title = "Log Splitter - Data Model: "+model.getName();
		root.setUserObject("Fields: "+model.getName());
		root.removeAllChildren();
		this.setTitle(title);
		
		/**/
		
		DefaultMutableTreeNode nodeC = new DefaultMutableTreeNode(OracleLogMinerExtractor.COLUMN_CHANGES);
		root.add(nodeC);
		
		for (String c: OracleLogMinerExtractor.LOG_MINER_BASIC_FIELDS) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(c);
			root.add(node);
		}
		
		/**/
		
		DefaultMutableTreeNode tablesFolderNode = new DefaultMutableTreeNode("Tables");
		root.add(tablesFolderNode);
		for (TableInfo t: model.getTables()) {
			DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode(t);
			tablesFolderNode.add(tableNode);
			if (t.columns == null) {
				
			}
			for (String c: t.columns) {
				tableNode.add(new DefaultMutableTreeNode(OracleLogMinerExtractor.NEW_VALUES_PREFIX+c));
				tableNode.add(new DefaultMutableTreeNode(OracleLogMinerExtractor.OLD_VALUES_PREFIX+c));
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
	
	private class ListTransferHandler extends TransferHandler {
		@Override
		public boolean canImport(TransferSupport info) {
			// Check for String flavor
	        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
	            return false;
	        }
	        return true;
	   }
		
		@Override
		public boolean importData(TransferSupport info) {
			//return super.importData(support);
			if (!info.isDrop()) {
				return false;
			}
			JList<String> list = (JList<String>)info.getComponent();
			
			Transferable t = info.getTransferable();
	        String data;
	        try {
	            data = (String)t.getTransferData(DataFlavor.stringFlavor);
	        } 
	        catch (Exception e) { return false; }
			
	        DefaultListModel<String> listModel = (DefaultListModel<String>)list.getModel();
	        
	        listModel.addElement(data);
	        
			return true;
		}
	}
	
	private class TextFieldTransferHandler extends TransferHandler {
		@Override
		public boolean canImport(TransferSupport info) {
			// Check for String flavor
	        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
	            return false;
	        }
	        return true;
	   }
		
		@Override
		public boolean importData(TransferSupport info) {
			//return super.importData(support);
			if (!info.isDrop()) {
				return false;
			}
			JTextField field = (JTextField) info.getComponent();
			
			Transferable t = info.getTransferable();
	        String data;
	        try {
	            data = (String)t.getTransferData(DataFlavor.stringFlavor);
	        } 
	        catch (Exception e) { return false; }
			
	        field.setText(data);	        
			return true;
		}
	}
	
	private FrameLogSplitter() {
		super("Log Splitter");
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		setBounds(350, 280, 620, 339);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		
		tree = new JTree();
		root = new DefaultMutableTreeNode("Fields");
		tree_model = new DefaultTreeModel(root);
		tree.setModel(tree_model);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
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
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.add(progressBar);
		
		
		btnSplitLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String timestampFieldName = textFieldTimestamp.getText();
				final String sortFieldName = textFieldSort.getText();
				final String traceIdFieldName = textFieldTraceId.getText();
				DefaultListModel<String> listActivityModel = (DefaultListModel<String>) listActivityNameFields.getModel();
				final String[] activityFieldNames = new String[listActivityModel.getSize()];
				
				for (int i = 0; i < listActivityModel.getSize(); i++) {
					activityFieldNames[i] = (String) listActivityModel.get(i);
				}
				
				AskNameDialog askDiag = new AskNameDialog();
				final String outputLogName = askDiag.showDialog();
				
				final File logFile = FrameLogs.getInstance().getFileFromSelector();
				final File splittedLogFile = new File(System.currentTimeMillis()+"-splitted-"+outputLogName);
				
				Thread logSplitThread = new Thread(new Runnable() {
					@Override
					public void run() {
						progressBar.setIndeterminate(true);
						progressBar.setStringPainted(true);
						progressBar.setString("Splitting...");
						LogTraceSplitter.splitLog(logFile, model, traceIdFieldName, sortFieldName, timestampFieldName, activityFieldNames, splittedLogFile);
						FrameLogs.getInstance().addLog(splittedLogFile.getName(), splittedLogFile);
						progressBar.setIndeterminate(false);
						progressBar.setString("Log Splitted");
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
		
		JPanel panel_side = new JPanel();
		getContentPane().add(panel_side, BorderLayout.EAST);
		GridBagLayout gbl_panel_side = new GridBagLayout();
		gbl_panel_side.columnWidths = new int[] {100, 200};
		gbl_panel_side.rowHeights = new int[]{40, 40, 40, 0, 0, 0};
		gbl_panel_side.columnWeights = new double[]{1.0, 1.0};
		gbl_panel_side.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_side.setLayout(gbl_panel_side);
		
		JLabel lblNewLabel = new JLabel("Timestamp");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_side.add(lblNewLabel, gbc_lblNewLabel);
		
		TextFieldTransferHandler textTransferHandler = new TextFieldTransferHandler();
		
		textFieldTimestamp = new JTextField();
		textFieldTimestamp.setDropMode(DropMode.INSERT);
		textFieldTimestamp.setTransferHandler(textTransferHandler);
		textFieldTimestamp.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_side.add(textFieldTimestamp, gbc_textField);
		textFieldTimestamp.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Order");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_side.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textFieldSort = new JTextField();
		textFieldSort.setDropMode(DropMode.INSERT);
		textFieldSort.setTransferHandler(textTransferHandler);
		textFieldSort.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel_side.add(textFieldSort, gbc_textField_1);
		textFieldSort.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("TraceId");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel_side.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textFieldTraceId = new JTextField();
		textFieldTraceId.setDropMode(DropMode.INSERT);
		textFieldTraceId.setTransferHandler(textTransferHandler);
		textFieldTraceId.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel_side.add(textFieldTraceId, gbc_textField_2);
		textFieldTraceId.setColumns(10);
		
		JLabel lblActivityNames = new JLabel("Activity Names");
		GridBagConstraints gbc_lblActivityNames = new GridBagConstraints();
		gbc_lblActivityNames.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblActivityNames.insets = new Insets(0, 5, 5, 5);
		gbc_lblActivityNames.gridx = 0;
		gbc_lblActivityNames.gridy = 3;
		panel_side.add(lblActivityNames, gbc_lblActivityNames);
		
		listActivityNameFields = new JList<String>();
		listActivityNameFields.setModel(new DefaultListModel<String>());
		listActivityNameFields.setDropMode(DropMode.INSERT);
		listActivityNameFields.setTransferHandler(new ListTransferHandler());
		
		JScrollPane scrollPane_1 = new JScrollPane(listActivityNameFields);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 3;
		panel_side.add(scrollPane_1, gbc_scrollPane_1);
	}	
}
