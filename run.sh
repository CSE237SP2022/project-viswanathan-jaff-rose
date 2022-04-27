#!/bin/bash

javac -d bin/ -sourcepath src src/Interpreter/*.java

stringZ="java -cp bin Interpreter.Main"

for var in "$@"
do 
	stringZ="${stringZ} \"${var}\""
done

eval $stringZ
