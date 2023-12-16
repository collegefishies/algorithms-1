import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TaxicabNumbers {
    // api
    public static int[] taxicabNumbers(int n) {
        n -= 1;
        int[] cubicSums = new int[n*(n-1)/2];
        int i = 0;
        for (int a = 1; a <= n; a++) {
            for (int b = a+1; b <= n; b++) {
                cubicSums[i++] = sumOfCubes(a,b);
            }
        }
        Arrays.sort(cubicSums);

        Queue<Integer> q = new LinkedList<>();
        for (i = 0; i < cubicSums.length - 1; i++) {
            if (cubicSums[i] == cubicSums[i+1]) {
                q.add(cubicSums[i]);
            }
        }

        int[] numbers = new int[q.size()];
        for (i = 0; i < numbers.length; i++) {
            numbers[i] = q.remove();
        }

        return numbers;
    }
    // private
    private static boolean isTaxicab(int a, int b, int c, int d) {
        if (a == c || a == d) return false;
        if (b == c || b == d) return false;
        return sumOfCubes(a,b) == sumOfCubes(c,d);
    }

    private static int find(int[] a, int val) {
        // finds cube in the list or returns -1.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (val < a[mid]) hi = mid-1;
            else if (val > a[mid]) lo = mid + 1;
            else return mid;
        }

        return -1;
    }
    private static int sumOfCubes(int a, int b) {
        return a*a*a + b*b*b;
    }

    private static int cube(int a) {
        return a*a*a;
    }

    // test
    public static void main(String[] args) {
        int N = 200;
        int[] numbers = taxicabNumbers(N);
        int[] cubes = new int[N+1];
        for (int i = 0; i <= N; i++)
            cubes[i] = i*i*i;

//        StdOut.println(find(cubes, cube(13)));
        StdOut.println("Taxicab numbers whose components are less than " + N);
        StdOut.println(Arrays.toString(numbers));
    }
}
