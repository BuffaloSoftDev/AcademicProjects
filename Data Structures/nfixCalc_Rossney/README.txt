 
 George Rossney 
 Project 2: Infix Calculator  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 


Description: The main goals of this project are to convert mathematical expression from infix notation, traditional math, to postfix notation, then evaluate the postfix expression. The expressions come from a text file that can be read by a file scanner in Java. Each expression is stored into an array, converted, evaluated, then printed separately as console out. Operators such as, “+” and “*“ are stored in array strings, then converted into their respective functions, such as “+” for addition. Since libraries like StringTokenizer are not allowed, I wrote methods to splice the string characters into their functions using switch case conditionals. I wrote the program to take a variable number of lines and variable length of the lines from the input text file. I researched the Shunting-Yard Algorithm to figure out how to do the infix to postfix conversion in Java. Since order of operations is necessary for calculating unique math expressions accurately, I implemented methods for a linked list, stack, and queue from previous labs. While the expressions are being evaluated, the stack holds the operators and the queue holds the operands, generally. The output will have the same number of lines as the input, but will contain a sum, or answer, for each expression on each line. For this project I modified my linked list, stack, and queue files from labs 2 and 6. I made them simpler so each does not have its own “myNode” and test files. 

Known Issue: All code files do any  errors or warnings, however there is an occasionally NullPointerException error that appears in the console after running the program. Even with the error, the program did output the correct answers for the 25-line test file given on BlackBoard. 

Note: I wrote, compiled, and ran all files for this project in the Java language in the Eclipse Luna IDE for Mac.

Sources: 

http://rosettacode.org/wiki/Parsing/Shunting-yard_algorithm: 
	I did not use any of the code here, but I gave me ideas on how to implement the 	Shunting-Yard algorithm. 
https://igor.io/2013/12/03/stack-machines-shunting-yard.html
	Helped me understand what the calculator has to do 
http://eddmann.com/posts/shunting-yard-implementation-in-java/
	More info on the Shunting-Yard algorithm 

Included Files (8 total): 
   - All Java Files are included in the calcpackage folder 

Main.java - Contains main method, contains file scanner that takes in lines of text from the input text file, puts them into a string array, and splices the characters. 

InfixCalc.java _ the main file of the project, contains methods necessary to convert from infix to postfix, using a stack, queue, and linked list. Also contains methods to covert the string characters back into their functions, so “+” will mean addition for example. 

Stack.java - Stack class that includes read, pop, and push methods, based off of the linked list 

StackInterface.java - method for calling the read, pop, and push methods. 

Queue.java - Queue class that includes dequeue and enqueue methods, based off of the linked list 

QueueInterface.java - method for calling the dequeue and enqueue methods. 

LinkedList.java- modified version of my linked list from lab 2, the base structure for the stack and queue. 

LLInterface.java- linked list interface, contains methods for calling the insert, delete, lookup, and print method of the linked list. 

Text Files: For any list of expressions I was testing, I moved the text file containing them directly into the project folder, so the file scanner can read them.  


 

