package Atmega328CPUInstructions;

import Interpreter.ATmega328PCPUState;
import Interpreter.ATmega328PInstruction;
import Interpreter.AbstractCPU;
import Interpreter.AbstractCPUState;
import Interpreter.InstructionType;
public class SUB extends ATmega328PInstruction {

    //member variable declaration
    private String destRegister;
    private String srcRegister;
    private ATmega328PCPUState cpustate;

    public SUB() {
        this.opcode = "SUB";
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
    	int destNumber = cpustate.getRegister(this.destRegister);
    	int srcNumber = cpustate.getRegister(this.srcRegister);
    	int result = srcNumber - destNumber;
        if(debug) {
            printDebug(cpustate.getRegister(this.srcRegister));
        }
        //checks for overflow of register
        if(result < 0x00) {
            cpustate.setRegister(this.destRegister, result);
            cpustate.setRegister("V", (byte) 1);
            return cpustate;
        } else {
            cpustate.setRegister(this.destRegister,result);
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
        System.out.println("New Value at destination register " + this.destRegister + ": " + (this.cpustate.getRegister(this.srcRegister) - this.cpustate.getRegister(this.destRegister)));
    }
}









//package Atmega328CPUInstructions;
//
//import Interpreter.InstructionType;
//import Interpreter.AbstractCPU;
//import Interpreter.AbstractInstruction;
//
//public class SUB extends AbstractInstruction {
//	
//	private String destinationRegister;
//    private String srcRegister;
//
//    private AbstractCPU cpu;
//
//    public SUB() {
//        this.opcode = "SUB";
//        this.CPU = "Atmega328P";
//    }
//
//    public void setArgs(String[] args) {
//        this.destinationRegister = args[0];
//        this.srcRegister = args[1];
//    }
//    
//    public AbstractCPU run(AbstractCPU cpu, boolean debug) {
//    	this.cpu = cpu;
//    	
//    	int destinationAmount = cpu.getRegister(destinationRegister);
//    	int srcAmount = cpu.getRegister(srcRegister);
//    	int resultOfSubtraction = destinationAmount - srcAmount;
//    	
//    	if( debug ) {
//    		
//    	} 
//    	cpu.setRegister(this.destinationRegister, resultOfSubtraction);
//    	 
//    	
//    }
//
//
//}
