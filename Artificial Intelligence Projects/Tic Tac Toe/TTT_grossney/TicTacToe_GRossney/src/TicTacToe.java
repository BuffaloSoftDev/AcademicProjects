/*
 * George Rossney            
 * CSC 242
 * Project 1: Tic Tac Toe
 * Part 1: 3x3 Version 
 * Date: 2/15/16
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class TicTacToe { 


	// using tree structure to start minimax algorithm
	public Node start(String[][] board) {
		// any empty board is the the initial state 
		Node initial = new Node();
		initial.board = board;
		// computer is always "X"
		initial.turn = "X";
		return initial;
	}
	
	// new instance of node 
	public Node initializeNode() {
		return new Node();
	}
	
	// input is a single character in a position in a 2-D array
	public String[][] addSymbol(int row, String inputLine, String[][] boardAtRow) {
		//initially no characters in columns 
		int column = 0, index=0;
		// zero to three characters in each row
		for (index = 0; index < inputLine.length(); index = index + 2) {
			////symbol is "X" or "O" 
			char symbol = inputLine.charAt(index);
			// add nothing for no char
			if (symbol == ' ') {
				// adding "X" or "O" character by row to column
				boardAtRow[row][column] = null;
				column++;
			}
			// either move over a column with an "X","O", or empty space
			else {
				boardAtRow[row][column] = Character.toString(symbol);
				column++;
			}
		}
		// return new row
		return boardAtRow;
	}

	// //boolean childNode is either a winning move or not
	public boolean childNode(Node state) {
		// state is the current tree node (game move) 
		if (this.checkWinOnRow(state) || this.checkWinOnColumn(state) 
				|| this.checkWinOnDiagonal(state)) {
			return true;
		}
		if (this.emptyCheck(state)!= null) {
			return false;
		} else {
			return true;
		}
	}
	
	// check heuristicVal at childNode.
	public int checkHeuristicValue(Node state) {
		if (state.turn == "O" && (this.checkWinOnRow(state) == true 
				|| this.checkWinOnColumn(state)==true 
				|| this.checkWinOnDiagonal(state))){
			/// user wants positive score
			return 1;
		}
		if (state.turn == "X" && (this.checkWinOnRow(state) == true 
				|| this.checkWinOnColumn(state)==true 
				|| this.checkWinOnDiagonal(state))){
			// computer wants negative score
			return -1;
		} 
//		//error condition so that "0" ( a draw) is returned if and only if there is a draw
//		if (state.turn != "O" || state.turn != "X"){
//			System.err.println("Error. Player must be 'X' or 'O'");
//			//2 is random, needed a different value to return 
//			return 2; 
		else{
			return 0;
		}
	}
	// checking for errors, mainly for my own debugging 
		public void checkNode(Node state){
			//the next player has to be "O" or "X", no other players 
			if(state.turn!="O" || state.turn!="X"){
				System.err.println("Error: Not a valid player. Try again."); 
			}
		}
		
	// does the current row contain a win?
	public boolean checkWinOnRow(Node state){
		//initially, there is no win found 
		int row=0,column=0; 
		for (row=0; row < state.board.length; row++) {
			int repeatNode = 0;
			//looking for winning X's or O's 
			String scanForElement = state.board[row][0];
			if (scanForElement == null)
				break;
			//iterate columns to check for row win 
			for (column = 1; column < state.board.length; column++) {
				String nextString = state.board[row][column];
				if (nextString == null)
					break;
				else if (scanForElement.contentEquals(nextString) == false)
					break;
				else {
					repeatNode++;
				}
			}
			//there is a win if there are 3 same symbols in a row 
			if (repeatNode==2)
				return true;
		}
		//no win found, keep playing 
		return false;
	}

	// does the current column contain a win?
	public boolean checkWinOnColumn(Node state) {
		int row,column=0;
		for (column =0;column<state.board.length;column++) {
			int repeatNode=0;
			String scanForElement=state.board[0][column];
			// break if there is no string
			if (scanForElement == null) {
				break;
			}
			for (row = 1;row<state.board.length; row++) {
				String nextString=state.board[row][column];
				if (nextString==null) {
					break;
				} else if (scanForElement.contentEquals(nextString) == false) {
					break;
				} else {
					repeatNode++;
				}
			}
			//same as row, there is a win if there are 3 same symbols in a row 
			if (repeatNode==2) {
				return true;
			}
		}
		//no win found, keep playing 
		return false;
	}

	// checking diagonals for winning moves
	public boolean checkWinOnDiagonal(Node state) {
		// using 0,1,2 to point to 2-D array positions on game board
		String[][] aBoard = state.board;
		//(1,1) is the center of the 3x3 board
		//if the center spot is not used, then there cannot be a diagonal win 
		if (aBoard[1][1] != null) {
			if (aBoard[0][0] != null && aBoard[2][2] != null)
				return this.checkWinOnLeftDiagonal(state);
			else if (aBoard[2][0] != null && aBoard[0][2] != null)
				return this.checkWinOnRightDiagonal(state);
			else
				// no diagonal win
				return false;
		} else
			return false;
	}

	//using center spot (1,1) as a basis to check for left & right diagonal wins 
	
	public boolean checkWinOnRightDiagonal(Node state) {
		String[][] aBoard = state.board;
		//using specific 2-D array coordinates to check for X or O contents 
		return (aBoard[1][1].contentEquals(aBoard[0][2]) 
				&& aBoard[1][1].contentEquals(aBoard[2][0]));
	}

	public boolean checkWinOnLeftDiagonal(Node state) {
		String[][] aBoard = state.board;
		return (aBoard[1][1].contentEquals(aBoard[0][0])
				&& aBoard[1][1].contentEquals(aBoard[2][2]));
	}
	//taking account of available spaces for new X's or O's to be placed
	//used for vectors and next move
	public int[] emptyCheck(Node state) {
		int boardSize = state.board.length;
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				if (state.board[row][column] == null)
					return this.addValueToArray(row, column);
			}
		}
		return null;
	}
	//implementing ArrayList to check for empty spaces, casting for int over string 
	public ArrayList<int[]> scanAllEmptySquareOnBoard(Node state) {
		int boardSize = state.board.length;
		ArrayList<int[]> TTTArrayList = new ArrayList<int[]>();
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				if (state.board[row][column] == null)
					TTTArrayList.add(this.addValueToArray(row, column));
			}
		}
		return TTTArrayList;
	}

	public int[] addValueToArray(int firstNum, int secondNum) {
		int[] TTTArray = new int[2];
		TTTArray[0] = firstNum;
		TTTArray[1] = secondNum;
		return TTTArray;
	}
	//updating the 2-D array board after empty spots have been searched for 
	public String[][] updateBoard(Node state, int[] openSpot) {
		String[][] newBoard = this.update(state.board);
		newBoard[openSpot[0]][openSpot[1]] = state.turn;
		return newBoard;
	}
	//successor is the next move, has to go in an open spot 
	public Node getSuccessor(Node state, int[] openSpot) {
		if (this.childNode(state) == true)
			return null;
		else {
			//I made the computer user always "X" so I could code this in...
			if (state.turn == "X") {
				return new Node(this.updateBoard(state, openSpot), state,
						this.checkHeuristicValue(state), state.treeDepth + 1, "O");
			} else {
				return new Node(this.updateBoard(state, openSpot), state,
						this.checkHeuristicValue(state), state.treeDepth + 1, "X");
			}
		}
	}

	//Vectors: idea for MiniMax algorithm from AIMA textbook
	public Vector<Node> getSuccessors(Node state) {
		Vector<Node> Successors = new Vector<Node>();
		ArrayList<int[]> allEmptySquareOnBoard = this.scanAllEmptySquareOnBoard(state);
		int numberOfEmptySquareOnBoard = allEmptySquareOnBoard.size();
		for (int i = 0; i < numberOfEmptySquareOnBoard; i++) {
			Successors.add(this.getSuccessor(state, allEmptySquareOnBoard.get(i)));
		}
		return Successors;
	}
	//for minimax algorithm: sorting greater or lesser value 
	
	public int getMaxTwoIntegers(int firstInt, int secondInt) {
		if (firstInt < secondInt)
			return secondInt;
		else
			return firstInt;
	}

	public int getMinTwoIntegers(int firstInt, int secondInt) {
		if (firstInt > secondInt)
			return secondInt;
		else
			return firstInt;
	}
	//optimizing the computer's move 
	public Node getMinNodeInList(Vector<Node> aVectorNode) {
		Node minNode = aVectorNode.get(0);
		int listSize = aVectorNode.size();
		for (int index = 0; index < listSize; index++)
			if (minNode.heuristicVal > aVectorNode.get(index).heuristicVal)
				minNode = aVectorNode.get(index);
		return minNode;
	}
   //optimizing the user's move 
	public Node getMaxNodeInList(Vector<Node> aVectorNode) {
		Node maxNode = aVectorNode.get(0);
		int listSize = aVectorNode.size();
		for (int index = 0; index < listSize; index++)
			if (maxNode.heuristicVal < aVectorNode.get(index).heuristicVal)
				maxNode = aVectorNode.get(index);
		return maxNode;
	}

	// implementing MiniMax algorithm with vectors 
	Vector<Node> possibleNextMoveNodes = new Vector<Node>();

	public int getMinimax(Node state) {
		if (this.childNode(state) == true)
			return this.miniMaxChildNode(state);
		else
			return 0;
		}

	public int miniMaxNonChildNode(Node state) {
		//the successors are the range of possible moves for the heuristic to evaluate 
		Vector<Node> allSuccessors = this.getSuccessors(state);
		for (int atIndex = 0; atIndex < allSuccessors.size(); atIndex++) {
			Node aSuccessor = allSuccessors.get(atIndex);
			if (state.turn == "O")
				//heurisitic on human user, even though they make their own moves 
				state.heuristicVal = this.getMinTwoIntegers(state.heuristicVal,
						this.getMinimax(aSuccessor));
			else
				state.heuristicVal = this.getMaxTwoIntegers(state.heuristicVal,
						this.getMinimax(aSuccessor));
		}
		if (this.possibleNextMoveNodes(state) != null)
			possibleNextMoveNodes.add(state);
		//use heuristic if there are still possible moves to be made 
		return state.heuristicVal;
	}

	public int miniMaxChildNode(Node state) {
		if (this.possibleNextMoveNodes(state) != null)
			possibleNextMoveNodes.add(state);
		return this.checkHeuristicValue(state);
	}

	//determining most negative or most positive value 
	public static int infinity = 1000;

	// setup for alpha-beta pruning 
	public int initializeAlpha(Node state) {
		if (this.childNode(state) == true)
			return this.checkHeuristicValue(state);
		else
			return -infinity;
	}

	public int initializeBeta(Node state) {
		if (this.childNode(state) == true)
			return this.checkHeuristicValue(state);
		else
			return infinity;
	}
	
	public int pruningCheck(Node state){
		if ((this.childNode(state)==false) 
			&& (this.childNode(state)==false)){
			return 0; 
		}else {
			//nothing else to return
			return 0;
		}
	}
	//adopted from pseudocode on page 173 of textbook
	public int minimaxAlphaBetaPruning(Node state, int alpha, int beta) {
		int alphaOfCurrentNode = alpha;
		int betaOfCurrentNode = beta;
		if (this.childNode(state) == true)
			return this.miniMaxChildNode(state);
		else if (state.turn == "O")
			return this.minimaxAlpha_CurrentMaxNode(state, alphaOfCurrentNode, betaOfCurrentNode);
		else
			return this.minimaxBeta_CurrentMinNode(state, alphaOfCurrentNode, betaOfCurrentNode);
	}

	//combining minimax algorithm with alpha-beta pruning:
	
	public int minimaxAlpha_CurrentMaxNode(Node state, int alphaOfCurrentNode, int betaOfCurrentNode) {
		Vector<Node> allSuccessors = this.getSuccessors(state);
		for (int atIndex = 0; atIndex < allSuccessors.size(); atIndex++) {
			Node aSuccessor = allSuccessors.get(atIndex);
			int currentMin = this.minimaxAlphaBetaPruning(aSuccessor, alphaOfCurrentNode, betaOfCurrentNode);
			betaOfCurrentNode = this.getMinTwoIntegers(betaOfCurrentNode, currentMin);
			state.heuristicVal = this.getMinTwoIntegers(state.heuristicVal, betaOfCurrentNode);
			if (alphaOfCurrentNode >= betaOfCurrentNode)
				break;
		}
		if (this.possibleNextMoveNodes(state) != null)
			possibleNextMoveNodes.add(state);
		return betaOfCurrentNode;
	}

	public int minimaxBeta_CurrentMinNode(Node state, int alphaOfCurrentNode, int betaOfCurrentNode) {
		Vector<Node> allSuccessors = this.getSuccessors(state);
		for (int atIndex = 0; atIndex < allSuccessors.size(); atIndex++) {
			Node aSuccessor = allSuccessors.get(atIndex);
			int currentMax = this.minimaxAlphaBetaPruning(aSuccessor, alphaOfCurrentNode, betaOfCurrentNode);
			alphaOfCurrentNode = this.getMaxTwoIntegers(alphaOfCurrentNode, currentMax);
			state.heuristicVal = this.getMaxTwoIntegers(state.heuristicVal, alphaOfCurrentNode);
			if (alphaOfCurrentNode >= betaOfCurrentNode)
				break;
		}
		if (this.possibleNextMoveNodes(state) != null)
			possibleNextMoveNodes.add(state);
		return alphaOfCurrentNode;
	}

	// Get next move
	public Node possibleNextMoveNodes(Node state) {
		if (state.treeDepth == 1)
			return state;
		else
			return null;
	}

	public Node nextNodeToMove(Node state) {
		this.minimaxAlphaBetaPruning(state, this.initializeAlpha(state), this.initializeBeta(state));
		Node newNode = this.getMaxNodeInList(possibleNextMoveNodes);
		possibleNextMoveNodes.removeAllElements();
		return newNode;
	}

	// game moves:
	public void userMove(Node state) {
		Node newNode = new Node(); 
		//user enters one value at a time 
		int[] userInput = this.getCorrectInputFromUserMove(state);
		newNode = this.getSuccessor(state, userInput);
		this.Display(newNode.board);
		if (this.checkWinOnRow(newNode) == true || this.checkWinOnColumn(newNode)==true ||this.checkWinOnDiagonal(newNode)){
			initializeNode(); 
			//note: the user can only win after their own move, since
			//the computer plays instantly after 
			System.out.println("Congrats. You won!");
		}else if (this.childNode(newNode) == true){
			initializeNode(); 
			System.out.println("The game is draw");
		}else{
			this.compMove(newNode);
		}
	}

	public void compMove(Node state) {
		//used for setting the game to replay
		@SuppressWarnings("unused")
		int replayVar=0; 
		Node newNode = this.start(state.board);
		newNode = this.nextNodeToMove(newNode);
		this.Display(newNode.board);
		if (this.checkWinOnRow(newNode) == true 
				|| this.checkWinOnColumn(newNode) == true 
				|| checkWinOnDiagonal(newNode) == true){
			initializeNode(); 
			System.out.println("Computer won! Try again.");	
			replayVar=1;
		}else if (this.childNode(newNode) == true){
			System.out.println("The game is draw");
			replayVar=2; 
		}else{
			this.userMove(newNode);
		}
	}

	@SuppressWarnings("resource")
	public int firstMove() {
		Scanner player = new Scanner(System.in);
		System.out.print("Enter 1 to move first, or 2 to go second: ");
		return player.nextInt();
	}
	
	public void gamePlay() {
		Node root = this.initializeNode();
		int player = this.firstMove();
		if (player == 1) {
			root.turn = "O";
			this.userMove(root);
		} else {
			root.turn = "X";
			this.compMove(root);
		}
	}

	public int[] getCorrectInputFromUserMove(Node state) {
		int[] userInput = this.readUserInput();
		while(userInput[0]<0 || userInput[0]>=3 || userInput[1] < 0 || userInput[1] >= 3){
			System.out.println("Error. Make another move.");
			userInput = this.readUserInput();
		}
		while (this.checkEmptySquareFromUserInput(state, userInput)!= true) {
			System.out.println("Error. Make another move.");
			userInput = this.readUserInput();
		}
		return userInput;
	}

	public boolean checkEmptySquareFromUserInput(Node state, int[] userInput) {
		return state.board[userInput[0]][userInput[1]] == null;
	}

	public int[] updatedInput(int[] userInput) {
		int[] returnFromUserInput = new int[2];
		returnFromUserInput[0] = userInput[0] - 1;
		returnFromUserInput[1] = userInput[1] - 1;
		return returnFromUserInput;
	}

	@SuppressWarnings("resource")
	public int[] readUserInput() {
		int[] userInput = new int[2];
		//prompting the user for input array coordinates 
		Scanner row = new Scanner(System.in);
		Scanner column = new Scanner(System.in);
		//user enters row and column separately 
		System.out.print("Enter row: ");
		userInput[0] = row.nextInt();
		System.out.print("Enter column: ");
		userInput[1] = column.nextInt();
		return this.updatedInput(userInput);
	}

	// displaying the game board
	public void Display(String[][] board) {
		int boardSize = board.length;
		System.out.println("Game Board: ");
		System.out.print("-------------");
		System.out.println();
		for (int row = 0; row < boardSize; row++){
			// using +,|,and -- symbols to make output board display
			System.out.print("|");
			for (int column = 0; column < boardSize; column++) {
				if (board[row][column] == null)
					System.out.print("  " + " |");
				else
					System.out.print(" " + board[row][column] + " |");
			}
			System.out.println();
			System.out.print("-------------");
			System.out.println();
		}
	}

	// updating the game board
	public String[][] update(String[][] aBoard) {
		int boardSize = aBoard.length;
		String[][] newBoard = new String[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++)
				newBoard[row][column] = aBoard[row][column];
		}
		return newBoard;
	}
   
} // closing public class TicTacToe
