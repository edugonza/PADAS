package org.processmining.database.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXAttributeValue;
import org.processmining.openslex.SLEXEvent;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXEventResultSet;
import org.processmining.openslex.SLEXPerspective;
import org.processmining.openslex.SLEXTrace;
import org.processmining.openslex.SLEXTraceResultSet;
import org.processmining.openslex.metamodel.*;
import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.LogTraceSplitter;
import org.processmining.redologs.common.SLEXAttributeMapper;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.common.TraceID;
import org.processmining.redologs.common.TraceIDPattern;

public class MetaModelPopulator {

	private DataModel dm;
	private SLEXEventCollection evCol;
	private MetaModel mm;
	private List<SLEXAttribute> orderAttributes;
	private List<SLEXAttribute> activityAttributes;
	private SLEXAttributeMapper mapper;
	private TableInfo commonTable;
	private SLEXPerspective perspective;
	
	public MetaModelPopulator(TableInfo common, DataModel dm,
			SLEXEventCollection evCol, List<Column> orderColumns,
			SLEXAttributeMapper mapper, List<Column> activityColumns,
			SLEXPerspective perspective) {
		
		this.evCol = evCol;
		this.mm = new MetaModel();
		this.mapper = mapper;
		this.dm = dm;
		this.commonTable = common;
		this.perspective = perspective;
		
		orderAttributes = new ArrayList<>();
		for (Column ordC: orderColumns) {
			orderAttributes.add(mapper.map(ordC));
		}
		
		activityAttributes = new ArrayList<>();
		for (Column actC: activityColumns) {
			activityAttributes.add(mapper.map(actC));
		}
	}
	
	public TableInfo getTableFromEvent(SLEXEvent e, SLEXAttributeMapper m) {
		TableInfo t = null;
		
		for (SLEXAttribute a: e.getAttributeValues().keySet()) {
			Column c = m.map(a);
			if (c != null) {
				if (c.table != null && c.table != commonTable) {
					return c.table;
				}
			}
		}
		
		return t;
	}
	
	public DataModel getDataModel() {
		return this.dm;
	}
	
