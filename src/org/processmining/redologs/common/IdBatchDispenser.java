package org.processmining.redologs.common;

public class IdBatchDispenser {

	private int startId = 0;
	private int batchSize = 100;
	
	public IdBatchDispenser(int startId, int batchSize) {
		this.startId = startId;
		this.batchSize = batchSize;
	}
	
	public IdDispenser getDispenser() {
		IdDispenser idd = new IdDispenser(this);
		return idd;
	}
	
	protected synchronized int[] getBatch() {
		int[] b = new int[] {startId,startId+batchSize};
		startId += batchSize + 1;
		return b;
	}
	
}
