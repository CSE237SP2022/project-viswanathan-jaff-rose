package Atmega328CPUInstructions;

import Interpreter.AbstractInstructionType;
import Interpreter.AbstractCPU;
import Interpreter.AbstractInstruction;

public class INC extends AbstractInstruction {
	
	private String registerIncremented;
	
	private AbstractCPU cpu;
	
	public INC() {
		this.opcode = "INC";
		this.CPU = "Atmega328P";
		this.type = AbstractInstructionType.HWInstruction;
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
		
		if(cpu.getRegister(this.registerIncremented) == 0x7F) {
			
			cpu.setRegister(this.registerIncremented, (byte)(cpu.getRegister(this.registerIncremented) + 1) );
			
			cpu.setRegister("V", (byte) 1);
			
			return cpu;
			
		}
		
		cpu.setRegister(this.registerIncremented, (byte)(cpu.getRegister(this.registerIncremented) + 1) );
		
		return cpu;	
	}
	
	private void printDebug(byte newVal) {
		
		System.out.println("Existing Value at register " + this.registerIncremented + ": " + this.cpu.getRegister(this.registerIncremented));
		System.out.println("New Value at register " + this.registerIncremented + ": " + newVal);
	
	}
	
	
	

	

}
