package com.cloud.staff.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
@RefreshScope
public class Test {
	
	@Value("${logging.path}")
	private String path;
      
	@RequestMapping("/getconfig")
	@ResponseBody
	public String test() {
		System.out.println(path);
		return path;
	}
	
}
