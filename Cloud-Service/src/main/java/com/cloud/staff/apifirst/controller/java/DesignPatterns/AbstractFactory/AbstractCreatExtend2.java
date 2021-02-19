package com.cloud.staff.apifirst.controller.java.DesignPatterns.AbstractFactory;

public class AbstractCreatExtend2 extends AbstractCreat{

	@Override
	ProductFactoryA createProductA() {
		return new OperationA2();
	}

	@Override
	ProductFactoryB createProductB() {
		return new OperationB2();
	}

}
