
/*
 * George Rossney
 * CSC 242
 * Project 2: Automated Reasoning
 * Submitted: 3/16/16
 */

public class ModusPonens {
	
	//modus ponens rule: (((P->Q)^P)->Q)
	
	public void MPrule(String input){
		String modusponens="(((P->Q)^P)->Q)"; 
		String modusponens2="((P->Q)^P)->Q";
	
		if(input.equals(modusponens)||input.equals(modusponens2)){
			System.out.println("Modus ponens is satisfiable");
		}else{
			System.out.println("Rule unsatisfiable"); 
		}
		
	}
	
}
