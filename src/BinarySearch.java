import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BinarySearch {
    public static int any(int[] a, int val) { // finds any index that matches val
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] > val)
                high = mid - 1;
            else if (a[mid] < val)
                low = mid + 1;
            else
                return mid;
        }

        return -1;
    }

    public static int left(int[] a, int val) {
        //return the whole array
        int left = 0;
        int right = a.length - 1;

        StdOut.println("Target Value: " + val);
        while (left <= right) {
            // assert if `val` is in the array, it must be within (left, right).
            int mid = left + (right - left) / 2;
            StdOut.printf("%-5s: %5d\n%-5s: %5d\n%-5s: %5d\n", "left", left, "mid", mid, "right", right);
            StdOut.println(Arrays.toString(Arrays.copyOfRange(a, left, right + 1)));
            if (val < a[mid])
                // assert within(left, right) and val < mid;
                // => within(left, mid - 1);
                right = mid - 1;
                // => within(left, right);
            else if (val == a[mid]) {
                // within(left, mid);
                if (mid == 0)
                    return mid;

                // within(left, mid);
                assert mid > 0;
                if (val != a[mid - 1]) {
                    // within(mid, mid);
                    assert a[mid] == val && a[mid - 1] != val;
                    return mid;
                } else {
                    // within (left, mid - 1);
                    assert val == a[mid - 1];
                    right = mid - 1;
                }
            } else if (val > a[mid])
                left = mid + 1;
        }

        int mid = left + (right - left) / 2;
        StdOut.printf("%-5s: %5d\n%-5s: %5d\n%-5s: %5d\n", "left", left, "mid", mid, "right", right);
        StdOut.println(Arrays.toString(Arrays.copyOfRange(a, left, right + 1)));

        return -1;
    }

    public static int right(int[] a, int val) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (val < a[mid])
                right = mid - 1;
            else if (val > a[mid])
                left = mid + 1;
            else {
                assert a[mid] == val;
                if (mid == a.length - 1) {
                    return mid;
                }

                if (a[mid + 1] == val) {
                    left = mid + 1;
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }
}
