package com.cloud.staff.demo.DesignPatterns.FactoryMethod;


public class AddOperation implements MethodFactory{

	@Override
	public void operation() {
		System.out.println("加法");
	}

}
