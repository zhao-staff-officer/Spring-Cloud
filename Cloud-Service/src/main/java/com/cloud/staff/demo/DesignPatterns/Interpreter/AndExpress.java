package com.cloud.staff.demo.DesignPatterns.Interpreter;

public class AndExpress extends Expression{

	private Expression exp1 =null;
	private Expression exp2 =null;

	public AndExpress(Expression exp1,Expression exp2) {
		this.exp1=exp1;
		this.exp2=exp2;
	}

	@Override
	public boolean interpret(String str) {
		return exp1.interpret(str) && exp2.interpret(str);
	}

}
