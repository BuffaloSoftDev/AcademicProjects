/*
George Rossney 
Project 3: Point Location  
CSC 172
Lab TA: Pauline Chen 
Session: T/Th 4:50-6:05pm 
*/
package pointlocationpkg;

public class Node {

	public Line line;
	public Node left, right; 

	public Node(Line line){
		//initial nodes are constructed as null 
		this.line = line;
		this.left = null;
		this.right = null;
	}
}//closing public class node 
