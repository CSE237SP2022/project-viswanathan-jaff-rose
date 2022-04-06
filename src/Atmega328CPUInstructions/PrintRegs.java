package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;

public class PrintRegs extends AbstractInstruction {

	public PrintRegs() {
		this.opcode = "@@printregs";
		this.CPU = "Atmega328P";
		this.type = InstructionType.Macro;
	}

	@Override
	public void setArgs(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public AbstractCPU run(AbstractCPU cpu, boolean debug) throws Exception {
		throw new Exception("Macro " + this.opcode  + " was ran as Hardware Instruction");
	}

}
