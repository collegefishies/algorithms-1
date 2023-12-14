import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Quicksort {
    public static <T extends Comparable<T>> void dutchSort(T[] a) {
        StdRandom.shuffle(a);
        dutchSort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void dutchSort(T[] a, int lo, int hi) {
        if (hi <= lo) return;
        // 3-way partition
        int pivot = lo; // right most edge of pivot inclusive.
        int lt = pivot; // right most edge of lessThan elements (non-inclusive);
        int gt = hi;    // left most edge of greaterThan elements (non-inclusive);
        int u = pivot + 1; // pointer to an unknown element.

        while (u <= gt) {
            T uVal = a[u];

            // conditionally swap, then update the indices
            if (uVal.compareTo(a[pivot]) > 0)
                // dont decrement u, as it remains unknown after the swap
                swap(a, u, gt--);
            else if (uVal.compareTo(a[pivot]) < 0) {
                swap(a, lt++, u); //lt always points to the left most pivot.
                pivot = u;
                u = u + 1;
            } else if (uVal.compareTo(a[pivot]) == 0) {
                pivot = u;
                u = u + 1;
            }

        }

        // record the positions that mark the boundaries of the
        // pivotValue equal subarray.
        int left = lt, right = pivot;
        // partition is done. sort left and right subhalves.
        dutchSort(a, lo, left - 1);
        dutchSort(a, right + 1, hi);
    }

    public static <T extends Comparable<T>> void hoareSort(T[] a) {
        StdRandom.shuffle(a);
        hoareSort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void hoareSort(T[] a, int lo, int hi) {
        if (hi <= lo) return;

        //partition
        int j = partition(a, lo, hi);
        //sort left
        hoareSort(a, lo, j - 1);
        //sort right
        hoareSort(a, j + 1, hi);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int pivot = lo;
        int lt = pivot;
        int gt = hi + 1;

        while (true) {
            // ++x guarantess that we can get a one-line incrementer,
            // while also ensuring that despite any breaks. x holds the value last compared.
            // x++ holds the value last compared plus 1. which is more confusing.
            // this means we just need to make the variables one off from their
            // first value
            while (a[++lt].compareTo(a[pivot]) <= 0)
                // if lt == hi, we have verified that the entire array is all <= the partition.
                // that means we are done.
                if (lt == hi) break;

            while (a[--gt].compareTo(a[pivot]) >= 0)
                // this IF means that we have also verified that the entire
                // array is partitioned with everything greater than pivot.
                if (gt == pivot) break;

            // pointers have swapped, which means we have partitioned the whole
            // array. Basically, just cus we found something that violated the invariant
            // either it was because an element was out of the way, or
            // we hit the boundary of the other subarray we were enforcing.
            // so break out if we hit the other array.

            if (gt < lt) break;
            // in the case that the array is already trivially partitioned,
            // gt may equal lt if there is no element in the greaterThan set.
            if (gt == lt) break;

            // if we haven't broken, we've found violations of our invariant.
            swap(a, gt, lt);
        }

        // when we're done place the pivot in the correct location.
        swap(a, pivot, gt); //since gt is non-inclusive, it labels the last position
        // of the subarray that's all less than or equal to the pivot.
        pivot = gt;
        return pivot;
    }

    private static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static <T extends Comparable<T>> boolean verifySort(T[] a) {
        if (a.length < 2) return true;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) return false;
        }
        return true;
    }

    private static boolean testHoareSort(int N) {

        Integer[] a = new Integer[N];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniformInt(N / 2 + 1);
        hoareSort(a);
        boolean sorted = verifySort(a);
        if (!sorted) {
//            StdOut.println("Hoare Sort failed to sort for N = " + N);
        } else if (N <= 10 && N > 1) {
            StdOut.println("Sorted: " + Arrays.toString(a) + " (N = " + N);
        }
        return sorted;
    }

    private static boolean testHoareSortConstant(int N) {

        Integer[] a = new Integer[N];
        for (int i = 0; i < a.length; i++)
            a[i] = 0;
        hoareSort(a);
        boolean sorted = verifySort(a);
        if (!sorted) {
//            StdOut.println("Hoare Sort failed to sort for N = " + N);
        } else if (N <= 10 && N > 1) {
            StdOut.println("Sorted: " + Arrays.toString(a) + " (N = " + N);
        }
        return sorted;
    }

    public static void main(String[] args) {
        StdRandom.setSeed(0);
        StdOut.print("Testing hoareSort... ");

        int fails = 0;
        boolean succeeded = true;
        for (int i = 1; i < 1000; i++) {
            if (i == 6) {
                StdOut.println();
            }
            if (! testHoareSort(i)) {
                fails++;
                succeeded = false;
            }
        }
        if (succeeded) {
            StdOut.println("Success!");
        } else {
            StdOut.printf("Failed %d tests.\n", fails);
        }

        StdOut.print("Testing hoare sort on constant input... ");
        testHoareSortConstant(10);
        StdOut.println("Success!");
    }
}
