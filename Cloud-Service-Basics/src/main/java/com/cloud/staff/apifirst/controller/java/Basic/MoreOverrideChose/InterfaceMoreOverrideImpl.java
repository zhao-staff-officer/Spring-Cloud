package com.cloud.staff.apifirst.controller.java.Basic.MoreOverrideChose;

import org.springframework.stereotype.Service;

@Service("InterfaceMoreOverride")
public class InterfaceMoreOverrideImpl implements InterfaceMoreOverride{

	@Override
	public String test1() {
		System.out.println("test1");
		return "1";
	}

	@Override
	public String sayHello() {
		System.out.println("hello");
		return null;
	}

}
