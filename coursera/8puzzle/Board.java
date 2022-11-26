import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class Board {

  private int n;
  private int[][] a;

  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    a = tiles.clone();
    n = a.length;
  }

  // string representation of this board
  public String toString() {
    StringBuilder ans = new StringBuilder();
    ans.append(n).append("\n");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        ans.append(a[i][j]);
        if (j < n - 1) ans.append(" ");
      }
      ans.append("\n");
    }
    return ans.toString();
  }

  // board dimension n
  public int dimension() {
    return n;
  }

  // number of tiles out of place
  public int hamming() {
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (a[i][j] != 0 && a[i][j] != i * n + j + 1) {
          cnt++;
        }
      }
    }
    return cnt;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    int ans = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (a[i][j] != 0) {
          ans += Math.abs(i - ((a[i][j] - 1) / n)) + Math.abs(j - ((a[i][j] - 1) % n));
        }
      }
    }
    return ans;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return hamming() == 0;
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (y == null || getClass() == y.getClass()) return false;
    Board o = (Board) y;
    if (n != o.n) return false;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (a[i][j] != o.a[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  private int[][] exchange(int i, int j, int k, int l) {
//    int[][] b = a.clone();
    int[][] b = new int[n][n];
    for (int u = 0; u < n; u++) {
      for (int v = 0; v < n; v++) {
        b[u][v] = a[u][v];
      }
    }
    int tmp = b[i][j];
    b[i][j] = b[k][l];
    b[k][l] = tmp;
    return b;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    int bi = 0, bj = 0; // blanks
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (a[i][j] == 0) {
          bi = i;
          bj = j;
          break;
        }
      }
    }
    List<Board> ans = new ArrayList<>();
    if (bi >= 1) ans.add(new Board(exchange(bi, bj, bi - 1, bj)));
    if (bi + 1 < n) ans.add(new Board(exchange(bi, bj, bi + 1, bj)));
    if (bj >= 1) ans.add(new Board(exchange(bi, bj, bi, bj - 1)));
    if (bj + 1 < n) ans.add(new Board(exchange(bi, bj, bi, bj + 1)));
    for (int i = 0; i < ans.size(); i++) {
      Board cur = ans.get(i);
//      System.out.println(cur.toString());
    }
    return ans;
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    if (a[0][0] == 0 || a[0][1] == 0) {
      return new Board(exchange(1, 0, 1, 1));
    }
    return new Board(exchange(0, 0, 0, 1));
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    In in = new In();
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        tiles[i][j] = in.readInt();
    Board board = new Board(tiles);
    System.out.println(board.toString());
  }

}