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
import org.processmining.redologs.common.Column;
import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.common.Key;
import org.processmining.redologs.common.SLEXAttributeMapper;
import org.processmining.redologs.common.TableInfo;
import org.processmining.redologs.common.TraceID;

public class MetaModel {

	public DataModel dm;
	public SLEXEventCollection evCol;
	public SLEXPerspective perspective;
	public SLEXAttributeMapper mapper;
	
	public HashSet<String> activitySet;

	public Set<CompactRelation> relations;
	public HTreeMap<Integer,CompactActivityInstance> eventActivityInstanceMap;
	public HashMap<TableInfo,Set<CompactObjectID>> objects;
	public HTreeMap<SLEXTrace,HashSet<CompactActivityInstance>> caseToActivityInstancesMap;
	public HashMap<TableInfo,NavigableSet<Fun.Tuple2<CompactObjectID, CompactObjectVersion>>> objectVersions;
	
	public HashMap<Column,CompactColumn> columnsCompactMap;
	public HashMap<CompactColumn,Column> columnsCompactReverseMap;
	
	public HashMap<Integer,Key> keyIndexMap;
	public HashMap<Key,Integer> keyIndexReverseMap;
	public HTreeMap<Integer, Long> endDateObjectVersionsMap;
}
