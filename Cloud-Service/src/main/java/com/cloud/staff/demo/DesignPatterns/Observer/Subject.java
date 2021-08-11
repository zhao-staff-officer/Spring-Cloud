package com.cloud.staff.demo.DesignPatterns.Observer;

public interface Subject {

	void registObserver(Observer o);

	void removeObserver(Observer o);

	void notifyObserver();

}
