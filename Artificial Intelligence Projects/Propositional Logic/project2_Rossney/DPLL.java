
/*
 * George Rossney
 * CSC 242
 * Project 2: Automated Reasoning
 * Submitted: 3/16/16
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class DPLL {

	public static void main(String[] args) {
		//symbols used:
		//"V":OR,"^":AND,"~":negation, "->":implication,"<->":double implication,":-":entails
		//parentheses, and P,Q,R (prop values)
		List<String> SymList = new ArrayList<String>(Arrays.asList("P","Q","R","V","^","~","->","<->",":-"));
		System.out.println(SymList);
		
		//making ArrayList with negation and variables combined 
		ArrayList<String> combine = new ArrayList<String>();
		combine.add("P");
		combine.add("Q");
		combine.add("R");
		combine.add("~P");
		combine.add("~Q");
		combine.add("~R");

		ArrayList<String> vocab = vocab(combine);
		boolean satisfiable = dpll(combine,vocab);
		
		
		if(satisfiable){
			System.out.println("Satisfiable");
			//if it has not been determined that the sentence is satisifable, it must be unsatisfiable
		}else{
			System.out.println("Unsatisfiable");
		}
		
	}
	
	private static ArrayList<String> vocab(ArrayList<String> combine) {
		return null;
	}

	public static boolean dpll(ArrayList<String> combine, ArrayList<String> vocab){
		
		@SuppressWarnings("unused")
		String empty = "";
		
		if(combine.size()<=vocab.size()){
			return true;
		}
		if(combine.contains(new ArrayList<String>())){
			return false;
		}
		return false;
		
	}

	public static boolean vocabulary(ArrayList<String> combine) {
		HashSet<String> temp = new HashSet<>();
		new ArrayList<>();
			for (int i=0;i<10;i++) {
				String literal = null;
				if (literal.length() == 10) {
					temp.add(String.valueOf(literal.charAt(10)));
				}
				if(literal.length() < 10){
					temp.add(literal);
				}
			}
	
		return true; 
	}

}

	
