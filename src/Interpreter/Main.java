package Interpreter;

public class Main {
	
	public static void main(String[] args) {
		
		boolean debugMode = false;
		
		if(args.length < 1) {
			System.out.println("\n"
					+ "Usage:\n"
					+ "  javaduino <AssemblyFile>\n"
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
		
		ASMFileReader AFR = new ASMFileReader(args[0]);
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		System.out.println(AFR.getAllParsedLines());
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines(), debugMode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		
	}
	

}
