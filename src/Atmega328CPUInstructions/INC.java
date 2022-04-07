package Atmega328CPUInstructions;

import Interpreter.InstructionType;
import Interpreter.ATmega328PCPUState;
import Interpreter.ATmega328PInstruction;
import Interpreter.AbstractCPU;
import Interpreter.AbstractCPUState;
import Interpreter.AbstractInstruction;

public class INC extends ATmega328PInstruction {
	
	private String registerIncremented;
	
	private AbstractCPU cpu;
	
	public INC() {
		this.opcode = "INC";
		this.CPU = "Atmega328P";
		this.type = InstructionType.HWInstruction;
	}
	
	public void setArgs(String[] args) {
		this.registerIncremented = args[0];
	}
	
	@Override
	public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{
		
		if(debug) {
			printDebug( (byte)(this.cpu.getRegister(this.registerIncremented) + 1) );
		}
		
		if(cpustate.getRegister(this.registerIncremented) == 0x7F) {
			
			cpustate.setRegister(this.registerIncremented, (byte)(cpu.getRegister(this.registerIncremented) + 1) );
			
			cpustate.setRegister("V", (byte) 1);
			
			return cpustate;
			
		}
		
		cpustate.setRegister(this.registerIncremented, (byte)(cpu.getRegister(this.registerIncremented) + 1) );
		
		return cpustate;	
	}
	
	private void printDebug(byte newVal) {
		
		System.out.println("Existing Value at register " + this.registerIncremented + ": " + this.cpu.getRegister(this.registerIncremented));
		System.out.println("New Value at register " + this.registerIncremented + ": " + newVal);
	
	}

	@Override
	public AbstractCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception {
		if(cpustate instanceof ATmega328PCPUState) {
			run((ATmega328PCPUState) cpustate, debug);
		}
		throw new Exception("Invalid or Corrupt CPU State");
	}
	
}
