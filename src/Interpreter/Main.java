package Interpreter;

public class Main {
	
	public static void main(String[] args) {
		
		boolean debugMode = false;
		
		if(args.length < 1) {
			printUsage();
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
		
		ArduinoUno.enableDebug(debugMode);
		
		try {
			ArduinoUno.run(AFR.getAllParsedLines());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printUsage() {
		System.out.println("\n"
				+ "Usage:\n"
				+ "  javaduino <AssemblyFile> [options]\n"
				+ "\n"
				+ "Options:\n"
				+ "  -d            Invoke Debug Mode.\n"
				);
	}
	

}
