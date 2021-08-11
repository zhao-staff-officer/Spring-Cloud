package com.cloud.staff.demo.DesignPatterns.ChainOfResponsibility;

public class FilterTwo implements Filter{

	@Override
	public String doFilter(String msg) {
		return msg.replace("你爸", "**");
	}

}
