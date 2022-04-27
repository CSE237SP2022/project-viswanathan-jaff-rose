package Interpreter;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class ATmega328PCPUState extends AbstractCPUState {

	protected HashMap<String, Integer> registers;

	private long programCounter;
	
	private LinkedList<Integer> memoryStack;

	public ATmega328PCPUState() {
		this.registers = new HashMap<String, Integer>();
		this.memoryStack = new LinkedList<Integer>();
		setDefault();
	}

	public ATmega328PCPUState(ATmega328PCPUState currentState) {
		this.registers = new HashMap<String, Integer>(currentState.getRegisters());
		this.memoryStack = new LinkedList<Integer> ();

	}

	public ATmega328PCPUState(HashMap<String, Integer> registers2, long programCounter2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setDefault() {
		// TODO Auto-generated method stub
		String[] rRegisterArray = { "r16", "r17", "r18", "r19", "r20", "r21", "r22", "r23", "r24", "r25", "r26", "r27",
				"r28", "r29", "r30", "r31", "r32" };

		this.registers.put("C", 0);
		this.registers.put("Z", 0);
		this.registers.put("N", 0);
		this.registers.put("V", 0);
		this.registers.put("S", 0);
		this.registers.put("H", 0);
		this.registers.put("T", 0);
		this.registers.put("I", 0);
		this.registers.put("SREG", 0);

		for (String rRegister : rRegisterArray) {

			this.registers.put(rRegister, 0);

		}

		this.programCounter = 0;
	}

	public long getProgramcounter() {
		return programCounter;
	}

	public void setProgramcounter(long programcounter) {
		this.programCounter = programcounter;
	}

	public HashMap<String, Integer> getRegisters() {
		return this.registers;
	}
	
	public LinkedList<Integer> getMemoryStack(){
		return this.memoryStack;
	}
	
	public void pushStack(Integer value) {
		this.memoryStack.push(value);
	}
	
	public Integer popStack() {
		return this.memoryStack.pop();
	}
	
	public Integer peekStack() {
		return this.memoryStack.peekFirst();
	}

	public String toString() {
		StringBuilder debugString = new StringBuilder();

		StringBuilder specialRegs = new StringBuilder();

		debugString.append("Registers: \n");

		int[] rRegcounter = { 0 };

		int[] sRegCounter = { 0 };

		this.registers.forEach((key, value) -> {

			if (key.substring(0, 1).equals("r")) {
				debugString.append(key + ": 0x");
				
				String longbyte = String.format("%02X ", value);
				
				debugString.append(longbyte.substring(longbyte.length()-3, longbyte.length()-1));
				debugString.append("  ");

				if (rRegcounter[0] % 6 == 5) {
					debugString.append("\n");
				}
				rRegcounter[0]++;

			} else {
				specialRegs.append(key + ": 0x");
				String longbyte = String.format("%02X ", value);
				
				specialRegs.append(longbyte.substring(longbyte.length()-3, longbyte.length()-1));
				specialRegs.append("  ");

				if (sRegCounter[0] % 6 == 5) {
					specialRegs.append("\n");
				}
				sRegCounter[0]++;
			}

		});

		debugString.append("\n Special Reigsters: \n");

		debugString.append(specialRegs);

		return debugString.toString();
	}

	public void setRegister(String register, int value) throws Exception {
		
		if(this.registers.containsKey(register)) {
			this.registers.put(register, value);
			return;
		}

		throw new Exception("Attempted to Write to Invalid Register '" + register + "'");
	
	}

	public Integer getRegister(String register) {
		return this.registers.get(register);
	}



}
