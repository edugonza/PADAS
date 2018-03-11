package org.processmining.openslex;
/*
 * 
 */


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map.Entry;

import org.deckfour.xes.classification.XEventAttributeClassifier;
import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.extension.std.XTimeExtension;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.model.XAttributeLiteral;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XAttributeTimestamp;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.processmining.xeslite.external.XFactoryExternalStore;

// TODO: Auto-generated Javadoc
/**
 * The Class SLEXExporter.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public class SLEXExporter {
	
	/**
	 * Export perspective to x log.
	 *
	 * @param p the p
	 * @return the x log
	 */
	public static XLog exportPerspectiveToXLog(SLEXPerspective p) {
		XLog xlog = null;
		SLEXStoragePerspective storage = p.getStorage();
		try {
			SLEXStorageCollection storageEvCol = new SLEXStorageImpl(storage.getPath(),p.getCollectionFileName(),SLEXStorage.TYPE_COLLECTION);
			//XFactory xfactory = XFactoryRegistry.instance().currentDefault();
			XFactory xfactory = new XFactoryExternalStore.MapDBDiskImpl();
			xlog = xfactory.createLog();
		
			SLEXAttribute table_nameAttr = storageEvCol.findAttribute("COMMON", "TABLE_NAME");
			SLEXAttribute operationAttr = storageEvCol.findAttribute("COMMON", "OPERATION");
			SLEXAttribute column_changesAttr = storageEvCol.findAttribute("COMMON", "COLUMN_CHANGES");
			SLEXAttribute timestampAttr = storageEvCol.findAttribute("COMMON", "TIMESTAMP");
			DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
			
			String[] classifier1Keys = new String[] {"_"+table_nameAttr.toString().replace(':','_'),"_"+operationAttr.toString().replace(':','_'),"_"+column_changesAttr.toString().replace(':','_')};
			String[] classifier2Keys = new String[] {"_"+table_nameAttr.toString().replace(':','_'),"_"+operationAttr.toString().replace(':','_')};
			xlog.getClassifiers().add(new XEventAttributeClassifier("Activity with changes vector", classifier1Keys));
			xlog.getClassifiers().add(new XEventAttributeClassifier("Activity without changes vector", classifier2Keys));
		
			SLEXTraceResultSet trset = p.getTracesResultSet();
			SLEXTrace t = null;
		
			while ((t = trset.getNext()) != null) {
			
				XAttributeMap tAttrMap = xfactory.createAttributeMap();
				XAttributeLiteral caseIdAttr = xfactory.createAttributeLiteral("concept:name", t.getCaseId(), XConceptExtension.instance());
				XAttributeLiteral idAttr = xfactory.createAttributeLiteral("Id", String.valueOf(t.getId()), null);
				tAttrMap.put("caseId", caseIdAttr);
				tAttrMap.put("Id", idAttr);
				XTrace xt = xfactory.createTrace(tAttrMap);
			
				xlog.add(xt);
			
				SLEXEventResultSet erset = t.getEventsResultSet();
				SLEXEvent e = null;
			
				while ((e = erset.getNext()) != null) {
					XAttributeMap eAttrMap = xfactory.createAttributeMap();
					XEvent xe = xfactory.createEvent();
					XAttributeLiteral eidAttr = xfactory.createAttributeLiteral("Id", String.valueOf(e.getId()), null);
//					if (timestampAttr != null) {
//						XAttributeTimestamp etsAttr = xfactory.createAttributeTimestamp("time:timestamp", , null);
//						eAttrMap.put("time:timestamp", etsAttr);
//					}
					eAttrMap.put("Id", eidAttr);
					
				
					for (Entry<SLEXAttribute,SLEXAttributeValue> entry: e.getAttributeValues().entrySet()) {
						String keyStr = "_"+entry.getKey().toString().replace(':','_');
						
						String valStr = entry.getValue().getValue();
						if (valStr == null) {
							valStr = "";
						}
						eAttrMap.put(keyStr, xfactory.createAttributeLiteral(keyStr, valStr, null));
					}
					SLEXAttributeValue timeVal = e.getAttributeValues().get(timestampAttr);
					Date datetime = dformat.parse(timeVal.getValue());
					XAttributeTimestamp time = xfactory.createAttributeTimestamp(XTimeExtension.KEY_TIMESTAMP,datetime,XTimeExtension.instance());
					eAttrMap.put(time.getKey(), time);
					xe.setAttributes(eAttrMap);
					xt.add(xe);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xlog;
	}
}
