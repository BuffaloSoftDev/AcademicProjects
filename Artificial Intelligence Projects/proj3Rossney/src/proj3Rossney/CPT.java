package proj3Rossney;

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */

import java.util.*;
/**
 * A CPT is a conditional probability table, as stored in a Node of a
 * BayesianNetwork. A CPT enumerates all possible combinations of
 * values for its <q>given</q> (conditioning) and <q>query</q>
 * variables. For each combination, a CPT stores the conditional
 * probability of the query variable value given the values of the
 * conditioning variables.
 * <p>
 * This implementation uses a tree of Entries. The root Entry is for the first
 * conditioning variable. It is a Dimension with an Entry for each possible
 * value of the variable. Each of these second-level Entries is a Dimension
 * for the second conditioning variable. And so on. The final level of the
 * tree corresponds to the values of the query variable, and its Entries
 * are all ProbabilityValues containing a double value.
 * <p>
 * This implementation does not impose any requirements on the order in
 * which variables and values are presented. That makes it easier to
 * use for inference, but at some cost in efficiency.
 * <p>
 * There are many, many ways one could be more efficient about
 * this, for example encoding the values as ordinals (1, 2, 3, etc.) and
 * then encoding the combinations of values as bit-patterns used as indexes
 * into an array. Since this is a crucial data structure for doing
 * inference with Bayesian Networks, you might want/need to improve it.
 */
public class CPT{
	
	private CPTNode root;

	public CPT(ArrayList<RandomVariable> vars) {
		root = new CPTNode(vars.get(0),"first node");
		for(int i = 0; i < vars.get(0).getDomain().size(); i++) {
			root.addChild(addVar(vars,vars.get(0).getDomain().get(i)));
		}
	}
	
	//adding child nodes 
	public CPTNode addVar(ArrayList<RandomVariable> varsLeft,String parentDomain) {
		CPTNode currentNode = new CPTNode(varsLeft.get(0),parentDomain);
		ArrayList<RandomVariable> varsLeft1 = new ArrayList<RandomVariable>();
		varsLeft1.addAll(varsLeft);
		varsLeft1.remove(0);
		//end adding when remaining is zero 
		if(varsLeft1.size() == 0)
			return currentNode;
		//keep adding children
		else {
			for(int i = 0; i < varsLeft1.get(0).getDomain().size(); i++) {
				currentNode.addChild(addVar(varsLeft1,varsLeft1.get(0).getDomain().get(i)));
			}
		}	
		return currentNode;
	}
	public double[] getProbs(){
		ArrayList<CPTNode> leaves = getLeaves();
		double[] toReturn = new double[leaves.size()];
		for(int i = 0; i < leaves.size(); i++)
			toReturn[i] = leaves.get(i).getProb();
		return toReturn;
	}
	//returning probabilities 
	public ArrayList<CPTNode> getLeaves() {
		ArrayList<CPTNode> leaves = new ArrayList<CPTNode>();
		//System.out.println(head.children.size());
		for(CPTNode child : root.children) {
			getLeaves(child,leaves);
		}
		return leaves;
	}
	
	public void getLeaves(CPTNode node,ArrayList<CPTNode> leaves) {
		if(node.children.size()==0) {
			leaves.add(node);
		}else {
			for(CPTNode child:node.children) {
				getLeaves(child,leaves);
			}
		}
	}
	
	public BNnode findBNode(ArrayList<BNnode> parents, RandomVariable lookFor) {
		for(int i = 0; i < parents.size(); i++) {
			if(parents.get(i).getName().equals(lookFor.getName()))
				return parents.get(i);
		}
		return null;
	}
	
	//adds probabilities
	public void addProbs(ArrayList<Double> probabilities) {
		ArrayList<CPTNode> leaves = this.getLeaves();
		for(int i = 0; i < probabilities.size(); i++)
			leaves.get(i).addProb(probabilities.get(i));
			
	}
	public double[] findSpots(BNnode node) {
		double[] toRet = new double[node.getDomain().size()];
		String hold = node.getVal();
		for(int i = 0; i < node.getDomain().size(); i++) {
			node.setVariable(node.getDomain().get(i));
			ArrayList<BNnode> ar = new ArrayList<BNnode>();
			ar.addAll(node.getParents());
			toRet[i] = findSpot(ar);
		}
		node.setVariable(hold);
		return toRet;
	}
	public double findSpot(ArrayList<BNnode> conjunct) {
		CPTNode dest = root.children.get(0);
		BNnode currentVar = findBNode(conjunct,dest.var);
		
		//asign child 
		for(int i = 0; i < root.children.size(); i++) {
			CPTNode currentChild = root.children.get(i);
			if(currentChild.val.equals(currentVar.getVariable().getValue())) {
				return findSpotRec(conjunct,currentChild);
			}
		}
		return 0;
	}
	
	public double findSpotRec(ArrayList<BNnode> conjunct, CPTNode currentNode) {
		if(currentNode.children.size() == 0)
			return currentNode.probability;
		
		CPTNode goTo = currentNode.children.get(0);
		BNnode currentVar = findBNode(conjunct,goTo.var);
		
		for(int i = 0; i < currentNode.children.size(); i++) {
			CPTNode currentChild = currentNode.children.get(i);
			if(currentChild.val.equals(currentVar.getVariable().getValue())) {
				return findSpotRec(conjunct,currentChild);
			}
		}
		return 0;
	}
		//print the tree
		public void print() {
			root.print();
			System.out.println("children = "+root.children.size());
			for(int i = 0; i < root.children.size(); i++) {
				printRec(root.children.get(i),1);
			}
		}
		
		public void printRec(CPTNode node,int depth) {
			node.print();
			if(node.children.size()>0){
				System.out.println("onto children");
				for(int i = 0; i < node.children.size(); i++) {
					printRec(node.children.get(i),depth++);
				}
			}
		}
		
	//nodes for tree to make CPT 
	private class CPTNode {
		private ArrayList<CPTNode> children;
		private String val;
		private double probability;
		private RandomVariable var;
		
		public CPTNode(RandomVariable var,String val) {
			this.var = var;
			this.val = val;
			this.probability = -1;
			children = new ArrayList<CPTNode>(var.getDomain().size());
			
		}
		
		public void addChild(CPTNode child){
			this.children.add(child);	
			}
		
		public void addProb(double probability){
			this.probability = probability;
			}
		
		public double getProb(){
			return this.probability;
			}
		
		public void print() {
			if(probability != -1) {
				System.out.print("val = " +val+" and probability = "+probability);
			}
		}
	}
} //end of public class CPT 
