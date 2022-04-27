package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPUState;
import Interpreter.ATmega328PInstruction;
import Interpreter.AbstractCPU;
import Interpreter.AbstractCPUState;
import Interpreter.InstructionType;
public class CP extends ATmega328PInstruction {

    //member variable declaration
    private String destRegister;
    private String srcRegister;
    private ATmega328PCPUState cpustate;

    public CP() {
        this.opcode = "CP";
        this.CPU = "Atmega328P";
        this.type = InstructionType.HWInstruction;
    }

    public void setArgs(String[] args) {
        this.destRegister = args[0];
        this.srcRegister = args[1];
    }

    @Override
    public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{
    	this.cpustate = cpustate;
        if(debug) {
            printDebug(cpustate.getRegister(this.srcRegister));
        }
        int destNumber = cpustate.getRegister(this.destRegister);
        int srcNumber = cpustate.getRegister(this.srcRegister);
        int result = destNumber - srcNumber;
        //checks for result of comparison
        if(result == 0) {
        	// They are equal
        	System.out.println("Equal regs!");
            cpustate.setRegister("Z", (byte) 1);
            return cpustate;
        } else if( result < 0 ) {
        	// dest is smaller
        	cpustate.setRegister("Z", (byte) 0);
        	cpustate.setRegister("C", (byte) 1);
        	return cpustate;
        } else {
        	// dest is larger
        	cpustate.setRegister("Z", (byte) 0);
        	cpustate.setRegister("C", (byte) 0);
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
        System.out.println("New Value at C register " + "C" + ": " + this.cpustate.getRegister("C"));
        System.out.println("New Value at Z register " + "Z" + ": " + this.cpustate.getRegister("Z"));
    }
}