package com.cloud.staff.apifirst.controller.java.DesignPatterns.Observer;

/**
 * 观察者
 * 定义对象之间的一对多依赖，当一个对象状态改变时，它的所有依赖都会收到通知并且自动更新状态
 * 主题（Subject）是被观察的对象，而其所有依赖者（Observer）称为观察者
 * @author zhaoHB
 *主题（Subject）具有注册和移除观察者、并通知所有观察者的功能，主题是通过维护一张观察者列表来实现这些操作的。
 *观察者（Observer）的注册功能需要调用主题的 registerObserver() 方法。
 */
public class ObserverMain {
    
	public static void main(String[] args) {
		 WeathData weatherData = new WeathData();
		 Observerimp1 example1=new Observerimp1(weatherData);
		 ObserverImp2 example2=new ObserverImp2(weatherData);
		 
		 weatherData.setMeasurements(1, 1, 1);
		 
		 weatherData.setMeasurements(2, 2, 2);
		 
//		 1.0，1.0，1.0
//		 1.0,1.0,1.0,
//		 2.0，2.0，2.0
//		 2.0,2.0,2.0,
	}
	
}
