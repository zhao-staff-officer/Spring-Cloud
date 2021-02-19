package com.cloud.staff.apifirst.controller.java.DesignPatterns.State;

public class PuttingState extends LinkState{

	@Override
	public void carry() {
		System.out.println("小Y:还在敌方区，无法再扛炸药包");
	}

	@Override
	public void put() {
		System.out.println("小Y:好险，成功把放置炸药包");
		
	}

	@Override
	public void goaway() {
		 super.context.setLinkeSate(Context.goAwayingState);
		 super.context.getLinkState().goaway();
		
	}

	@Override
	public void ignite() {
		System.out.println("小Y:还没逃离敌方区，无法引爆炸药");
		
	}

}
