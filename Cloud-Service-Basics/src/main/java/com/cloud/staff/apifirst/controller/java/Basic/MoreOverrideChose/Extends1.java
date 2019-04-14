package com.cloud.staff.apifirst.controller.java.Basic.MoreOverrideChose;

import org.springframework.stereotype.Service;

@Service("extends1")
public class Extends1 extends InterfaceMoreOverrideImpl{
	@Override
	public String test1() {
		return "2";
	}
}
