# Javaduino

An Arduino Assembly Interpreter written in Java. This can be considered an HLE-like implementation of an AVR assembly emulation.

### What it supports
- Basic arithmetic instructions (add, addi, inc, etc.)
- Basic register management instructions (ldi, mov, etc.)
- Jumping and branching (via jmp, breq, brne)
- Emulated SRAM memory stack such that push and pop are supported
- 

### What it does not support
- Call-saved/Call-clobbered registers 
- GPIO pin emulation
- C-Style function calls
- Data Space instructions (sts, etc.)
- X, Y, Z pointers
- Interrupts
- Directly running compiled binaries (this is an **Interpreter** not a direct emulator)
- Certain GNU Assembler directives (ex. .data labels)


### What user stories were completed this iteration?
  - More arithmetic instructions were addded (ADDI, SUBI)
  - Logical instructions were added (CP, LSL, LSR)
  - Labels and global directives
  - The ability to jump to labels
  - The ability to branch to labels
  - Register comparison for said branching
  - SRAM stack emulation (PUSH and POP can be executed)
   
  
### Is there anything that you implemented but doesn't currently work?

RET is technically implemented but it is not 100% compatible with Arduino Assembly. It doesn't obey call-saved registers, but it does propely end execution of assembly labels.


### What commands are needed to compile and run your code from the command line? 

A shell script is provided to run it, and a test Assembly File has been provided.

run without debug:

```bash
sh run.sh Test.S
```

run with debug:

```bash
sh run.sh Test.S -d
```

We have also added a demo of the Fibonacci Sequece

```bash
sh run.sh Fib.S
```
