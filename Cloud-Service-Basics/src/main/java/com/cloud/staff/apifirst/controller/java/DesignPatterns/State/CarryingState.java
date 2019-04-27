package com.cloud.staff.apifirst.controller.java.DesignPatterns.State;

public class CarryingState extends LinkState{

	@Override
	public void carry() {
		System.out.println("小Y:报告排长，我已跑到炮楼底下");
		
	}

	@Override
	public void put() {
//		super.context.setLinkeSate(linkState);
		
	}

	@Override
	public void goaway() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignite() {
		// TODO Auto-generated method stub
		
	}

}
