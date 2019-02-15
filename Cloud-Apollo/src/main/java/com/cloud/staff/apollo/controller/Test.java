package com.cloud.staff.apollo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Test {
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/getinfo")
	@ResponseBody
	public String getinfos() {
		System.out.println(port);
		return port;
	}

}
