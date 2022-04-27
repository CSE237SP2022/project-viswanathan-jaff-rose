package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPUState;
import Interpreter.AbstractCPUState;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;

public class RET extends AbstractInstruction {

	public RET() {
		this.opcode = "RET";
		this.CPU = "Atmega328P";
		this.type = InstructionType.FlowInstruction;
	}

	@Override
	public void setArgs(String[] args) throws Exception {
		// TODO Auto-generated method stub

	}
	

	public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{

		

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
