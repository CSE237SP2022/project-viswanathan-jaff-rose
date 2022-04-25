package Interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import Atmega328CPUInstructions.*;

public class ATmega328PCPU extends AbstractCPU {

	protected ATmega328PCPUState currentState;

	protected LinkedList<ATmega328PCPUState> CPUStates;

	private HashMap<String, AbstractInstruction> instructionMap;
	
	private HashMap<String, LinkedList<String[]>> program;

	private void create_opcode_map() {

		this.instructionMap.put("INC", new INC());
		this.instructionMap.put("LDI", new LDI());
		this.instructionMap.put("ADD", new ADD());
		this.instructionMap.put("RET", new RET());
		this.instructionMap.put("JMP", new JMP());
		this.instructionMap.put("PUSH", new PUSH());
		this.instructionMap.put("POP", new POP());
		this.instructionMap.put("@@PRINTREGS", new PrintRegs());
	}

	public ATmega328PCPU() {

		this.instructionMap = new HashMap<String, AbstractInstruction>();

		this.CPUStates = new LinkedList<ATmega328PCPUState>();
		
		this.currentState = new ATmega328PCPUState();

		create_opcode_map();

	}

	public ATmega328PCPU(ATmega328PCPU oldcpu) {

		this.currentState = new ATmega328PCPUState(oldcpu.getCurrentState());
		this.instructionMap = new HashMap<String, AbstractInstruction>(oldcpu.getInstructionMap());

	}
	
	public void loadProgram(HashMap<String, LinkedList<String[]>> allParsedLines) {
		
		this.program = allParsedLines;
		
		return;
	}

	private ATmega328PCPUState getCurrentState() {
		return this.currentState;
	}

	public void acceptNewCPUState(ATmega328PCPUState newState) {
		this.currentState = newState;
	}

	public ATmega328PCPU(HashMap<String, Integer> registers, LinkedList<ATmega328PCPUState> CPUStates,
			long programCounter) {

		this.currentState = new ATmega328PCPUState(registers, programCounter);

		this.CPUStates = new LinkedList<ATmega328PCPUState>(CPUStates);

		this.instructionMap = new HashMap<String, AbstractInstruction>();

		create_opcode_map();

	}

	public HashMap<String, AbstractInstruction> getInstructionMap() {
		return this.instructionMap;
	}

	public HashMap<String, Integer> getRegisters() {
		return this.currentState.getRegisters();
	}

	public void run(String functionName) throws Exception {

		if (this.debugFlag) {
			System.out.println(this.toString() + "\n");
		}
		
		LinkedList<String[]> instructions = this.program.get(functionName);
		
		if(instructions == null) {
			throw new Exception("Unable to locate function " + "\"" + functionName + "\". Did you forget to declare it?");
		}

		for (String[] Line : instructions) {
			
			String cleanedOpcode = stadardizeOpcodeString(Line[0]);

			AbstractInstruction InstructionToExecute = instructionMap.get(cleanedOpcode);

			if (InstructionToExecute == null) {
				throw new Exception("Invalid Instruction or Macro " + "\"" + cleanedOpcode + "\"" + " Specified");
			}

			ATmega328PCPUState oldCPU = new ATmega328PCPUState(this.currentState);

			this.CPUStates.add(oldCPU);
			
			if(this.debugFlag) {
				System.out.println(InstructionToExecute.getType());
			}
			
			if(cleanedOpcode.equals("RET")) {
				return;
			}
			
			String runResult  = determineAndExecuteInstruction(Line, cleanedOpcode, InstructionToExecute);
			
			switch(runResult) {
				case "RET":
					
			
				default:
				break;
			}

		}

	}

	private String determineAndExecuteInstruction(String[] Line, String cleanedOpcode,
			AbstractInstruction InstructionToExecute) throws Exception {
		
		String[] Args = pop_args(Line);
		
		InstructionToExecute.setArgs(Args);
		
		switch (InstructionToExecute.getType()) {
		
		case FlowInstruction:
			this.runFlowInstruction(cleanedOpcode, Args);
			break;
		
		case HWInstruction:
			AbstractCPUState newState = InstructionToExecute.run(this.currentState, this.debugFlag);
			if(newState instanceof ATmega328PCPUState){
				this.acceptNewCPUState((ATmega328PCPUState) newState);
			}
			break;
		case Macro:
			this.runMacro(cleanedOpcode);
			break;
		}

		if (this.debugFlag) {
			System.out.println(Arrays.toString(Line));
			System.out.println(this.toString() + "\n");
		}
		
		return "NEC";
	}

	private String stadardizeOpcodeString(String opcode) {
		return opcode.strip().toUpperCase();
	}

	private void runMacro(String opcode) throws Exception {

		switch (opcode) {

		case "@@PRINTREGS":
			System.out.println(this.toString());
			return;

		default:
			throw new Exception("Invalid or Unimplemented Macro: " + opcode);
		}

	}
	
	private String runFlowInstruction(String opcode, String [] args) throws Exception {

		switch (opcode) {
		
		case "JMP":
			
			if(!this.program.containsKey(args[0])) {
				throw new Exception("Invalid function " + args[0] + " called");
			}
			this.run(args[0]);
			return opcode;
			
			
		
		default:
			throw new Exception("Invalid or Unimplemented Flow Instruction: " + opcode);
		}

	}


	private String[] pop_args(String[] line) {

		String[] args = new String[line.length - 1];

		for (int i = 1; i < line.length; i++) {
			args[i - 1] = line[i];
		}

		return args;
	}
	public String toString() {
		return this.currentState.toString();

	}

	@Override
	public void setRegister(String register, Integer value) throws Exception {
		this.currentState.setRegister(register, value);
		return;

	}


	@Override
	public Integer getRegister(String register) {

		if (this.debugFlag) {
			System.out.println("Getting register: " + register);
		}

		switch (register) {
		default:
			return this.currentState.getRegister(register);

		}
	}
	
	public LinkedList<Integer> getMemStack() {
		return this.currentState.getMemoryStack();
	}

	public HashMap<String, Integer> getRegisterMap() {
		return this.currentState.getRegisters();
	}

	public HashMap<String, AbstractInstruction> getSupportedInstructions() {
		return this.instructionMap;
	}


}
