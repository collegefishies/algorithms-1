/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

import java.util.TreeSet;


public class PointSET {
    private int i;
    private int size;
    private TreeSet<Point2D> set;

    public PointSET() {
        set = new TreeSet<>();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("No null");
        set.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("No null");
        return set.contains(p);
    }

    public void draw() {
        for (Point2D x : set) {
            x.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("No null");
        Stack<Point2D> stack = new Stack<Point2D>();
        for (Point2D x : set) {
            if (rect.contains(x)) {
                stack.push(x);
            }
        }
        return stack;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("No null");
        Point2D champion = new Point2D(1000, 1000);
        for (Point2D x : set) {
            if (p.distanceTo(x) < p.distanceTo(champion)) {
                champion = x;
            }
        }
        return champion;
    }

    public static void main(String[] args) {

    }
}
