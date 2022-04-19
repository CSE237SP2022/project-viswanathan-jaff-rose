package Interpreter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class ASMFileReader {
	
	private String filepath;
	
	private File file;
	
	private LinkedList<String> assemblyLines;
	
	private String currentFunctionIdentifier;
	
	private boolean isParsingAFunction;
	
	private LinkedList<String[]> currentFunction;
	
	private HashMap<String, LinkedList<String[]>> parsedAssembly;
	
	public ASMFileReader() {
		this.assemblyLines = new LinkedList<String>();
		this.parsedAssembly = new HashMap<String, LinkedList<String[]>>();
		this.isParsingAFunction = false;
	}
	
	public ASMFileReader(String filename) {
		
		this.file = new File(filename);
		this.filepath = filename;
		this.assemblyLines = new LinkedList<String>();
		this.currentFunction = new LinkedList<String []>();
		this.parsedAssembly = new HashMap<String, LinkedList<String[]>>();
		this.isParsingAFunction = false;
	}
	
	public String getIndividualASMline(int lineNumber) {
		return assemblyLines.get(lineNumber);
	}
	
	public String getFilepath() {
		return this.filepath;
	}
	
	public void read() {
		
		try {
	            Scanner sc = new Scanner(this.file);
	            
	            extractAndValidateFile(sc);

	            while (sc.hasNextLine()) {
	            	String line = sc.nextLine();
	            	if ( !line.equals(" ") ) {
	            		this.assemblyLines.add(line);
	            	}
	                System.out.println(line);
	            }
	            sc.close();
	            
	            parseAllAssemblyLines();

        } catch (Exception e){
            System.out.println(e);
        }
		
	}

	private void extractAndValidateFile(Scanner sc) throws Exception {
		String fileName = this.file.toString();
		boolean validFile = false;
		int index = fileName.lastIndexOf('.');

		if(index > 0) {
		    String extension = fileName.substring(index + 1);
		    if(extension.equals("S")){
		        validFile = true;
		    }
		}

		if(!validFile){
			sc.close();
		    throw new Exception("INVALID FILE TYPE");
		}
	}

	public String[] parseAssemblyLine(String lineOfAssembly ) {
		
		if(lineOfAssembly.isBlank()) {
			String [] blank = {""};
			return blank;
		}
		
		int indexOfString = 0;
		String opCode = "";
		
		while(lineOfAssembly.charAt(indexOfString) != ' ') {
			opCode += lineOfAssembly.charAt(indexOfString);
			indexOfString++;
			if(lineOfAssembly.length() == indexOfString) {
				break;
			}
			
		}
		
		lineOfAssembly = lineOfAssembly.substring(indexOfString).replaceAll(" ", "");
		String[] arrayOfParameters = lineOfAssembly.split(",");
		String[] arrayOfSeperateOpcodeAndParameters = new String[arrayOfParameters.length + 1];
		arrayOfSeperateOpcodeAndParameters[0] = opCode;
		
		for( int i = 0; i < arrayOfParameters.length; i++ ) {
			arrayOfSeperateOpcodeAndParameters[i+1] = arrayOfParameters[i];
		}
		return arrayOfSeperateOpcodeAndParameters;
	}
	
	public void parseAllAssemblyLines() {
		for( int i = 0; i < assemblyLines.size(); i++ ) {
			String[] parsedLine = parseAssemblyLine(assemblyLines.get(i));
			
			if(!parsedLine[0].isBlank()) {
				
				determineLine(parsedLine);
				
			}
			
		}
		
		if(this.isParsingAFunction) {
			endFunctionParse(this.currentFunctionIdentifier);
		}
		
		
	}
	
	private void determineLine(String [] parsedLine) {
		
		if(parsedLine[0].equals(".global")) {
			
			try {
				addGlobalLabel(parsedLine);
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
		else if(parsedLine[0].contains(":")) {
			try {
				initiateFunctionDefinition(parsedLine[0]);
			} 
			catch (AssemblyParserException e) {
				System.out.println(e);
			}
		}
		else {
			
			currentFunction.add(parsedLine);
		
		}
		
	}

	private void initiateFunctionDefinition(String parsedLabel) throws AssemblyParserException {
		if( !this.parsedAssembly.containsKey(parsedLabel)) {
			throw new AssemblyParserException("Missing Forward Delcaration of assembly function " + parsedLabel);
		}
		else {
			if(this.isParsingAFunction) {
				endFunctionParse(this.currentFunctionIdentifier);
			}
			else {
				startFunctionParse(parsedLabel);
			}
		}
		
	}

	private void addGlobalLabel(String [] parsedLine) throws AssemblyParserException {
		
		if(parsedLine.length < 2) {
			throw new AssemblyParserException("No Label for global function specified");
		}
		
		if(parsedLine[1].isBlank()) {
			throw new AssemblyParserException("Blank Label for global function specified");
		}
		
		if(this.isParsingAFunction) {
			endFunctionParse(this.currentFunctionIdentifier);
		}

	}

	private void endFunctionParse(String functionname) {
		
		LinkedList<String []> ClonedASMFunction = new LinkedList<String []>(this.currentFunction);
		
		parsedAssembly.put(functionname, ClonedASMFunction);
		
		this.currentFunctionIdentifier = functionname;
		
		this.currentFunction = new LinkedList<String[]>();
		
		this.isParsingAFunction = false;
		
	}
	
	private void startFunctionParse(String functionname) {
		
		this.currentFunctionIdentifier = functionname;
		
		this.currentFunction = new LinkedList<String[]>();
		
		parsedAssembly.put(functionname, null);
		
		this.isParsingAFunction = true;
	}
	

	public HashMap<String, LinkedList<String[]>> getAllParsedLines() {
		return this.parsedAssembly;
	}

    public static void main(String[] args){
    	
    	ASMFileReader AFR = new ASMFileReader(args[0]);
		
		AFR.read();
        
    }
}