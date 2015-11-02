package org.processmining.database.exploration;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXAttributeValue;
import org.processmining.openslex.SLEXEvent;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXEventResultSet;
import org.processmining.openslex.SLEXFactory;
import org.processmining.openslex.SLEXStorage;
import org.processmining.openslex.SLEXStorageCollection;
import org.processmining.database.redologs.common.DataModel;
import org.processmining.database.redologs.common.Key;
import org.processmining.database.redologs.common.SLEXAttributeMapper;
import org.processmining.database.redologs.common.TableInfo;
import org.processmining.database.redologs.oracle.OracleLogMinerExtractor;

public class PerspectiveDetector {
	
	private SLEXEventCollection evCol;
	private DataModel dm;
	private SLEXAttributeMapper mapper;
	
	private List<Set<TableInfo>> subgraphs;
	private HashMap<TableInfo,Integer> eventsPerTable;
	private HashMap<TableInfo, Integer> inArcs;
	private HashMap<TableInfo, Integer> outArcs;
	private DirectedGraph<TableInfo,Key> dirGraph;
	
	public PerspectiveDetector(SLEXEventCollection evCol, DataModel dm, SLEXAttributeMapper mapper) {
		this.mapper = mapper;
		this.evCol = evCol;
		this.dm = dm;
	}
	
	private DirectedGraph<TableInfo,Key> getDirectedGraph() {
		if (dirGraph != null) {
			return dirGraph;
		}
		
		dirGraph = new DefaultDirectedGraph<>(Key.class);
		
		for (TableInfo t: dm.getTables()) {
			dirGraph.addVertex(t);
		}
		
		for (Key k: dm.getForeignKeys().values()) {
			TableInfo source = k.table;
			TableInfo target = k.refers_to.table;
			
			dirGraph.addEdge(source, target, k);
		}
		
		return dirGraph;
	}
	
	public List<Set<TableInfo>> getSubgraphs() {
		if (subgraphs != null) {
			return subgraphs;
		}
		
		DirectedGraph<TableInfo,Key> graph = getDirectedGraph();
		
		ConnectivityInspector<TableInfo, Key> connInspector = new ConnectivityInspector<>(graph);
		
		subgraphs = connInspector.connectedSets();
		
		return subgraphs;
	}

	public HashMap<TableInfo,Integer> getEventsPerTable() {
		
		if (eventsPerTable != null) {
			return eventsPerTable;
		}
		
		eventsPerTable = new HashMap<>();
		
		SLEXEventResultSet erset = evCol.getEventsResultSet();
		SLEXEvent e = null;
		SLEXAttribute tableNameAttr = evCol.getStorage().findAttribute(
				SLEXStorageCollection.COMMON_CLASS_NAME, OracleLogMinerExtractor.COLUMN_TABLE_NAME);
		
		HashMap<String,TableInfo> tablesPerName = new HashMap<>();
		for (TableInfo t: dm.getTables()) {
			tablesPerName.put(t.name, t);
		}
		
		if (tableNameAttr != null) {
			while ((e = erset.getNext()) != null) {
				SLEXAttributeValue valAtt = e.getAttributeValues().get(tableNameAttr);
				if (valAtt != null) {
					String val = valAtt.getValue();
					TableInfo t = tablesPerName.get(val);
					if (t != null) {
						Integer i = eventsPerTable.get(t);
						if (i == null) {
							i = 0;
						}
						eventsPerTable.put(t, i+1);
					}
				}
			}
		} else {
			System.err.println("ERROR: Table Name attribute not found!!");
		}
		
		return eventsPerTable;
	}

	public HashMap<TableInfo,Integer> getInArcs() {
		
		if (inArcs != null) {
			return inArcs;
		}
		
		inArcs = new HashMap<>();
		
		DirectedGraph<TableInfo, Key> graph = getDirectedGraph();
		
		for (TableInfo t: dm.getTables()) {
			inArcs.put(t, graph.inDegreeOf(t));
		}
		
		return inArcs;
	}
	
	public HashMap<TableInfo,Integer> getOutArcs() {
		
		if (outArcs != null) {
			return outArcs;
		}
		
		outArcs = new HashMap<>();
		
		DirectedGraph<TableInfo, Key> graph = getDirectedGraph();
		
		for (TableInfo t: dm.getTables()) {
			outArcs.put(t, graph.outDegreeOf(t));
		}
		
		return outArcs;
	}
	
}
