CFLAGS = -Wall -fPIC
CMAIN = -Wall -L.
INC = def.h constante.h
SRC = client.c
OBJ = $(SRC:*.c=*.o)

PROG = main

all: *.o  lib main

*.o: *.c $(INC)
	gcc $(CFLAGS) -c $<

lib:$(OBJ)
	gcc -shared programmeC.o client.o -o libTwisk.so

main: $(OBJ)
	gcc $(CMAIN) main.c  -o $(PROG) -lTwisk
	export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.

test:
	nm -D libTwisk.so
clean:
	rm -f client.o main.o $(PROG)
	rm -f libTwisk.so
