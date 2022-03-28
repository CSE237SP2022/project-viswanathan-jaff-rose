package Interpreter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ASMFileReader {
	
	private String filepath;
	
	private File file;
	
	private LinkedList<String> assemblyLines;
	
	public ASMFileReader() {
		this.assemblyLines = new LinkedList<String>();
	}
	
	public ASMFileReader(String filename) {
		
		this.file = new File(filename);
		this.filepath = filename;
		this.assemblyLines = new LinkedList<String>();
		
	}
	
	public String getFilepath() {
		return this.filepath;
	}
	
	public void loadFile(String filename) {
		
		this.file = new File(filename);
		
		try {
            Scanner sc = new Scanner(file);
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
            Scanner sc = new Scanner(file);
            String fileName = file.toString();
            boolean validFile = false;
            int index = fileName.lastIndexOf('.');

            if(index > 0) {
                String extension = fileName.substring(index + 1);
                if(extension.equals("S")){
                    validFile = true;
                }
            }

            if(!validFile){
                System.out.println("INVALID FILE TYPE!");
            }

            while (sc.hasNextLine()) {
            	String line = sc.nextLine();
                this.assemblyLines.add(line);
                System.out.println(line);
            }
            sc.close();

        } catch (Exception e){
            System.out.println("File not found");
        }
		
	}

    public static void main(String[] args){

        

        
        
    }
}