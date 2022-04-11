package Interpreter;

public abstract class AbstractInstruction {
	
	protected String opcode;
	
	protected String CPU;
	
	protected InstructionType type;
	
	public abstract void setArgs(String[] args) throws Exception;
  
	public abstract AbstractCPUState run(AbstractCPUState cpustate, boolean debug) throws Exception;

	
	public String getOpcode() {
		return this.opcode;
	}
	
	public InstructionType getType() {
		return this.type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CPU == null) ? 0 : CPU.hashCode());
		result = prime * result + ((opcode == null) ? 0 : opcode.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractInstruction other = (AbstractInstruction) obj;
		if (CPU == null) {
			if (other.CPU != null)
				return false;
		} else if (!CPU.equals(other.CPU))
			return false;
		if (opcode == null) {
			if (other.opcode != null)
				return false;
		} else if (!opcode.equals(other.opcode))
			return false;
		if (type != other.type)
			return false;
		return true;
	}


}