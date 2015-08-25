package org.processmining.database.metamodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.processmining.openslex.SLEXDMDataModel;
import org.processmining.openslex.SLEXEvent;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXTrace;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.SLEXAttributeMapper;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.common.TraceID;

public class MetaModel {

	public HashMap<TableInfo, HashSet<TraceID>> objects;
	HashSet<Relation> relations;
	public HashMap<TableInfo, HashMap<TraceID, LinkedHashSet<ObjectVersion>>> objectVersions;
	public DataModel dm;
	//public SLEXDMDataModel slxdm;
	public SLEXEventCollection evCol;
	public SLEXAttributeMapper mapper;
	public HashSet<String> activitySet;
	public HashMap<SLEXTrace, HashSet<ActivityInstance>> caseToActivityInstancesMap;
	public HashMap<SLEXEvent, ActivityInstance> eventActivityInstanceMap;

}
