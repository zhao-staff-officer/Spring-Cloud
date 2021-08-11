package com.cloud.staff.demo.DesignPatterns.Strategy;

public class Duck {

	private QuackBehavior quackBehavior;

	public void performQuack() {
		quackBehavior.quack();
	}

	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior=quackBehavior;
	}

}
