package org.processmining.database.metamodel.poql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.processmining.openslex.metamodel.SLEXMMActivity;
import org.processmining.openslex.metamodel.SLEXMMActivityInstance;
import org.processmining.openslex.metamodel.SLEXMMActivityInstanceResultSet;
import org.processmining.openslex.metamodel.SLEXMMActivityResultSet;
import org.processmining.openslex.metamodel.SLEXMMAttribute;
import org.processmining.openslex.metamodel.SLEXMMAttributeResultSet;
import org.processmining.openslex.metamodel.SLEXMMAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMCase;
import org.processmining.openslex.metamodel.SLEXMMCaseResultSet;
import org.processmining.openslex.metamodel.SLEXMMClass;
import org.processmining.openslex.metamodel.SLEXMMClassResultSet;
import org.processmining.openslex.metamodel.SLEXMMDataModel;
import org.processmining.openslex.metamodel.SLEXMMDataModelResultSet;
import org.processmining.openslex.metamodel.SLEXMMEvent;
import org.processmining.openslex.metamodel.SLEXMMEventAttribute;
import org.processmining.openslex.metamodel.SLEXMMEventAttributeResultSet;
import org.processmining.openslex.metamodel.SLEXMMEventAttributeValue;
import org.processmining.openslex.metamodel.SLEXMMEventResultSet;
import org.processmining.openslex.metamodel.SLEXMMObject;
import org.processmining.openslex.metamodel.SLEXMMObjectResultSet;
import org.processmining.openslex.metamodel.SLEXMMObjectVersion;
import org.processmining.openslex.metamodel.SLEXMMObjectVersionResultSet;
import org.processmining.openslex.metamodel.SLEXMMRelation;
import org.processmining.openslex.metamodel.SLEXMMRelationResultSet;
import org.processmining.openslex.metamodel.SLEXMMRelationship;
import org.processmining.openslex.metamodel.SLEXMMRelationshipResultSet;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
import org.processmining.redologs.ui.components.Autocomplete;

import com.google.common.collect.Sets;

public class POQLFunctions {

	private SLEXMMStorageMetaModel slxmm = null;
	private boolean checkerMode = false;
	private List<String> suggestions = null;
	private Token offendingToken = null;
	private Vocabulary vocabulary = null;
	
	private static final int MAX_IDS_ARRAY_SIZE = 40000;

	public void setCheckerMode(boolean mode) {
		this.checkerMode = mode;
	}

	public boolean isCheckerModeEnabled() {
		return this.checkerMode;
	}

	public void setMetaModel(SLEXMMStorageMetaModel strg) {
		this.slxmm = strg;
	}

	public Set<Object> set_operation(int op, Set<Object> listA, Set<Object> listB, Class type) {
		HashSet<Object> resultList = new HashSet<>();
		
		if (op == poqlParser.UNION) {
			resultList.addAll(listA);
			resultList.addAll(listB);
		} else if (op == poqlParser.EXCLUDING) {
			resultList.addAll(listA);
			resultList.removeAll(listB);
		} else if (op == poqlParser.INTERSECTION) {
			HashSet<Object> intersectionSet = new HashSet<>();
			intersectionSet.addAll(listA);
			for (Object o: listB) {
				if (intersectionSet.contains(o)) {
					resultList.add(o);
				}
			}
		} else {
			return listA;
		}
		
		return resultList;
	}
	
