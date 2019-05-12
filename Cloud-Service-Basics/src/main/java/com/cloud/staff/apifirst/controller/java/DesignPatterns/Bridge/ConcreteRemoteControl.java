package com.cloud.staff.apifirst.controller.java.DesignPatterns.Bridge;

public class ConcreteRemoteControl extends RemoteControl{

	public ConcreteRemoteControl(TV tv) {
		super(tv);
	}

	@Override
	public void on() {
		tv.on();
		
	}

	@Override
	public void off() {
		tv.off();
	}

}
