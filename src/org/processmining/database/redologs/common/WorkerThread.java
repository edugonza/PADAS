package org.processmining.database.redologs.common;

public class WorkerThread extends Thread {

	private boolean stop = true;
	private boolean finished = true;
	private Task task = null;
	
	@Override
	public synchronized void start() {
		stop = false;
		super.start();
	}
	
	public synchronized void stopThread() {
		stop = true;
		notifyAll();
	}
	
	public Task waitUntilTaskFinished() {
		synchronized (this) {
			while (!finished) {
				try {
					wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return task;
	}
	
	public void setTask(Task task) {
		synchronized(this) {
			this.task = task;
			finished = false;
		
			notifyAll();
		}
	}
	
	public boolean doTask() {
		if (task != null) {
			task.doTask();
		}
		return true;
	}
	
	@Override
	public void run() {
		while (!stop) {
			try {
				if (finished) {
					synchronized (this) {
						while (finished) {
							this.wait();
						}
					}
				} else {
					doTask();
					finished = true;
					synchronized (this) {
						this.notifyAll();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
