package Atmega328CPUTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Interpreter.ASMFileReader;
import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;
import Interpreter.AssemblyParserException;

public class TestPUSH {

	@Test
    void testPUSH_Simple() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/PUSH/PUSHSimple.S");
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
		}
        
		int reg = ArduinoUno.getRegister("r29");
		
		LinkedList<Integer> memstack = ArduinoUno.getMemStack();
		
        assertEquals(memstack.getFirst(), reg);

    }
	
	@Test
    void testPUSH_Multiple() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/PUSH/PUSHmultiple.S");
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
		}
		
		LinkedList<Integer> memstack = ArduinoUno.getMemStack();
        
		int Firstreg = ArduinoUno.getRegister("r29");
		
        assertEquals(memstack.pop(), Firstreg);
        
        int Secondreg = ArduinoUno.getRegister("r28");
		
        assertEquals(memstack.pop(), Secondreg);

    }

}
