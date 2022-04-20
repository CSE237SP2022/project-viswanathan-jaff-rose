.global main
.global someFunction

main:
ldi r29, 0x14
INC r29
LDI r28, 0xAF
inc r28
@@printregs
call someFunction
@@printregs
ret

someFunction:
INC r29
ret
