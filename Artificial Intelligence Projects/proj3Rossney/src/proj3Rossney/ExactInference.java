package proj3Rossney;

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */

import java.util.ArrayList;

//PART ONE
public class ExactInference{
	
	BN bayesnet; 
	BNnode query;
	ArrayList<String[]> evidence;
	ArrayList<BNnode> evidenceVars;
	
	private void EvidenceEnum() {
		evidenceVars = new ArrayList<BNnode>();
		for(String[] clause : evidence) {
			BNnode current = bayesnet.getNodeByName(clause[0]);
			current.setVariable(clause[1]);
			evidenceVars.add(current);
		}
	}
	
	public ExactInference(BNnode query,ArrayList<String[]> evidence,BN bayesnet) {
		this.query = query;
		this.evidence = evidence;
		this.bayesnet = bayesnet;
		
		EvidenceEnum();
	}
	
	public double getProbability() {
		return result();
	}
	
	public double result(){
		ArrayList<BNnode> filled = BNnode.copyArray(bayesnet.getNodes());
		ArrayList<BNnode> unfilled = BNnode.copyArray(bayesnet.getNodes());
		
		//new instance of joint distribution tree 
		JointDistTree sumTree = new JointDistTree(filled,unfilled);
		double answer =  (double) sumTree.sumProbs(bayesnet);
		return answer;
	}

}
