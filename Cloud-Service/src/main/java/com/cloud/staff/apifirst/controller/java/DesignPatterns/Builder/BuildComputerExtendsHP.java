package com.cloud.staff.apifirst.controller.java.DesignPatterns.Builder;

public class BuildComputerExtendsHP extends BuilderComputer{

	@Override
	public void setcomputername() {
		// TODO Auto-generated method stub
		super.computer.setCpu("HP");
		System.out.println("HP");
	}

	@Override
	public void setcomputerscran() {
		// TODO Auto-generated method stub
		super.computer.setScran("蓝屏");
		System.out.println("蓝屏");
	}

	@Override
	public void setcomputercpu() {
		// TODO Auto-generated method stub
		super.computer.setCpu("1");
		System.out.println("1");
	}

	@Override
	public void setcomputermemery() {
		// TODO Auto-generated method stub
		super.computer.setMemery("1M");
		System.out.println("1M");
	}

}
