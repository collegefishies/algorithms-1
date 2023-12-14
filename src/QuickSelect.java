import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickSelect {
    public static <T extends Comparable<T>> T select(T[] a, int rank) {
        // returns the value T of the item with the rank RANK.
        // if rank == 0, return the smallest, if rank == N return the largest.
        int lo = 0; int hi = a.length - 1;
        while (lo <= hi) {
            int j = partition(a, lo, hi);
            if (rank < j) {
                hi = j - 1;
            } else if (rank > j) {
                lo = j + 1;
            } else {
                return a[j];
            }
        }

        return null;
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        T pivotVal = a[lo];
        int lt = lo, gt = hi;
        int pivot = lo;
        int u = lo + 1;
        while (u <= gt) {
            if (a[u].compareTo(pivotVal) < 0) {
                swap(a, u, lt);
                u++;
                lt++;
                pivot++;
            } else if (a[u].compareTo(pivotVal) > 0) {
                swap(a, u, gt);
                gt--;
            } else {
                u++;
                pivot++;
            }
        }
        return lt;
    }

    private static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        StdRandom.setSeed(10);
        int N = 1000000;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = i;
        StdRandom.shuffle(a);

        Stopwatch s = new Stopwatch();
        int TRIALS = 10;
        for (int trials = 0; trials < TRIALS; trials++) {
            int K = StdRandom.uniformInt(N);
            if (K != select(a, K)) StdOut.println("Quick Select Failed.");
            else StdOut.println("Quick Select Found " + K);
        }
        double t = s.elapsedTime();
        StdOut.printf("Average running time was T/(N*Trials) = %g s", t/(N*TRIALS));
    }
}
