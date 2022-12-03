import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
  private Digraph G;

  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    this.G = new Digraph(G);
  }

  private int[] bfs(Iterable<Integer> st) {
    Queue<Integer> q = new Queue<>();
    int n = G.V();
    int[] d = new int[n];
    for (int i = 0; i < n; i++) d[i] = Integer.MAX_VALUE;
    for (Integer s : st) {
//      System.out.println(s);
      q.enqueue(s);
      d[s] = 0;
    }
    while (!q.isEmpty()) {
      int v = q.dequeue();
      for (int u : G.adj(v)) {
        if (d[u] == Integer.MAX_VALUE) {
          d[u] = d[v] + 1;
          q.enqueue(u);
        }
      }
    }
//    for (int i = 0; i < n; i++) {
//      if (d[i] != Integer.MAX_VALUE) {
//        System.out.print(i + " " + d[i] + "\n");
//      }
//    }
    return d;
  }

  private int[] solve(Iterable<Integer> u, Iterable<Integer> v) {
    int[] du = bfs(u);
    int[] dv = bfs(v);
    int n = G.V();
    int ans = Integer.MAX_VALUE, id = -1;
    for (int i = 0; i < n; i++) {
      if (du[i] != Integer.MAX_VALUE && dv[i] != Integer.MAX_VALUE && ans > du[i] + dv[i]) {
        ans = du[i] + dv[i];
        id = i;
      }
    }
    return new int[]{ans == Integer.MAX_VALUE ? -1 : ans, id};
  }

  private int[] solve(int u, int v) {
    SET<Integer> su = new SET<>();
    su.add(u);
    SET<Integer> sv = new SET<>();
    sv.add(v);
    return solve(su, sv);
  }

  private boolean invalid(int v) {
    return v < 0 || v >= G.V();
  }

  private boolean invalid(Iterable<Integer> a) {
    if (a == null) throw new IllegalArgumentException();
    for (Integer u : a) {
      if (u == null || invalid(u)) return true;
    }
    return false;
  }

  // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w) {
    if (invalid(v) || invalid(w)) throw new IllegalArgumentException();
    return solve(v, w)[0];
  }

  // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w) {
    if (invalid(v) || invalid(w)) throw new IllegalArgumentException();
    return solve(v, w)[1];
  }

  // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    if (invalid(v) || invalid(w)) throw new IllegalArgumentException();
    return solve(v, w)[0];
  }

  // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    if (invalid(v) || invalid(w)) throw new IllegalArgumentException();
    return solve(v, w)[1];
  }

  // do unit testing of this class
  public static void main(String[] args) {
    In in = new In("digraph1.txt");
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    while (!StdIn.isEmpty()) {
      int v = StdIn.readInt();
      int w = StdIn.readInt();
      int length = sap.length(v, w);
      int ancestor = sap.ancestor(v, w);
      StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
  }
}