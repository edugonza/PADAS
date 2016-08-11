package org.processmining.database.redologs.ui;

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

import org.processmining.database.redologs.ui.components.BackgroundImage;
import org.processmining.openslex.SLEXDMDataModel;
import org.processmining.openslex.SLEXDMDataModelResultSet;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXEventCollectionResultSet;
import org.processmining.openslex.SLEXPerspective;
import org.processmining.openslex.SLEXPerspectiveResultSet;
import org.processmining.openslex.SLEXStorage;
import org.processmining.openslex.SLEXStorageCollection;
import org.processmining.openslex.SLEXStorageDataModel;
import org.processmining.openslex.SLEXStorageImpl;
import org.processmining.openslex.SLEXStoragePerspective;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModelImpl;
import org.processmining.database.redologs.common.Constants;
import org.processmining.database.redologs.common.DataModel;
import org.processmining.database.redologs.common.SLEXDataModelExportImport;
import org.processmining.database.redologs.config.Config;

import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class PadasMainUI {

	private static PadasMainUI _instance;
	private JFrame frmRedologInspector;
	private JDesktopPane desktopPane;
	
	List<SLEXStorageCollection> colStorages = new Vector<>();
	List<SLEXStoragePerspective> perStorages = new Vector<>();
	List<SLEXStorageDataModel> dmStorages = new Vector<>();
	List<SLEXMMStorageMetaModel> mmStorages = new Vector<>();
	
	public void closeStorages() {
		for (SLEXStorageCollection st: colStorages) {
			st.disconnect();
		}
		for (SLEXStoragePerspective st: perStorages) {
			st.disconnect();
		}
		for (SLEXStorageDataModel st: dmStorages) {
			st.disconnect();
		}
		for (SLEXMMStorageMetaModel st: mmStorages) {
			st.disconnect();
		}
	}
	
	//private SLEXStorage storage = null;
	
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
					AboutDialog about = new AboutDialog();
					about.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PadasMainUI.getInstance().frmRedologInspector.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public static PadasMainUI getInstance() {
		if (_instance == null) {
			_instance = new PadasMainUI();
		}
		return _instance;
	}
	
	public void addFrame(JInternalFrame frame) {
		desktopPane.add(frame);
	}
	
	/**
	 * Create the application.
	 */
	private PadasMainUI() {
		initialize();
		
		try {
			String dir = "data";
			String lib = "lib";
			File dataDir = new File(dir);
			File libDir = new File(lib);
			if (!dataDir.exists()) {
				dataDir.mkdirs();
			}
			if (!libDir.exists()) {
				libDir.mkdir();
			}
			File[] filesInData = dataDir.listFiles();
			for (File f: filesInData) {
				if (!f.isDirectory()) {
					if (f.getName().endsWith(SLEXStorage.COLLECTION_FILE_EXTENSION)) {
						SLEXStorageCollection st = new SLEXStorageImpl(dir,f.getName(),SLEXStorage.TYPE_COLLECTION);
						colStorages.add(st);
					} else if (f.getName().endsWith(SLEXStorage.DATAMODEL_FILE_EXTENSION)) {
						SLEXStorageDataModel st = new SLEXStorageImpl(dir,f.getName(),SLEXStorage.TYPE_DATAMODEL);
						dmStorages.add(st);
					} else if (f.getName().endsWith(SLEXStorage.PERSPECTIVE_FILE_EXTENSION)) {
						SLEXStoragePerspective st = new SLEXStorageImpl(dir,f.getName(),SLEXStorage.TYPE_PERSPECTIVE);
						perStorages.add(st);
					} else if (f.getName().endsWith(SLEXMMStorageMetaModel.METAMODEL_FILE_EXTENSION)) {
						SLEXMMStorageMetaModel st = new SLEXMMStorageMetaModelImpl(dir,f.getName());
						mmStorages.add(st);
					}
				}
			}
			
			FrameTables.getInstance();			
			
			for (SLEXStorageCollection st: colStorages) {
				SLEXEventCollectionResultSet ecrset = st.getEventCollections();
				SLEXEventCollection ec = null;
				while ((ec = ecrset.getNext()) != null) {
					FrameEventCollections.getInstance().addEventCollection(ec);
				}
				ecrset.close();
			}
			
			for (SLEXStoragePerspective st: perStorages) {
				SLEXPerspectiveResultSet prset = st.getPerspectives();
				SLEXPerspective p = null;
				while ((p = prset.getNext()) != null) {
					FramePerspectives.getInstance().addPerspective(p);
				}
				prset.close();
			}
			
			for (SLEXStorageDataModel st: dmStorages) {
				SLEXDMDataModelResultSet dmrset = st.getDataModels();
				SLEXDMDataModel sdm = null;
				while ((sdm = dmrset.getNext()) != null) {
					DataModel dm = SLEXDataModelExportImport.loadDataModelFromSLEXDM(sdm);
					FrameDataModels.getInstance().addDataModel(dm);
				}
				dmrset.close();
			}
			
			for (SLEXMMStorageMetaModel st: mmStorages) {
				FrameMetaModels.getInstance().addMetaModel(st);
			}
			
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
		frmRedologInspector.setTitle(Constants.APP_NAME+" v"+Constants.VERSION);
		//frmRedologInspector.setIconImage(Toolkit.getDefaultToolkit().getImage(RedoLogInspector.class.getResource("/org/processmining/redologs/resources/r.png")));
		frmRedologInspector.setIconImage(Toolkit.getDefaultToolkit().getImage(PadasMainUI.class.getResource("/org/processmining/database/resources/letters/p.png")));
		frmRedologInspector.setBounds(100, 100, 1111, 829);
		frmRedologInspector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRedologInspector.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmRedologInspector.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem fileConnManagerMI = new JMenuItem("Connection Manager");
		fileConnManagerMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionManager manager = new ConnectionManager((JComponent) frmRedologInspector.getContentPane());
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
					closeStorages();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
//		JMenu mnNewMenu_1 = new JMenu("Edit");
//		menuBar.add(mnNewMenu_1);
//		
//		JMenu mnView = new JMenu("View");
//		menuBar.add(mnView);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
//		JMenuItem mntmMetrics = new JMenuItem("Metrics");
//		mntmMetrics.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				FrameMetrics metrics = new FrameMetrics();
//				desktopPane.add(metrics);
//				metrics.setVisible(true);
//				metrics.setFocusable(true);
//			}
//		});
//		mnTools.add(mntmMetrics);
		
//		JMenuItem mntmRelationsGraph = new JMenuItem("Relations Graph");
//		mntmRelationsGraph.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				FrameRelationsGraph relations = new FrameRelationsGraph();
//				desktopPane.add(relations);
//				relations.setVisible(true);
//				relations.setFocusable(true);
//			}
//		});
//		mnTools.add(mntmRelationsGraph);
		
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
		
//		JMenuItem mntmPersInteract = new JMenuItem("Perspectives Interaction Miner");
//		mntmPersInteract.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				FramePerspectivesInteraction pIntMnr = new FramePerspectivesInteraction();
//				desktopPane.add(pIntMnr);
//				pIntMnr.setVisible(true);
//				pIntMnr.setFocusable(true);
//			}
//		});
//		mnTools.add(mntmPersInteract);
		
		JMenuItem mntmMetaModel = new JMenuItem("MetaModel Computer");
		mntmMetaModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameMetaModelGenerator pMetaModel = new FrameMetaModelGenerator();
				desktopPane.add(pMetaModel);
				pMetaModel.setVisible(true);
				pMetaModel.setFocusable(true);
			}
		});
		mnTools.add(mntmMetaModel);
		
