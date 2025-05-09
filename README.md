Command to run MPI.c

        -> mpicc -o mpi_c MPI.c -lm
        -> mpirun -np 4 ./mpi_c


Comman to run TokenRing.java

        -> javac TokenRing.java
        -> java TokenRing

        
Command to run BullyAlgo.java

        -> javac BullyAlgo.java
        -> java BullyAlgo


Command to run RingAlgo.java

        -> javac RingAlgo.java
        -> java RingAlgo


Command to run RMI

        Trerminal 1: 
        
        -> javac *.java
        -> rmic Servant
        -> rmiregistry
        
        Terminal 2:
        
        -> java Server
        
        Terminal 3:
        
        -> java Client
        -> 127.0.0.1
        -> First Meaage
        -> Second Message


Command to run CORBA

        Terminal 1:
        
        -> idlj -fall Calculator.idl
        -> javac CalculatorApp/*.java *.java
        -> tnameserv -ORBInitialPort 1050

        Terminal 2:
        -> java CalculatorServer -ORBInitialPort 1050 -ORBInitialHost localhost

        Terminal 3:
        -> java CalculatorClient -ORBInitialPort 1050 -ORBInitialHost localhost
        
