package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPUState;
import Interpreter.AbstractCPUState;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;

public class BRNE extends AbstractInstruction {

	public BRNE() {
		this.opcode = "BRNE";
		this.CPU = "Atmega328P";
		this.type = InstructionType.FlowInstruction;
	}

	@Override
	public void setArgs(String[] args) throws Exception {

	}

	@Override
	public AbstractCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception {
		throw new Exception("Ran Flow Instruction " + this.opcode + " as Hardware Instruction");
	}

}
