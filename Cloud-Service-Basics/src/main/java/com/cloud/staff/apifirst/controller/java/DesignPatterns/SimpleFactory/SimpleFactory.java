package com.cloud.staff.apifirst.controller.java.DesignPatterns.SimpleFactory;

public class SimpleFactory {
	
	public static Product creatProduct(int type) {
		if(type==1) {
			return new ConcreteProduct();
		}else {
			return new ConcreteProduct2();
		}
	}
	
	public static void main(String[] args) {
		SimpleFactory simpleFactory=new SimpleFactory();
		Product operInstance=SimpleFactory.creatProduct(2);
		operInstance.operation();
	}

}
