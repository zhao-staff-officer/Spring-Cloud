package com.cloud.staff.apifirst.controller.java.DesignPatterns.ChainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter{
	
	public List<Filter> filters=new ArrayList<>();
	
	//添加filter实例
	public FilterChain addFilter(Filter filter) {
		this.filters.add(filter);
		return this;
	}
    
	@Override
	public String doFilter(String msg) {
		for(Filter filter:filters) {
			msg=filter.doFilter(msg);
		}
		return msg;
	}
	
	

}
