package com.cloud.staff.apifirst.controller.java.DesignPatterns.Bridge;

/**
 * 设计模式-桥接
 * @author 赵参谋
 * @date 2019-05-11
 * 将抽象与实现分离开来，使它们可以独立变化。
 */
public class BridgeMain {
	
	public static void main(String[] args) {
		ConcreteRemoteControl concrete=new ConcreteRemoteControl(new RCA());
		concrete.on();
		concrete.off();
	}
//	RAC ON
//	RAC OFF

}
