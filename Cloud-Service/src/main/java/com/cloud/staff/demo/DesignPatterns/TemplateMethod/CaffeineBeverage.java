package com.cloud.staff.demo.DesignPatterns.TemplateMethod;

public abstract class CaffeineBeverage {

	final void CaffeineBeverage () {
		 boilWater();
	     brew();
	     pourInCup();
	     addCondiments();
	}

	abstract void brew();

    abstract void addCondiments();

    void boilWater() {
        System.out.println("boilWater");
    }

    void pourInCup() {
        System.out.println("pourInCup");
    }

}
