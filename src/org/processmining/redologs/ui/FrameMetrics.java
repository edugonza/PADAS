package org.processmining.redologs.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.LogTraceSplitter;

public class FrameMetrics extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -491671567731399767L;
	
	private JTable table;
	
	private JButton btnCalculateMetrics;
	private JProgressBar progressBar_metrics;
	private static final String[] metricsTableColumnNames = new String[] {"Attribute","Mean # events","Traces","Min # events","Max # events","Std deviation # events"};
	
	public void calculateMetrics(final SLEXEventCollection collection, final DataModel model) {
		btnCalculateMetrics.setEnabled(false);
		
		
		Thread metricsThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				progressBar_metrics.setIndeterminate(true);

				if (collection != null) {
					String title = "Metrics - Log: "+collection.getName();
					if (model != null) {
						title += " Data Model: "+model.getName();
					}
					FrameMetrics.this.setTitle(title);
				
					final Object[][] metrics = LogTraceSplitter
							.computeMetrics(collection, model);

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
			                        return SLEXAttribute.class;
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
	
	public FrameMetrics() {
		super("Metrics");
		this.setClosable(true);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setBounds(334, 52, 310, 121);
		table = new JTable(new Object[0][0] ,metricsTableColumnNames);
		table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		this.getContentPane().add(panel_2, BorderLayout.NORTH);
		
		progressBar_metrics = new JProgressBar();
		panel_2.add(progressBar_metrics);
		
		btnCalculateMetrics = new JButton("Calculate");
		btnCalculateMetrics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DataModel model = FrameDataModels.getInstance().getSelectedDataModel();
				//SLEXEventCollection ec = FrameEventCollections.getInstance().getEventCollectionFromSelector();
				//File logFile = FrameLogs.getInstance().getFileFromSelector();
				//calculateMetrics(ec,model);
				// FIXME calculate metrics button
			}
		});
		panel_2.add(btnCalculateMetrics);
	}
}
