package com.cloud.staff.demo.DesignPatterns.FactoryMethod;

public class OlgorAdd implements OlgorFactory{

	@Override
	public void olgor() {
		 new AddOperation().operation();
	}

}
