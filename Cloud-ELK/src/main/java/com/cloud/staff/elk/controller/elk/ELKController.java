package com.cloud.staff.elk.controller.elk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elk")
public class ELKController {
	
	private static final Logger log=LoggerFactory.getLogger(ELKController.class);
	
	@GetMapping("/test")
	public String elktest() {
		System.out.println("elk-sysout");
		log.info("elk-log");
		return "elk测试";
	}
	
	@GetMapping("/error")
	public void elkError() throws Exception {
		throw new Exception();
	}

}
