package Atmega328CPUInstructions;

import Interpreter.AbstractCPU;

public class INC extends AbstractInstruction {
	
	private String registerIncremented;
	
	private AbstractCPU cpu;
	
	public INC() {
		// TODO Auto-generated constructor stub
	}
	
	public void setArgs(String[] args) {
		this.registerIncremented = args[0];
	}
	
	@Override
	public AbstractCPU run(AbstractCPU cpu, boolean debug) {
		
		this.cpu = cpu;
		
		if(debug) {
			printDebug( (byte)(this.cpu.getRegister(this.registerIncremented) + 1) );
		}
		
		cpu.setRegister(this.registerIncremented, (byte)(cpu.getRegister(this.registerIncremented) + 1) );
		
		return cpu;	
	}
	
	private void printDebug(byte newVal) {
		
		System.out.println("Existing Value at register " + this.registerIncremented + ": " + this.cpu.getRegister(this.registerIncremented));
		System.out.println("New Value at register " + this.registerIncremented + ": " + newVal);
	
	}
	
	
	

	

}
