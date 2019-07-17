/* 
 George Rossney 
 Project 2: Infix Calculator  
 CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/
package calcpackage; 

public class LinkedList<T> implements LLInterface<T>{
	//declaring head and tail 
	private Node<T> head, tail; 
	
	public static class Node<T>{	
		//constructor
		public Node(T d,Node<T> n, Node<T> p){ 
			data = d; 
			next = n; 
			prev = p;
		} 
		
		public T data;
		public Node<T> next;
		public Node<T> prev;
	}

	//constructor 
	public LinkedList(){
		head = new Node<T>(null,tail,null);
		tail= new Node<T>(null,null,head);
		head.next = tail;
	}
	

	public Node<T> callHead(){
		return head;
	}
	
	public Node<T> callTail(){
		return tail;
	}
	
	//insert method 
	public void insert(T x){
		if(x != ""){
			Node<T> temp = new Node<T>(x,tail,tail.prev);
			tail.prev.next = temp;
			tail.prev = temp;
		}
	}
	
	//print the whole list
	public void printList(Node<T> node){
		System.out.println(node.data);
		if(node.next != null)
			printList(node.next);
	}
	
	
	//lookup method 
	public boolean lookUp(T x, Node<T> node){
		if(node.data != x){
			if(node.next != null){
				return lookUp(x, node.next);
			}
			else
				return false;		
		}else{
			return true;
		}
		
	}
	
	//delete method 
	public void delete(T x, Node<T> node){
		if(node.data != x){
			if(node.next != null){
				delete(x, node.next);
			}
			else
				Main.print("not found");	
		}else{
			node.next.prev = node.prev;
			node.prev.next = node.next;
			if(node.next != null);
		}
	}
	
	public int getLength(Node<T> node, int i)
	{
		if(node.next != null)
		{
			i++;
			return getLength(node.next,i);
		}
		else
			return i;
	}
	public String[] toString(Node<T>node, int i,int length,String[] space){
		if(node.next != null)
		{
			space[i] = node.data.toString();
			i++;
			return toString(node.next,i,length,space);
		}else{
			return space;
		}
	}
}