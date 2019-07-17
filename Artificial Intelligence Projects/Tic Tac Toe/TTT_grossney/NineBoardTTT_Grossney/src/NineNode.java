/*
 * George Rossney
 * CSC 242
 * Project 1: Tic Tac Toe
 * Part 1: Nine-Board Version 
 * Date: 2/15/16
 */

public class NineNode {
//	String[][] board = new String[3][3];
	//modifying for larger 9x9 size 
	String[][] board = new String[9][9];
	String turn;
	NineNode parent;
	int treeDepth, heuristicVal;
	
	public NineNode(){}
	public NineNode(String[][] board, NineNode parent, int heuristicVal, int treeDepth, String turn) {
		this.board = board;
		this.turn = turn;
		this.parent = parent;
		this.heuristicVal = heuristicVal;
		this.treeDepth = treeDepth;
	}
	public NineNode(String[][] board) {
		this(board, null, 0, 0, null);
	}
	
}