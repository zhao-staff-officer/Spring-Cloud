package com.cloud.staff.demo.DesignPatterns.Strategy;

public class Quack implements QuackBehavior{

	@Override
	public void quack() {
		 System.out.println("quack!");
	}

}
