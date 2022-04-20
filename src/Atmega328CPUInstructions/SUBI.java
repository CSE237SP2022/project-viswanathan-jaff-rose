package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPUState;
import Interpreter.ATmega328PInstruction;
import Interpreter.AbstractCPU;
import Interpreter.AbstractCPUState;
import Interpreter.InstructionType;
public class SUBI extends ATmega328PInstruction {

    //member variable declaration
    private String destRegister;
    private int immediate;
    private ATmega328PCPUState cpustate;

    public ADD() {
        this.opcode = "SUBI";
        this.CPU = "Atmega328P";
        this.type = InstructionType.HWInstruction;
    }

    public void setArgs(String[] args) {
        this.destRegister = args[0];
        this.immediate = args[1];
    }

    @Override
    public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{
        this.cpustate = cpustate;
        if(debug) {
//            printDebug(cpustate.getRegister(this.srcRegister));
            System.out.println('Value to sub: '+this.immediate+': Register to sub: '+cpustate.getRegister(this.destRegister))
        }
        int result = cpustate.getRegister(this.destRegister) - this.immediate;
        //checks for two's complement overflow of register
        if(result >= 0x7F) {
            cpustate.setRegister(this.destRegister, (this.cpustate.getRegister(this.destRegister)-this.immediate));
            cpustate.setRegister("V", (byte) 1);
            return cpustate;
        }
        else {
            cpustate.setRegister(this.destRegister, (this.cpustate.getRegister(this.destRegister)-this.immediate));
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
        System.out.println("New Value at destination register " + this.destRegister + ": " + (this.cpustate.getRegister(this.destRegister)-this.immediate));
    }
}