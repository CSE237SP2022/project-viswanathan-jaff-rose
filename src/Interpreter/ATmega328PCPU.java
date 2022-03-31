package Interpreter;

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
	
	private void create_opcode_map() {
		
		this.instructionMap.put("INC", new INC());
	}
	
	
	public ATmega328PCPU() {
		this.r_registers = new byte[30];
		
		this.instructionMap = new HashMap<String, AbstractInstruction>();
		
		create_opcode_map();
		
	}
	
	public void run(String[][] instructions, boolean debugMode) {
		
		for(String [] Line : instructions) {
			
			AbstractInstruction InstructionToExecute = instructionMap.get(Line[0]);
			InstructionToExecute.run();
			
			if(debugMode) {
				System.out.println(this.toString());
			}
			
		}
		
		
		
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

	public static void main(String[] args) {
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		String [][] instructions = { {"INC", "r29" } };
		
		ArduinoUno.run(instructions, true);

	}

}
