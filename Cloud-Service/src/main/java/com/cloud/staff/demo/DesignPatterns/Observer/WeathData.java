package com.cloud.staff.demo.DesignPatterns.Observer;

import java.util.ArrayList;
import java.util.List;

public class WeathData implements Subject{

	private List<Observer> observers;

	private float temp;

	private float humidity;

	private float pressure;

	public WeathData() {
		this.observers=new ArrayList<>();
	}

	public void setMeasurements(float temp,float humidity,float pressure) {
		this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.notifyObserver();
	}


	@Override
	public void registObserver(Observer o) {
		observers.add(o);

	}

	@Override
	public void removeObserver(Observer o) {
		int i =observers.indexOf(o);
		if(i>0) {
			observers.remove(i);
		}

	}

	@Override
	public void notifyObserver() {
		for(Observer o:observers) {
			o.update(temp, humidity, pressure);
		}

	}

}
