package com.cloud.staff.apifirst.controller.java.DesignPatterns.Strategy;

public class Duck {
	
	private QuackBehavior quackBehavior;
	
	public void performQuack() {
		quackBehavior.quack();
	}
	
	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior=quackBehavior;
	}

}
