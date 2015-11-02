package org.processmining.database.logschema.discovery;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.in.XParser;
import org.deckfour.xes.in.XesXmlGZIPParser;
import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.processmining.openslex.SLEXDMClass;
import org.processmining.openslex.SLEXDMClassResultSet;
import org.processmining.openslex.SLEXDMDataModel;
import org.processmining.openslex.SLEXStorage;
import org.processmining.openslex.SLEXStorageDataModel;
import org.processmining.openslex.SLEXStorageImpl;
import org.processmining.database.redologs.common.Column;
import org.processmining.database.redologs.common.DataModel;
import org.processmining.database.redologs.common.Key;
import org.processmining.database.redologs.common.SLEXDataModelExportImport;
import org.processmining.database.redologs.common.TableInfo;

import com.bethecoder.ascii_table.ASCIITable;

public class SchemaMiner {

	private XLog log;
	private SLEXDMDataModel dm;
	private HashMap<String,List<String>> classesMapCI;
	private HashMap<String,List<String>> classesMapCF;
	private double limitClustering = 0.0;
	
	public SchemaMiner(XLog log, SLEXDMDataModel dm) {
		this.log = log;
		this.dm = dm;
	}
	
	public SLEXDMDataModel getDataModel() {
		return dm;
	}
	
	public XLog getLog() {
		return log;
	}
	
	public double getLimitClustering() {
		return limitClustering;
	}
	
	private String hashEventClass(List<String> attrList) {
		return String.valueOf(attrList.hashCode());
	}
	
