package Interpreter;

public abstract class AbstractCPU {
	
	public abstract void run(String[][] instructions, boolean debugMode);
	
	public abstract void setRegister(String register, byte value);

	public abstract byte getRegister(String register);

}
