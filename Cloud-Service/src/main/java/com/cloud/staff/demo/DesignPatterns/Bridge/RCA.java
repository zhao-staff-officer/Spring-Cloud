package com.cloud.staff.demo.DesignPatterns.Bridge;

public class RCA extends TV{

	@Override
	public void on() {
		System.out.println("RAC ON");

	}

	@Override
	public void off() {
		System.out.println("RAC OFF");

	}

}
