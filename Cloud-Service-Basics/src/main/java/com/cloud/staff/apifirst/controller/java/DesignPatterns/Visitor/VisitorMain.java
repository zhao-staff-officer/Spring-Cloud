package com.cloud.staff.apifirst.controller.java.DesignPatterns.Visitor;

/**
 * 设计模式-访问者
 * @author zhaoHB
 * 为一个对象结构（比如组合结构）增加新能力。
 *Visitor：访问者，为每一个 ConcreteElement 声明一个 visit 操作
 *ConcreteVisitor：具体访问者，存储遍历过程中的累计结果
 *ObjectStructure：对象结构，可以是组合结构，或者是一个集合。
 */
public class VisitorMain {
	
	public static void main(String[] args) {
        Customer customer1 = new Customer("customer1");
        customer1.addOrder(new Order("order1", "item1"));
        customer1.addOrder(new Order("order2", "item1"));
        customer1.addOrder(new Order("order3", "item1"));

        Order order = new Order("order_a");
        order.addItem(new Item("item_a1"));
        order.addItem(new Item("item_a2"));
        order.addItem(new Item("item_a3"));
        Customer customer2 = new Customer("customer2");
        customer2.addOrder(order);

        CustomerGroup customers = new CustomerGroup();
        customers.addCustomer(customer1);
        customers.addCustomer(customer2);

        GeneralReport visitor = new GeneralReport();
        customers.accept(visitor);
        visitor.displayResults();
    }
	
//	customer1
//	order1
//	item1
//	order2
//	item1
//	order3
//	item1
//	customer2
//	order_a
//	item_a1
//	item_a2
//	item_a3
//	Number of customers: 2
//	Number of orders:    4
//	Number of items:     6

}
