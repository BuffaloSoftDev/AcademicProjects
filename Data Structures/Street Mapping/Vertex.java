/* 
 George Rossney 
 Project 4: Street Mapping 
 Course: CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/
public class Vertex {
	
	public AdjList adj;
	public int id;
	public String label;
	public double dist;
	public boolean isKnown;
	public Vertex vpath;
	public double x, y;
	
	//constructing the vertex of the graph/map 
	public Vertex(int id, String label, double x, double y){
		this.id = id;
		this.label = label;
		double infinitynum = 999999999; 
		this.dist =  infinitynum; 
		this.isKnown = false;
		this.vpath = null;
		this.x = x;
		this.y = y;
	}


	public boolean maxDistance(){
		double infinitynum2 = 999999999; 
		if (dist == infinitynum2){
			return true;
		}
		if (dist != infinitynum2){
			return false;
		}
		return isKnown;
	}

	
	public void setID(int id) {
		this.id = id;
	}

	public boolean isKnown() {
		return isKnown;
	}

	public void setKnown(boolean v) {
		isKnown = v;
	}
	
	
} //closing public class Vertex 

