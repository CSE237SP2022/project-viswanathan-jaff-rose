package Atmega328CPUInstructionsTest;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import Interpreter.ASMFileReader;

public class TestINC {
	
	@Test
	void test_constructor_no_filepath() {
		try {
			ASMFileReader AFR = new ASMFileReader();
		} catch (Exception e) {
			fail("Unable to construct empty ASMFileReader");
		}
	}

}
