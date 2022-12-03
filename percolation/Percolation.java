import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private WeightedQuickUnionUF uf, dsu;
  private int n, top, bottom;
  private boolean[][] a; // Open grid
  private int os; // Open sites

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    uf = new WeightedQuickUnionUF(n * n + 2);
    dsu = new WeightedQuickUnionUF(n * n + 2);
    this.n = n;
    top = n * n + 1;
    bottom = n * n;
    a = new boolean[n][n];
    os = 0;
  }

  private int id(int row, int col) {
    return row * n + col;
  }

  private boolean invalid(int i, int j) {
    return i < 0 || j < 0 || i >= n || j >= n;
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    if (invalid(--row, --col)) throw new IllegalArgumentException();
    if (isOpen(row + 1, col + 1)) return;
    os++;
    a[row][col] = true;
    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, -1, 0, 1};
    for (int c = 0; c < 4; c++) {
      int x = row + dx[c];
      int y = col + dy[c];
      if (x == -1) {
        uf.union(id(row, col), bottom);
        dsu.union(id(row, col), bottom);
      }
      if (x == n) {
        uf.union(id(row, col), top);
      }
      if (invalid(x, y) || !isOpen(x + 1, y + 1)) continue;
      uf.union(id(row, col), id(x, y));
      dsu.union(id(row, col), id(x, y));
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    if (invalid(--row, --col)) throw new IllegalArgumentException();
    return a[row][col];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    if (invalid(--row, --col)) throw new IllegalArgumentException();
    return dsu.find(id(row, col)) == dsu.find(bottom);
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return os;
  }

  // does the system percolate?
  public boolean percolates() {
    return uf.find(top) == uf.find(bottom);
  }
}