package com.cloud.staff.apifirst.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloud.staff.apifirst.feign.fallback.FeignFallback;

@FeignClient(name="apisecond",fallback=FeignFallback.class)
public interface FeignTestClient {
	
	@RequestMapping("/feign/test2")
	public String feigntest();

}
