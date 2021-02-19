package com.cloud.staff.apifirst.controller.java.DesignPatterns.State;

public abstract class LinkState {
	
	protected Context context;
	
	public void setcontext(Context context) {
		this.context=context;
	}
	
	public abstract void carry();
	
	public abstract void put();
	
	public abstract void goaway();
	
	public abstract void ignite();

}
