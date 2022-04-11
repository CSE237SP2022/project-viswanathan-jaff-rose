package Atmega328CPUInstructions;

import java.util.Arrays;
import java.util.regex.*;

import Interpreter.ATmega328PCPUState;
import Interpreter.ATmega328PInstruction;
import Interpreter.AbstractCPU;
import Interpreter.AbstractCPUState;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;
import Utils.ParsingUtils;

public class LDI extends ATmega328PInstruction {
	
	private String [] args;
	Integer immediateValue;
	String registerToBeLoaded;
	
	public LDI() {
		this.opcode = "LDI";
		this.CPU = "Atmega328P";
		this.type = InstructionType.HWInstruction;
		
	}

	@Override
	public void setArgs(String[] args) throws Exception {	
		
		if(args.length != 2) {			
			throw new Exception("Incorrect Number of Arguments specified, was " + args.length + " expected 2");
		}
		
		this.registerToBeLoaded = args[0];
		
		this.immediateValue = ParsingUtils.regexParseAssemblyConstant(args[1]);
		
		if(this.immediateValue.equals(null)) {
			throw new Exception("Invalid Immediate Constant " + args[1]);
		}
		
		this.args = args;
	}

	public ATmega328PCPUState run(ATmega328PCPUState cpustate, boolean debug) throws Exception{
		if(debug) {
			System.out.println("Args: " + Arrays.toString(args));
		}
		
		cpustate.setRegister(this.registerToBeLoaded, this.immediateValue.byteValue());
		
		return cpustate;
	}
	
	@Override
	public AbstractCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception {
		if(cpustate instanceof ATmega328PCPUState) {
			return run((ATmega328PCPUState) cpustate, debug);
		}
		throw new Exception("Invalid or Corrupt CPU State");
	}

}
