package com.cloud.staff.apifirst.controller.java.DesignPatterns.Mediator;

public class ColleageType1 extends Colleague{

	@Override
	public void doEvent(Mediator meditor) {
		meditor.doEvent("type1");
	}
    
	public void doExamp() {
		System.out.println("type1");
	}
}
