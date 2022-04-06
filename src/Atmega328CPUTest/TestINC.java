package Atmega328CPUTest;

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
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(ArduinoUno.getRegister("r29"), 1);
		
		
	}
	
	@Test
	void testINC_Double_Increment() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/INCDoubleINC.S");
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		System.out.println(AFR.getAllParsedLines());
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(ArduinoUno.getRegister("r29"), 2);
		
	}
	
	@Test
	void testINC_Multiple_Registers() {
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/INCMultiRegs.S");
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		System.out.println(AFR.getAllParsedLines());
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(1, ArduinoUno.getRegister("r27"));
		assertEquals(1, ArduinoUno.getRegister("r28"));
		assertEquals(1, ArduinoUno.getRegister("r29"));
		
	}
	
	@Test
	void testINC_All_Registers() {
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/INCAllRegs.S");
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		System.out.println(AFR.getAllParsedLines());
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(1, ArduinoUno.getRegister("r16"));
		assertEquals(1, ArduinoUno.getRegister("r17"));
		assertEquals(1, ArduinoUno.getRegister("r18"));
		assertEquals(1, ArduinoUno.getRegister("r19"));
		assertEquals(1, ArduinoUno.getRegister("r20"));
		assertEquals(1, ArduinoUno.getRegister("r21"));
		assertEquals(1, ArduinoUno.getRegister("r22"));
		assertEquals(1, ArduinoUno.getRegister("r23"));
		assertEquals(1, ArduinoUno.getRegister("r24"));
		assertEquals(1, ArduinoUno.getRegister("r25"));
		assertEquals(1, ArduinoUno.getRegister("r26"));
		assertEquals(1, ArduinoUno.getRegister("r27"));
		assertEquals(1, ArduinoUno.getRegister("r28"));
		assertEquals(1, ArduinoUno.getRegister("r29"));
		
	}
	
	@Test
	void testINC_Overflow() {
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/INCOverflow.S");
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(ArduinoUno.getRegister("V"), 1);
		assertEquals(ArduinoUno.getRegister("r29"), -128);
	
	}

}
