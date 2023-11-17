import java.util.Arrays;

public class BinarySearch {
    public static int any(int[] a, int val) {
        // finds any index that matches val
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int mid = low + (high - low + 1)/2;

            if (a[mid] > val) {
                high = mid - 1;
            } else if (a[mid] < val) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static int left(int[] a, int val) {
        if (a.length == 0) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;
        int mid = (high - low) / 2 + low;
        int width = high - low + 1;

        while (low < high) {
            width = high - low + 1;
            int[] subarray = Arrays.copyOfRange(a, low, high + 1);

            mid = (high - low) / 2 + low;

            if (a[mid] > val) {
                high = mid - 1;
            } else if (a[mid] < val) {
                low = mid + 1;
            } else {
                return low;
            }
        }

        if (a[low] == val) {
            return low;
        }
        return -1;
    }

    public static int right(int[] a, int val) {
        if (a.length == 0) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;
        int mid = (high - low) / 2 + low;

        while (low < high) {
            if (a[mid] > val) {
                high = mid - 1;
            } else if (a[mid] < val) {
                low = mid + 1;
            } else {
                low = mid + 1;
            }

            mid = (high - low) / 2 + low;
        }

        if (a[high] == val) {
            return high;
        } else if (a[high - 1] == val) {
            return high - 1;
        }

        return -1;
    }
}
