package org.processmining.database.logschema.discovery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class QuantileHistogram {
	int n = 0;
	int l = 0;
	
	HashMap<Integer,ArrayList<Integer>> dist;
	HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> distMap;
	HashMap<String,HashSet<Integer>> cellsMap;
	
	double[][] qql;
	//int[][] qcount;
	
	int sizeP = 0;
	
	public QuantileHistogram(int n, int l) {
		this.n = n;
		this.l = l;
		this.qql = new double[n][l];
		//this.qcount = new int[n][l];
		
		this.dist = new HashMap<>();
		this.distMap = new HashMap<>();
		this.cellsMap = new HashMap<>();
	}
}