package com.cloud.staff.apifirst.controller.java.DesignPatterns.Adapter;

/**
 * 设计模式-适配器
 * @author zhaoHB
 * 把一个类接口转换成另一个用户需要的接口。
 */
public class AdapterMain {
	
	public static void main(String[] args) {
		DogImpl dog=new DogImpl();
		DuckImpl duck=new DuckImpl(dog);
		duck.dosomthing();
	}
//	小鸭叫
//	小狗叫

}
