/* 
 George Rossney 
 Project 2: Infix Calculator  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/
package calcpackage; 

import java.io.*;
import java.util.*;

public class Main{
	public static void main( String[] args){ 
		//accessing the input file for the calculator: file provided on BlackBoard 
		File file = new File("infix_expr_short.txt"); 
		//using try & catch to read the file, in case of an exception is needed 
		try{
			//scan files, collect number of equations (lines) 
			@SuppressWarnings("resource") //suggested by Eclipse 
			Scanner scan1 = new Scanner(file);
			int length = 0;  //zero equations to start 
			while (scan1.hasNextLine()) {
				length++;
				scan1.nextLine();
			}
			//array for the lines in the file 
			String[] lines = new String[length];
			scan1 = new Scanner(file);
			
			//solving the equation at each line 
			for(int i = 0; i < length;i++){
				lines[i] = scan1.nextLine();
				//splicing at each line 
				String[] splice = spliceExp(lines[i]);
				
				//defining the calculator 
				InfixCalc calc = new InfixCalc(splice);
				//converting to postfix 
				calc.postfix = calc.toPost(calc.input,calc.postfix);
				//calculating the answer  
				Double answer = calc.CalcExp(calc.postfix,calc.result);

				System.out.println("line "+(i+1)+" answer is: ");
				System.out.println(answer);
			}
		}
		//Exception for file not found 
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
}
	
	//Method to splice, instead of using StringTokenizer 
	public static String[] spliceExp(String input){
		//calculate the size of the return array
		LinkedList<String> list = new LinkedList<String>(); 
		String space = "";
		for(int i = 0; i < input.length();i++){
			//different case for each operator 
			switch (input.charAt(i)){
			//Inserting the operator into the list for each case of the operator
				case '+': 
					list.insert(space);             
					list.insert("+");
					space = "";
					break;
				case '-': 
					list.insert(space);
					list.insert("-");
					space= "";
					break;
				case '*': 
					list.insert(space);
					list.insert("*");
					space = "";
					break;
				case '/': 
					list.insert(space);
					list.insert("/");
					space = "";
					break;
				case '<': 
					list.insert(space);
					list.insert("<");
					space = "";
					break;
				case '>': 
					list.insert(space);
					list.insert(">");
					space = "";
					break;
				case '=': 
					list.insert(space);
					list.insert("=");
					space = "";
					break;
				case ')': 
					list.insert(space);
					list.insert(")");
					space = "";
					break;
				case '(': 
					list.insert(space);
					list.insert("(");
					space = "";
					break;
				case '!': 
					list.insert(space);
					list.insert("!");
					space = "";
					break;
				case '&': 
					list.insert(space);
					list.insert("&");
					space = "";
					break;
				case '|': 
					list.insert(space);
					list.insert("|");
					space = "";
					break;
				//way to ignore spaces in the line, no insertion  
				case ' ':
					break;
				//put the number in space, since it can hold different amount of characters 
				default:
					space += Character.toString(input.charAt(i));
					break;
			}
		}
		list.insert(space);

		//taking the linked list as an array
		//the lines of the text file are taken in as an array 
		int length = list.getLength(list.callHead(),0)-1;
		String[] returned = new String[length];
		return list.toString(list.callHead().next,0,length,returned);
	} //closing spliceExp method 
	
	//function for printing 
		public static <AnyType> void print(AnyType input){
			System.out.println(input);
		}
}
