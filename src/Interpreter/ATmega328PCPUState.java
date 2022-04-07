package Interpreter;

import java.util.HashMap;
import java.util.Map;

public class ATmega328PCPUState extends AbstractCPUState {

	protected HashMap<String, Byte> registers;

	private long programCounter;

	public ATmega328PCPUState() {
		this.registers = new HashMap<String, Byte>();
		setDefault();
	}

	public ATmega328PCPUState(ATmega328PCPUState currentState) {
		this.registers = new HashMap<String, Byte>(currentState.getRegisters());
	}

	public ATmega328PCPUState(HashMap<String, Byte> registers2, long programCounter2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setDefault() {
		// TODO Auto-generated method stub
		String[] rRegisterArray = { "r16", "r17", "r18", "r19", "r20", "r21", "r22", "r23", "r24", "r25", "r26", "r27",
				"r28", "r29", "r30", "r31", "r32" };

		this.registers.put("C", (byte) 0);
		this.registers.put("Z", (byte) 0);
		this.registers.put("N", (byte) 0);
		this.registers.put("V", (byte) 0);
		this.registers.put("S", (byte) 0);
		this.registers.put("H", (byte) 0);
		this.registers.put("T", (byte) 0);
		this.registers.put("I", (byte) 0);
		this.registers.put("SREG", (byte) 0);

		for (String rRegister : rRegisterArray) {

			this.registers.put(rRegister, (byte) 0);

		}

		this.programCounter = 0;
	}

	public long getProgramcounter() {
		return programCounter;
	}

	public void setProgramcounter(long programcounter) {
		this.programCounter = programcounter;
	}

	public HashMap<String, Byte> getRegisters() {
		return this.registers;
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
				debugString.append(String.format("%02X ", value));
				debugString.append("  ");

				if (rRegcounter[0] % 6 == 5) {
					debugString.append("\n");
				}
				rRegcounter[0]++;

			} else {
				specialRegs.append(key + ": 0x");
				specialRegs.append(String.format("%02X ", value));
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

	public void setRegister(String register, byte value) {
		this.registers.put(register, value);
		return;
		
	}

	public byte getRegister(String register) {
		return this.registers.get(register);
	}



}
