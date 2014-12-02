package org.processmining.redologs.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeMapImpl;
import org.deckfour.xes.model.impl.XLogImpl;
import org.deckfour.xes.model.impl.XTraceImpl;
import org.deckfour.xes.out.XSerializer;
import org.deckfour.xes.out.XesXmlSerializer;
import org.processmining.openslex.SLEXDMDataModel;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXStorage;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.SLEXDataModelExportImport;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.config.Config;
import org.processmining.redologs.config.DatabaseConnectionData;
import org.processmining.redologs.oracle.OracleLogMinerExtractor;
import org.processmining.redologs.oracle.OracleRelationsExplorer;

public class FrameTables extends CustomInternalFrame {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6717664634757776762L;
	private JTable table_tables;
	private static FrameTables _instance;
	private JComboBox comboBoxConnections;
	private final String[] tablesTableColumnNames = new String[] {"Name","Database Name","Table Name"};
	
	private OracleLogMinerExtractor extractor = null;
	private boolean stopExtractor = false;

	public static FrameTables getInstance() {
		if (_instance == null) {
			_instance = new FrameTables();
		}
		return _instance;
	}
	
	public void stopExtractor() {
		this.stopExtractor = true;
		if (extractor != null) {
			extractor.setExtractionFlag(false);
		}
	}
	
	public List<TableInfo> getSelectedTables() {
		int[] selected = table_tables.getSelectedRows();
		Vector<TableInfo> selectedTables = new Vector<>();
		for (int i: selected) {
			selectedTables.add((TableInfo) table_tables.getModel().getValueAt(table_tables.convertRowIndexToModel(i), 0));
		}
		return selectedTables;
	}
	
	public void refreshConnectionsComboBox() {
		Vector<DatabaseConnectionData> connectionsVector = new Vector<>(Config.getInstance().getConnections());
		comboBoxConnections.setModel(new DefaultComboBoxModel<>(connectionsVector));
	}
	
	public DatabaseConnectionData getSelectedConnection() {
		return (DatabaseConnectionData) comboBoxConnections.getSelectedItem();
	}
	
