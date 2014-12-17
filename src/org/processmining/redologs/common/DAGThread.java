package org.processmining.redologs.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import org.processmining.openslex.SLEXTrace;
import org.processmining.redologs.dag.DAG;
import org.processmining.redologs.dag.DAGNode;

public class DAGThread extends Thread {

	private class DAGTask {
		int action;
		SLEXTrace t;
		TraceID tid;
		Integer v;
		
		public DAGTask(int a, SLEXTrace t, TraceID tid, Integer v) {
			this.action = a;
			this.t = t;
			this.tid = tid;
			this.v = v;
		}
	}
	
	public static final int ADD_CHILD_TO_ROOT = 0;
	public static final int ADD_TO_MAP = 1;
	public static final int ADD_TO_DAG = 2;
	public static final int ADD_EVENTS_TO_TRACE = 3;
	public static final int FINISH = 4;
	
	private boolean stop = false;
	
	private SLEXTrace nullTrace = null;
	private DAG<SLEXTrace> subtraceDAG = new DAG<>(nullTrace);
	private Queue<DAGTask> tasksQueue = new LinkedList<>();
	
	private HashMap<SLEXTrace,TraceID> tracesMap = new HashMap<>();
	private HashMap<SLEXTrace,Integer> tracesEventsNumMap = new HashMap<>();
	
	private HashMap<Column,HashMap<String,HashSet<SLEXTrace>>> relatedMap = new HashMap<>();
	
	private TraceIDPattern tp = null;
	private ProgressHandler phandler = null;
	
	public DAGThread(TraceIDPattern tp, ProgressHandler phandler) {
		super("DAGThread");
		this.tp = tp;
		this.phandler = phandler;
		for (Column cpa: tp.getPAList()) {
			relatedMap.put(cpa, new HashMap<String,HashSet<SLEXTrace>>());
		}
	}
	
	public void addTask(int action, SLEXTrace t, TraceID tid, Integer v) {
		DAGTask task = new DAGTask(action,t,tid,v);
		synchronized (tasksQueue) {
			tasksQueue.offer(task);
			tasksQueue.notify();
		}
	}
	
	public DAG<SLEXTrace> getDAG() {
		return subtraceDAG;
	}
	
	@Override
	public synchronized void start() {
		this.stop = false;
		super.start();
	}
	
	public void stopThread() {
		this.stop = true;
	}
	
