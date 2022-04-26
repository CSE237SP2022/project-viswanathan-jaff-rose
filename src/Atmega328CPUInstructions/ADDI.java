package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPUState;
import Interpreter.ATmega328PInstruction;
import Interpreter.AbstractCPU;
import Interpreter.AbstractCPUState;
import Interpreter.InstructionType;
import Utils.ParsingUtils;
public class ADDI extends ATmega328PInstruction {

    //member variable declaration
    private String destRegister;
    private String immediate;
    private String [] args;
    private ATmega328PCPUState cpustate;

    public ADDI() {
        this.opcode = "ADDI";
        this.CPU = "Atmega328P";
        this.type = InstructionType.HWInstruction;
    }

    public void setArgs(String[] args) throws Exception{
       
        
        if(args.length != 2) {			
			throw new Exception("Incorrect Number of Arguments specified, was " + args.length + " expected 2");
		}
        
        this.destRegister = args[0];
        this.immediate = args[1];
        
        if(this.immediate.equals(null)) {
			throw new Exception("Invalid Immediate Constant " + args[1]);
		}
		
		this.args = args;
    }

    @Override
    public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{
        this.cpustate = cpustate;
        if(debug) {
            printDebug(Integer.valueOf(this.immediate));
        }
        int result = cpustate.getRegister(this.destRegister) + Integer.valueOf(this.immediate);
        //checks for two's complement overflow of register
        if(result >= 0x7F) {
            cpustate.setRegister(this.destRegister, (Integer.valueOf(this.immediate) + this.cpustate.getRegister(this.destRegister)));
            cpustate.setRegister("V", (byte) 1);
            return cpustate;
        }
        else {
            cpustate.setRegister(this.destRegister, (Integer.valueOf(this.immediate) + this.cpustate.getRegister(this.destRegister)));
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
        System.out.println("New Value at destination register " + this.destRegister + ": " + (this.immediate + this.cpustate.getRegister(this.destRegister)));
    }
}