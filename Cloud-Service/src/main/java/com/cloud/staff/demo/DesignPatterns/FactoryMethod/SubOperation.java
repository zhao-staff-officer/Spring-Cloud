package com.cloud.staff.demo.DesignPatterns.FactoryMethod;

public class SubOperation implements MethodFactory{

	@Override
	public void operation() {
		System.out.println("减法");
	}

}
