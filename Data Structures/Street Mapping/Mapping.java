/* 
 George Rossney 
 Project 4: Street Mapping 
 Course: CSC 172
 Lab TA: Pauline Chen 
 Session: T/Th 4:50-6:05pm 
*/

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Mapping extends JFrame {

	private static final long serialVersionUID = 1L; //Eclipse suggestion 
	private static int vertCount = 1, vertID=1; 
	
	static JFrame frame; //jframe for map 
	
	//using HashMap structure 
	private static Map<String, Vertex> vMap = new HashMap<String, Vertex>();																
	private static Map<Integer, Vertex> intMap = new HashMap<Integer, Vertex>();
	private static ArrayList<Edge> mwst = new ArrayList<Edge>();

	static Graph map = new Graph(0, false);
	static Scanner s;
	static Edge[] mwst1;

	private static ArrayList<Integer> path = new ArrayList<Integer>();
	private static Map<String, Edge> edgeMap = new HashMap<String, Edge>();
	private static Map<String, Edge> valEdgeMap = new HashMap<String, Edge>();

	@SuppressWarnings({ "serial", "resource" })
	//main method: reading text file for map data 
	public static void main(String[] args) throws IOException {

		try {
			//buffered reader to read text file line by line 
			BufferedReader graphFile = new BufferedReader
					(new FileReader("ur.txt"));
					//(new FileReader("monroe.txt"));
					//(newFileReader("nys.txt"));
			
			int count = 1;
			String line = "";
	
			while ((line = graphFile.readLine()) != null) {
				//using string tokenizer to take in input from the text file, 
				 //then split then parse out the numbers 
				StringTokenizer st = new StringTokenizer(line);
				String id = st.nextToken();
				// intersections as vertices
				if (id.equals("i")){
					String ID = st.nextToken();
					double x = Double.parseDouble(st.nextToken());
					double y = Double.parseDouble(st.nextToken());

					Vertex v = vMap.get(ID);

					//no vertex condition
					if (v == null) {

						v = new Vertex(vertID, ID, x, y);

						vMap.put(ID, v);
						intMap.put(vertID, v);

						vertCount = vertID++;
					}
				}
				// if true, number of lines if the vertID 
				if (vertID == count) 
					map = new Graph(vertCount + 1, false);

				else if (id.equals("r")) {
					String ID = st.nextToken();
					//tokenize scanner inputs from user 
					String s1 = st.nextToken();
					String s2 = st.nextToken();

					Vertex v1 = vMap.get(s1);
					Vertex v2 = vMap.get(s2);

					Edge e = new Edge(ID, v1, v2);
					e.setID(ID);

					edgeMap.put(ID, e);
					mwst.add(e);
					map.insert(e);
					valEdgeMap.put((s1 + "|" + s2), e);

				}
				count++;
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		System.out.println("Let's find the shortest path."); 
		//Querying the user for input 
		s = new Scanner(System.in);
		System.out.println("Enter a starting point (example: \"i15\"):  "); 
		String start = s.next();
		//take input and pass it to Dijkstra's algorithm 
		map.Dijkstra(vMap.get(start).id);
		System.out.println("Enter a destination point: ");

		String destination = s.next();
		path = map.printPath(vMap.get(destination).id);

		// generating the MWST
		mwst1 = listToArray(mwst);
		//mwst method is below 
		mwst(); 
		final ArrayList<Edge> drawMST = genMWST();

		//console output 
		for (int i = 0; i < path.size(); i++) {
			System.out.print("Intersect: " + intMap.get(path.get(i)).label);
			if (i != path.size() - 1)
				//arrow, since graph/map is directed 
				System.out.print("-->"); 
		}
		
		System.out.println();
		
		for (int i = 0; i < (path.size() - 1); i++){
			if (valEdgeMap.get((intMap.get(path.get(i)).label + "|" + intMap.get(path.get(i + 1)).label)) != null){
				System.out.print(" Road: "+ valEdgeMap.get((intMap.get(path.get(i)).label + "|" + intMap
						.get(path.get(i + 1)).label)).getID());		
										
			}else{
				System.out.print(" Road: "+ valEdgeMap.get((intMap.get(path.get(i + 1)).label
						+ "|" + intMap.get(path.get(i)).label)).getID());
										
			if (i != path.size() - 2){
				System.out.print(" , ");
			}
			}
		}

		System.out.println("");
		System.out.println("Number of intersections is: " + intMap.size());
		System.out.print("Number of roads is: " + path.size());

		//displaying the map in a frame 
		Mapping mapframe = new Mapping();
		
		mapframe.setVisible(true);
		mapframe.setTitle("Street Mapping: Shortest Path  (GWR)");  
		//frame will close and the program will end when the user closes the window
		mapframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mapframe.setSize(500, 500);
		
		//adding components to the frame 
		mapframe.add(new JComponent() {

			@SuppressWarnings("rawtypes")
			public void paintComponent(Graphics g) {
				//frame.pack();
				Graphics2D g2 = (Graphics2D) g;
				//super(); 
				super.paintComponent(g2);
				g2.setBackground(Color.WHITE); 
				g2.setColor(Color.BLUE);
				 
				Iterator nextEdge = edgeMap.entrySet().iterator();

				//generate a line in the frame
				//go through the while loop for each line (road) of the path 
				while (nextEdge.hasNext()){
					Map.Entry pair = (Map.Entry) nextEdge.next();
					Edge e = (Edge) pair.getValue();
					//draw from four-coordinates of the line 
					Shape line = new Line2D.Double(e.v.x, e.v.y, e.w.x, e.w.y);
				
					g2.draw(line);
					nextEdge.remove();
				}
				//drawing roads in blue 
				g2.setColor(Color.BLUE);
				for (Edge e : drawMST) {
					Edge edges = e;
					Shape line = new Line2D.Double(edges.v.x, edges.v.y,edges.w.x, edges.w.y);
							
					g2.draw(line);
				}

				//drawing shortest path in red
				g2.setColor(Color.RED);
				if (path.size() > 1) {
					for (int i = 0; i < path.size() - 1; i++) {
						Shape line = new Line2D.Double(
						intMap.get(path.get(i)).x, intMap.get(path.get(i)).y,
						intMap.get(path.get(i + 1)).x, intMap.get(path.get(i + 1)).y);
					//drawing lines separately, red path line on top of the blue road line					
					g2.draw(line);
					}
				}
			}
		}); //closing frame component method 
	
	} //closing main method 
	
	//calculating the  MWST, implementing algorithm from Weiss textbook 
	public static void mwst() {
		for (int i = 0; i < mwst1.length; i++) {
			double min = mwst1[i].weight;
			int minCheck = i;

			for (int j = i + 1; j < mwst1.length; j++) {
				if ((double) mwst1[j].weight < min) {
					min = mwst1[j].weight;
					minCheck = j;
				}
			}
			if (minCheck != i) {

				Edge e = mwst1[minCheck];
				mwst1[minCheck] = mwst1[i];
				mwst1[i] = e;
			}
		}
	}

	//generate the MWST 
	public static ArrayList<Edge> genMWST() {
		ArrayList<Edge> list = new ArrayList<Edge>(); 
		for (int i = 0; i < mwst1.length; i++) {
			if (!mwst1[i].isVisited()) {
				list.add(mwst1[i]);  
				mwst1[i].setVisited();	
			}
		} 
		//return the list of all visited edges
		return list;  
	}
	
	//method to convert list of edges into an array list 
		public static Edge[] listToArray(ArrayList<Edge> list){
			Edge[] EdgeAry = new Edge[list.size()];
			for (int i = 0; i < list.size(); i++) {
				EdgeAry[i] = list.get(i);
			}
			return EdgeAry;
		}

} //closing public class RoadMap

