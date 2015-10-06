package org.processmining.database.metamodel.poql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.processmining.openslex.metamodel.SLEXMMActivity;
import org.processmining.openslex.metamodel.SLEXMMActivityInstance;
import org.processmining.openslex.metamodel.SLEXMMActivityInstanceResultSet;
import org.processmining.openslex.metamodel.SLEXMMCase;
import org.processmining.openslex.metamodel.SLEXMMClass;
import org.processmining.openslex.metamodel.SLEXMMEvent;
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
				if (condition.getKey().equals("classid")) {
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
				if (condition.getKey().equals("objectid")) {
					v = String.valueOf(ob.getObjectId());
				} else if (condition.getKey().equals("id")) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKey().equals("start_timestamp")) {
					v = String.valueOf(ob.getStartTimestamp());
				} else if (condition.getKey().equals("end_timestamp")) {
					v = String.valueOf(ob.getEndTimestamp());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}
				
				if (filterOperation(v,condition.value,condition.operator)) {
					filteredList.add(o);
				}
				
			}
		} else if (type == SLEXMMCase.class) {
			
		} else if (type == SLEXMMEvent.class) {
			
		} else if (type == SLEXMMClass.class) {
			
		} else if (type == SLEXMMActivity.class) {
			for (Object o: list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;
				String v = null;
				if (condition.getKey().equals("name")) {
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
	
	public FilterTree createEqualTerminalFilter(String key, String value) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_EQUAL);
	}
	
	public FilterTree createDifferentTerminalFilter(String key, String value) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_DIFFERENT);
	}
	
	public FilterTree createEqualOrGreaterTerminalFilter(String key, String value) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_EQUAL_OR_GREATER_THAN);
	}
	
	public FilterTree createEqualOrSmallerTerminalFilter(String key, String value) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_EQUAL_OR_SMALLER_THAN);
	}
	
	public FilterTree createGreaterTerminalFilter(String key, String value) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_GREATER_THAN);
	}
	
	public FilterTree createSmallerTerminalFilter(String key, String value) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_SMALLER_THAN);
	}
	
	public FilterTree createContainsTerminalFilter(String key, String value) {
		return createTerminalFilter(key, value, FilterTree.OPERATOR_CONTAINS);
	}
	
	public FilterTree createTerminalFilter(String key, String value, int operator) {
		FilterTree node = new FilterTree();
		node.node = FilterTree.NODE_TERMINAL;
		node.operator = operator;
		node.key = key;
		node.value = value;
		return node;
	}
	
	public List<Object> objectsOf(List<Object> list, Class type) {
		// TODO
		return null;
	}
	
	public List<Object> casesOf(List<Object> list, Class type) {
		// TODO
		return null;
	}
	
	public List<Object> eventsOf(List<Object> list, Class type) {
		// TODO
		return null;
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
		// TODO
		return null;
	}
	
	public List<Object> getAllVersions() {
		// TODO
		return null;
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
