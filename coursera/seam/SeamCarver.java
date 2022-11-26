import edu.princeton.cs.algs4.Picture;

import java.awt.Color;
import java.util.Arrays;

public class SeamCarver {
  private Picture pic;
  private int n, m;

  // create a seam carver object based on the given picture
  public SeamCarver(Picture picture) {
    if (picture == null) {
      throw new IllegalArgumentException();
    }
    this.pic = new Picture(picture);
    n = pic.width();
    m = pic.height();
  }

  // current picture
  public Picture picture() {
    return new Picture(pic);
  }

  // width of current picture
  public int width() {
    return n;
  }

  // height of current picture
  public int height() {
    return m;
  }

  private double delta(Color a, Color b) {
    return Math.pow(a.getRed() - b.getRed(), 2) + Math.pow(a.getGreen() - b.getGreen(), 2) + Math.pow(a.getBlue() - b.getBlue(), 2);
  }

  // energy of pixel at column x and row y
  public double energy(int x, int y) {
    if (x < 0 || y < 0 || x >= n || y >= m) {
      throw new IllegalArgumentException();
    }
    if (x == 0 || y == 0 || x == n - 1 || y == m - 1) {
      return 1000;
    }
    Color left = pic.get(x - 1, y);
    Color right = pic.get(x + 1, y);
    Color up = pic.get(x, y - 1);
    Color down = pic.get(x, y + 1);

    return Math.sqrt(delta(left, right) + delta(up, down));
  }

  // sequence of indices for horizontal seam
  public int[] findHorizontalSeam() {
    double[][] dp = new double[n][m];
//    int[][] trace = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
//        System.out.print(energy(i, j) + " ");
        if (i == 0) {
          dp[i][j] = 1000;
          continue;
        }
        dp[i][j] = Math.min(dp[i - 1][j], Math.min(
                        j >= 1 ? dp[i - 1][j - 1] : Double.POSITIVE_INFINITY,
                        j + 1 < m ? dp[i - 1][j + 1] : Double.POSITIVE_INFINITY)) + energy(i, j);
//        System.out.print(dp[i][j] + " ");
      }
//      System.out.println();
    }
    int[] ans = new int[n];
    double mn = dp[n - 1][0];
    int k = 0;
    for (int j = 0; j < m; j++) {
      if (mn > dp[n - 1][j]) {
        mn = dp[n - 1][j];
//        System.out.print(dp[n - 1][j] + " ");
        k = j;
      }
    }
    for (int i = n - 1; i > 0; i--) {
      ans[i] = k;
      double e = energy(i, k);
      if (k >= 1 && dp[i - 1][k - 1] + e == dp[i][k]) {
        k--;
      } else if (k + 1 < m && dp[i - 1][k + 1] + e == dp[i][k]) {
        k++;
      }
    }
//    System.out.println(mn + " " + k);
    ans[0] = k;
    return ans;
  }

  private void rotate() {
    Picture np = new Picture(m, n);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        np.set(j, i, pic.get(i, j));
      }
    }
    n = np.width();
    m = np.height();
    pic = np;
  }

  // sequence of indices for vertical seam
  public int[] findVerticalSeam() {
    rotate();
    int[] ans = findHorizontalSeam();
    rotate();
    return ans;
  }

  // remove horizontal seam from current picture
  public void removeHorizontalSeam(int[] seam) {
    if (seam == null || seam.length != n) {
      throw new IllegalArgumentException();
    }
    Picture np = new Picture(n, m - 1);
    for (int i = 0; i < n; i++) {
      if (seam[i] < 0 || seam[i] >= m || Math.abs(i >= 1 ? seam[i] - seam[i - 1] : 0) > 1) {
        throw new IllegalArgumentException();
      }
      for (int j = 0; j < seam[i]; j++) np.set(i, j, pic.get(i, j));
      for (int j = seam[i] + 1; j < m; j++) np.set(i, j - 1, pic.get(i, j));
    }
    m--;
    pic = np;
  }

  // remove vertical seam from current picture
  public void removeVerticalSeam(int[] seam) {
    rotate();
    removeHorizontalSeam(seam);
    rotate();
  }

  //  unit testing (optional)
  public static void main(String[] args) {
    SeamCarver seam = new SeamCarver(new Picture("3x4.png"));
    System.out.println(seam.width() + " " + seam.height());
    System.out.println(Arrays.toString(seam.findVerticalSeam()));
    seam.removeHorizontalSeam(seam.findHorizontalSeam());
  }
}