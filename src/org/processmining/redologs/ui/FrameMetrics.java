package org.processmining.redologs.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXStorage;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.LogTraceSplitter;

import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameMetrics extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -491671567731399767L;
	
	private JTable table;
	
	private JButton btnCalculateMetrics;
	private JProgressBar progressBar_metrics;
	private static final String[] metricsTableColumnNames = new String[] {"Attribute","Mean # events","Traces","Min # events","Max # events","Std deviation # events"};
	private JPanel panel;
	private HistogramPanel histogramPanel = new HistogramPanel();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	private SLEXEventCollection collection;
	private DataModel datamodel;
	private boolean histogramLoaded = false;
	
	public void calculateMetrics(final SLEXEventCollection collection, final DataModel model) {
		btnCalculateMetrics.setEnabled(false);
		
		Thread metricsThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				progressBar_metrics.setIndeterminate(true);

				if (!histogramLoaded) {
					histogramPanel.setData(collection,dateFormat);
					histogramLoaded = true;
				}
				
				Date[] dates = histogramPanel.getSelectedRange();
				String startDate = dateFormat.format(dates[0]);
				String endDate = dateFormat.format(dates[1]);
				
				if (collection != null) {
					String title = "Metrics - Log: "+collection.getName();
					if (model != null) {
						title += " Data Model: "+model.getName();
					}
					FrameMetrics.this.setTitle(title);
				
					final Object[][] metrics = LogTraceSplitter
							.computeMetrics(collection, model,startDate,endDate);

					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
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

						}
					});
				} else {

				}
				
				progressBar_metrics.setIndeterminate(false);
				btnCalculateMetrics.setEnabled(true);
			}
		},"MetricsThread");
		
		metricsThread.start();
	}
	
	public FrameMetrics(final SLEXEventCollection evc, final DataModel model) {
		super("Metrics");
		SLEXStorage storage = null;
		try {
			storage = new SLEXStorage();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		this.collection = storage.getEventCollection(evc.getId());
		
//		this.collection = evc;
		this.datamodel = model;
		
		
		this.setClosable(true);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setBounds(334, 52, 544, 499);
		table = new JTable(new Object[0][0] ,metricsTableColumnNames);
		table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		this.getContentPane().add(panel_2, BorderLayout.NORTH);
		
		progressBar_metrics = new JProgressBar();
		panel_2.add(progressBar_metrics);
		
		btnCalculateMetrics = new JButton("Calculate for selected Period");
		btnCalculateMetrics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateMetrics(collection,model);
			}
		});
		panel_2.add(btnCalculateMetrics);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		GridBagConstraints gbc_histogramPanel_1 = new GridBagConstraints();
		gbc_histogramPanel_1.anchor = GridBagConstraints.NORTH;
		gbc_histogramPanel_1.fill = GridBagConstraints.BOTH;
		gbc_histogramPanel_1.gridx = 0;
		gbc_histogramPanel_1.gridy = 1;
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {5, 60, 0};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{0,1.0,0};
		panel.setLayout(gbl_panel);
		
		GridBagLayout gridBagLayout = (GridBagLayout) histogramPanel.getLayout();
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		GridBagConstraints gbc_histogramPanel_2 = new GridBagConstraints();
		gbc_histogramPanel_2.fill = GridBagConstraints.BOTH;
		gbc_histogramPanel_2.gridx = 0;
		gbc_histogramPanel_2.gridy = 1;
		panel.add(histogramPanel, gbc_histogramPanel_2);
		
//		histogramPanel.addDateRangeChangeListener(new ChangeListener() {
//			
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				Date[] dates = histogramPanel.getSelectedRange();
//				lblStartdatevalue.setText(dateFormat.format(dates[0]));
//				lblEnddatevalue.setText(dateFormat.format(dates[1]));
//			}
//			
//		});
		
		calculateMetrics(collection, datamodel);
	}
}
