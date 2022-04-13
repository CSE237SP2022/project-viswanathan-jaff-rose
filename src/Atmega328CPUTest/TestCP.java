package Atmega328CPUTest;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

import Interpreter.ASMFileReader;
import Interpreter.ATmega328PCPU;
import Interpreter.AbstractCPU;

public class TestCP {

    @Test
    void testCP_Equal() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADDSimple.S");
        AFR.read();
        AbstractCPU ArduinoUno = new ATmega328PCPU();
        System.out.println(AFR.getAllParsedLines());

        try {
            ArduinoUno.run(AFR.getAllParsedLines());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int correctVal = ArduinoUno.getRegister("Z");
        assertEquals(1, correctVal);
    }
    
    @Test
    void testCP_LessThan() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADDSimple.S");
        AFR.read();
        AbstractCPU ArduinoUno = new ATmega328PCPU();
        System.out.println(AFR.getAllParsedLines());

 
        try {
            ArduinoUno.run(AFR.getAllParsedLines());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int correctVal = ArduinoUno.getRegister("C");
        assertEquals(1, correctVal);
    }
    
    @Test
    void testCP_GreaterThan() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/ADDSimple.S");
        AFR.read();
        AbstractCPU ArduinoUno = new ATmega328PCPU();
        System.out.println(AFR.getAllParsedLines());

        try {
            ArduinoUno.run(AFR.getAllParsedLines());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int correctVal = ArduinoUno.getRegister("C");
        assertEquals(0, correctVal);
    }

    
    
}

