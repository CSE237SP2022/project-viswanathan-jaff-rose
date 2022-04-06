package Atmega328CPUInstructions;

import Interpreter.AbstractCPU;
public class ADD extends AbstractInstruction {

    private String destRegister;
    private string srcRegister;

    private AbstractCPU cpu;

    public ADD() {
        this.opcode = "ADD";
        this.CPU = "Atmega328P";
    }

    public void setArgs(String[] args) {
        this.destRegister = args[0];
        this.srcRegister = args[1];
    }

    @Override
    public AbstractCPU run(AbstractCPU cpu, boolean debug) {
        this.cpu = cpu;

        if(debug) {
            printDebug( (byte)(this.cpu.getRegister(this.srcRegister) );
        }

        int result = (byte)this.destRegister + (byte)this.srcRegister;
        cpu.setRegister(this.destRegister, (byte)result);
        return cpu;
    }

    private void printDebug(byte newVal) {
        System.out.println("Existing Value at destination register " + this.destRegister + ": " + this.cpu.getRegister(this.destRegister));
        System.out.println("Value at source register " + this.srcRegister + ": " + this.cpu.getRegister(this.srcRegister));
        System.out.println("New Value at destination register " + this.destRegister + ": " + newVal);
    }
}