//		JMenuItem mntmPerspectiveDetector = new JMenuItem("Perspectives Detector");
//		mntmPerspectiveDetector.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				FramePerspectivesDetector pPersDetec = new FramePerspectivesDetector();
//				desktopPane.add(pPersDetec);
//				pPersDetec.setVisible(true);
//				pPersDetec.setFocusable(true);
//			}
//		});
//		mnTools.add(mntmPerspectiveDetector);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog about = new AboutDialog();
				about.setLocationRelativeTo(PadasMainUI.this.frmRedologInspector);
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
		
		desktopPane.add(FrameMetaModels.getInstance());
		Rectangle rMetaModels = FrameMetaModels.getInstance().getBounds();
		rMetaModels.height += 200;
		rMetaModels.x = rDataModels.x + rDataModels.width + 30;
		rMetaModels.y = rDataModels.y;
		FrameMetaModels.getInstance().setBounds(rMetaModels);
		FrameMetaModels.getInstance().setVisible(true);
		
		JPanel panel_StatusBar = new JPanel();
		frmRedologInspector.getContentPane().add(panel_StatusBar, BorderLayout.SOUTH);
		panel_StatusBar.setLayout(new BoxLayout(panel_StatusBar, BoxLayout.X_AXIS));
		
		
	}
}
