package org.processmining.database.utils;

public class Pair<T1,T2> {

	private T1 a = null;
	private T2 b = null;
	
	public Pair(T1 a, T2 b) {
		this.a = a;
		this.b = b;
	}
	
	public void setA(T1 a) {
		this.a = a;
	}
	
	public void setB(T2 b) {
		this.b = b;
	}
	
	public T1 getA() {
		return this.a;
	}
	
	public T2 getB() {
		return this.b;
	}
	
	@Override
	public String toString() {
		String aStr = "";
		String bStr = "";
		
		if (a != null) {
			aStr = a.toString();
		}
		
		if (b != null) {
			bStr = b.toString();
		}
		
		return aStr+"#"+bStr;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Pair) {
			Pair pObj = (Pair) obj;
			if (pObj.getA().equals(getA()) && (pObj.getB().equals(getB()))) {
				return true;
			}
		}
		
		return false;
	}
}
