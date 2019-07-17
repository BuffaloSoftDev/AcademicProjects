/* 
 George Rossney 
 Project 2: Infix Calculator  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/
package calcpackage; 

public interface LLInterface<T>{
	public void insert(T x);
	public void delete(T x, LinkedList.Node<T> node);
	public boolean lookUp(T x, LinkedList.Node<T> node);
	public void printList(LinkedList.Node<T> node);
}
