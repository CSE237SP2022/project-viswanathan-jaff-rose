package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPUState;
import Interpreter.ATmega328PInstruction;
import Interpreter.AbstractCPU;
import Interpreter.AbstractCPUState;
public class ADD extends ATmega328PInstruction {

    private String destRegister;
    private String srcRegister;

    private AbstractCPU cpu;

    public ADD() {
        this.opcode = "ADD";
        this.CPU = "Atmega328P";
    }

    public void setArgs(String[] args) {
        this.destRegister = args[0];
        this.srcRegister = args[1];
    }

    @Override
    public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{

        if(debug) {
            printDebug((byte)(this.cpu.getRegister(this.srcRegister));
        }

        int result = (byte) cpustate.getRegister(this.destRegister) + (byte) cpustate.getRegister(this.srcRegister);
        cpu.setRegister(this.destRegister, (byte)result);
        return cpustate;
    }
    
	@Override
	public AbstractCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception {
		if(cpustate instanceof ATmega328PCPUState) {
			return run((ATmega328PCPUState) cpustate, debug);
		}
		throw new Exception("Invalid or Corrupt CPU State");
	}

    private void printDebug(byte newVal) {
        System.out.println("Existing Value at destination register " + this.destRegister + ": " + this.cpu.getRegister(this.destRegister));
        System.out.println("Value at source register " + this.srcRegister + ": " + this.cpu.getRegister(this.srcRegister));
        System.out.println("New Value at destination register " + this.destRegister + ": " + newVal);
    }
}