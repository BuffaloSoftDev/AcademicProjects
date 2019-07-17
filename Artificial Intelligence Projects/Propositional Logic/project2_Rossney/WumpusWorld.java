
/*
 * George Rossney
 * CSC 242
 * Project 2: Automated Reasoning
 * Submitted: 3/16/16
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WumpusWorld {
	
	//using facts given in projec pdf 
	List<String> WWVars = new ArrayList<String>(Arrays.asList(
			"P11","P12","P21","P22","P31","B11","B21"));
	List<String> negatedWWVars = new ArrayList<String>(Arrays.asList(
			"~P11","~B11"));
	
	String case1="~P11";
	String case2="B11 <-> (P12 V P21)";
	String case3="P11 V P22 V P31";
	String case4="~B11";
	String case5="B21";
	{
	
	if(case2.equals(true)&&case3.equals(true)){
		System.out.println("true,satisfiable");
	}
}

}