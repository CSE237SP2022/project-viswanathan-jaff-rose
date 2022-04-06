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
	void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() {
		
		ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/PrintRegsBasic.S");
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			e.printStackTrace();
		}
	        
	   assertEquals("@@printregs\nRegisters: \n"
	   		+ "r30: 0x00   r32: 0x00   r31: 0x00   r16: 0x00   r18: 0x00   r17: 0x00   \n"
	   		+ "r19: 0x00   r21: 0x00   r20: 0x00   r23: 0x00   r22: 0x00   r25: 0x00   \n"
	   		+ "r24: 0x00   r27: 0x00   r26: 0x00   r29: 0x03   r28: 0x00   \n"
	   		+ " Special Reigsters: \n"
	   		+ "C: 0x00   H: 0x00   I: 0x00   N: 0x00   S: 0x00   T: 0x00   \n"
	   		+ "V: 0x00   Z: 0x00   SREG: 0x00", outputStreamCaptor.toString().trim());
	   
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
