import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    // api
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public Value get(Key k) {
        Value v = get(root, k);
        return v;
    }

    public void delete(Key k) {
        root = delete(root, k);
    }

    public void deleteAll() {
        root = null;
    }

    public Iterable<Key> iterator() {
        return null;
    }


    // private
    private Node put(Node node, Key key, Value value) {
        // Inserts (key,value) into the tree defined by the root `node`.
        // returns the node upon completion.
        if (node == null) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else if (cmp == 0) node.val = value;

        return node;
    }

    private Value get(Node node, Key key) {
        // gets value in tree `node`
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else /*if (cmp == 0)*/ return node.val;
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
            return node;
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
            return node;
        }
        /*else if (cmp == 0)*/
        // this is the node to delete
        if (node.left == null && node.right == null) {
            return null;
        } else if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        }
        // two children

        if (StdRandom.bernoulli(0.5)) {
            // hibbard deletion
            Node min = min(node.right); // get min from right tree
            node.right = delete(node.right, min.key); // delete the min from the right tree.
            // guaranteed to have only one child.
            min.left = node.left;
            min.right = node.right;
            return min;
        } else {
            // opposite of hibbard deletion
            Node max = max(node.left); // get max from right tree
            node.left = delete(node.left, max.key); // delete the min from the left tree.
            // guaranteed to have only one child.
            max.left = node.left;
            max.right = node.right;
            return max;
        }
    }

    private Node min(Node node) {
        // returns the minimum node.
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node max(Node node) {
        // returns the maximum node.
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private class Node {
        private Key key;
        private Value val;
        // height of the binary search tree including self.
        Node left, right;

        Node(Key k, Value v) {
            key = k;
            val = v;
        }
    }

    private StringBuilder prettyPrint(Node root, int currentHeight, int totalHeight) {
        StringBuilder sb = new StringBuilder();
        int spaces = getSpaceCount(totalHeight - currentHeight + 1);
        if (root == null) {
            //create a 'spatial' block and return it
            String row = String.format("%" + (2 * spaces + 1) + "s%n", "");
            //now repeat this row space+1 times
            String block = new String(new char[spaces + 1]).replace("\0", row);
            return new StringBuilder(block);
        }
        if (currentHeight == totalHeight) return new StringBuilder(root.key + "");
        int slashes = getSlashCount(totalHeight - currentHeight + 1);
        sb.append(String.format("%" + (spaces + 1) + "s%" + spaces + "s", root.key + "", ""));
        sb.append("\n");
        //now print / and \
        // but make sure that left and right exists
        char leftSlash = root.left == null ? ' ' : '/';
        char rightSlash = root.right == null ? ' ' : '\\';
        int spaceInBetween = 1;
        for (int i = 0, space = spaces - 1; i < slashes; i++, space--, spaceInBetween += 2) {
            for (int j = 0; j < space; j++) sb.append(" ");
            sb.append(leftSlash);
            for (int j = 0; j < spaceInBetween; j++) sb.append(" ");
            sb.append(rightSlash + "");
            for (int j = 0; j < space; j++) sb.append(" ");
            sb.append("\n");
        }

        //now get string representations of left and right subtrees
        StringBuilder leftTree = prettyPrint(root.left, currentHeight + 1, totalHeight);
        StringBuilder rightTree = prettyPrint(root.right, currentHeight + 1, totalHeight);
        // now line by line print the trees side by side
        Scanner leftScanner = new Scanner(leftTree.toString());
        Scanner rightScanner = new Scanner(rightTree.toString());
        while (leftScanner.hasNextLine()) {
            if (currentHeight == totalHeight - 1) {
                sb.append(String.format("%-2s %2s", leftScanner.nextLine(), rightScanner.nextLine()));
                sb.append("\n");
                spaceInBetween -= 2;
            } else {
                sb.append(leftScanner.nextLine());
                sb.append(" ");
                sb.append(rightScanner.nextLine() + "\n");
            }
        }

        return sb;

    }

    private int getSpaceCount(int height) {
        return (int) (3 * Math.pow(2, height - 2) - 1);
    }

    private int getSlashCount(int height) {
        if (height <= 3) return height - 1;
        return (int) (3 * Math.pow(2, height - 3) - 1);
    }

    // test
    private static int[] sortedInts(int N) {
        int[] data = new int[N];
        for (int i = 0; i < N; i++)
            data[i] = i;
        return data;
    }

    private static int[] randomInts(int N) {
        int[] data = new int[N];
        for (int i = 0; i < N; i++)
            data[i] = i;
        StdRandom.shuffle(data);
        return data;
    }

    public static void main(String[] args) {
        StdRandom.setSeed(42);
        int N = 50000;
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();

        // try random
        bst.deleteAll();
        StdOut.printf("Inserting N = %d randomly sorted datapoints...\n", N);
        for (int x : randomInts(N))
            bst.put(x, x);

        //try get
        bst.get(0);
        bst.get(12);

        //try delete and check height is accurate.m
        for (int i = 0; i < N; i++) {
            bst.delete(i);
        }
        StdOut.println("Deleted everything!");
    }
}