	public static void main(String[] args) {
		try {
			XParser parser = new XesXmlGZIPParser();
			List<XLog> xlogList = parser.parse(new File("/home/eduardo/Dropbox/Road_Traffic_Fine_Management_Process.xes.gz"));
			XLog xlog = xlogList.get(0);
		
			SLEXStorageDataModel st = new SLEXStorageImpl("data","test-dm-schema-miner.slexdm",SLEXStorage.TYPE_DATAMODEL);
			SLEXDMDataModel dm = st.createDMDataModel("Test-CF");
			SchemaMiner sminer = new SchemaMiner(xlog, dm);
			sminer.discoverClasses(1.0);
			//sminer.discoverKeys();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<SLEXDMClass> discoverClasses(double limit) {
		List<SLEXDMClass> classesList = new ArrayList<>();
		
		this.limitClustering = limit;
		
		/* Class Set Discovery */
		// Compute CI
		HashMap<String,Integer> eventsCountPerClass = new HashMap<>();
		this.classesMapCI = computeCI(eventsCountPerClass);
		
		// Count common attributes to all classes
		HashSet<String> attrCommon = null;
		for (String c: classesMapCI.keySet()) {
			List<String> atts = classesMapCI.get(c);
			if (attrCommon == null) {
				attrCommon = new HashSet<>();
				for (String at: atts) {
					attrCommon.add(at);
				}
			} else {
				Iterator<String> it = attrCommon.iterator();
				while (it.hasNext()) {
					String comm = it.next();
					if (!atts.contains(comm)) {
						it.remove();
					}
				}
			}
		}
		int commonAtts = attrCommon.size();
		System.out.println("# Common attributes: "+commonAtts+" => "+attrCommon);
		
		// Compute Diff and Dominant maps
		HashMap<String,HashMap<String,Double>> diffMap = computeDiffMap(classesMapCI,commonAtts);
		HashMap<String,HashMap<String,Double>> dominantMap = computeDominantMap(classesMapCI,eventsCountPerClass);
		
		printDiffAndDominantMap(diffMap,dominantMap);
		
		// Compute SG
		HashSet<Entry<String,String>> sg = computeSG(limitClustering, classesMapCI, diffMap);
		
		// Compute DSG
		HashSet<Entry<String,String>> dsg = computeDSG(sg,dominantMap);
		
		// Compute TDSG
		HashSet<Entry<String,String>> tdsg = computeTDSG(dsg,diffMap,dominantMap);
		
		System.out.println(tdsg.toString());
		
		// Compute CF
		this.classesMapCF = computeCF(classesMapCI,tdsg);
		
		/**/
		
		for (String c: classesMapCF.keySet()) {
			SLEXDMClass cl = dm.getStorage().createDMClass(dm.getId(), c, false);
			// Add attributes to class
			for (String at: classesMapCF.get(c)) {
				dm.getStorage().createDMAttribute(cl.getId(), at, false);
			}
			
			classesList.add(cl);
		}
		dm.commit();
		
		SLEXDMDataModel dmci = dm.getStorage().createDMDataModel("Test-CI");
		for (String c: classesMapCI.keySet()) {
			SLEXDMClass cl = dmci.getStorage().createDMClass(dmci.getId(), c, false);
			// Add attributes to class
			for (String at: classesMapCI.get(c)) {
				dmci.getStorage().createDMAttribute(cl.getId(), at, false);
			}
			
			classesList.add(cl);
		}
		dmci.commit();
		return classesList;
	}
	
	private void printDiffAndDominantMap(
			HashMap<String, HashMap<String, Double>> diffMap,
			HashMap<String, HashMap<String, Double>> dominantMap) {
		
		String[] header = new String[diffMap.size()+1];
		
		int h = 1;
		header[0] = "";
		for (String c: diffMap.keySet()) {
			header[h] = c;
			h++;
		}
		
		String[][] diffData = new String[header.length-1][header.length];
		String[][] domData = new String[header.length-1][header.length];
		
		for (int i = 1; i < header.length; i++) {
			diffData[i-1][0] = header[i];
			domData[i-1][0] = header[i];
			for (int j = 1; j < header.length; j++) {
				diffData[i-1][j] = String.format("%1$,.2f", diffMap.get(header[i]).get(header[j]));
				domData[i-1][j] = String.format("%1$,.2f", dominantMap.get(header[i]).get(header[j]));
			}
		}
		
		ASCIITable.getInstance().printTable(header, diffData);
		ASCIITable.getInstance().printTable(header, domData);
	}

	private HashMap<String, List<String>> computeCF(HashMap<String,List<String>> ci,
			HashSet<Entry<String, String>> tdsg) {
		
		HashMap<String, List<String>> cf = new HashMap<>();
		
		for (String cla: ci.keySet()) {
			cf.put(cla, ci.get(cla));
		}
		
		for (Entry<String,String> e: tdsg) {
			cf.remove(e.getKey());
		}
		
		return cf;
	}

	private HashSet<Entry<String, String>> computeTDSG(
			HashSet<Entry<String, String>> dsg,
			HashMap<String, HashMap<String, Double>> diffMap,
			HashMap<String, HashMap<String, Double>> dominantMap) {
		
		HashSet<Entry<String, String>> tdsg = new HashSet<>();
		
		HashMap<String,String> bestMap = new HashMap<>();
		
		for (Entry<String,String> e: dsg) {
			if (bestMap.containsKey(e.getKey())) {
				String cla = e.getKey();
				String clj = bestMap.get(cla); 
				String clk = e.getValue();
				
				if (diffMap.get(cla).get(clk) < diffMap.get(cla).get(clj)) {
					bestMap.put(cla, clk);
				} else if (diffMap.get(cla).get(clk) == diffMap.get(cla).get(clj)) {
					if (dominantMap.get(clj).get(clk) < dominantMap.get(clk).get(clj)) {
						bestMap.put(cla, clk);
					}
				}
				
			} else {
				bestMap.put(e.getKey(), e.getValue());
			}
		}
		
		for (Entry<String,String> e: bestMap.entrySet()) {
			tdsg.add(e);
		}
		
		return tdsg;
	}

	private HashSet<Entry<String, String>> computeDSG(
			HashSet<Entry<String, String>> sg,
			HashMap<String, HashMap<String, Double>> dominantMap) {
		
		HashSet<Entry<String, String>> dsg = new HashSet<>();
		
		for (Entry<String,String> e: sg) {
			double d1 = dominantMap.get(e.getKey()).get(e.getValue());
			double d2 = dominantMap.get(e.getValue()).get(e.getKey());
			
			if (d1 > d2) {
				dsg.add(e);
			}
		}
		
		return dsg;
	}

	private HashMap<String, HashMap<String, Double>> computeDominantMap(
			HashMap<String, List<String>> ciMap,
			HashMap<String, Integer> eventsCountPerClass) {
		HashMap<String, HashMap<String, Double>> dominantMap = new HashMap<>();
		
		for (String cla: ciMap.keySet()) {
			List<String> claList = ciMap.get(cla);
			
			for (String clb: ciMap.keySet()) {
				List<String> clbList = ciMap.get(clb);
				
				int eA = eventsCountPerClass.get(cla);
				int eB = eventsCountPerClass.get(clb);
				int atA = claList.size();
				int atB = clbList.size();
				
				Double dominantVal = 0.0; 
				
				if (eA == eB) {
					if (atA == atB) {
						if (cla.compareTo(clb) > 0) {
							dominantVal = 1.0;
						} else {
							dominantVal = 0.0;
						}
					} else {
						dominantVal = (double) atA / (double) (atA + atB);
					}
				} else {
					dominantVal = (double) eA / (double) (eA + eB);
				}
				
				HashMap<String,Double> aMap = null;
				if (dominantMap.containsKey(cla)) {
					aMap = dominantMap.get(cla);
				} else {
					aMap = new HashMap<>();
					dominantMap.put(cla, aMap);
				}
				aMap.put(clb, dominantVal);
				
			}
		}
		
		return dominantMap;
	}

	private HashMap<String, HashMap<String, Double>> computeDiffMap(
			HashMap<String, List<String>> ciMap, int commonAtts) {
		HashMap<String, HashMap<String, Double>> diffMap = new HashMap<>();
		
		for (String cla: ciMap.keySet()) {
			List<String> claList = ciMap.get(cla);
			
			for (String clb: ciMap.keySet()) {
				List<String> clbList = ciMap.get(clb);
				
				int total = 0;
				int common = 0;
				
				for (String atA: claList) {
					if (clbList.contains(atA)) {
						common++;
					}
				}
				
				total = claList.size() - common + clbList.size() - commonAtts;
				
				Double diffVal = Math.abs((((double) (common - commonAtts + 1.0) / (double) (total + 1.0)) - 1.0)) ;
				
				HashMap<String,Double> aMap = null;
				if (diffMap.containsKey(cla)) {
					aMap = diffMap.get(cla);
				} else {
					aMap = new HashMap<>();
					diffMap.put(cla, aMap);
				}
				aMap.put(clb, diffVal);
				
			}
		}
		
		return diffMap;
	}

	private HashSet<Entry<String,String>> computeSG(double limit, HashMap<String,
			List<String>> ciMap, HashMap<String,HashMap<String,Double>> diffMap) {
		HashSet<Entry<String,String>> sg = new HashSet<>();
		
		for (String cla: diffMap.keySet()) {
			HashMap<String,Double> aMap = diffMap.get(cla);
			for (String clb: aMap.keySet()) {
				if (aMap.get(clb) < limit) {
					Entry<String,String> entry = new AbstractMap.SimpleEntry<String, String>(cla, clb);
					sg.add(entry);
				}
			}
		}
		
		return sg;
	}
	
	private HashMap<String,List<String>> computeCI(HashMap<String,Integer> eventsCountPerClass) {
		HashMap<String,List<String>> ci = new HashMap<>();
		
		for (XTrace trace: log) {
			for (XEvent e: trace) {
				List<String> attrList = new ArrayList<>();
				for (String attrName: e.getAttributes().keySet()) {
					attrList.add(attrName);
				}
				java.util.Collections.sort(attrList);
				String hashAttrSet = hashEventClass(attrList);
				
				int count = 0;
				if (ci.containsKey(hashAttrSet)) {
					count = eventsCountPerClass.get(hashAttrSet);
				} else {
					ci.put(hashAttrSet, attrList);
				}
				
				count++;
				eventsCountPerClass.put(hashAttrSet, count);
			}
		}
		
		return ci;
	}
	
	public void discoverKeys() {
		// TODO
		
		DataModel datamodel = SLEXDataModelExportImport.loadDataModelFromSLEXDM(dm);
		
		List<TableInfo> tables = datamodel.getTables();
		List<Column> columns = new ArrayList<>();
		List<Key> pks = new ArrayList<>();
		pks.addAll(datamodel.getPrimaryKeys().values());
		
		for (TableInfo t: tables) {
			columns.addAll(t.columns);
		}
		
		ForeignKeyDiscovery fkd = new ForeignKeyDiscovery(tables, columns, pks);
		
		int k = 0; // FIXME
		int l = 0; // FIXME
		double inclusionCoefficient = 0.0; // FIXME
		DataAccess values = new DataAccess(); // FIXME
		
		List<Key> fks = fkd.discoverForeignKeys(k, l, inclusionCoefficient, values);
		
	}
	
}
