package org.processmining.logschema.discovery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.processmining.utils.Pair;

public class ForeignKeyDiscovery {
	
	private List<String> tables = null;
	private List<String> columns = null;
	private List<String[]> pks = null;
	
	public ForeignKeyDiscovery(List<String> columns, List<String[]> pks) {
		this.columns = columns;
		this.pks = pks;
	}
	
	public List<Pair<String[],String[]>> discoverForeignKeys(int k, int l, double inclusionCoefficient, String[][] values) {
		
		List<Pair<String[],String[]>> fks = new ArrayList<>();
		
		HashMap<String,HashMap<Integer,String[]>> colKSketches = new HashMap<>();
		HashMap<Integer,HashMap<Integer,String[]>> pkKSketches = new HashMap<>();
		
		HashMap<Pair<String[],String>,String> colToPKColMap = new HashMap<>();
		HashMap<Integer,QuantileHistogram> pksQuantileHistogram = new HashMap<>();
		
		// Phase 1
		
		for (int i = 0; i < columns.size(); i++) {
			colKSketches.put(columns.get(i), bottomKSketch(k, new int[] {i}, values));
		}
		
		for (int i = 0; i < pks.size(); i++) {
			String[] pki = pks.get(i);
			for (int j = 0; j < pki.length; j++) {
				for (int f = 0; f < columns.size(); f++) {
					if (computeInclusionCoefficient(colKSketches.get(columns.get(f)),colKSketches.get(pki[j])) >= inclusionCoefficient) {
						if (pki.length == 1) {
							fks.add(new Pair<String[], String[]>(pki, new String[] {columns.get(f)}));
						} else {
							colToPKColMap.put(new Pair<String[], String>(pki, pki[j]), columns.get(f));
						}
					}
				}
				
			}
			
			int[] pkint = new int[pki.length];
			if (pki.length > 1) {
				for (int ik = 0; ik < pki.length; ik++) {
					pkint[ik] = columns.indexOf(pki[ik]);
				}
				pkKSketches.put(i,bottomKSketch(k, pkint,values));
			}
			
			// Compute Q[P] <- Quantile histogram of P
			QuantileHistogram qh = lquantileHistogram(l, pkint, values);
			pksQuantileHistogram.put(i, qh);
		}
		
		// Phase 2 TODO
		
		for (int i = 0; i < pks.size(); i++) {
			String[] pki = pks.get(i);
			if (pki.length == 1) {
				// Single-column PK TODO
			} else {
				// Multi-column PK TODO
				
			}
		}
		
		// Phase 3 TODO
		
		return fks;
	}
	
	public static double computeInclusionCoefficient(HashMap<Integer,String[]> a, HashMap<Integer,String[]> b) {
		
		// TODO
		
		return 0.0;
	}
	
	public static QuantileHistogram lquantileHistogram(int l, int[] p, String[][] values) {
		
		QuantileHistogram qh = new QuantileHistogram(p.length,l);
		qh.sizeP = values.length;
		
		for (int i = 0; i < values.length; i++) {
			
			StringBuffer sv = new StringBuffer();
			for (int j = 0; j < p.length; j++) {
				sv.append(values[i][p[j]]+"#");
			}
			int vHash = sv.toString().hashCode();
			
			for (int j = 0; j < p.length; j++) {
				String v = values[i][p[j]];
				ArrayList<Integer> di = qh.dist.get(j);
				if (di == null) {
					di = new ArrayList<>();
					qh.dist.put(j, di);
				}
				int vjHash = v.hashCode();
				di.add(vjHash);
				
				HashMap<Integer,ArrayList<Integer>> distMapQI = qh.distMap.get(j);
				if (distMapQI == null) {
					distMapQI = new HashMap<>();
					qh.distMap.put(j, distMapQI);
				}
				ArrayList<Integer> dQIList = distMapQI.get(vjHash);
				if (dQIList == null) {
					dQIList = new ArrayList<>();
					distMapQI.put(vjHash, dQIList);
				}
				dQIList.add(vHash);
			}
		}
		
		for (int i = 0; i < qh.n; i++) {
			ArrayList<Integer> di = qh.dist.get(i);
			Collections.sort(di);
			
			for (int lj = 0; lj < l; lj++) {
				double ph = ((double) lj) / ((double) l);
				qh.qql[i][lj] = sas(di,ph,di.size());
			}
		}
		
		computeCellsQuantileHistogram(qh,0,"",new HashSet<Integer>());
		
		return qh;
	}
	
	private static QuantileHistogram computeCellsQuantileHistogram(
			QuantileHistogram qh, int in, String cellKey, HashSet<Integer> intersection) {
		
		for (int i = in; i < qh.n; i++) {
			String cellKeyNew = cellKey+i+"#";
			int lastqi = 0;
			for (int li = 0; li < qh.l; li++) {
				HashSet<Integer> intersectionNew = new HashSet<>();
				double qb = qh.qql[i][li];
				ArrayList<Integer> vals = qh.dist.get(i);
				int v = 0;
				int vi = lastqi+1;
				while (v < qb) {
					v = vals.get(vi);
					if (v < qb) {
						for (Integer vv: qh.distMap.get(i).get(v)) {
							if (i == 0 || intersection.contains(vv)) {
								intersectionNew.add(vv);
							}
						}
						lastqi = vi;
					}
				}
				
				if (i == qh.n-1) {
					qh.cellsMap.put(cellKeyNew, intersectionNew);
				} else {
					computeCellsQuantileHistogram(qh,i+1,cellKeyNew,intersectionNew);
				}
			}
		}
		
		return qh;
	}
	
	public static double sas(ArrayList<Integer> orderedV, double p, int n) {
		double y = 0;
		int j = 0;
		double g = 0;
		
		double np = ((double) n) * p;
		
		j = (int) np;
		g = np - ((double) j);
		
		if (g == 0) {
			y = (orderedV.get(j) - orderedV.get(j+1)) / 2.0;
		} else {
			y = orderedV.get(j+1);
		}
		
		return y;
	}
	
	public static HashMap<Integer,String[]> bottomKSketch(int k, int[] f, String[][] values) {
		
		HashMap<Integer,String[]> sketch = new HashMap<>();
		List<Integer> sketchSorted = new ArrayList<>();
		
		for (int i = 0; i < values.length; i++) {
			String[] v = new String[f.length];
			StringBuffer strV = new StringBuffer();
			for (int j = 0; j < f.length; j++) {
				v[j] = values[i][f[j]];
				strV.append(v[j]+"#");
			}
			int hash = strV.toString().hashCode();
			
			if (sketch.size() < k) {
				sketch.put(hash, v);
				sketchSorted.add(hash);
				Collections.sort(sketchSorted);
			} else {
				int max = sketchSorted.get(k-1);
				if (max > hash) {
					sketch.remove(max);
					sketch.put(hash, v);
					sketchSorted.remove(max);
					sketchSorted.add(hash);
					Collections.sort(sketchSorted);
				}
			}
		}
		
		return sketch;
	}
	
}
