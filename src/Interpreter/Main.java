package Interpreter;

public class Main {
	
	public static void main(String[] args) {
		
		boolean verboseMode = false;
		
		String functionToRun = "main";
		
		if(args.length < 1) {
			printUsage();
			return;
		}
		
		if(args.length >= 2) {
			
			for(int i=1; i< args.length; i++) {
				if(args[i].equals("-v")) {
					verboseMode = true;
				}
				if(args[i].equals("-f")) {
					if(i+1 >= args.length) {
						System.out.println(new IllegalArgumentException("Custom function to run not specified"));
						return;
					}
					functionToRun = args[i+1];
					
				}
				
			}
			
		}
		
		if(verboseMode) {
			System.out.println("Verbose Mode Enabled");
			
			if(functionToRun.equals("main")) {
				System.out.println("Running custom function: " + functionToRun);
			}
		}
		
		ASMFileReader AFR = new ASMFileReader(args[0]);
		
		AFR.read();
		
		AbstractCPU ArduinoUno = new ATmega328PCPU();
		
		ArduinoUno.enableDebug(verboseMode);
		
		try {
			ArduinoUno.loadProgram(AFR.getAllParsedLines());
			ArduinoUno.run(functionToRun);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printUsage() {
		System.out.println("\n"
				+ "Usage:\n"
				+ "  javaduino <AssemblyFile> [options]\n"
				+ "  javaduino <AssemblyFile> -f <AssemblyFunction> [options]\n"
				+ "\n"
				+ "Options:\n"
				+ "  -d            Invoke Debug Mode.\n"
				+ "  -f            Invoke custom assembly function other than main\n"
				);
	}
	

}
