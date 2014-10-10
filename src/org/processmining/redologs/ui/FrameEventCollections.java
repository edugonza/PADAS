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

import org.processmining.openslex.SLEXStorage;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXEventCollectionResultSet;

import edu.uci.ics.jung.algorithms.metrics.Metrics;

public class FrameEventCollections extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548475893070069493L;
	private JTable table_logs;
	private DefaultTableModel model_table;
	private static FrameEventCollections _instance;
	
	public static FrameEventCollections getInstance() {
		if (_instance == null) {
			_instance = new FrameEventCollections();
		}
		return _instance;
	}
	
	public SLEXEventCollection getEventCollectionFromSelector() {
		int selectedRow = table_logs.getSelectedRow();
		if (selectedRow >= 0) {
			Object ec = table_logs.getModel().getValueAt(table_logs.convertRowIndexToModel(selectedRow), 0);
			if (ec instanceof SLEXEventCollection) {
				return (SLEXEventCollection) ec;
			}
		}
		return null;
	}
	
	public void addEventCollection(SLEXEventCollection ec) {
		model_table.addRow(new Object[] {ec,ec.getName()});
	}
	
	private void queryEventCollections() {
		try {
			SLEXEventCollectionResultSet evcrset = SLEXStorage.getInstance()
					.getEventCollections();
			SLEXEventCollection ec = null;
			while ((ec = evcrset.getNext()) != null) {
				addEventCollection(ec);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private FrameEventCollections() {
		super("Event collections");
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
		table_logs = new JTable(model_table);
		table_logs.setAutoCreateRowSorter(true);
		table_logs.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_2 = new JScrollPane(table_logs);
		this.getContentPane().add(scrollPane_2, BorderLayout.CENTER);
		
		
		JPanel panel_4 = new JPanel();
		this.getContentPane().add(panel_4, BorderLayout.NORTH);
		
		queryEventCollections();
		
		JButton btnMetrics = new JButton("Show metrics");
		btnMetrics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameMetrics fmetrics = new FrameMetrics();
				RedoLogInspector.getInstance().addFrame(fmetrics);
				fmetrics.setVisible(true);
				fmetrics.setFocusable(true);
				fmetrics.calculateMetrics(getEventCollectionFromSelector(), null); // FIXME
			}
		});
		panel_4.add(btnMetrics);
	}
}
