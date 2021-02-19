package com.cloud.staff.apifirst.controller.java.DesignPatterns.Strategy;

public class Squeak implements QuackBehavior{

	@Override
	public void quack() {
		 System.out.println("squeak!");
	}

}
