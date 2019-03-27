package com.cloud.staff.sentinel.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;

public class SentinelDemo1 {
	
//	规则配置，都是存在内存中的。即如果应用重启，这个规则就会失效。因此我们提供了开放的接口，您可以通过实现 DataSource 接口的方式，来自定义规则的存储数据源。通常我们的建议有：
//	整合动态配置系统，如 ZooKeeper、Nacos 等，动态地实时刷新配置规则
//	结合 RDBMS、NoSQL、VCS 等来实现该规则
//	配合 Sentinel Dashboard 使用
	
	public static void main(String[] args) {
		initRule();
		//定义资源
		Entry entry=null;
		try {
			entry=SphU.entry("sentinel");
			System.out.println("sentinel");
		}catch(BlockException e1){
			System.out.println("block");
		}finally {
			if (entry != null) {
			       entry.exit();
			   }
		}
		
	}
	
	public static void initRule() {
	  //流浪控制
		List<FlowRule> flowRules=new ArrayList<>();
		FlowRule rule_flow=new FlowRule();
		//申明定义资源
		rule_flow.setRefResource("sentinel");
		//限流阈值类型，QPS 或线程数模式
		//The threshold type of flow control (0: thread count, 1: QPS).
		rule_flow.setGrade(RuleConstant.FLOW_GRADE_QPS);
		//流控针对的调用来源
		//rule.setLimitApp("");
		//直接拒绝 / 排队等待 / 慢启动模式
		//0. default(reject directly), 1. warm up, 2. rate limiter, 3. warm up + rate limiter
		//rule.setControlBehavior(0);
		//限流阈值
		rule_flow.setCount(2);
		flowRules.add(rule_flow);
		FlowRuleManager.loadRules(flowRules);
     //熔断降级规则
		List<DegradeRule> degraRules = new ArrayList<>();
		DegradeRule rule_degra = new DegradeRule();
		//申明熔断资源
		rule_degra.setResource("sentinel");
		//申明阀值
		rule_degra.setCount(1);
		rule_degra.setGrade(RuleConstant.DEGRADE_GRADE_RT);
		//降级的时间，单位为 s
		rule_degra.setTimeWindow(10);
		degraRules.add(rule_degra);
		DegradeRuleManager.loadRules(degraRules);
	//系统保护，此数据最后不要设置，是基于服务器整个控制
		List<SystemRule> sysRules = new ArrayList<>();
		SystemRule rule_sys = new SystemRule();
		//当系统 load1 超过阈值，且系统当前的并发线程数超过系统容量时才会触发系统保护。系统容量由系统的 maxQps * minRt 计算得出。设定参考值一般是 CPU cores * 2.5。
		rule_sys.setHighestSystemLoad(100);
		//所有入口流量的平均响应时间
		rule_sys.setAvgRt(1000);
		//入口流量的最大并发数
		rule_sys.setMaxThread(1000);
		//所有入口资源的 QPS
		rule_sys.setQps(-1);
		sysRules.add(rule_sys);
		SystemRuleManager.loadRules(sysRules);
	//授权规则，黑白名单
		AuthorityRule authorRule = new AuthorityRule();
		authorRule.setResource("test");
		authorRule.setStrategy(RuleConstant.AUTHORITY_WHITE);
		authorRule.setLimitApp("appA,appB");
		AuthorityRuleManager.loadRules(Collections.singletonList(authorRule));
	//热点参数限流
		ParamFlowRule rule = new ParamFlowRule("sentinel")
			    .setParamIdx(0)
			    .setCount(5);
			// 针对 int 类型的参数 PARAM_B，单独设置限流 QPS 阈值为 10，而不是全局的阈值 5.
			ParamFlowItem item = new ParamFlowItem().setObject(String.valueOf("param"))
			    .setClassType(int.class.getName())
			    .setCount(10);
			rule.setParamFlowItemList(Collections.singletonList(item));
			ParamFlowRuleManager.loadRules(Collections.singletonList(rule));
		
	}

}
