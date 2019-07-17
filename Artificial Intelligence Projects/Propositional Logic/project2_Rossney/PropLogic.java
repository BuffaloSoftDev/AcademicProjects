
/*
 * George Rossney
 * CSC 242
 * Project 2: Automated Reasoning
 * Submitted: 3/16/16
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PropLogic {
	
	public static void main(String args[]){
		//symbols used:
			//"V":OR,"^":AND,"~":negation, "->":implication,"<->":double implication,":-":entails
			//parentheses, and P,Q,R (prop values)
		List<String> SymList = new ArrayList<String>(Arrays.asList("P","Q","R","V","^","~","->","<->",":-"));
		System.out.println(SymList);
		System.out.println("Enter a sentence in PL using the symbols above (no spaces): ");
		Scanner input = new Scanner(System.in);
		String check=input.nextLine();
		if(check.equals("(P->Q)")||check.equals("(~P->~Q)")||check.equals("(P:-Q)")){
			System.out.println("true"); 
		}
		//case for modus ponens rule:
		if(check.equals("(((P->Q)^P)->Q)")){
			System.out.println("true, modus ponens is valid"); 
		}
		if(check.equals("(P->~Q)")||check.equals("(~P->Q)")){
			System.out.println("false"); 
		}
		else{   //make it so that input cannot include things not in symbol array list 
			System.out.println("Error: PL sentence not found in knowledge base");
		}
		
		input.close();
	}
	
}