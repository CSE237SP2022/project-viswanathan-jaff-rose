package Atmega328CPUInstructionsTest;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

import Interpreter.ASMFileReader;
import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;

public class TestINC {
	
	@Test
	void testINC_Simple() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/INCSimple.S");
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		System.out.println(AFR.getAllParsedLines());
		
		ArduinoUno.run(AFR.getAllParsedLines(), false);
		
		assertEquals(ArduinoUno.getRegister("r29"), 1);
		
		
	}
	
	@Test
	void testINC_Double_Increment() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/INCDoubleINC.S");
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		System.out.println(AFR.getAllParsedLines());
		
		ArduinoUno.run(AFR.getAllParsedLines(), false);
		
		assertEquals(ArduinoUno.getRegister("r29"), 2);
		
	}
	
	@Test
	void testINC_Multiple_Registers() {
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/INCMultiRegs.S");
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		System.out.println(AFR.getAllParsedLines());
		
		ArduinoUno.run(AFR.getAllParsedLines(), false);
		
		assertEquals(ArduinoUno.getRegister("r27"), 1);
		assertEquals(ArduinoUno.getRegister("r28"), 1);
		assertEquals(ArduinoUno.getRegister("r29"), 1);
		
	}
	
	@Test
	void testINC_All_Registers() {
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/INCAllRegs.S");
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		System.out.println(AFR.getAllParsedLines());
		
		ArduinoUno.run(AFR.getAllParsedLines(), false);
		
		assertEquals(ArduinoUno.getRegister("r16"), 1);
		assertEquals(ArduinoUno.getRegister("r17"), 1);
		assertEquals(ArduinoUno.getRegister("r18"), 1);
		assertEquals(ArduinoUno.getRegister("r19"), 1);
		assertEquals(ArduinoUno.getRegister("r20"), 1);
		assertEquals(ArduinoUno.getRegister("r21"), 1);
		assertEquals(ArduinoUno.getRegister("r22"), 1);
		assertEquals(ArduinoUno.getRegister("r23"), 1);
		assertEquals(ArduinoUno.getRegister("r24"), 1);
		assertEquals(ArduinoUno.getRegister("r25"), 1);
		assertEquals(ArduinoUno.getRegister("r26"), 1);
		assertEquals(ArduinoUno.getRegister("r27"), 1);
		assertEquals(ArduinoUno.getRegister("r28"), 1);
		assertEquals(ArduinoUno.getRegister("r29"), 1);
		
	}

}
