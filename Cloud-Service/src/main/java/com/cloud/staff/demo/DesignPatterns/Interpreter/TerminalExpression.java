package com.cloud.staff.demo.DesignPatterns.Interpreter;

import java.util.StringTokenizer;

public class TerminalExpression extends Expression{

	private String literal=null;

	public TerminalExpression (String str) {
		this.literal=str;
	}

	@Override
	public boolean interpret(String str) {
		StringTokenizer token=new StringTokenizer(str);
		while(token.hasMoreTokens()) {
			String nexttoken=token.nextToken();
			if(nexttoken.equals(token)) {
				return true;
			}
		}
		return false;
	}



}
