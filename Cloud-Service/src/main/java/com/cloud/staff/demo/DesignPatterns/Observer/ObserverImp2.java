package com.cloud.staff.demo.DesignPatterns.Observer;

public class ObserverImp2 implements Observer{

	public ObserverImp2(Subject  weatherData) {
		weatherData.registObserver(this);
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		System.out.println(temp+","+humidity+","+humidity+",");

	}

}