	public void computeMetaModel() {
		
		SLEXEventResultSet evrset = evCol.getEventsResultSetOrderedBy(orderAttributes);
		
		HashMap<TableInfo,HashSet<TraceID>> objects = new HashMap<>();
		HashSet<Relation> relationsSet = new HashSet<>();
		HashMap<TableInfo,HashMap<TraceID,LinkedHashSet<ObjectVersion>>> objectVersions = new HashMap<>();
		//HashMap<SLEXEvent,String> eventActivityMap = new HashMap<>();
		HashMap<SLEXEvent,ActivityInstance> eventActivityInstanceMap = new HashMap<>();
		HashSet<String> activitySet = new HashSet<>();
		HashMap<SLEXTrace,HashSet<ActivityInstance>> caseToActivityInstancesMap = new HashMap<>(); 
		
		this.mm.objects = objects;
		this.mm.relations = relationsSet;
		this.mm.objectVersions = objectVersions;
		this.mm.dm = dm;
		//this.mm.slxdm = slxdm;
		this.mm.evCol = evCol;
		this.mm.mapper = mapper;
		this.mm.activitySet = activitySet;
		this.mm.caseToActivityInstancesMap = caseToActivityInstancesMap;
		this.mm.eventActivityInstanceMap = eventActivityInstanceMap;
		
		SLEXEvent e = null;
		
		int order = 0;
		
		while ((e = evrset.getNext()) != null) {
			// Get the class for the event			
			TableInfo t = getTableFromEvent(e,mapper);

			// Find event Activity and create ActivityInstance
			String activityName = getActivityNameFromEvent(e,activityAttributes);
			if (!activitySet.contains(activityName)) {
				activitySet.add(activityName);
			}
			ActivityInstance actIns = new ActivityInstance(activityName); 
			eventActivityInstanceMap.put(e,actIns);
			
			List<Key> fks = new ArrayList<>();
			// Get the PK of such class
			List<Key> keys = dm.getKeysPerTable(t);
			Key pk = null;
			for (Key k: keys) {
				if (k.type == Key.PRIMARY_KEY) {
					pk = k;
				} else if (k.type == Key.FOREIGN_KEY) {
					fks.add(k);
				}
			}
			
			if (pk == null) {
				continue;
			}
			
			// Get the Object ID
			TraceIDPattern tp = new TraceIDPattern(dm);
			tp.add(pk);
			TraceID eID = LogTraceSplitter.generateTraceID(tp,mapper,e);
			
			// If object does not exist
			if (!objects.containsKey(t)) {
				objects.put(t, new HashSet<TraceID>());
			}
			HashSet<TraceID> objs = objects.get(t);
			
			if (!objs.contains(eID)) {
				// Create Object
				objs.add(eID);
			}
			
			// Create version for object
			// Link event to version
			ObjectVersion objVersion = new ObjectVersion(eID, e, order);
			order++;
			if (!objectVersions.containsKey(t)) {
				objectVersions.put(t, new HashMap<TraceID,LinkedHashSet<ObjectVersion>>());
			}
			HashMap<TraceID,LinkedHashSet<ObjectVersion>> objectsForTable = objectVersions.get(t);
			if (!objectsForTable.containsKey(eID)) {
				objectsForTable.put(eID, new LinkedHashSet<ObjectVersion>());
			}
			LinkedHashSet<ObjectVersion> objVersions = objectsForTable.get(eID);
			objVersions.add(objVersion);
			
			for (SLEXAttribute a: e.getAttributeValues().keySet()) {
				Column c = mapper.map(a);
				if (c != null && c.table == t) {
					SLEXAttributeValue v = e.getAttributeValues().get(a);
					String vStr = v.getValue();
					objVersion.addAttributeValue(c, vStr);
				}				
			}
			
			// Get FKs of class
			// Get relations. For each:
			for (Key fk: fks) {
				// Find relation and referred object
				TraceIDPattern tpfk = new TraceIDPattern(dm);
				tpfk.add(fk);
				TraceID fkeID = LogTraceSplitter.generateTraceID(tpfk, mapper, e);
				
				Key referred_pk = fk.refers_to;
				
				TraceIDPattern referredPKTP = new TraceIDPattern(dm);
				referredPKTP.add(referred_pk);
				TraceID referredPkID = new TraceID(referredPKTP);
				for (Column lc: fk.fields) {
					Column rc = fk.refers_to_column.get(lc);
					referredPkID.setValue(rc, fkeID.getValue(lc));
				}
				
				/**/
				if (!objects.containsKey(referred_pk.table)) {
					objects.put(referred_pk.table, new HashSet<TraceID>());
				}
				if (objects.get(referred_pk.table).contains(referredPkID)) {
					//System.out.println("Contained");
				} else {
					//System.out.println("Not Contained");
					objects.get(referred_pk.table).add(referredPkID);
				}
				/**/
				
				Relation r = new Relation(objVersion,getLastObjectVersion(referred_pk.table,referredPkID,objectVersions));
				
				relationsSet.add(r);
								
			}
			
		}
		
		// Get cases and find relation to Activity Instances
		if (perspective != null) {
			SLEXTraceResultSet trset = perspective.getTracesResultSet();
			SLEXTrace tr = null;
			while ((tr = trset.getNext()) != null) {
				if (!caseToActivityInstancesMap.containsKey(tr)) {
					caseToActivityInstancesMap.put(tr, new HashSet<ActivityInstance>());
				}
				HashSet<ActivityInstance> aiSet = caseToActivityInstancesMap.get(tr);
			
				SLEXEventResultSet erset = tr.getEventsResultSet();
				SLEXEvent ev = null;
				while ((ev = erset.getNext()) != null) {
					ActivityInstance ai = eventActivityInstanceMap.get(ev);
					if (ai != null) {
						aiSet.add(ai);
					}
				}
			}
		}
	}
	
