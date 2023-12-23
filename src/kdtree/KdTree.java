/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;


public class KdTree {
    private int DIMENSIONS = 2;
    private int size;
    private Node root;

    public KdTree() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("No null");
        RectHV pointRange = new RectHV(0, 0, 1, 1);
        root = insert(root, p, 0, pointRange);
        size++;
    }

    private Node insert(Node root, Point2D p, int level, RectHV bounds) {
        if (root == null) return new Node(p.x(), p.y(), level, bounds);

        int cmp = root.compareTo(p);
        bounds = updateBounds(bounds, root, p);
        if (cmp > 0) {
            root.left = insert(root.left, p, level + 1, bounds);
        }
        else if (cmp <= 0) {
            root.right = insert(root.right, p, level + 1, bounds);
        }
        else {
            return root;
        }

        return root;
    }

    private RectHV updateBounds(RectHV oldBounds, Node root, Point2D p) {
        int level = root.level;
        RectHV newBounds;
        int cmp = root.compareTo(p);
        // level 0 , compare x.
        if (level == 0 && cmp > 0) {
            newBounds = new RectHV(
                    oldBounds.xmin(),
                    oldBounds.ymin(),
                    root.x[0],
                    oldBounds.ymax()
            );
        }
        else if (level == 0 && cmp <= 0) {
            newBounds = new RectHV(
                    root.x[0],
                    oldBounds.ymin(),
                    oldBounds.xmax(),
                    oldBounds.ymax()
            );
        }
        else if (level == 1 && cmp > 0) {
            newBounds = new RectHV(
                    oldBounds.xmin(),
                    oldBounds.ymin(),
                    oldBounds.xmax(),
                    root.x[1]
            );
        }
        else if (level == 1 && cmp <= 0) {
            newBounds = new RectHV(
                    oldBounds.xmin(),
                    root.x[1],
                    oldBounds.xmax(),
                    oldBounds.ymax()
            );
        }
        else {
            newBounds = new RectHV(0, 0, 1, 1);
            throw new RuntimeException("Logic Error. Should not have occured.");
        }

        // return oldBounds;

        return newBounds;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("No null");
        return contains(root, p);
    }

    public boolean contains(Node root, Point2D p) {
        if (root == null) return false;

        int cmp = root.compareTo(p);
        if (cmp > 0) return contains(root.left, p);
        else {
            if (root.val().equals(p)) return true;
            return contains(root.right, p);
        }
    }


    public void draw() {
        drawTree(root);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        stringTree(sb, root);
        return sb.toString();
    }

    private void stringTree(StringBuilder sb, Node root) {
        if (root == null) return;
        stringTree(sb, root.left);
        sb.append(root.val());
        stringTree(sb, root.right);
    }

    private void drawTree(Node x) {
        if (x == null) return;
        x.draw();
        drawTree(x.left);
        drawTree(x.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("No null");
        return null;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("No null");
        return null;
    }

    private class Node {
        private double x[];
        private int level;
        private Node left, right;
        private RectHV bounds;

        Node(double x, double y, int level, RectHV bound) {
            this.x = new double[] { x, y };
            this.level = level % DIMENSIONS;
            bounds = bound;
        }

        public void draw() {
            Point2D p = new Point2D(x[0], x[1]);
            p.draw();
            if (level == 0) {
                StdDraw.setPenColor(Color.red);
                StdDraw.line(x[0], bounds.ymin(), x[0], bounds.ymax());
            }
            else {
                StdDraw.setPenColor(Color.blue);
                StdDraw.line(bounds.xmin(), x[1], bounds.xmax(), x[1]);
            }
        }

        public Point2D val() {
            return new Point2D(x[0], x[1]);
        }

        public int compareTo(Point2D p) {
            if (level == 0) {
                return (int) Math.signum(x[0] - p.x());
            }
            else {
                return (int) Math.signum(x[1] - p.y());
            }
        }


    }

    public static void main(String[] args) {
        StdRandom.setSeed(42);
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.5, 0.5));
        for (int i = 0; i < 10; i++) {
            tree.insert(new Point2D(
                    StdRandom.uniformDouble(),
                    StdRandom.uniformDouble()
            ));
        }

        tree.draw();

    }
}
