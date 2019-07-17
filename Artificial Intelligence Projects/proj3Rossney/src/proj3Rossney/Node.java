package proj3Rossney;

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */
import java.util.ArrayList;

public class Node {
	public static final short ELEMENT_NODE = 0;
	public static short ELEMENT_NODE1;
	public static short TEXT_NODE;
	private RandomVariable variable;
	private ArrayList<Node> parents = null;
	
	
	public Node(){
		if(parents != null)
			this.parents.addAll(parents);
		else
			this.parents = null;
	}
	
	public void addParent(Node parent) {
		this.parents.add(parent);
	}
	
	public String getName() {
		return variable.getName();
	}
	public RandomVariable getVariable() {
		return variable;
	}

	public short getNodeType() {
		return 0;
	}
}
