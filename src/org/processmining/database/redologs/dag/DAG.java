package org.processmining.database.redologs.dag;

import java.util.HashMap;

public class DAG<T> {

	private HashMap<T,DAGNode<T>> nodes = new HashMap<>();
	private DAGNode<T> root;
	
	public DAG(T rootValue) {
		this.root = createNode(rootValue);
	}
	
	public DAGNode<T> getRoot() {
		return root;
	}
	
	public DAGNode<T> getNode(T v) {
		return nodes.get(v);
	}
	
	public DAGNode<T> createNode(T value) {
		DAGNode<T> n = null;
		if (nodes.containsKey(value)) {
			n = nodes.get(value);
		} else {
			n = new DAGNode<T>(value);
			nodes.put(value, n);
		}
		return n;
	}
	
	public void addChild(DAGNode<T> parent, T child) {
		addChild(parent,createNode(child));
	}
	
	public void addChild(DAGNode<T> parent, DAGNode<T> child) {
		parent.addChild(child);
	}
	
	public void addParent(DAGNode<T> child, T parent) {
		addParent(child,createNode(parent));
	}
	
	public void addParent(DAGNode<T> child, DAGNode<T> parent) {
		child.addParent(parent);
	}
	
	public void removeChild(DAGNode<T> parent, DAGNode<T> child) {
		parent.removeChild(child);
	}
	public void removeChild(DAGNode<T> parent, T child) {
		removeChild(parent,createNode(child));
	}
	
	public void removeParent(DAGNode<T> child, DAGNode<T> parent) {
		child.removeParent(parent);
	}
	
	public void removeParent(DAGNode<T> child, T parent) {
		removeParent(child,createNode(parent));
	}
	
}
