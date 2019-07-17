package proj3Rossney;
import java.util.ArrayList;

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */

public class JointDistTree {
	private JointDistNode head;
	
	public JointDistTree(ArrayList<BNnode> unused,ArrayList<BNnode> used) {
		//initialize head
		head = new JointDistNode(unused.get(0),"head");
		
		//using conjunctions to increase tree depth 
		ArrayList<BNnode> conjunctVars = BNnode.copyArray(used);
		ArrayList<String> conjunctVals = new ArrayList<String>();
		for(int i = 0; i < conjunctVars.size(); i++)
			conjunctVals.add(conjunctVars.get(i).getVariable().getValue());
		
		BNnode currentVar = unused.get(0);
		ArrayList<String> currentDomain = currentVar.getVariable().getDomain();
		System.out.println("currentDomain size- "+currentDomain.size());
		for(int i = 0; i < currentDomain.size(); i++) {
			head.addChild(addVar(unused,currentDomain.get(i),conjunctVars,conjunctVals));
		}
	}
	
	public JointDistNode addVar(ArrayList<BNnode> varsLeft,String currentDomain,ArrayList<BNnode> conjunctVars,ArrayList<String> conjunctVals) {
		//add current variable to list of conjuncts and values
		ArrayList<BNnode> conjunctVars1 = BNnode.copyArray(conjunctVars);
		ArrayList<String> conjunctVals1 = copyArray(conjunctVals);
		conjunctVars1.add(varsLeft.get(0));
		conjunctVals1.add(currentDomain);

		//create this node
		JointDistNode currentNode = new JointDistNode(varsLeft.get(0),currentDomain,conjunctVars1,conjunctVals1);
				
		ArrayList<BNnode> varsLeft1 = BNnode.copyArray(varsLeft);
		varsLeft1.remove(0);
		
		//stopping adding when there are no more variables
		if(varsLeft1.size() == 0) {
			return currentNode;
		}
		//otherwise repeat for another layer (variable)
		else {
			for(int i = 0; i < varsLeft1.get(0).getVariable().getDomain().size(); i++) {
				//domain of next variable
				ArrayList<String> domain = varsLeft1.get(0).getVariable().getDomain();
				head.addChild(addVar(varsLeft1,domain.get(i),conjunctVars1,conjunctVals1));
			}
		}
			
		return currentNode;
	}
	
	public double sumProbs(BN bnet) {
		double sum = 0;
		ArrayList<JointDistNode> conjuncts = getLeaves();
		for(int i = 0; i < conjuncts.size(); i++) {
			sum= sum + solveConjunct(conjuncts.get(i),bnet);
		}
		return sum;
	}

	public double solveConjunct(JointDistNode conjunct,BN bnet) {
		double product = 1;
		System.out.println("solving a conjunction");
		for(int i = 0; i < conjunct.conjunctVars.size(); i++){
			BNnode current = bnet.getNodeByName(conjunct.conjunctVars.get(i).getName());
			current.setVariable(conjunct.conjunctVals.get(i));
		}
		for(int i = 0; i < conjunct.conjunctVars.size(); i++){
			BNnode current = bnet.getNodeByName(conjunct.conjunctVars.get(i).getName());
			product= product *(current.getCPT().findSpot(current.getParents()));
		}
		return product;
	}
	
	public ArrayList<JointDistNode> getLeaves() {
		ArrayList<JointDistNode> leaves = new ArrayList<JointDistNode>();
		for(JointDistNode child : head.children) {
			leaves.addAll(getLeavesRec(child));
		}
		return leaves;
	}
	public ArrayList<JointDistNode> getLeavesRec(JointDistNode node) {
		ArrayList<JointDistNode> leaves = new ArrayList<JointDistNode>();
		if(node.children.isEmpty()) {
			leaves.add(node);
			return leaves;
		}
		else {
			for(JointDistNode child : node.children) {
				leaves.addAll(getLeavesRec(child));
			}
		}
		return null;
	}
	
	public ArrayList<String> copyArray(ArrayList<String> parents) {
		ArrayList<String> copy = new ArrayList<String>();
		for(int i = 0; i < parents.size(); i++)
			copy.add(parents.get(i));
		return copy;
	}
	
	private class JointDistNode {
		public String val,name;
		public ArrayList<JointDistNode> children;
		public double probability;
		public ArrayList<BNnode> conjunctVars;
		public ArrayList<String> conjunctVals;
		
		public JointDistNode(BNnode var,String val) {
			this.val = val;
			name = var.getName();
			children = new ArrayList<JointDistNode>(var.getVariable().getDomain().size());
			this.probability = -1;
			this.conjunctVars = null;
		}
		
		public JointDistNode(BNnode var,String val,ArrayList<BNnode>	conjunctVars,ArrayList<String> conjunctVals) {
			this.val = val;
			name = var.getName();
			children = new ArrayList<JointDistNode>(var.getVariable().getDomain().size());
			this.probability = -1;
			this.conjunctVals = conjunctVals;
			this.conjunctVars = conjunctVars;
		}
		
		public void addChild(JointDistNode child) {
			this.children.add(child);
		}
		
		public void addProb(double probability) {
			this.probability = probability;
		}
		
	}
}
