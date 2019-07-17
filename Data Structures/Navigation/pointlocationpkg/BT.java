/*
George Rossney 
Project 3: Point Location  
CSC 172
Lab TA: Pauline Chen 
Session: T/Th 4:50-6:05pm 
*/
package pointlocationpkg;

public class BT {

    private Node root;
    //clock & colinear setup given in project pdf 
    private final static int COUNTERCLOCKWISE = 1;
    private final static int CLOCKWISE = -1;
    private final static int COLLINEAR = 0;

    //empty method, suggested by java 
    public BT() {
    }
    //accessing Node 
    public Node getRoot() {
        return root; 
    }
    //for height of BT 
    public int extHeight() {
        return extHeight(root, 0);
    }
    
  
    //finding height of binary tree (BT) 
    private int extHeight(Node node, int depth) {
        if (node.right == null && node.left == null)
            return depth;
        else
            return extHeight(node.left, depth + 1) + extHeight(node.right, depth + 1);
    }
    
    //put is the insert method 
    public void put(Line line) {
        root = put(line, root);
    }
    
    //implementing BT put method from Princeton:
    //http://algs4.cs.princeton.edu/32bst/BST.java.html 
    private Node put(Line line, Node n) {
    	//method to insert objects into the BT 
        if (n == null)
            n = new Node(line);
        if (Line.isIntersect(line, n.line)) {
            n.left = put(line, n.left);
            n.right = new Node(line);
        } else if (ccw(line.first, n.line.first, n.line.second) < 0) {
            n.right = put(line, n.right);
        } else if (ccw(line.first, n.line.first, n.line.second) > 0) {
            n.left = new Node(line);
        }
        return n;
    }

   //used same Princeton source above as reference for the search method 
    public Node search(Node node, Point first, Point second) {
        int position1 = ccw(first, node.line.first, node.line.second);
        int position2 = ccw(second, node.line.first, node.line.second);

        if (position1 != position2)
            return node;
        if (position1 == -1) {
            if (node.right == null)
                return null;
            else
                search(node.right, first, second);
        }
        if (position1 == 1) {
            if (node.left == null)
                return null;
            else
                search(node.left, first, second);
        }
        return null;
    }

    //implementing ccw method from the project 3 pdf 
    public static int ccw(Point p0, Point first, Point second) {
        double dx1 = Math.abs(first.a - p0.a);
        double dy1 = Math.abs(first.b - p0.b);
        double dx2 = Math.abs(second.a - p0.a);
        double dy2 = Math.abs(second.b - p0.b);
        if (dx1 * dy2 > dy1 * dx2) return COUNTERCLOCKWISE;
        else if (dx1 * dy2 < dy1 * dx2) return CLOCKWISE;
        else if ((dx1 * dx2 < 0) || (dy1 * dy2 < 0)) return CLOCKWISE;
        else if ((dx1 * dx1 + dy1 * dy1) < (dx2 * dx2 + dy2 * dy2)) return COUNTERCLOCKWISE;
        return COLLINEAR;
    }
}//closing public class BT 