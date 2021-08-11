package com.cloud.staff.demo.DesignPatterns.Visitor;

public interface Visitor {

	void visit(Customer customer);

	void visit(Order order);

	void visit(Item iteam);

}
