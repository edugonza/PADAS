package org.processmining.redologs.ui;

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

import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.GraphEdge;
import org.processmining.redologs.common.GraphNode;
import org.processmining.redologs.oracle.OracleRelationsExplorer;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;

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
	
	public FrameRelationsGraph() {
		this(false,null);
	}
	
	public FrameRelationsGraph(boolean traceIdSelector, final FrameLogSplitter logSplitter) {
		super("Relations Graph");
		this.traceIdSelector = traceIdSelector;
		graphPanel = new JPanel();
		graphPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this.setBounds(404, 267, 542, 421);
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
				vv.getRenderContext().getMultiLayerTransformer()
						.getTransformer(Layer.VIEW)
						.setScale(1.0, 1.0, vv.getCenter());
				vv.getRenderContext().getMultiLayerTransformer()
						.getTransformer(Layer.LAYOUT)
						.setScale(1.0, 1.0, vv.getCenter());
			}
		});
		panel_1.add(btnRepaint);
		
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
		
		JButton btnShowExtraFields = new JButton("Toggle Extra fields");
		btnShowExtraFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VertexDisplayPredicate<GraphNode, GraphEdge> vdpred =
						(VertexDisplayPredicate<GraphNode, GraphEdge>) vv.getRenderContext().getVertexIncludePredicate();
				vdpred.filterSmall(!vdpred.filter_small);
				vv.repaint();
			}
		});
		panel_1.add(btnShowExtraFields);
		panel_1.add(btnSaveTraceidHierarchy);
		
		JButton btnHighlightTraceidHierarchy = new JButton("Highlight TraceId Hierarchy");
		btnHighlightTraceidHierarchy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickTraceIdHierarchy(logSplitter.getTraceIdHierarchy());
			}
		});
		panel_1.add(btnHighlightTraceidHierarchy);
		
		if (traceIdSelector) {
			btnSaveTraceidHierarchy.setVisible(true);
			btnHighlightTraceidHierarchy.setVisible(true);
		} else {
			btnSaveTraceidHierarchy.setVisible(false);
			btnHighlightTraceidHierarchy.setVisible(false);
		}
		
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataModel model = FrameDataModels.getInstance().getSelectedDataModel();
				reloadGraph(model);
			}
		});
		this.getContentPane()
				.add(graphPanel, BorderLayout.CENTER);
		graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
	}
	
	public void reloadGraph(final DataModel model) {

		graphPanel.removeAll();

		Thread relationsThread = new Thread(new Runnable() {

			@Override
			public void run() {
				progressBar.setIndeterminate(true);
				// explorer = new OracleRelationsExplorer();

				if (model != null) {
					String title = "Relations Graph - Data Model: "+model.getName();
					
					FrameRelationsGraph.this.setTitle(title);
					
					graph = OracleRelationsExplorer.generateRelationsGraphPKAndFK(model);

					vv = OracleRelationsExplorer.getViewer(graph,
						"Relations from Foreign and Primary Keys");

					gbc_vv = new GridBagConstraints();
					gbc_vv.fill = GridBagConstraints.BOTH;
					gbc_vv.gridx = 0;
					gbc_vv.gridy = 0;

					vv.setSize(graphPanel.getSize());

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
		}
	}
}
