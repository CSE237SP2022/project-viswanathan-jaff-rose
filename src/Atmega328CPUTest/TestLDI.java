package Atmega328CPUTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import Interpreter.ASMFileReader;
import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;

public class TestLDI {

	@Test
	void testLDI_SimpleHex() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/LDISimpleHex.S");
		AFR.read();
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		System.out.println(AFR.getAllParsedLines());
		ArduinoUno.enableDebug(true);
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(0x05, ArduinoUno.getRegister("r29"));
		
	}
	
	@Test
	void testLDI_SimpleBinary() {
	
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/LDISimpleBinary.S");
		AFR.read();
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		System.out.println(AFR.getAllParsedLines());
		ArduinoUno.enableDebug(true);
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(0b01110101, ArduinoUno.getRegister("r29"));
		
	}
	
	@Test
	void testLDI_SimpleDecimal() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/LDISimpleDecimal.S");
		AFR.read();
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		System.out.println(AFR.getAllParsedLines());
		ArduinoUno.enableDebug(true);
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(20, ArduinoUno.getRegister("r29"));
		
	}
	
	@Test
	void testLDI_SimpleOctal() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/LDISimpleOctal.S");
		AFR.read();
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		System.out.println(AFR.getAllParsedLines());
		ArduinoUno.enableDebug(true);
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(023, ArduinoUno.getRegister("r29"));
		
	}
	
	@Test
	void testLDI_InvalidHex() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/LDIInvalidHex.S");
		AFR.read();
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		System.out.println(AFR.getAllParsedLines());
		ArduinoUno.enableDebug(true);
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
			fail("Allowed for invalid hex value to be set");
		} catch (Exception e) {

		}
		
	}
	
	@Test
	void testLDI_InvalidBinary() {
	
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/LDIInvalidBinary.S");
		AFR.read();
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		System.out.println(AFR.getAllParsedLines());
		ArduinoUno.enableDebug(true);
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
			fail("Allowed for invalid binary value to be set");
		} catch (Exception e) {

		}
		
	}
	
	@Test
	void testLDI_InvalidDecimal() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/LDIInvalidDecimal.S");
		AFR.read();
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		System.out.println(AFR.getAllParsedLines());
		ArduinoUno.enableDebug(true);
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
			fail("Allowed for invalid decimal value to be set");
		} catch (Exception e) {

		}
		
	}
	
	@Test
	void testLDI_InvalidOctal() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/LDIInvalidOctal.S");
		AFR.read();
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		System.out.println(AFR.getAllParsedLines());
		ArduinoUno.enableDebug(true);
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
			fail("Allowed for invalid octal value to be set");
		} catch (Exception e) {
			
		}
		
	}
	

}
