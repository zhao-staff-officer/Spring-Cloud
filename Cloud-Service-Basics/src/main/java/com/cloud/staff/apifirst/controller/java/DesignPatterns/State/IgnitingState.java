package com.cloud.staff.apifirst.controller.java.DesignPatterns.State;

public class IgnitingState extends LinkState{

	@Override
	public void carry() {
		System.out.println("炮楼没彻底倒下，小Y继续扛炸药");
	    super.context.setLinkeSate(Context.carryState);
	    super.context.getLinkState().carry();
		
	}

	@Override
	public void put() {
		 System.out.println("小Y:炸药已引爆，无须进行炸药包放置");
	}

	@Override
	public void goaway() {
		System.out.println("小Y:炸药已引爆，早已撤离");
		
	}

	@Override
	public void ignite() {
		System.out.println("小Y:炸楼完成，准备加薪晋职");
	}

}
