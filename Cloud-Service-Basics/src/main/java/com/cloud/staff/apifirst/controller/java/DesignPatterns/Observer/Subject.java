package com.cloud.staff.apifirst.controller.java.DesignPatterns.Observer;

public interface Subject {
	
	void registObserver(Observer o);
	
	void removeObserver(Observer o);
	
	void notifyObserver();

}