	public Set<Object> filterTerminal(Set<Object> list, Class type,
			FilterTree condition) {
		HashSet<Object> filteredList = new HashSet<>();

		if (type == SLEXMMObject.class) {
			for (Object o : list) {
				SLEXMMObject ob = (SLEXMMObject) o;
				String v = null;
				if (condition.isAttribute()) {
					// ERROR
					System.err.println("No attributes for type Object");
					return list;
				} else if (condition.getKeyId() == (poqlParser.CLASS_ID)) {
					v = String.valueOf(ob.getClassId());
				} else if (condition.getKeyId() == poqlParser.ID) {
					v = String.valueOf(ob.getId());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}

				if (filterOperation(v, condition.value, condition.operator)) {
					filteredList.add(o);
				}

			}
		} else if (type == SLEXMMObjectVersion.class) {
			for (Object o : list) {
				SLEXMMObjectVersion ob = (SLEXMMObjectVersion) o;
				String v = null;
				SLEXMMAttribute slxAtt = null;
				if (condition.isAttribute()) {
					HashMap<SLEXMMAttribute, SLEXMMAttributeValue> attsMap = ob
							.getAttributeValues();
					slxAtt = null;

					for (SLEXMMAttribute at : attsMap.keySet()) {
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

				} else if (condition.getKeyId() == poqlParser.ID) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKeyId() == poqlParser.OBJECT_ID) {
					v = String.valueOf(ob.getObjectId());
				} else if (condition.getKeyId() == poqlParser.START_TIMESTAMP) {
					v = String.valueOf(ob.getStartTimestamp());
				} else if (condition.getKeyId() == poqlParser.END_TIMESTAMP) {
					v = String.valueOf(ob.getEndTimestamp());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}

				if (condition.isChanged()) {
					if (slxAtt != null) {
						if (filterChangedOperation(ob, slxAtt, v,
								condition.valueFrom, condition.valueTo)) {
							filteredList.add(o);
						}
					}
				} else if (v != null
						&& filterOperation(v, condition.value,
								condition.operator)) {
					filteredList.add(o);
				}

			}
		} else if (type == SLEXMMCase.class) {
			for (Object o : list) {
				SLEXMMCase ob = (SLEXMMCase) o;
				String v = null;
				if (condition.isAttribute()) {
					// ERROR
					System.err.println("No attributes for type Case");
					return list;
				} else if (condition.getKeyId() == (poqlParser.NAME)) {
					v = String.valueOf(ob.getName());
				} else if (condition.getKeyId() == poqlParser.ID) {
					v = String.valueOf(ob.getId());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}

				if (filterOperation(v, condition.value, condition.operator)) {
					filteredList.add(o);
				}

			}
		} else if (type == SLEXMMEvent.class) {
			
			List<SLEXMMEventAttribute> slxAtts = new ArrayList<>();
			
			if (condition.isAttribute()) {
				SLEXMMEventAttributeResultSet earset = slxmm.getEventAttributes();
				SLEXMMEventAttribute ea = null;
				while ((ea = earset.getNext()) != null) {
					if (ea.getName().equals(condition.getKey())) {
						slxAtts.add(ea);
						break;
					}
				}
			}
			
			for (Object o : list) {
				SLEXMMEvent ob = (SLEXMMEvent) o;
				String v = null;
				if (condition.isAttribute()) {
					HashMap<SLEXMMEventAttribute, SLEXMMEventAttributeValue> attsMap = ob
							.getAttributeValues();
					
					if (!slxAtts.isEmpty()) {
						for (SLEXMMEventAttribute at: slxAtts) {
							SLEXMMEventAttributeValue slxAttVal = attsMap
								.get(at);
							if (slxAttVal != null) {
								v = slxAttVal.getValue();
								break;
							}
						}
					}

				} else if (condition.getKeyId() == poqlParser.ID) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKeyId() == poqlParser.ACTIVITY_INSTANCE_ID) {
					v = String.valueOf(ob.getActivityInstanceId());
				} else if (condition.getKeyId() == poqlParser.ORDERING) {
					v = String.valueOf(ob.getOrder());
				} else if (condition.getKeyId() == poqlParser.TIMESTAMP) {
					v = String.valueOf(ob.getTimestamp());
				} else if (condition.getKeyId() == poqlParser.LIFECYCLE) {
					v = String.valueOf(ob.getLifecycle());
				} else if (condition.getKeyId() == poqlParser.RESOURCE) {
					v = String.valueOf(ob.getResource());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}

				if (v != null
						&& filterOperation(v, condition.value,
								condition.operator)) {
					filteredList.add(o);
				}

			}
		} else if (type == SLEXMMClass.class) {
			for (Object o : list) {
				SLEXMMClass ob = (SLEXMMClass) o;
				String v = null;
				if (condition.isAttribute()) {
					// ERROR
					System.err.println("No attributes for type Class");
					return list;
				} else if (condition.getKeyId() == poqlParser.ID) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKeyId() == poqlParser.DATAMODEL_ID) {
					v = String.valueOf(ob.getDataModelId());
				} else if (condition.getKeyId() == poqlParser.NAME) {
					v = String.valueOf(ob.getName());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}

				if (v != null
						&& filterOperation(v, condition.value,
								condition.operator)) {
					filteredList.add(o);
				}

			}
		} else if (type == SLEXMMActivity.class) {
			for (Object o : list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;
				String v = null;
				if (condition.isAttribute()) {
					// ERROR
					System.err.println("No attributes for type Activity");
					return list;
				} else if (condition.getKeyId() == poqlParser.ID) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKeyId() == poqlParser.PROCESS_ID) {
					v = String.valueOf(ob.getProcessId());
				} else if (condition.getKeyId() == poqlParser.NAME) {
					v = String.valueOf(ob.getName());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}

				if (filterOperation(v, condition.value, condition.operator)) {
					filteredList.add(o);
				}

			}
		} else if (type == SLEXMMActivityInstance.class) {
			for (Object o : list) {
				SLEXMMActivityInstance ob = (SLEXMMActivityInstance) o;
				String v = null;
				if (condition.isAttribute()) {
					// ERROR
					System.err.println("No attributes for type Activity Instance");
					return list;
				} else if (condition.getKeyId() == poqlParser.ID) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKeyId() == poqlParser.ACTIVITY_ID) {
					v = String.valueOf(ob.getActivityId());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}

				if (filterOperation(v, condition.value, condition.operator)) {
					filteredList.add(o);
				}

			}
		} else if (type == SLEXMMRelation.class) {
			for (Object o : list) {
				SLEXMMRelation ob = (SLEXMMRelation) o;
				String v = null;
				if (condition.isAttribute()) {
					// ERROR
					System.err.println("No attributes for type Relation");
					return list;
				} else if (condition.getKeyId() == poqlParser.ID) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKeyId() == poqlParser.SOURCE_OBJECT_VERSION_ID) {
					v = String.valueOf(ob.getSourceObjectVersionId());
				} else if (condition.getKeyId() == poqlParser.TARGET_OBJECT_VERSION_ID) {
					v = String.valueOf(ob.getTargetObjectVersionId());
				} else if (condition.getKeyId() == poqlParser.RELATIONSHIP_ID) {
					v = String.valueOf(ob.getRelationshipId());
				} else if (condition.getKeyId() == poqlParser.START_TIMESTAMP) {
					v = String.valueOf(ob.getStartTimestamp());
				} else if (condition.getKeyId() == poqlParser.END_TIMESTAMP) {
					v = String.valueOf(ob.getEndTimestamp());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}

				if (filterOperation(v, condition.value, condition.operator)) {
					filteredList.add(o);
				}

			}
		} else if (type == SLEXMMRelationship.class) {
			for (Object o : list) {
				SLEXMMRelationship ob = (SLEXMMRelationship) o;
				String v = null;
				if (condition.isAttribute()) {
					// ERROR
					System.err.println("No attributes for type Relationship");
					return list;
				} else if (condition.getKeyId() == poqlParser.ID) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKeyId() == poqlParser.SOURCE) {
					v = String.valueOf(ob.getSourceClassId());
				} else if (condition.getKeyId() == poqlParser.TARGET) {
					v = String.valueOf(ob.getTargetClassId());
				} else if (condition.getKeyId() == poqlParser.NAME) {
					v = String.valueOf(ob.getName());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}

				if (filterOperation(v, condition.value, condition.operator)) {
					filteredList.add(o);
				}

			}
		} else if (type == SLEXMMAttribute.class) {
			for (Object o : list) {
				SLEXMMAttribute ob = (SLEXMMAttribute) o;
				String v = null;
				if (condition.isAttribute()) {
					// ERROR
					System.err.println("No attributes for type Attribute");
					return list;
				} else if (condition.getKeyId() == poqlParser.ID) {
					v = String.valueOf(ob.getId());
				} else if (condition.getKeyId() == poqlParser.CLASS_ID) {
					v = String.valueOf(ob.getClassId());
				} else if (condition.getKeyId() == poqlParser.NAME) {
					v = String.valueOf(ob.getName());
				} else {
					// ERROR
					System.err.println("Unknown key");
					return list;
				}

				if (v != null
						&& filterOperation(v, condition.value,
								condition.operator)) {
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

	public boolean filterChangedOperation(SLEXMMObjectVersion ov,
			SLEXMMAttribute slxAtt, String v, String valueFrom, String valueTo) {

		SLEXMMObjectVersionResultSet ovrset = slxmm
				.getObjectVersionsForObjectOrdered(ov.getObjectId());
		SLEXMMObjectVersion ova = null;
		SLEXMMObjectVersion ovb = null;

		while ((ovb = ovrset.getNext()) != null) {
			if (ovb.getId() == ov.getId()) {
				break;
			} else {
				ova = ovb;
			}
		}

		if (ova != null) {
			SLEXMMAttributeValue prevAtV = ova.getAttributeValues().get(slxAtt);
			String prevV = prevAtV.getValue();
			if (valueFrom != null) {
				if (!prevV.equals(valueFrom)) {
					return false;
				}
			}
			if (valueTo != null) {
				if (!v.equals(valueTo)) {
					return false;
				}
			}
			if (prevV.equals(v)) {
				return false;
			}
		} else {
			// ov was already the first Object Version for this object. We
			// cannot decide what changed.
			return false;
		}

		return true;
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

	public Set<Object> filter(Set<Object> list, Class type,
			FilterTree conditions) {
		HashSet<Object> filteredList = new HashSet<>();

		if (conditions.isTerminal()) {
			// Filter terminal
			return filterTerminal(list, type, conditions);
		} else if (conditions.isNot()) {
			// Filter NOT
			for (Object o : list) {
				if (filter(new HashSet<>(Arrays.asList(o)), type, conditions.leftChild)
						.isEmpty()) {
					filteredList.add(o);
				}
			}
		} else if (conditions.isAnd()) {
			// Filter AND
			for (Object o : list) {
				if (!filter(new HashSet<>(Arrays.asList(o)), type, conditions.leftChild)
						.isEmpty()) {
					if (!filter(new HashSet<>(Arrays.asList(o)), type, conditions.rightChild)
							.isEmpty()) {
						filteredList.add(o);
					}
				}
			}
		} else if (conditions.isOr()) {
			// Filter OR
			for (Object o : list) {
				if (filter(new HashSet<>(Arrays.asList(o)), type, conditions.leftChild)
						.isEmpty()) {
					if (!filter(new HashSet<>(Arrays.asList(o)), type, conditions.rightChild)
							.isEmpty()) {
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

	public FilterTree createNode(FilterTree left, FilterTree right, int operator) {
		if (operator == FilterTree.NODE_AND) {
			return createAndNode(left, right);
		} else if (operator == FilterTree.NODE_OR) {
			return createOrNode(left, right);
		} else if (operator == FilterTree.NODE_NOT) {
			return createNotNode(left);
		} else {
			// ERROR
			System.err.println("Unknown Node Type");
			return null;
		}
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

	public FilterTree createChangedTerminalFilter(String key, String from,
			String to) {
		FilterTree node = new FilterTree();
		node.node = FilterTree.NODE_TERMINAL;
		node.operator = FilterTree.OPERATOR_CHANGED;
		node.key = key;
		node.valueFrom = from;
		node.valueTo = to;
		node.att = true;
		return node;
	}

	public FilterTree createTerminalFilter(int id, String key, String value,
			int operator, boolean att) {
		FilterTree node = new FilterTree();
		node.node = FilterTree.NODE_TERMINAL;
		node.operator = operator;
		node.key = key;
		node.keyId = id;
		node.value = value;
		node.att = att;
		return node;
	}

	public Set<Object> objectsOf(Set<Object> list, Class type) {
		
		HashSet<Object> listResult = new HashSet<>();
	 	
		if (type == SLEXMMObject.class) {
			return list;
		} else if (type == SLEXMMEvent.class) {
			for (Object o : list) {
				SLEXMMEvent ob = (SLEXMMEvent) o;

				SLEXMMObjectResultSet orset = slxmm.getObjectsForEvent(ob);
				SLEXMMObject slxo = null;
				while ((slxo = orset.getNext()) != null) {
					listResult.add(slxo);
				}
			}
		} else if (type == SLEXMMCase.class) {
			for (Object o : list) {
				SLEXMMCase c = (SLEXMMCase) o;
				
				SLEXMMObjectResultSet orset = slxmm.getObjectsForCase(c.getId());
				SLEXMMObject slxo = null;
				while ((slxo = orset.getNext()) != null) {
					listResult.add(slxo);
				}
			}
		} else if (type == SLEXMMActivity.class) {
			for (Object o : list) {
				SLEXMMActivity a = (SLEXMMActivity) o;
				
				SLEXMMObjectResultSet orset = slxmm.getObjectsForActivity(a.getId());
				SLEXMMObject slxo = null;
				while ((slxo = orset.getNext()) != null) {
					listResult.add(slxo);
				}
			}
		} else if (type == SLEXMMClass.class) {
			for (Object o : list) {
				SLEXMMClass c = (SLEXMMClass) o;

				SLEXMMObjectResultSet orset = slxmm.getObjectsPerClass(c
						.getId());
				SLEXMMObject ob = null;

				while ((ob = orset.getNext()) != null) {
					listResult.add(ob);
				}
			}
		} else if (type == SLEXMMRelationship.class) {
			for (Object o : list) {
				SLEXMMRelationship rs = (SLEXMMRelationship) o;
				
				SLEXMMObjectResultSet orset = slxmm.getObjectsForRelationship(rs.getId());
				SLEXMMObject slxo = null;
				while ((slxo = orset.getNext()) != null) {
					listResult.add(slxo);
				}
			}
		} else if (type == SLEXMMObjectVersion.class) {
			HashMap<Integer, SLEXMMObject> mapObjects = new HashMap<>();
			for (Object o : list) {
				SLEXMMObjectVersion ob = (SLEXMMObjectVersion) o;
				if (!mapObjects.containsKey(ob.getObjectId())) {
					SLEXMMObject obj = slxmm.getObjectPerId(ob.getObjectId());
					if (obj != null) {
						mapObjects.put(obj.getId(), obj);
					}
				}
			}

			listResult.addAll(mapObjects.values());
		} else if (type == SLEXMMRelation.class) {
			for (Object o : list) {
				SLEXMMRelation r = (SLEXMMRelation) o;
				
				SLEXMMObjectResultSet orset = slxmm.getObjectsForRelation(r.getId());
				SLEXMMObject slxo = null;
				while ((slxo = orset.getNext()) != null) {
					listResult.add(slxo);
				}
			}
		} else if (type == SLEXMMActivityInstance.class) {
			for (Object o : list) {
				SLEXMMActivityInstance ai = (SLEXMMActivityInstance) o;
				
				SLEXMMObjectResultSet orset = slxmm.getObjectsForActivityInstance(ai.getId());
				SLEXMMObject slxo = null;
				while ((slxo = orset.getNext()) != null) {
					listResult.add(slxo);
				}
			}
		} else if (type == SLEXMMAttribute.class) {
			for (Object o : list) {
				SLEXMMAttribute at = (SLEXMMAttribute) o;
				
				SLEXMMObjectResultSet orset = slxmm.getObjectsForAttribute(at.getId());
				SLEXMMObject slxo = null;
				while ((slxo = orset.getNext()) != null) {
					listResult.add(slxo);
				}
			}
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public Set<Object> casesOf(Set<Object> list, Class type) {
		HashSet<Object> listResult = new HashSet<>();
	 	
		if (type == SLEXMMObject.class) {
			for (Object o : list) {
				SLEXMMObject ob = (SLEXMMObject) o;
				
				SLEXMMCaseResultSet crset = slxmm.getCasesForObject(ob.getId());
				SLEXMMCase slxc = null;
				while ((slxc = crset.getNext()) != null) {
					listResult.add(slxc);
				}
			}
		} else if (type == SLEXMMEvent.class) {
			for (Object o : list) {
				SLEXMMEvent ev = (SLEXMMEvent) o;
				
				SLEXMMCaseResultSet crset = slxmm.getCasesForEvent(ev.getId());
				SLEXMMCase slxc = null;
				while ((slxc = crset.getNext()) != null) {
					listResult.add(slxc);
				}
			}
		} else if (type == SLEXMMCase.class) {
			return list;
		} else if (type == SLEXMMActivity.class) {
			for (Object o : list) {
				SLEXMMActivity ac = (SLEXMMActivity) o;
				
				SLEXMMCaseResultSet crset = slxmm.getCasesForActivity(ac.getId());
				SLEXMMCase slxc = null;
				while ((slxc = crset.getNext()) != null) {
					listResult.add(slxc);
				}
			}
		} else if (type == SLEXMMClass.class) {
			for (Object o : list) {
				SLEXMMClass c = (SLEXMMClass) o;
				
				SLEXMMCaseResultSet crset = slxmm.getCasesForClass(c.getId());
				SLEXMMCase slxc = null;
				while ((slxc = crset.getNext()) != null) {
					listResult.add(slxc);
				}
			}
		} else if (type == SLEXMMRelationship.class) {
			for (Object o : list) {
				SLEXMMRelationship rs = (SLEXMMRelationship) o;
				
				SLEXMMCaseResultSet crset = slxmm.getCasesForRelationship(rs.getId());
				SLEXMMCase slxc = null;
				while ((slxc = crset.getNext()) != null) {
					listResult.add(slxc);
				}
			}
		} else if (type == SLEXMMObjectVersion.class) {
			for (Object o : list) {
				SLEXMMObjectVersion ov = (SLEXMMObjectVersion) o;
				
				SLEXMMCaseResultSet crset = slxmm.getCasesForObjectVersion(ov.getId());
				SLEXMMCase slxc = null;
				while ((slxc = crset.getNext()) != null) {
					listResult.add(slxc);
				}
			}
		} else if (type == SLEXMMRelation.class) {
			for (Object o : list) {
				SLEXMMRelation r = (SLEXMMRelation) o;
				
				SLEXMMCaseResultSet crset = slxmm.getCasesForRelation(r.getId());
				SLEXMMCase slxc = null;
				while ((slxc = crset.getNext()) != null) {
					listResult.add(slxc);
				}
			}
		} else if (type == SLEXMMActivityInstance.class) {
			for (Object o : list) {
				SLEXMMActivityInstance ai = (SLEXMMActivityInstance) o;
				
				SLEXMMCaseResultSet crset = slxmm.getCasesForActivityInstance(ai.getId());
				SLEXMMCase slxc = null;
				while ((slxc = crset.getNext()) != null) {
					listResult.add(slxc);
				}
			}
		} else if (type == SLEXMMAttribute.class) {
			for (Object o : list) {
				SLEXMMAttribute at = (SLEXMMAttribute) o;
				
				SLEXMMCaseResultSet crset = slxmm.getCasesForAttribute(at.getId());
				SLEXMMCase slxc = null;
				while ((slxc = crset.getNext()) != null) {
					listResult.add(slxc);
				}
			}
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public Set<Object> eventsOf(Set<Object> list, Class type) {
		
		HashSet<Object> listResult = new HashSet<>();
	 	
		if (type == SLEXMMObject.class) {
			for (Object o : list) {
				SLEXMMObject ob = (SLEXMMObject) o;

				SLEXMMEventResultSet erset = slxmm.getEventsForObject(ob.getId());
				SLEXMMEvent e = null;
				while ((e = erset.getNext()) != null) {
					listResult.add(e);
				}
			}
		} else if (type == SLEXMMEvent.class) {
			return list;
		} else if (type == SLEXMMCase.class) {
			for (Object o : list) {
				SLEXMMCase c = (SLEXMMCase) o;

				SLEXMMEventResultSet erset = slxmm.getEventsForCaseOrdered(c.getId());
				SLEXMMEvent e = null;
				while ((e = erset.getNext()) != null) {
					listResult.add(e);
				}
			}
		} else if (type == SLEXMMActivity.class) {
			for (Object o : list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;

				SLEXMMEventResultSet erset = slxmm.getEventsForActivity(ob);
				SLEXMMEvent e = null;
				while ((e = erset.getNext()) != null) {
					listResult.add(e);
				}
			}
		} else if (type == SLEXMMClass.class) {
			for (Object o : list) {
				SLEXMMClass cl = (SLEXMMClass) o;

				SLEXMMEventResultSet erset = slxmm.getEventsForClass(cl.getId());
				SLEXMMEvent e = null;
				while ((e = erset.getNext()) != null) {
					listResult.add(e);
				}
			}
		} else if (type == SLEXMMRelationship.class) {
			for (Object o : list) {
				SLEXMMRelationship rs = (SLEXMMRelationship) o;

				SLEXMMEventResultSet erset = slxmm.getEventsForRelationship(rs.getId());
				SLEXMMEvent e = null;
				while ((e = erset.getNext()) != null) {
					listResult.add(e);
				}
			}
		} else if (type == SLEXMMObjectVersion.class) {
			for (Object o : list) {
				SLEXMMObjectVersion ov = (SLEXMMObjectVersion) o;

				SLEXMMEventResultSet erset = slxmm.getEventsForObjectVersion(ov
						.getId());
				SLEXMMEvent ev = null;

				while ((ev = erset.getNext()) != null) {
					listResult.add(ev);
				}
			}
		} else if (type == SLEXMMRelation.class) {
			for (Object o : list) {
				SLEXMMRelation r = (SLEXMMRelation) o;

				SLEXMMEventResultSet erset = slxmm.getEventsForRelation(r.getId());
				SLEXMMEvent e = null;
				while ((e = erset.getNext()) != null) {
					listResult.add(e);
				}
			}
		} else if (type == SLEXMMActivityInstance.class) {
			for (Object o : list) {
				SLEXMMActivityInstance ai = (SLEXMMActivityInstance) o;

				SLEXMMEventResultSet erset = slxmm.getEventsForActivityInstance(ai.getId());
				SLEXMMEvent e = null;
				while ((e = erset.getNext()) != null) {
					listResult.add(e);
				}
			}
		} else if (type == SLEXMMAttribute.class) {
			for (Object o : list) {
				SLEXMMAttribute at = (SLEXMMAttribute) o;

				SLEXMMEventResultSet erset = slxmm.getEventsForAttribute(at.getId());
				SLEXMMEvent e = null;
				while ((e = erset.getNext()) != null) {
					listResult.add(e);
				}
			}
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
		
	}

	public Set<Object> versionsOf(Set<Object> list, Class type) {
		HashSet<Object> listResult = new HashSet<>();
		
		if (type == SLEXMMObject.class) {
			for (Object o : list) {
				SLEXMMObject ob = (SLEXMMObject) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm
						.getObjectVersionsForObjectOrdered(ob);
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMEvent.class) {
			for (Object o : list) {
				SLEXMMEvent ev = (SLEXMMEvent) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersionsForEvent(ev.getId());
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMCase.class) {
			for (Object o : list) {
				SLEXMMCase c = (SLEXMMCase) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersionsForCase(c.getId());
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMActivity.class) {
			for (Object o : list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersionsForActivity(ob);
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMClass.class) {
			for (Object o : list) {
				SLEXMMClass cl = (SLEXMMClass) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersionsForClass(cl.getId());
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMRelationship.class) {
			for (Object o : list) {
				SLEXMMRelationship rs = (SLEXMMRelationship) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersionsForRelationship(rs.getId());
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMObjectVersion.class) {
			return list;
		} else if (type == SLEXMMRelation.class) {
			for (Object o : list) {
				SLEXMMRelation r = (SLEXMMRelation) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersionsForRelation(r.getId());
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMActivityInstance.class) {
			for (Object o : list) {
				SLEXMMActivityInstance ai = (SLEXMMActivityInstance) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersionsForActivityInstance(ai.getId());
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMAttribute.class) {
			for (Object o : list) {
				SLEXMMAttribute at = (SLEXMMAttribute) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersionsForAttribute(at.getId());
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

	public Set<Object> activitiesOf(Set<Object> list, Class type) {
		HashSet<Object> listResult = new HashSet<>();
	 	
		if (type == SLEXMMObject.class) {
			for (Object o : list) {
				SLEXMMObject ob = (SLEXMMObject) o;

				SLEXMMActivityResultSet ovrset = slxmm.getActivitiesForObject(ob.getId());
				SLEXMMActivity ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMEvent.class) {
			for (Object o : list) {
				SLEXMMEvent ob = (SLEXMMEvent) o;

				SLEXMMActivityResultSet ovrset = slxmm.getActivitiesForEvent(ob.getId());
				SLEXMMActivity ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMCase.class) {
			for (Object o : list) {
				SLEXMMCase ob = (SLEXMMCase) o;

				SLEXMMActivityResultSet ovrset = slxmm.getActivitiesForCase(ob.getId());
				SLEXMMActivity ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMActivity.class) {
			return list;
		} else if (type == SLEXMMClass.class) {
			for (Object o : list) {
				SLEXMMClass ob = (SLEXMMClass) o;

				SLEXMMActivityResultSet ovrset = slxmm.getActivitiesForClass(ob.getId());
				SLEXMMActivity ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMRelationship.class) {
			for (Object o : list) {
				SLEXMMRelationship ob = (SLEXMMRelationship) o;

				SLEXMMActivityResultSet ovrset = slxmm.getActivitiesForRelationship(ob.getId());
				SLEXMMActivity ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMObjectVersion.class) {
			for (Object o : list) {
				SLEXMMObjectVersion ob = (SLEXMMObjectVersion) o;

				SLEXMMActivityResultSet ovrset = slxmm.getActivitiesForObjectVersion(ob.getId());
				SLEXMMActivity ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMRelation.class) {
			for (Object o : list) {
				SLEXMMRelation ob = (SLEXMMRelation) o;

				SLEXMMActivityResultSet ovrset = slxmm.getActivitiesForRelation(ob.getId());
				SLEXMMActivity ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMActivityInstance.class) {
			for (Object o : list) {
				SLEXMMActivityInstance ob = (SLEXMMActivityInstance) o;

				SLEXMMActivityResultSet ovrset = slxmm.getActivitiesForActivityInstance(ob.getId());
				SLEXMMActivity ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMAttribute.class) {
			for (Object o : list) {
				SLEXMMAttribute ob = (SLEXMMAttribute) o;

				SLEXMMActivityResultSet ovrset = slxmm.getActivitiesForAttribute(ob.getId());
				SLEXMMActivity ov = null;
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

	public Set<Object> classesOf(Set<Object> list, Class type) {
		HashSet<Object> listResult = new HashSet<>();
	 	
		if (type == SLEXMMObject.class) {
			for (Object o : list) {
				SLEXMMObject ob = (SLEXMMObject) o;

				SLEXMMClassResultSet crset = slxmm.getClassesForObject(ob.getId());
				SLEXMMClass c = null;
				while ((c = crset.getNext()) != null) {
					listResult.add(c);
				}
			}
		} else if (type == SLEXMMEvent.class) {
			for (Object o : list) {
				SLEXMMEvent ob = (SLEXMMEvent) o;

				SLEXMMClassResultSet crset = slxmm.getClassesForEvent(ob.getId());
				SLEXMMClass c = null;
				while ((c = crset.getNext()) != null) {
					listResult.add(c);
				}
			}
		} else if (type == SLEXMMCase.class) {
			for (Object o : list) {
				SLEXMMCase ob = (SLEXMMCase) o;

				SLEXMMClassResultSet crset = slxmm.getClassesForCase(ob.getId());
				SLEXMMClass c = null;
				while ((c = crset.getNext()) != null) {
					listResult.add(c);
				}
			}
		} else if (type == SLEXMMActivity.class) {
			for (Object o : list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;

				SLEXMMClassResultSet crset = slxmm.getClassesForActivity(ob.getId());
				SLEXMMClass c = null;
				while ((c = crset.getNext()) != null) {
					listResult.add(c);
				}
			}
		} else if (type == SLEXMMClass.class) {
			return list;
		} else if (type == SLEXMMRelationship.class) {
			for (Object o : list) {
				SLEXMMRelationship ob = (SLEXMMRelationship) o;

				SLEXMMClassResultSet crset = slxmm.getClassesForRelationship(ob.getId());
				SLEXMMClass c = null;
				while ((c = crset.getNext()) != null) {
					listResult.add(c);
				}
			}
		} else if (type == SLEXMMObjectVersion.class) {
			for (Object o : list) {
				SLEXMMObjectVersion ob = (SLEXMMObjectVersion) o;

				SLEXMMClassResultSet crset = slxmm.getClassesForObjectVersion(ob.getId());
				SLEXMMClass c = null;
				while ((c = crset.getNext()) != null) {
					listResult.add(c);
				}
			}
		} else if (type == SLEXMMRelation.class) {
			for (Object o : list) {
				SLEXMMRelation ob = (SLEXMMRelation) o;

				SLEXMMClassResultSet crset = slxmm.getClassesForRelation(ob.getId());
				SLEXMMClass c = null;
				while ((c = crset.getNext()) != null) {
					listResult.add(c);
				}
			}
		} else if (type == SLEXMMActivityInstance.class) {
			for (Object o : list) {
				SLEXMMActivityInstance ob = (SLEXMMActivityInstance) o;

				SLEXMMClassResultSet crset = slxmm.getClassesForActivityInstance(ob.getId());
				SLEXMMClass c = null;
				while ((c = crset.getNext()) != null) {
					listResult.add(c);
				}
			}
		} else if (type == SLEXMMAttribute.class) {
			for (Object o : list) {
				SLEXMMAttribute ob = (SLEXMMAttribute) o;

				SLEXMMClassResultSet crset = slxmm.getClassesForAttribute(ob.getId());
				SLEXMMClass c = null;
				while ((c = crset.getNext()) != null) {
					listResult.add(c);
				}
			}
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public Set<Object> relationsOf(Set<Object> list, Class type) {
		HashSet<Object> listResult = new HashSet<>();
	 	
		if (type == SLEXMMObject.class) {
			for (Object o : list) {
				SLEXMMObject ob = (SLEXMMObject) o;

				SLEXMMRelationResultSet rrset = slxmm.getRelationsForObject(ob.getId());
				SLEXMMRelation r = null;
				while ((r = rrset.getNext()) != null) {
					listResult.add(r);
				}
			}
		} else if (type == SLEXMMEvent.class) {
			for (Object o : list) {
				SLEXMMEvent ob = (SLEXMMEvent) o;

				SLEXMMRelationResultSet rrset = slxmm.getRelationsForEvent(ob.getId());
				SLEXMMRelation r = null;
				while ((r = rrset.getNext()) != null) {
					listResult.add(r);
				}
			}
		} else if (type == SLEXMMCase.class) { // TODO Check
			int[][] ids = getArrayIds(list,type);
			for (int i = 0; i < ids.length; i++) {
				SLEXMMRelationResultSet rrset = slxmm.getRelationsForCases(ids[i]);
				SLEXMMRelation r = null;
				while ((r = rrset.getNext()) != null) {
					listResult.add(r);
				}
			}
		} else if (type == SLEXMMActivity.class) { // TODO Check
			for (Object o : list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;

				SLEXMMRelationResultSet rrset = slxmm.getRelationsForActivity(ob.getId());
				SLEXMMRelation r = null;
				while ((r = rrset.getNext()) != null) {
					listResult.add(r);
				}
			}
		} else if (type == SLEXMMClass.class) {
			int[][] ids = getArrayIds(list,type);
			for (int i = 0; i < ids.length; i++) {
				SLEXMMRelationResultSet rrset = slxmm.getRelationsForClasses(ids[i]);
				SLEXMMRelation r = null;
				while ((r = rrset.getNext()) != null) {
					listResult.add(r);
				}
			}
		} else if (type == SLEXMMRelationship.class) {
			for (Object o : list) {
				SLEXMMRelationship ob = (SLEXMMRelationship) o;

				SLEXMMRelationResultSet rrset = slxmm.getRelationsForRelationship(ob.getId());
				SLEXMMRelation r = null;
				while ((r = rrset.getNext()) != null) {
					listResult.add(r);
				}
			}
		} else if (type == SLEXMMObjectVersion.class) {
			for (Object o : list) {
				SLEXMMObjectVersion ob = (SLEXMMObjectVersion) o;

				SLEXMMRelationResultSet rrset = slxmm.getRelationsForObjectVersion(ob.getId());
				SLEXMMRelation r = null;
				while ((r = rrset.getNext()) != null) {
					listResult.add(r);
				}
			}
		} else if (type == SLEXMMRelation.class) {
			return list;
		} else if (type == SLEXMMActivityInstance.class) { // TODO Check
			for (Object o : list) {
				SLEXMMActivityInstance ob = (SLEXMMActivityInstance) o;

				SLEXMMRelationResultSet rrset = slxmm.getRelationsForActivityInstance(ob.getId());
				SLEXMMRelation r = null;
				while ((r = rrset.getNext()) != null) {
					listResult.add(r);
				}
			}
		} else if (type == SLEXMMAttribute.class) {
			for (Object o : list) {
				SLEXMMAttribute ob = (SLEXMMAttribute) o;

				SLEXMMRelationResultSet rrset = slxmm.getRelationsForAttribute(ob.getId());
				SLEXMMRelation r = null;
				while ((r = rrset.getNext()) != null) {
					listResult.add(r);
				}
			}
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public Set<Object> relationshipsOf(Set<Object> list, Class type) {
		HashSet<Object> listResult = new HashSet<>();
	 	
		if (type == SLEXMMObject.class) { // TODO Check
			for (Object o : list) {
				SLEXMMObject ob = (SLEXMMObject) o;

				SLEXMMRelationshipResultSet rsrset = slxmm.getRelationshipsForObject(ob.getId());
				SLEXMMRelationship rs = null;
				while ((rs = rsrset.getNext()) != null) {
					listResult.add(rs);
				}
			}
		} else if (type == SLEXMMEvent.class) { // TODO Check
			for (Object o : list) {
				SLEXMMEvent ob = (SLEXMMEvent) o;

				SLEXMMRelationshipResultSet rsrset = slxmm.getRelationshipsForEvent(ob.getId());
				SLEXMMRelationship rs = null;
				while ((rs = rsrset.getNext()) != null) {
					listResult.add(rs);
				}
			}
		} else if (type == SLEXMMCase.class) { // TODO Check
			for (Object o : list) {
				SLEXMMCase ob = (SLEXMMCase) o;

				SLEXMMRelationshipResultSet rsrset = slxmm.getRelationshipsForCase(ob.getId());
				SLEXMMRelationship rs = null;
				while ((rs = rsrset.getNext()) != null) {
					listResult.add(rs);
				}
			}
		} else if (type == SLEXMMActivity.class) { // TODO Check
			for (Object o : list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;

				SLEXMMRelationshipResultSet rsrset = slxmm.getRelationshipsForActivity(ob.getId());
				SLEXMMRelationship rs = null;
				while ((rs = rsrset.getNext()) != null) {
					listResult.add(rs);
				}
			}
		} else if (type == SLEXMMClass.class) { // TODO Check
			for (Object o : list) {
				SLEXMMClass ob = (SLEXMMClass) o;

				SLEXMMRelationshipResultSet rsrset = slxmm.getRelationshipsForClass(ob.getId());
				SLEXMMRelationship rs = null;
				while ((rs = rsrset.getNext()) != null) {
					listResult.add(rs);
				}
			}
		} else if (type == SLEXMMRelationship.class) { // TODO Check
			return list;
		} else if (type == SLEXMMObjectVersion.class) { // TODO Check
			for (Object o : list) {
				SLEXMMObjectVersion ob = (SLEXMMObjectVersion) o;

				SLEXMMRelationshipResultSet rsrset = slxmm.getRelationshipsForObjectVersion(ob.getId());
				SLEXMMRelationship rs = null;
				while ((rs = rsrset.getNext()) != null) {
					listResult.add(rs);
				}
			}
		} else if (type == SLEXMMRelation.class) { // TODO Check
			for (Object o : list) {
				SLEXMMRelation ob = (SLEXMMRelation) o;

				SLEXMMRelationshipResultSet rsrset = slxmm.getRelationshipsForRelation(ob.getId());
				SLEXMMRelationship rs = null;
				while ((rs = rsrset.getNext()) != null) {
					listResult.add(rs);
				}
			}
		} else if (type == SLEXMMActivityInstance.class) { // TODO Check
			for (Object o : list) {
				SLEXMMActivityInstance ob = (SLEXMMActivityInstance) o;

				SLEXMMRelationshipResultSet rsrset = slxmm.getRelationshipsForActivityInstance(ob.getId());
				SLEXMMRelationship rs = null;
				while ((rs = rsrset.getNext()) != null) {
					listResult.add(rs);
				}
			}
		} else if (type == SLEXMMAttribute.class) { // TODO Check
			for (Object o : list) {
				SLEXMMAttribute ob = (SLEXMMAttribute) o;

				SLEXMMRelationshipResultSet rsrset = slxmm.getRelationshipsForAttribute(ob.getId());
				SLEXMMRelationship rs = null;
				while ((rs = rsrset.getNext()) != null) {
					listResult.add(rs);
				}
			}
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public Set<Object> activityInstancesOf(Set<Object> list, Class type) {
		HashSet<Object> listResult = new HashSet<>();
	 	
		if (type == SLEXMMObject.class) {
			// TODO
		} else if (type == SLEXMMEvent.class) {
			// TODO
		} else if (type == SLEXMMCase.class) {
			// TODO
		} else if (type == SLEXMMActivity.class) {
			// TODO
		} else if (type == SLEXMMClass.class) {
			// TODO
		} else if (type == SLEXMMRelationship.class) {
			// TODO
		} else if (type == SLEXMMObjectVersion.class) {
			// TODO
		} else if (type == SLEXMMRelation.class) {
			// TODO
		} else if (type == SLEXMMActivityInstance.class) {
			return list;
		} else if (type == SLEXMMAttribute.class) {
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}
	
	public Set<Object> attributesOf(Set<Object> list, Class type) {
		HashSet<Object> listResult = new HashSet<>();
	 	
		if (type == SLEXMMObject.class) {
			// TODO
		} else if (type == SLEXMMEvent.class) {
			// TODO
		} else if (type == SLEXMMCase.class) {
			// TODO
		} else if (type == SLEXMMActivity.class) {
			// TODO
		} else if (type == SLEXMMClass.class) {
			// TODO
		} else if (type == SLEXMMRelationship.class) {
			// TODO
		} else if (type == SLEXMMObjectVersion.class) {
			// TODO
		} else if (type == SLEXMMRelation.class) {
			// TODO
		} else if (type == SLEXMMActivityInstance.class) {
			// TODO
		} else if (type == SLEXMMAttribute.class) {
			return list;
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}
	
	private int[][] getArrayIds(Set<Object> list, Class type) {
		Iterator<Object> it = list.iterator();
		int remaining = list.size();
		int numArrays = (int) Math.ceil(((float)remaining / (float)MAX_IDS_ARRAY_SIZE));
		int[][] idsArrays = new int[numArrays][];
		for (int a = 0; a < numArrays; a++) {
			int size = 0;
			if (remaining > MAX_IDS_ARRAY_SIZE) {
				remaining -= MAX_IDS_ARRAY_SIZE;
				size = MAX_IDS_ARRAY_SIZE;
			} else {
				size = remaining;
				remaining = 0;
			}
			idsArrays[a] = new int[size];
			int[] ids = idsArrays[a];
			
			if (type == SLEXMMObject.class) {
				for (int i = 0; i < size; i++) {
					SLEXMMObject ob = (SLEXMMObject) it.next();
					ids[i] = ob.getId();
				}
			} else if (type == SLEXMMEvent.class) {
				for (int i = 0; i < size; i++) {
					SLEXMMEvent ob = (SLEXMMEvent) it.next();
					ids[i] = ob.getId();
				}
			} else if (type == SLEXMMCase.class) {
				for (int i = 0; i < size; i++) {
					SLEXMMCase ob = (SLEXMMCase) it.next();
					ids[i] = ob.getId();
				}
			} else if (type == SLEXMMActivity.class) {
				for (int i = 0; i < size; i++) {
					SLEXMMActivity ob = (SLEXMMActivity) it.next();
					ids[i] = ob.getId();
				}
			} else if (type == SLEXMMClass.class) {
				for (int i = 0; i < size; i++) {
					SLEXMMClass ob = (SLEXMMClass) it.next();
					ids[i] = ob.getId();
				}
			} else if (type == SLEXMMRelationship.class) {
				for (int i = 0; i < size; i++) {
					SLEXMMRelationship ob = (SLEXMMRelationship) it.next();
					ids[i] = ob.getId();
				}
			} else if (type == SLEXMMObjectVersion.class) {
				for (int i = 0; i < size; i++) {
					SLEXMMObjectVersion ob = (SLEXMMObjectVersion) it.next();
					ids[i] = ob.getId();
				}
			} else if (type == SLEXMMRelation.class) {
				for (int i = 0; i < size; i++) {
					SLEXMMRelation ob = (SLEXMMRelation) it.next();
					ids[i] = ob.getId();
				}
			} else if (type == SLEXMMActivityInstance.class) {
				for (int i = 0; i < size; i++) {
					SLEXMMActivityInstance ob = (SLEXMMActivityInstance) it.next();
					ids[i] = ob.getId();
				}
			} else if (type == SLEXMMAttribute.class) {
				for (int i = 0; i < size; i++) {
					SLEXMMAttribute ob = (SLEXMMAttribute) it.next();
					ids[i] = ob.getId();
				}
			} else {
				// ERROR
				System.err.println("Unknown type");
			}
		}
		
		
		return idsArrays;
	}

	public Set<Object> versionsRelatedTo(Set<Object> list, Class type) {
		HashSet<Object> setResult = new HashSet<>();

		if (type == SLEXMMObjectVersion.class) {
			for (Object o : list) {
				SLEXMMObjectVersion ob = (SLEXMMObjectVersion) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm
						.getVersionsRelatedToObjectVersion(ob);

				SLEXMMObjectVersion ov = null;

				while ((ov = ovrset.getNext()) != null) {
					setResult.add(ov);
				}
			}
		} else {
			// ERROR
			System.err.println("Unknown type");
		}

		return setResult;
	}

	public Set<Object> getAllObjects() {
		HashSet<Object> list = new HashSet<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMObjectResultSet orset = slxmm.getObjects();
			SLEXMMObject o = null;
			while ((o = orset.getNext()) != null) {
				list.add(o);
			}
		}
		return list;
	}

	public Set<Object> getAllCases() {
		HashSet<Object> list = new HashSet<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMCaseResultSet crset = slxmm.getCases();
			SLEXMMCase c = null;
			while ((c = crset.getNext()) != null) {
				list.add(c);
			}
		}
		return list;
	}

	public Set<Object> getAllEvents() {
		HashSet<Object> list = new HashSet<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMEventResultSet erset = slxmm.getEvents();
			SLEXMMEvent e = null;
			while ((e = erset.getNext()) != null) {
				list.add(e);
			}
		}
		return list;
	}

	public Set<Object> getAllVersions() {
		HashSet<Object> list = new HashSet<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersions();
			SLEXMMObjectVersion ov = null;
			while ((ov = ovrset.getNext()) != null) {
				list.add(ov);
			}
		}
		return list;
	}

	public Set<Object> getAllActivities() {
		HashSet<Object> list = new HashSet<>();
		if (!isCheckerModeEnabled()) {
			list.addAll(slxmm.getActivities());
		}
		return list;
	}

	public Set<Object> getAllClasses() {
		HashSet<Object> list = new HashSet<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMDataModelResultSet dms = slxmm.getDataModels();
			SLEXMMDataModel dm = null;
			while ((dm = dms.getNext()) != null) {
				SLEXMMClassResultSet crset = slxmm.getClassesForDataModel(dm);
				SLEXMMClass cl = null;
				while ((cl = crset.getNext()) != null) {
					list.add(cl);
				}
			}
		}
		return list;
	}

	public Set<Object> getAllRelations() {
		HashSet<Object> list = new HashSet<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMRelationResultSet rrset = slxmm.getRelations();
			SLEXMMRelation r = null;
			while ((r = rrset.getNext()) != null) {
				list.add(r);
			}
		}
		return list;
	}

	public Set<Object> getAllRelationships() {
		HashSet<Object> list = new HashSet<>();
		if (!isCheckerModeEnabled()) {
			list.addAll(slxmm.getRelationships());
		}
		return list;
	}

	public Set<Object> getAllActivityInstances() {
		HashSet<Object> list = new HashSet<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMActivityInstanceResultSet airset = slxmm.getActivityInstances();
			SLEXMMActivityInstance ai = null;
			while ((ai = airset.getNext()) != null) {
				list.add(ai);
			}
		}
		return list;
	}

	public Set<Object> getAllAttributes() {
		HashSet<Object> list = new HashSet<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMAttributeResultSet arset = slxmm.getAttributes();
			SLEXMMAttribute at = null;
			while ((at = arset.getNext()) != null) {
				list.add(at);
			}
		}
		return list;
	}
	
	public void computeSuggestions(Token offendingToken, Set<Integer> set) {
		List<String> suggestions = new ArrayList<>();
		for (Integer i : set) {
			if (i >= 0) {
				String name = vocabulary.getLiteralName(i);
				if (name == null) {
					name = vocabulary.getSymbolicName(i);
				} else {
					name = name.substring(1, name.length() - 1);
				}
				if (i == poqlParser.STRING) {
					name = "\"\"";
				} else if (i == poqlParser.IDATT) {
					name = "at.";
				} else if (i == poqlParser.EQUAL) {
					name = "==";
				} else if (i == poqlParser.EQUAL_OR_GREATER) {
					name = "=>";
				} else if (i == poqlParser.EQUAL_OR_SMALLER) {
					name = "=<";
				} else if (i == poqlParser.SMALLER) {
					name = "<";
				} else if (i == poqlParser.GREATER) {
					name = ">";
				} else if (i == poqlParser.DIFFERENT) {
					name = "<>";
				}
				suggestions.add(name);
			}
		}

		System.out.println(suggestions);
		this.suggestions = suggestions;
		this.offendingToken = offendingToken;
	}

	public List<String> getSuggestions() {
		return this.suggestions;
	}

	public Token getOffendingToken() {
		return this.offendingToken;
	}

	public void setVocabulary(Vocabulary vocabulary) {
		this.vocabulary = vocabulary;
	}
}
