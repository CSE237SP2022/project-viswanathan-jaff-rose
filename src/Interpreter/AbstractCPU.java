package Interpreter;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class AbstractCPU {

	protected AbstractCPUState currentState;
	
	protected LinkedList CPUStates;
	
	protected boolean debugFlag;

	public abstract void run(LinkedList<String[]> instructions) throws Exception;
	
	public abstract void setRegister(String register, byte value) throws Exception;

	public abstract byte getRegister(String register);
	
	public void enableDebug(boolean isEnabled) {
		this.debugFlag = isEnabled;
	}

}
