package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPUState;
import Interpreter.AbstractCPUState;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;

public class PrintReg extends AbstractInstruction {

	public PrintReg() {
		this.opcode = "@@printreg";
		this.CPU = "Atmega328P";
		this.type = InstructionType.Macro;
	}

	@Override
	public void setArgs(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public ATmega328PCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception {

		throw new Exception("Macro " + this.opcode  + " was ran as Hardware Instruction");
	}

}
