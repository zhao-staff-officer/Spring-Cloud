package com.cloud.staff.apifirst.controller.java.DesignPatterns.Bridge;

public class Sony extends TV{

	@Override
	public void on() {
		System.out.println("sony on");
		
	}

	@Override
	public void off() {
		System.out.println("sony off");
	}

}
