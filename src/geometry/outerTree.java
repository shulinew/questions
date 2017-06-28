package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/*
 * There are some trees, where each tree is represented by (x,y) coordinate in a two-dimensional garden. Your job is to
 *  fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all 
 *  the trees are enclosed. Your task is to help find the coordinates of trees which are exactly located on the fence 
 *  perimeter.
 */
class Point {
	int x;
	int y;
	Point() {x = 0; y =0;}
	Point(int a, int b) {
		x = a;
		y = b;
	}
}
public class outerTree {
    public List<Point> outerTrees(Point[] points) {
        Set<Point> result = new HashSet<Point>();
        
        // Find the leftmost point
        Point first = points[0];
        int firstIndex = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].x < first.x) {
                first = points[i];
                firstIndex = i;
            }
        }
        result.add(first);
        
        Point cur = first;
        int curIndex = firstIndex;
        do {
            Point next = points[0];
            int nextIndex = 0;
            for (int i = 1; i < points.length; i++) {
                if (i == curIndex) continue;
                int cross = crossProductLength(cur, points[i], next);
                if (nextIndex == curIndex || cross > 0 ||
                        // Handle collinear points
                        (cross == 0 && distance(points[i], cur) > distance(next, cur))) {
                    next = points[i];
                    nextIndex = i;
                }
            }
            // Handle collinear points
            for (int i = 0; i < points.length; i++) {
                if (i == curIndex) continue;
                int cross = crossProductLength(cur, points[i], next);
                if (cross == 0) {
                    result.add(points[i]);
                }
            }

            cur = next;
            curIndex = nextIndex;
            
        } while (curIndex != firstIndex);
        
        return new ArrayList<Point>(result);
    }
    
    private int crossProductLength(Point A, Point B, Point C) {
        // Get the vectors' coordinates.
        int BAx = A.x - B.x;
        int BAy = A.y - B.y;
        int BCx = C.x - B.x;
        int BCy = C.y - B.y;
    
        // Calculate the Z coordinate of the cross product.
        return (BAx * BCy - BAy * BCx);
    }

    private int distance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
    
    
    /*
     * =====================================================================================
     * Hi there! Your solution is good! I had no idea about those algorithms, but I have got Accepted with slow, but 
     * simple solution (apparently it is similiar to Gift Wrapping algorithm (non-optimized version)).
	The main idea is also finding convex polygon with minimal perimeter that encompasses all the points.
	Here are some observations before understanding the solution,

    The top most, bottom most, left most and right most points lie on the border.
    All the points lie on the same semi-plane according to lines the polygons sides belong to.

	The algorithm below is based only on that observations.
	Well, initially we find any of the top most, bottom most, left most and right most points. In my case I decided 
	to start from top most point.
	Then starting from that point (let's call it start point) we find next point that must lie on the border of our 
	polygon. That can be done according to our second observation. It means, according to the line defined by the 
	current point and the start point, all the other points must lie on the same semi-plane. Consequently repeat that 
	operation starting from the current point until we realize that the current point is already visited. Finally, 
	we have to find the points that lie on the border, but missed in the previous stage. The latter can also be done by
	 iterating through the points and already found points on the border. Overall time complexity of the algorithm is
	 O(hn**2).

     */
    public List<Point> outerTrees11(Point[] points) {
        List<Point> result =  new ArrayList<Point>();
        if(points == null || points.length == 0) return result;
        Set<Point> visited  = new HashSet<Point>();
        //Find top most node
        Point start = points[0];
        for(int i = 1;i<points.length;i++){
            if(points[i].y > start.y || (points[i].y == start.y && points[i].x<start.x)){
                start = points[i];
            } 
        }
        if(points.length == 1){
        	result.add(start);
            return result;
        }
        //Find general convex hull
        Point current = start;
        while(current != null && visited.add(current)){
        	result.add(current);
            for(int i = 0;i<points.length;i++){
                if(visited.contains(points[i])) continue;
                if(isBorder(current, points[i], points)){
                    current = points[i];
                    break;
                }
            }
        }
       //Append missing points that lie on the border of polygon
        for(int i = 0;i<points.length;i++){
            if(visited.contains(points[i]) || result.contains(points[i])) continue;
            int size = result.size();
            for(int j = 0;j<size;j++){
                Point p = result.get(j);
                if(isBorder(p, points[i], points)) {
                    visited.add(points[i]);
                    result.add(points[i]);
                    break;
                }
            }
        }
        return result;
    }
    
   // Find whether points lie on the same semi-plane related to the line defined by points p1 and p2
    private boolean isBorder(Point p1, Point p2, Point [] points){
        int dx = p1.x-p2.x;
        int dy = p1.y-p2.y;
        int b = p1.x*dy - p1.y*dx;
        int prev = 0;
        for(int i = 0;i<points.length;i++){
            int x = points[i].x;
            int y = points[i].y;
            int sign = dx*y-dy*x+b;
            if(sign== 0) continue;
            if(sign*prev < 0) return false;
            if(sign <0) prev = -1;
            else prev = 1;
        }
        return true;
    }
    
    ///////////////////////////
    /*
     * The trick is that once all points are sorted by polar angle with respect to the reference point:

    For collinear points in the begin positions, make sure they are sorted by distance to reference point in ascending 
    order.
    For collinear points in the end positions, make sure they are sorted by distance to reference point in descending 
    order.

	For example:
	(0, 0), (2, 0), (3, 0), (3, 1), (3, 2), (2, 2), (1, 2), (0, 2), (0, 1)
	These points are sorted by polar angle
	The reference point (bottom left point) is (0, 0)

    In the begin positions (0, 0) collinear with (2, 0), (3, 0) sorted by distance to reference point in ascending order.
    In the end positions (0, 0) collinear with (0, 2), (0, 1) sorted by distance to reference point in descending order.

	Now we can run the standard Graham scan to give us the desired result.
     */
    
    public List<Point> outerTrees1(Point[] points) {
        if (points.length <= 1)
            return Arrays.asList(points);
        sortByPolar(points, bottomLeft(points));
        Stack<Point> stack = new Stack<Point>(); 
        stack.push(points[0]);                      
        stack.push(points[1]);                              
        for (int i = 2; i < points.length; i++) {
            Point top = stack.pop();                                
            while (ccw(stack.peek(), top, points[i]) < 0)
                top = stack.pop();
            stack.push(top);
            stack.push(points[i]);
        }       
        return new ArrayList<Point>(stack);
    }                               

    private static Point bottomLeft(Point[] points) {
        Point bottomLeft = points[0];
        for (Point p : points)          
            if (p.y < bottomLeft.y || p.y == bottomLeft.y && p.x < bottomLeft.x)
                bottomLeft = p;                 
        return bottomLeft;                                                  
    }

    /**
     * @return positive if counter-clockwise, negative if clockwise, 0 if collinear
     */
    private static int ccw(Point a, Point b, Point c) {
        return a.x * b.y - a.y * b.x + b.x * c.y - b.y * c.x + c.x * a.y - c.y * a.x;       
    }

    /**
     * @return distance square of |p - q|
     */
    private static int dist(Point p, Point q) {
        return (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y);
    }
                              
    private static void sortByPolar(Point[] points, Point r) {
        Arrays.sort(points, (p, q) -> {
            int compPolar = ccw(p, r, q);
            int compDist = dist(p, r) - dist(q, r); 
            return compPolar == 0 ? compDist : compPolar;
        });     
        // find collinear points in the end positions
        Point p = points[0], q = points[points.length - 1];
        int i = points.length - 2;
        while (i >= 0 && ccw(p, q, points[i]) == 0)
            i--;    
        // reverse sort order of collinear points in the end positions
        for (int l = i + 1, h = points.length - 1; l < h; l++, h--) {
            Point tmp = points[l];
            points[l] = points[h];
            points[h] = tmp;
        }
    }
}