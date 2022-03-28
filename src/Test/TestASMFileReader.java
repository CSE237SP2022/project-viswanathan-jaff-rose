package Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import Interpreter.ASMFileReader;

public class TestASMFileReader {
	
	@Test
	void test_constructor_no_filepath() {
		try {
			ASMFileReader AFR = new ASMFileReader();
		} catch (Exception e) {
			fail("Unable to construct empty ASMFileReader");
		}
	}
	
	@Test
	void test_constructor_filepath() {
		try {
			ASMFileReader AFR = new ASMFileReader("myAsmFile.S");
			
			assertTrue(AFR.getFilepath().equals("myAsmFile.S"));
			
			AFR.read();
			
		} catch (Exception e) {
			fail("Unable to construct ASMFileReader with fed in filepath");
		}
	}
	
	@Test
	void test_read_file() {
		try {
			ASMFileReader AFR = new ASMFileReader("myAsmFile.S");
			
			assertTrue(AFR.getFilepath().equals("myAsmFile.S"));
			
			AFR.read();
			
		} catch (Exception e) {
			fail("Unable to construct ASMFileReader with fed in filepath");
		}
	}
	
	

}