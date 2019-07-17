

 George Rossney 
 Project 1- Mastermind  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm


Description: 

This project is a game coded in Java, but unlike most other games, the program is the codebreaker and the user is the code maker. For this game all of the output is in the console as the program runs, instead of a GUI. The user is prompted to enter a number of colors, anywhere from 1 to 10. The more colors, the more complex the game is and the longer it will take the computer to guess your combination of color codes. Each color code is represented by a capital letter, example: “R” for red. The user can enter any combination of the available colors once they have decided a number of colors. For example, if the user chooses to make the difficulty 3 colors, then the codes RGB are provided. Possible combinations are RGB, BGR, RRR, GGG, BBR, etc. Codes can be repeated. The code does take longer to run when more color codes are entered, because it takes the computer longer to guess. It does not always take the computer more guesses to break the code when there are more colors used. For each combination of the color code, the program will output the number of white pegs and the number of black pegs.

Algorithm: 

Essentially, the algorithm I wrote for this project takes in user input from a buffered reader, stores it in a string array, then uses methods to guess the user’s code and give output. The main method contains references to start and replay the game. As static string arrays, I declared the colors by name and then the colors by their respective first letters, like “R” for red. The user color code is inputted as one string of capital letters, but each letter needs to be treated as a separate item. In order to make each letter separate, I defined static character arrays. The computer takes in the codes with zero initial guesses, then the number of guesses increases as the code is evaluated. To prevent duplicate combinations, I compare the current move to previous moves. Output from print statements will be displayed in the console, which is the display for the user. My algorithm includes methods to start, reset, and replay the game, which are similar to methods that I’ve used in previous projects, such as the Tetris project in CSC 171. In order for the program to run properly, the user must enter a valid number of colors and a valid combination of colors. There is a method to check that the input is valid. 

Notes: 

Depending on the code entered and the number of colors, it can take up to a minute for the program to return output. It only takes a few seconds for 1,2,3,4, and 5 colors. The program does consistently run and give output with the number of white and black pegs for each combination and the number of guesses it took the computer to crack the code. 

I wrote, compiled, ran, and tested this project using the the Eclipse Luna IDE for Mac. 


Included File: 

Mastermind_Rossney.java - This java file contains my entire project. It contains a main method for running the program, and all of the methods required for the setup, logic, and execution. 

OUTPUT: Contains sample output with random color amount and combinations, which show that the program can run for variable amount of colors in the code. 

Sources: 

I did not use any sources for specific methods for this project, rather I used various online sources to guide my understanding and implementation of the Mastermind game.

	Wikipedia for general info: http://en.wikipedia.org/wiki/Mastermind_%28board_game%29
	More explanation: http://codebreaker.creativitygames.net/mastermind_strategy_for_real_people.php
	Strategy info: https://code.google.com/p/mastermind-strategy/wiki/Overview 





	






