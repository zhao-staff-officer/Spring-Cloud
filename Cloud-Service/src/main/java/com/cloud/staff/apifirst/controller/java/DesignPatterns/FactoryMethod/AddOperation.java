package com.cloud.staff.apifirst.controller.java.DesignPatterns.FactoryMethod;


public class AddOperation implements MethodFactory{

	@Override
	public void operation() {
		System.out.println("加法");
	}

}
