package com.cloud.staff.apifirst.controller.java.DesignPatterns.Command;

/**
 * 命令
 * 使用命令来参数化其它对象
 * 将命令放入队列中进行排队
 * 将命令的操作记录到日志中
 * 支持可撤销的操作
 * @author zhaoHB
 * Command：命令
 * Receiver：命令接收者，也就是命令真正的执行者
 * Invoker：通过它来调用命令
 * Client：可以设置命令与命令的接收者

 *
 */
public class IntentMain {
	public static void main(String[] args) {
		Invoker invoker=new Invoker();
		
		Ligth ligth=new Ligth();
		
		Command commandOn=new CommandOn(ligth);
		Command commandoff=new CommandOff(ligth);
		
		invoker.setCommandOn(commandOn, 1);
		invoker.setCommandOff(commandoff, 2);
		
		invoker.pushButtonOn(1);
		invoker.pushButtonOff(2);
//		打开开关
//		关闭
		
	}

}