	@Override
	public void run() {
		try {
			int taskcounter = 0;
			int taskspending = 0;
			while (!stop) {
				DAGTask task = null;
				synchronized (tasksQueue) {
					while (!stop && task == null) {
						task = tasksQueue.poll();
						if (task == null) {
							tasksQueue.wait(5000);
						} else {
							taskspending = tasksQueue.size();
						}
					}
				}
				
				if (task != null) {
					switch (task.action) {
					case ADD_CHILD_TO_ROOT:
						subtraceDAG.addChild(subtraceDAG.getRoot(),task.t);
						break;
					case ADD_TO_MAP:
						tracesMap.put(task.t, task.tid);
						tracesEventsNumMap.put(task.t, task.v);
						LogTraceSplitter.addTraceToRelatedMap(task.t,task.tid,relatedMap);
						break;
					case ADD_TO_DAG:
						addTraceToDAG(subtraceDAG,relatedMap,tracesMap,tracesEventsNumMap,task.t,task.tid);
						break;
					case ADD_EVENTS_TO_TRACE:
						int n = tracesEventsNumMap.get(task.t);
						n+=task.v;
						tracesEventsNumMap.put(task.t, n);
						break;
					case FINISH:
						stopThread();
						break;
					default:
						System.err.println("Unknown action");
						break;
					}
				}
				
				taskcounter++;
				phandler.refreshValue("DAGTasks", "Done: "+String.valueOf(taskcounter)+" Pending: "+taskspending);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final int NODE_IS_UNK = 0;
	private static final int NODE_IS_SUB = 1;
	private static final int NODE_IS_SUP = -1;
	private static final int NODE_IS_NOP = 2;
	
	public static int checkTracesInDAG(TraceID tnewId, SLEXTrace tnew, SLEXTrace tb,			
			HashMap<SLEXTrace,TraceID> tracesMap,
			HashMap<SLEXTrace,Integer> tracesEventNumMap,
			HashMap<SLEXTrace,Integer> explorationMap,
			DAG<SLEXTrace> subtraceDAG) {
		
		int tbi = 3;
		if (explorationMap.containsKey(tb)) {
			tbi = explorationMap.get(tb);
		} else {
			tbi = NODE_IS_UNK;
		}
		if (tb != null) {
			if (tnew.getId() == tb.getId()) {
				tbi = NODE_IS_SUB;
				explorationMap.put(tb,tbi);
			}
		}
		if (tbi == NODE_IS_SUB || tbi == NODE_IS_SUP || tbi == NODE_IS_NOP) {
			return tbi;
		} else if (tbi == NODE_IS_UNK) {
			TraceID tbId = tracesMap.get(tb);
			boolean nSUBb = false;
			boolean nSUPb = false;
			if (LogTraceSplitter.subtrace(tnewId,tbId)) {
				nSUBb = true;
			}
			if (LogTraceSplitter.supertrace(tnewId,tbId)) {
				nSUPb = true;
			}
			
			if (nSUBb && nSUPb) {
				// Find out if it is super or sub trace looking at the events
				int tnewN = tracesEventNumMap.get(tnew);
				int tbN = tracesEventNumMap.get(tb);
				if (tnewN > tbN) {
					nSUPb = true;
					nSUBb = false;
				} else {
					nSUPb = false;
					nSUBb = true;
				}
			}
			
			if (nSUPb) {
				// tnew is supertrace of tb
				tbi = NODE_IS_SUB;
				explorationMap.put(tb,tbi);
				
				boolean children_nop = true;
				List<DAGNode<SLEXTrace>> children = new Vector<>();
				for (DAGNode<SLEXTrace> c: subtraceDAG.getNode(tb).getChildren()) {
					children.add(c);
				}
				for (DAGNode<SLEXTrace> c: children) {
					if (checkTracesInDAG(tnewId, tnew, c.getValue(), tracesMap,tracesEventNumMap, explorationMap, subtraceDAG) != NODE_IS_NOP) {
						children_nop = false;
					}
				}
				if (children_nop) {
					// tnew is a child of tb
					subtraceDAG.addChild(subtraceDAG.getNode(tb), tnew);
				}
			} else if (nSUBb) {
				// tnew is subtrace of tb
				tbi = NODE_IS_SUP;
				explorationMap.put(tb,tbi);
				boolean children_nop = true;
				List<DAGNode<SLEXTrace>> parents = new Vector<>();
				for (DAGNode<SLEXTrace> p: subtraceDAG.getNode(tb).getParents()) {
					parents.add(p);
				}
				for (DAGNode<SLEXTrace> p: parents) {
					int r = checkTracesInDAG(tnewId, tnew, p.getValue(), tracesMap,tracesEventNumMap, explorationMap, subtraceDAG);
					if (r != NODE_IS_NOP) {
						children_nop = false;
					}
					if (r == NODE_IS_SUB) {
						// tnew is a child of p and parent of tb
						// remove p as parent of tb
						subtraceDAG.removeParent(subtraceDAG.getNode(tb),p);
						subtraceDAG.addChild(p,tnew);
						subtraceDAG.addParent(subtraceDAG.getNode(tb), tnew);
					}
				}
				if (children_nop) {
					// tnew is parent of tb and child of root
					subtraceDAG.addChild(subtraceDAG.getRoot(), tnew);
					subtraceDAG.addParent(subtraceDAG.getNode(tb), tnew);
				}
			} else {
				tbi = NODE_IS_NOP;
				explorationMap.put(tb,tbi);
			}
			
			return tbi;
		} else {
			// WRONG!!
			System.err.println("ERROR");
		}
		
		return 3;
	}
	
	public static void addTraceToDAG(DAG<SLEXTrace> subtraceDAG,
			HashMap<Column, HashMap<String, HashSet<SLEXTrace>>> relatedMap,
			HashMap<SLEXTrace,TraceID> tracesMap,
			HashMap<SLEXTrace,Integer> tracesEventsNumMap,
			SLEXTrace tnew, TraceID tnewId) {
		/**/
		// tnew could be sub or super trace of any of his compatible and related traces
		HashSet<SLEXTrace> t2CAndR = new HashSet<>();
		HashMap<SLEXTrace,Integer> explorationMap = new HashMap<>();
		
		for (SLEXTrace tb: LogTraceSplitter.getRelatedTracesFromMap(tnewId,relatedMap)) {
			TraceID tbId = tracesMap.get(tb);
			if (LogTraceSplitter.compatibleTraces(tbId,tnewId)) {
				t2CAndR.add(tb);
				explorationMap.put(tb,NODE_IS_UNK);
			}
		}
		
		t2CAndR.remove(tnew);
		boolean no_sub_or_sup = true;
		for (SLEXTrace tb: t2CAndR) {
			int r = checkTracesInDAG(tnewId, tnew, tb, tracesMap, tracesEventsNumMap, explorationMap,subtraceDAG);
			if (r == NODE_IS_SUB || r == NODE_IS_SUP) {
				no_sub_or_sup = false;
			} else if (r != NODE_IS_NOP) {
				System.err.println("ERROR");
			}
		}
		
		if (no_sub_or_sup) {
			subtraceDAG.addChild(subtraceDAG.getRoot(),tnew);
		}
		
		DAGNode<SLEXTrace> n = subtraceDAG.getNode(tnew);
		if (n == null) {
			System.err.println("ERROR");
		}
		/**/
	}
}
