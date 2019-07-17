George Rossney 
Project 4: Street Mapping 
Course: CSC 172
Lab TA: Pauline Chen 
Session: T/Th 4:50-6:05pm 

README

For this project on street mapping, I started by implementing code from the previous labs on graphs. The adjacency matrix (2-D array), vertex, and edge structure for this project are more advanced versions of those classes from the labs. To find the shortest path between the  start and end points that the user enters, I implemented Dijktra’s algorithm from the Weiss textbook. To create the map, the vertices and edges are passed in line by line from the bufffered reader and user input scanner. The jFrame should show the map with the shortest path highlighted in red. The jFrame should generate in a new window after the console output is displayed. The console output shows the user directions, or steps of the shortest path, using their start and end points. I have found that my program takes considerably longer to run when the map size jumps from UR, to Monroe County, then to New York State. The state map is a challenge because I do not have any experience handling so much data using basic data structures.  

Run-time: I estimate the run-time to by O(n^2), coinciding with Dijktra’s algorithm. Other parts of the program may run faster on their own, but the run-time is expected to be O(n^2) overall. 

Note: To run the program with different text files, I changed the name of the file in the buffered reader. I have “ur.txt” and left “monroe.txt” and “nys.txt” as commented out options. I wrote, compiled, and ran the files for this project in the Eclipse Luna IDE for Mac. I did have to increase the heap memory size in the run configuration arguments to run the program with the larger text files. 

Included files:

AdjList.java - Adjacency list interface
Vertex.java - setting up the vertex structure 
Edge.java - setting up the graph edge structure
Graph.java - implementing adjacency 2-D array and Dijsktra’s algorithm 
Mapping.java - main method, reading text file and user input, console output, jFrame setup for constructing the map 

