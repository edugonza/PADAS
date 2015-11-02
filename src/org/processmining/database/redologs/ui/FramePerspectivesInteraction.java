package org.processmining.database.redologs.ui;

import org.processmining.database.redologs.ui.components.CustomInternalFrame;
import org.processmining.database.redologs.ui.components.DataModelList;
import org.processmining.database.redologs.ui.components.DataModelTree;
import org.processmining.database.redologs.ui.components.InfoDialog;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXPerspective;
import org.processmining.database.redologs.common.Column;
import org.processmining.database.redologs.common.DataModel;
import org.processmining.database.redologs.common.EventAttributeColumn;
import org.processmining.database.redologs.common.GraphNode;
import org.processmining.database.redologs.common.Key;
import org.processmining.database.redologs.common.LogTraceSplitter;
import org.processmining.database.redologs.common.ProgressHandler;
import org.processmining.database.redologs.common.SLEXAttributeMapper;
import org.processmining.database.redologs.common.TableInfo;
import org.processmining.database.redologs.common.TraceIDPattern;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Rectangle;

public class FramePerspectivesInteraction extends CustomInternalFrame {

	private DataModelTree tree = new DataModelTree();
	private DataModel model = null;
	private DataModelList tpListA = null;
	private DataModelList tpListB = null;
	private DataModelList tpListCombined = null;
	private DataModelList orderList = null;
	private JTextField txtTparootfield;
	private JTextField txtTpbrootfield;
	private Object rootElementA;
	private Object rootElementB;
	
