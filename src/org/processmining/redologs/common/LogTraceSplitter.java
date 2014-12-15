package org.processmining.redologs.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
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
import org.processmining.openslex.SLEXPerspective;
import org.processmining.openslex.SLEXStorage;
import org.processmining.openslex.SLEXTrace;
import org.processmining.redologs.dag.DAG;
import org.processmining.redologs.dag.DAGNode;

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
			if (ae.getValue() != null) {
				v = ae.getValue().getValue();
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
	
	
	
	
	public static SLEXPerspective splitLog(String name, DataModel dm, SLEXEventCollection evCol, SLEXAttributeMapper m, TraceIDPattern tp, List<Column> orderFields, String startDate, String endDate, ProgressHandler phandler) {
		SLEXPerspective perspective = null;
		DAGThread dagThread = new DAGThread(tp);
		try {
			evCol.getStorage().setAutoCommit(false);
			int i = 0;
			int MAX_ITERATIONS_PER_COMMIT = 100;
			
			perspective = evCol.getStorage().createPerspective(evCol,name);
			
			HashMap<SLEXTrace,TraceID> tracesMap = new HashMap<>();
		
			HashMap<Column,HashMap<String,HashSet<SLEXTrace>>> relatedMap = new HashMap<>();
			
			for (Column cpa: tp.getPAList()) {
				relatedMap.put(cpa, new HashMap<String,HashSet<SLEXTrace>>());
			}
			
			dagThread.start();
			
			TraceIDPatternElement root = tp.getRoot();
			List<Column> rootCanonical = canonicalize(dm, tp, root);
					
			List<SLEXAttribute> orderAtts = new Vector<>();
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
			
			int events = 0;
			int traces = 0;
			
			while ((e = erset.getNext()) != null) {
				//List<SLEXTrace> traces = filterCompatibleAndRelated(tracesMap,e);				
				List<SLEXTrace> tracesCAndR = new Vector<>();
				TraceID eTID = generateTraceID(tp, m, e);
				
				for (SLEXTrace t: getRelatedTracesFromMap(eTID,relatedMap)) {
					if (compatibleTraces(tracesMap.get(t),eTID)) {
						tracesCAndR.add(t);
					}
				}
			
				boolean containsRoot = true;
				for (Column c: rootCanonical) {
					if (eTID.getValue(c) == null) {
						containsRoot = false;
						break;
					}
				}
				
				if (containsRoot) {
					SLEXTrace t = evCol.getStorage().createTrace(perspective.getId(),eTID.serialize());
					t.add(e);
					tracesMap.put(t, eTID);
					
					addTraceToRelatedMap(t,eTID,relatedMap);
					dagThread.addTask(DAGThread.ADD_TO_MAP,t,eTID);
					
					if (tracesCAndR.isEmpty()) {
						/**/
						// No trace is compatible and related => no trace is sub or super trace of t 
						// Therefore, we add it as a child of root
						dagThread.addTask(DAGThread.ADD_CHILD_TO_ROOT,t,eTID);
						//subtraceDAG.addChild(subtraceDAG.getRoot(),t);
						/**/
					} else {
						dagThread.addTask(DAGThread.ADD_TO_DAG,t,eTID);
						//addTraceToDAG(subtraceDAG,relatedMap,tracesMap,t,eTID);
					}
					
					traces++;
					phandler.refreshValue("Traces", String.valueOf(traces));
					i++;
				}
				
				if (!tracesCAndR.isEmpty()) {
					for (SLEXTrace t: tracesCAndR) {
						TraceID tid = tracesMap.get(t);
						TraceID tAndETID = generateTraceID(tp, tid, m, e);
						if (tid.equals(tAndETID)) {
							t.add(e);
						} else {
							SLEXTrace t2 = evCol.getStorage().cloneTrace(t);
							t2.setCaseId(tAndETID.serialize());
							t2.commit();
							t2.add(e);
							tracesMap.put(t2, tAndETID);
						
							addTraceToRelatedMap(t2,tAndETID,relatedMap);
							dagThread.addTask(DAGThread.ADD_TO_MAP,t2,tAndETID);
						
							dagThread.addTask(DAGThread.ADD_TO_DAG,t2,tAndETID);
							//addTraceToDAG(subtraceDAG,relatedMap,tracesMap,t2,tAndETID);
						
							traces++;
							phandler.refreshValue("Traces", String.valueOf(traces));
						}
						i++;
					}
				}
				
				events++;
				phandler.refreshValue("Events", String.valueOf(events));
				
				if (i >= MAX_ITERATIONS_PER_COMMIT) {
					evCol.getStorage().commit();
					i=0;
				}
			}
			
			dagThread.addTask(DAGThread.FINISH, null, null);
			
			evCol.getStorage().commit();
			
			dagThread.join();
			
			int removedTraces = 0;
			for (SLEXTrace t: tracesMap.keySet()) {
				if (!dagThread.getDAG().getNode(t).getChildren().isEmpty()) {
					perspective.remove(t);
					removedTraces++;
					phandler.refreshValue("RemovedTraces", String.valueOf(removedTraces));
					
					i++;
					if (i >= MAX_ITERATIONS_PER_COMMIT) {
						evCol.getStorage().commit();
						i=0;
					}
				}
			}
			
			evCol.getStorage().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			dagThread.interrupt();
		} finally {
			evCol.getStorage().commit();
			evCol.getStorage().setAutoCommit(true);
		}
		
		return perspective;
	}

}
