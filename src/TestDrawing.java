import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class TestDrawing {
    private static void drawScaling() {
        int N = 100;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N*N);
        StdDraw.setPenRadius(0.01);
        for (int i = 1; i <= N; i++) {
            StdDraw.point(i, i);
            StdDraw.point(i, i*i);
            StdDraw.point(i, i*Math.log(i));
        }
    }

    private static void drawRandomArray() {
        int N = 50;
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        double[] a = new double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.random();
        for (int i = 0; i < N; i++) {
            double x = 1.0 * i / N;
            double y = a[i]/2.0;
            double rw = 0.4/N;
            double rh = a[i]/2.0;
            StdDraw.filledRectangle(x,y,rw,rh);
        }
    }

    private static void drawSortedRandomArray() {
        int N = 50;
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        double[] a = new double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.random();
        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            double x = 1.0 * i / N;
            double y = a[i]/2.0;
            double rw = 0.4/N;
            double rh = a[i]/2.0;
            StdDraw.filledRectangle(x,y,rw,rh);
        }
    }

    public static void main(String[] args) {
        drawSortedRandomArray();
    }
}
