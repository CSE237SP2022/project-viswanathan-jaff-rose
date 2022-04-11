package Atmega328CPUTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Atmega328CPUInstructions.PrintRegs;
import Interpreter.ASMFileReader;
import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;

public class TestPrintRegs {
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}


	@Test
	void testPrintRegsHWExceptionHandling() {
		PrintRegs pr = new PrintRegs();
		
		try {
			pr.run(null, false);
			
			fail("PrintRegs was able to be executed as a hardware instruction");
			
		} catch (Exception e) {

		}
	}


}
