package org.processmining.database.metamodel.poql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.processmining.openslex.metamodel.SLEXMMActivity;
import org.processmining.openslex.metamodel.SLEXMMActivityInstance;
import org.processmining.openslex.metamodel.SLEXMMActivityInstanceResultSet;
import org.processmining.openslex.metamodel.SLEXMMAttribute;
import org.processmining.openslex.metamodel.SLEXMMAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMCase;
import org.processmining.openslex.metamodel.SLEXMMClass;
import org.processmining.openslex.metamodel.SLEXMMEvent;
import org.processmining.openslex.metamodel.SLEXMMEventAttribute;
import org.processmining.openslex.metamodel.SLEXMMEventAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMEventResultSet;
import org.processmining.openslex.metamodel.SLEXMMObject;
import org.processmining.openslex.metamodel.SLEXMMObjectResultSet;
import org.processmining.openslex.metamodel.SLEXMMObjectVersion;
import org.processmining.openslex.metamodel.SLEXMMObjectVersionResultSet;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;

public class POQLFunctions {
	
	private SLEXMMStorageMetaModel slxmm = null;
	
	public void setMetaModel(SLEXMMStorageMetaModel strg) {
		this.slxmm = strg;
	}
	
	public List<Object> filterTerminal(List<Object> list, Class type, FilterTree condition) {
		List<Object> filteredList = new ArrayList<>();
		
		if (type == SLEXMMObject.class) {
			for (Object o: list) {
				SLEXMMObject ob = (SLEXMMObject) o;
				String v = null;
				if (condition.isAttribute()) {
					// ERROR
					System.err.println("No attributes for type Object");
					return list;
				} else if (condition.getKey().equals("classid")) {
					v = String.valueOf(ob.getClassId());
				} else if (condition.getKey().equals("id")) {
					v = String.valueOf(ob.getId());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}
				
				if (filterOperation(v,condition.value,condition.operator)) {
					filteredList.add(o);
				}
				
			}
		} else if (type == SLEXMMObjectVersion.class) {			
			for (Object o: list) {
				SLEXMMObjectVersion ob = (SLEXMMObjectVersion) o;
				String v = null;
				if (condition.isAttribute()) {
					HashMap<SLEXMMAttribute, SLEXMMAttributeValue> attsMap = ob.getAttributeValues();
					SLEXMMAttribute slxAtt = null;
					
					for (SLEXMMAttribute at: attsMap.keySet()) {
						if (at.getName().equals(condition.getKey())) {
							slxAtt = at;
							break;
						}
					}
					
					if (slxAtt != null) {
						SLEXMMAttributeValue slxAttVal = attsMap.get(slxAtt);
						if (slxAttVal != null) {
							v = slxAttVal.getValue();
						}
					}
					
				} else if (condition.getKey().equals("objectid")) {
					v = String.valueOf(ob.getObjectId());
				} else if (condition.getKey().equals("id")) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKey().equals("eventid")) {
					v = String.valueOf(ob.getEventId());
				} else if (condition.getKey().equals("start_timestamp")) {
					v = String.valueOf(ob.getStartTimestamp());
				} else if (condition.getKey().equals("end_timestamp")) {
					v = String.valueOf(ob.getEndTimestamp());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}
				
				if (v != null && filterOperation(v,condition.value,condition.operator)) {
					filteredList.add(o);
				}
				
			}
		} else if (type == SLEXMMCase.class) {
			// TODO
		} else if (type == SLEXMMEvent.class) {
			for (Object o: list) {
				SLEXMMEvent ob = (SLEXMMEvent) o;
				String v = null;
				if (condition.isAttribute()) {
					HashMap<SLEXMMEventAttribute, SLEXMMEventAttributeValue> attsMap = ob.getAttributeValues();
					SLEXMMEventAttribute slxAtt = null;
					
					for (SLEXMMEventAttribute at: attsMap.keySet()) {
						if (at.getName().equals(condition.getKey())) {
							slxAtt = at;
							break;
						}
					}
					
					if (slxAtt != null) {
						SLEXMMEventAttributeValue slxAttVal = attsMap.get(slxAtt);
						if (slxAttVal != null) {
							v = slxAttVal.getValue();
						}
					}
					
				} else if (condition.getKey().equals("activity_instance_id")) {
					v = String.valueOf(ob.getActivityInstanceId());
				} else if (condition.getKey().equals("id")) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKey().equals("ordering")) {
					v = String.valueOf(ob.getOrder());
				} else if (condition.getKey().equals("timestamp")) {
					v = String.valueOf(ob.getTimestamp());
				} else if (condition.getKey().equals("lifecycle")) {
					v = String.valueOf(ob.getLifecycle());
				} else if (condition.getKey().equals("resource")) {
					v = String.valueOf(ob.getResource());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}
				
				if (v != null && filterOperation(v,condition.value,condition.operator)) {
					filteredList.add(o);
				}
				
			}
		} else if (type == SLEXMMClass.class) {
			// TODO
		} else if (type == SLEXMMActivity.class) {
			for (Object o: list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;
				String v = null;
				if (condition.isAttribute()) {
					// ERROR
					System.err.println("No attributes for type Activity");
					return list;
				} else if (condition.getKey().equals("name")) {
					v = String.valueOf(ob.getName());
				} else if (condition.getKey().equals("id")) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKey().equals("processid")) {
					v = String.valueOf(ob.getProcessId());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}
				
				if (filterOperation(v,condition.value,condition.operator)) {
					filteredList.add(o);
				}
				
			}
		} else {
			// ERROR
			System.err.println("Unknown type");
			return list;
		}
		
		return filteredList;
	}
	
	public boolean filterOperation(String a, String b, int op) {
		switch (op) {
		case FilterTree.OPERATOR_CONTAINS:
			return (a.contains(b));
		case FilterTree.OPERATOR_DIFFERENT:
			return !a.equals(b);
		case FilterTree.OPERATOR_EQUAL:
			return a.equals(b);
		case FilterTree.OPERATOR_EQUAL_OR_GREATER_THAN:
			return a.compareTo(b) >= 0;
		case FilterTree.OPERATOR_EQUAL_OR_SMALLER_THAN:
			return a.compareTo(b) <= 0;
		case FilterTree.OPERATOR_GREATER_THAN:
			return a.compareTo(b) > 0;
		case FilterTree.OPERATOR_SMALLER_THAN:
			return a.compareTo(b) < 0;
		default:
			return false;
		}
	}
	
	public List<Object> filter(List<Object> list, Class type, FilterTree conditions) {
		List<Object> filteredList = new ArrayList<>();
		
		if (conditions.isTerminal()) {
			// Filter terminal
			return filterTerminal(list,type,conditions);
		} else if (conditions.isNot()) {
			// Filter NOT
			for (Object o: list) {
				if (filter(Arrays.asList(o),type,conditions.leftChild).isEmpty()) {
					filteredList.add(o);
				}
			}
		} else if (conditions.isAnd()) {
			// Filter AND
			for (Object o: list) {
				if (!filter(Arrays.asList(o),type,conditions.leftChild).isEmpty()) {
					if (!filter(Arrays.asList(o),type,conditions.rightChild).isEmpty()) {
						filteredList.add(o);
					}
				}
			}
		} else if (conditions.isOr()) {
			// Filter OR
			for (Object o: list) {
				if (filter(Arrays.asList(o),type,conditions.leftChild).isEmpty()) {
					if (!filter(Arrays.asList(o),type,conditions.rightChild).isEmpty()) {
						filteredList.add(o);
					}
				} else {
					filteredList.add(o);
				}
			}
		} else {
			// ERROR
			System.err.println("Unknown Filter node");
			return list;
		}
		
		return filteredList;
	}
	
	public FilterTree createNotNode(FilterTree tree) {
		FilterTree notNode = new FilterTree();
		notNode.node = FilterTree.NODE_NOT;
		notNode.leftChild = tree;
		return notNode;
	}
	
	public FilterTree createAndNode(FilterTree left, FilterTree right) {
		FilterTree andNode = new FilterTree();
		andNode.node = FilterTree.NODE_AND;
		andNode.leftChild = left;
		andNode.rightChild = right;
		return andNode;
	}
	
	public FilterTree createOrNode(FilterTree left, FilterTree right) {
		FilterTree orNode = new FilterTree();
		orNode.node = FilterTree.NODE_OR;
		orNode.leftChild = left;
		orNode.rightChild = right;
		return orNode;
	}
	
	public FilterTree createEqualTerminalFilter(String key, String value, boolean att) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_EQUAL,att);
	}
	
	public FilterTree createDifferentTerminalFilter(String key, String value, boolean att) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_DIFFERENT,att);
	}
	
	public FilterTree createEqualOrGreaterTerminalFilter(String key, String value, boolean att) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_EQUAL_OR_GREATER_THAN,att);
	}
	
	public FilterTree createEqualOrSmallerTerminalFilter(String key, String value, boolean att) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_EQUAL_OR_SMALLER_THAN,att);
	}
	
	public FilterTree createGreaterTerminalFilter(String key, String value, boolean att) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_GREATER_THAN,att);
	}
	
	public FilterTree createSmallerTerminalFilter(String key, String value, boolean att) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_SMALLER_THAN,att);
	}
	
	public FilterTree createContainsTerminalFilter(String key, String value, boolean att) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_CONTAINS,att);
	}
	
	public FilterTree createTerminalFilter(String key, String value, int operator, boolean att) {
		FilterTree node = new FilterTree();
		node.node = FilterTree.NODE_TERMINAL;
		node.operator = operator;
		node.key = key;
		node.value = value;
		node.att = att;
		return node;
	}
	
	public List<Object> objectsOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
		
		if (type == SLEXMMActivity.class) {
			// TODO
		} else if (type == SLEXMMEvent.class) {
			for (Object o: list) {
				SLEXMMEvent ob = (SLEXMMEvent) o;
				
				SLEXMMObjectResultSet orset = slxmm.getObjectsForEvent(ob);
				SLEXMMObject slxo = null;
				while ((slxo = orset.getNext()) != null) {
					listResult.add(slxo);
				}
			}
		} else if (type == SLEXMMObjectVersion.class) {
			// TODO
		} else if (type == SLEXMMCase.class) {
			// TODO
		} else if (type == SLEXMMObject.class) {
			return list;
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}
	
	public List<Object> casesOf(List<Object> list, Class type) {
		// TODO
		return null;
	}
	
	public List<Object> eventsOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
		
		if (type == SLEXMMActivity.class) {
			for (Object o: list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;
				
				SLEXMMEventResultSet erset = slxmm.getEventsForActivity(ob);
				SLEXMMEvent e = null;
				while ((e = erset.getNext()) != null) {
					listResult.add(e);
				}
			}
		} else if (type == SLEXMMObject.class) {
			// TODO
		} else if (type == SLEXMMObjectVersion.class) {
			// TODO
		} else if (type == SLEXMMCase.class) {
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public List<Object> versionsOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
		
		if (type == SLEXMMActivity.class) {
			for (Object o: list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;
				
				SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersionsForActivity(ob);
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMObject.class) {
			for (Object o: list) {
				SLEXMMObject ob = (SLEXMMObject) o;
				
				SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersionsForObjectOrdered(ob);
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMEvent.class) {
			// TODO
		} else if (type == SLEXMMCase.class) {
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}
	
	public List<Object> activitiesOf(List<Object> list, Class type) {
		// TODO
		return null;
	}
	
	public List<Object> classesOf(List<Object> list, Class type) {
		// TODO
		return null;
	}
	
	public List<Object> versionsRelatedTo(List<Object> list, Class type) {
		HashSet<SLEXMMObjectVersion> setResult = new HashSet<>();
		List<Object> listResult = new ArrayList<>();
		
		if (type == SLEXMMObjectVersion.class) {
			for (Object o: list) {
				SLEXMMObjectVersion ob = (SLEXMMObjectVersion) o;
				
				SLEXMMObjectVersionResultSet ovrset = slxmm.getVersionsRelatedToObjectVersion(ob);
				
				SLEXMMObjectVersion ov = null;
				
				while ((ov = ovrset.getNext()) != null) {
					setResult.add(ov);
				}
			}
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		listResult.addAll(setResult);
		
		return listResult;
	}
	
	public List<Object> getAllObjects() {
		ArrayList<Object> list = new ArrayList<>();
		SLEXMMObjectResultSet orset = slxmm.getObjects();
		SLEXMMObject o = null;
		while ((o = orset.getNext()) != null) {
			list.add(o);
		}
		return list;
	}
	
	public List<Object> getAllCases() {
		// TODO
		return null;
	}
	
	public List<Object> getAllEvents() {
		ArrayList<Object> list = new ArrayList<>();
		SLEXMMEventResultSet erset = slxmm.getEvents();
		SLEXMMEvent e = null;
		while ((e = erset.getNext()) != null) {
			list.add(e);
		}
		return list;
	}
	
	public List<Object> getAllVersions() {
		ArrayList<Object> list = new ArrayList<>();
		SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersions();
		SLEXMMObjectVersion ov = null;
		while ((ov = ovrset.getNext()) != null) {
			list.add(ov);
		}
		return list;
	}
	
	public List<Object> getAllActivities() {
		ArrayList<Object> list = new ArrayList<>();
		list.addAll(slxmm.getActivities());
		return list;
	}
	
	public List<Object> getAllClasses() {
		// TODO
		return null;
	}
}
