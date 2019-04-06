package com.cloud.staff.apifirst.controller.java.DesignPatterns.Prototype;

public class CreatePrototype extends Prototype{
	
	private String file;
	
	public CreatePrototype(String file) {
		this.file=file;
	}

	@Override
	Prototype myclone() {
		return new CreatePrototype(file);
	}

}
