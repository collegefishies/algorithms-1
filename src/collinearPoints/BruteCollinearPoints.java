import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class BruteCollinearPoints {
    private int i = 0;
    private LineSegment[] segments = new LineSegment[1];

    private void add(LineSegment s) {
        if (i == segments.length) resize(2 * segments.length);
        segments[i++] = s;
    }

    private void resize(int newSize) {
        LineSegment[] newSegments = new LineSegment[newSize];
        for (int i = 0; i < segments.length; i++)
            newSegments[i] = segments[i];
        segments = newSegments;
    }

    private Point min(Point a, Point b) {
        if (a.compareTo(b) < 0) return a;
        else if (a.compareTo(b) > 0) return b;
        else return a;
    }

    private Point max(Point a, Point b) {
        if (a.compareTo(b) < 0) return b;
        else if (a.compareTo(b) > 0) return a;
        else return a;
    }

    private Point min(Point a, Point b, Point c, Point d) {
        Point _min = min(a, b);
        _min = min(_min, c);
        _min = min(_min, d);
        return _min;
    }

    private Point max(Point a, Point b, Point c, Point d) {
        Point _max = max(a, b);
        _max = max(_max, c);
        _max = max(_max, d);
        return _max;
    }
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("No null arrays");
        Point oldP = null;
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("No null points in points array.");
            if (p.equals(oldP)) throw new IllegalArgumentException("No duplicate points.");
            oldP = p;
        }
        int n = points.length;
        // sort by the coordinates
        for (int i = 0; i < n - 3; i++) {
            Comparator<Point> c = points[i].slopeOrder();
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    if (c.compare(points[j], points[k]) != 0) continue;
                    for (int l = k + 1; l < n; l++) {
                        if (c.compare(points[j], points[l]) != 0) continue;
                        if (c.compare(points[k], points[l]) != 0) continue;
                        add(
                                new LineSegment(
                                        min(points[i], points[j], points[k], points[l]),
                                        max(points[i], points[j], points[k], points[l]))
                        );
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return i;
    }

    public LineSegment[] segments() {
        LineSegment[] returnCopy = new LineSegment[i];
        for (int j = 0; j < i; j++) {
            returnCopy[j] = segments[j];
        }
        return returnCopy;
    }

    public static void main(String[] args) {
        if (args.length != 1)
            throw new IllegalArgumentException("Needs file input");
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

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        StdOut.println("Number of Segments = " + bcp.numberOfSegments());
        for (LineSegment segment : bcp.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
