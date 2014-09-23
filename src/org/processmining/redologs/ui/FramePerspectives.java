package org.processmining.redologs.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.processmining.openslex.SLEXPerspective;

public class FramePerspectives extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548475893070069493L;
	private JTable table_logs;
	private DefaultTableModel model_table;
	private static FramePerspectives _instance;
	
	public static FramePerspectives getInstance() {
		if (_instance == null) {
			_instance = new FramePerspectives();
		}
		return _instance;
	}
	
	public SLEXPerspective getPerspectiveFromSelector() {
		int selectedRow = table_logs.getSelectedRow();
		if (selectedRow >= 0) {
			Object perspective = table_logs.getModel().getValueAt(table_logs.convertRowIndexToModel(selectedRow), 0);
			if (perspective instanceof SLEXPerspective) {
				return (SLEXPerspective) perspective;
			}
		}
		return null;
	}
	
	public void addPerspective(SLEXPerspective perspective) {
		model_table.addRow(new Object[] {perspective,perspective.getName(),perspective.getCollectionId()});
	}
	
	private FramePerspectives() {
		super("Perspectives");
		this.setClosable(false);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setBounds(350, 41, 310, 121);
		
		final String[] perspectiveTableColumnNames = new String[] {"Id","Name","Collection ID"};
		//table_2 = new JTable(new String[0][0] ,logsTableColumnNames);
		model_table = new DefaultTableModel(new Object[0][0] ,perspectiveTableColumnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7762648250691096226L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_logs = new JTable(model_table);
		table_logs.setAutoCreateRowSorter(true);
		table_logs.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_2 = new JScrollPane(table_logs);
		this.getContentPane().add(scrollPane_2, BorderLayout.CENTER);
		
		
		JPanel panel_4 = new JPanel();
		this.getContentPane().add(panel_4, BorderLayout.NORTH);
		
//		JButton btnAddLog = new JButton("Add log");
//		final JFileChooser fc = new JFileChooser();
//		btnAddLog.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int returnVal = fc.showOpenDialog(FrameLogs.this);
//
//		        if (returnVal == JFileChooser.APPROVE_OPTION) {
//		            File file = fc.getSelectedFile();
//		            model_table.addRow(new Object[] {file,file.getName(),String.valueOf(file.length())});
//		        }
//			}
//		});
//		panel_4.add(btnAddLog);
	}
}