	private String getActivityNameFromEvent(SLEXEvent e,
			List<SLEXAttribute> activityAttributes) {
		StringBuffer name = new StringBuffer();
		
		Hashtable<SLEXAttribute, SLEXAttributeValue> attValues = e.getAttributeValues();
		
		int i = 1;
		for (SLEXAttribute at: activityAttributes) {
			SLEXAttributeValue atV = attValues.get(at);
			name.append(atV.getValue());
			if (i < activityAttributes.size()) {
				name.append("+");
			}
			i++;
		}
		
		return name.toString();
	}

	public ObjectVersion getLastObjectVersion(TableInfo t, TraceID objId, HashMap<TableInfo,HashMap<TraceID,LinkedHashSet<ObjectVersion>>> objectVersions) {
		
		if (objectVersions.containsKey(t)) {
			HashMap<TraceID, LinkedHashSet<ObjectVersion>> objsT = objectVersions.get(t);
			if (objsT.containsKey(objId)) {
				LinkedHashSet<ObjectVersion> versionsObj = objsT.get(objId);
				if (versionsObj != null && !versionsObj.isEmpty()) {
					
					ObjectVersion last = null;
					Iterator<ObjectVersion> it = versionsObj.iterator();
					while (it.hasNext()) {
						last = it.next();
					}
					
					return last;
				}
			}
		}
		
		return null;
	}
	
	public MetaModel getMetaModel() {
		return mm;
	}
	
