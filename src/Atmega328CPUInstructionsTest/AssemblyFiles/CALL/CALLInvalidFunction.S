

.global myFunc

.global main

main:
ldi r29, 0xAF

call invalidFunction

ret

myFunc:
INC r29
ret
