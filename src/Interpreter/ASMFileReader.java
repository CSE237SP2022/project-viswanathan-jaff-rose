package Interpreter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ASMFileReader {
	
	private String filepath;
	
	private File file;
	
	private LinkedList<String> assemblyLines;
	
	private LinkedList<String[]> parsedAssemblyLines;
	
	public ASMFileReader() {
		this.assemblyLines = new LinkedList<String>();
		this.parsedAssemblyLines = new LinkedList<String[]>();
	}
	
	public ASMFileReader(String filename) {
		
		this.file = new File(filename);
		this.filepath = filename;
		this.assemblyLines = new LinkedList<String>();
		this.parsedAssemblyLines = new LinkedList<String[]>();
	}
	
	public String getIndividualASMline(int lineNumber) {
		return assemblyLines.get(lineNumber);
	}
	
	public String getFilepath() {
		return this.filepath;
	}
	
	public void loadFile(String filename) {
		
		this.file = new File(filename);
		
		try {
            Scanner sc = new Scanner(filename);
            String fileName = file.toString();
            boolean validFile = false;
            int index = fileName.lastIndexOf('.');

            if(index > 0) {
                String extension = fileName.substring(index + 1);
                if(!extension.equals("S")){
                	System.out.println("Invalid File Extension: is not a .S file");
                    throw new FileNotFoundException();
                }
            }
            
            this.filepath = filename;
		}
		catch (Exception e){
			
		}
		
	}
	
	public void read() {
		
		try {
            Scanner sc = new Scanner(this.file);
            
            //System.out.println(sc);
            
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
                throw new Exception("INVALID FILE TYPE");
            }

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
	
	public String[] parseAssemblyLine( String lineOfAssembly ) {
		int indexOfString = 0;
		String opCode = "";
		
		
		while( lineOfAssembly.charAt(indexOfString) != ' ' ) {
			opCode += lineOfAssembly.charAt(indexOfString);
			indexOfString++;
			
			if( indexOfString == lineOfAssembly.length()) {
				break;
			}
			
		}
		
		
		lineOfAssembly = lineOfAssembly.substring(indexOfString);
		lineOfAssembly = lineOfAssembly.replaceAll(" ", "");
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
			String[] parsedLine = parseAssemblyLine( assemblyLines.get(i));
			parsedAssemblyLines.add(parsedLine);
		}
	}
	
	public LinkedList<String[]> getAllParsedLines() {
		return this.parsedAssemblyLines;
	}

    public static void main(String[] args){
    	
    	ASMFileReader AFR = new ASMFileReader(args[0]);
		
		AFR.read();
        
    }
}