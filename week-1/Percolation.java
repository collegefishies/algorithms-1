import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sideLength;
    private int numberOfOpenSites;
    private boolean[][] openSites;
    //use algs4 function for homework submission.
    private WeightedQuickUnionUF UnionFind;

    public Percolation(int n) {
        //O(n^2)
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than or equal to 1");
        }
        sideLength = n;
        numberOfOpenSites = 0;
        openSites = new boolean[n][n];

        UnionFind = new WeightedQuickUnionUF(n * n + 2);
        //connect all virtual sites with top and bottom row.
        for (int i = 1; i <= n; i++) {
            UnionFind.union(0, enumerate(1, i));
        }
        for (int i = 1; i <= n; i++) {
            UnionFind.union(n * n + 1, enumerate(n, i));
        }
    }

    public static void main(String[] args) {

    }

    private int enumerate(int row, int col) {
        //returns an enumeration from 1 to sideLength^2 for each site in the array
        if (row < 1 || row > sideLength || col < 1 || col > sideLength) {
            throw new IllegalArgumentException("All indices must be from 1 to " + sideLength);
        }
        return col + (row - 1) * sideLength;
    }

    private void connectOpenNeighbor(int row1, int col1, int row2, int col2) {
        if (row1 < 1 || row1 > sideLength || col1 < 1 || col1 > sideLength) {
            throw new IllegalArgumentException("All indices must be from 1 to " + sideLength);
        }
        if (row2 < 1 || row2 > sideLength || col2 < 1 || col2 > sideLength) {
            throw new IllegalArgumentException("All indices must be from 1 to " + sideLength);
        }

        if (isOpen(row1, col1) && isOpen(row2, col2)) {
            UnionFind.union(enumerate(row1, col1), enumerate(row2, col2));
        }
    }

    private void connectAllOpenNeighbors(int row, int col) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Directions: up, down, left, right

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            try {
                connectOpenNeighbor(row, col, newRow, newCol);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    public void open(int row, int col) {
        //O(1)

        //open sites
        if (row < 1 || row > sideLength || col < 1 || col > sideLength) {
            throw new IllegalArgumentException("All indices must be from 1 to " + sideLength);
        }
        openSites[row - 1][col - 1] = true;
        numberOfOpenSites += 1;

        //connect neighbors
        connectAllOpenNeighbors(row, col);
    }

    public boolean isOpen(int row, int col) {
        //O(1)
        if (row < 1 || row > sideLength || col < 1 || col > sideLength) {
            throw new IllegalArgumentException("All indices must be from 1 to " + sideLength);
        }
        return openSites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        //O(1)
        return UnionFind.connected(0, enumerate(row, col));
    }

    public int numberOfOpenSites() {
        //O(1)
        return numberOfOpenSites;
    }

    public boolean percolates() {
        //must be O(1)
        return UnionFind.connected(0, sideLength * sideLength + 1);
    }
}
