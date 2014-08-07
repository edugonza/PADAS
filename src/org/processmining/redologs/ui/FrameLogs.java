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

public class FrameLogs extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548475893070069493L;
	private JTable table_logs;
	private DefaultTableModel model_table;
	private static FrameLogs _instance;
	
	public static FrameLogs getInstance() {
		if (_instance == null) {
			_instance = new FrameLogs();
		}
		return _instance;
	}
	
	public File getFileFromSelector() {
		int selectedRow = table_logs.getSelectedRow();
		if (selectedRow >= 0) {
			Object file = table_logs.getModel().getValueAt(table_logs.convertRowIndexToModel(selectedRow), 0);
			if (file instanceof File) {
				return (File) file;
			}
		}
		return null;
	}
	
	public void addLog(String name, File logFile) {
		model_table.addRow(new Object[] {logFile,logFile.getName(),String.valueOf(logFile.length())});
	}
	
	private FrameLogs() {
		super("Logs list");
		this.setClosable(false);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setBounds(12, 41, 310, 121);
		
		final String[] logsTableColumnNames = new String[] {"Path","Name","Size"};
		//table_2 = new JTable(new String[0][0] ,logsTableColumnNames);
		model_table = new DefaultTableModel(new Object[0][0] ,logsTableColumnNames) {
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
		
		JButton btnAddLog = new JButton("Add log");
		final JFileChooser fc = new JFileChooser();
		btnAddLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(FrameLogs.this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            model_table.addRow(new Object[] {file,file.getName(),String.valueOf(file.length())});
		        }
			}
		});
		panel_4.add(btnAddLog);
	}
}
