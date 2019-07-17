package proj3Rossney;
import java.util.*;

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */

public class RandomVariable{

	private String name;
	private ArrayList<String> domain;
	//private ArrayList<Integer> domainVals;
	private String value = null;
	
	public RandomVariable(String name){
		this.name = name;
		}
	
	public RandomVariable(String name, ArrayList<String> domain, String value){
		this.name = name;
		this.domain = domain;
		
		if(domain.contains(value))
			this.value = value;
		else
			System.out.println("error, attempted assignment outside of domain on "+name);
		
	}
	
	public RandomVariable(String name, ArrayList<String> domain){
		this.name = name;
		this.domain = domain;
		}
	
	public String getName() 
	{return name;}
	
	public ArrayList<String> getDomain(){
		return domain;
		}
	public void setValue(String val){
		this.value = val;
		}

	public String getValue(){
		return this.value;
		}
	
}
