package Interpreter;

public abstract class ATmega328PInstruction extends AbstractInstruction {

	public ATmega328PInstruction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setArgs(String[] args) throws Exception {
		// TODO Auto-generated method stub

	}

	public abstract ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception;

}
