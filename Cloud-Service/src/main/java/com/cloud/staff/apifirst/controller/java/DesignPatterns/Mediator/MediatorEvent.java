package com.cloud.staff.apifirst.controller.java.DesignPatterns.Mediator;

public class MediatorEvent extends Mediator{
	
	private ColleageType1 type1;
	
	private ColleageType2 type2;
	
	public MediatorEvent(ColleageType1 type1,ColleageType2 type2) {
		this.type1=type1;
		this.type2=type2;
	}

	@Override
	void doEvent(String type) {
		switch (type) {
		case "type1":
			type1.doExamp();
			break;
		case "type2":
		   type2.doExamp();
		   break;
		default:
			break;
		}
		
	}

}
