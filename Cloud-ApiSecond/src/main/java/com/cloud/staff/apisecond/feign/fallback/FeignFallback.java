package com.cloud.staff.apisecond.feign.fallback;

import org.springframework.stereotype.Component;

import com.cloud.staff.apisecond.feign.FeignTestClient;

@Component
public class FeignFallback implements FeignTestClient{

	@Override
	public String feigntest() {
		return "服务调用出错";
	}

}
