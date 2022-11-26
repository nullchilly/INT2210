import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    RandomizedQueue<String> rq = new RandomizedQueue<>();
    while (!StdIn.isEmpty()) {
      String i = StdIn.readString();
      if (i == null) break;
      rq.enqueue(i);
    }
    Iterator<String> i = rq.iterator();
    while (i.hasNext() && n-- > 0) {
      Object cur = i.next();
      System.out.println(cur);
    }
  }
}
