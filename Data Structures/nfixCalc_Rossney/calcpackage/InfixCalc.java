/* 
 George Rossney 
 Project 2: Infix Calculator  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/
package calcpackage; 

public class InfixCalc {
	
	//Setting up the structure of the infix to postfix conversion and calculation
	//the Queue holds the postfix 
		public Queue<String> postfix; 
		//the Stacks holds the operators 
		public Stack<String> stack;
		//the result 
		public Stack<Double> result;	
	//constructing calc: using stack & queue
		public InfixCalc(String[] in){
			input = in;
			stack = new Stack<String>();
			postfix = new Queue<String>();
			result = new Stack<Double>();	
		}
	//Setting up the operators that are used in the calculator 
	//string array for operators 
	public String[] input; 
	//all possible operators for the equations 
	public String[] operators = {"(",")","*","/","+","-","<",">","=","!","&","|"};
	//PEMDAS: order of operations for the operators: 1st, 2nd, 3rd, etc.
	public int[] pemdas = { 1,  1,  2,  2,  3,  3,  4,  4,  5,  5,  6,  6 };
	//number of operands needed for each operator, ex.- you need 2 operands for the "+" operator 
	public int[] numOperands = { 0,  0,  2,  2,  2,  2,  2,  2,  2,  1,  2,  2 };
	// the 3 arrays above should match up, 12 elements in each 
	
	//Infix to postfix method, casting for String 
	public Queue<String> toPost(String[] input,Queue<String> postfx){
		//keeping evaluating for the number of inputs 
		for(int i = 0; i < input.length; i++){
			//checking to see if the character is an operator 
			if(checkIn(input[i],operators) != -1){
				//if the stack is empty, then queue the operator 
				if(stack.read() == null)
	         	stack.push(input[i]);
				
				//queue the "C" characters  
				else if(input[i].equals("("))
					stack.push(input[i]);
				//for ")" characters 
				else if(input[i].equals(")")){
					while(!(stack.read().toString().equals("(")))
						postfix.enqueue(stack.pop().toString());
					stack.pop();
				}
				
				//queue from the top of the stack for lower pemdas order 
				else if ((stack.read() != null) && (!stack.read().toString().equals("("))
						&& (pemdas[checkIn(input[i], operators)] >= pemdas[checkIn(stack.read().toString(), operators)])){
					while((stack.read() != null) && (!stack.read().toString().equals("("))
							&& (pemdas[checkIn(input[i], operators)] >= pemdas[checkIn(stack.read().toString(), operators)]))
						postfix.enqueue(stack.pop().toString());
					stack.push(input[i]);
				}
				//add to the stack for higher pemdas order 
				else
					stack.push(input[i]);
			}
			//queue the operands 
			else
				postfix.enqueue(input[i]);
		}
		//queue what's in the stack 
		while((stack.read() != null))
			postfix.enqueue(stack.pop().toString());
		return postfix;
		
	} //closing Queue method 
	
	//calculating the expression 
	public Double CalcExp(Queue<String> post, Stack<Double> sum){
		//evaluate if the list is not empty 
		while(post.callHead().next.data != null){
			String data = post.callHead().next.data;
			if(checkIn(data,operators) == -1){
				//make the operands doubles 
				sum.push(Double.valueOf(data).doubleValue());
				post.dequeue();
			}
			
			//popping 1 if needed
			else if(numOperands[checkIn(data,operators)] == 1){
				sum.push(OneOperator(data,sum.pop()));
				post.dequeue();
			}
			
			//popping 2 for other operators 
			else{ 
				Double pop1 = sum.pop(); 
				Double pop2= sum.pop();
				sum.push(TwoOperands(data,pop2,pop1));
				post.dequeue();
			}
		}
		//returning the sum, the answer 
		return sum.read();
	} //closing CalcExp method 
	
	//finding where the character is in the array 
	public int checkIn(String a, String[] b){
		for(int i = 0; i < b.length; i++)
			if(a.equals(b[i]))
				return i;
		return -1;
	}
	
	//One operator for the "!" (not) operator 
		public double OneOperator(String operator, Double op1){
			switch (operator) {
				case "!":
					if((op1.equals(1.0)))
						return 0;
					else
						return 1;
			}
			return 0;
		} //closing OneOperator method 
	
	//evaluating for operators that use two operands 
	public double TwoOperands(String operator, Double num1,Double num2){
		switch (operator) {
			//taking the operator string and using its function 
			case "+": 
				return (num1 + num2);
			case "-": 
				return (num1 - num2);
			case "*": 
				return (num1 * num2);
			case "/": 
				return (num1 / num2);
			case "<": 
				if(num1 < num2)
					return 1;
				else
					return 0;
			case ">": 
				if(num1 > num2)
					return 1;
			  	else
			        return 0;
			case "=":
				if(num1.equals(num2))
 	  		  		return 1;
	  		  	else
	  		  		return 0;
			case "&":
				if(num1.equals(num2))
 	  		  		return 1;
	  		  	else
	  		  		return 0;
			case "|":
				if(!(num1.equals(num2)))
					return 1;
				else
					return 0;
		}
		//any other case must return zero 
		return 0; 
	} //closing TwoOperators method 

} //closing public class InfixCalc 
