package Atmega328CPUInstructions;

import Interpreter.AbstractCPU;

public class INC extends AbstractInstruction {
	
	public String registerIncremented;
	
	public INC() {
		// TODO Auto-generated constructor stub
	}
	
	public void setArgs(String[] args) {
		this.registerIncremented = args[0];
	}
	
	@Override
	public AbstractCPU run(AbstractCPU cpu) {
		
		cpu.setRegister(this.registerIncremented, (byte)(cpu.getRegister(this.registerIncremented) + 1) );
		
		return cpu;
		
	}
	

	

}
