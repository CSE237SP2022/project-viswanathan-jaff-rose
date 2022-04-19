package Interpreter;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class AbstractCPU {

	protected AbstractCPUState currentState;
	
	protected LinkedList CPUStates;
	
	protected boolean debugFlag;

	public abstract void run(String functionName) throws Exception;
	
	public abstract void setRegister(String register, Integer value) throws Exception;

	public abstract Integer getRegister(String register);
	
	public void enableDebug(boolean isEnabled) {
		this.debugFlag = isEnabled;
	}

	public abstract void loadProgram(HashMap<String, LinkedList<String[]>> allParsedLines);

}
