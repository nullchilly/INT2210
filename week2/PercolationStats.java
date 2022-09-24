import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private int k;
  private double[] x;


  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("N and T must be <= 0");
    }
    k = trials;
    x = new double[k];

    for (int i = 0; i < k; i++) {
      Percolation percolation = new Percolation(n);
      while (!percolation.percolates()) {
        int row = StdRandom.uniform(1, n + 1);
        int col = StdRandom.uniform(1, n + 1);
        percolation.open(row, col);
      }
      int openSites = percolation.numberOfOpenSites();
      x[i] = (double) openSites / (n * n);
    }
  }


  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(x);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(x);
  }

  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - ((1.96 * stddev()) / Math.sqrt(k));

  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + ((1.96 * stddev()) / Math.sqrt(k));
  }

  // test client (described below)
  public static void main(String[] args) {
    int n = 10;
    int k = 10;
    if (args.length >= 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    }
    PercolationStats ps = new PercolationStats(n, k);

    StdOut.println("mean                    = " + ps.mean());
    StdOut.println("stddev                  = " + ps.stddev());
    System.out.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
  }
}