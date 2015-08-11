package org.processmining.database.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXAttributeValue;
import org.processmining.openslex.SLEXDMDataModel;
import org.processmining.openslex.SLEXEvent;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXEventResultSet;
import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.LogTraceSplitter;
import org.processmining.redologs.common.SLEXAttributeMapper;
import org.processmining.redologs.common.SLEXDataModelExportImport;
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
		HashMap<TableInfo,HashMap<TraceID,HashMap<TraceID,Relation>>> relations = new HashMap<>();
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
				tp.add(fk);
				TraceID fkeID = LogTraceSplitter.generateTraceID(tpfk, mapper, e);
				
				Key referred_pk = fk.refers_to;
				
				Relation r = new Relation(fk,t,referred_pk.table,eID,fkeID);
				
				// Search existing open (not ended) relation for this object
				if (!relations.containsKey(t)) {
					relations.put(t, new HashMap<TraceID,HashMap<TraceID,Relation>>());
				}
				HashMap<TraceID,HashMap<TraceID,Relation>> relsT = relations.get(t);
				
				if (!relsT.containsKey(eID)) {
					relsT.put(eID, new HashMap<TraceID,Relation>());
				}
				HashMap<TraceID,Relation> relsMap = relsT.get(eID);

				// If does not exist:
				if (relsMap.isEmpty()) {
					// Add relation and link to event
					relsMap.put(fkeID, r);
					r.setFromEvent(e);
					r.setStartSourceObjectVersion(objVersion);
					r.setEndSourceObjectVersion(null);
					r.setStartTargetObjectVersion(getLastObjectVersion(referred_pk.table,fkeID,objectVersions));
					r.setEndTargetObjectVersion(null);
				// else (If relation exists):
				} else {
					// If relation is equal:
					if (relsMap.containsKey(fkeID)) {
						// Remain open
					// Else (If relation is different):
					} else {
						Relation lastOpenRelation = null;
						for (Relation oldR: relsMap.values()) {
							if (oldR.getEndSourceObjectVersion() == null) {
								lastOpenRelation = oldR;
								break;
							}
						}
						// Close previous relation (link to this event)
						lastOpenRelation.setEndSourceObjectVersion(objVersion);
						lastOpenRelation.setEndTargetObjectVersion(getLastObjectVersion(referred_pk.table,fkeID,objectVersions));
						// Add new relation and link to event
						relsMap.put(fkeID, r);
						r.setFromEvent(e);
						r.setStartSourceObjectVersion(objVersion);
						r.setEndSourceObjectVersion(null);
						r.setStartTargetObjectVersion(getLastObjectVersion(referred_pk.table,fkeID,objectVersions));
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