package Interpreter;

public class Main {
	
	public static void main(String[] args) {
		
		boolean debugMode = false;
		
		System.out.println("\n"
				+ "Usage:\n"
				+ "  javaduino <AssemblyFile>\n"
				+ "  naval_fate --version\n"
				+ "\n"
				+ "Options:\n"
				+ "  -d            Invoke Debug Mode.\n"
				);
		
		if(args.length < 1) {
			System.out.println("\n"
					+ "Usage:\n"
					+ "  javaduino <AssemblyFile>\n"
					+ "  naval_fate --version\n"
					+ "\n"
					+ "Options:\n"
					+ "  -d            Invoke Debug Mode.\n"
					);
			return;
		}
		
		if(args.length >= 2) {
			
			if(args[1].equals("-d")) {
				
				debugMode = true;
				
				System.out.println("Debug Mode Enabled");
				
			}
			
			
		}
		
		
		
		else {
			
			ASMFileReader AFR = new ASMFileReader(args[0]);
			
			
			AFR.read();
			
			AbstractCPU ArduinoUno = new ATmega328PCPU();
			
			System.out.println(AFR.getAllParsedLines());
			
			ArduinoUno.run(AFR.getAllParsedLines(), debugMode);
		
		}
		
	}
	

}
