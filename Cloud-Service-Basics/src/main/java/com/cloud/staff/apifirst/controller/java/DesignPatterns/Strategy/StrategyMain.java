package com.cloud.staff.apifirst.controller.java.DesignPatterns.Strategy;

/**
 * 设计模式-策略
 * @author zhaoHB
 *Intent
 *定义一系列算法，封装每个算法，并使它们可以互换。
 *策略模式可以让算法独立于使用它的客户端。
 *Class Diagram
 *Strategy 接口定义了一个算法族，它们都实现了 behavior() 方法。
 *Context 是使用到该算法族的类，其中的 doSomething() 方法会调用 behavior()，setStrategy(Strategy) 方法可以动态地改变 strategy 
 *对象，也就是说能动态地改变 Context 所使用的算法。
 */
public class StrategyMain {
	
	public static void main(String[] args) {
		Duck duck =new Duck();
		duck.setQuackBehavior(new Squeak());
        duck.performQuack();
        duck.setQuackBehavior(new Quack());
        duck.performQuack();
	}
//	squeak!
//	quack!

}
