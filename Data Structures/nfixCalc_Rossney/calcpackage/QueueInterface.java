/* 
 George Rossney 
 Project 2: Infix Calculator  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/
package calcpackage; 


public interface QueueInterface<T> {
	
	public T dequeue();
	public void enqueue(T pushed);
}
