package com.cloud.staff.apifirst.controller.java.DesignPatterns.Bridge;

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
