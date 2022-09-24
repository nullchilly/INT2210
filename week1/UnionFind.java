import edu.princeton.cs.algs4.*;

public class UnionFind {
  int[] id;

  public UnionFind(int n) {
    int[] id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }
  public boolean connected(int u, int v) {
    return id[u] == id[v];
  }
  public void union(int u, int v) {
  }
  public static void main(String[] args) {
  }
}
