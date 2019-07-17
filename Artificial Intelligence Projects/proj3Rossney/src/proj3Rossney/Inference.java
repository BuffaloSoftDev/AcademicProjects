package proj3Rossney;

import java.util.ArrayList;

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */


public class Inference{
	
	BN bayesnet;
	BNnode query;
	ArrayList<BNnode> evidenceVars; 
	double[] answer;
	
	//no evidence 
		public Inference(BNnode query,BN bayesnet) {
			this.query = query;
			this.bayesnet = bayesnet;
			evidenceVars = new ArrayList<BNnode>();
		}
		
	//evidence 
	public Inference(BNnode query,String evidence,BN bayesnet) {
		this.query = query;
		this.bayesnet = bayesnet;
		this.setUpEvidence(evidence);
		evidenceVars = new ArrayList<BNnode>();
		
	}
	
	//converts string of evidence into BNnodes
	public void setUpEvidence(String evidence) {
		ArrayList<String[]> done = new ArrayList<String[]>();
		String[] eClauses = evidence.split(",");
		for(int i = 0; i < eClauses.length; i++) {
			String[] eClause = eClauses[i].split("=");
			done.add(eClause); 
		}
		
		for(String[] clause : done) {
			BNnode current = bayesnet.getNodeByName(clause[0]);
			current.setVariable(clause[1]);
			evidenceVars.add(current);
		}
	}
	
	public double[] ansVector(double[] vector) {
		double sum = 0;
		for(int i = 0; i < vector.length; i++) {
			sum= (sum + vector[i]);
		}
		double alpha = (double) (1.0/sum);
		double[] returnIt = new double[vector.length];
		for(int i = 0; i < vector.length; i++) {
			vector[i] = ((vector[i])*alpha);
			returnIt[i] = vector[i];
		}
		//System.out.println(returnIt); 
		return returnIt;
		
	}
} //end of public class Inferecne 


