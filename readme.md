### What user stories were completed this iteration?
  - We built the CPU class/CPU States and the OOP that handles execution
  - We then refactored the CPU model because there were things wrong with it
  - CPU Instructions ADD, LDI, and INC
  - Assembly Macros (@@printregs)
  - Basic code to tryout assembly SUB and CP (not in production currently)
  - Unit tests to verify all this works

### What user stories do you intend to complete next iteration?
  - More instructions. Including ADI, and MOV, etc
  - More robust exception handling
  - If time: Function detection and function calling
  
### Is there anything that you implemented but doesn't currently work?
  - We refactored the code to make the registers Java Integers, because encoding the data into Java bytes was giving us issues when it came to values in the high bits since bytes are signed. As a result, the CPU registers theoretically can't handle overflow right now, but in practice, the overflow moves into the 24 high bits which are ignored for the instructions and workflow we've written. We plan to immediately add overflow checking in our next model.
  - The commands SUB and CP (assembly subtract and compare) are currently being tested and worked on. They are in their own branch and do not currently work, but they will be finished for iteration 3
  
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