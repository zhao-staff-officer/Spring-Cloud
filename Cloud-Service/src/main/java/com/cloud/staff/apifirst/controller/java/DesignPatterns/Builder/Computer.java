package com.cloud.staff.apifirst.controller.java.DesignPatterns.Builder;

public class Computer {
	
	//名字
	private String name;
	//显示屏
	private String scran;
    //cpu
	private String cpu;
	//内存
	private String memery;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScran() {
		return scran;
	}
	public void setScran(String scran) {
		this.scran = scran;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getMemery() {
		return memery;
	}
	public void setMemery(String memery) {
		this.memery = memery;
	}
}
