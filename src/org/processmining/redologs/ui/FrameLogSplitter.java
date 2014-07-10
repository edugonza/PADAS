package org.processmining.redologs.ui;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.oracle.OracleLogMinerExtractor;
import org.processmining.redologs.oracle.OracleRelationsExplorer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		for (String c: OracleLogMinerExtractor.LOG_MINER_BASIC_FIELDS) {
			root.add(new DefaultMutableTreeNode(c));
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
	
	private FrameLogSplitter() {
		super("Log Splitter");
		setBounds(350, 280, 333, 230);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		
		tree = new JTree();
		root = new DefaultMutableTreeNode("Fields");
		tree_model = new DefaultTreeModel(root);
		tree.setModel(tree_model);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tree);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnLoad = new JButton("Load Data Model");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataModel model = FrameDataModels.getInstance().getSelectedDataModel();
				if (model != null) {
					FrameLogSplitter.this.setDataModel(model);
				}
			}
		});
		panel.add(btnLoad);
		
	}	
}
