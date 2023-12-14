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
        MinPQ<SearchNode> pq = new MinPQ<>();
        MinPQ<SearchNode> pqTwin = new MinPQ<>();
        pq.insert(new SearchNode(null, initial, 0));
        pqTwin.insert(new SearchNode(null, initial.twin(), 0));

        // run A* and twin A*
        while (true) {
            if (!pq.isEmpty()) {
                SearchNode x = pq.min();
                pq.delMin();

                if (x.board().isGoal()) {
                    solvable = true;
                    moves = x.moves();
                    // remember solution
                    while (x != null) {
                        solution.push(x.board());
                        x = x.prev;
                    }
                    return;
                }

                // add all neighbors.
                for (Board neighbor : x.neighbors()) {
                    if (x.prev != null && x.prev.prev != null && neighbor.equals(
                            x.prev.prev.board())) continue;
                    pq.insert(new SearchNode(x, neighbor, x.moves() + 1));
                }

            }
            if (!pqTwin.isEmpty()) {
                SearchNode t = pqTwin.min();
                pqTwin.delMin();

                if (t.board().isGoal()) {
                    // the initial is unsolvable.
                    return;
                }
                for (Board neighbor : t.neighbors()) {
                    if (t.prev != null && t.prev.prev != null && neighbor.equals(
                            t.prev.prev.board())) continue;
                    pqTwin.insert(new SearchNode(t, neighbor, t.moves() + 1));
                }
            }
            if (pq.isEmpty() && pqTwin.isEmpty()) {
                return;
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
