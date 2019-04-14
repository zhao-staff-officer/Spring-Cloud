package com.cloud.staff.apifirst.controller.java.DesignPatterns.Builder;

/**
 * 构造器
 * 封装一个对象的构造过程，并允许按步骤构造。
 * 优点
 * 将一个对象分解为各个组件
 * 将对象组件的构造封装起来
 * 可以控制整个对象的生成过程
 * @author zhaoHB
 * 缺点
 *对不同类型的对象需要实现不同的具体构造器的类，这可能回答大大增加类的数量
 */
public class BuidTest {
	public static void main(String[] args) {
		BuilderComputer hp=new BuildComputerExtendsLenvov();
		DirctComputer dirct=new DirctComputer();
		dirct.setComputer(hp);
		dirct.setAttribute();
	}

}
