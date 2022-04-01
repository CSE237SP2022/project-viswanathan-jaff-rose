package Interpreter;

public class Main {
	
	public static void main(String[] args) {
		
		if(args.length < 1) {
			System.out.println("Please specify Assembly file");
		}
		else {
			
			ASMFileReader AFR = new ASMFileReader(args[0]);
			
			
			AFR.read();
			
			AbstractCPU ArduinoUno = new ATmega328PCPU();
			
			System.out.println(AFR.getAllParsedLines());
			
			ArduinoUno.run(AFR.getAllParsedLines(), true);
		
		}
		
	}
	

}
