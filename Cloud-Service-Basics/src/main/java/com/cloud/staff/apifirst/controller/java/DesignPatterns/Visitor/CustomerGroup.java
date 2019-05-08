package com.cloud.staff.apifirst.controller.java.DesignPatterns.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 * @author zhaoHB
 *
 */
public class CustomerGroup {
	
	private List<Customer> 	customers=new ArrayList<>();
	
	void accept(Visitor visitor) {
		for(Customer customer:customers) {
			customer.accept(visitor);
		}
	}
	
	void addCustomer(Customer customer) {
		customers.add(customer);
	}

}
