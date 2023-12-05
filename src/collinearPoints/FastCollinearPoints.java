import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private int index;
    private LineSegment[] segments = new LineSegment[1];

    private void add(LineSegment line) {
        if (index == segments.length) { resize( 2 * segments.length ); }
        segments[index++] = line;
    }
    private void resize(int newSize) {
        LineSegment[] newSegments = new LineSegment[newSize];
        for (int i = 0; i < index; i++)
            newSegments[i] = segments[i];
        segments = newSegments;
    }

    public FastCollinearPoints(Point[] points) {
        // sort points by positionOrder
        Point[] positionOrder = new Point[points.length];
        for (int i = 0; i < positionOrder.length; i++) {
            positionOrder[i] = points[i];
        }
        Arrays.sort(positionOrder);

        // for each point
        for (Point origin: positionOrder) {
            // assume origin is the bottom-left most point
            // sort the points according to slope
            Comparator<Point> c = origin.slopeOrder();

            //Arrays.sort is stable so...
            Arrays.sort(points); // position order
            Arrays.sort(points, c); // then slopeOrder
            // all points with equal slopeOrder will there for be also
            // sorted by position.

            // iterate across the points to find three or more points with equal slope.
            int i = 1; // origin is always the first point (in slopeOrder).
            while (i < points.length) {
                if ( origin.compareTo(points[i]) > 0 ) {
                    // origin could not be the left most point of the line.
                    i++; continue;
                }
                // this origin <= points[i] (in position) therefore it's left most.
                int j = i + 1;
                // while we found a section with equal slopes
                while ( j < points.length && c.compare(points[i], points[j]) == 0 ) { //while collinear
                    j++; // increase the section
                } // j is the first non-collinear point
                int numCollinear = j - i + 1;
                if (numCollinear >= 4) {
                    add(new LineSegment(origin, points[j-1]));
                }
                // move the counter to where we know the first
                // non collinear point is
                i = j;
            }
        }
    }
    public int numberOfSegments() { return index;}
    public LineSegment[] segments() {
        LineSegment[] newSegments = new LineSegment[index];
        for (int i = 0; i < index; i++) {
            newSegments[i] = segments[i];
        }
        return newSegments;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setYscale(0, 32768);
        StdDraw.setXscale(0, 32768);
        for (Point p: points) {
            p.draw();
        }
        StdDraw.show();

        StdOut.println("Finding...");
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        StdOut.println("Number of segments = " + fcp.numberOfSegments());
        for (LineSegment segment : fcp.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
