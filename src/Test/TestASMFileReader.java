package Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
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
	
	@Test
	void test_parse_line() {
		ASMFileReader AFR = new ASMFileReader();
		String lineToParse = "ADD r24, r25";
		String[] completedParse = {"ADD", "r24", "r25"};
		
		String[] returnedArray = AFR.parseAssemblyLine(lineToParse);
		
		for( int i = 0; i < completedParse.length; i++ ) {
			assertTrue(completedParse[i].equals(returnedArray[i]));
		}
		
		
		
	}
	
	@Test
	void test_parse_all_lines() {
		
		try {
			
			ASMFileReader AFR = new ASMFileReader("src/Test/Test.S");
			AFR.read();    
			String[] completedParseOne= {"ADD", "r24", "r25"};
			String[] completedParseTwo = {"INC", "r23"};
			LinkedList<String[]> listOfParsedLines = new LinkedList<>();
			listOfParsedLines.add(completedParseOne);
			listOfParsedLines.add(completedParseTwo);
			
			AFR.parseAllAssemblyLines();
			
			LinkedList<String[]> returnedLines = AFR.getAllParsedLines();
			
			
			for( int i = 0; i < returnedLines.size(); i++ ) {
				
				for( int j = 0; j < returnedLines.get(i).length; j++ ) {
					
					assertTrue( listOfParsedLines.get(i)[j].equals(returnedLines.get(i)[j]));
				}
			}
			
			
		} catch (Exception e) {
			fail("Error occured during file reading");
		}
		
			
		
		
	}
	
	

}
