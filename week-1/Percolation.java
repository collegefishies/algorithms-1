public class Percolation {
    private int sideLength;
    private int numberOfOpenSites;
    private boolean[][] openSites;
    private WeightedQuickUnionWithPathCompressionUF UnionFind;

    public Percolation(int n) {
        //O(n^2)
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than or equal to 1");
        }
        sideLength = n;
        numberOfOpenSites = 0;
        openSites = new boolean[n][n];
    }

    public void open(int row, int col) {
        //O(1)
        if (row < 1 || row > sideLength || col < 1 || col > sideLength) {
            throw new IllegalArgumentException("All indices must be from 1 to " + sideLength);
        }
        openSites[row - 1][col - 1] = true;
        numberOfOpenSites += 1;
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
        return !isOpen(row, col);
    }

    public int numberOfOpenSites() {
        //O(1)
        return numberOfOpenSites;
    }

    public boolean percolates() {
        //must be O(1)
        return false;
    }

    public static void main(String[] args) {

    }
}
