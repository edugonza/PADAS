package org.processmining.openslex;

import java.util.Map.Entry;

import org.deckfour.xes.classification.XEventAttributeClassifier;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryRegistry;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeLiteralImpl;

public class SLEXExporter {
	
	public static XLog exportPerspectiveToXLog(SLEXPerspective p) {
		XFactory xfactory = XFactoryRegistry.instance().currentDefault();
		XLog xlog = xfactory.createLog();
		String[] classifierKeys = new String[] {"1:5:TABLE_NAME","1:6:OPERATION","1:1:COLUMN_CHANGES"};
		xlog.getClassifiers().add(new XEventAttributeClassifier("Activity", classifierKeys));
		
		SLEXTraceResultSet trset = p.getTracesResultSet();
		SLEXTrace t = null;
		
		while ((t = trset.getNext()) != null) {
			
			XAttributeMap tAttrMap = xfactory.createAttributeMap();
			tAttrMap.put("caseId", new XAttributeLiteralImpl("concept:name", t.getCaseId()));
			tAttrMap.put("Id", new XAttributeLiteralImpl("Id", String.valueOf(t.getId())));
			XTrace xt = xfactory.createTrace(tAttrMap);
			
			xlog.add(xt);
			
			SLEXEventResultSet erset = t.getEventsResultSet();
			SLEXEvent e = null;
			
			while ((e = erset.getNext()) != null) {
				XAttributeMap eAttrMap = xfactory.createAttributeMap();
				XEvent xe = xfactory.createEvent(eAttrMap);
				eAttrMap.put("Id", new XAttributeLiteralImpl("Id", String.valueOf(e.getId())));
				
				for (Entry<SLEXAttribute,SLEXAttributeValue> entry: e.getAttributeValues().entrySet()) {
					String keyStr = entry.getKey().toString();
					String valStr = entry.getValue().getValue();
					if (keyStr == null) {
						keyStr = "";
					}
					if (valStr == null) {
						valStr = "";
					}
					eAttrMap.put(entry.getKey().toString(), new XAttributeLiteralImpl(keyStr,valStr));
				}
				
				xt.add(xe);
			}
		}
		
		return xlog;
	}
}
