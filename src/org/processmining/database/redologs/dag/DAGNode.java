package org.processmining.database.redologs.dag;

import java.util.HashSet;

public class DAGNode<T> {
	
	private HashSet<DAGNode<T>> parents = new HashSet<>();
	private HashSet<DAGNode<T>> children = new HashSet<>();
	private T value;
	
	protected DAGNode(T v) {
		this.value = v;
	}
	
	protected void addChild(DAGNode<T> c) {
		this.children.add(c);
		c.parents.add(this);
	}
	
	protected void addParent(DAGNode<T> p) {
		this.parents.add(p);
		p.children.add(this);
	}
	
	protected void removeChild(DAGNode<T> c) {
		this.children.remove(c);
		c.parents.remove(this);
	}
	
	protected void removeParent(DAGNode<T> p) {
		this.parents.remove(p);
		p.children.remove(this);
	}
	
	public HashSet<DAGNode<T>> getParents() {
		return parents;
	}
	
	public HashSet<DAGNode<T>> getChildren() {
		return children;
	}
	
	public T getValue() {
		return value;
	}
}
