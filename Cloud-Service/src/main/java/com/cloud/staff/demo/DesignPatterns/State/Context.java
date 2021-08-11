package com.cloud.staff.demo.DesignPatterns.State;

public class Context {

	public final static CarryingState carryState=new CarryingState();

	public final static PuttingState puttingState=new PuttingState();

	public final static GoAwayingState goAwayingState=new GoAwayingState();

	public final static IgnitingState ignitingState=new IgnitingState();

	private LinkState linkState;

	public LinkState getLinkState() {
		return linkState;
	}

	public void setLinkeSate(LinkState linkState) {
		this.linkState=linkState;
		this.linkState.setcontext(this);
	}

	public void carry() {
		this.linkState.carry();
	}

	public void put() {
		this.linkState.put();
	}

	public void goaway() {
		this.linkState.goaway();
	}

	public void ignite() {
		this.linkState.ignite();
	}

}
