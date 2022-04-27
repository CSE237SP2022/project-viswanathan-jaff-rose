package Atmega328CPUInstructions;

import Interpreter.InstructionType;
import Interpreter.ATmega328PCPUState;
import Interpreter.ATmega328PInstruction;

import Interpreter.AbstractCPU;
import Interpreter.AbstractCPUState;
import Interpreter.AbstractInstruction;

public class MOV extends ATmega328PInstruction {
	
	private String sourceRegister;
	private String destinationRegister;
	
	public MOV() {
		this.opcode = "MOV";
		this.CPU = "Atmega328P";
		this.type = InstructionType.HWInstruction;
	}
	
	public void setArgs(String[] args) {
		this.destinationRegister = args[0];
		this.sourceRegister = args[1];
		
	}
	
	@Override
	public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{

		
		if(debug) {
			printDebug( (cpustate.getRegister(this.destinationRegister)), cpustate );
		}
		
		cpustate.setRegister(this.destinationRegister, cpustate.getRegister(this.sourceRegister));
		
		return cpustate;	
	}
	
	private void printDebug(int newVal, ATmega328PCPUState cpustate) {
		System.out.println("Existing Value at register " + cpustate.getRegister(this.sourceRegister));
		System.out.println("New Value at register "+  cpustate.getRegister(this.destinationRegister));
	}

	@Override
	public AbstractCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception {
		if(cpustate instanceof ATmega328PCPUState) {
			return run((ATmega328PCPUState) cpustate, debug);
		}
		throw new Exception("Invalid or Corrupt CPU State");
	}
	
}
