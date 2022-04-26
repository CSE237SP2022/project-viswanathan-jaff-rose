
.global loop
.global main
.global end

; r29: Fibonacci # Desired
; r28: Loop Counter
; r27  First Term
; r28  Second Term



main:
ldi r29, 3
ldi r28, 0
ldi r27, 0
ldi r27, 1
jmp loop
finished

loop:
cp r28, r29
breq end
PUSH r27
add r27, r28
POP r28
jmp loop

end:
@@printregs



