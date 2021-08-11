package com.cloud.staff.demo.DesignPatterns.Observer;

public class Observerimp1 implements Observer{

	//注入发布者
	public Observerimp1(Subject  weatherData){
		weatherData.registObserver(this);
	}


	@Override
	public void update(float temp, float humidity, float pressure) {
		System.out.println(temp+"，"+humidity+"，"+pressure);
	}

}
