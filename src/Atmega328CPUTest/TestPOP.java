package Atmega328CPUTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Interpreter.ASMFileReader;
import Interpreter.ATmega328PCPU;
import Interpreter.AssemblyParserException;

public class TestPOP {

	@Test
    void testPOP_Simple() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/POP/POPsimple.S");
        try {
			AFR.read();
		} catch (FileNotFoundException | AssemblyParserException e1) {
			e1.printStackTrace();
			fail("Exception Occured During Parsing");
		}
        ATmega328PCPU ArduinoUno = new ATmega328PCPU();
       
		try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run("main");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error during execution");
		}
        
		int reg = ArduinoUno.getRegister("r27");
		
        assertEquals(reg, ArduinoUno.getRegister("r29"));

    }
	
	@Test
    void testPUSH_Multiple() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/POP/POPmultiple.S");
        try {
			AFR.read();
		} catch (FileNotFoundException | AssemblyParserException e1) {
			e1.printStackTrace();
			fail("Exception Occured During Parsing");
		}
        ATmega328PCPU ArduinoUno = new ATmega328PCPU();

       
		try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run("main");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error during execution");
		}
		
        assertEquals(ArduinoUno.getRegister("r27"), ArduinoUno.getRegister("r28"));
        assertEquals(ArduinoUno.getRegister("r26"), ArduinoUno.getRegister("r29"));
		

    }

}
