package Atmega328CPUInstructions;

import Interpreter.AbstractCPUState;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;

public class BREQ extends AbstractInstruction {

	public BREQ() {
		this.opcode = "BREQ";
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
