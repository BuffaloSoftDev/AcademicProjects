/*
 * George Rossney
 * CSC 242
 * Project 1: Tic Tac Toe
 * Part 1: Nine-Board Version 
 * Date: 2/15/16
 */

public class NineMain {
	
	public static void main(String args[]){
		//main method creates a new instance of the game 
		
		System.out.println("Welcome Nine-board Tic Tac Toe.");
		System.out.println("The board ranges from 0 to 8 on rows & columns.");
		//default is to always play again 
		int playagain=50;
		while(playagain<=50){
		NineBoardTTT game2= new NineBoardTTT();
		game2.gamePlay();
		}
	}
}

 