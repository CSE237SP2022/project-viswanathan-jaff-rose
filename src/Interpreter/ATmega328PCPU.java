package Interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import Atmega328CPUInstructions.AbstractInstruction;
import Atmega328CPUInstructions.INC;

public class ATmega328PCPU extends AbstractCPU {
	
	private byte[] r_registers;
	
	//Special Registers
	private byte SREG_register;
	private byte C_register;
	private byte Z_register;
	private byte N_register;
	private byte V_register;
	private byte S_register;
	private byte H_register;
	private byte T_register;
	private byte I_register;
	
	private HashMap<String, AbstractInstruction> instructionMap;
	
	private LinkedList<AbstractCPU> CPUStates;
	
	private void create_opcode_map() {
		
		this.instructionMap.put("INC", new INC());
	}
	
	
	public ATmega328PCPU() {
		this.r_registers = new byte[30];
		
		this.instructionMap = new HashMap<String, AbstractInstruction>();
		
		create_opcode_map();
		
	}
	
	public void run(String[][] instructions, boolean debugMode) {
		
		if(debugMode) {
			System.out.println(this.toString() + "\n");
		}
		
		for(String [] Line : instructions) {
			
			AbstractInstruction InstructionToExecute = instructionMap.get(Line[0]);
			
			String[] Args = pop_args(Line);
			
			InstructionToExecute.setArgs( Args );
			InstructionToExecute.run(this);
			
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


	public String toString() {
		
		StringBuilder debugString = new StringBuilder();
		
		debugString.append("CPU Dump: \n");
		
		int registerNumber = 1;
		for(byte register : this.r_registers) {
			
			debugString.append("r" + registerNumber + ": 0x");
			debugString.append(register);
			debugString.append("  ");

			if(registerNumber % 6 == 0) {
				debugString.append("\n");
			}
			
			registerNumber++;
			
		}
		
		return debugString.toString();
		
	}

	@Override
	public void setRegister(String register, byte value) {
		
		switch (register) {
			case "r29":
				this.r_registers[28] = value;
			
		}
		
	}
	
	@Override
	public byte getRegister(String register) {
		
		switch (register) {
		case "r29":
			return this.r_registers[29];
		
		}
		return 0;
	}
	
public static void main(String[] args) {
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		String [][] instructions = { {"INC", "r29" } };
		
		ArduinoUno.run(instructions, true);

	}




}
