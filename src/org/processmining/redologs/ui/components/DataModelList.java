package org.processmining.redologs.ui.components;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.EventAttributeColumn;
import org.processmining.redologs.common.GraphNode;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.TraceIDPattern;

public class DataModelList extends JList {

	private JList list = null;
	private List<Object> elementsList = new  ArrayList<>();
	private List<GraphNode> nodesList = new  ArrayList<>();
	
	public DataModelList() {
		list = this;
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new DefaultListModel<>());
		list.setDropMode(DropMode.INSERT);
		list.setTransferHandler(new ListTransferHandler(ListTransferHandler.TRACEID_HANDLER));
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_DELETE) {
					int selected = list.getSelectedIndex();
					if (selected > -1) {
						DefaultListModel model = (DefaultListModel) list.getModel();
						Object selectedObj = model.get(selected);
						elementsList.remove(selectedObj);
						if (nodesList != null) {
							nodesList.remove(selectedObj);
						}
						model.remove(selected);
					}
				}
			}
		});
	}
	
	public TraceIDPattern getTraceIDPattern(DataModel model) {
		TraceIDPattern tp = new TraceIDPattern(model);
		for (GraphNode n: this.getNodesList()) {
			if (n instanceof Column) {
				tp.add((Column) n);
			} else if (n instanceof Key) {
				tp.add((Key) n);
			}
		}
		
		return tp;
	}
	
	public void clear() {
		DefaultListModel list2Model = (DefaultListModel) list.getModel();
		list2Model.removeAllElements();
		elementsList = new ArrayList<>();
		nodesList = new ArrayList<>();
	}
	
	public void addElement(GraphNode g) {
		DefaultListModel model = (DefaultListModel) list.getModel();
		model.addElement(g);
		nodesList.add(g);
	}
	
	public List<Object> getElementsList() {
		return this.elementsList;
	}
	
	public List<GraphNode> getNodesList() {
		return this.nodesList;
	}
	
	private class ListTransferHandler extends TransferHandler {
		
		public static final int TRACEID_HANDLER = 1;
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
		        	elementsList.add(data);
		        	if (nodesList != null) {
		        		if (data instanceof GraphNode) {
		        			nodesList.add((GraphNode) data);
		        		} else if (data instanceof EventAttributeColumn) {
		        			nodesList.add(((EventAttributeColumn) data).c);
		        		}
		        	}
		        } else if (type == ORDER_HANDLER) {
		        	elementsList.add(data);
		        }
		        return true;
	        } catch (Exception e) {
	        	return false;
	        }
		}
	}
}
