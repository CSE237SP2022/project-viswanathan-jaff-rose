What user stories were completed this iteration?
  - We were able to take in an assembly file
  - We were able to check if the file is a valid ".S" file
  - Parse out the instructions and compact them into an array
  - Unit tests to verify all this works

What user stories do you intend to complete next iteration?
  - A CPU object and a basic flow for executing instructions
  - An abstract instruction object
  - An increment instruction
  - A printregs macro auto-encoded
  
Is there anything that you implemented but doesn't currently work?
  - Not yet
  
What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
  - Run
```bash

sh run.sh src/Interpreter/Test.S

```

It does not currently compile the code, we will fix that over the weekend but it will run a test case.
