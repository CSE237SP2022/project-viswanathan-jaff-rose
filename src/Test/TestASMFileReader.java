package Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
			ASMFileReader AFR = new ASMFileReader("src/Test/Test.S");
			
			assertTrue(AFR.getFilepath().equals("src/Test/Test.S"));
			
		} catch (Exception e) {
			fail("Unable to construct ASMFileReader with fed in filepath");
		}
	}
	
	@Test
	void test_read_file() {
		try {
			ASMFileReader AFR = new ASMFileReader("src/Test/Test.S");
			
			AFR.read();
	            
			Scanner sc = new Scanner(new File("src/Test/Test.S"));
			
			String comp1 = sc.nextLine();
			
			String comp2 = AFR.getIndividualASMline(0);
			
			assertTrue(comp1.equals(comp2));
			
			
		} catch (Exception e) {
			fail("Error occured during file reading");
		}
	}
	
	

}
