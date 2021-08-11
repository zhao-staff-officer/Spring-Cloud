package com.cloud.staff.demo.DesignPatterns.Interpreter;


/**
 * 解释器
 * 为语言创建解释器，通常由语言的语法和语法分析来定义。
 * @author zhaoHB
 * TerminalExpression：终结符表达式，每个终结符都需要一个 TerminalExpression。
 * Context：上下文，包含解释器之外的一些全局信息。
 */
public class InterpreterMain {
	public static void main(String[] args) {

		TerminalExpression trma=new TerminalExpression("a");
		TerminalExpression trmb=new TerminalExpression("b");
		TerminalExpression trmc=new TerminalExpression("c");
		TerminalExpression trmd=new TerminalExpression("d");

		Expression ordbc =new OrExpress(trmb, trmc);
		Expression ordabc =new AndExpress(trma, ordbc);
		Expression anddabc =new AndExpress(trmd, ordabc);

		System.out.println(anddabc.interpret("A B"));

	}

}
