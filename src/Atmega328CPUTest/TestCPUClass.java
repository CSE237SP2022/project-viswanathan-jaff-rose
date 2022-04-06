package Atmega328CPUTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import Atmega328CPUInstructions.INC;
import Atmega328CPUInstructions.LDI;
import Interpreter.ASMFileReader;
import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;
import Interpreter.AbstractInstruction;

public class TestCPUClass {

	@Test
	void testCPUEmptyConstructorNoFail() {
		
		ATmega328PCPU ArduinoUno = new ATmega328PCPU();
		
	}
	
	@Test
	void testCPUHasAllRegisters() {
		
		ATmega328PCPU ArduinoUno = new ATmega328PCPU();
		
		HashMap<String, Byte> expectedRegisternMap =  new HashMap<String, Byte>();
		
		String [] rRegisterArray = {"r16","r17","r18","r19","r20","r21","r22","r23","r24","r25","r26","r27","r28","r29","r30","r31","r32"};
		
		expectedRegisternMap.put("C", (byte) 0);
		expectedRegisternMap.put("Z", (byte) 0);
		expectedRegisternMap.put("N", (byte) 0);
		expectedRegisternMap.put("V", (byte) 0);
		expectedRegisternMap.put("S", (byte) 0);
		expectedRegisternMap.put("H", (byte) 0);
		expectedRegisternMap.put("T", (byte) 0);
		expectedRegisternMap.put("I", (byte) 0);
		expectedRegisternMap.put("SREG", (byte) 0);
		
		for(String rRegister : rRegisterArray) {
		
			expectedRegisternMap.put(rRegister, (byte) 0);

		}
		
		assertTrue(expectedRegisternMap.equals(ArduinoUno.getRegisterMap()));
	}
	
	@Test
	void testCPUHasAllSupportedInstructions() {
		
		ATmega328PCPU ArduinoUno = new ATmega328PCPU();
		
		HashMap<String, AbstractInstruction> expectedInstructionMap = new HashMap<String, AbstractInstruction>();	
		
		expectedInstructionMap.put("INC", new INC());
		expectedInstructionMap.put("LDI", new LDI());
		
		System.out.println(expectedInstructionMap.toString());
		System.out.println(ArduinoUno.getSupportedInstructions().toString());

		assertTrue(expectedInstructionMap.equals(ArduinoUno.getSupportedInstructions()));
	}
	
	@Test
	void testCPUGetandSetRegisterSimple() {
		ATmega328PCPU ArduinoUno = new ATmega328PCPU();
		
		try {
			ArduinoUno.setRegister("r29", (byte) 0x8E);
		} catch (Exception e) {
			fail("Failed to set register");
		}
		
		
		if(ArduinoUno.getRegister("r29") != (byte) 0x8E) {
			fail("Unable to get correct register value");
		}
		
	
		
		
	}
	
	@Test
	void testCPUSetInvalidRegister() {
		
		ATmega328PCPU ArduinoUno = new ATmega328PCPU();
		
		try {
			ArduinoUno.setRegister("ZMM0", (byte) 0x7B);
			fail("Allowed Register to be written to invalid ZMM0 register");
		} catch (Exception e) {

		}
		
		
		
		
		
	}
	
	

}
