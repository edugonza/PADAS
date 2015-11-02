package org.processmining.database.redologs.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import org.processmining.database.redologs.ui.components.CustomInternalFrame;
import org.processmining.database.redologs.ui.graphs.VertexDisplayPredicate;
import org.processmining.database.redologs.common.DataModel;
import org.processmining.database.redologs.common.GraphEdge;
import org.processmining.database.redologs.common.GraphNode;
import org.processmining.database.redologs.oracle.OracleRelationsExplorer;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;

public class FrameRelationsGraph extends CustomInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3381778662806389348L;
	private Graph<GraphNode, GraphEdge> graph;
	private VisualizationViewer<GraphNode, GraphEdge> vv;
	private GridBagConstraints gbc_vv;
	private JProgressBar progressBar;
	private JPanel graphPanel;
	private boolean traceIdSelector = false;
	private List<GraphNode> listSelectedNodes;
	private Layout<GraphNode, GraphEdge> layout;
	
	public FrameRelationsGraph() {
		this(false,null);
	}
	
	public FrameRelationsGraph(boolean traceIdSelector, final FrameLogSplitter logSplitter) {
		super("Relations Graph");
		this.traceIdSelector = traceIdSelector;
		graphPanel = new JPanel();
		graphPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this.setBounds(404, 267, 534, 441);
		this.setClosable(true);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		final JPanel panel = new JPanel();
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		progressBar = new JProgressBar();
		panel.add(progressBar);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		JButton btnReload = new JButton("Reload");
		panel_2.add(btnReload);
		btnReload.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReload.setVerticalAlignment(SwingConstants.TOP);

		JButton btnRepaint = new JButton("Reset");
		panel_2.add(btnRepaint);
		btnRepaint.setVerticalAlignment(SwingConstants.TOP);

		JButton btnShowExtraFields = new JButton("Toggle Extra fields");
		panel_2.add(btnShowExtraFields);
		
		JButton btnLock = new JButton("Lock");
		btnLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((SpringLayout2) layout).lock(true);
			}
		});
		panel_2.add(btnLock);
		btnShowExtraFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (vv != null) {
					VertexDisplayPredicate<GraphNode, GraphEdge> vdpred = (VertexDisplayPredicate<GraphNode, GraphEdge>) vv
							.getRenderContext().getVertexIncludePredicate();
					vdpred.filterExtraFields(!vdpred.isFilter_small());
					vv.repaint();
				}
			}
		});
		btnRepaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vv.getRenderContext().getMultiLayerTransformer()
						.getTransformer(Layer.VIEW)
						.setScale(1.0, 1.0, vv.getCenter());
				vv.getRenderContext().getMultiLayerTransformer()
						.getTransformer(Layer.LAYOUT)
						.setScale(1.0, 1.0, vv.getCenter());
			}
		});

		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataModel model = FrameDataModels.getInstance()
						.getSelectedDataModel();
				reloadGraph(model);
			}
		});

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSaveTraceidHierarchy = new JButton("Save TraceId Hierarchy");
		btnSaveTraceidHierarchy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listSelectedNodes != null) {
					if (logSplitter != null) {
						logSplitter.setTraceIdHierarchy(listSelectedNodes);
					}
				}
			}
		});
		
		JButton btnHighlightTraceidHierarchy = new JButton("Highlight TraceId Hierarchy");
		btnHighlightTraceidHierarchy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickTraceIdHierarchy(logSplitter.getTraceIdHierarchy());
			}
		});
		
		JButton btnToogleFilterTraceidHierarchy = new JButton("Show only TraceId Hierarchy");
		btnToogleFilterTraceidHierarchy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTraceIdHierarchy(logSplitter.getTraceIdHierarchy());
			}
		});
		
		panel_1.add(btnHighlightTraceidHierarchy);
		panel_1.add(btnSaveTraceidHierarchy);
		panel_1.add(btnToogleFilterTraceidHierarchy);
		
		if (traceIdSelector) {
			btnSaveTraceidHierarchy.setVisible(true);
			btnHighlightTraceidHierarchy.setVisible(true);
			btnToogleFilterTraceidHierarchy.setVisible(true);
		} else {
			btnSaveTraceidHierarchy.setVisible(false);
			btnHighlightTraceidHierarchy.setVisible(false);
			btnToogleFilterTraceidHierarchy.setVisible(false);
		}
		this.getContentPane()
				.add(graphPanel, BorderLayout.CENTER);
		GridBagLayout gbl_graphPanel = new GridBagLayout();
		gbl_graphPanel.columnWidths = new int[]{0};
		gbl_graphPanel.rowHeights = new int[]{0};
		gbl_graphPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_graphPanel.rowWeights = new double[]{Double.MIN_VALUE};
		graphPanel.setLayout(gbl_graphPanel);
	}
	
	public void reloadGraph(final DataModel model) {

		graphPanel.removeAll();
		
		if (layout != null) {
			((SpringLayout2<?, ?>) layout).lock(true); 
		}

		Thread relationsThread = new Thread(new Runnable() {

			@Override
			public void run() {
				progressBar.setIndeterminate(true);
				// explorer = new OracleRelationsExplorer();

				if (model != null) {
					String title = "Relations Graph - Data Model: "+model.getName();
					
					FrameRelationsGraph.this.setTitle(title);
					
					graph = OracleRelationsExplorer.generateRelationsGraphPKAndFK(model);

					layout = new SpringLayout2<>(graph);
					vv = OracleRelationsExplorer.getViewer(graph, layout,
						"Relations from Foreign and Primary Keys");

					//vv.getGraphLayout().setSize(new Dimension(1600,900));
					vv.getGraphLayout().setSize(graphPanel.getSize());
					gbc_vv = new GridBagConstraints();
					gbc_vv.fill = GridBagConstraints.BOTH;
					gbc_vv.gridx = 0;
					gbc_vv.gridy = 0;

					//vv.setSize(graphPanel.getSize());
					//vv.setSize(new Dimension(50, 50));

					if (traceIdSelector) {
					    
						listSelectedNodes = new Vector<>();
						
						final PickedState<GraphNode> pickedState = vv.getPickedVertexState();
						 
						// Attach the listener that will print when the vertices selection changes.
						pickedState.addItemListener(new ItemListener() {
						 
						    @Override
						    public void itemStateChanged(ItemEvent e) {
						    Object subject = e.getItem();
						        // The graph uses Integers for vertices.
						        if (subject instanceof GraphNode) {
						            GraphNode vertex = (GraphNode) subject;
						            if (pickedState.isPicked(vertex)) {
//						                System.out.println("Vertex " + vertex
//						                    + " is now selected");
						                listSelectedNodes.add(vertex);
						            } else {
//						                System.out.println("Vertex " + vertex
//						                    + " no longer selected");
						                listSelectedNodes.remove(vertex);
						            }
						        }
						    }
						});
					}
					
					GraphZoomScrollPane graphPanel2 = new GraphZoomScrollPane(
							vv);
					graphPanel.add(graphPanel2, gbc_vv);
					graphPanel.revalidate();
					
				}
				progressBar.setIndeterminate(false);
			}
		});

		relationsThread.start();
	}
	
	public void pickTraceIdHierarchy(List<GraphNode> listNodes) {
		PickedState<GraphNode> pickedState = vv.getPickedVertexState();
		pickedState.clear();
		for (GraphNode g: listNodes) {
			pickedState.pick(g, true);
			g.traceIdSet = true;
		}
	}
	
	public void filterTraceIdHierarchy(List<GraphNode> listNodes) {
		if (vv != null) {
			VertexDisplayPredicate<GraphNode, GraphEdge> vdpred = (VertexDisplayPredicate<GraphNode, GraphEdge>) vv
					.getRenderContext().getVertexIncludePredicate();
			vdpred.filterTraceId(!vdpred.isFilter_traceId());
			vv.repaint();
		}
	}
}
