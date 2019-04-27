package com.cloud.staff.apifirst.controller.java.DesignPatterns.State;

public class Context {
	
	public final static CarryingState carryState=new CarryingState();
	
	private LinkState linkState;
	
	public LinkState setLinkState() {
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
