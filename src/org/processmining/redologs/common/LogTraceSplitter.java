package org.processmining.redologs.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import org.processmining.redologs.oracle.OracleRelationsExplorer;

import com.thoughtworks.xstream.core.util.Base64Encoder;

import edu.uci.ics.jung.graph.Graph;

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
	
	
	public static Object[][] computeMetrics(File logFile, boolean new_values, boolean old_values,DataModel model) {
		try {
			XParser parser = new XesXmlParser();
			List<XLog> originalLogs = parser.parse(logFile);
			XLog originalLog = originalLogs.get(0);
			Hashtable<String,Hashtable<String,Integer>> attributesValuesCount = new Hashtable<>();
			Object[][] results = null;
			
			for (int i = 0; i < originalLog.size(); i++) {
				XTrace originalTrace = originalLog.get(i);
				for (int j = 0; j < originalTrace.size(); j++) {
					XEvent event = originalTrace.get(j);
					XAttributeMap attributes = event.getAttributes();
					XAttribute tableNameAttr = attributes.get("TABLE_NAME");
					String tableName = "";
					if (tableNameAttr instanceof XAttributeLiteral) {
						tableName = ((XAttributeLiteral) tableNameAttr).getValue();
					}
					XAttribute DBNameAttr = attributes.get("SEG_OWNER");
					String DBName = "";
					if (DBNameAttr instanceof XAttributeLiteral) {
						DBName = ((XAttributeLiteral) DBNameAttr).getValue();
					}
					
					
					/* Metrics of Keys are checked here */
					if (model != null) {
						String prefix_new = "K_NEW_";
						String prefix_old = "K_OLD_";
						List<Key> keys = model.getKeysPerTable(DBName,
								tableName);
						Base64Encoder encoder64 = new Base64Encoder();
						for (Key k : keys) {
							String keyId = k.table + "#" + "("
									+ k.typeToString() + ")" + k.name;
							String keyValNew = "";
							String keyValOld = "";
							for (Column c : k.fields) {
								XAttribute attrNew = attributes.get("V_NEW_"
										+ c.name);
								XAttribute attrOld = attributes.get("V_OLD_"
										+ c.name);
								String vNew = "";
								String vOld = "";
								if (attrNew != null) {
									if (attrNew instanceof XAttributeLiteral) {
										vNew = ((XAttributeLiteral) attrNew)
												.getValue();
									}
								}
								if (attrOld != null) {
									if (attrOld instanceof XAttributeLiteral) {
										vOld = ((XAttributeLiteral) attrOld)
												.getValue();
									}
								}
								keyValNew += ","
										+ encoder64.encode(vNew.getBytes());
								keyValOld += ","
										+ encoder64.encode(vOld.getBytes());
							}

							if (k.type == Key.FOREIGN_KEY
									&& k.refers_to != null) {
								keyId = k.refers_to.table + "#" + "("
										+ k.refers_to.typeToString() + ")"
										+ k.refers_to.name;
							}

							Hashtable<String, Integer> valuesCount;
							if (attributesValuesCount.containsKey(prefix_new
									+ keyId)) {
								valuesCount = attributesValuesCount
										.get(prefix_new + keyId);
							} else {
								valuesCount = new Hashtable<>();
								attributesValuesCount.put(prefix_new + keyId,
										valuesCount);
							}
							int count = 1;
							if (valuesCount.containsKey(keyValNew)) {
								count = valuesCount.get(keyValNew) + 1;
							}
							valuesCount.put(keyValNew, count);

							if (attributesValuesCount.containsKey(prefix_old
									+ keyId)) {
								valuesCount = attributesValuesCount
										.get(prefix_old + keyId);
							} else {
								valuesCount = new Hashtable<>();
								attributesValuesCount.put(prefix_old + keyId,
										valuesCount);
							}
							count = 1;
							if (valuesCount.containsKey(keyValOld)) {
								count = valuesCount.get(keyValOld) + 1;
							}
							valuesCount.put(keyValOld, count);
						}
					}
					/**/
					
					Iterator<Entry<String,XAttribute>> it = attributes.entrySet().iterator();
					while (it.hasNext()) {
						Entry<String,XAttribute> entry = it.next();
						Hashtable<String,Integer> valuesCount;
						String key = entry.getKey();
						
						if (((key.startsWith("V_NEW_") && new_values)) ||
								(key.startsWith("V_OLD_") && old_values)) {

							String prefix = key.substring(0, 6);
							String originalKey = key.substring(6);
							
							String canonizedKey = tableName+":"+originalKey;
							
							if (attributesValuesCount.containsKey(prefix+canonizedKey)) {
								valuesCount = attributesValuesCount.get(prefix+canonizedKey);
							} else {
								valuesCount = new Hashtable<>();
								attributesValuesCount.put(prefix+canonizedKey,valuesCount);
							}

							XAttribute valueAttr = entry.getValue();
							String value = null;
							if (valueAttr instanceof XAttributeLiteral) {
								value = ((XAttributeLiteral) valueAttr)
										.getValue();
							}

							int count = 1;
							if (valuesCount.containsKey(value)) {
								count = valuesCount.get(value) + 1;
							}
							valuesCount.put(value, count);
						}
					}
				}
			}
			
			Iterator<Entry<String, Hashtable<String, Integer>>> it = attributesValuesCount.entrySet().iterator();
			results = new Object[attributesValuesCount.size()][6];
			int i = 0;
			while (it.hasNext()) {
				Entry<String, Hashtable<String, Integer>> entry = it.next();
				
				int sum = 0;
				int num = 0;
				int min = Integer.MAX_VALUE;
				int max = 0;
				int val = 0;
				Iterator<Entry<String, Integer>> valuesIt = entry.getValue().entrySet().iterator();
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
					stdSum += Math.pow(((float) val - mean),2.0);
				}
				
				float std = (float) Math.sqrt(stdSum / (float) num);
				
				results[i] = new Object[] {entry.getKey(),new Float(mean),new Integer(num),new Integer(min),new Integer(max),new Float(std)};
				//System.out.println(entry.getKey()+" - Mean: "+mean+" Traces: "+num+" Max: "+max+" Min: "+min+" Std: "+std);
				i++;
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
		
	}
	
//	public static void main(String[] args) {
//		//String traceIdField = "V_NEW_CUSTOMER_ID";
//		String traceIdField = "V_NEW_CUSTOMER_ID";
//		String timestampField = "TIMESTAMP";
//		String orderField = "SCN";
//		String[] activityFields = new String[] {"SEG_OWNER","TABLE_NAME","OPERATION","COLUMN_CHANGES"};
//		File logFile = new File("result.xes");
//		File splittedLogFile = new File("result-splitted-"+traceIdField+".xes");
//		
//		OracleRelationsExplorer explorer = new OracleRelationsExplorer();
//		if (explorer.connect()) {
//			RelationResult result = explorer.generateRelationsGraphFromForeignKeys(true);
//			
//			explorer.showGraph(result.graph,"Relations from Foreign Keys");
//			
//			explorer.disconnect();
//		
//			FieldNameCanoniser canoniser = new FieldNameCanoniser();
//			canoniser.setRelations(result.relations);
//			
//			computeMetrics(logFile,true,true,canoniser);
//			
//		} else {
//			System.err.println("ERROR: connection failed");
//		}
//		
//		splitLog(logFile,traceIdField,orderField,timestampField,activityFields,splittedLogFile);
//		
//		
//	}

}
