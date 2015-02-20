package org.processmining.redologs.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import org.deckfour.xes.classification.XEventAttributeClassifier;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.extension.std.XLifecycleExtension;
import org.deckfour.xes.extension.std.XTimeExtension;
import org.deckfour.xes.in.XParser;
import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeLiteral;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XAttributeTimestamp;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeLiteralImpl;
import org.deckfour.xes.model.impl.XAttributeMapImpl;
import org.deckfour.xes.model.impl.XAttributeTimestampImpl;
import org.deckfour.xes.model.impl.XLogImpl;
import org.deckfour.xes.model.impl.XTraceImpl;
import org.deckfour.xes.out.XSerializer;
import org.deckfour.xes.out.XesXmlSerializer;
import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXAttributeValue;
import org.processmining.openslex.SLEXEvent;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXEventResultSet;
import org.processmining.openslex.SLEXFactory;
import org.processmining.openslex.SLEXPerspective;
import org.processmining.openslex.SLEXStorage;
import org.processmining.openslex.SLEXTrace;
import org.processmining.openslex.SLEXTraceResultSet;
import org.processmining.redologs.dag.DAG;
import org.processmining.redologs.dag.DAGNode;
import org.processmining.redologs.ui.components.DataModelList;

import com.sun.beans.finder.FieldFinder;

public class LogTraceSplitter {
	
