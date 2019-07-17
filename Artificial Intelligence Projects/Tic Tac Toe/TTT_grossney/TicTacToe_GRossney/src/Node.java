/*
 * George Rossney
 * CSC 242
 * Project 1: Tic Tac Toe
 * Part 1: 3x3 Version 
 * Date: 2/15/16
 */

public class Node {
	String[][] board = new String[3][3];
	String turn;
	Node parent;
	int treeDepth, heuristicVal;
	
	public Node(){}
	public Node(String[][] board, Node parent, int heuristicVal, int treeDepth, String turn) {
		this.board = board;
		this.turn = turn;
		this.parent = parent;
		this.heuristicVal = heuristicVal;
		this.treeDepth = treeDepth;
	}
	public Node(String[][] board) {
		this(board, null, 0, 0, null);
	}
	
}