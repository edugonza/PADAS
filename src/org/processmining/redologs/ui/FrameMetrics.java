package org.processmining.redologs.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.FieldNameCanoniser;
import org.processmining.redologs.common.LogTraceSplitter;
import org.processmining.redologs.common.RelationResult;
import org.processmining.redologs.oracle.OracleRelationsExplorer;

public class FrameMetrics extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -491671567731399767L;
	
	private JTable table;
	
	public FrameMetrics() {
		super("Metrics");
		this.setClosable(true);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setBounds(334, 52, 310, 121);
		final String[] metricsTableColumnNames = new String[] {"Attribute","Mean","Amount","Min","Max","Std deviation"};
		table = new JTable(new Object[0][0] ,metricsTableColumnNames);
		table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		this.getContentPane().add(panel_2, BorderLayout.NORTH);
		
		final JProgressBar progressBar_metrics = new JProgressBar();
		panel_2.add(progressBar_metrics);
		
		final JButton btnCalculateMetrics = new JButton("Calculate");
		btnCalculateMetrics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnCalculateMetrics.setEnabled(false);
				final File logFile = FrameLogs.getInstance().getFileFromSelector();
				
				Thread metricsThread = new Thread(new Runnable() {
									
					@Override
					public void run() {
						progressBar_metrics.setIndeterminate(true);
						
						DataModel model = FrameDataModels.getInstance().getSelectedDataModel();

						if (logFile != null) {
							FrameMetrics.this.setTitle("Metrics: "+logFile.getName());
						
							final Object[][] metrics = LogTraceSplitter
									.computeMetrics(logFile, true, true, model);

							table.setModel(new DefaultTableModel(metrics,
									metricsTableColumnNames) {
								private static final long serialVersionUID = 4497128118537468593L;

								@Override
								public boolean isCellEditable(int row,
										int column) {
									return false;
								}
								
								@Override
					            public Class getColumnClass(int column) {
									// "Attribute","Mean","Amount","Min","Max","Std deviation"
					                switch (column) {
					                    case 0:
					                        return String.class;
					                    case 1:
					                        return Float.class;
					                    case 2:
					                        return Integer.class;
					                    case 3:
					                    	return Integer.class;
					                    case 4:
					                    	return Integer.class;
					                    case 5:
					                    	return Float.class;
					                    default:
					                        return String.class;
					                }
					            }

							});
						} else {

						}
						
						progressBar_metrics.setIndeterminate(false);
						btnCalculateMetrics.setEnabled(true);
					}
				});
				
				metricsThread.start();
				
			}
		});
		panel_2.add(btnCalculateMetrics);
	}
}
