package org.processmining.database.metamodel.poql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.processmining.openslex.metamodel.SLEXMMActivity;
import org.processmining.openslex.metamodel.SLEXMMActivityInstance;
import org.processmining.openslex.metamodel.SLEXMMActivityInstanceResultSet;
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
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
import org.processmining.redologs.ui.components.Autocomplete;

public class POQLFunctions {

	private SLEXMMStorageMetaModel slxmm = null;
	private boolean checkerMode = false;
	private List<String> suggestions = null;
	private Token offendingToken = null;
	private Vocabulary vocabulary = null;

	public void setCheckerMode(boolean mode) {
		this.checkerMode = mode;
	}

	public boolean isCheckerModeEnabled() {
		return this.checkerMode;
	}

	public void setMetaModel(SLEXMMStorageMetaModel strg) {
		this.slxmm = strg;
	}

	public List<Object> filterTerminal(List<Object> list, Class type,
			FilterTree condition) {
		List<Object> filteredList = new ArrayList<>();

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

	public List<Object> filter(List<Object> list, Class type,
			FilterTree conditions) {
		List<Object> filteredList = new ArrayList<>();

		if (conditions.isTerminal()) {
			// Filter terminal
			return filterTerminal(list, type, conditions);
		} else if (conditions.isNot()) {
			// Filter NOT
			for (Object o : list) {
				if (filter(Arrays.asList(o), type, conditions.leftChild)
						.isEmpty()) {
					filteredList.add(o);
				}
			}
		} else if (conditions.isAnd()) {
			// Filter AND
			for (Object o : list) {
				if (!filter(Arrays.asList(o), type, conditions.leftChild)
						.isEmpty()) {
					if (!filter(Arrays.asList(o), type, conditions.rightChild)
							.isEmpty()) {
						filteredList.add(o);
					}
				}
			}
		} else if (conditions.isOr()) {
			// Filter OR
			for (Object o : list) {
				if (filter(Arrays.asList(o), type, conditions.leftChild)
						.isEmpty()) {
					if (!filter(Arrays.asList(o), type, conditions.rightChild)
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

	public List<Object> objectsOf(List<Object> list, Class type) {
		
		ArrayList<Object> listResult = new ArrayList<>();
	 	
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
			// TODO
		} else if (type == SLEXMMActivity.class) {
			// TODO
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
			// TODO
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
			// TODO
		} else if (type == SLEXMMActivityInstance.class) {
			// TODO
		} else if (type == SLEXMMAttribute.class) {
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public List<Object> casesOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
	 	
		if (type == SLEXMMObject.class) {
			// TODO
		} else if (type == SLEXMMEvent.class) {
			// TODO
		} else if (type == SLEXMMCase.class) {
			return list;
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
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public List<Object> eventsOf(List<Object> list, Class type) {
		
		ArrayList<Object> listResult = new ArrayList<>();
	 	
		if (type == SLEXMMObject.class) {
			// TODO
		} else if (type == SLEXMMEvent.class) {
			return list;
		} else if (type == SLEXMMCase.class) {
			// TODO
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
			// TODO
		} else if (type == SLEXMMRelationship.class) {
			// TODO
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
			// TODO
		} else if (type == SLEXMMActivityInstance.class) {
			// TODO
		} else if (type == SLEXMMAttribute.class) {
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
		
	}

	public List<Object> versionsOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
		
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
			// TODO
		} else if (type == SLEXMMCase.class) {
			// TODO
		} else if (type == SLEXMMActivity.class) {
			for (Object o : list) {
				SLEXMMActivity ob = (SLEXMMActivity) o;

				SLEXMMObjectVersionResultSet ovrset = slxmm
						.getObjectVersionsForActivity(ob);
				SLEXMMObjectVersion ov = null;
				while ((ov = ovrset.getNext()) != null) {
					listResult.add(ov);
				}
			}
		} else if (type == SLEXMMClass.class) {
			// TODO
		} else if (type == SLEXMMRelationship.class) {
			// TODO
		} else if (type == SLEXMMObjectVersion.class) {
			return list;
		} else if (type == SLEXMMRelation.class) {
			// TODO
		} else if (type == SLEXMMActivityInstance.class) {
			// TODO
		} else if (type == SLEXMMAttribute.class) {
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public List<Object> activitiesOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
	 	
		if (type == SLEXMMObject.class) {
			// TODO
		} else if (type == SLEXMMEvent.class) {
			// TODO
		} else if (type == SLEXMMCase.class) {
			// TODO
		} else if (type == SLEXMMActivity.class) {
			return list;
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
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public List<Object> classesOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
	 	
		if (type == SLEXMMObject.class) {
			// TODO
		} else if (type == SLEXMMEvent.class) {
			// TODO
		} else if (type == SLEXMMCase.class) {
			// TODO
		} else if (type == SLEXMMActivity.class) {
			// TODO
		} else if (type == SLEXMMClass.class) {
			return list;
		} else if (type == SLEXMMRelationship.class) {
			// TODO
		} else if (type == SLEXMMObjectVersion.class) {
			// TODO
		} else if (type == SLEXMMRelation.class) {
			// TODO
		} else if (type == SLEXMMActivityInstance.class) {
			// TODO
		} else if (type == SLEXMMAttribute.class) {
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public List<Object> relationsOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
	 	
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
			return list;
		} else if (type == SLEXMMActivityInstance.class) {
			// TODO
		} else if (type == SLEXMMAttribute.class) {
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public List<Object> relationshipsOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
	 	
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
			return list;
		} else if (type == SLEXMMObjectVersion.class) {
			// TODO
		} else if (type == SLEXMMRelation.class) {
			// TODO
		} else if (type == SLEXMMActivityInstance.class) {
			// TODO
		} else if (type == SLEXMMAttribute.class) {
			// TODO
		} else {
			// ERROR
			System.err.println("Unknown type");
		}
		
		return listResult;
	}

	public List<Object> activityInstancesOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
	 	
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
	
	public List<Object> attributesOf(List<Object> list, Class type) {
		ArrayList<Object> listResult = new ArrayList<>();
	 	
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

	public List<Object> versionsRelatedTo(List<Object> list, Class type) {
		HashSet<SLEXMMObjectVersion> setResult = new HashSet<>();
		List<Object> listResult = new ArrayList<>();

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

		listResult.addAll(setResult);

		return listResult;
	}

	public List<Object> getAllObjects() {
		ArrayList<Object> list = new ArrayList<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMObjectResultSet orset = slxmm.getObjects();
			SLEXMMObject o = null;
			while ((o = orset.getNext()) != null) {
				list.add(o);
			}
		}
		return list;
	}

	public List<Object> getAllCases() {
		ArrayList<Object> list = new ArrayList<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMCaseResultSet crset = slxmm.getCases();
			SLEXMMCase c = null;
			while ((c = crset.getNext()) != null) {
				list.add(c);
			}
		}
		return list;
	}

	public List<Object> getAllEvents() {
		ArrayList<Object> list = new ArrayList<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMEventResultSet erset = slxmm.getEvents();
			SLEXMMEvent e = null;
			while ((e = erset.getNext()) != null) {
				list.add(e);
			}
		}
		return list;
	}

	public List<Object> getAllVersions() {
		ArrayList<Object> list = new ArrayList<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMObjectVersionResultSet ovrset = slxmm.getObjectVersions();
			SLEXMMObjectVersion ov = null;
			while ((ov = ovrset.getNext()) != null) {
				list.add(ov);
			}
		}
		return list;
	}

	public List<Object> getAllActivities() {
		ArrayList<Object> list = new ArrayList<>();
		if (!isCheckerModeEnabled()) {
			list.addAll(slxmm.getActivities());
		}
		return list;
	}

	public List<Object> getAllClasses() {
		ArrayList<Object> list = new ArrayList<>();
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

	public List<Object> getAllRelations() {
		ArrayList<Object> list = new ArrayList<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMRelationResultSet rrset = slxmm.getRelations();
			SLEXMMRelation r = null;
			while ((r = rrset.getNext()) != null) {
				list.add(r);
			}
		}
		return list;
	}

	public List<Object> getAllRelationships() {
		ArrayList<Object> list = new ArrayList<>();
		if (!isCheckerModeEnabled()) {
			list.addAll(slxmm.getRelationships());
		}
		return list;
	}

	public List<Object> getAllActivityInstances() {
		ArrayList<Object> list = new ArrayList<>();
		if (!isCheckerModeEnabled()) {
			SLEXMMActivityInstanceResultSet airset = slxmm.getActivityInstances();
			SLEXMMActivityInstance ai = null;
			while ((ai = airset.getNext()) != null) {
				list.add(ai);
			}
		}
		return list;
	}

	public List<Object> getAllAttributes() {
		ArrayList<Object> list = new ArrayList<>();
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
