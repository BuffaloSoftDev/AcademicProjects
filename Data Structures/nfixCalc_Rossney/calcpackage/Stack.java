/* 
 George Rossney 
 Project 2: Infix Calculator  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/
package calcpackage; 

public class Stack<T> extends LinkedList<T> implements StackInterface<T> {	
	
	public Stack(){
		super();
	}
	
	public T read(){
		return callTail().prev.data; 
	}
	
	//pop the last item 
	public T pop(){
		T popped = callTail().prev.data;
		callTail().prev.prev.next = callTail();
		callTail().prev = callTail().prev.prev;
		return popped;
	}
	
	//inserting 
	public void push(T pushed){
		insert(pushed);
	}
	
}//closing public class Stack 