package proj3Rossney;

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */

import java.io.IOException;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class EnumerationQuery extends Inference{
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		EnumerationQuery EQ = null;
		
		//calling a new instance of the parser to read in BN data
		XMLBIFParser parser = new XMLBIFParser();
		//reading in data to make Bayesian Network 
		BN network = parser.readNetworkFromFile(args[0]);
		
		BNnode query = network.getNodeByName(args[1]);
		
		while(args.length > 2) {
			EQ = new EnumerationQuery(query,args[2],network); 
		}
		while(args.length<=2){
			EQ = new EnumerationQuery(query,network);
			}
		
		EQ.answer = EQ.getAllProbability();
	} //end of main method 
	
	//calculating probability for exact inference 
	public EnumerationQuery(BNnode query,BN bnet){
		super(query,bnet);
		}
	
	public EnumerationQuery(BNnode query,String evidence,BN bnet){
		super(query,evidence,bnet);
		}

	public double[] getAllProbability() {
		this.evidenceVars.add(query);
		double toReturn[] = new double[(query.getDomain().size())];
		//iterating thru query values 
		for(int i = 0; i < toReturn.length; i++) {
			this.query.setVariable(query.getDomain().get(i));
			toReturn[i] = enumerateAll(bayesnet.getNodes(),evidenceVars);
		}
		evidenceVars.remove(query);
		return ansVector(toReturn);
	}
	public double enumerateAll(ArrayList<BNnode> vars,ArrayList<BNnode> evidenceVars) {
		if(vars.size()==0){
			return 1;
		}
		
		BNnode currentVar = vars.get(0);
		ArrayList<BNnode> restVars = BNnode.copyArray(vars);
		restVars.remove(0);
		
		//only check values not already in evidence 
		double containment= currentVar.getCPT().findSpot(currentVar.getParents())*enumerateAll(restVars,evidenceVars);
		if(evidenceVars.contains(currentVar)){
			return containment; 
			}
		else {
			double sum = 0;
			ArrayList<BNnode> evAdd = BNnode.copyArray(evidenceVars);
			evAdd.add(currentVar);
			for(int i = 0; i < currentVar.getDomain().size(); i++) {
				currentVar.setVariable(currentVar.getDomain().get(i));
				sum= sum + (currentVar.getCPT().findSpot(currentVar.getParents()) * enumerateAll(restVars,evAdd));
			}
			return sum;
		}
	}
	
}
