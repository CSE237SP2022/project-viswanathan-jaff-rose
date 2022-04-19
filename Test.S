.global main

main:
LDI r29, 0x14
INC r29
LDI r28, 0xAF
INC r28
@@printregs
ADD r28, r29
@@printregs