	public static void splitLog(File logFile, DataModel model, String traceIdField, final String orderField, String timestampField, String[] activityNameFields, File splittedLogFile) {
		try {
			XParser parser = new XesXmlParser();
			List<XLog> originalLogs = parser.parse(logFile);
			XLog originalLog = originalLogs.get(0);
			XLog log = new XLogImpl(originalLog.getAttributes());
			log.getExtensions().add(XTimeExtension.instance());
			log.getExtensions().add(XConceptExtension.instance());
			log.getExtensions().add(XLifecycleExtension.instance());
			XEventClassifier classifier = new XEventAttributeClassifier("Activity", activityNameFields);
			log.getClassifiers().add(classifier);
			
			Hashtable<String,XTrace> tracesTable = new Hashtable<>();
			
			
			for (int i = 0; i < originalLog.size(); i++) {
				XTrace originalTrace = originalLog.get(i);
				for (int j = 0; j < originalTrace.size(); j++) {
					XEvent event = originalTrace.get(j);
					XAttribute idAttribute = event.getAttributes().get(traceIdField);
					if (idAttribute != null) {
						if (idAttribute instanceof XAttributeLiteral) {
							XTrace trace = null;
							String value = ((XAttributeLiteral) idAttribute).getValue();
							if (tracesTable.containsKey(value)) {
								trace = tracesTable.get(value);
							} else {
								XAttributeMap traceAttributeMap = new XAttributeMapImpl();
								XAttributeLiteral instanceAttribute = new XAttributeLiteralImpl(XConceptExtension.KEY_NAME, ((XAttributeLiteral) idAttribute).getValue()); 
								traceAttributeMap.put(instanceAttribute.getKey(),instanceAttribute);
								trace = new XTraceImpl(traceAttributeMap);
								tracesTable.put(value, trace);
								log.add(trace);
							}
							XAttribute timeAttribute = event.getAttributes().get(timestampField);
							if (timeAttribute != null) {
								if (timeAttribute instanceof XAttributeLiteral) {
									event.getAttributes().remove(timestampField);
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
									Date date = dateFormat.parse(((XAttributeLiteral) timeAttribute).getValue());
									XAttributeTimestamp timestampAttribute = new XAttributeTimestampImpl(XTimeExtension.KEY_TIMESTAMP, date);
									event.getAttributes().put(XTimeExtension.KEY_TIMESTAMP, timestampAttribute);
								}
							}
							String className = classifier.getClassIdentity(event);
							event.getAttributes().put(XConceptExtension.KEY_NAME, new XAttributeLiteralImpl(XConceptExtension.KEY_NAME,className));
							trace.add(event);
						}
					}
				}
			}
			
			Iterator<XTrace> it = log.iterator();
			while (it.hasNext()) {
				XTrace trace = it.next();
				Collections.sort(trace,new Comparator<XEvent>() {
					@Override
					public int compare(XEvent e1, XEvent e2) {
						XAttribute e1_SCN = e1.getAttributes().get(orderField);
						XAttribute e2_SCN = e2.getAttributes().get(orderField);
						if (e1_SCN != null && e2_SCN != null) {
							if (e1_SCN instanceof XAttributeLiteral && e2_SCN instanceof XAttributeLiteral) {
								String e1_SCN_value = ((XAttributeLiteral) e1_SCN).getValue();
								String e2_SCN_value = ((XAttributeLiteral) e2_SCN).getValue();
								try {
									Integer e1_SCN_int = Integer.parseInt(e1_SCN_value);
									Integer e2_SCN_int = Integer.parseInt(e2_SCN_value);
									return e1_SCN_int.compareTo(e2_SCN_int);
								} catch (Exception e) {
									// no valid integers.
								}
								return e1_SCN_value.compareTo(e2_SCN_value);
							}
						}
						return 0;
					}
				});
			}
			
			XSerializer serializer = new XesXmlSerializer();
			OutputStream splittedLogOut = new FileOutputStream(splittedLogFile);
			serializer.serialize(log, splittedLogOut);
			splittedLogOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static Object[][] computeMetrics(SLEXEventCollection collection, DataModel model, String startDate, String endDate) {
		try {
			Hashtable<SLEXAttribute,HashMap<String,Integer>> attributesValuesCount = new Hashtable<>();
			Object[][] results = null;
			SLEXEventResultSet erset = collection.getEventsResultSetBetweenDatesOrderedBy(null, startDate, endDate); //getEventsResultSet();
			SLEXEvent ev = null;
			while ((ev = erset.getNext()) != null) {
				Hashtable<SLEXAttribute, SLEXAttributeValue> attributeValues = ev.getAttributeValues();
				
				Iterator<Entry<SLEXAttribute, SLEXAttributeValue>> it = attributeValues.entrySet().iterator();
				while (it.hasNext()) {
					Entry<SLEXAttribute, SLEXAttributeValue> entry = it.next();
					HashMap<String,Integer> valuesCount;
					SLEXAttribute key = entry.getKey();
						
					if (attributesValuesCount.containsKey(key)) {
						valuesCount = attributesValuesCount.get(key);
					} else {
						valuesCount = new HashMap<>();
						attributesValuesCount.put(key,valuesCount);
					}
					SLEXAttributeValue valueAttr = entry.getValue();
					String value = valueAttr.getValue();

					int count = 1;
					if (valuesCount.containsKey(value)) {
						count = valuesCount.get(value) + 1;
					}
					valuesCount.put(value, count);
					
				}
			}

			Iterator<Entry<SLEXAttribute, HashMap<String, Integer>>> it = attributesValuesCount
					.entrySet().iterator();
			results = new Object[attributesValuesCount.size()][6];
			int i = 0;
			while (it.hasNext()) {
				Entry<SLEXAttribute, HashMap<String, Integer>> entry = it.next();

				int sum = 0;
				int num = 0;
				int min = Integer.MAX_VALUE;
				int max = 0;
				int val = 0;
				Iterator<Entry<String, Integer>> valuesIt = entry.getValue()
						.entrySet().iterator();
				while (valuesIt.hasNext()) {
					val = valuesIt.next().getValue();
					if (val > max) {
						max = val;
					}
					if (val < min) {
						min = val;
					}

					sum += val;
					num++;
				}

				float mean = (float) sum / (float) num;

				valuesIt = entry.getValue().entrySet().iterator();
				float stdSum = 0;

				while (valuesIt.hasNext()) {
					val = valuesIt.next().getValue();
					stdSum += Math.pow(((float) val - mean), 2.0);
				}

				float std = (float) Math.sqrt(stdSum / (float) num);

				results[i] = new Object[] { entry.getKey(), new Float(mean),
						new Integer(num), new Integer(min), new Integer(max),
						new Float(std) };
				i++;
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static SLEXAttributeMapper computeMapping(SLEXEventCollection ev, List<TableInfo> tables) {
		
		SLEXAttributeMapper mapper = null;
		List<Column> unpaired = new Vector<>();
		
		try {
			mapper = new SLEXAttributeMapper();
			
			for (TableInfo t: tables) {
				for (Column c: t.columns) {
					SLEXAttribute at = ev.getStorage().findAttribute(t.name, c.name);
					if (at != null) {
						mapper.put(at, c);
					} else {
						unpaired.add(c);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapper;
	}
	
	public static List<Column> canonicalize(DataModel dm, TraceIDPattern tp, TraceIDPatternElement e) {
		Vector<Column> r = new Vector<>();
		if (e.isKey()) {
			Key k = e.getKey();
			for (Column c: k.fields) {
				r.addAll(canonicalize(dm,tp,new TraceIDPatternElement(c)));
			}
		} else {
			boolean existsK = false;
			Column c = e.getColumn();
			for (Key k: dm.getKeysPerTable(c.table)) {
				if (k.fields.contains(c)) {
					if (k.type == Key.FOREIGN_KEY) {
						if (tp.containsKey(k)) {
							if (tp.containsKey(k.refers_to)) {
								r = (Vector<Column>) canonicalize(dm,tp,new TraceIDPatternElement(k.refers_to_column.get(c)));
								//r.add(k.refers_to_column.get(c));
								existsK = true;
							}
						}
					}
//					break;
				}
			}
			if (!existsK) {
				r.add(c);
			}
		}
		return r;
	}

	public static TraceID generateTraceID(TraceIDPattern tp, SLEXAttributeMapper m, SLEXTrace t) {
		TraceID tid = new TraceID(tp);
		
		SLEXEventResultSet erset = t.getEventsResultSet();
		SLEXEvent e = null;
		
		while ((e = erset.getNext()) != null) {
			if (generateTraceID(tp,tid,m,e,false) == null) {
				return null;
			}
		}
		
		return tid;
	}
	
	public static TraceID generateTraceID(TraceIDPattern tp, SLEXAttributeMapper m, SLEXEvent e) {
		TraceID tid = new TraceID(tp);
		return generateTraceID(tp, tid, m, e, false);
	}
	
	public static TraceID generateTraceID(TraceIDPattern tp, TraceID tid, SLEXAttributeMapper m, SLEXEvent e) {
		return generateTraceID(tp, tid, m, e, true);
	}
	
	private static TraceID generateTraceID(TraceIDPattern tp, TraceID tid, SLEXAttributeMapper m, SLEXEvent e, boolean cloneTID) {
		TraceID newTID = tid;
		if (cloneTID) {
			newTID = tid.clone();
		}
		
		for (Entry<SLEXAttribute, SLEXAttributeValue> ae : e.getAttributeValues().entrySet()) {
			String v = null;
			if (ae.getValue() != null && ae.getValue().getValue() != null) {
				v = ae.getValue().getValue();
			} else {
				continue;
			}
			
			Column c_mapped = m.map(ae.getKey());
			Column c_canonical = tp.getCanonical(c_mapped);
			
			if (tp.getPAList().contains(c_canonical)) {
				String tva = newTID.getValue(c_canonical);
				if (tva != null && v != null && !tva.equals(v)) {
					return null;
				} else {
					newTID.setValue(c_canonical, v);
				}
			}
		}
		
		return newTID;
	}
	
	public static boolean compatibleTraces(TraceID tidA, TraceID tidB) { 
		
		for (Column ca: tidA.getPA()) {
			String vB = tidB.getValue(ca);
			if (vB != null) {
				String vA = tidA.getValue(ca);
				if (vA != null) {
					if (!vA.equals(vB)) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean relatedTraces(TraceID tidA, TraceID tidB) {
		
		for (Column ca: tidA.getPA()) {
			String vB = tidB.getValue(ca);
			if (vB != null) {
				String vA = tidA.getValue(ca);
				if (vA != null) {
					if (vA.equals(vB)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public static boolean supertrace(TraceID tidA, TraceID tidB) {
		
		return subtrace(tidB,tidA);
	}
	
	public static boolean subtrace(TraceID tidA, TraceID tidB) {
		
		if (tidA == null) {
			return true; 
		} else if (tidB == null) {
			return false;
		}
		for (Column c: tidA.getPA()) {
			String vA = tidA.getValue(c);
			if (vA != null) {
				String vB = tidB.getValue(c);
				if (vB != null) {
					if (!vB.equals(vA)) {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		
		return true;
	}
	
	protected static void addTraceToRelatedMap(SLEXTrace trace, TraceID tid, HashMap<Column,HashMap<String,HashSet<SLEXTrace>>> relatedMap) {
		// TEST
		for (Column cpa: tid.getPA()) {
			if (tid.getValue(cpa) != null) {
				HashMap<String,HashSet<SLEXTrace>> cpaMap = relatedMap.get(cpa);
				String v = tid.getValue(cpa);
				if (cpaMap.containsKey(v)) {
					cpaMap.get(v).add(trace);
				} else {
					HashSet<SLEXTrace> tset = new HashSet<>();
					tset.add(trace);
					cpaMap.put(v, tset);
				}
			}
		}
	}
	
	protected static HashSet<SLEXTrace> getRelatedTracesFromMap(TraceID tid, HashMap<Column,HashMap<String,HashSet<SLEXTrace>>> relatedMap) {
		// TEST
		HashSet<SLEXTrace> relatedTraces = new HashSet<>();
		
		for (Column cpa: tid.getPA()) {
			if (tid.getValue(cpa) != null) {
				HashMap<String,HashSet<SLEXTrace>> cpaMap = relatedMap.get(cpa);
				String v = tid.getValue(cpa);
				if (cpaMap.containsKey(v)) {
					for (SLEXTrace t: cpaMap.get(v)) {
						relatedTraces.add(t);
					}
				}
			}
		}
		
		return relatedTraces;
	}
	
	protected static HashSet<SLEXTrace> getRelatedTracesFromMaps(TraceID tid, List<HashMap<Column,HashMap<String,HashSet<SLEXTrace>>>> relatedMaps) {
		// TEST
		
		HashSet<SLEXTrace> totalRelatedTraces = new HashSet<>();
		
		for (HashMap<Column,HashMap<String,HashSet<SLEXTrace>>> relatedMap: relatedMaps) {
			HashSet<SLEXTrace> relatedTraces = getRelatedTracesFromMap(tid,relatedMap);
			totalRelatedTraces.addAll(relatedTraces);
		}
		
		return totalRelatedTraces;
	}
	
	
	
	public static SLEXPerspective splitLog(String name, DataModel dm, SLEXEventCollection evCol, final SLEXAttributeMapper m, final TraceIDPattern tp, List<Column> orderFields, String startDate, String endDate, ProgressHandler phandler) {
		SLEXPerspective perspective = null;
		//DAGThread dagThread = new DAGThread(tp,phandler);
		try {
			long startTimeSplitting = System.currentTimeMillis();
			int i = 0;
			int MAX_ITERATIONS_PER_COMMIT = 100;
			
			int events = 0;
			int traces = 0;
			
			SLEXFactory slexFactory = new SLEXFactory(null);
			
			perspective = slexFactory.createStoragePerspective().createPerspective(evCol, name);
			final SLEXPerspective perspectiveF = perspective;
			
			final HashMap<SLEXTrace,List<Integer>> tempTraceEventsMap = new HashMap<>();
			
			perspective.getStorage().setAutoCommit(false);
			perspective.getStorage().setAutoCommitOnCreation(false);
			
			final HashMap<SLEXTrace,TraceID> tracesMap = new HashMap<>();
			
			//dagThread.start();
			final HashSet<SLEXTrace> originClonedTraces = new HashSet<>();
			
			TraceIDPatternElement root = tp.getRoot();
			List<Column> rootCanonical = canonicalize(dm, tp, root);
					
			List<SLEXAttribute> orderAtts = new ArrayList<>();
			for (Column ordC: orderFields) {
				orderAtts.add(m.map(ordC));
			}
			//m.map(orderField); // FIXME orderAt when no in map
			
			SLEXEventResultSet erset = null;
			if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				erset = evCol.getEventsResultSetBetweenDatesOrderedBy(orderAtts,startDate,endDate);
			} else {
				erset = evCol.getEventsResultSetOrderedBy(orderAtts);
			}
			SLEXEvent e = null;
			
			/**/			
			class CompatibleTask implements Task {
				
				public SLEXTrace[] relatedTraces = null;
				public int start = 0;
				public int end = 0;
				public TraceID eTID = null;
				public List<SLEXTrace> tracesC = null;
				
				@Override
				public void doTask() {
					tracesC = new  ArrayList<>();
					int i = start;
					while (i < end) {
						SLEXTrace t = relatedTraces[i];
						TraceID tid = tracesMap.get(t);
						if (compatibleTraces(tid,eTID)) {
							tracesC.add(t);
						}
						i++;
					}
				}
			}
			
			class SplitTask implements Task {
				
				public List<List<SLEXTrace>> tracesRandC = null;
				public int start = 0;
				public int end = 0;
				//public TraceID eTID = null;
				public SLEXEvent e = null;
				int traces = 0;
				public HashMap<Column,HashMap<String,HashSet<SLEXTrace>>> relatedMap = null;
				public IdDispenser idd = null;
				
				@Override
				public void doTask() {
					traces = 0;
					int i = 0;
					int j = start;
					List<SLEXTrace> l = tracesRandC.get(i);
					int pre = 0;
					while (j < end) {
						if ( (j - pre) < l.size()) {
							SLEXTrace t = l.get((j-pre));
							/**/							
							
							TraceID tid = null;
							synchronized (tracesMap) {
								tid = tracesMap.get(t);
							}
							TraceID tAndETID = generateTraceID(tp, tid, m, e);
							if (tid.equals(tAndETID)) {
								//t.add(e);
								synchronized (tempTraceEventsMap) {
									List<Integer> evList = tempTraceEventsMap.get(t);
									evList.add(e.getId());
								}
							} else {
								//SLEXTrace t2 = perspectiveF.getStorage().cloneTrace(t,idd.getNextId());
								SLEXTrace t2 = perspectiveF.getStorage().createTrace(idd.getNextId(), t.getPerspectiveId(), tAndETID.serialize());
								//t2.setCaseId(tAndETID.serialize());
								//t2.add(e);
								List<Integer> t2events = new ArrayList<>();
								t2events.addAll(tempTraceEventsMap.get(t));
								t2events.add(e.getId());
								
								synchronized (tempTraceEventsMap) {
									tempTraceEventsMap.put(t2, t2events);
								}
								
								synchronized (tracesMap) {
									tracesMap.put(t2, tAndETID); // FIXME
								}
								
								synchronized (originClonedTraces) {
									originClonedTraces.add(t); // FIXME
								}
									
								addTraceToRelatedMap(t2,tAndETID,relatedMap);
									
								traces++;
							}
								
							/**/
							j++;
						} else {
							i++;
							pre += l.size();
							l = tracesRandC.get(i);
						}
					}
				}
			}
			
			class CleanupTask implements Task {

				public SLEXTrace[] tracesArray = null;
				public List<HashMap<Column,HashMap<String,HashSet<SLEXTrace>>>> relatedMaps = null;
				public int removedTraces = 0;
				public int start = 0;
				public int end = 0;
				
				@Override
				public void doTask() {
					removedTraces = 0;
					int j = start;
					while (j < end) {
						SLEXTrace t = tracesArray[j];
						if (originClonedTraces.contains(t)) {
							synchronized (tempTraceEventsMap) {
								tempTraceEventsMap.remove(t);
							}
							//perspective.remove(t);
							removedTraces++;
							
						} else {
						
							TraceID tid = tracesMap.get(t);
							HashSet<SLEXTrace> relatedTraces = getRelatedTracesFromMaps(tid, relatedMaps);
							relatedTraces.remove(t);
							for (SLEXTrace t2: relatedTraces) {
								if (!originClonedTraces.contains(t2)) {
									if (subtrace(tid, tracesMap.get(t2))) {
										if (subtrace(tracesMap.get(t2),tid)) {
											synchronized (tempTraceEventsMap) {
												List<Integer> tevs = tempTraceEventsMap.get(t);
												List<Integer> t2evs = tempTraceEventsMap.get(t2);
												if (tevs != null && t2evs != null) {
													if (tevs.size() < t2evs.size()) {
														tempTraceEventsMap.remove(t);
														removedTraces++;
														break;
													}
												}
											}
										} else {
											//perspective.remove(t);
											synchronized (tempTraceEventsMap) {
												tempTraceEventsMap.remove(t);
											}
											removedTraces++;
											break;
										}
									}
								}
							}
						}
						j++;
					}
					
				}
				
			}
			
			int cores = Runtime.getRuntime().availableProcessors();			
			List<WorkerThread> workers = new ArrayList<>();
			
			List<CompatibleTask> compTasks = new ArrayList<>();
			List<SplitTask> splitTasks = new ArrayList<>();
			List<CleanupTask> cleanupTasks = new ArrayList<>(); 
			List<HashMap<Column,HashMap<String,HashSet<SLEXTrace>>>> relatedMaps = new ArrayList<>();
			
			IdBatchDispenser idbd = new IdBatchDispenser(perspective.getStorage().getMaxTraceId(), 2000);
			
			for (int j = 0; j < cores; j++) {
				HashMap<Column,HashMap<String,HashSet<SLEXTrace>>> relatedMap = new HashMap<>();
				
				for (Column cpa: tp.getPAList()) {
					relatedMap.put(cpa, new HashMap<String,HashSet<SLEXTrace>>());
				}
				
				relatedMaps.add(relatedMap);
				
				compTasks.add(new CompatibleTask());
				SplitTask spltTask = new SplitTask();
				spltTask.idd = idbd.getDispenser();
				splitTasks.add(spltTask);
				cleanupTasks.add(new CleanupTask());
				WorkerThread wt = new WorkerThread();
				workers.add(wt);
				wt.start();
			}
			
			IdDispenser idd = idbd.getDispenser();
			
			/**/
			
			while ((e = erset.getNext()) != null) {
				//List<SLEXTrace> tracesCAndR = new Vector<>();
				TraceID eTID = generateTraceID(tp, m, e);

				HashSet<SLEXTrace> relatedTraces = getRelatedTracesFromMaps(eTID,relatedMaps);
				SLEXTrace[] relatedTracesArray = relatedTraces.toArray(new SLEXTrace[0]);
				
				int last_end = 0;
				int amount = relatedTraces.size();
				
				for (int j = 0; j< workers.size(); j++) {
					CompatibleTask task = compTasks.get(j);
					task.relatedTraces = relatedTracesArray;
					task.eTID = eTID;
					task.start = last_end;
					task.end = task.start + (amount / (workers.size() - j));
					amount -= task.end - task.start;
					last_end = task.end;
					workers.get(j).setTask(task);
					
					System.out.println("CompatibleTask: "+j+" from "+task.start+" to "+task.end);
				}
				
				List<List<SLEXTrace>> listsTracesCandR = new  ArrayList<>();
				amount = 0;
				
				for (int j = 0; j< workers.size(); j++) {
					WorkerThread wt = workers.get(j);
					CompatibleTask task = (CompatibleTask) wt.waitUntilTaskFinished();
					List<SLEXTrace> tracesC = task.tracesC;
					listsTracesCandR.add(tracesC);
					amount += tracesC.size();
				}
			
				boolean containsRoot = true;
				for (Column c: rootCanonical) {
					if (eTID.getValue(c) == null) {
						containsRoot = false;
						break;
					}
				}
				
				if (containsRoot) {
					SLEXTrace t = perspective.getStorage().createTrace(idd.getNextId(),perspective.getId(),eTID.serialize());
					List<Integer> tevents = new ArrayList<>();
					tevents.add(e.getId());
					tempTraceEventsMap.put(t, tevents);
					//t.add(e);
					tracesMap.put(t, eTID);
					
					addTraceToRelatedMap(t,eTID,relatedMaps.get(0));
					
					traces++;
					phandler.refreshValue("Traces", String.valueOf(traces));
					i++;
				}
				
				
				last_end = 0;
			
				for (int j = 0; j< workers.size(); j++) {
					SplitTask task = splitTasks.get(j);
					task.tracesRandC = listsTracesCandR;
					//task.eTID = eTID;
					task.e = e;
					task.relatedMap = relatedMaps.get(j);
					task.start = last_end;
					task.end = task.start + (amount / (workers.size() - j));
					amount -= task.end - task.start;
					last_end = task.end;
					workers.get(j).setTask(task);
					
					System.out.println("SplitTask: "+j+" from "+task.start+" to "+task.end);
				}
				System.out.println("--");
				
				for (int j = 0; j< workers.size(); j++) {
					WorkerThread wt = workers.get(j);
					SplitTask task = (SplitTask) wt.waitUntilTaskFinished();
					traces += task.traces;
					i+=task.traces;
				}

				phandler.refreshValue("Traces", String.valueOf(traces));

				events++;
				phandler.refreshValue("Events", String.valueOf(events));
				
				if (i >= MAX_ITERATIONS_PER_COMMIT) {
					perspective.getStorage().commit();
					i=0;
				}
				
			}
			
			//dagThread.addTask(DAGThread.FINISH, null, null, null);
			
			perspective.getStorage().commit();
			
			//dagThread.join();
			
			long endTimeSplitting = System.currentTimeMillis();
			
			long durationSplitting = endTimeSplitting - startTimeSplitting;
			
			System.out.println("Splitting time: "+Utils.printTime(durationSplitting));
			System.out.println("originClonedTraces elements: "+originClonedTraces.size());
			long startTimeCleanup = System.currentTimeMillis();
			
			
			/**/
			
			int amount = tracesMap.size();
			int last_end = 0;
			
			SLEXTrace[] tracesArray = tracesMap.keySet().toArray(new SLEXTrace[0]);
			
			for (int j = 0; j< workers.size(); j++) {
				CleanupTask task = cleanupTasks.get(j);
				task.tracesArray = tracesArray;
				task.relatedMaps = relatedMaps;
				task.start = last_end;
				task.end = task.start + (amount / (workers.size() - j));
				amount -= task.end - task.start;
				last_end = task.end;
				workers.get(j).setTask(task);
				
				System.out.println("CleanupTask: "+j+" from "+task.start+" to "+task.end);
			}
			System.out.println("--");
			
			int removedTraces = 0;
			
			for (int j = 0; j< workers.size(); j++) {
				WorkerThread wt = workers.get(j);
				CleanupTask task = (CleanupTask) wt.waitUntilTaskFinished();
				removedTraces += task.removedTraces;
				i+=task.removedTraces;
			}
						
			phandler.refreshValue("RemovedTraces", String.valueOf(removedTraces));
			
			/**/

			for (int j = 0; j < workers.size(); j++) {
				workers.get(j).stopThread();
			}
			
			long endTimeCleanup = System.currentTimeMillis();
			
			long durationCleanup = endTimeCleanup - startTimeCleanup;
			
			System.out.println("Cleanup time: "+Utils.printTime(durationCleanup));

			long startTimeCommit = System.currentTimeMillis();
			
			perspective.getStorage().setAutoCommitOnCreation(true);
			
			for (SLEXTrace t: tempTraceEventsMap.keySet()) {
				t.commit();
				List<Integer> evlist = tempTraceEventsMap.get(t);
				for (Integer eId: evlist) {
					perspective.getStorage().addEventToTrace(t.getId(), eId);
				}
			}
			
			perspective.getStorage().commit();
		
			long endTimeCommit = System.currentTimeMillis();
			
			long durationCommit = endTimeCommit - startTimeCommit;
			
			System.out.println("Commit time: "+Utils.printTime(durationCommit));

			
		} catch (Exception e) {
			e.printStackTrace();
			//dagThread.interrupt();
		} finally {
			perspective.getStorage().commit();
			perspective.getStorage().setAutoCommit(true);
		}
		
		return perspective;
	}


	private static int successionArcs = 0; // FIXME
	public static void computeMatrix(File outputFile, DataModel dm,
			SLEXEventCollection evCol, SLEXAttributeMapper m,
			TraceIDPattern tp, List<Column> orderFields, String startDate,
			String endDate, ProgressHandler phandler) {
		
		try {
			long startTimeSplitting = System.currentTimeMillis();
			
			int events = 0;
			int eventsInMap = 0; // FIXME
			successionArcs = 0;
			
			TraceIDPatternElement root = tp.getRoot();
			List<Column> rootCanonical = canonicalize(dm, tp, root);
					
			List<SLEXAttribute> orderAtts = new ArrayList<>();
			for (Column ordC: orderFields) {
				orderAtts.add(m.map(ordC));
			}
				
			SLEXEventResultSet erset = null;
			if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				erset = evCol.getEventsResultSetBetweenDatesOrderedBy(orderAtts,startDate,endDate);
			} else {
				erset = evCol.getEventsResultSetOrderedBy(orderAtts);
			}
			SLEXEvent e = null;
				
			HashMap<Column,HashMap<String,HashSet<SLEXEvent>>> relatedEventsMap = new HashMap<>();
			
			HashMap<Integer,SLEXEvent> eventsMap = new HashMap<>();
			HashMap<Integer,TraceID> eventsTraceIDMap = new HashMap<>();
			HashMap<Integer,ArrayList<Integer>> eventFollowersMap = new HashMap<>();
			
			HashMap<String,HashMap<String,Integer>> dfMatrix = new HashMap<>();
			HashMap<String,Integer> startMap = new HashMap<>();
			HashMap<String,Integer> endMap = new HashMap<>();
			
			ArrayList<SLEXAttribute> activityAttributes = new ArrayList<>();
			
			activityAttributes.add(evCol.getStorage().findAttribute("COMMON", "COLUMN_CHANGES"));
			activityAttributes.add(evCol.getStorage().findAttribute("COMMON", "TABLE_NAME"));
			activityAttributes.add(evCol.getStorage().findAttribute("COMMON", "OPERATION"));
			
			/**/
				
			while ((e = erset.getNext()) != null) {
				//List<SLEXTrace> tracesCAndR = new Vector<>();
				TraceID eTID = generateTraceID(tp, m, e);
				
				HashSet<SLEXEvent> relatedEvents = getRelatedEventsFromMap(eTID, relatedEventsMap);
				
				HashMap<SLEXEvent,Boolean> visited = new HashMap<>();
				
				boolean found = false;
				
				for (SLEXEvent re: relatedEvents) {
					if (recursiveCheckEventTrace(e,eTID,re,visited,eventsTraceIDMap,eventFollowersMap,
							eventsMap,dfMatrix,endMap,activityAttributes,phandler)) {
						found = true;
					}
				}
				
				boolean containsRoot = true;
				
				if (!found) {
					
					for (Column c: rootCanonical) {
						if (eTID.getValue(c) == null) {
							containsRoot = false;
							break;
						}
					}
				
				}
				
				if (containsRoot || found) {
					eventsTraceIDMap.put(e.getId(), eTID);
					eventsMap.put(e.getId(), e);
					
					eventsInMap++;
					phandler.refreshValue("Traces", String.valueOf(eventsInMap));
					
					String activity = getActivityFromEvent(e, activityAttributes);
					
					Integer ve = endMap.get(activity);
					if (ve == null) {
						ve = 0;
					}
					ve++;
					endMap.put(activity, ve);
					
					if (!found && containsRoot) {
						addEventToRelatedMap(e, eTID, relatedEventsMap); // This ensures only the beginning of each trace can be found and the Graph is explored starting there.
						
						Integer vs = startMap.get(activity);
						if (vs == null) {
							vs = 0;
						}
						vs++;
						startMap.put(activity, vs);
					}
				}
				
				events++;
				phandler.refreshValue("Events", String.valueOf(events));
					
			}
			
			long endTimeSplitting = System.currentTimeMillis();
				
			long durationSplitting = endTimeSplitting - startTimeSplitting;
				
			System.out.println("Splitting time: "+Utils.printTime(durationSplitting));
							
			printDfg(outputFile,dfMatrix,startMap,endMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;

	}

	private static boolean recursiveCheckEventTrace(SLEXEvent e, TraceID eTID,
			SLEXEvent re, HashMap<SLEXEvent,Boolean> visited,
			HashMap<Integer, TraceID> eventsTraceIDMap,
			HashMap<Integer, ArrayList<Integer>> eventFollowersMap,
			HashMap<Integer,SLEXEvent> eventsMap,
			HashMap<String,HashMap<String,Integer>> dfMatrix,
			HashMap<String,Integer> endMap,
			ArrayList<SLEXAttribute> activityAttributes,
			ProgressHandler phandler) {
		boolean found = false;
		if (!visited.containsKey(re)) {
			
			TraceID reTID = eventsTraceIDMap.get(re.getId());
			if (compatibleTraces(reTID, eTID)) {
				if (eventFollowersMap.containsKey(re.getId())) {
					ArrayList<Integer> followers = eventFollowersMap.get(re.getId());
					for (Integer eid: followers) {
						SLEXEvent fev = eventsMap.get(eid);
						if (recursiveCheckEventTrace(e, eTID, fev, visited, eventsTraceIDMap,
								eventFollowersMap, eventsMap,dfMatrix,endMap,activityAttributes,phandler)) {
							found = true;
						}
					}
				}
				
				if (!found) {
					String reActivity = getActivityFromEvent(re,activityAttributes);
					String eActivity = getActivityFromEvent(e,activityAttributes);
					
					ArrayList<Integer> followers = eventFollowersMap.get(re.getId());
					
					if (followers == null) {
						followers = new ArrayList<>();
						eventFollowersMap.put(re.getId(), followers);
						
						Integer ve = endMap.get(reActivity);
						if (ve == null || ve == 0) {
							ve = 1;
						}
						ve--;
						endMap.put(reActivity,ve);
					}
					followers.add(e.getId());
					
					HashMap<String, Integer> row = dfMatrix.get(reActivity);
					if (row == null) {
						row = new HashMap<>();
						dfMatrix.put(reActivity, row);
					}
					
					Integer count = 0;
					
					if (row.containsKey(eActivity)) {
						count = row.get(eActivity);
					}
					
					count++;
					row.put(eActivity, count);
					
					found = true;
					
					successionArcs++;
					phandler.refreshValue("RemovedTraces", String.valueOf(successionArcs));
				}
				
			} else {
				found = false;
			}
			
			visited.put(re,found);
		} else {
			found = visited.get(re);
		}
		
		return found;
	}
	
	private static void printDfg(File outputFile, HashMap<String,HashMap<String,Integer>> dfMatrix, HashMap<String,Integer> startMap, HashMap<String,Integer> endMap) {
		StringBuffer fileOutput = new StringBuffer();
		
		ArrayList<String> activities = new ArrayList<>();
		int i = dfMatrix.size();
		for (String activity: dfMatrix.keySet()) {
			activities.add(activity);
			fileOutput.append(activity);
			i--;
			if (i > 0) {
				fileOutput.append(",");
			}
		}
		fileOutput.append('\n');
		
		// Include line with start activities
		i = activities.size();
		for (String activity: activities) {
			Integer v = startMap.get(activity);
			if (v == null) {
				v = 0;
			}
			fileOutput.append(v);
			i--;
			if (i > 0) {
				fileOutput.append(",");
			}
		}
		fileOutput.append('\n');
		
		// Include line with end activities
		i = activities.size();
		for (String activity: activities) {
			Integer v = endMap.get(activity);
			if (v == null) {
				v = 0;
			}
			fileOutput.append(v);
			i--;
			if (i > 0) {
				fileOutput.append(",");
			}
		}
		fileOutput.append('\n');
		
		// Include lines with edges
		
		for (String activity: activities) {
			HashMap<String, Integer> row = dfMatrix.get(activity);
			i = activities.size();
			for (String a: activities) {
				Integer v = 0;
				if (row != null) {
					v = row.get(a);
				}
				if (v == null) {
					v = 0;
				}
				fileOutput.append(v);
				i--;
				if (i > 0) {
					fileOutput.append(",");
				}
			}
			fileOutput.append('\n');
		}
		
		System.out.println(fileOutput);
		
		if (outputFile != null) {
			try {
				FileWriter w = new FileWriter(outputFile);
				w.write(fileOutput.toString());
				w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	protected static String getActivityFromEvent(SLEXEvent e,ArrayList<SLEXAttribute> activityAttributes) {
		String activity = "";
		
		Hashtable<SLEXAttribute, SLEXAttributeValue> attributes = e.getAttributeValues();
		
		for (SLEXAttribute at: activityAttributes) {
			SLEXAttributeValue v = attributes.get(at);
			activity += v.getValue()+"+";
		}
		
		return activity;
	}
	
	protected static void addEventToRelatedMap(SLEXEvent e, TraceID tid, HashMap<Column,HashMap<String,HashSet<SLEXEvent>>> relatedMap) {
		// TEST
		for (Column cpa: tid.getPA()) {
			if (tid.getValue(cpa) != null) {
				HashMap<String,HashSet<SLEXEvent>> cpaMap = relatedMap.get(cpa);
				String v = tid.getValue(cpa);
				if (cpaMap == null) {
					cpaMap = new HashMap<>();
					relatedMap.put(cpa, cpaMap);
				}
				if (cpaMap.containsKey(v)) {
					cpaMap.get(v).add(e);
				} else {
					HashSet<SLEXEvent> eset = new HashSet<>();
					eset.add(e);
					cpaMap.put(v, eset);
				}
			}
		}
	}
	
	protected static HashSet<SLEXEvent> getRelatedEventsFromMap(TraceID tid, HashMap<Column,HashMap<String,HashSet<SLEXEvent>>> relatedMap) {
		// TEST
		HashSet<SLEXEvent> relatedEvents = new HashSet<>();
		
		for (Column cpa: tid.getPA()) {
			if (tid.getValue(cpa) != null) {
				HashMap<String,HashSet<SLEXEvent>> cpaMap = relatedMap.get(cpa);
				String v = tid.getValue(cpa);
				if (cpaMap != null && cpaMap.containsKey(v)) {
					for (SLEXEvent e: cpaMap.get(v)) {
						relatedEvents.add(e);
					}
				}
			}
		}
		
		return relatedEvents;
	}


	public static void computeInteractionMatrix(SLEXPerspective perspectiveA,
			SLEXPerspective perspectiveB,
			TraceIDPattern tp,
			//List<Column> sortFields,
			SLEXAttributeMapper m) {

		try {
			long startTime = System.currentTimeMillis();
			
//			List<SLEXAttribute> orderAtts = new ArrayList<>();
//			for (Column ordC : sortFields) {
//				orderAtts.add(m.map(ordC));
//			}

			// Go through all traces in Perspective A and build an index with
			// the new TP
			
			HashMap<SLEXTrace,TraceID> tracesMapA = new HashMap<>();
			HashMap<Column, HashMap<String, HashSet<SLEXTrace>>> relatedMapA = new HashMap<>();
			
			SLEXTraceResultSet trset = perspectiveA.getTracesResultSet();
			SLEXTrace t = null;

			while ((t = trset.getNext()) != null) {
				TraceID tid = generateTraceID(tp, m, t);
				tracesMapA.put(t, tid);
				addTraceToRelatedMap(t, tid, relatedMapA);
			}
			
			// Go through all traces in Perspective B and build another index
			// with the new TP

			HashMap<SLEXTrace,TraceID> tracesMapB = new HashMap<>();
			HashMap<Column, HashMap<String, HashSet<SLEXTrace>>> relatedMapB = new HashMap<>();
			
			trset = perspectiveB.getTracesResultSet();
			t = null;

			while ((t = trset.getNext()) != null) {
				TraceID tid = generateTraceID(tp, m, t);
				tracesMapB.put(t, tid);
				addTraceToRelatedMap(t, tid, relatedMapB);
			}
			
			// Go through all traces in A and find for each of them the set of
			// traces in B which are related and compatible
			// For each pair, find DFG between activities of A and activities of
			// B

			HashMap<String, HashMap<String, Integer>> dfMatrix = new HashMap<>();
			HashMap<String, Integer> startMap = new HashMap<>();
			HashMap<String, Integer> endMap = new HashMap<>();
			
			for (Entry<SLEXTrace, TraceID> entry: tracesMapA.entrySet()) {
				SLEXTrace tA = entry.getKey();
				TraceID tidA = entry.getValue();
				HashSet<SLEXTrace> relatedB = getRelatedTracesFromMap(tidA, relatedMapB);
				
				ArrayList<SLEXEvent> eventsA = getEventsFromTrace(tA);
				
				// Sort eventsA by ORDER fields
				// TODO
				
				for (SLEXTrace tB: relatedB) {
					TraceID tidB = tracesMapB.get(tB);
					if (compatibleTraces(tidA, tidB)) {
						
						// Obtain tB events, add eventsA to the list, sort by ORDER, and count DFG
						// TODO
					}
				}
			}
			
			// Print Computation time
			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			
			System.out.println("Interaction Matrix computation time: "+Utils.printTime(duration));
			
			// Print DFG
			printDfg(null, dfMatrix, startMap, endMap );
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
	}
	
	private static ArrayList<SLEXEvent> getEventsFromTrace(SLEXTrace t) {
		ArrayList<SLEXEvent> eventsList = new ArrayList<>();
		
		SLEXEventResultSet erset = t.getEventsResultSet();
		SLEXEvent e = null;
		
		while ((e = erset.getNext()) != null) {
			eventsList.add(e);
		}
		
		return eventsList;
	}
}
