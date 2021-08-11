package com.cloud.staff.demo.DesignPatterns.FactoryMethod;

public class OlgorSub implements OlgorFactory{

	@Override
	public void olgor() {
		new SubOperation().operation();

	}

}
