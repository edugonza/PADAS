package org.processmining.redologs.miner;

import java.util.Map.Entry;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class LoglessFHMiner {
	
	public final static double DEPENDENCY_THRESHOLD_DEFAULT = 0.9;
	public final static double L1L_THRESHOLD_DEFAULT = 0.9;
	public final static double L2L_THRESHOLD_DEFAULT = 0.9;
	public final static double RELATIVE_TO_BEST_THRESHOLD_DEFAULT = 0.05;
	
	private CountMatrix dfc; // Directly-follows count
	private CountMatrix l2lc; // Length-two-loop count
	private double depThld = DEPENDENCY_THRESHOLD_DEFAULT; // Dependency Threshold
	private double l1lThld = L1L_THRESHOLD_DEFAULT; // Length-one-loops Threshold
	private double l2lThld = L2L_THRESHOLD_DEFAULT; // Length-two-loops Threshold
	private double rtbThld = RELATIVE_TO_BEST_THRESHOLD_DEFAULT; // Relative to best Threshold
	
	private DGraph dgraph; // Dependency Graph

	public LoglessFHMiner(CountMatrix dfc, CountMatrix l2lc) {
		this(dfc,l2lc,DEPENDENCY_THRESHOLD_DEFAULT,
				L1L_THRESHOLD_DEFAULT,
				L2L_THRESHOLD_DEFAULT,
				RELATIVE_TO_BEST_THRESHOLD_DEFAULT);
	}
	
	public LoglessFHMiner(CountMatrix dfc, CountMatrix l2lc, double depThld, double l1lThld, double l2lThld, double rtbThld) {
		this.dfc = dfc;
		this.l2lc = l2lc;
		this.depThld = depThld;
		this.l1lThld = l1lThld;
		this.l2lThld = l2lThld;
		this.rtbThld = rtbThld;
	}
	
	public HeuristicModel mine() {
	
		// Compute Dependency Graph. DG
		DGraph dgraph = getDependencyGraph();
		
		System.out.println(dgraph.toString());
		
		// Mine Splits/Joins
		// TODO
		
		// Mine long-distance dependencies
		// TODO
		
		HeuristicModel hm = new HeuristicModel(dgraph);
		//hm.setDependencyGraph(dgraph);
		
		return hm;
	}
	
	private DGraph computeDependencyGraph() {
	
		DGraph depGraph = new DGraph();
		DGraph dgraphL1L = new DGraph();
		DGraph dgraphL2L = new DGraph();
		DGraph dgraphCout = new DGraph();
		DGraph dgraphCin = new DGraph();
		DGraph dgraphCoutPrime = new DGraph();
		DGraph dgraphCinPrime = new DGraph();
		DGraph dgraphCoutPrimePrime = new DGraph();
		DGraph dgraphCinPrimePrime = new DGraph();
		
		Set<String> activities = this.dfc.getActivities();
		
		// Length-one-loop Measure. (C1)
		for (String activityA: activities) {
			
			double l1ldm = computeL1LDepMeasure(dfc, activityA);
			
			if (l1ldm >= l1lThld) {
				// It is above the threshold
				dgraphL1L.add(activityA,activityA);
			}
			
		}
		
		// Length-two-loop Measure. (C2)
		for (String activityA: activities) {
			
			if (!dgraphL1L.contains(activityA,activityA)) {
			
				for (String activityB: activities) {
				
					if (!dgraphL1L.contains(activityB,activityB)) {
						
						double l2ldm = computeL2LDepMeasure(l2lc, activityA, activityB);
						
						if (l2ldm >= l2lThld) {
							
							dgraphL2L.add(activityA, activityB);
							
						}	
					}
				}
			}
		}
		
		// Find strongest follower. (Cout)
		for (String activityA: activities) {
			
			double max = 0.0;
			String strongestFollower = null;
			
			for (String activityB: activities) {
				
				if (!activityA.equals(activityB)) {
					double measure = computeDepMeasure(dfc, activityA, activityB);
					
					if (measure >= max && measure > 0.0) {
						max = measure;
						strongestFollower = activityB;
					}
				}
				
			}
			
			if (strongestFollower != null) {
				dgraphCout.add(activityA, strongestFollower);
			}
		}
		
		// Find strongest cause. (Cin)
		for (String activityB: activities) {
			
			double max = 0.0;
			String strongestCause = null;
			
			for (String activityA: activities) {
				
				if (!activityA.equals(activityB)) {
					double measure = computeDepMeasure(dfc, activityA, activityB);
					
					if (measure >= max && measure > 0.0) {
						max = measure;
						strongestCause = activityA;
					}
				}
				
			}
			
			if (strongestCause != null) {
				dgraphCin.add(strongestCause, activityB);
			}
		}
		
		// Weak outgoing-connections for a length-two loop. (Cout')
		for (String activityA: activities) {
			
			for (String activityX: dgraphCout.getFollowers(activityA)) {
				
				double measureAX = computeDepMeasure(dfc, activityA, activityX);
				
				if (measureAX < depThld) {
					
					boolean found = false;
					
					for (String activityB: dgraphL2L.getFollowers(activityA)) {
						
						for (String activityY: dgraphCout.getFollowers(activityB)) {
							
							double measureBY = computeDepMeasure(dfc, activityB, activityY);
							
							if ((measureBY - measureAX) > rtbThld) {
								
								found = true;
								
							}
						}
					}
					
					if (found) {
						dgraphCoutPrime.add(activityA, activityX);
					}
				}
			}
		}
		
		// Remove weak connections. (Cout = Cout - Cout')
		for (Entry<String,String> entry: dgraphCoutPrime.getEntries()) {
			
			dgraphCout.remove(entry.getKey(),entry.getValue());
			
		}
		
		// Weak incoming-connections for a length-two loop (Cin')
		for (String activityX: activities) {
			
			for (String activityA: dgraphCin.getFollowers(activityX)) {
				
				double measureXA = computeDepMeasure(dfc, activityX, activityA);
				
				if (measureXA < depThld) {
					
					boolean found = false;
					
					for (String activityB: dgraphL2L.getFollowers(activityA)) {
						
						for (String activityY: dgraphCin.getCauses(activityB)) {
							
							double measureYB = computeDepMeasure(dfc, activityY, activityB);
							
							if ((measureYB - measureXA) > rtbThld) {
								
								found = true;
								
							}
						}
					}
					
					if (found) {
						dgraphCinPrime.add(activityX, activityA);
					}
				}
			}
		}
		
		// Remove weak connections. (Cin = Cin - Cin')
		for (Entry<String,String> entry: dgraphCinPrime.getEntries()) {
			
			dgraphCin.remove(entry.getKey(),entry.getValue());
			
		}
		
		// Cout''
		for (String activityA: activities) {
			
			for (String activityB: activities) {
				
				double measureAB = computeDepMeasure(dfc, activityA, activityB);
				
				boolean found = false;
				
				if (measureAB >= depThld) {
					
					found = true;
					
				} else {
					
					for (String activityC: dgraphCout.getFollowers(activityA)) {
						
						double measureAC = computeDepMeasure(dfc, activityA, activityC);
						
						if ((measureAC - measureAB) < rtbThld) {
							found = true;
						}
					}
				}
				
				if (found) {
					dgraphCoutPrimePrime.add(activityA, activityB);
				}
			}
		}
		
		// Cin''
		for (String activityB: activities) {
			
			for (String activityA: activities) {
				
				double measureBA = computeDepMeasure(dfc, activityB, activityA);
				
				boolean found = false;
				
				if (measureBA >= depThld) {
					
					found = true;
					
				} else {
					
					for (String activityC: dgraphCin.getFollowers(activityB)) {
						
						double measureBC = computeDepMeasure(dfc, activityB, activityC);
						
						if ((measureBC - measureBA) < rtbThld) {
							found = true;
						}
					}
				}
				
				if (found) {
					dgraphCinPrimePrime.add(activityB, activityA);
				}
			}
		}
		
		// DG = C1 U C2 U Cout'' U Cin''
		List<DGraph> graphsToJoin = new Vector<>();
		graphsToJoin.add(dgraphL1L);
		graphsToJoin.add(dgraphL2L);
		graphsToJoin.add(dgraphCoutPrimePrime);
		graphsToJoin.add(dgraphCinPrimePrime);
		
		for (DGraph g: graphsToJoin) {
			for (Entry<String, String> entry: g.getEntries()) {
				depGraph.add(entry.getKey(), entry.getValue());
			}
		}
		
		return depGraph;
	}
	
	public DGraph getDependencyGraph() {
		if (this.dgraph == null) {
			this.dgraph = computeDependencyGraph();
		}
		
		return dgraph;
	}
	
	private static double computeL1LDepMeasure(CountMatrix dfc, String activity) {
		
		int count = dfc.getCount(activity,activity);
		
		double mesure = Math.abs((double) count) / Math.abs((double) count + 1.0);
		
		return mesure;
	}
	
	private static double computeL2LDepMeasure(CountMatrix l2lc, String activityA, String activityB) {
		
		int countAB = l2lc.getCount(activityA,activityB);
		int countBA = l2lc.getCount(activityB,activityA);
		
		double sum = Math.abs(countAB) + Math.abs(countBA);
		double mesure = sum / (sum + 1.0);
		
		return mesure;
	}
	
	private static double computeDepMeasure(CountMatrix dfc, String activityA, String activityB) {
		
		int countAB = dfc.getCount(activityA,activityB);
		int countBA = dfc.getCount(activityB,activityA);
		
		double mesure = ((double) Math.abs(countAB) - Math.abs(countBA)) / ((double) Math.abs(countAB) + Math.abs(countBA) + 1.0);
		
		return mesure;
	}
}
