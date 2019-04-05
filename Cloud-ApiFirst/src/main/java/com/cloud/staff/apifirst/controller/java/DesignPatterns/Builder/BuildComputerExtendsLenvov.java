package com.cloud.staff.apifirst.controller.java.DesignPatterns.Builder;

public class BuildComputerExtendsLenvov extends BuilderComputer{

	@Override
	public void setcomputername() {
		super.computer.setName("联想");
		System.out.println("联想");
		
	}

	@Override
	public void setcomputerscran() {
		super.computer.setScran("黑屏");
		System.out.println("黑屏");
		
	}

	@Override
	public void setcomputercpu() {
		super.computer.setCpu("2HZ");
		System.out.println("2HZ");
		
	}

	@Override
	public void setcomputermemery() {
		// TODO Auto-generated method stub
		super.computer.setMemery("512");
		System.out.println("512");
	}

}
