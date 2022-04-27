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

public class TestSUBI {

    @Test
    void testSUBI_Simple() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/SUBISimple.S");
        try {
            AFR.read();
        } catch (FileNotFoundException | AssemblyParserException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            fail("Exception Occured During Parsing");
        }
        AbstractCPU ArduinoUno = new ATmega328PCPU();

        //tests subi after LDI
        int oldDest = ArduinoUno.getRegister("r29")+40;
        try {
            ArduinoUno.loadProgram(AFR.getAllParsedLines());
            ArduinoUno.run("main");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //checks if sub results in correct value
        int correctVal = oldDest - 4;
        assertEquals(ArduinoUno.getRegister("r29"), correctVal);
    }

    @Test
    void testSUBI_AllRegs() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/SUBIFull.S");
        try {
            AFR.read();
        } catch (FileNotFoundException | AssemblyParserException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            fail("Exception Occured During Parsing");
        }
        AbstractCPU ArduinoUno = new ATmega328PCPU();
        int old16 = ArduinoUno.getRegister("r16");
        int old17 = ArduinoUno.getRegister("r17");
        int old18 = ArduinoUno.getRegister("r18");
        int old19 = ArduinoUno.getRegister("r19");
        int old20 = ArduinoUno.getRegister("r20");
        int old21 = ArduinoUno.getRegister("r21");
        int old22 = ArduinoUno.getRegister("r22");
        int old23 = ArduinoUno.getRegister("r23");
        int old24 = ArduinoUno.getRegister("r24");
        int old25 = ArduinoUno.getRegister("r25");
        int old26 = ArduinoUno.getRegister("r26");
        int old27 = ArduinoUno.getRegister("r27");
        int old28 = ArduinoUno.getRegister("r28");
        int old29 = ArduinoUno.getRegister("r29");
        int old30 = ArduinoUno.getRegister("r30");
        int old31 = ArduinoUno.getRegister("r31");

        try {
            ArduinoUno.loadProgram(AFR.getAllParsedLines());
            ArduinoUno.run("main");
        } catch (Exception e) {
            e.printStackTrace();
        }


        assertEquals(ArduinoUno.getRegister("r16"), old16-4);
        assertEquals(ArduinoUno.getRegister("r17"), old17-4);
        assertEquals(ArduinoUno.getRegister("r18"), old18-4);
        assertEquals(ArduinoUno.getRegister("r19"), old19-4);
        assertEquals(ArduinoUno.getRegister("r20"), old20-4);
        assertEquals(ArduinoUno.getRegister("r21"), old21-4);
        assertEquals(ArduinoUno.getRegister("r22"), old22-4);
        assertEquals(ArduinoUno.getRegister("r23"), old23-4);
        assertEquals(ArduinoUno.getRegister("r24"), old24-4);
        assertEquals(ArduinoUno.getRegister("r25"), old25-4);
        assertEquals(ArduinoUno.getRegister("r26"), old26-4);
        assertEquals(ArduinoUno.getRegister("r27"), old27-4);
        assertEquals(ArduinoUno.getRegister("r28"), old28-4);
        assertEquals(ArduinoUno.getRegister("r29"), old29-4);
        assertEquals(ArduinoUno.getRegister("r30"), old30-4);
        assertEquals(ArduinoUno.getRegister("r31"), old31-4);

    }

    @Test
    void testSUBI_Neg() {
        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/SUBINeg.S");
        try {
            AFR.read();
        } catch (FileNotFoundException | AssemblyParserException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            fail("Exception Occured During Parsing");
        }
        AbstractCPU ArduinoUno = new ATmega328PCPU();
        ArduinoUno.enableDebug(true);
        int oldReg = ArduinoUno.getRegister("r20");

        try {
            ArduinoUno.loadProgram(AFR.getAllParsedLines());
            ArduinoUno.run("main");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int correctVal = oldReg+1;
        assertEquals(ArduinoUno.getRegister("r20"), correctVal);
    }

}