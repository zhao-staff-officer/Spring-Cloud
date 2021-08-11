package com.cloud.staff.demo.DesignPatterns.Strategy;

public class Squeak implements QuackBehavior{

	@Override
	public void quack() {
		 System.out.println("squeak!");
	}

}
