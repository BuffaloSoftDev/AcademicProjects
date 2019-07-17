package proj3Rossney;

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */


import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

//Part II: Approximate Inference 
public class LikelihoodWeighting extends Inference{
	
	public LikelihoodWeighting (BNnode query,BN bayesnet){
		super(query,bayesnet);
		}
	
	public LikelihoodWeighting(BNnode query,String evidence,BN bayesnet){
		super(query,evidence,bayesnet);
		}
	
	public double[] rejAsk() {
		//set up answer vector
		ArrayList<String> queryVals = query.getVariable().getDomain();
		int[] foundEach = new int[queryVals.size()];
		
		double[] probEach = new double[queryVals.size()];
		ArrayList<String> evCopy = new ArrayList<String>();
		for(int i = 0; i < evidenceVars.size(); i++)
			evCopy.add(evidenceVars.get(i).getVal());
		
		//for a number of iterations, sample a value for each value and note the value of the query variable
		for(int i = 0; i <= 990000; i++) {
				this.priorSample();
				probEach[query.getDomain().indexOf(query.getVal())]+=lhw;
				
		}
		return ansVector(probEach);
	}
	
	public boolean evConsistent(ArrayList<BNnode> toCheck, ArrayList<String> checkAgainst) {
		for(int i = 0; i < toCheck.size(); i++) {
			System.out.println("checking "+toCheck.get(i).getName()+" "+toCheck.get(i).getVal()+" against "+checkAgainst.get(i)+" "+checkAgainst.get(i));
			if(!toCheck.get(i).getVal().equals(checkAgainst.get(i)))
				return false;
		}
		System.out.println("consistent");
		return true;
	}
	double lhw;
	
	//setting all variables 
	public void priorSample() {
		lhw = 1;
		ArrayList<BNnode> nodes = BNnode.copyArray(this.bayesnet.getNodesSorted());
		for(int i = 0; i < nodes.size(); i++) {	
			if(this.evidenceVars.contains(nodes.get(i))) {
				//weighting is done by multiplication 
				lhw = lhw*(nodes.get(i).getCPT().findSpot(nodes.get(i).getParents()));
			}
			else {
				double[] currentProb = nodes.get(i).getCPT().findSpots(nodes.get(i));
				
				int great = greatest(currentProb);
				nodes.get(i).setVariable(nodes.get(i).getDomain().get(great));
			}
		}
	}
	
	//accessing child nodes 
	public ArrayList<BNnode> getChildren(BNnode parent) {
		ArrayList<BNnode> nodes = bayesnet.getNodes();
		ArrayList<BNnode> children = new ArrayList<BNnode>();
		for(int i = 0; i < nodes.size(); i++) {
			if(!nodes.get(i).getName().equals(parent.getName()) && nodes.get(i).getParents().contains(parent)) {
				children.add(nodes.get(i));
				}
		}
		return children;
	}

	//returns a random spot in the vector provided, with a bias towards more likely outcomes
	public int greatest(double[] vector) {
		Random random = new Random();
		
		vector = ansVector(vector);
		double[][] range = new double[vector.length][2];
		//give each spot a range
		double base = 0;
		for(int i = 0; i < range.length; i++) {
			range[i][0] = base;
			range[i][1] = base + vector[i];
			base = base + vector[i];
		}
		
		double rn = random.nextDouble();
		//double greatest = 0;
		int index = 0;
		for(int i = 0; i < vector.length; i++) {
			if(rn >= range[i][0] && rn < range[i][1])
				index = i;
		}
		return index;
	}
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		LikelihoodWeighting lhw2 = null;
		
		//creating new instance of BN using parser data
		XMLBIFParser parser = new XMLBIFParser();
		BN network = parser.readNetworkFromFile(args[0]);
		
		BNnode query = network.getNodeByName(args[1]);

		while(args.length > 2) {
			lhw2 = new LikelihoodWeighting (query,args[2],network);
		}
		while(args.length<= 2){
			lhw2 = new LikelihoodWeighting (query,network);
		} 
		
		lhw2.answer = lhw2.rejAsk();
	}
}