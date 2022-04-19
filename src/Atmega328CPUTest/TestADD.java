package Atmega328CPUTest;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

import Interpreter.ASMFileReader;
import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;

public class TestADD {

    @Test
    void testADD_Simple() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADDSimple.S");
        AFR.read();
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
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADDFull.S");
        AFR.read();
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
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADDSelf.S");
        AFR.read();
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
    void testADD_Overflow() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADDOverflow.S");
        AFR.read();
        AbstractCPU ArduinoUno = new ATmega328PCPU();
        ArduinoUno.enableDebug(true);

    	try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run("main");
		} catch (Exception e) {
			e.printStackTrace();
		}

        //check register is over 127 and V is set to 1
        assertEquals(ArduinoUno.getRegister("r29"), 0x80);
        assertEquals(ArduinoUno.getRegister("V"), 1);
    }
    
}

