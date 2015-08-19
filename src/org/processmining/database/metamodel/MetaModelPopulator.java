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

	//private SLEXDMDataModel slxdm;
	private DataModel dm;
	private SLEXEventCollection evCol;
	private MetaModel mm;
	private List<Column> orderColumns;
	private List<SLEXAttribute> orderAttributes;
	private SLEXAttributeMapper mapper;
	private TableInfo commonTable;
	
	public MetaModelPopulator(TableInfo common, DataModel dm, SLEXEventCollection evCol, List<Column> orderColumns, SLEXAttributeMapper mapper) {
		//this.slxdm = slxdm;
		this.evCol = evCol;
		this.mm = new MetaModel();
		this.orderColumns = orderColumns;
		this.mapper = mapper;
		this.dm = dm;
		this.commonTable = common;
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
		
		List<SLEXAttribute> orderAtts = new ArrayList<>();
		for (Column ordC: orderColumns) {
			orderAtts.add(mapper.map(ordC));
		}
		
//		SLEXAttributeMapper mapper = LogTraceSplitter.computeMapping(evCol, dm.getTables());
		
		SLEXEventResultSet evrset = evCol.getEventsResultSetOrderedBy(orderAttributes);
		
		HashMap<TableInfo,HashSet<TraceID>> objects = new HashMap<>();
		HashMap<TableInfo,HashMap<TraceID,HashMap<Key,HashMap<TraceID,HashSet<Relation>>>>> relations = new HashMap<>();
		HashMap<TableInfo,HashMap<TraceID,LinkedHashSet<ObjectVersion>>> objectVersions = new HashMap<>();
		
		this.mm.objects = objects;
		this.mm.relations = relations;
		this.mm.objectVersions = objectVersions;
		this.mm.dm = dm;
		//this.mm.slxdm = slxdm;
		this.mm.evCol = evCol;
		this.mm.mapper = mapper;
		
		SLEXEvent e = null;
		
		long order = 0;
		
		while ((e = evrset.getNext()) != null) {
			// Get the class for the event			
			TableInfo t = getTableFromEvent(e,mapper);

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
				
				Relation r = new Relation(fk,t,referred_pk.table,eID,referredPkID);
				
				// Search existing open (not ended) relation for this object
				if (!relations.containsKey(t)) {
					relations.put(t, new HashMap<TraceID,HashMap<Key,HashMap<TraceID,HashSet<Relation>>>>());
				}
				HashMap<TraceID,HashMap<Key,HashMap<TraceID,HashSet<Relation>>>> relsT = relations.get(t);
				
				if (!relsT.containsKey(eID)) {
					relsT.put(eID, new HashMap<Key,HashMap<TraceID,HashSet<Relation>>>());
				}
				HashMap<Key,HashMap<TraceID,HashSet<Relation>>> keysMap = relsT.get(eID);

				if (!keysMap.containsKey(fk)) {
					keysMap.put(fk, new HashMap<TraceID,HashSet<Relation>>());
				}
				
				HashMap<TraceID,HashSet<Relation>> relsMap = keysMap.get(fk);
				// If does not exist:
				if (relsMap.isEmpty()) {
					// Add relation and link to event
					HashSet<Relation> relsSet = new HashSet<>();
					relsSet.add(r);
					relsMap.put(referredPkID, relsSet);
					r.setFromEvent(e);
					r.setStartSourceObjectVersion(objVersion);
					r.setEndSourceObjectVersion(null);
					r.setStartTargetObjectVersion(getLastObjectVersion(referred_pk.table,referredPkID,objectVersions));
					r.setEndTargetObjectVersion(null);
				// else (If relation exists):
				} else {
					// Find last open relation
					Relation lastOpenRelation = null;
					outOfLoops:
					for (HashSet<Relation> set: relsMap.values()) {
						for (Relation oldR: set) {
							if (oldR.getEndSourceObjectVersion() == null) {
								lastOpenRelation = oldR;
								break outOfLoops;
							}
						}
					}
					// If relation is equal: 
					if (lastOpenRelation.getToObject().equals(referredPkID)) {
						// Remain open
					} else {
						// Close previous relation (link to this event)
						lastOpenRelation.setEndSourceObjectVersion(objVersion);
						lastOpenRelation.setEndTargetObjectVersion(getLastObjectVersion(referred_pk.table,referredPkID,objectVersions));
						// Add new relation and link to event
						if (!relsMap.containsKey(referredPkID)) {
							relsMap.put(referredPkID, new HashSet<Relation>());
						}
						HashSet<Relation> set = relsMap.get(referredPkID);
						set.add(r);
						r.setFromEvent(e);
						r.setStartSourceObjectVersion(objVersion);
						r.setEndSourceObjectVersion(null);
						r.setStartTargetObjectVersion(getLastObjectVersion(referred_pk.table,referredPkID,objectVersions));
						r.setEndTargetObjectVersion(null);
					}
					
				}
				
			}
			
		}
		
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
	
	public void saveMetaModelToDisk(String name) {
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
			HashMap<Key,HashMap<Column,SLEXMMRelationshipAttribute>> keyFieldToRelAttrMap = new HashMap<>();
			
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
						for (Column c: k.fields) {
							SLEXMMAttribute sourceAtt = columnToAttributeMap.get(c);
							SLEXMMAttribute targetAtt = columnToAttributeMap.get(k.refers_to_column.get(c));
							SLEXMMRelationshipAttribute rlAt = strg.createRelationshipAttribute(rl.getId(),
									sourceAtt.getId(), targetAtt.getId());
							if (!keyFieldToRelAttrMap.containsKey(k)) {
								keyFieldToRelAttrMap.put(k, new HashMap<Column,SLEXMMRelationshipAttribute>());
							}
							keyFieldToRelAttrMap.get(k).put(c, rlAt);							
						}
					}
				}
			}
			
			// Save Event Collection
			
			SLEXMMEventCollection slxmmevCol = strg.createEventCollection(evCol.getName());
			SLEXEventResultSet evrset = evCol.getEventsResultSetOrderedBy(orderAttributes);
			SLEXEvent e = null;
			
			strg.setAutoCommit(false);
			
			int i = 0;
			while ((e = evrset.getNext()) != null) {
				SLEXMMEvent mme = strg.createEvent(slxmmevCol.getId(),i);
				eventToMMEventMap.put(e,mme);
				i++;
				Hashtable<SLEXAttribute, SLEXAttributeValue> attrVals = e.getAttributeValues();
				for (SLEXAttribute at: attrVals.keySet()) {
					SLEXMMEventAttribute mmatt = null;
					if (eventAttrMap.containsKey(at)) {
						mmatt = eventAttrMap.get(at);
					} else {
						mmatt = strg.createEventAttribute(slxmmevCol.getId(), at.getName());
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
			int j = 1;
			
			for (TableInfo t: mm.relations.keySet()) {
				HashMap<TraceID,HashMap<Key,HashMap<TraceID,HashSet<Relation>>>> tableRelations = mm.relations.get(t);
				for (TraceID tid: tableRelations.keySet()) {
					HashMap<Key,HashMap<TraceID,HashSet<Relation>>> sourceObjectRelations = tableRelations.get(tid);
					for (Key k: sourceObjectRelations.keySet()) {
						HashMap<TraceID,HashSet<Relation>> keyObjectRelations = sourceObjectRelations.get(k);
						for (TraceID tid2: keyObjectRelations.keySet()) {
							HashSet<Relation> setRelations = keyObjectRelations.get(tid2);
							for (Relation r: setRelations) {
								
								SLEXMMRelationship mmRelationship = fkeyToRelationshipMap.get(r.getRelationshipKey());
								SLEXMMObject sourceObject = mapTraceIdToObject.get(r.getFromObject());
								SLEXMMObject targetObject = mapTraceIdToObject.get(r.getToObject());
								SLEXMMObjectVersion startSourceObjectV = objVToMMObjVersionMap.get(r.getStartSourceObjectVersion());
								int startSourceObjectVId = -1;
								if (startSourceObjectV != null) {
									startSourceObjectVId = startSourceObjectV.getId();
								}
								SLEXMMObjectVersion endSourceObjectV = objVToMMObjVersionMap.get(r.getEndSourceObjectVersion());
								int endSourceObjectVId = -1;
								if (endSourceObjectV != null) {
									endSourceObjectVId = endSourceObjectV.getId();
								}
								SLEXMMObjectVersion startTargetObjectV = objVToMMObjVersionMap.get(r.getStartTargetObjectVersion());
								int startTargetObjectVId = -1;
								if (startTargetObjectV != null) {
									startTargetObjectVId = startTargetObjectV.getId();
								}
								SLEXMMObjectVersion endTargetObjectV = objVToMMObjVersionMap.get(r.getEndTargetObjectVersion());
								int endTargetObjectVId = -1;
								if (endTargetObjectV != null) {
									endTargetObjectVId = endTargetObjectV.getId();
								}
								SLEXMMEvent event = eventToMMEventMap.get(r.getEvent());
								int eventId = -1;
								if (event != null) {
									eventId = event.getId();
								}
								SLEXMMRelation mmRel = strg.createRelation(mmRelationship.getId(),
										sourceObject.getId(), targetObject.getId(),
										startSourceObjectVId, endSourceObjectVId,
										startTargetObjectVId, endTargetObjectVId,
										eventId);
						
								for (Column srcC: r.getRelationshipKey().fields) {
									Column trgC = r.getRelationshipKey().refers_to_column.get(srcC);
									SLEXMMRelationshipAttribute mmRelAttr =
											keyFieldToRelAttrMap.get(r.getRelationshipKey()).get(srcC);
									String value = tid.getValue(srcC);
									String type = "STRING";
									SLEXMMRelationshipAttributeValue mmRelAttrVal =
											strg.createRelationshipAttributeValue(mmRel.getId(), mmRelAttr.getId(), value, type);
								}
							}
						}
					}
				}
			}
			
			strg.commit();
			strg.setAutoCommit(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		
//		SLEXDMDataModel dm = null;
//		SLEXEventCollection evCol = null;
//	
//		MetaModelPopulator mmp = new MetaModelPopulator(dm, evCol);		
//		
//		List<SLEXAttribute> orderAttributes = new ArrayList<>();
//		
//		mmp.computeMetaModel(orderAttributes);
//	
//		MetaModel mm = mmp.getMetaModel();
//		
//	}
	
}