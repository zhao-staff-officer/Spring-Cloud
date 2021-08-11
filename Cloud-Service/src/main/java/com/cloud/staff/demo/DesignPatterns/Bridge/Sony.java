package com.cloud.staff.demo.DesignPatterns.Bridge;

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
