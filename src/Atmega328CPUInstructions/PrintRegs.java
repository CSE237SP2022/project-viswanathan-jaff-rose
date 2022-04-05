package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;
import Interpreter.AbstractInstruction;

public class PrintRegs extends AbstractInstruction {

	public PrintRegs() {
		// TODO Auto-generated constructor stub
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
		
		HashMap<String, Byte> regsmap = ((ATmega328PCPU) cpu).getRegisters();
		
		return cpu;
	}

}
