package com.cloud.staff.demo.DesignPatterns.TemplateMethod;

public class Coffee extends CaffeineBeverage{


	@Override
	void brew() {
		System.out.println("Coffee.brew");

	}

	@Override
	void addCondiments() {
		 System.out.println("Coffee.addCondiments");

	}

}
