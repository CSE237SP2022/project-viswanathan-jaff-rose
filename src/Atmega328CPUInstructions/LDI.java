package Atmega328CPUInstructions;

import java.util.Arrays;
import java.util.regex.*;

import Interpreter.AbstractCPU;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;
import Utils.ParsingUtils;

public class LDI extends AbstractInstruction {
	
	private String [] args;
	Byte immediateValue;
	String registerToBeLoaded;
	
	public LDI() {
		this.opcode = "LDI";
		this.CPU = "Atmega328P";
		this.type = InstructionType.HWInstruction;
		
	}

	@Override
	public void setArgs(String[] args) throws Exception {	
		
		if(args.length != 2) {			
			throw new Exception("Incorrect Number of Arguments specified, was " + this.args.length + " expected 2");
		}
		
		this.registerToBeLoaded = args[0];
		
		this.immediateValue = ParsingUtils.regexParseAssemblyConstant(args[1]);
		
		if(this.immediateValue.equals(null)) {
			throw new Exception("Invalid Immediate Constant " + args[1]);
		}
		
		this.args = args;
	}

	@Override
	public AbstractCPU run(AbstractCPU cpu, boolean debug) throws Exception{
		if(debug) {
			System.out.println("Args: " + Arrays.toString(args));
		}
		
		cpu.setRegister(this.registerToBeLoaded, this.immediateValue.byteValue());
		
		return cpu;
	}

}