	public FramePerspectivesInteraction() {
		super("Perspectives Interaction Miner");
		setBounds(new Rectangle(100, 100, 840, 500));
		setMinimumSize(new Dimension(840, 500));
		//setBounds(100, 100, 638, 471);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.NORTH);
		
		JButton btnLoadDataModel = new JButton("Load Data Model");
		btnLoadDataModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataModel dm = FrameDataModels.getInstance().getSelectedDataModel();
				if (dm != null) {
					FramePerspectivesInteraction.this.model = dm;
					tree.setDataModel(dm);
				} else {
					InfoDialog inf = new InfoDialog("No data model has been selected", FramePerspectivesInteraction.this);
					inf.showDialog();
				}
			}
		});
		panel_2.add(btnLoadDataModel);
		
		JButton btnAutocompleteCommon = new JButton("Autocomplete Common");
		btnAutocompleteCommon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model != null) {
					
					tpListCombined.clear();
					
					HashSet<GraphNode> combinedElements = new HashSet<>();
					
					for (GraphNode n: tpListA.getNodesList()) {
						combinedElements.add(n);
					}
					
					for (GraphNode n: tpListB.getNodesList()) {
						combinedElements.add(n);
					}
					
					for (GraphNode n: combinedElements) {
						tpListCombined.addElement(n);
					}
					
				} else {
					InfoDialog inf = new InfoDialog("No data model has been selected", FramePerspectivesInteraction.this);
					inf.showDialog();
				}
			}
		});
		panel_2.add(btnAutocompleteCommon);
		
		JButton btnComputedfg = new JButton("Compute DFG");
		btnComputedfg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final TraceIDPattern tpA = tpListA.getTraceIDPattern(model);
				
				final TraceIDPattern tpB = tpListB.getTraceIDPattern(model);
				
				if (rootElementA instanceof Column) {
					tpA.setRoot((Column) rootElementA);
				} else if (rootElementA instanceof Key) {
					tpA.setRoot((Key) rootElementA);
				}
				
				if (rootElementB instanceof Column) {
					tpB.setRoot((Column) rootElementB);
				} else if (rootElementB instanceof Key) {
					tpB.setRoot((Key) rootElementB);
				}
				
				final List<Column> sortFields = new Vector<>();
				List<Object> orderNamesSelected = orderList.getElementsList(); 
				for (int i = 0; i < orderNamesSelected.size(); i++) {
					if (orderNamesSelected.get(i) instanceof EventAttributeColumn) {
						sortFields.add(((EventAttributeColumn) orderNamesSelected.get(i)).c);
					} else if (orderNamesSelected.get(i) instanceof Column) {
						sortFields.add((Column) orderNamesSelected.get(i));
					}
				}
				
				final SLEXEventCollection evCol = FrameEventCollections.getInstance().getEventCollectionFromSelector();
				
				List<TableInfo> tables = new Vector<>();
				tables.addAll(model.getTables());
				tables.add(tree.getCommonTable());
				
				final SLEXAttributeMapper mapper = LogTraceSplitter.computeMapping(evCol, tables);
				
				final ProgressHandler phandler = new ProgressHandler() {
					
					@Override
					public void refreshValue(String key, String value) {
						
					}
				};
				
				Thread logSplitThread = new Thread(new Runnable() {
					@Override
					public void run() {
//						progressBar.setIndeterminate(true);
//						progressBar.setStringPainted(true);
//						progressBar.setString("Splitting...");
						
						SLEXPerspective perspectiveA = LogTraceSplitter.splitLog(null, model, evCol, mapper, tpA, sortFields, null,null, phandler);
						
						FramePerspectives.getInstance().addPerspective(perspectiveA);
						
						SLEXPerspective perspectiveB = LogTraceSplitter.splitLog(null, model, evCol, mapper, tpB, sortFields, null,null, phandler);
												
						FramePerspectives.getInstance().addPerspective(perspectiveB);
						
						LogTraceSplitter.computeInteractionMatrix(evCol,perspectiveA,perspectiveB,tpListCombined.getTraceIDPattern(model),sortFields,mapper);
						
//						progressBar.setIndeterminate(false);
//						progressBar.setString("Log Split");
					}
				},"LogSplitThread");
				
				logSplitThread.start();
				
			}
		});
		panel_2.add(btnComputedfg);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane(tree);
		scrollPane_2.setPreferredSize(new Dimension(270, 200));
		panel.add(scrollPane_2, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(560, 300));
		panel_4.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Trace ID Pattern A");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_7.add(lblNewLabel, BorderLayout.NORTH);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		tpListA = new DataModelList();
		
		JScrollPane scrollPane = new JScrollPane(tpListA);
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_7.add(scrollPane);
		
		JPanel panel_6 = new JPanel();
		panel_7.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		JButton btnSetrootA = new JButton("Root");
		btnSetrootA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rootElementA = tpListA.getSelectedValue();
				txtTparootfield.setText(rootElementA.toString());
			}
		});
		panel_6.add(btnSetrootA);
		
		txtTparootfield = new JTextField();
		txtTparootfield.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_6.add(txtTparootfield);
		txtTparootfield.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Trace ID Pattern B");
		panel_8.add(lblNewLabel_1, BorderLayout.NORTH);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		tpListB = new DataModelList();
		
		JScrollPane scrollPane_1 = new JScrollPane(tpListB);
		panel_8.add(scrollPane_1);
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
		
		JButton btnSetrootB = new JButton("Root");
		btnSetrootB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rootElementB = tpListB.getSelectedValue();
				txtTpbrootfield.setText(rootElementB.toString());
			}
		});
		panel_9.add(btnSetrootB);
		
		txtTpbrootfield = new JTextField();
		txtTpbrootfield.setColumns(10);
		txtTpbrootfield.setAlignmentX(0.0f);
		panel_9.add(txtTpbrootfield);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_5.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JLabel lblOrder = new JLabel("Order");
		panel_11.add(lblOrder, BorderLayout.NORTH);
		lblOrder.setHorizontalAlignment(SwingConstants.CENTER);
		
		orderList = new DataModelList();
		
		JScrollPane scrollPane_4 = new JScrollPane(orderList);
		panel_11.add(scrollPane_4, BorderLayout.CENTER);
		
		JPanel panel_12 = new JPanel();
		panel_5.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCommon = new JLabel("Trace ID Pattern Combined");
		panel_12.add(lblCommon, BorderLayout.NORTH);
		lblCommon.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommon.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		tpListCombined = new DataModelList();
		JScrollPane scrollPane_3 = new JScrollPane(tpListCombined);
		panel_12.add(scrollPane_3, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.SOUTH);
		
	}
}
