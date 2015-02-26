package org.processmining.redologs.miner.visualizer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import org.processmining.models.connections.GraphLayoutConnection;
import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.ViewSpecificAttributeMap;
import org.processmining.models.graphbased.directed.DirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraphEdge;
import org.processmining.models.graphbased.directed.DirectedGraphNode;
import org.processmining.models.jgraph.ProMGraphModel;
import org.processmining.models.jgraph.ProMJGraph;
import org.processmining.redologs.miner.HeuristicModel;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import com.sun.java.swing.plaf.gtk.GTKConstants.ArrowType;

public class HeuristicModelProMVisualizer extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3389372475414103740L;
	private HeuristicModel hm;
	
	public HeuristicModelProMVisualizer(HeuristicModel hm) {
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

		AttributeMap defaultAttributeMap = new AttributeMap();
		defaultAttributeMap.put(AttributeMap.EDGEEND, AttributeMap.ArrowType.ARROWTYPE_CLASSIC);
		defaultAttributeMap.put(AttributeMap.AUTOSIZE, true);
		
		AttributeMap specialEdgesAttributeMap = new AttributeMap();
		specialEdgesAttributeMap.put(AttributeMap.EDGEEND, AttributeMap.ArrowType.ARROWTYPE_CLASSIC);
		specialEdgesAttributeMap.put(AttributeMap.AUTOSIZE, true);
		specialEdgesAttributeMap.put(AttributeMap.DASHPATTERN, new float[] {3.0f});
		
		HeuristicModelGraph hmg = new HeuristicModelGraph(hm,defaultAttributeMap,specialEdgesAttributeMap,hm.getSpecialEdges());
		
		ProMJGraph pgraph = buildJGraph(hmg);
		//ProMJGraphPanel pgpanel = new ProMJGraphPanel(pgraph);
		
		panel.add(pgraph.getComponent(), BorderLayout.CENTER);
		
		this.add(panel);
	}
	
	private ProMJGraph buildJGraph(DirectedGraph<?, ?> causalNet) {
		
		ProMGraphModel model = new ProMGraphModel(causalNet);
		ViewSpecificAttributeMap map = new ViewSpecificAttributeMap();
		GraphLayoutConnection layoutConnection = new GraphLayoutConnection(causalNet);
		 
//		causalNet.getAttributeMap().put(AttributeMap.EDGEEND, AttributeMap.ArrowType.ARROWTYPE_CLASSIC);
//		causalNet.getAttributeMap().put(AttributeMap.AUTOSIZE, true);
		
		ProMJGraph pgraph = new ProMJGraph(model, map, layoutConnection);
		
		JGraphHierarchicalLayout layout = new JGraphHierarchicalLayout();
		layout.setDeterministic(false);
		layout.setCompactLayout(false);
		layout.setFineTuning(true);
		layout.setParallelEdgeSpacing(15);
		layout.setFixRoots(false);

		layout.setOrientation(map.get(causalNet, AttributeMap.PREF_ORIENTATION, SwingConstants.NORTH));

		if(!layoutConnection.isLayedOut()){
		
			JGraphFacade facade = new JGraphFacade(pgraph);
	
			facade.setOrdered(false);
			facade.setEdgePromotion(true);
			facade.setIgnoresCellsInGroups(false);
			facade.setIgnoresHiddenCells(false);
			facade.setIgnoresUnconnectedCells(false);
			facade.setDirected(true);
			facade.resetControlPoints();
			facade.run(layout, true);
	
			java.util.Map<?, ?> nested = facade.createNestedMap(true, true);
	
			pgraph.getGraphLayoutCache().edit(nested);
			layoutConnection.setLayedOut(true);
		}
		
		pgraph.setUpdateLayout(layout);
		
		layoutConnection.updated();
		
		return pgraph;
	}
	
}
