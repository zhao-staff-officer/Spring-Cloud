package com.cloud.staff.apifirst.controller.java.DesignPatterns.FactoryMethod;

public class OlgorSub implements OlgorFactory{

	@Override
	public void olgor() {
		new SubOperation().operation();
		
	}

}
