
/*
 * George Rossney
 * CSC 242
 * Project 2: Automated Reasoning
 * Submitted: 3/16/16
 */


public class HornClauses {
	/*
	 * If the unicorn is mythical, then it is immortal, but if
	it is not mythical, then it is a mortal mammal. If the unicorn is either immortal or a
	mammal, then it is horned. The unicorn is magical if it is horned.
	 */
	
	//setting variables in English to symbols for prop logic
	static String U= "unicorn";
	static String M= "mammal";
	static String My="mythical";
	static String Ma="magical";
	static String H="horned";
	static String I="immortal"; 
	
	public static boolean inference(int sat){
		if(U.equals(Ma)){
			U.equals(I); 
		}else if(U.isEmpty()){
			System.out.println("unicorn is a mortal mammal");	
		}else if(U.equals(I)||U.equals(M)){
			System.out.println("unicorn is horned");
		}else if(U.equals(H)){
			System.out.println("magical unicorns are horned");
		}
		//no case satisfied.
		return false;
	}
	
	

} 
