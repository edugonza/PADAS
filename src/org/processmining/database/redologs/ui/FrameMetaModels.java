package org.processmining.database.redologs.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.processmining.database.redologs.ui.components.CustomInternalFrame;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModelImpl;

public class FrameMetaModels extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548475893070069493L;
	private JTable table_mms;
	private DefaultTableModel model_table;
	private static FrameMetaModels _instance;
	
	public static FrameMetaModels getInstance() {
		if (_instance == null) {
			_instance = new FrameMetaModels();
		}
		return _instance;
	}
	
	public SLEXMMStorageMetaModel getMetaModelFromSelector() {
		int selectedRow = table_mms.getSelectedRow();
		if (selectedRow >= 0) {
			Object ec = table_mms.getModel().getValueAt(table_mms.convertRowIndexToModel(selectedRow), 0);
			if (ec instanceof SLEXMMStorageMetaModel) {
				return (SLEXMMStorageMetaModel) ec;
			}
		}
		return null;
	}
	
	public void addMetaModel(SLEXMMStorageMetaModel ec) {
		model_table.addRow(new Object[] {ec,ec.getFilename()});
	}
	
//	public void queryEventCollections(SLEXStorageCollection storage) {
//		try {
//			SLEXEventCollectionResultSet evcrset = storage
//					.getEventCollections();
//			SLEXEventCollection ec = null;
//			while ((ec = evcrset.getNext()) != null) {
//				addEventCollection(ec);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	private FrameMetaModels() {
		super("Meta Models");
		this.setClosable(false);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setBounds(12, 41, 310, 121);
		
		final String[] collectionsTableColumnNames = new String[] {"ID","Name"};
		model_table = new DefaultTableModel(new Object[0][0] ,collectionsTableColumnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7762648250691096226L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_mms = new JTable(model_table);
		table_mms.setAutoCreateRowSorter(true);
		table_mms.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_2 = new JScrollPane(table_mms);
		this.getContentPane().add(scrollPane_2, BorderLayout.CENTER);
		
		
		JPanel panel_4 = new JPanel();
		this.getContentPane().add(panel_4, BorderLayout.NORTH);
		
		JButton btnShow = new JButton("Show Meta Model");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SLEXMMStorageMetaModel mmstrg = getMetaModelFromSelector();
				if (mmstrg != null) {
					SLEXMMStorageMetaModel mmstrgAux;
					try {
						mmstrgAux = new SLEXMMStorageMetaModelImpl(mmstrg.getPath(),mmstrg.getFilename());
						FrameMetaModelInspect fmmins = new FrameMetaModelInspect(mmstrgAux);
						RedoLogInspector.getInstance().addFrame(fmmins);
						fmmins.setVisible(true);
						fmmins.setFocusable(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel_4.add(btnShow);
	}
}
