/* 
 George Rossney 
 Project 2: Infix Calculator  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/
package calcpackage; 

public interface StackInterface<T> {
	public T pop();
	public void push(T pushed);
	public T read();
}
