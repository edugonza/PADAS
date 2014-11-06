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

import org.processmining.openslex.SLEXDMDataModel;
import org.processmining.openslex.SLEXDMDataModelResultSet;
import org.processmining.openslex.SLEXStorage;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.SLEXDataModelExportImport;
import org.processmining.redologs.common.TableInfo;

public class FrameDataModels extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2037015962169175501L;
	private static FrameDataModels _instance;
	private JTree tree;
	private DefaultTreeModel tree_model;
	private MutableTreeNode root;
	
	public static FrameDataModels getInstance() {
		if (_instance == null) {
			_instance = new FrameDataModels();
		}
		return _instance;
	}
	
	public void addDataModel(DataModel model) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(model);
		for (TableInfo t: model.getTables()) {
			node.add(new DefaultMutableTreeNode(t));
		}
		tree_model.insertNodeInto(node, root, root.getChildCount());
		
	}
	
	public DataModel getSelectedDataModel() {
		Object selected = tree.getLastSelectedPathComponent();
		
		if (selected != null) {
			if (selected instanceof DefaultMutableTreeNode) {
				Object selectedObj = ((DefaultMutableTreeNode) selected).getUserObject();
				if (selectedObj instanceof DataModel) {
					return (DataModel) selectedObj;
				}
			}
		}
		return null;
	}
	
	private void obtainDataModelsFromDB() {
		try {
			SLEXDMDataModelResultSet rset = SLEXStorage.getInstance()
					.getDataModels();
			SLEXDMDataModel dm = null;
			while ((dm = rset.getNext()) != null) {
				addDataModel(SLEXDataModelExportImport.loadDataModelFromSLEXDM(dm));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private FrameDataModels() {
		super("Data Models");
		setClosable(false);
		setBounds(291, 233, 333, 230);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		
		tree = new JTree();
		root = new DefaultMutableTreeNode("Data Models");
		tree_model = new DefaultTreeModel(root);
		tree.setModel(tree_model);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tree);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnLoad = new JButton("Load");
		panel.add(btnLoad);
		
		obtainDataModelsFromDB();
	}	
}
