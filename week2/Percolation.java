import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private int n;

  private boolean[][] o; // Open grid
  private int os; // Open sites

  private int top;
  private int bottom;

  private WeightedQuickUnionUF uf;

  private int convert1D(int i, int j) {
    return i * n + j;
  }

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    this.n = n;
    o = new boolean[n][n];
    uf = new WeightedQuickUnionUF(convert1D(n - 1, n - 1) + 10);
    top = convert1D(n - 1, n - 1) + 1;
    bottom = convert1D(n - 1, n - 1) + 2;
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    row--;
    col--;
    if (o[row][col]) {
      return;
    }
    o[row][col] = true;
    os++;

    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, -1, 0, 1};
    for (int c = 0; c < 4; c++) {
      int i = row + dx[c], j = col + dy[c];
      if (i < 0 || j < 0 || i >= n || j >= n) {
        continue;
      }
      uf.union(convert1D(row, col), convert1D(i, j));
    }
    if (row == 0) {
      uf.union(top, convert1D(row, col));
    }
    if (row == n - 1) {
      uf.union(bottom, convert1D(row, col));
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    row--;
    col--;
    return o[row][col];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    row--;
    col--;
    return uf.find(top) == uf.find(convert1D(row, col));
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return os;
  }

  // does the system percolate?
  public boolean percolates() {
    return uf.find(top) == uf.find(bottom);
  }

  // test client (optional)
  public static void main(String[] args) {

  }
}
