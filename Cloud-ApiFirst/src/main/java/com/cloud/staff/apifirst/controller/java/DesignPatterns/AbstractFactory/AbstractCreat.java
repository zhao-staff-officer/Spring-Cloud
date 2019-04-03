package com.cloud.staff.apifirst.controller.java.DesignPatterns.AbstractFactory;

/***
 * 抽象工厂模式创建的是对象家族，也就是很多对象而不是一个对象，并且这些对象是相关的，也就是说必须一起创建出来。
 * @author zhaoHB
 *
 */
public abstract class AbstractCreat {
	
	abstract ProductFactoryA createProductA();
    abstract ProductFactoryB createProductB();

}
