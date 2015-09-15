package org.processmining.database.metamodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;

import org.mapdb.Fun;
import org.mapdb.HTreeMap;
import org.processmining.openslex.SLEXDMDataModel;
import org.processmining.openslex.SLEXEvent;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXPerspective;
import org.processmining.openslex.SLEXTrace;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.SLEXAttributeMapper;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.common.TraceID;

public class MetaModel {

	//public HashMap<TableInfo, HashSet<TraceID>> objects;
	//HashSet<Relation> relations;
	//public HashMap<TableInfo, HashMap<TraceID, LinkedHashSet<ObjectVersion>>> objectVersions;
	public DataModel dm;
	public SLEXEventCollection evCol;
	public SLEXPerspective perspective;
	public SLEXAttributeMapper mapper;
	public HashSet<String> activitySet;
	//public HashMap<SLEXTrace, HashSet<ActivityInstance>> caseToActivityInstancesMap;
	//public HashMap<SLEXEvent, ActivityInstance> eventActivityInstanceMap;

	public Set<Relation> relations;
	public HTreeMap<Integer,ActivityInstance> eventActivityInstanceMap;
	public HashMap<TableInfo,Set<TraceID>> objects;
	public HTreeMap<SLEXTrace,HashSet<ActivityInstance>> caseToActivityInstancesMap;
	public HashMap<TableInfo,NavigableSet<Fun.Tuple2<TraceID, ObjectVersion>>> objectVersions;
}
