package proj3Rossney;

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */

import java.util.ArrayList;
import java.util.StringTokenizer;


public class BNnode {
	
	private ArrayList<BNnode> parents = null;
	private RandomVariable variable;
	private CPT cpt;
	
	
	public BNnode(RandomVariable variable) {
		this.parents = new ArrayList<BNnode>();
		this.variable = variable;

	}
	
	//probability for CPT
	public void addProbs(String str){
		StringTokenizer tokens = new StringTokenizer(str);
		ArrayList<Double> set = new ArrayList<Double>();
		while (tokens.hasMoreTokens()) {
		    String token = tokens.nextToken();
		    set.add(Double.parseDouble(token));
		}
		cpt.addProbs(set);
	}

	public void addParents(ArrayList<BNnode> parents) {
		for(BNnode p : parents)
			this.parents.add(p);
	}
	
	//building CPT from random variables 
	public void buildCPT() {
		ArrayList<RandomVariable> totalVar = new ArrayList<RandomVariable>();
		for(BNnode node : this.parents) {
			totalVar.add(node.getVariable());
		}
		cpt = new CPT(totalVar);
	}
	
	//accessors: 
	public String getName(){
		return variable.getName();
		}
	
	public RandomVariable getVariable(){
		return variable;
		}
	
	public void setVariable(String val){
		variable.setValue(val);
		}
	
	public CPT getCPT(){
		return this.cpt;
		}
	
	public String getVal(){
		return this.variable.getValue();
		}
	
	public ArrayList<String> getDomain(){
		return this.variable.getDomain();
		}
	
	public ArrayList<BNnode> getParents(){ 
		return this.parents;
		}
	
	public static ArrayList<BNnode> copyArray(ArrayList<BNnode> toCopy){
		ArrayList<BNnode> toReturn = new ArrayList<BNnode>();
		toReturn.addAll(toCopy);
		return toReturn;
	}
	
	public void print() {
		for(BNnode p : parents){
			System.out.println("parents: "+p); 
			}
		System.out.println("Conditional Probability Table");
		cpt.print();
		}
} //end of public class BNnode
