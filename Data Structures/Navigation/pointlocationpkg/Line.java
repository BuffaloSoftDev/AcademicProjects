/*
George Rossney 
Project 3: Point Location  
CSC 172
Lab TA: Pauline Chen 
Session: T/Th 4:50-6:05pm 
*/
package pointlocationpkg;

public class Line {

    Point first, second;

    public Line(double a1, double b1, double a2, double b2) {
        first = new Point(a1, b1);
        second = new Point(a2, b2);
    }
    //empty method, suggested by Eclipse 
    public Line() {
    }
    
    //solving for intersection point between 1st and 2nd lines 
    public static Point getPointOfIntersect(Line first_line, Line second_line) {
    	//four points for two lines
        double a1 = first_line.first.a;
        double b1 = first_line.first.b;
        double a2 = first_line.second.a;
        double b2 = first_line.second.b;

        double x3 = second_line.first.a;
        double b3 = second_line.first.b;
        double x4 = second_line.second.a;
        double b4 = second_line.second.b;

        double d = ((a1 - a2) * (b3 - b4) - (b1 - b2) * (x3 - x4));
        if (d == 0) 
        	return null;
        //solving for the intersection points 
        double eq1 = (((x3 - x4) * (a1 * b2 - b1 * a2) - (a1 - a2) * (x3 * b4 - b3 * x4)) / d);
        double eq2 = (((b3 - b4) * (a1 * b2 - b1 * a2) - (b1 - b2) * (x3 * b4 - b3 * x4)) / d);

        if (x3 == x4) {
            if (eq2 < Math.min(b1, b2) || eq2 > Math.max(b1, b2))
                return null;
        }
        Point solvedPt = new Point(eq1, eq2);
        
        if (eq1 < Math.min(x3, x4) || eq1 > Math.max(x3, x4))
            return null;

        if (eq1 < Math.min(a1, a2) || eq1 > Math.max(a1, a2))
            return null;

      //returning the intersection point(s)
        return solvedPt; 
    }

    //Is there an intersection? boolean for yes or no 
    public static boolean isIntersect(Line FirstLine, Line SecondLine) {
    	
        return getPointOfIntersect(FirstLine, SecondLine) != null;
    }

    //returning the result
    public String toString() {
        return "" +"(" +first.a +", " +first.b +") (" +second.a +", " +second.b +")";
    }
}
