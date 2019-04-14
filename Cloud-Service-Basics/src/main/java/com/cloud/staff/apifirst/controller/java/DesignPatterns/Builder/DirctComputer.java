package com.cloud.staff.apifirst.controller.java.DesignPatterns.Builder;

public class DirctComputer {
	
	public BuilderComputer builderComputer;
	
	public void setComputer(BuilderComputer buildComputerSig) {
		this.builderComputer=buildComputerSig;
	}
	
	public void setAttribute() {
		builderComputer.setComputer();
		builderComputer.setcomputercpu();
		builderComputer.setcomputermemery();
		builderComputer.setcomputername();
		builderComputer.setcomputerscran();
	}

}
