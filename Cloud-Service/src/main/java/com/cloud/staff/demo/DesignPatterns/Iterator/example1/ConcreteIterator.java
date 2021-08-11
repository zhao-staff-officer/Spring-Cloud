package com.cloud.staff.demo.DesignPatterns.Iterator.example1;

public class ConcreteIterator<Item> implements Iterator{

	private Item[] iteam;
	private int position;

	public ConcreteIterator(Item[] iteam) {
		this.iteam=iteam;
	}


	@Override
	public Object next() {
		return iteam[position++];
	}

	@Override
	public boolean hasNext() {
		if(position<iteam.length) {
			return true;
		}
		return false;
	}

}
