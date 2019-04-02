package com.cloud.staff.apifirst.controller.java.DesignPatterns.FactoryMethod;

public class SubOperation implements MethodFactory{

	@Override
	public void operation() {
		System.out.println("减法");
	}

}
