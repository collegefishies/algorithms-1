/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solver {
    private boolean solvable = false;
    private int moves = -1;
    private Deque<Board> solution = new ArrayDeque<>();

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Argument must be non-null.");

        // initialize A* and twin A*
        MinPQ<SearchNode> searchQueue = new MinPQ<>();
        MinPQ<SearchNode> twinSearchQueue = new MinPQ<>();
        searchQueue.insert(new SearchNode(null, initial, 0));
        twinSearchQueue.insert(new SearchNode(null, initial.twin(), 0));

        // run A* and twin A*
        while (!searchQueue.isEmpty() || !twinSearchQueue.isEmpty()) {
            if (!searchQueue.isEmpty()) {
                SearchNode currentNode = searchQueue.min();
                searchQueue.delMin();

                if (currentNode.board().isGoal()) {
                    solvable = true;
                    moves = currentNode.moves();
                    // remember solution
                    while (currentNode != null) {
                        solution.push(currentNode.board());
                        currentNode = currentNode.prev;
                    }
                    return;
                }

                // add all neighbors.
                for (Board neighbor : currentNode.neighbors()) {
                    if (currentNode.prev != null && currentNode.prev.prev != null
                            && neighbor.equals(
                            currentNode.prev.prev.board())) continue;
                    searchQueue.insert(
                            new SearchNode(currentNode, neighbor, currentNode.moves() + 1));
                }

            }
            if (!twinSearchQueue.isEmpty()) {
                SearchNode twinNode = twinSearchQueue.min();
                twinSearchQueue.delMin();

                if (twinNode.board().isGoal()) {
                    // the initial is unsolvable.
                    return;
                }
                for (Board neighbor : twinNode.neighbors()) {
                    if (twinNode.prev != null && twinNode.prev.prev != null && neighbor.equals(
                            twinNode.prev.prev.board())) continue;
                    twinSearchQueue.insert(
                            new SearchNode(twinNode, neighbor, twinNode.moves() + 1));
                }
            }
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        if (solution.size() == 0) return null;
        else return new ArrayDeque<>(solution);
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        public SearchNode prev;
        private Board curr;
        private int movesMade;

        SearchNode(SearchNode prev, Board curr, int movesMade) {
            this.prev = prev;
            this.curr = curr;
            this.movesMade = movesMade;
        }

        private int manhattanPriority(SearchNode x) {
            int a = curr.manhattan() + movesMade;
            int b = x.curr.manhattan() + x.movesMade;
            if (a > b) return 1;
            else if (a < b) return -1;
            else return 0;
        }

        // private int hammingPriority(SearchNode x) {
        //     int a = curr.hamming() + movesMade;
        //     int b = x.curr.hamming() + x.movesMade;
        //     if (a > b) return 1;
        //     else if (a < b) return -1;
        //     else return 0;
        // }

        public int compareTo(SearchNode x) {
            return manhattanPriority(x);
        }

        public Iterable<Board> neighbors() {
            return curr.neighbors();
        }

        public int moves() {
            return movesMade;
        }

        public Board board() {
            return curr;
        }

        public Board prevBoard() {
            if (prev == null) return null;
            return prev.board();
        }
    }
}