	public SLEXMMStorageMetaModel saveMetaModelToDisk(String name) {
		try {
			SLEXMMStorageMetaModel strg = new SLEXMMStorageMetaModelImpl("data",name+"-"
					+System.currentTimeMillis()+SLEXMMStorageMetaModel.METAMODEL_FILE_EXTENSION);
			
			HashMap<TableInfo,SLEXMMClass> tableToClassMap = new HashMap<>();
			HashMap<Column,SLEXMMAttribute> columnToAttributeMap = new HashMap<>();
			HashMap<TableInfo,Key> primaryKeysPerTable = new HashMap<>();
			HashMap<SLEXAttribute,SLEXMMEventAttribute> eventAttrMap = new HashMap<>();
			HashMap<TraceID,SLEXMMObject> mapTraceIdToObject = new HashMap<>();
			HashMap<SLEXEvent,SLEXMMEvent> eventToMMEventMap = new HashMap<>();
			HashMap<ObjectVersion,SLEXMMObjectVersion> objVToMMObjVersionMap = new HashMap<>();
			HashMap<Key,SLEXMMRelationship> fkeyToRelationshipMap = new HashMap<>();
			HashMap<String,SLEXMMActivity> activityMap = new HashMap<>();
			HashMap<ActivityInstance,SLEXMMActivityInstance> activityInstancesMap = new HashMap<>();
			
			// Save DataModel
			
			SLEXMMDataModel slxmmdm = strg.createDataModel(dm.getName());
			
			for (TableInfo t: dm.getTables()) {
				SLEXMMClass cl = strg.createClass(slxmmdm.getId(), t.name);
				tableToClassMap.put(t, cl);
				for (Column c: t.columns) {
					SLEXMMAttribute at = strg.createAttribute(cl.getId(), c.name);
					columnToAttributeMap.put(c,at);
				}
			}
			
			for (TableInfo t: dm.getTables()) {
				for (Key k: dm.getKeysPerTable(t)) {
					if (k.type == Key.PRIMARY_KEY) {
						primaryKeysPerTable.put(t,k);
					} else if (k.type == Key.FOREIGN_KEY) {
						SLEXMMClass sourceClass = tableToClassMap.get(t);
						SLEXMMClass targetClass = tableToClassMap.get(k.refers_to.table);
						SLEXMMRelationship rl = strg.createRelationship(k.name,
								sourceClass.getId(), targetClass.getId());
						fkeyToRelationshipMap.put(k,rl);
					}
				}
			}
			
			// Create process
			SLEXMMProcess proc = strg.createProcess("proc01");
			
			// Create Activities
			for (String actName: mm.activitySet) {
				SLEXMMActivity act = strg.createActivity(proc.getId(), actName);
				activityMap.put(actName,act);
			}			

			strg.setAutoCommit(false);

			// Save Cases and Activity Instances
			for (SLEXTrace tr : mm.caseToActivityInstancesMap.keySet()) {
				SLEXMMCase pcase = strg.createCase(tr.getCaseId());
				for (ActivityInstance ai : mm.caseToActivityInstancesMap
						.get(tr)) {
					if (!activityInstancesMap.containsKey(ai)) {
						SLEXMMActivity act = activityMap.get(ai
								.getActivityName());
						SLEXMMActivityInstance mmai = strg
								.createActivityInstance(act);
						activityInstancesMap.put(ai, mmai);
					}
					SLEXMMActivityInstance mmai = activityInstancesMap.get(ai);

					pcase.add(mmai);
				}
			}

			strg.commit();
				
			// Save Event Collection
						
			SLEXEventResultSet evrset = evCol.getEventsResultSetOrderedBy(orderAttributes);
			SLEXEvent e = null;
			
			int i = 1;
			while ((e = evrset.getNext()) != null) {
				
				ActivityInstance ai = mm.eventActivityInstanceMap.get(e);
				if (!activityInstancesMap.containsKey(ai)) {
					SLEXMMActivity act = activityMap.get(ai.getActivityName());
					SLEXMMActivityInstance mmai = strg.createActivityInstance(act);
					activityInstancesMap.put(ai, mmai);
				}
				SLEXMMActivityInstance mmai = activityInstancesMap.get(ai);
				
				SLEXMMEvent mme = strg.createEvent(i,mmai.getId());
				eventToMMEventMap.put(e,mme);
				
				i++;
				Hashtable<SLEXAttribute, SLEXAttributeValue> attrVals = e.getAttributeValues();
				for (SLEXAttribute at: attrVals.keySet()) {
					SLEXMMEventAttribute mmatt = null;
					if (eventAttrMap.containsKey(at)) {
						mmatt = eventAttrMap.get(at);
					} else {
						mmatt = strg.createEventAttribute(at.getName());
						eventAttrMap.put(at, mmatt);
					}
					SLEXAttributeValue attVal = attrVals.get(at);
					String value = attVal.getValue();
					String type = "STRING";
					SLEXMMEventAttributeValue mmattVal = strg.createEventAttributeValue(mmatt.getId(),
							mme.getId(), value, type);
				}
				
			}
		
			strg.commit();
			// Save Objects
			
			for (TableInfo t: mm.objects.keySet()) {
				SLEXMMClass c = tableToClassMap.get(t);
				for (TraceID objTraceId: mm.objects.get(t)) {
					SLEXMMObject obj = strg.createObject(c.getId());
					mapTraceIdToObject.put(objTraceId,obj);
					
					// Save ObjectVersions
					if (mm.objectVersions.containsKey(t)) {
						if (mm.objectVersions.get(t).containsKey(objTraceId)) {
							for (ObjectVersion objV: mm.objectVersions.get(t).get(objTraceId)) {
								SLEXMMEvent ev = eventToMMEventMap.get(objV.getEvent());
								SLEXMMObjectVersion mmObjV = strg.createObjectVersion(obj.getId(),ev.getId());
								objVToMMObjVersionMap.put(objV,mmObjV);
								
								// Save Attribute values for Object version
								
								for (Column ck: objV.getAttributeValues().keySet()) {
									SLEXMMAttribute mmAttr = columnToAttributeMap.get(ck);
									String value = objV.getAttributeValues().get(ck);
									String type = "STRING";
									SLEXMMAttributeValue attVal =
											strg.createAttributeValue(mmAttr.getId(), mmObjV.getId(), value, type);
								}
							}
						}
					}
				}
			}
			
			strg.commit();
			
			// Save Relations			
			for (Relation r: mm.relations) {								
				SLEXMMObjectVersion sourceObjectVersion = objVToMMObjVersionMap.get(r.getFromObjectVersion());
				SLEXMMObjectVersion targetObjectVersion = objVToMMObjVersionMap.get(r.getToObjectVersion());;
				
				if (sourceObjectVersion != null && targetObjectVersion != null) {
					SLEXMMRelation mmRel = strg.createRelation(
							sourceObjectVersion.getId(), targetObjectVersion.getId());
				}
			}
			strg.commit();
			strg.setAutoCommit(true);
			
			return strg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}