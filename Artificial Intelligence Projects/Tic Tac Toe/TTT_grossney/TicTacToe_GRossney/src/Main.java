/*
 * George Rossney
 * CSC 242
 * Project 1: Tic Tac Toe
 * Part 1: 3x3 Version 
 * Date: 2/15/16
 */

public class Main {
	
	public static void main(String args[]){
		//main method creates a new instance of the game 
		
		System.out.println("Welcome to 3x3 Tic Tac Toe.");
		System.out.println("The board ranges from 0 to 2 on rows & columns.");
		//default is to always play again 
		int playagain=50;
		while(playagain<=50){
		TicTacToe game1= new TicTacToe();
		game1.gamePlay();
		}
	}
}

 