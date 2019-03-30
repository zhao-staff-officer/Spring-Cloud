package com.cloud.staff.sentinel.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;


@RestController
public class SentinelDemo2 {
	
	@GetMapping("/sentinel/demo2")
	@SentinelResource(value="sentinel")
	String demo2() {
		return "sentinel";
	}

}
