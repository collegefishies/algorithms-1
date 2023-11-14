import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private double[] openFractions;
    private Percolation percolation;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must both be positive.");
        }
        openFractions = new double[trials];

        for (int trial = 0; trial < trials; trial++) {
            percolation = new Percolation(n);

            while (!percolation.percolates()) {
                // select random site
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;
                percolation.open(row, col);
            }

            openFractions[trial] = (1.0) * percolation.numberOfOpenSites() / Math.pow(n, 2);
        }
    }

    private void printStats() {
        System.out.printf("mean                     = %f%n", mean());
        System.out.printf("stddev                   = %f%n", stddev());
        System.out.printf("95%% confidence interval = [%f, %f]%n", confidenceLo(), confidenceHi());
    }

    public double mean() {
        return StdStats.mean(openFractions);
    }

    public double stddev() {
        return StdStats.stddev(openFractions);
    }

    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(openFractions.length);
    }

    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(openFractions.length);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Must have two args, n (grid size), and trials (number of experiments to determine percolation threshold)");
        }

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(n, trials);

        p.printStats();
    }
}

