package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPUState;
import Interpreter.AbstractCPUState;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;

public class POP extends AbstractInstruction {

	private String registerToPop;

	public POP() {
		this.opcode = "POP";
		this.CPU = "Atmega328P";
		this.type = InstructionType.HWInstruction;
	}
	
	@Override
	public void setArgs(String[] args) throws Exception {	
		
		if(args.length != 1) {			
			throw new Exception("Incorrect Number of Arguments specified, was " + args.length + " expected 1");
		}
		
		this.registerToPop = args[0];

	}

	public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{
		if(debug) {
			System.out.println("Stack Dump: " + cpustate.getMemoryStack().toString());
		}
		
		cpustate.setRegister(registerToPop, cpustate.popStack());
		
		return cpustate;

	}

	@Override
	public AbstractCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception {
		if(cpustate instanceof ATmega328PCPUState) {
			return run((ATmega328PCPUState) cpustate, debug);
		}
		throw new Exception("Invalid or Corrupt CPU State");

	}

}
