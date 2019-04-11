package com.cloud.staff.apifirst.controller.java.DesignPatterns.ChainOfResponsibility;

public class FilterOne implements Filter{

	@Override
	public String doFilter(String msg) {
		return msg.replace("尼玛", "**");
	}

}