	private FrameTables() {
		super("Tables list");
		this.setClosable(false);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setBounds(26, 208, 319, 202);
		
		final DefaultTableModel model_tables = new DefaultTableModel(new Object[0][0] ,tablesTableColumnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7762648250691096226L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_tables = new JTable(model_tables);
		table_tables.setAutoCreateRowSorter(true);
		table_tables.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_3 = new JScrollPane(table_tables);
		this.getContentPane().add(scrollPane_3, BorderLayout.CENTER);
		
		
		JPanel panel_5 = new JPanel();
		this.getContentPane().add(panel_5, BorderLayout.NORTH);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.rowHeights = new int[] {27, 0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel_5.setLayout(gbl_panel_5);
		
		comboBoxConnections = new JComboBox();
		refreshConnectionsComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.anchor = GridBagConstraints.WEST;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 0;
		gbc_comboBox_1.gridy = 0;
		panel_5.add(comboBoxConnections, gbc_comboBox_1);
		JButton btnObtainTables = new JButton("Obtain tables");
		
		final JProgressBar progressBar_1 = new JProgressBar();
		GridBagConstraints gbc_progressBar_1 = new GridBagConstraints();
		gbc_progressBar_1.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_1.gridwidth = 2;
		gbc_progressBar_1.gridx = 0;
		gbc_progressBar_1.gridy = 2;
		panel_5.add(progressBar_1, gbc_progressBar_1);
		
		btnObtainTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread obtainTablesThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						progressBar_1.setIndeterminate(true);
						model_tables.setDataVector(new Object[0][0], tablesTableColumnNames);
						List<TableInfo> tables = new Vector<>();
						try {
							tables = OracleRelationsExplorer.getTables(getSelectedConnection(),false);
						} catch (Exception e) {
							InfoDialog info = new InfoDialog(e.getMessage(),FrameTables.getInstance());
							info.showDialog();
						}
						for (TableInfo t: tables) {
							model_tables.addRow(new Object[] {t,t.db,t.name});
						}
						progressBar_1.setIndeterminate(false);
					}
				});
				obtainTablesThread.start();
			}
		});
		
		GridBagConstraints gbc_btnObtainTables = new GridBagConstraints();
		gbc_btnObtainTables.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnObtainTables.anchor = GridBagConstraints.EAST;
		gbc_btnObtainTables.insets = new Insets(0, 0, 5, 0);
		gbc_btnObtainTables.gridx = 1;
		gbc_btnObtainTables.gridy = 0;
		panel_5.add(btnObtainTables, gbc_btnObtainTables);
		
		JButton btnSaveDataModel = new JButton("Save Data Model");
		btnSaveDataModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AskNameDialog nameDiag = new AskNameDialog(FrameTables.getInstance());
				final String modelName = nameDiag.showDialog();
				
				if (modelName != null && !modelName.isEmpty()) {
					
					Thread extractDataModelThread = new Thread(new Runnable() {
						@Override
						public void run() {
							
							progressBar_1.setIndeterminate(true);
							try {
								OracleRelationsExplorer explorer = new OracleRelationsExplorer(
										getSelectedConnection(),
										getSelectedTables());

								if (explorer.connect()) {
									DataModel model = explorer
											.extractRelations();
									model.setName(modelName);
									SLEXDMDataModel dm = SLEXDataModelExportImport
											.saveDataModelToSLEXDM(model);
									FrameDataModels.getInstance().addDataModel(
											model);
									explorer.disconnect();
								} else {
									InfoDialog info = new InfoDialog(
											"Failure connecting to Database",
											FrameTables.getInstance());
									info.showDialog();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							progressBar_1.setIndeterminate(false);
						}
					});
					
					extractDataModelThread.start();
					
				}
			}
		});
		GridBagConstraints gbc_btnSaveDataModel = new GridBagConstraints();
		gbc_btnSaveDataModel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveDataModel.anchor = GridBagConstraints.WEST;
		gbc_btnSaveDataModel.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveDataModel.gridx = 0;
		gbc_btnSaveDataModel.gridy = 1;
		panel_5.add(btnSaveDataModel, gbc_btnSaveDataModel);
		
		JButton btnExtractRedoLog = new JButton("Extract Redo Log");
		btnExtractRedoLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AskNameDialog nameDiag = new AskNameDialog(FrameTables.getInstance());
				final String logName = nameDiag.showDialog();

				if (logName != null && !logName.isEmpty()) {

					stopExtractor = false;
					
					Thread extractLogThread = new Thread(new Runnable() {

						@Override
						public void run() {
							progressBar_1.setIndeterminate(true);
							progressBar_1.setString("Connecting...");
							progressBar_1.setStringPainted(true);

							List<TableInfo> tables = getSelectedTables();

							extractor = new OracleLogMinerExtractor(
									getSelectedConnection(), tables);
							extractor.setExtractionFlag(true);
							if (extractor.connect()) {
								List<String> redoFiles = extractor
										.getRedoLogFiles();
								if (extractor.startLogMiner(redoFiles)) {
									
									SLEXEventCollection eventCollection = null;
									
									try {
										eventCollection = SLEXStorage.getInstance().createEventCollection(logName);
									} catch (Exception e) {
										e.printStackTrace();
									}
									
									progressBar_1.setIndeterminate(false);
									
									int total = tables.size();
									int progress = 0;
									progressBar_1.setValue((progress*100)/total);
									progressBar_1.setString("Extracting: "+(progress*100)/total+"%");
									
									HashMap<String,Integer> orderIds = extractor.getOrderOfLogs(tables);
									
									for (int it = 0; (it < tables.size()) && !stopExtractor; it++) {
										extractor.disconnect();
										extractor.connect();
										extractor.startLogMiner(redoFiles);
										//for (TableInfo t : tables) {
										TableInfo t = tables.get(it);
										progress++;
										if (t.columns == null) {
											extractor.getTableColumns(t);
										}
										System.out.println("Table: "+t.name);
										
										extractor.getLogsForTableWithColumns(t,
												null, eventCollection, false, true,orderIds);
										progressBar_1.setValue((progress*100)/total);
										progressBar_1.setString("Extracting: "+(progress*100)/total+"%");
									}

									try {
										FrameEventCollections.getInstance().addEventCollection(eventCollection);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
								extractor.disconnect();
								extractor = null;
							} else {
								System.err.println("ERROR: connection failed");
							}

							progressBar_1.setString("");
							progressBar_1.setIndeterminate(false);
						}
					});
					
					extractLogThread.start();
				}
			}
		});
		GridBagConstraints gbc_btnExtractRedoLog = new GridBagConstraints();
		gbc_btnExtractRedoLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExtractRedoLog.anchor = GridBagConstraints.EAST;
		gbc_btnExtractRedoLog.insets = new Insets(0, 0, 5, 0);
		gbc_btnExtractRedoLog.gridx = 1;
		gbc_btnExtractRedoLog.gridy = 1;
		panel_5.add(btnExtractRedoLog, gbc_btnExtractRedoLog);
		
		JButton btnStop = new JButton("Stop Extraction");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopExtractor();
			}
		});
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 0, 5);
		gbc_btnStop.gridx = 0;
		gbc_btnStop.gridy = 3;
		panel_5.add(btnStop, gbc_btnStop);
	}
}
