
import java.util.*; 

/*
 George Rossney 
 Project 1- Mastermind  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/ 

public class Mastermind_Rossney{
	
	//Main method: starting and replaying the game 
		public static void main(String[] args) throws Exception{
			while (true){
				//if the user replies "yes", start over 
				start(); 
				if (!replay()){ 
					//break and end if the user replies "no" to play again
					break;
				}
			}
		}

	static String[] colorNames={
		//ROYGBIV (7 colors) & PTB (3 colors) = 10 total
		"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet",
		"Pink", "Turquoise", "Brown"  }; 

	static String[] maxColorArray={
		//one char initial for each color 
		"R", "O", "Y", "G", "B", "I", "V", "P", "T", "B" };

	static String colorCode = ""; 
	static String secretCode = "BBBB"; //string to hold secret code 
 
	static int maxColors; 
	static char[] codeArray;
	static int length; //declaring length, used for codeArray 
	static char[] secretCodeArray; 
	static char[] GuessArray; 
	static boolean firstguess = true;
	
	//declaring an array for all possible moves 
	static ArrayList<String> possibleMoves = new ArrayList<String>();

	static int guesses= 0; //starting with no guesses made yet 
	
	//method for starting the game 
	static void start(){  
		//pulling in the colors and guesses to start the guessing
		getMaxColors();
		getPlayerGuess();
		initialGuess();
		
		System.out.println("White Pegs: " + compareCodes(GuessArray)[0]); 
		System.out.println("Black Pegs: " + compareCodes(GuessArray)[1]);
		System.out.println("----------");
		System.out.println();
		perms(colorCode, length, new StringBuffer());
		
		//while it doesn't equal length means it's not done 
		//stop finding when the guess array is the size of the length 
		while(compareCodes(GuessArray)[1]!=length){
			findMoves();
			System.out.println("White Pegs: " + compareCodes(GuessArray)[0]); 
			System.out.println("Black Pegs: " + compareCodes(GuessArray)[1]);
			//spacing for clarity in console 
			System.out.println("--------"); 
			System.out.println();
		}
		System.out.println("It took " +guesses +" guesses to find the solution.");
	}
	
	//method to reset the game, used if user wants to play again
	static void reset(){
		//basically just reinitializing the same variables 
		colorCode = "";
		secretCode = "BBBB";
		length = 0;
		maxColors = 0;
		firstguess = true;
		guesses = 0;
	}
	
	//boolean for either yes replay or no replay 
	static boolean replay(){ 
		String line="N";
		while (true){
			System.out.print("Would you like to play again? Enter \"Y\" for yes  or \"N\" for no :  "); 
			line=getInputLine(); 
			line = line.toUpperCase();
			if ("YN".indexOf(line)>=0){
				break;
			}
		}
		if (line.equals("Y")){
			System.out.print("\nLet me break another code!"); 
			//reset the game before starting again
			//the game does not have to be reset if you are ending the program 
			reset();
			return true;
		}
		return false;
	} //closing replay method 
 
	//getting user input: max number of colors they'd like to use: changes game difficulty for the computer 
	public static void getMaxColors(){
		String numberInput="";
		System.out.println("\nHow many colors from R,O,Y,G,B,I,V,P,T,B? Input an amount of colors from 1 to 10 using capital letters : ");
		numberInput=getInputLine();
		//parsing in the number of colors from the user: difficult of the game for the computer 
		maxColors=Integer.parseInt(numberInput);
		for(int i=0;i<maxColors;i++){
			//enter each color code (capital letter) into the maxColor array 
			colorCode = colorCode.concat(maxColorArray[i]);
		}
	}
	//storing input from user 
	public static void getPlayerGuess(){
		boolean inputDone=false;  //starting with not done, waiting for input 
		String codeInput=""; 		
		while (!inputDone){
			//if input is not done, prompt the user for input 
			System.out.println("Color Code : "+colorCode);
			//taking in the input number from the user and using it in the print statement 
			System.out.print("Enter a color combination with " +maxColors +" color codes: ");
			//taking in the user input and checking it for errors, input has to be valid colors
			codeInput=getInputLine();
			//other letters of the alphabet will result in an error, will loop back to original prompt 
		    String error=checkInput(codeInput);
			secretCode = codeInput;
			
			if (error.equals("true")){ //pulling in "true" from the return statement of the getInputLine method  
		        inputDone=true;
		      	System.out.println("Your color combination is: "+secretCode);
		      }
		      else
		    	//stop the input if there is an error 
		        System.out.println(error);
		} 
		System.out.println(); //spacing 
		codeArray = codeInput.toCharArray();
		length = codeArray.length;
	}//closing getPlayerGuess method 

	
	//if the guess is correct, make it part of the array 
	public static int[] compareCodes(char[] guessArray){
		//all the correct characters are returned in one string, not one at a time
		int[] correct = new int [2];
		//there are zero white and black to begin with 
		int white = 0;
		int black = 0;

		for (int i = 0; i < length; i++){
			if (guessArray[i] == codeArray[i]){
				//if the guess is in the user code array, increment black 
				black++;
			}
		}
		
		ArrayList<Integer> evaluated = new ArrayList<Integer>();
		for (int color : codeArray)
			for (int k = 0; k < length; k++)
				if (color == guessArray[k] && !evaluated.contains(k)){
					//add the color if it has not already been evaluated
					evaluated.add(k);
					//adding to white for each color added 
					white++;
					break;
				}
		white = white - black; 
		correct[0] = white;
		correct[1] = black;
		return(correct);
	} // closing compareCodes method 

	public static int[] compareCodes2(char[] guessArray){ 
		int[] correct = new int [2];
		int white = 0;
		int black = 0;

		for (int i = 0; i < length; i++)
			if (guessArray[i] == secretCodeArray[i])
				black++;
		ArrayList<Integer> evaluated = new ArrayList<Integer>();

		for (int color : secretCodeArray) //end of array is variable, based on input  
			for (int p = 0; p < length; p++)
				if (color == guessArray[p] && !evaluated.contains(p)){
					evaluated.add(p);
					//increment white if the evaluated color is not in the user array 
					white++;
					break;
				}
		//final white is white minus the count of the black pegs
		white = white - black;
		correct[0] = white;
		correct[1] = black;
		return(correct);
	} //closing compareCodes2 method 
	
	//method for generating the first guess 
		static void initialGuess(){
			String guess = "";
			for(int i=0;i<(length/2);i++){
				guess = guess.concat("B"); 
			}
			for(int j=(length/2);j<length;j++){
				guess = guess.concat("R"); 
			}
			firstguess = false;

			GuessArray = guess.toCharArray();
			for(int i=0;i<GuessArray.length;i++){
				System.out.printf("%s",GuessArray[i]);
			}
			System.out.println();
			secretCodeArray = guess.toCharArray();
			//the number of guesses increases for every wrong guess 
			guesses++;
		}

	static void findMoves(){
		int moveSize = possibleMoves.size();
		ArrayList<Integer> toDelete = new ArrayList<Integer>();

		if(possibleMoves.size()>1) {
			for(int i = 0; i < moveSize;i++){
				char[] temp = possibleMoves.get(i).toCharArray();
				if(Arrays.equals(compareCodes2(temp), (compareCodes(GuessArray)))==false){
					toDelete.add(i);
				}
			}
		}

		//converting from arraylist to array, code goes into non-ending loop without this  
		int move1 = toDelete.size();
		int[] temp = new int[move1];
		for (int i = 0; i < move1; i++) {
			temp[i] = toDelete.get(i).intValue();
		}
		for(int i = temp.length-1; i>=0;i--){
			possibleMoves.remove(temp[i]);
		}

		GuessArray = possibleMoves.get(0).toCharArray();

		for(int i = 0; i < GuessArray.length;i++){
			System.out.print(GuessArray[i]);	 
		}
		
		System.out.println();
		//when done guessing, the fakeCode Array is the GuessArray 
		secretCodeArray = GuessArray;
		//add to the guess count for each one 
		guesses++;
	}


	//string characters for the color letters are inputs and outputs 
	static void perms(String input, int depth, StringBuffer output) {
		if (depth == 0){
			possibleMoves.add(output.toString());
		} 
		else{
			for (int i = 0; i < input.length(); i++){
				output.append(input.charAt(i));
				perms(input, depth - 1, output);
				output.deleteCharAt(output.length() - 1);
			}
		}
	}
	
	//taking user input as a string 
	static String getInputLine(){
		//java technique: using try and catch around buffered reader 
		try{
			String line=new java.io.BufferedReader(new java.io.InputStreamReader(System.in)).readLine();
			return line;
		} 
		catch (Exception e){
			return ""; //can return empty string for string-type method 
		}
	}

	static int getColorIndex(char char1){
		//get the color index from the color code, return the character spot in the string 
		return colorCode.indexOf(char1);
	}
	
	//checking to make sure the string entered by the use has all valid characters 
		static String checkInput(String line){
		    boolean[] choice=new boolean[colorNames.length];
		    for (int i=0;i<line.length();i++){
		      char char2=line.charAt(i);
		      int m=getColorIndex(char2);
		      //
		      if (m==-1){
		    	  return "Not a valid color";
		      } else{ 
		      	choice[m]=true;
		      }
		    }
		    return "true";  
		  }
	
} //closing public class Mastermind_Rossney 

