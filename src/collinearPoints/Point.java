/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        int rise = that.y - this.y;
        int run = that.x - this.x;
        if (rise != 0 && run == 0) return Double.POSITIVE_INFINITY;
        else if (rise == 0 && run != 0) return 0;
        else if (rise != 0 && run != 0) return 1.0 * rise / run;
        else /*(rise == 0 && run == 0)*/ return Double.NEGATIVE_INFINITY;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (y < that.y || (y <= that.y && x < that.x)) return -1;
        else if (y - that.y == 0 && x - that.x == 0) return 0;
        else return 1;
    }


    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new SlopeOrderComparator();
    }

    private class SlopeOrderComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            double x = slopeTo(a) - slopeTo(b);
            if (x > 0) return 1;
            else if (x < 0) return -1;
            else return 0;
        }
    }
    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point origin = new Point(0,0);
        int[][] pointCoords = {{0,0}, {1,0}, {0,1}, {-1, 0}, {0, -1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
        Point[] points = new Point[pointCoords.length];
        for (int i = 0; i < pointCoords.length; i++) {
            points[i] = new Point(pointCoords[i][0], pointCoords[i][1]);
            StdOut.println(origin + "*" + points[i] + " CompareTo: " + origin.compareTo(points[i]));
        }

        for (int i = 0; i < pointCoords.length; i++) {
            StdOut.println(origin + "*" + points[i] + " SlopeTo: " + origin.slopeTo(points[i]));
        }

        Comparator<Point> c = origin.slopeOrder();
        for (Point p : points) {
            for (Point q: points) {
                StdOut.println("" + p + q + " c(p,q) = " + c.compare(p,q));
            }
        }
    }
}
