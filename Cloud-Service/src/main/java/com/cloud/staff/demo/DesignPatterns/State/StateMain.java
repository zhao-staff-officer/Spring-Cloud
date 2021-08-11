package com.cloud.staff.demo.DesignPatterns.State;

/**
 * 允许对象在内部状态改变时改变它的行为，
 * 对象看起来好像修改了它所属的类。
 * @author zhaoHB
 *State抽象状态角色
 *接口或抽象类，负责对象状态定义，并且封装环境角色以实现状态切换。
 *ConcreteState具体状态角色
 *具体状态主要有两个职责：一是处理本状态下的事情，二是从本状态如何过渡到其他状态。
 *Context环境角色
 *定义客户端需要的接口，并且负责具体状态的切换。
*/
public class StateMain {
	public static void main(String[] args) {
		Context context = new Context();
		context.setLinkeSate(Context.carryState);
		context.carry();
		context.ignite();
		context.put();
		context.goaway();
		context.ignite();
	}

//	小Y:报告排长，我已跑到炮楼底下
//	小Y:还没放置炸药成功，引爆失败
//	小Y:呼~~，终于捡回条小命
//	小Y:炸楼完成，准备加薪晋职

}
