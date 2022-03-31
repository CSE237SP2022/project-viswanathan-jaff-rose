package Atmega328CPUInstructions;


import Interpreter.AbstractCPU;

public abstract class AbstractInstruction {
	
	
	
	public abstract void setArgs(String[] args);
	
	
	public abstract void run();


	public abstract AbstractCPU run(AbstractCPU CPU);
	
	
	
	
}
