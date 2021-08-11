package com.cloud.staff.demo.DesignPatterns.Bridge;

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
