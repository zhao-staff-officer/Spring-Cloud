package com.cloud.staff.apisecond.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloud.staff.apisecond.feign.fallback.FeignFallback;

@FeignClient(name="apifirts",fallback=FeignFallback.class)
public interface FeignTestClient {
	
	@RequestMapping("/feign/test2")
	public String feigntest();

}
