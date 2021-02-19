package com.cloud.staff.apifirst.controller.java.DesignPatterns.Strategy;

public class Quack implements QuackBehavior{

	@Override
	public void quack() {
		 System.out.println("quack!");
	}

}
