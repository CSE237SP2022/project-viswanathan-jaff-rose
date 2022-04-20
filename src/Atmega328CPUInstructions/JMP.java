package Atmega328CPUInstructions;

import Interpreter.AbstractCPUState;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;

public class JMP extends AbstractInstruction {

	public JMP() {
		this.opcode = "CALL";
		this.CPU = "Atmega328P";
		this.type = InstructionType.FlowInstruction;
	}

	@Override
	public void setArgs(String[] args) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception {
		return null;
	}

}
