Start:
1. \src\main\java 
in this folder, open the command line and write the command to compille
javac *.java
2. after compilation we enter:
java Sort C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=a
your directory C:\testSort\ 
type=i for Integer, type=s for String
mode=a for Sort by growth, mode=b for invert Sort

Another examples:
C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=a
C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=b
C:\testSort\ --out-prefix=sorted_ --content- type=s --sort-mode=a


another way to run through the jar file used:
1. \out\artifacts\insertion_jar in this folder we call the command line
java –jar insertion.jar C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=a

