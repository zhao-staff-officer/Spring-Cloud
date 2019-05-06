package com.cloud.staff.apifirst.controller.java.DesignPatterns.TemplateMethod;

public class Tea extends CaffeineBeverage{

	@Override
	void brew() {
		 System.out.println("Tea.brew");
		
	}

	@Override
	void addCondiments() {
		System.out.println("Tea.addCondiments");
		
	}

}
