package org.processmining.redologs.miner;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;

import org.processmining.redologs.miner.visualizer.HeuristicModelProMVisualizer;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class LoglessFHMinerTester {

	private CountMatrix dfc1;
	private CountMatrix dfc2;
	private CountMatrix dfcCommon;
	
	public LoglessFHMinerTester(CountMatrix dfc1, CountMatrix dfc2, CountMatrix dfcCommon) {
		this.dfc1 = dfc1;
		this.dfc2 = dfc2;
		this.dfcCommon = dfcCommon;
	}
	
	public static void main(String[] args) {
		File dfcFileA = new File("/home/eduardo/outputLogs/dfg/dfgA.dfg");
		File dfcFileB = new File("/home/eduardo/outputLogs/dfg/dfgB.dfg");
		File dfcFileC = new File("/home/eduardo/outputLogs/dfg/dfgInteraction.dfg");
		
		CountMatrix dfcA = dfgFileToCountMatrix(dfcFileA);
		CountMatrix dfcB = dfgFileToCountMatrix(dfcFileB);
		CountMatrix dfcC = dfgFileToCountMatrix(dfcFileC);
		
		LoglessFHMinerTester lfhmTester = new LoglessFHMinerTester(dfcA,dfcB,dfcC);
		lfhmTester.show();
	}
	
	public static CountMatrix dfgFileToCountMatrix(File f) {
		CountMatrix cm = new CountMatrix();
		
		FileReader fread = null;
		
		try {
			fread = new FileReader(f);
			BufferedReader bfr = new BufferedReader(fread);
			
			String line = "";
			int i = 0;
			String[] activities = null;
			while (line != null) {
				line = bfr.readLine();
				if (line != null) {
					if (i == 0) {
						// First line. Activity names
						activities = line.split(",");
					} else if (i == 1) {
						// Start vector
						// TODO
					} else if (i == 2) {
						// End vector
						// TODO
					} else {
						// Count vectors
						String[] counts = line.split(",");
						for (int j = 0; j < activities.length; j++) {
							cm.setCount(activities[i-3], activities[j], Integer.valueOf(counts[j]));
						}
					}
					i++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fread != null) {
				try {
					fread.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		return cm;
	}
	
	public void show() {
		
		CountMatrix l2lc1 = new CountMatrix();
		
		CountMatrix l2lc2 = new CountMatrix();
		
		CountMatrix l2lcCommon = new CountMatrix();

//		String[] activities = new String[] {"1444+CONCERT+INSERT+","3111+CONCERT+UPDATE+",
//				"14444+TICKET+INSERT+","4111+CONCERT+UPDATE+","31111+TICKET+UPDATE+",
//				"44+BAND_PLAYING+INSERT+"};
//		int[] start = new int[] {17,0,0,0,0,0};
//		int[] end = new int[] {3,1,0,0,15,11};
//		int[] a = new int[] {0,0,1094,0,0,12};
//		int[] b = new int[] {0,0,0,0,40,3};
//		int[] c = new int[] {0,4192,0,756,0,0};
//		int[] d = new int[] {0,0,0,0,27,0};
//		int[] e = new int[] {0,0,0,14,0,99};
//		int[] f = new int[] {0,0,3854,1,61,67};
//		int[][] counts = new int[][] {a,b,c,d,e,f};
//		
//		for (int i = 0; i < activities.length; i++) {
//			for (int j = 0; j < activities.length; j++) {
//				dfc1.setCount(activities[i],activities[j],counts[i][j]);
//			}
//		}
		
		LoglessFHMiner lfhm1 = new LoglessFHMiner(dfc1, l2lc1);
		
		HeuristicModel hm1 = lfhm1.mine();
		
		LoglessFHMiner lfhm2 = new LoglessFHMiner(dfc2, l2lc2);
		
		HeuristicModel hm2 = lfhm2.mine();
		
		HeuristicModelProMVisualizer hmv1 = new HeuristicModelProMVisualizer(hm1);
		
		HeuristicModelProMVisualizer hmv2 = new HeuristicModelProMVisualizer(hm2);
		
//		HeuristicModel hm = new HeuristicModel();
//		DGraph dgraph = new DGraph();
//		
//		for (Entry<String,String> entry: hm1.getDependencyGraph().getEntries()) {
//			dgraph.add(entry.getKey(), entry.getValue());
//		}
//		
//		for (Entry<String,String> entry: hm2.getDependencyGraph().getEntries()) {
//			dgraph.add(entry.getKey(), entry.getValue());
//		}
//		
//		hm.setDependencyGraph(dgraph);
		
		LoglessFHMiner lfhmCommon = new LoglessFHMiner(dfcCommon, l2lcCommon);
		
		HeuristicModel hmCommon = lfhmCommon.mine();
		
		HashSet<Entry<String,String>> specialEdges = new HashSet<>();
		for (Entry<String,String> entry: hmCommon.getDependencyGraph().getEntries()) {
			if (!hm1.getDependencyGraph().getEntries().contains(entry)) {
				if (!hm2.getDependencyGraph().getEntries().contains(entry)) {
					specialEdges.add(entry);
				}
			}
		}
		
		hmCommon.setSpecialEdges(specialEdges);
		
		//HeuristicModelProMVisualizer hmvCommon = new HeuristicModelProMVisualizer(hmCommon);
		HeuristicModelProMVisualizer hmvCommon = new HeuristicModelProMVisualizer(hmCommon);
		
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel p = new JPanel();
		JScrollPane scrollPanel = new JScrollPane(p);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPanel);
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p.add(hmv1);
		p.add(hmv2);
		p.add(hmvCommon);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}