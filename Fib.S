
.global loop
.global main
.global end

; Basic Fibonacci Assembly Code, r27 holds the result of the fibonacci number

; r29: Fibonacci # Desired
; r26: Loop Counter
; r27  First Term
; r28  Second Term



main:
ldi r29, 5
ldi r28, 0
ldi r26, 0
ldi r27, 1
jmp loop
finished

loop:
cp r26, r29
breq end
PUSH r27
add r27, r28
POP r28
inc r26
jmp loop

end:
@@printreg r27
@@kill


