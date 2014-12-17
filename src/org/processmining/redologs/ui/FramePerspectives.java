package org.processmining.redologs.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.deckfour.xes.model.XLog;
import org.deckfour.xes.out.XesXmlSerializer;
import org.processmining.openslex.SLEXExporter;
import org.processmining.openslex.SLEXPerspective;
import org.processmining.openslex.SLEXPerspectiveResultSet;
import org.processmining.openslex.SLEXStoragePerspective;

import com.sun.java.swing.plaf.windows.WindowsBorders.ProgressBarBorder;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JProgressBar;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class FramePerspectives extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548475893070069493L;
	private JTable table_logs;
	private DefaultTableModel model_table;
	private static FramePerspectives _instance;
	private JProgressBar progressBar;
	
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
	
//	public void queryPerspectives(SLEXStoragePerspective storage) {
//		try {
//			SLEXPerspectiveResultSet prset = storage.getPerspectives();
//			SLEXPerspective p = null;
//			while ((p = prset.getNext()) != null) {
//				addPerspective(p);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	private FramePerspectives() {
		super("Perspectives");
		this.setClosable(false);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setBounds(350, 41, 310, 331);
		
		final String[] perspectiveTableColumnNames = new String[] {"Id","Name","Event Collection"};
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
		//table_logs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_logs.getColumnModel().getColumn(0).setPreferredWidth(8);;
		table_logs.getColumnModel().getColumn(1).setPreferredWidth(table_logs.getColumnModel().getTotalColumnWidth()-8-8);;
		table_logs.getColumnModel().getColumn(2).setPreferredWidth(8);;
		table_logs.setAutoCreateRowSorter(true);
		table_logs.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_2 = new JScrollPane(table_logs);
		this.getContentPane().add(scrollPane_2, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		this.getContentPane().add(panel_4, BorderLayout.NORTH);
		
		JButton btnExportLog = new JButton("Export");
		btnExportLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(FramePerspectives.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					Thread exportLogThread = new Thread(new Runnable() {
					
						@Override
						public void run() {
							try {
								File file = fc.getSelectedFile();
								
								progressBar.setIndeterminate(true);
								progressBar.setStringPainted(true);
								progressBar.setString("Exporting...");
								
								SLEXPerspective p = getPerspectiveFromSelector();
								XLog xlog = SLEXExporter.exportPerspectiveToXLog(p);
								XesXmlSerializer serializer = new XesXmlSerializer();
								BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
								serializer.serialize(xlog, out);
								out.close();
							} catch (Exception ex) {
								ex.printStackTrace();
							} finally {
								progressBar.setIndeterminate(false);
								progressBar.setString("Exported");
							}
						}
					});
				
					exportLogThread.start();

				}
			}
		});
		panel_4.setLayout(new BorderLayout(5, 5));
		panel_4.add(btnExportLog, BorderLayout.NORTH);
		
		progressBar = new JProgressBar();
		panel_4.add(progressBar, BorderLayout.CENTER);
	}
}
