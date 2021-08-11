package com.cloud.staff.demo.DesignPatterns.Adapter;

public class DuckImpl implements Duck{

	private Dog dog;

	public DuckImpl(Dog dog) {
		this.dog=dog;
	}

	@Override
	public void dosomthing() {
		System.out.println("小鸭叫");
		dog.dosomthing();
	}

}
