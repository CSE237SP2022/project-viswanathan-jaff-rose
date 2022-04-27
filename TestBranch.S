.global main
.global somefunc

main:
LDI r29, 0x0F
LDI r28, 0x0F
cp r29, r28
breq somefunc


somefunc:
@@printregs
