package org.processmining.openslex;

import java.util.List;
import java.util.Map.Entry;

import org.deckfour.xes.classification.XEventAttributeClassifier;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeLiteralImpl;
import org.deckfour.xes.model.impl.XAttributeMapImpl;
import org.deckfour.xes.model.impl.XEventImpl;
import org.deckfour.xes.model.impl.XLogImpl;
import org.deckfour.xes.model.impl.XTraceImpl;

public class SLEXExporter {
	
	public static XLog exportPerspectiveToXLog(SLEXPerspective p) {
		XAttributeMap lAttrMap = new XAttributeMapImpl();
		XLog xlog = new XLogImpl(lAttrMap);
		XEventClassifier classifier = new XEventAttributeClassifier("Activity", "1:5:TABLE_NAME 1:6:OPERATION 1:1:COLUMN_CHANGES");
		XLogInfo xlogInfo = XLogInfoFactory.createLogInfo(xlog, classifier);
		xlog.setInfo(classifier, xlogInfo);
		
		List<XEventClassifier> classifiersList = xlog.getClassifiers();
		
		SLEXTraceResultSet trset = p.getTracesResultSet();
		SLEXTrace t = null;
		
		while ((t = trset.getNext()) != null) {
			
			XAttributeMap tAttrMap = new XAttributeMapImpl();
			tAttrMap.put("caseId", new XAttributeLiteralImpl("caseId", t.getCaseId()));
			tAttrMap.put("Id", new XAttributeLiteralImpl("Id", String.valueOf(t.getId())));
			XTrace xt = new XTraceImpl(tAttrMap);
			
			xlog.add(xt);
			
			SLEXEventResultSet erset = t.getEventsResultSet();
			SLEXEvent e = null;
			
			while ((e = erset.getNext()) != null) {
				XEvent xe = new XEventImpl();
				XAttributeMap eAttrMap = new XAttributeMapImpl();
				eAttrMap.put("Id", new XAttributeLiteralImpl("Id", String.valueOf(e.getId())));
				
				for (Entry<SLEXAttribute,SLEXAttributeValue> entry: e.getAttributeValues().entrySet()) {
					eAttrMap.put(entry.getKey().toString(), new XAttributeLiteralImpl(entry.getKey().toString(),entry.getValue().getValue()));
				}
				
				xe.setAttributes(eAttrMap);
				
				xt.add(xe);
			}
		}
		
		return xlog;
	}
}
