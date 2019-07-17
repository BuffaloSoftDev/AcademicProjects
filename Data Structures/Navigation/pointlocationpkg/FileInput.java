/*
George Rossney 
Project 3: Point Location  
CSC 172
Lab TA: Pauline Chen 
Session: T/Th 4:50-6:05pm 
*/
package pointlocationpkg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//taking in user's text file with the number of lines & the points as input 
public class FileInput {

	//empty method, suggested by Eclipse
    @SuppressWarnings("unused")
	private class Input {
    }
    //main method for the project, user enters a text file name to be read 
    public static void main(String[] args) throws IOException {
    	//casting to Line and point
        List<Line> lineList = new ArrayList<Line>();
        List<List<Point>> pointList = new ArrayList<List<Point>>();

        //querying the user for their input text file 
        String path = "";
        @SuppressWarnings("resource")
		Scanner fscan = new Scanner(System.in);
        //Note: the text file must have integers only, first line must be an integer, not a space 
        System.out.println("Please enter a text file name, including path, without spaces: ");
        path = fscan.next();

        //reading the input 
        readInput(path, lineList, pointList);
        
        BT tree = new BT();
        //constructing node array from the linelist input
        Node[] result = new Node[lineList.size()];

        for (int i = 0; i < lineList.size(); i++) {
            tree.put(lineList.get(i));
        }

        //determining whether or not the point line crosses a plane
        for (int ptnum = 0; ptnum < pointList.size(); ptnum++) {
            System.out.println("Point Set(s) " + (ptnum + 1) + ": ");
            result[ptnum] = tree.search(tree.getRoot(), pointList.get(ptnum).get(0), pointList.get(ptnum).get(1));
            if (result[ptnum] == null)
                System.out.println("No intersection point.");
            else if (result[ptnum] != null)
                System.out.println("Insersection: " + result[ptnum].line);
        }

    }

    //method for reading in the inputs
    public static void readInput(String path, List<Line> lineList, List<List<Point>> pointList) throws IOException {

        FileReader fr = new FileReader(new File(path));
        @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);

        String s;
        //start with no lines or points 
        int count = 0, numberOfLines = 0;
        //count<999 so there is never a chance for an infinite line number
        while ((s = br.readLine()) != null && count<999) {

            if (count == 0) {
                numberOfLines = Integer.parseInt(s);
                
            } else if (count >= 1 && count <= numberOfLines) {

                String[] temp = s.split(" ");
                lineList.add(new Line(Double.parseDouble(temp[0].trim()),
                        Double.parseDouble(temp[1].trim()),
                        Double.parseDouble(temp[2].trim()),
                        Double.parseDouble(temp[3].trim())));
            } else {
            	//only spaces between the points in each line of the text 
                String[] temp = s.split(" ");
                Point p1 = new Point(Double.parseDouble(temp[0].trim()), Double.parseDouble(temp[1].trim()));
                Point p2 = new Point(Double.parseDouble(temp[2].trim()), Double.parseDouble(temp[3].trim()));

                List<Point> ptList = new ArrayList<Point>(2);
                ptList.add(p1);
                ptList.add(p2);
                pointList.add(ptList);
            }
            count++;
        }
    }
}