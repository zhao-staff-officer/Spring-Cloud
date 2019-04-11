package com.cloud.staff.apifirst.controller.java.DesignPatterns.Command;

public class Invoker {
	private Command[] commandOn;
	private Command[] commandOff;
	private final int button=7;
	
	public Invoker(){
		this.commandOn=new Command[button];
		this.commandOff=new Command[button];
	}
	
	public void setCommandOn(Command command,int button) {
		this.commandOn[button]=command;
	}
	
	public void setCommandOff(Command command,int button) {
		this.commandOff[button]=command;
	}
	
	public void pushButtonOn(int button) {
		commandOn[button].excute();
	}
	
	public void pushButtonOff(int button) {
		commandOff[button].excute();
	}
	
	

}
