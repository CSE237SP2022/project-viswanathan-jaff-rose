package Interpreter;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class AbstractCPU {

	public HashMap<String, Byte> registers;
	
	protected boolean debugFlag;

	public abstract void run(LinkedList<String[]> instructions) throws Exception;
	
	public abstract void setRegister(String register, byte value);

	public abstract byte getRegister(String register);
	
	public void enableDebug(boolean isEnabled) {
		this.debugFlag = isEnabled;
	}

}
