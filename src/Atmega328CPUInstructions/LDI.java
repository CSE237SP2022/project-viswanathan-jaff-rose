package Atmega328CPUInstructions;

import Interpreter.AbstractCPU;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;

public class LDI extends AbstractInstruction {

	public LDI() {
		this.opcode = "LDI";
		this.CPU = "Atmega328P";
		this.type = InstructionType.HWInstruction;
	}

	@Override
	public void setArgs(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractCPU run(AbstractCPU cpu, boolean debug) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

}
