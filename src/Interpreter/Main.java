package Interpreter;

public class Main {
	
	public static void main(String[] args) {
		
		
		ASMFileReader avr = new ASMFileReader(args[0]);
		
		avr.read();
		
	}
	

}
