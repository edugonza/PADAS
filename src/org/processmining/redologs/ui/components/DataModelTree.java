package org.processmining.redologs.ui.components;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.EventAttributeColumn;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.oracle.OracleLogMinerExtractor;

public class DataModelTree extends JTree {

	private JTree tree;
	private DefaultTreeModel tree_model;
	private DefaultMutableTreeNode root;
	private DataModel model;
	private TableInfo common_table = null;
	
	public DataModelTree() {
		tree = this;
		tree.setVisibleRowCount(15);
		root = new DefaultMutableTreeNode("Fields");
		tree_model = new DefaultTreeModel(root);
		tree.setModel(tree_model);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setTransferHandler(new TreeTransferHandler());
		tree.setDragEnabled(true);
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
					Object selectedObj = ((DefaultMutableTreeNode) selected)
							.getUserObject();
					return new TransferableDataModelElement(selectedObj);
				}
			}

			return null;
		}
	}

	private static class TransferableDataModelElement implements Transferable {

		private Object data;
		private DataFlavor dataFlavor;
		public final static DataFlavor[] flavors = new DataFlavor[] {
				new DataFlavor(Key.class, "redolog-inspector-key"),
				new DataFlavor(EventAttributeColumn.class,
						"redolog-inspector-EventAttributeColumn") };

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
				dataFlavor = new DataFlavor(data.getClass(), data.getClass()
						.toString());
			}
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { dataFlavor };
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return (flavor.getRepresentationClass().equals(dataFlavor
					.getRepresentationClass()));
		}

		@Override
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {
			return data;
		}

	}
	
	public TableInfo getCommonTable() {
		return this.common_table;
	}
	
	public void setDataModel(DataModel model) {
		
		this.model = model;
		root.setUserObject("Fields: "+model.getName());
		root.removeAllChildren();
		
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
		
		EventAttributeColumn ec2 = new EventAttributeColumn();
		ec2.c = new Column();
		ec2.c.name = OracleLogMinerExtractor.COLUMN_ORDER;
		ec2.c.table = common_table;
		common_table.columns.add(ec2.c);
		DefaultMutableTreeNode nodeC2 = new DefaultMutableTreeNode(ec2);
		root.add(nodeC2);
		
		
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
				EventAttributeColumn ect = new EventAttributeColumn();
				ect.c = c;
				ect.type = 0;
				
				tableNode.add(new DefaultMutableTreeNode(ect));
			}
			
		}
		
		//tree.expandPath(tree.getPathForRow(0));
		tree.repaint();
	}	
}
