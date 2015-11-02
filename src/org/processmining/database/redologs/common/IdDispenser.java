package org.processmining.database.redologs.common;

public class IdDispenser {
	
	private int endId;
	private int next;
	private IdBatchDispenser idbd;
	
	public IdDispenser(IdBatchDispenser batchDispenser) {
		this.idbd = batchDispenser;
		getNewBatch();
	}
	
	private void getNewBatch() {
		int[] b = idbd.getBatch();
		this.next = b[0];
		this.endId = b[1];
	}
	
	public int getNextId() {
		if (this.next > this.endId) {
			getNewBatch();
			return getNextId();
		}
		int id = this.next;
		this.next++;
		return id;
	}
}
