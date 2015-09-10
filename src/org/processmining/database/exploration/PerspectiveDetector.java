package org.processmining.database.exploration;

import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.SLEXAttributeMapper;
import org.processmining.redologs.common.TableInfo;

public class PerspectiveDetector {
	
	private SLEXEventCollection evCol;
	private DataModel dm;
	private SLEXAttributeMapper mapper;
	
	private List<Set<TableInfo>> subgraphs;
	
	public PerspectiveDetector(SLEXEventCollection evCol, DataModel dm, SLEXAttributeMapper mapper) {
		this.mapper = mapper;
		this.evCol = evCol;
		this.dm = dm;
	}
	
	public List<Set<TableInfo>> getSubgraphs() {
		if (subgraphs != null) {
			return subgraphs;
		}
		
		DirectedGraph<TableInfo,Key> dirGraph = new DefaultDirectedGraph<>(Key.class);
		
		for (TableInfo t: dm.getTables()) {
			dirGraph.addVertex(t);
		}
		
		for (Key k: dm.getForeignKeys().values()) {
			TableInfo source = k.table;
			TableInfo target = k.refers_to.table;
			
			dirGraph.addEdge(source, target, k);
		}
		
		ConnectivityInspector<TableInfo, Key> connInspector = new ConnectivityInspector<>(dirGraph);
		
		subgraphs = connInspector.connectedSets();
		
		return subgraphs;
	}
	
}
