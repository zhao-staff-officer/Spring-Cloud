package com.cloud.staff.apifirst.controller.java.DesignPatterns.Visitor;

public interface Visitor {
	
	void visit(Customer customer);
	
	void visit(Order order);
	
	void visit(Item iteam);

}
