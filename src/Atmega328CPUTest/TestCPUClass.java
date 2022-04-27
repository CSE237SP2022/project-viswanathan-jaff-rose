package Atmega328CPUTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import Atmega328CPUInstructions.*;
import Interpreter.ASMFileReader;
import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;
import Interpreter.AbstractInstruction;
import Interpreter.AssemblyParserException;

public class TestCPUClass {

	@Test
	void testCPUEmptyConstructorNoFail() {
		
		ATmega328PCPU ArduinoUno = new ATmega328PCPU();
		
	}
	
	@Test
	void testCPUHasAllRegisters() {
		
		ATmega328PCPU ArduinoUno = new ATmega328PCPU();
		
		HashMap<String, Integer> expectedRegisternMap =  new HashMap<String, Integer>();
		
		String [] rRegisterArray = {"r16","r17","r18","r19","r20","r21","r22","r23","r24","r25","r26","r27","r28","r29","r30","r31","r32"};
		
		expectedRegisternMap.put("C",  0);
		expectedRegisternMap.put("Z",   0);
		expectedRegisternMap.put("N",   0);
		expectedRegisternMap.put("V",   0);
		expectedRegisternMap.put("S",   0);
		expectedRegisternMap.put("H",   0);
		expectedRegisternMap.put("T",   0);
		expectedRegisternMap.put("I",   0);
		expectedRegisternMap.put("SREG",   0);
		
		for(String rRegister : rRegisterArray) {
		
			expectedRegisternMap.put(rRegister,   0);

		}
		
		assertTrue(expectedRegisternMap.equals(ArduinoUno.getRegisterMap()));
	}
	
	
	@Test
	void testCPUGetandSetRegisterSimple() {
		ATmega328PCPU ArduinoUno = new ATmega328PCPU();
		
		try {
			ArduinoUno.setRegister("r29", 0x8E);

		} catch (Exception e) {
			fail("Failed to set register");
		}
		
		if(ArduinoUno.getRegister("r29") != 0x8E) {

			fail("Unable to get correct register value");
		}
		
	
		
		
	}
	
	@Test
	void testCPUSetInvalidRegister() {
		
		ATmega328PCPU ArduinoUno = new ATmega328PCPU();
		
		try {
			ArduinoUno.setRegister("ZMM0", 0x7B);

			fail("Allowed Register to be written to invalid ZMM0 register");
		} catch (Exception e) {

		}
	}
	
	@Test
	void testCPURunInvalidFunction() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/CALL/CALLInvalidFunction.S");
		
		try {
			AFR.read();
		} catch (FileNotFoundException | AssemblyParserException e1) {
			e1.printStackTrace();
			return;
		}
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run("ThisFunctionDoesNotExist");
			fail("Allowed an invalid function to run");
		} catch (Exception e) {
			
		}
		
	}
	
	

}
