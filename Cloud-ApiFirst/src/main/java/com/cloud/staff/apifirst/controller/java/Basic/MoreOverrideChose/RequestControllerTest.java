package com.cloud.staff.apifirst.controller.java.Basic.MoreOverrideChose;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Reource name 按照name加载ben 
 *          type 按照classtype加载ben
 *          默认name加载
 * @Autowired按byType自动注入
 * 
 * @author zhaoHB
 *
 */
@RestController
@RequestMapping("/moreoverride")
public class RequestControllerTest {
	
	@Resource(name="InterfaceMoreOverride")
	InterfaceMoreOverride interfaceMoreOverride;
	
	@RequestMapping("/test")
	public String moreOverride() {
		return interfaceMoreOverride.test1();
	}

}
