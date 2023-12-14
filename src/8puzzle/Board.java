import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int n;
    private int[][] tiles;
    private int zeroI, zeroJ;
    private int hammingDistance = -1;
    private int manhattanDistance = -1;

    public Board(int[][] tiles) {
        int a = tiles.length;
        int b = tiles[0].length;
        if (a != b) throw new IllegalArgumentException(
                "Tiles must be an N x N (square) matrix not rectangular.");
        if (a < 2 || a >= 128)
            throw new IllegalArgumentException("Board size must be 2 <= N < 128.");

        this.tiles = new int[a][a];

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                int x = tiles[i][j];
                if (x < 0 || x > a * a - 1) throw new IllegalArgumentException(
                        "Tiles must be consecutive from 0 to N^2 -1");
                this.tiles[i][j] = x;
                if (x == 0) {
                    zeroI = i;
                    zeroJ = j;
                }
            }
        }
        this.n = a;


    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n);
        sb.append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%2d ", tiles[i][j]));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        if (hammingDistance == -1) hammingDistance = hamming(tiles);
        return hammingDistance;
    }

    public int manhattan() {
        if (manhattanDistance == -1) manhattanDistance = manhattan(tiles);
        return manhattanDistance;
    }

    private int hamming(int[][] initialTiles) {
        int wrongPositions = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (initialTiles[i][j] == 0) continue;
                if (initialTiles[i][j] != (j + 1) + i * n) wrongPositions++;
            }
        }
        return wrongPositions;
    }

    private int manhattan(int[][] initialTiles) {
        int distance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance += manhattan(i, j, initialTiles[i][j]);
            }
        }
        return distance;
    }

    private int manhattan(int i, int j, int val) {
        if (val == 0) return 0;
        int desiredI = (val - 1) / n, desiredJ = (val - 1) % n;
        return Math.abs(i - desiredI) + Math.abs(j - desiredJ);
    }

    public boolean isGoal() {
        return manhattan() == 0;
    }

    public boolean equals(Object y) {
        if (y == null) return false;
        if (this == y) return true;
        if (y.getClass() != this.getClass()) return false;
        Board x = (Board) y;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != x.tiles[i][j]) return false;
            }
        }
        return true;
    }

    private void swap(int i, int j, int x, int y) {
        int temp = tiles[i][j];
        tiles[i][j] = tiles[x][y];
        tiles[x][y] = temp;
    }

    public Iterable<Board> neighbors() {
        Stack<Board> boards = new Stack<>();
        int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int[] dir : directions) {
            int x = zeroI + dir[0];
            int y = zeroJ + dir[1];
            if (isValid(x, y)) {
                // make a copy of this board
                Board neighbor = new Board(tiles);
                // then swap the empty tile with the neighboring tile.
                neighbor.swap(zeroI, zeroJ, x, y);
                neighbor.zeroI = x;
                neighbor.zeroJ = y;
                neighbor.hammingDistance = neighbor.hamming(neighbor.tiles);
                neighbor.manhattanDistance = neighbor.manhattan(neighbor.tiles);

                boards.push(neighbor);
            }
        }
        return boards;
    }

    private boolean isValid(int i, int j) {
        if (i < 0 || i >= n) return false;
        if (j < 0 || j >= n) return false;
        return true;
    }

    public Board twin() {
        int i = 0, j = 0;

        while (tiles[i][j] == 0) {
            i = (i + 1);
            if (i == n) {
                i = i % n;
                j += 1;
            }
        }

        int x = i, y = j;

        while ((x == i && y == j) || tiles[x][y] == 0) {
            x = (x + 1);
            if (x == n) {
                x = x % n;
                y += 1;
            }
        }

        Board twin = new Board(tiles);
        twin.swap(i, j, x, y);
        return twin;
    }

    private static void testBoard(int n) {
        StdOut.printf("Creating a board of size: %d\n", n);
        Board test, other;
        StdOut.printf("\tInitializing tiles...\n");
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = (j + i * n + 1) % (n * n);
            }
        }
        StdOut.printf("\tInitializing test board with tiles...\n");
        test = new Board(tiles);
        other = new Board(tiles);
        StdOut.printf("Testing Board toString...\n%s\n", test.toString());
        StdOut.printf("Testing board dimension. Is board dimension correct? %b\n",
                      n == test.dimension());
        StdOut.printf("The hamming distance for this board is %d\n", test.hamming());
        StdOut.printf("The Manhattan distance for this board is %d\n", test.manhattan());
        StdOut.printf("IsGoal? %b\n", test.isGoal());
        StdOut.printf("Are two equivalent boards equal? %b\n", test.equals(other));
        StdOut.printf("The neighbors are...\n");
        for (Board neighbor : test.neighbors())
            StdOut.println(neighbor);
        StdOut.printf("One twin is \n%s\n", test.twin());
    }

    public static void main(String[] args) {
        testBoard(2);
        testBoard(3);
        testBoard(127);
    }
}
