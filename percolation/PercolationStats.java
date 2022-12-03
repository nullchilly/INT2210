import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private int trials;
  private double[] a;


  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("N and T must be <= 0");
    }
    this.trials = trials;
    a = new double[trials];

    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);
      while (!percolation.percolates()) {
        percolation.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1, n + 1));
      }
      a[i] = (double) percolation.numberOfOpenSites() / (n * n);
    }
  }


  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(a);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(a);
  }

  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
  }

  // test client (described below)
  public static void main(String[] args) {
    int n = 10, k = 10;
    if (args.length >= 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    }
    PercolationStats ans = new PercolationStats(n, k);

    StdOut.println("mean                    = " + ans.mean());
    StdOut.println("stddev                  = " + ans.stddev());
    StdOut.printf("95%% confidence interval = [%f, %f]\n", ans.confidenceLo(), ans.confidenceHi());
  }
}