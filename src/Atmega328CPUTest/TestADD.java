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

//        ASMFileReader AFR = new ASMFileReader("src/Atmega328CPUInstructionsTest/AssemblyFiles/INCSimple.S");
//        AFR.read();
//        AbstractCPU ArduinoUno = new ATmega328PCPU();
//        System.out.println(AFR.getAllParsedLines());
//
//        try {
//            ArduinoUno.run(AFR.getAllParsedLines());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        assertEquals(ArduinoUno.getRegister("r29"), 1);
    }

