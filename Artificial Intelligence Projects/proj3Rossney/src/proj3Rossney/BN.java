package proj3Rossney;

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */

import java.util.ArrayList;

//main file for Bayesian network structure 
public class BN{
	
	//constructing tree as an arrayList for the network 
	private ArrayList<BNnode> tree;
	
	public BN(){ 
		tree = new ArrayList<BNnode>();
	}

	public void addBNode(BNnode BNode){ 
		tree.add(BNode);
	}
	
	public ArrayList<BNnode> getNodes() {
		return tree;
	}
	
	//filing nodes into arrayList by checking for visited nodes 
	public ArrayList<BNnode> getNodesSorted() {	
		ArrayList<BNnode> empty = new ArrayList<BNnode>(tree.size());
		ArrayList<BNnode> end = new ArrayList<BNnode>(tree.size());
		for (BNnode node : tree) {
			while(getChildren(node).isEmpty()) {
				end.add(node);
			}
		}	
		ArrayList<BNnode> visited = new ArrayList<BNnode>(tree.size()); 
			for (BNnode n : end) {
				visit(n, empty, visited);
			}
		return empty;
		}

	protected void visit(BNnode n, ArrayList<BNnode> empty, ArrayList<BNnode> visited) {
		//go to what hasn't been visited 
		if (!visited.contains(n)) {
			visited.add(n);
			//handling edges 
			for (BNnode edge : tree) {
				if (getChildren(edge).contains(n)) {
					visit(edge, empty, visited);
				}
				if (getChildren(edge).contains(visited)){
					visit(edge,visited,visited); 
				}
			}	
			empty.add(n);
		}
	}

	public BNnode getNodeByName(String name) {
		for(BNnode c : tree) {
			if(c.getName().equals(name) || c.getName().contains(name))
				return c;
				System.out.print(c); 
		System.out.print("null"); 
		return null;
		}
		return null;
	}

	
	public ArrayList<BNnode> getChildren(BNnode parent) {
		ArrayList<BNnode> nodes = this.getNodes();
		ArrayList<BNnode> children = new ArrayList<BNnode>();
		for(int i = 0; i < nodes.size(); i++) {
			if(!nodes.get(i).getName().equals(parent.getName()) && nodes.get(i).getParents().contains(parent)){
				children.add(nodes.get(i));
				}
		}
		return children;
	}
/*
//------code below is from Prof. Ferguson's "Bayesian network" file------
//Printable

/**
 * Print this BayesianNetwork to the given PrintWriter.
 * Let's see. The right thing is to do a topological sort of the
 * network, which must be a DAG, then output the nodes in some
 * way that brings out the hierarchy. Or not.
 
public void print(PrintWriter out) {
for (Node node : nodes) {
    node.variable.print(out);
    out.print(" <- ");
    if (node.parents != null) {
	for (Node pnode : node.parents) {
	    pnode.variable.print(out);
	    out.print(" ");
	}
    }
    out.println();
    if (node.cpt != null) {
	// Might not want this if it clutters things up...
	node.cpt.print(out);
    }
}
}


/**
 * Print this BayesianNetwork to the given PrintStream.

	public void print(PrintStream out) {
		PrintWriter writer = new PrintWriter(out, true);
		print(writer);
		writer.flush();
		}

/**
 * Print this BayesianNetwork to System.out.
public void print() {
print(System.out);
}

/**
 * Return the string representation of this BayesianNetwork.

	public String toString() {
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		print(out);
		out.flush();
		return writer.toString();	
		}

 */
} //end of public class BN 
