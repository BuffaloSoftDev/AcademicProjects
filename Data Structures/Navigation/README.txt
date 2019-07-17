
 George Rossney 
 Project 3: Point Location  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 

	This goal of this project is to solve the point location problem by using an binary tree structure. Input: a scanner prompts the user for a text file name and location in the console. Output: output is in the console. For the program to run, the input format must be exact, which is a number of lines, two sets of x & y coordinate for each line, then point coordinates for the line segments. The test case I included has four lines and coordinates and two line segments and coordinates. If there are no intersections, the output will say so, if there are, the intersection coordinate points will be given. One of the most difficult parts of the project for me was getting the input to work using a file scanner and buffered reader, instead of a console input scanner. For the buffered reader, the format has to be perfect and the code interpretation has to be perfect, or you will get number/string format errors. 

Note: Text files can be located anywhere and ran from there as long as the proper path is given. I put my text file in my src, folder so the path was “src/…”. 
Sources: 
- cow method from the project 3 pdf
- http://www.cs.princeton.edu/courses/archive/spr07/cos226/assignments/locate.html
	I used the Princeton website that this assignment was originally created at for help with the BT put methods. 


Files Included:
PointText.txt -the test text input file I used 
package: pointlocationpkg
BT.java - binary tree structure 
FileInput.java -taking in input and constructing a BT with it 
Line.java - solving for the intersection(s)
Node.java -constructing nodes for the BT
Point.java -constructing points, made it a separate class because it’s used so much. 

