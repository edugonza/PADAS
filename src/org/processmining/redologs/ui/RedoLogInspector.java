package org.processmining.redologs.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionEvent;

import org.processmining.redologs.common.FieldNameCanoniser;
import org.processmining.redologs.common.GraphEdge;
import org.processmining.redologs.common.GraphNode;
import org.processmining.redologs.common.LogTraceSplitter;
import org.processmining.redologs.common.RelationResult;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.config.Config;
import org.processmining.redologs.config.DatabaseConnectionData;
import org.processmining.redologs.oracle.OracleRelationsExplorer;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;

import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JButton;

import java.awt.GridBagConstraints;

import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class RedoLogInspector {

	private JFrame frame;
	private Graph<GraphNode, GraphEdge> graph;
	private VisualizationViewer<GraphNode, GraphEdge> vv;
	private GridBagConstraints gbc_vv;
	private JTable table;
	private JTable table_logs;
	private JTable table_tables;
	private JComboBox<DatabaseConnectionData> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo lf: UIManager.getInstalledLookAndFeels()) {
				if (lf.getName().equals("Nimbus")) {
					UIManager.setLookAndFeel(lf.getClassName());
				}
			}
		} catch (UnsupportedLookAndFeelException e) {
			// handle exceptiongraphPanel2 
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		Config.getInstance();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedoLogInspector window = new RedoLogInspector();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RedoLogInspector() {
		initialize();
	}

	private File getFileFromSelector() {
		int selectedRow = table_logs.getSelectedRow();
		if (selectedRow >= 0) {
			Object file = table_logs.getModel().getValueAt(selectedRow, 0);
			if (file instanceof File) {
				return (File) file;
			}
		}
		return null;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(RedoLogInspector.class.getResource("/org/processminig/redologs/resources/200px-Icon-inspector.svg.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem fileConnManagerMI = new JMenuItem("Connection Manager");
		fileConnManagerMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionManager manager = new ConnectionManager();
				manager.setVisible(true);
				refreshConnectionsComboBox();
			}
		});
		mnNewMenu.add(fileConnManagerMI);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog.getInstance().setLocationRelativeTo(RedoLogInspector.this.frame);
				AboutDialog.getInstance().setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		final JInternalFrame infrMetrics = new JInternalFrame("Metrics");
		infrMetrics.setClosable(true);
		infrMetrics.setResizable(true);
		infrMetrics.setMaximizable(true);
		infrMetrics.setIconifiable(true);
		infrMetrics.setBounds(113, 41, 310, 121);
		desktopPane.add(infrMetrics);
		final String[] metricsTableColumnNames = new String[] {"Attribute","Mean","Amount","Min","Max","Std deviation"};
		table = new JTable(new String[0][0] ,metricsTableColumnNames);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        
		infrMetrics.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		infrMetrics.getContentPane().add(panel_2, BorderLayout.NORTH);
		
		final JProgressBar progressBar_metrics = new JProgressBar();
		panel_2.add(progressBar_metrics);
		
		JButton btnCalculateMetrics = new JButton("Calculate");
		btnCalculateMetrics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				final File logFile = getFileFromSelector();
				
				Thread metricsThread = new Thread(new Runnable() {
									
					@Override
					public void run() {
						progressBar_metrics.setIndeterminate(true);
						OracleRelationsExplorer explorer = new OracleRelationsExplorer(getSelectedConnection(),getSelectedTables());
						if (explorer.connect()) {
							RelationResult result = explorer
									.generateRelationsGraphFromForeignKeys(true);

							// explorer.showGraph(result.graph,"Relations from Foreign Keys");

							explorer.disconnect();

							FieldNameCanoniser canoniser = new FieldNameCanoniser();
							canoniser.setRelations(result.relations);

							if (logFile != null) {

								infrMetrics.setTitle("Metrics: "+logFile.getName());
								
								final String[][] metrics = LogTraceSplitter
										.computeMetrics(logFile, true, true,
												canoniser);

								table.setModel(new DefaultTableModel(metrics,
										metricsTableColumnNames) {

									/**
								 * 
								 */
									private static final long serialVersionUID = 4497128118537468593L;

									@Override
									public boolean isCellEditable(int row,
											int column) {
										// TODO Auto-generated method stub
										return false;
									}

								});
							} else {

							}
						}
						
						progressBar_metrics.setIndeterminate(false);
					}
				});
				
				metricsThread.start();
				
			}
		});
		panel_2.add(btnCalculateMetrics);
		final JPanel graphPanel = new JPanel();
		graphPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		JInternalFrame infrRelationsGraph = new JInternalFrame("Relations Graph");
		infrRelationsGraph.setBounds(60, 101, 225, 132);
		desktopPane.add(infrRelationsGraph);
		infrRelationsGraph.setClosable(true);
		infrRelationsGraph.setResizable(true);
		infrRelationsGraph.setMaximizable(true);
		infrRelationsGraph.setIconifiable(true);
		infrRelationsGraph.getContentPane().setLayout(new BorderLayout(0, 0));
		infrRelationsGraph.setVisible(true);
		final JPanel panel = new JPanel();
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		infrRelationsGraph.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		final JProgressBar progressBar = new JProgressBar();
		panel.add(progressBar);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		JButton btnReload = new JButton("Reload");
		panel_1.add(btnReload);
		btnReload.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReload.setVerticalAlignment(SwingConstants.TOP);
		
		JButton btnRepaint = new JButton("Reset");
		btnRepaint.setVerticalAlignment(SwingConstants.TOP);
		btnRepaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setScale(1.0, 1.0, vv.getCenter());
				vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setScale(1.0, 1.0, vv.getCenter());
			}
		});
		panel_1.add(btnRepaint);
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				graphPanel.removeAll();
				
				Thread relationsThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						progressBar.setIndeterminate(true);
						//explorer = new OracleRelationsExplorer();
						
						OracleRelationsExplorer explorer = new OracleRelationsExplorer(getSelectedConnection(),getSelectedTables());
						if (explorer.connect()) {
							graph = explorer.generateRelationsGraphPKAndFK();
							
							vv = explorer.getViewer(graph,"Relations from Foreign and Primary Keys");
							
							gbc_vv = new GridBagConstraints();
							gbc_vv.fill = GridBagConstraints.BOTH;
							gbc_vv.gridx = 0;
							gbc_vv.gridy = 0;
							
							vv.setSize(graphPanel.getSize());
							
							GraphZoomScrollPane graphPanel2 = new GraphZoomScrollPane(vv);
							graphPanel.add(graphPanel2, gbc_vv);
							graphPanel.revalidate();
							
							explorer.disconnect();
							
						} else {
							System.err.println("ERROR: connection failed");
						}
						
						progressBar.setIndeterminate(false);
					}
				});
				
				relationsThread.start();
			}
		});
		infrRelationsGraph.getContentPane().add(graphPanel, BorderLayout.CENTER);
		graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
		
		final JInternalFrame infrLogsList = new JInternalFrame("Logs list");
		infrLogsList.setClosable(true);
		infrLogsList.setResizable(true);
		infrLogsList.setMaximizable(true);
		infrLogsList.setIconifiable(true);
		infrLogsList.setBounds(113, 41, 310, 121);
		desktopPane.add(infrLogsList);
		
		final String[] logsTableColumnNames = new String[] {"Path","Name","Size"};
		//table_2 = new JTable(new String[0][0] ,logsTableColumnNames);
		final DefaultTableModel model_table_2 = new DefaultTableModel(new Object[0][0] ,logsTableColumnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7762648250691096226L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_logs = new JTable(model_table_2);
		table_logs.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_2 = new JScrollPane(table_logs);
		infrLogsList.getContentPane().add(scrollPane_2, BorderLayout.CENTER);
		
		
		JPanel panel_4 = new JPanel();
		infrLogsList.getContentPane().add(panel_4, BorderLayout.NORTH);
		
		JButton btnAddLog = new JButton("Add log");
		final JFileChooser fc = new JFileChooser();
		btnAddLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(infrLogsList);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            model_table_2.addRow(new Object[] {file,file.getName(),String.valueOf(file.length())});
		        }
			}
		});
		panel_4.add(btnAddLog);
		
		/**/
		final JInternalFrame infrTablesList = new JInternalFrame("Tables list");
		infrTablesList.setClosable(true);
		infrTablesList.setResizable(true);
		infrTablesList.setMaximizable(true);
		infrTablesList.setIconifiable(true);
		infrTablesList.setBounds(113, 41, 310, 121);
		desktopPane.add(infrTablesList);
		
		final String[] tablesTableColumnNames = new String[] {"Name","Database Name","Table Name"};
		//table_2 = new JTable(new String[0][0] ,logsTableColumnNames);
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
		table_tables.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_3 = new JScrollPane(table_tables);
		infrTablesList.getContentPane().add(scrollPane_3, BorderLayout.CENTER);
		
		
		JPanel panel_5 = new JPanel();
		infrTablesList.getContentPane().add(panel_5, BorderLayout.NORTH);
		
		JButton btnObtainTables = new JButton("Obtain tables");
		btnObtainTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TableInfo> tables = OracleRelationsExplorer.getTables(getSelectedConnection());
				for (TableInfo t: tables) {
					model_tables.addRow(new Object[] {t,t.db,t.name});
				}
			}
		});
		panel_5.add(btnObtainTables);
		/**/
		
		JPanel panel_StatusBar = new JPanel();
		frame.getContentPane().add(panel_StatusBar, BorderLayout.SOUTH);
		panel_StatusBar.setLayout(new BoxLayout(panel_StatusBar, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Connection: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_StatusBar.add(lblNewLabel);
		
		comboBox = new JComboBox<DatabaseConnectionData>();
		refreshConnectionsComboBox();
		
		comboBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_StatusBar.add(comboBox);
		infrLogsList.setVisible(true);
		infrMetrics.setVisible(true);
		infrTablesList.setVisible(true);
	}
	
	private void refreshConnectionsComboBox() {
		Vector<DatabaseConnectionData> connectionsVector = new Vector<>(Config.getInstance().getConnections());
		comboBox.setModel(new DefaultComboBoxModel<>(connectionsVector));
	}
	
	public DatabaseConnectionData getSelectedConnection() {
		return (DatabaseConnectionData) comboBox.getSelectedItem();
	}
	
	public List<TableInfo> getSelectedTables() {
		int[] selected = table_tables.getSelectedRows();
		Vector<TableInfo> selectedTables = new Vector<>();
		for (int i: selected) {
			selectedTables.add((TableInfo) table_tables.getModel().getValueAt(i, 0));
		}
		return selectedTables;
	}
}
