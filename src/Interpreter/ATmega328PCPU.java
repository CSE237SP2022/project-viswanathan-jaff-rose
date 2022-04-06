package Interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Atmega328CPUInstructions.*;

public class ATmega328PCPU extends AbstractCPU {
	
	private HashMap<String, Byte> registers;
	
	
	private HashMap<String, AbstractInstruction> instructionMap;
	
	private LinkedList<AbstractCPU> CPUStates;

	
	private void create_opcode_map() {
		
		this.instructionMap.put("INC", new INC());
		this.instructionMap.put("LDI", new LDI());
		this.instructionMap.put("@@printregs", new PrintRegs());
	}
	
	
	public ATmega328PCPU() {
		
		this.registers = new HashMap<String, Byte>();
		
		map_registers();
		
		this.instructionMap = new HashMap<String, AbstractInstruction>();
		
		this.CPUStates = new LinkedList<AbstractCPU>();
		
		create_opcode_map();
		
	}
	
	
	private void map_registers() {
		
		String [] rRegisterArray = {"r16","r17","r18","r19","r20","r21","r22","r23","r24","r25","r26","r27","r28","r29","r30","r31","r32"};
		
		this.registers.put("C", (byte) 0);
		this.registers.put("Z", (byte) 0);
		this.registers.put("N", (byte) 0);
		this.registers.put("V", (byte) 0);
		this.registers.put("S", (byte) 0);
		this.registers.put("H", (byte) 0);
		this.registers.put("T", (byte) 0);
		this.registers.put("I", (byte) 0);
		this.registers.put("SREG", (byte) 0);
		
		for(String rRegister : rRegisterArray) {
		
			this.registers.put(rRegister, (byte) 0);

		}
		
	}


	public ATmega328PCPU(ATmega328PCPU oldcpu) {

		this.registers = new HashMap<String, Byte>(oldcpu.getRegisters());
		this.instructionMap = new HashMap<String, AbstractInstruction>(oldcpu.getInstructionMap());
		
	}
	
	public ATmega328PCPU(HashMap<String, Byte> registers, LinkedList<AbstractCPU> CPUStates) {

		this.registers = new HashMap<String, Byte>(registers);
		
		this.CPUStates = new LinkedList<AbstractCPU>(CPUStates);
		
		this.instructionMap = new HashMap<String, AbstractInstruction>();
		
		create_opcode_map();
		
	}
	
	
	public HashMap<String, AbstractInstruction> getInstructionMap() {
		return this.instructionMap;
	}
	
	public HashMap<String, Byte> getRegisters(){
		return this.registers;
	}


	public void run(LinkedList<String[]> instructions) throws Exception {
		
		if(this.debugFlag) {
			System.out.println(this.toString() + "\n");
		}
		
		for(String [] Line : instructions) {
			
			AbstractInstruction InstructionToExecute = instructionMap.get(Line[0]);
			
			if(InstructionToExecute == null) {
				throw new Exception("Invalid Instruction " + "\"" + Line[0] + "\"" + " Specified");
			}
			
			String[] Args = pop_args(Line);
			
			InstructionToExecute.setArgs( Args );
			
			switch(InstructionToExecute.getType()) {
				case HWInstruction:
					this.updateCPU((ATmega328PCPU) InstructionToExecute.run(this, this.debugFlag));
					break;
				case Macro:
					this.runMacro(InstructionToExecute.getOpcode());
					break;
			} 

			
			
			
			ATmega328PCPU oldCPU = new ATmega328PCPU(this.registers, this.CPUStates);
			this.CPUStates.add(oldCPU);
			
			if(this.debugFlag) {
				System.out.println(Arrays.toString(Line));
				System.out.println(this.toString() + "\n");
			}
			
		}
		
	}


	private void runMacro(String opcode) throws Exception {
		
		
		switch(opcode) {
		
		case "@@printregs":
			System.out.println(this.toString());
			return;
			
		default:
			throw new Exception("Invalid or Unimplemented Macro: " + opcode);
		}
		
	}


	private String[] pop_args(String[] line) {
		
		String [] args = new String[line.length-1];
		
		for(int i=1;i<line.length;i++) {
			args[i-1] = line[i];
		}
		
		return args;
	}


	private void updateCPU(ATmega328PCPU ATmega328PCPU) {
		
		this.registers = ATmega328PCPU.registers;
		
		
	}


	public String toString() {
		
		StringBuilder debugString = new StringBuilder();
		
		StringBuilder specialRegs = new StringBuilder();
		
		debugString.append("Registers: \n");
		
		int[] rRegcounter = {0};
		
		int[] sRegCounter = {0};
		
		this.registers.forEach((key, value) -> {
			
			if(key.substring(0,1).equals("r")) {
				debugString.append(key + ": 0x");
				debugString.append(String.format("%02X ", value));
				debugString.append("  ");
				
				if(rRegcounter[0] % 6 == 5) {
					debugString.append("\n");
				}
				rRegcounter[0]++;
				
			}
			else {
				specialRegs.append(key + ": 0x");
				specialRegs.append(String.format("%02X ", value));
				specialRegs.append("  ");

				if(sRegCounter[0] % 6 == 5) {
					specialRegs.append("\n");
				}
				sRegCounter[0]++;
			}

		 });
		
		debugString.append("\n Special Reigsters: \n");
		
		debugString.append(specialRegs);
		
		return debugString.toString();
		
	}


	@Override
	public void setRegister(String register, byte value) {
		
		switch (register.substring(0,1)) {
			default:
				this.registers.put(register, value);
				return;
			
		}
		
	}
	
	@Override
	public byte getRegister(String register) {
		
		System.out.println("getting register: " + register);
		
		switch (register) {
		default:
			return this.registers.get(register);
		
		}
	}
	
	public HashMap<String, Byte> getRegisterMap() {
		return this.registers;
	}
	
	public HashMap<String, AbstractInstruction> getSupportedInstructions() {
		return this.instructionMap;
	}
	
	
	
public static void main(String[] args) {
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		String [][] instructions = { {"INC", "r29" } };
		
		//ArduinoUno.run(instructions, true);

	}




}
