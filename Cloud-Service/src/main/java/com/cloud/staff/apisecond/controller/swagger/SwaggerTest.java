package com.cloud.staff.apisecond.controller.swagger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * swagger测试
 * @author zhaoHB
 *
 */
@RestController("/swagger")
@Api(tags="swagger测试2")
public class SwaggerTest {
	
	@RequestMapping("/test")
	public String swagger() {
		return "swagger测试成功2";
	}

}
