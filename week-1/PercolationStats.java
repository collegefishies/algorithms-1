import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int n;
    private int trials;
    private double openFractions[];
    private Percolation percolation;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must both be positive.");
        }
        this.n = n;
        this.trials = trials;
        openFractions = new double[trials];

        for (int trial = 0; trial < trials; trial++) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                //select random site
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;

                if (percolation.isFull(row, col)) {
                    percolation.open(row, col);
                }
            }

            if (percolation.percolates()) {
                openFractions[trial] = (1.0) * percolation.numberOfOpenSites() / Math.pow(n, 2);
            }
        }

        printStats();
    }

    private void printStats() {
        System.out.printf("mean                     = %f\n", mean());
        System.out.printf("stddev                   = %f\n", stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n", confidenceLo(), confidenceHi());
    }

    public double mean() {
        double mean = 0;
        for (int i = 0; i < openFractions.length; i++) {
            mean += openFractions[i];
        }
        mean /= openFractions.length;
        return mean;
    }

    public double stddev() {
        double avg = mean();
        double variance = 0;
        for (double x : openFractions) {
            variance += Math.pow(x - avg, 2);
        }
        variance /= openFractions.length - 1;
        return Math.sqrt(variance);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(openFractions.length);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(openFractions.length);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Must have two args, n (grid size), and trials (number of experiments to determine percolation threshold)");
        }

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);


    }
}

