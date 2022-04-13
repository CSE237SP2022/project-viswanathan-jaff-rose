package Atmega328CPUInstructions;

import Interpreter.InstructionType;
import Interpreter.AbstractCPU;
import Interpreter.AbstractInstruction;

public class SUB extends AbstractInstruction {
	
	private String destinationRegister;
    private String srcRegister;

    private AbstractCPU cpu;

    public SUB() {
        this.opcode = "SUB";
        this.CPU = "Atmega328P";
    }

    public void setArgs(String[] args) {
        this.destinationRegister = args[0];
        this.srcRegister = args[1];
    }
    
    public AbstractCPU run(AbstractCPU cpu, boolean debug) {
    	this.cpu = cpu;
    	
    	int destinationAmount = cpu.getRegister(destinationRegister);
    	int srcAmount = cpu.getRegister(srcRegister);
    	int resultOfSubtraction = destinationAmount - srcAmount;
    	
    	if( debug ) {
    		
    	} 
    	cpu.setRegister(this.destinationRegister, resultOfSubtraction);
    	 
    	
    }


}
