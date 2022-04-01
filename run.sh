#!/bin/bash
javac -d bin/ src/Interpreter/*.java
java -cp bin Interpreter.Main "$1"
