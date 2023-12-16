/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private boolean solvable = false;
    private int moves = -1;
    private Stack<Board> solution = new Stack<>();

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
                    if (currentNode.prev != null
                            && neighbor.equals(currentNode.prev.board())) continue;
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
                    if (twinNode.prev != null && neighbor.equals(
                            twinNode.prev.board())) continue;
                    twinSearchQueue.insert(
                            new SearchNode(twinNode, neighbor, twinNode.moves() + 1));
                }
            }
        }
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

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        if (solution.size() == 0) return null;
        else return solution;
    }

    private class SearchNode implements Comparable<SearchNode> {
        public SearchNode prev;
        private Board curr;
        private int movesMade;
        private int manhattanPriority = -1;
        private int hammingPriority = -1;

        SearchNode(SearchNode prev, Board curr, int movesMade) {
            this.prev = prev;
            this.curr = curr;
            this.movesMade = movesMade;

        }

        public int compareTo(SearchNode x) {
            return manhattanPriority(x);
        }

        public void initManhattan() {
            if (manhattanPriority == -1)
                manhattanPriority = curr.manhattan() + movesMade;
        }

        public void initHamming() {
            if (hammingPriority == -1)
                hammingPriority = curr.hamming() + movesMade;
        }

        private int hammingPriority(SearchNode x) {
            initHamming();
            x.initHamming();
            int a = this.hammingPriority;
            int b = x.hammingPriority;
            if (a > b) return 1;
            else if (a < b) return -1;
            else return 0;
        }

        private int manhattanPriority(SearchNode x) {
            initManhattan();
            x.initManhattan();
            int a = this.manhattanPriority;
            int b = x.manhattanPriority;
            if (a > b) return 1;
            else if (a < b) return -1;
            else return hammingPriority(x);
        }

        public Iterable<Board> neighbors() {
            return curr.neighbors();
        }

        public int moves() {
            return movesMade;
        }

        public Board prevBoard() {
            if (prev == null) return null;
            return prev.board();
        }

        public Board board() {
            return curr;
        }
    }
}
