package com.cloud.staff.apifirst.controller.java.DesignPatterns.Command;

public class CommandOff implements Command{
	
	Ligth ligth;
	
	CommandOff(Ligth ligth){
		this.ligth=ligth;
	}
	

	@Override
	public void excute() {
		ligth.off();
		
	}

}
