package org.processmining.redologs.ui;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionEvent;

import org.processmining.openslex.SLEXStorage;
import org.processmining.redologs.common.Constants;
import org.processmining.redologs.config.Config;
import org.processmining.redologs.config.DatabaseConnectionData;

import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;

public class RedoLogInspector {

	private static RedoLogInspector _instance;
	private JFrame frmRedologInspector;
	private JDesktopPane desktopPane;
	
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
//					SplashWindow splash = new SplashWindow(
//							//SplashWindow.class.getResource("/org/processmining/redologs/resources/200px-Icon-inspector.svg.png"),
//							//SplashWindow.class.getResource("/org/processmining/redologs/resources/redolog-inspector.png"),
//							SplashWindow.class.getResource("/org/processmining/redologs/resources/r.png"),
//							null, 3000);
//					splash.setAlwaysOnTop(true);
					
					AboutDialog about = new AboutDialog();
					about.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedoLogInspector.getInstance().frmRedologInspector.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public static RedoLogInspector getInstance() {
		if (_instance == null) {
			_instance = new RedoLogInspector();
		}
		return _instance;
	}
	
	public void addFrame(JInternalFrame frame) {
		desktopPane.add(frame);
	}
	
	/**
	 * Create the application.
	 */
	private RedoLogInspector() {
		initialize();
		
		try {
			
			SLEXStorage logStorage = SLEXStorage.getInstance();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRedologInspector = new JFrame();
		frmRedologInspector.setTitle("RedoLog Inspector"+" v"+Constants.VERSION);
		frmRedologInspector.setIconImage(Toolkit.getDefaultToolkit().getImage(RedoLogInspector.class.getResource("/org/processmining/redologs/resources/r.png")));
		frmRedologInspector.setBounds(100, 100, 887, 700);
		frmRedologInspector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRedologInspector.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmRedologInspector.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem fileConnManagerMI = new JMenuItem("Connection Manager");
		fileConnManagerMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionManager manager = new ConnectionManager();
				manager.setVisible(true);
				FrameTables.getInstance().refreshConnectionsComboBox();
			}
		});
		mnNewMenu.add(fileConnManagerMI);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SLEXStorage.getInstance().disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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
		
		JMenuItem mntmMetrics = new JMenuItem("Metrics");
		mntmMetrics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameMetrics metrics = new FrameMetrics();
				desktopPane.add(metrics);
				metrics.setVisible(true);
				metrics.setFocusable(true);
			}
		});
		mnTools.add(mntmMetrics);
		
		JMenuItem mntmRelationsGraph = new JMenuItem("Relations Graph");
		mntmRelationsGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameRelationsGraph relations = new FrameRelationsGraph();
				desktopPane.add(relations);
				relations.setVisible(true);
				relations.setFocusable(true);
			}
		});
		mnTools.add(mntmRelationsGraph);
		
		JMenuItem mntmLogSplitter = new JMenuItem("Log Splitter");
		mntmLogSplitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameLogSplitter logSplitter = new FrameLogSplitter();
				desktopPane.add(logSplitter);
				logSplitter.setVisible(true);
				logSplitter.setFocusable(true);
			}
		});
		mnTools.add(mntmLogSplitter);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog about = new AboutDialog();
				about.setLocationRelativeTo(RedoLogInspector.this.frmRedologInspector);
				about.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		frmRedologInspector.getContentPane().setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		desktopPane.setBorder(new BackgroundImage());
		frmRedologInspector.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		desktopPane.add(FrameTables.getInstance());
		Rectangle rTables = FrameTables.getInstance().getBounds();
		rTables.y = 30;
		rTables.height += 150;
		FrameTables.getInstance().setBounds(rTables);
		FrameTables.getInstance().setVisible(true);
		
		desktopPane.add(FramePerspectives.getInstance());
		Rectangle rPerspectives = FramePerspectives.getInstance().getBounds();
		rPerspectives.x = rTables.x;
		rPerspectives.y = rTables.y + rTables.height + 30;
		rPerspectives.width = rTables.width;
		FramePerspectives.getInstance().setBounds(rPerspectives);
		FramePerspectives.getInstance().setVisible(true);
		
		desktopPane.add(FrameEventCollections.getInstance());
		Rectangle rEvCollections = FrameEventCollections.getInstance().getBounds();
		rEvCollections.height += 200;
		rEvCollections.x = rTables.x + rTables.width + 30;
		rEvCollections.y = rTables.y;
		FrameEventCollections.getInstance().setBounds(rEvCollections);
		FrameEventCollections.getInstance().setVisible(true);
		
		
		desktopPane.add(FrameDataModels.getInstance());
		Rectangle rDataModels = FrameDataModels.getInstance().getBounds();
		rDataModels.x = rEvCollections.x;
		rDataModels.y = rEvCollections.y + rEvCollections.height + 30;
		rDataModels.width = rEvCollections.width;
		FrameDataModels.getInstance().setBounds(rDataModels);
		FrameDataModels.getInstance().setVisible(true);
		
		JPanel panel_StatusBar = new JPanel();
		frmRedologInspector.getContentPane().add(panel_StatusBar, BorderLayout.SOUTH);
		panel_StatusBar.setLayout(new BoxLayout(panel_StatusBar, BoxLayout.X_AXIS));
		
		
	}
}
