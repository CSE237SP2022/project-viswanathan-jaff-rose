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
	}
	
	
	public ATmega328PCPU() {
		
		this.registers = new HashMap<String, Byte>();
		
		this.registers.put("C", (byte) 0);
		this.registers.put("Z", (byte) 0);
		this.registers.put("N", (byte) 0);
		this.registers.put("V", (byte) 0);
		this.registers.put("S", (byte) 0);
		this.registers.put("H", (byte) 0);
		this.registers.put("T", (byte) 0);
		this.registers.put("I", (byte) 0);
		this.registers.put("SREG", (byte) 0);
		this.registers.put("r28", (byte) 0);
		this.registers.put("r29", (byte) 0);
		
		this.instructionMap = new HashMap<String, AbstractInstruction>();
		
		this.CPUStates = new LinkedList<AbstractCPU>();
		
		create_opcode_map();
		
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
	
	protected HashMap<String, Byte> getRegisters(){
		return this.registers;
	}


	public void run(LinkedList<String[]> instructions, boolean debugMode) throws Exception {
		
		if(debugMode) {
			System.out.println(this.toString() + "\n");
		}
		
		for(String [] Line : instructions) {
			
			AbstractInstruction InstructionToExecute = instructionMap.get(Line[0]);
			
			if(InstructionToExecute == null) {
				throw new Exception("Invalid Instruction " + "\"" + Line[0] + "\"" + " Specified");
			}
			
			String[] Args = pop_args(Line);
			
			InstructionToExecute.setArgs( Args );
			this.updateCPU((ATmega328PCPU) InstructionToExecute.run(this, debugMode));
			
			//ATmega328PCPU oldCPU = new ATmega328PCPU(this.registers, this.CPUStates);
			
			//this.CPUStates.add(oldCPU);
			
			
			if(debugMode) {
				System.out.println(Arrays.toString(Line));
				System.out.println(this.toString() + "\n");
			}
			
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
		
		debugString.append("CPU Dump: \n");
		
		int registerNumber = 1;
		
		int[] iarr = {0};
		
		this.registers.forEach((key, value) -> {
			
			 

			debugString.append(key + ": 0x");
			debugString.append(String.format("%02X ", value));
			debugString.append("  ");

			if(iarr[0] % 6 == 0) {
				debugString.append("\n");
			}
			
			iarr[0]++;
		      
		 });
		
		
		return debugString.toString();
		
	}

	@Override
	public void setRegister(String register, byte value) {
		
		switch (register.substring(0,1)) {
			case "r":
				this.registers.put("r28", value);
				return;
			
		}
		
	}
	
	@Override
	public byte getRegister(String register) {
		
		switch (register) {
		case "r29":
			return this.registers.get("r28");
		
		}
		return 0;
	}
	
public static void main(String[] args) {
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		String [][] instructions = { {"INC", "r29" } };
		
		//ArduinoUno.run(instructions, true);

	}




}
