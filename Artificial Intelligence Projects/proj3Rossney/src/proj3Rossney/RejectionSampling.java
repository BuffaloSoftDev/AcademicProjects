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
public class RejectionSampling extends Inference{
	
	public RejectionSampling(BNnode query,BN bnet){
		super(query,bnet);
		}
	
	public RejectionSampling(BNnode query,String evidence,BN bnet){ 
		super(query,evidence,bnet);
	}

	
	public double[] rejAsk() {
		//setting up answer vector
		ArrayList<String> queryVals = query.getVariable().getDomain();
		double[] probEach = new double[queryVals.size()];
		ArrayList<String> evCopy = new ArrayList<String>();
		for(int i = 0; i < evidenceVars.size(); i++)
			evCopy.add(evidenceVars.get(i).getVal());
		
		for(int i = 0; i <= 99999; i++) {
				this.priorSample();
				//check for consistency
				if(evConsistent(this.evidenceVars,evCopy)) {
					//note query variable
					probEach[query.getDomain().indexOf(query.getVal())]++;
				}
		}
		return ansVector(probEach);
	}
	
	public boolean evConsistent(ArrayList<BNnode> toCheck, ArrayList<String> checkAgainst) {
		for(int i = 0; i < toCheck.size(); i++) {
			if(!toCheck.get(i).getVal().equals(checkAgainst.get(i)))
				return false;
		}
		return true;
	}
	
	//prior sampling- sets all variables
	public void priorSample() {
		ArrayList<BNnode> nodes = BNnode.copyArray(this.bayesnet.getNodesSorted());
		for(int i = 0; i < nodes.size(); i++) {	
			double[] currentProb = nodes.get(i).getCPT().findSpots(nodes.get(i));
			int great = greatest(currentProb);
			nodes.get(i).setVariable(nodes.get(i).getDomain().get(great));
		}
	}
	
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

	//returning value based on likely outcome 
	public int greatest(double[] vector) {
		Random random = new Random();
		
		vector = ansVector(vector);
		double[][] range = new double[vector.length][2];
		double base = 0;
		for(int i = 0; i < range.length; i++) {
			range[i][0] = base;
			range[i][1] = base + vector[i];
			base= base+ vector[i];
		}
		
		double rn = random.nextDouble();
		int index = 0;
		for(int i = 0; i < vector.length; i++) {
			if(rn >= range[i][0] && rn < range[i][1])
				index = i;
		}
		return index;
	}
	
	public static void main(String[] args) throws IOException, ParserConfigurationException,SAXException {
		//creating a BN from parser data
		XMLBIFParser parser = new XMLBIFParser();
		BN network = parser.readNetworkFromFile(args[0]);

		BNnode query = network.getNodeByName(args[1]);
		RejectionSampling reject = null;
		
		while(args.length > 2) {
			reject = new RejectionSampling(query,args[2],network);
		}
		while(args.length<=2){
			reject = new RejectionSampling(query,network);
		}

		reject.answer = reject.rejAsk();
	}
}