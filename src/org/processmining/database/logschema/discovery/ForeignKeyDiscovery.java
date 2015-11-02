package org.processmining.database.logschema.discovery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.processmining.database.utils.Pair;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.database.redologs.common.Column;
import org.processmining.database.redologs.common.Key;
import org.processmining.database.redologs.common.TableInfo;

public class ForeignKeyDiscovery {
	
	private List<TableInfo> tables = null;
	private List<Column> columns = null;
	private List<Key> pks = null;
	
	public ForeignKeyDiscovery(List<TableInfo> tables, List<Column> columns, List<Key> pks) {
		this.tables = tables;
		this.columns = columns;
		this.pks = pks;
	}
	
	public List<Key> discoverForeignKeys(int k, int l, double inclusionCoefficient, DataAccess values) {
		
		//List<Pair<String[],String[]>> fks = new ArrayList<>();
		List<Key> fks = new ArrayList<>();
		
		//HashMap<String,HashMap<Integer,String[]>> colKSketches = new HashMap<>();
		HashMap<Column,HashMap<Integer,String[]>> colKSketches = new HashMap<>();
		
		HashMap<Integer,HashMap<Integer,String[]>> pkKSketches = new HashMap<>();
		
		HashMap<Pair<Key,Column>,Column> colToPKColMap = new HashMap<>();
		HashMap<Integer,QuantileHistogram> pksQuantileHistogram = new HashMap<>();
		
		// Phase 1
		
		for (int i = 0; i < columns.size(); i++) {
			Column c = columns.get(i);
			colKSketches.put(c, bottomKSketch(k, new Column[] {c}, values.getRowsForColumn(c)));
		}
		
		for (int i = 0; i < pks.size(); i++) {
			Key pki = pks.get(i);
			for (int j = 0; j < pki.fields.size(); j++) {
				for (int f = 0; f < columns.size(); f++) {
					if (computeInclusionCoefficient(colKSketches.get(columns.get(f)),colKSketches.get(pki.fields.get(j)))
							>= inclusionCoefficient) {
						if (pki.fields.size() == 1) {
							Key fk = new Key();
							fk.refers_to = pki;
							fk.fields = new ArrayList<>();
							fk.fields.add(columns.get(f));
							fk.refers_to_column.put(columns.get(f),pki.fields.get(0));
							fks.add(fk);
						} else {
							colToPKColMap.put(new Pair<Key, Column>(pki, pki.fields.get(j)), columns.get(f));
						}
					}
				}
			}
			
			if (pki.fields.size() > 1) {
				pkKSketches.put(i,bottomKSketch(k, pki.fields.toArray(new Column[] {}),values.getRowsForTable(pki.table)));
			}
			
			// Compute Q[P] <- Quantile histogram of P
			QuantileHistogram qh = lquantileHistogram(l, pki.fields.toArray(new Column[] {}), values.getRowsForTable(pki.table));
			pksQuantileHistogram.put(i, qh);
		}
		
		// Phase 2 TODO
		
		for (int i = 0; i < fks.size(); i++) {
			// Single-column FK TODO
			Key fki = fks.get(i);
			QuantileHistogram qhfki = lquantileHistogram(l, fki.fields.toArray(new Column[] {}), values.getRowsForTable(fki.table));
			int pkindex = pks.indexOf(fki.refers_to);
			QuantileHistogram qhpki = pksQuantileHistogram.get(pkindex);
			// Compute distribution histogram of F with respect to P TODO 
		}
		
		for (int i = 0; i < pks.size(); i++) {
			Key pki = pks.get(i);
			if (pki.fields.size() == 1) {
				// Single-column PK TODO
				// Do nothing
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
	
	public static QuantileHistogram lquantileHistogram(int l, Column[] pCols, RowsAccess values) {
		
		QuantileHistogram qh = new QuantileHistogram(pCols.length,l);
		qh.sizeP = values.size();
		
		for (int i = 0; i < values.size(); i++) {
			RowData rd = values.getNext();
			
			StringBuffer sv = new StringBuffer();
			for (int j = 0; j < pCols.length; j++) {
				sv.append(rd.get(pCols[j])+"#");
			}
			int vHash = sv.toString().hashCode();
			
			for (int j = 0; j < pCols.length; j++) {
				String v = rd.get(pCols[j]);
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
	
	public static HashMap<Integer,String[]> bottomKSketch(int k, Column[] cols, RowsAccess values) {
		
		HashMap<Integer,String[]> sketch = new HashMap<>();
		List<Integer> sketchSorted = new ArrayList<>();
		
		for (int i = 0; i < values.size(); i++) {
			String[] v = new String[cols.length];
			StringBuffer strV = new StringBuffer();
			RowData row = values.getNext();
			for (int j = 0; j < cols.length; j++) {
				v[j] = row.get(cols[j]);
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
