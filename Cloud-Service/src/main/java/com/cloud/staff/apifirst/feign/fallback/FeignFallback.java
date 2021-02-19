package com.cloud.staff.apifirst.feign.fallback;

import org.springframework.stereotype.Component;

import com.cloud.staff.apifirst.feign.FeignTestClient;


@Component
public class FeignFallback implements FeignTestClient{

	@Override
	public String feigntest() {
		return "服务1调用出错";
	}

}
