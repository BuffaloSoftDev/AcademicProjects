/* 
 George Rossney 
 Project 4: Street Mapping 
 Course: CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/

import java.util.ArrayList;

//**class for the graph structure of the map, building upon code from graph labs**\\
public class Graph {
	
	private int vertexAmt, edgeAmt;
	//adjacency matrices are 2-D arrays 
	private double adj[][];
	
	private double[] dist;
	private int[] path;
	static Edge[] mwst;
	
	private boolean[] isKnown;
	//graph is directed or undirected 
	private boolean dirgraph;
	
	private ArrayList<Integer> graphAL = new ArrayList<Integer>();

	
	public Graph(int numVertices, boolean isDirgraph){
		this.vertexAmt = numVertices;
		isKnown = new boolean[vertexAmt];
		adj = new double[vertexAmt][vertexAmt];
		dist = new double[vertexAmt];
		path = new int[vertexAmt];
		dirgraph = isDirgraph;
		//filling the adjaceny matrix 
		for (int i=0; i<adj.length; i++){
			for(int j=0; j<adj[i].length; j++){
				adj[i][j] = (i==j)? 0 : -1;
			}
		}
	}//closing public Graph constructor 

	public int size() {
		int vertexSize= vertexAmt-10; 
		return adj[vertexSize].length;
	}
	public void setVert(int numVertices) {
		this.vertexAmt = numVertices;
	}
	public int Vertex1(){
		return vertexAmt;
	}
	public int Edge1(){
		return edgeAmt;
	}
	public void insert(Edge e){	
		if (edge(e.v, e.w)){
			return;
		}
		adj[e.v.id][e.w.id] = e.weight;
		edgeAmt++;

		if (!dirgraph){
			adj[e.w.id][e.v.id] = e.weight;
		}
	}
	//method to delete edges, for MWST
	public void delete(Edge e){
		int vID= e.v.id;
		int wID= e.w.id; 
		if(!edge(e.v,e.w))
			return;
		adj[vID][wID] = -1;

		if (!dirgraph)
			adj[wID][vID] = -1;

		edgeAmt--;
	}

	public void setWeight(Edge e, int updateWeight){
		int vID= e.v.id;
		int wID= e.w.id; 
		//changes the weight of the edge
		e.weight = updateWeight;
		//changes the weight in the adj matrix
		adj[vID][wID] = e.weight;
	}

	public AdjList getAdjList(Vertex vertex){
		return new AdjArray(vertex);
	}

	public boolean edge(Vertex v1, Vertex v2){
		return adj[v1.id][v2.id] > 0;
	}


	public double weight(Vertex v, Vertex w) {
		return new Edge("string",v,w).calcLength(v, w);
	}

	private class AdjArray implements AdjList {
		//staring at "i0", can't be negative 
		private Vertex v, i = new Vertex(0, "i0",0,0);
		
		AdjArray(Vertex v){
			this.v = v;
			i.setID(-1);
		}
		public int next(){
			
			
			for(i.id++;i.id<Vertex1();i.id++){
				if(edge(v,i)){
					return i.id;
				}
			}
			return -1;
		}
		
		//begin, end, & next methods are called in the AdjList class
		public int begin(){
			i.setID(-1);
			return next();
		}
		public boolean end(){
			if (i.id < Vertex1())
				return false;
			return true;
		}
	}
	//implementing Dijkstra's algorithm from Weiss textbook 
	public void Dijkstra(int start){	
		for (int i = 0; i < dist.length; i++){
			if (i == start)
				dist[i] = 0;
			else
				dist[i] = Integer.MAX_VALUE;
			path[i] = -1;
			isKnown[i] = false;
		}
		int vertex;
		while((vertex = lowestVert(start)) != -1){ 
			isKnown[vertex] = true; 
			AdjList A = getAdjList(new Vertex(vertex, "i0", 0, 0));
			for(int w = A.begin(); !A.end(); w = A.next()){
				if(!isKnown[w] && (dist[vertex] + adj[vertex][w] < dist[w])){
					dist[w] = dist[vertex] + adj[vertex][w];
					path[w] = vertex;
				}
			}
		}
	}

	public int lowestVert(int lowV){
		//infinity value
		double dist1=9999999; 
		int vertex = -1;

		for (int i = 0; i < Vertex1(); i++){
			if (!isKnown[i] && dist[i] < dist1){
				dist1 = dist[i];
				vertex = i;
			}
		}
		return vertex;
	}
	//taking the array list and printing it, to show the user the path 
	public ArrayList<Integer> printPath(int v) {
		if (path[v] != -1) {
			printPath(path[v]);
		}
		graphAL.add(v);
		return graphAL;
	}

}