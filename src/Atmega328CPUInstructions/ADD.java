package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPUState;
import Interpreter.ATmega328PInstruction;
import Interpreter.AbstractCPU;
import Interpreter.AbstractCPUState;
import Interpreter.InstructionType;
public class ADD extends ATmega328PInstruction {

    //member variable declaration
    private String destRegister;
    private String srcRegister;
    private ATmega328PCPUState cpustate;

    public ADD() {
        this.opcode = "ADD";
        this.CPU = "Atmega328P";
        this.type = InstructionType.HWInstruction;
    }

    public void setArgs(String[] args) throws Exception {
		if(args.length != 2) {			
			throw new Exception("Incorrect Number of Arguments specified, was " + args.length + " expected 2");
		}
        this.destRegister = args[0];
        this.srcRegister = args[1];
    }

    @Override
    public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{
    	this.cpustate = cpustate;
        if(debug) {
            printDebug(cpustate.getRegister(this.srcRegister));
        }
        int result = cpustate.getRegister(this.destRegister) + cpustate.getRegister(this.srcRegister);
        //checks for two's complement overflow of register
        if(result >= 0x7F) {
            cpustate.setRegister(this.destRegister, (this.cpustate.getRegister(this.srcRegister) + this.cpustate.getRegister(this.destRegister)));
            cpustate.setRegister("V", (byte) 1);
            return cpustate;
        } 
        else {
            cpustate.setRegister(this.destRegister, (this.cpustate.getRegister(this.srcRegister) + this.cpustate.getRegister(this.destRegister)));
            return cpustate;
        }

    }
    
	@Override
	public AbstractCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception {
		if(cpustate instanceof ATmega328PCPUState) {
			return run((ATmega328PCPUState) cpustate, debug);
		}
		throw new Exception("Invalid or Corrupt CPU State");
	}

    private void printDebug(int newVal) {
        System.out.println("Existing Value at destination register " + this.destRegister + ": " + this.cpustate.getRegister(this.destRegister));
        System.out.println("Value at source register " + this.srcRegister + ": " + this.cpustate.getRegister(this.srcRegister));
        System.out.println("New Value at destination register " + this.srcRegister + ": " + (this.cpustate.getRegister(this.srcRegister) + this.cpustate.getRegister(this.destRegister)));
    }
}