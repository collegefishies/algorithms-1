import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sideLength;
    private int numberOfOpenSites;
    private boolean[][] openSites;
    private WeightedQuickUnionUF percolatesUnionFind;
    private WeightedQuickUnionUF noBackwashUnionFind;

    public Percolation(int n) {
        // O(n^2)
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than or equal to 1");
        }
        sideLength = n;
        numberOfOpenSites = 0;
        openSites = new boolean[n][n];

        percolatesUnionFind = new WeightedQuickUnionUF(n * n + 2);
        noBackwashUnionFind = new WeightedQuickUnionUF(n * n + 2);

        // connect all virtual sites with top row.
        for (int i = 1; i <= n; i++) {
            union(0, enumerate(1, i));
        }

        // connect all virtual sites with bottom row.
        for (int i = 1; i <= n; i++) {
            percolatesUnionFind.union(n * n + 1, enumerate(n, i));
        }
    }

    private void union(int p, int q) {
        percolatesUnionFind.union(p, q);
        noBackwashUnionFind.union(p, q);
    }

    private int enumerate(int row, int col) {
        // returns an enumeration from 1 to sideLength^2 for each site in the array
        if (row < 1 || row > sideLength || col < 1 || col > sideLength) {
            throw new IllegalArgumentException("All indices must be from 1 to " + sideLength);
        }
        return col + (row - 1) * sideLength;
    }

    private void connectOpenNeighbor(int row1, int col1, int row2, int col2) {
        if (!isValid(row1, col1) || !isValid(row2, col2)) {
            throw new IllegalArgumentException("All indices must be from 1 to " + sideLength);
        }

        if (isOpen(row1, col1) && isOpen(row2, col2)) {
            union(enumerate(row1, col1), enumerate(row2, col2));
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 1 && row <= sideLength && col >= 1 && col <= sideLength;
    }

    private void connectAllOpenNeighbors(int row, int col) {
        // O(1)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Directions: up, down, left, right

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isValid(newRow, newCol)) {
                connectOpenNeighbor(row, col, newRow, newCol);
            }
        }
    }

    public void open(int row, int col) {
        // O(1)

        // open sites
        if (row < 1 || row > sideLength || col < 1 || col > sideLength) {
            throw new IllegalArgumentException("All indices must be from 1 to " + sideLength);
        } else if (isOpen(row, col)) {
            return;
        }

        openSites[row - 1][col - 1] = true;
        numberOfOpenSites += 1;
        // connect neighbors
        connectAllOpenNeighbors(row, col);
    }

    public boolean isOpen(int row, int col) {
        // O(1)
        if (row < 1 || row > sideLength || col < 1 || col > sideLength) {
            throw new IllegalArgumentException("All indices must be from 1 to " + sideLength);
        }
        return openSites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        // O(1)
        boolean isConnectedToTop = noBackwashUnionFind.find(0) == noBackwashUnionFind.find(enumerate(row, col));
        return isConnectedToTop && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        // O(1)
        return numberOfOpenSites;
    }

    public boolean percolates() {
        // O(1)
        return percolatesUnionFind.find(0) == percolatesUnionFind.find(sideLength * sideLength + 1);
    }
}
