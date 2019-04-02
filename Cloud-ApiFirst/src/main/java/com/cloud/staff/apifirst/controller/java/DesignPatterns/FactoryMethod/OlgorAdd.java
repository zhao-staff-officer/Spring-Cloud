package com.cloud.staff.apifirst.controller.java.DesignPatterns.FactoryMethod;

public class OlgorAdd implements OlgorFactory{

	@Override
	public void olgor() {
		 new AddOperation().operation();
	}

}
