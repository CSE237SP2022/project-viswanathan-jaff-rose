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

public class TestADD {

    @Test
    void testADD_Simple() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADD/ADDSimple.S");
        try {
			AFR.read();
		} catch (FileNotFoundException | AssemblyParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("Exception Occured During Parsing");
		}
        AbstractCPU ArduinoUno = new ATmega328PCPU();

        int oldDest = ArduinoUno.getRegister("r29");
		try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run("main");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        //checks if add results in correct value
        int correctVal = ArduinoUno.getRegister("r28") + oldDest;
        assertEquals(ArduinoUno.getRegister("r29"), correctVal);
    }

    @Test
    void testADD_AllRegs() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADD/ADDFull.S");
        try {
			AFR.read();
		} catch (FileNotFoundException | AssemblyParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("Exception Occured During Parsing");
		}
        AbstractCPU ArduinoUno = new ATmega328PCPU();

        //test add for regs 16-31
        for(int i = 16; i < 31; i++){
            String destReg = "r"+i;
            String srcReg = "r"+(i+1);
            int oldDest = ArduinoUno.getRegister(destReg);
    		try {
    			ArduinoUno.loadProgram(AFR.getAllParsedLines());
    			ArduinoUno.run("main");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
            //checks if add value is correct
            int correctVal = ArduinoUno.getRegister(srcReg) + oldDest;
            assertEquals(ArduinoUno.getRegister(destReg), correctVal);
        }
    }

    @Test
    void testADD_Self(){
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADD/ADDSelf.S");
        try {
			AFR.read();
		} catch (FileNotFoundException | AssemblyParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("Exception Occured During Parsing");
		}
        AbstractCPU ArduinoUno = new ATmega328PCPU();

        int oldDest = ArduinoUno.getRegister("r28");
        
		try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run("main");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        //checks if add value is correct
        int correctVal = ArduinoUno.getRegister("r28") + oldDest;
        assertEquals(ArduinoUno.getRegister("r28"), correctVal);
    }

    @Test
    void testADD_TwosComplementOverflow() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADD/ADDTwosComplementOverflow.S");
        try {
			AFR.read();
		} catch (FileNotFoundException | AssemblyParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("Exception Occured During Parsing");
		}
        AbstractCPU ArduinoUno = new ATmega328PCPU();
        ArduinoUno.enableDebug(true);

    	try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run("main");
		} catch (Exception e) {
			e.printStackTrace();
		}

        //check register is over 127 and V is set to 1
        assertEquals(0x8E, ArduinoUno.getRegister("r29"));
        assertEquals(ArduinoUno.getRegister("V"), 1);
    }
    
    @Test
    void testADD_RealOverflow() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADD/ADDRealOverflow.S");
        try {
			AFR.read();
		} catch (FileNotFoundException | AssemblyParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("Exception Occured During Parsing");
		}
        AbstractCPU ArduinoUno = new ATmega328PCPU();
        ArduinoUno.enableDebug(true);

    	try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run("main");
		} catch (Exception e) {
			e.printStackTrace();
		}

        //check register is over 127 and V is set to 1
        assertEquals(0x0E, ArduinoUno.getRegister("r29"));

    }
    
}

