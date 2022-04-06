package Atmega328CPUInstructions;

import java.util.Arrays;
import java.util.regex.*;

import Interpreter.AbstractCPU;
import Interpreter.AbstractInstruction;
import Interpreter.InstructionType;

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
		
		this.immediateValue = regexParseAssemblyConstant(args[1]);
		
		if(this.immediateValue.equals(null)) {
			throw new Exception("Invalid Immediate Constant " + args[1]);
		}
		
		this.args = args;
	}

	private Byte regexParseAssemblyConstant(String potentialConstant) throws Exception {
	    Pattern pattern = Pattern.compile("(0[xX])([0-9a-fA-F]{1,2})|(0[bB])([0-1]{1,8})|(0[oO])([0-7]{1,3})|([0-9]{1,3})", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(potentialConstant);
	    if(!matcher.find()) {
	    	throw new Exception("Invalid Constant '"+ potentialConstant +"' Specified");
	    }
	    
	    System.out.print("Group 0: " + matcher.group(0));
	    System.out.print(" Group 1: " + matcher.group(1));
	    System.out.println(" Group 2: " + matcher.group(2));
	    
	    if(matcher.group(1) != null) {
	    	return Byte.parseByte(matcher.group(2), 16);
	    }
	    
	    if(matcher.group(7) != null) {
	    	return Byte.parseByte(matcher.group(7), 10);
	    }
	    
	    if(matcher.group(3) != null) {
	    	return Byte.parseByte(matcher.group(4), 2);
	    }
	    
	    if(matcher.group(5) != null) {
	    	return Byte.parseByte(matcher.group(6), 8);
	    }
	    
	    throw new Exception("Invalid Constant '"+ potentialConstant +"' Specified");
	 
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
