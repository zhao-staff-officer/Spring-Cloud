package com.cloud.staff.demo.DesignPatterns.Prototype;

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
