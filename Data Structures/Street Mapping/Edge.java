/* 
 George Rossney 
 Project 4: Street Mapping 
 Course: CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/

public class Edge {
	
	Vertex v, w; 
	//graph weights are numbers with decimals 
	double weight;
	//roads and intersection id's are strings 
	String id;

	public Edge(String id, Vertex v, Vertex w){
		//edge between 2 vertices, each edge has a string id
		this.v = new Vertex(v.id, v.label, v.x, v.y);
		this.w = new Vertex(w.id, w.label, w.x, w.y);
		
		//the weight is the distance between the vertices 
		this.weight = calcLength(v,w);
		this.id = id;
	}

	//accessor 
	public String getID() {
		return this.id;
	}
	//mutator 
	public void setID(String id) {
		this.id = id;
	}

	//parameters for distance 
	public double calcLength(Vertex v, Vertex w) {
		//using the distance formula between the x and y locations of each vertex
		double xVal= (w.x-v.x);
		double yVal= (w.y - v.y); 
		return Math.pow(Math.pow(xVal,2) + Math.pow(yVal,2), 0.5);
	}
	
	//v & w are known if the edge has been visited, & vice versa 
	public boolean isVisited() {
		return (v.isKnown() && w.isKnown() );
	}
	
	public void setVisited() {
		v.setKnown(true);
		w.setKnown(true);
	}
	
} //closing public class Edge 

