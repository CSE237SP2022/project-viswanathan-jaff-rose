package Atmega328CPUInstructions;

import Interpreter.AbstractCPU;
import Interpreter.AbstractInstruction;

public class LDI extends AbstractInstruction {

	public LDI() {
		this.opcode = "LDI";
		this.CPU = "Atmega328P";
	}

	@Override
	public void setArgs(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractCPU run(AbstractCPU cpu, boolean debug) {
		// TODO Auto-generated method stub
		return null;
	}

}
