package com.cloud.staff.apifirst.controller.java.DesignPatterns.Command;

public class CommandOn implements Command{
	
	Ligth ligth;
	CommandOn(Ligth ligth){
		this.ligth=ligth;
	}

	@Override
	public void excute() {
		ligth.on();
		
	}

}
