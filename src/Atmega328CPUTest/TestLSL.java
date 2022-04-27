package Atmega328CPUTest;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

import Interpreter.ASMFileReader;
import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;
import Interpreter.AssemblyParserException;

public class TestLSL {
	
	@Test
	void testLSL_Simple() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/LSL/LSL_Simple.S");
		
		try {
			AFR.read();
		} catch (FileNotFoundException | AssemblyParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("Exception Occured During Parsing");
		}
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		System.out.println(AFR.getAllParsedLines());
		
		try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run("main");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(ArduinoUno.getRegister("r29"), 32);
		
		
	}
	
	@Test
	void testLSL_Overflow() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/LSL/LSLOverflow.S");
		
		try {
			AFR.read();
		} catch (FileNotFoundException | AssemblyParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("Exception Occured During Parsing");
		}
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		System.out.println(AFR.getAllParsedLines());
		
		try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run("main");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(ArduinoUno.getRegister("r29"), 160);
		assertEquals(ArduinoUno.getRegister("V"), 1);
		
	}
	
	

}
