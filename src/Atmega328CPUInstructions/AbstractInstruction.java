package Atmega328CPUInstructions;


import Interpreter.AbstractCPU;

public abstract class AbstractInstruction {
	
	protected String opcode;
	
	protected String CPU;
	
	public abstract void setArgs(String[] args);
		
	public abstract void run();

	public abstract AbstractCPU run(AbstractCPU cpu, boolean debug);
	
	public String getOpcode() {
		return this.opcode;
	}

}