package com.cloud.staff.apifirst.controller.java.Basic.Enum;

public class EnumTestMain {
	
	public static void main(String[] args) {
		
		System.out.println("测试："+EnumTest.Fri.getDay());
		
		for(EnumTest e:EnumTest.values()) {
			System.out.println(e.getDay()+","+e.getNum()+","+e.ordinal());
		}
		// enum 对象的常用方法介绍
		//1.compareTo(E o) 比较此枚举与指定对象的顺序。
		//2.getDeclaringClass() 返回与此枚举常量的枚举类型相对应的 Class 对象。
		//3.name() 返回此枚举常量的名称，在其枚举声明中对其进行声明。
		//4.ordinal() 返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）
		//5.toString() 返回枚举常量的名称，它包含在声明中
		//6.valueOf()  返回带指定名称的指定枚举类型的枚举常量。
	}

}
