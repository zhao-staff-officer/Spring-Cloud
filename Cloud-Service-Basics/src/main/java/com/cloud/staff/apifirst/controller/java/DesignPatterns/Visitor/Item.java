package com.cloud.staff.apifirst.controller.java.DesignPatterns.Visitor;

public class Item implements Element{
	
	  private String name;
	  
	
	Item(String name) {
        this.name = name;
    }

	String getName() {
        return name;
    }
    
	@Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
