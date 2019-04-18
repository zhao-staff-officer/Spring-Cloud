package com.cloud.staff.apifirst.controller.java.DesignPatterns.Iterator.example1;

public class ConcreteAggregate implements Aggregate{
	
	private Integer[] iteam;
	
	public ConcreteAggregate() {
		iteam = new Integer[10];
		for(int i=0,j=10;i<j;i++) {
			iteam[i]=i;
		}
	}
	@Override
	public Iterator createIterator() {
		return new ConcreteIterator<Integer>(iteam);
	}

}
