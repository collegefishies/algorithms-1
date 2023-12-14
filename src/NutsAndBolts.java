// Idea: Use bolts as pivot points for the nuts.
//       Keep an empty array to memorize where the nuts as pivots.
//       At the end of the algorithm, nuts/bolts in the sameboxes will correspond to the same size.

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class NutsAndBolts {
    public static void sort(int[] nuts, int[] bolts) {
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);

//        StdOut.print("Nuts:  ");
//        StdOut.println(Arrays.toString(nuts));
//        StdOut.print("Bolts: ");
//        StdOut.println(Arrays.toString(bolts));

        sort(nuts, bolts, 0, nuts.length - 1);


    }

    private static void sort(int[] nuts, int[] bolts, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(nuts, bolts, lo, hi);
        sort(nuts, bolts, lo, j - 1);
        sort(nuts, bolts, j + 1, hi);
    }

    private static int partition(int[] nuts, int[] bolts, int lo, int hi) {
        int nutPivotVal = nuts[lo];
        //partition bolts
        int boltPivot = -1;
        int lt = lo, gt = hi; //lessThan, greaterThan boundaries (non-inclusive).
        int u = lo;
        while (u <= gt) {
            if (bolts[u] < nutPivotVal) {
                swap(bolts, u++, lt++);
            } else if (bolts[u] > nutPivotVal) {
                swap(bolts, u, gt--);
            } else {u++;}
            // just increment. we've found the pivot
            // but thanks to our invariant the pivot index == lt at the end of the algorithm.
        }
        boltPivot = lt;



        //partition nuts.
        int boltPivotVal = bolts[boltPivot];
        if (boltPivotVal != nutPivotVal) throw new AssertionError(
                "The pivots aren't equal, nutPivotVal was " + nutPivotVal + " while boltPivotVal was " + boltPivotVal);
        int nutPivot = -1;
        lt = lo; gt = hi;
        u = lo;
        while (u <= gt) {
            if (nuts[u] < boltPivotVal) {
                swap(nuts, u++, lt++);
            } else if (nuts[u] > boltPivotVal) {
                swap(nuts, u, gt--);
            } else {
                u++; // found pivot value.
            }
        }
        nutPivot = lt;
        if (nuts[nutPivot] != bolts[boltPivot]) throw new AssertionError(
                "Pivots not in same location.");
//        StdOut.printf("Partitioned Bolts (Pivot Value = %d : pivotIndex = %d): %s\n", nutPivotVal, boltPivot, Arrays.toString(bolts));
//        StdOut.printf("Partitioned Nuts  (Pivot Value = %d : pivotIndex = %d): %s\n", boltPivotVal, nutPivot, Arrays.toString(nuts));
        return boltPivot;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        StdRandom.setSeed(10);
        testSorts();
//        testPartition();
    }

    private static void testSorts() {
        StdOut.print("Testing sort()... \n");
        for (int N = 3; N < 20; N++) {
            int[] nuts = new int[N];
            int[] bolts = new int[N];
            for (int i = 0; i < N; i++) {
                nuts[i] = i + N + 1;
                bolts[i] = i + N + 1;
            }

            sort(nuts, bolts);
            StdOut.printf("Sorted Nuts : %s\n", Arrays.toString(nuts));
            StdOut.printf("Sorted Bolts: %s\n", Arrays.toString(bolts));
        }
        StdOut.println("Success!");
    }
    private static void testPartition() {
        StdOut.print("Testing partition()... ");
        for (int N = 3; N < 20; N++) {
            int[] nuts = new int[N];
            int[] bolts = new int[N];
            for (int i = 0; i < N; i++) {
                nuts[i] = i + N + 1;
                bolts[i] = i + N + 1;
            }
            StdRandom.shuffle(nuts);
            StdRandom.shuffle(bolts);
            int k = partition(nuts, bolts, 0, N - 1);
            if (nuts[k] != bolts[k]) throw new RuntimeException("Pivots aren't in same place");
        }
        StdOut.println("Success!");
    }
}
