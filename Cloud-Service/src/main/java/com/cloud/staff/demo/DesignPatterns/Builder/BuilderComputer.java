package com.cloud.staff.demo.DesignPatterns.Builder;

public abstract class BuilderComputer {

	protected Computer computer;

	public Computer getComputer() {
		return computer;
	}

	public void setComputer() {
		computer=new Computer();
		System.out.println("生成电脑");
	}

	public abstract void setcomputername();
	public abstract void setcomputerscran();
	public abstract void setcomputercpu();
	public abstract void setcomputermemery();
}
