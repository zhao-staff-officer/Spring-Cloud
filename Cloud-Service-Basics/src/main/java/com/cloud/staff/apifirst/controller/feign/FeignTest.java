package com.cloud.staff.apifirst.controller.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.staff.apifirst.feign.FeignTestClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * feign测试
 * @author zhaoHB
 *
 */
@Api(tags="feign测试")
@Controller
@RequestMapping("/feign")
public class FeignTest {
	
	@Autowired
	private FeignTestClient feignClient;
	
	
	@ApiOperation(value="服务1调用服务2")
	@RequestMapping(value="/test1")
	@ResponseBody
	public String feignTest1() {
		System.out.println("进来啦");
		return feignClient.feigntest();
	}
	
	@ApiOperation(value="服务2调用服务1")
	@RequestMapping("/test2")
	@ResponseBody
	public String feignTes2() {
		return "服务2调用服务1";
	}

}
