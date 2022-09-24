import edu.princeton.cs.algs4.*;

public class UFClient2 {
  public static void main(String[] args) {
    int N = StdIn.readInt();
    UF uf = new UF(N);
    int id = 0;
    while (!StdIn.isEmpty()) {
      id++;
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if (!uf.connected(p, q)) {
        uf.union(p, q);
      }
      if (uf.count() == 1) {
        System.out.println(id);
        return;
      }
    }
    System.out.println("FAILED");
  }
}
