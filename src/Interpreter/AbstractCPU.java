package Interpreter;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class AbstractCPU {

	public HashMap<String, Byte> registers;

	public abstract void run(LinkedList<String[]> instructions, boolean debugMode) throws Exception;
	
	public abstract void setRegister(String register, byte value);

	public abstract byte getRegister(String register);

}
