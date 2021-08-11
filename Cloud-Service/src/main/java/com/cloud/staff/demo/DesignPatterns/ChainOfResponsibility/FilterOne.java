package com.cloud.staff.demo.DesignPatterns.ChainOfResponsibility;

public class FilterOne implements Filter{

	@Override
	public String doFilter(String msg) {
		return msg.replace("尼玛", "**");
	}

}
