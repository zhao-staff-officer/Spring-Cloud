package com.cloud.staff.apisecond.controller.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.staff.apisecond.feign.FeignTestClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * feign测试
 * @author zhaoHB
 *
 */
@Controller
@RequestMapping("/feign")
@Api(value="feign测试")
public class FeignTest {
	
	@Autowired
	private FeignTestClient feignClient;
	
	/**
	 * feign测试调用apisecond
	 * @return
	 */
	@RequestMapping(value="/tes1",method=RequestMethod.POST)
	@ApiOperation(value="服务2调用服务1")
	public String feignTest1() {
		return feignClient.feigntest();
	}
	
	@RequestMapping("/test2")
	@ResponseBody
	@ApiOperation(value="服务1调用服务2")
	public String feignTest2() {
		return "服务1调用服务2返回信息";
	}

}
