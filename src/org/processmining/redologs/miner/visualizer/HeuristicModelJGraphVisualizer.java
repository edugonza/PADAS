package org.processmining.redologs.miner.visualizer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphModel;
import org.processmining.models.connections.GraphLayoutConnection;
import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.ViewSpecificAttributeMap;
import org.processmining.models.graphbased.directed.DirectedGraph;
import org.processmining.models.jgraph.ProMGraphModel;
import org.processmining.models.jgraph.ProMJGraph;
import org.processmining.redologs.miner.DGraph;
import org.processmining.redologs.miner.HeuristicModel;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.mxPartitionLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;

public class HeuristicModelJGraphVisualizer extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3389372475414103740L;
	private HeuristicModel hm;

	public HeuristicModelJGraphVisualizer(HeuristicModel hm) {
		super();
		this.hm = hm;
		createComponent();
	}

	public HeuristicModel getHeuristicModel() {
		return hm;
	}

	private void createComponent() {
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));

		int x = panel.getWidth() / 2;
		int y = panel.getHeight() / 2;

		JLabel lbl = new JLabel("Heuristic Net Visualizer");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl, BorderLayout.NORTH);
		
		HeuristicModelGraph hmg = new HeuristicModelGraph(hm,new AttributeMap(),new AttributeMap(),hm.getSpecialEdges());

		JComponent pgraph = buildJGraph(hmg);

		panel.add(pgraph, BorderLayout.CENTER);

		this.add(panel);
	}

	private JComponent buildJGraph(DirectedGraph<?, ?> causalNet) {

		mxGraph graph = new mxGraph();
		
		Object parent = graph.getDefaultParent();
		graph.setAutoSizeCells(true);

		Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
		// style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.SideToSide);
		// style.put(mxConstants.STYLE_DASHED, true);
		// style.put(mxConstants.STYLE_EDGE, mxConstants.SHAPE_CURVE);
		// style.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ORTHOGONAL);
		// style.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_SIDETOSIDE);
		//style.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE);
		//style.put(mxConstants.STYLE_DASHED, "true");
		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.ElbowConnector);
		
		graph.getModel().beginUpdate();
		try {
			DGraph dgraph = hm.getDependencyGraph();

			HashMap<String, Object> mappingActivityVertex = new HashMap<>();

			for (String activity : dgraph.getActivities()) {
				Object v = graph.insertVertex(parent, null, activity, 0, 0, 0,
						0);
				graph.updateCellSize(v);
				mappingActivityVertex.put(activity, v);
			}

			for (String activity : dgraph.getActivities()) {
				Object p = mappingActivityVertex.get(activity);

				for (String follower : dgraph.getFollowers(activity)) {
					Object f = mappingActivityVertex.get(follower);
					Object e = graph.insertEdge(parent, null, "", p, f);
				}
			}

		} finally {
			graph.getModel().endUpdate();
		}

		// define layout

		// mxCompactTreeLayout layout = new mxCompactTreeLayout(graph, true);
		// mxCompactTreeLayout layout = new mxCompactTreeLayout(graph, false);
		// mxFastOrganicLayout layout = new mxFastOrganicLayout(graph);
		//mxOrganicLayout layout = new mxOrganicLayout(graph);
		//mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
		//layout.setOrientation(SwingConstants.NORTH);
		mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
		 layout.setDisableEdgeStyle(false);
		//layout.setDisableEdgeStyle(false);
		 

		graph.getModel().beginUpdate();
		try {
			layout.execute(graph.getDefaultParent());

		} finally {
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graph.setCellsEditable(false);
		graph.setVertexLabelsMovable(false);
		graph.setEdgeLabelsMovable(false);
		graph.setAllowDanglingEdges(false);
		graph.setDisconnectOnMove(false);
		graph.setCellsDisconnectable(false);
		graph.setCellsBendable(false);

		return graphComponent;		
	}
}
