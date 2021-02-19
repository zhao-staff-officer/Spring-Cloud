package com.cloud.staff.apifirst.controller.java.DesignPatterns.Mediator;

public class ColleageType2 extends Colleague{

	@Override
	public void doEvent(Mediator meditor) {
		meditor.doEvent("type2");
	}
	
	public void doExamp() {
		System.out.println("type2");
	}

}
