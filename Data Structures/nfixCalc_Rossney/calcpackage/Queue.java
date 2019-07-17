/* 
 George Rossney 
 Project 2: Infix Calculator  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/
package calcpackage; 

public class Queue<T> extends LinkedList<T> implements QueueInterface<T> {	
	
	
	public Queue(){
		super();
	}
	
	public T dequeue(){
		T end = callHead().next.data;
		delete(end,callHead());
		return end;
	}
	
	public void enqueue(T pushed){
		insert(pushed);
	}
	
}
