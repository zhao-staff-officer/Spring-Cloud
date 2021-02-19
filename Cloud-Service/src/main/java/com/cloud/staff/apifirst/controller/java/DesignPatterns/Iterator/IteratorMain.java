package com.cloud.staff.apifirst.controller.java.DesignPatterns.Iterator;

import com.cloud.staff.apifirst.controller.java.DesignPatterns.Iterator.example1.ConcreteAggregate;
import com.cloud.staff.apifirst.controller.java.DesignPatterns.Iterator.example1.Iterator;

/**
 * 迭代器
 * 提供一种顺序访问聚合对象元素的方法，并且不暴露聚合对象的内部表示。
 * @author zhaoHB
 *
 */
public class IteratorMain {
	public static void main(String[] args) {
		ConcreteAggregate agr=new ConcreteAggregate();
		Iterator<Integer> itr=agr.createIterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

}
