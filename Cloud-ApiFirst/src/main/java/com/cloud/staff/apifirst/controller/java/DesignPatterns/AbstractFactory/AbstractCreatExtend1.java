package com.cloud.staff.apifirst.controller.java.DesignPatterns.AbstractFactory;

public class AbstractCreatExtend1 extends AbstractCreat{

	@Override
	ProductFactoryA createProductA() {
		return new OperactionA1();
	}

	@Override
	ProductFactoryB createProductB() {
		return new OperationB1();
	}

}
