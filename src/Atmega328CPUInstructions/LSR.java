package Atmega328CPUInstructions;

import Interpreter.InstructionType;
import Interpreter.ATmega328PCPUState;
import Interpreter.ATmega328PInstruction;

import Interpreter.AbstractCPU;
import Interpreter.AbstractCPUState;
import Interpreter.AbstractInstruction;

public class LSR extends ATmega328PInstruction {
	
	private String registerShifted;
	
	
	public LSR() {
		this.opcode = "LSR";
		this.CPU = "Atmega328P";
		this.type = InstructionType.HWInstruction;
	}
	
	public void setArgs(String[] args) {
		this.registerShifted = args[0];
	}
	
	@Override
	public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{

		
		if(debug) {
			printDebug( (cpustate.getRegister(this.registerShifted) >> 1), cpustate );
		}
		
		cpustate.setRegister(this.registerShifted, (cpustate.getRegister(this.registerShifted) >> 1) );
		
		return cpustate;	
	}
	
	private void printDebug(int newVal, ATmega328PCPUState cpustate) {
		
		System.out.println("Existing Value at register " + this.registerShifted + ": " + cpustate.getRegister(this.registerShifted));
		System.out.println("New Value at register " + this.registerShifted + ": " + newVal);
	
	}

	@Override
	public AbstractCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception {
		if(cpustate instanceof ATmega328PCPUState) {
			return run((ATmega328PCPUState) cpustate, debug);
		}
		throw new Exception("Invalid or Corrupt CPU State");
	}
	
}