import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

  private int moves = -1;
  private Stack<Board> ans;

  private class Node implements Comparable {
    protected int depth = 0;
    protected int height = 0, weight = 0;
    Node pre;

    Board cur;

    public Node(int height, int weight, Node pre, Board cur) {
      this.height = height;
      this.weight = weight;
      this.pre = pre;
      this.cur = cur;
    }

    @Override
    public int compareTo(Object that) {
      Node o = (Node) that;
      return weight == o.weight ? cur.manhattan() - o.cur.manhattan() : weight - o.weight;
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    MinPQ<Node> pq = new MinPQ<>();
    Board cur = initial;
//    pq.insert(new Node(0, cur.manhattan(), null, cur));
    Board twin = cur.twin();
    pq.insert(new Node(0, twin.manhattan(), null, twin));
//    System.out.println(cur.toString() + " " + twin.toString());
//    System.out.println(cur.manhattan() + " " + twin.manhattan());
    Node k = null;
    System.out.println("start");
    while (!pq.isEmpty()) {
      Node top = pq.delMin();
      cur = top.cur;
//      System.out.println(cur.toString() + " " + cur.hamming());
      int h = top.height;
//      int w = top.weight;
      if (cur.isGoal()) {
        moves = h;
        k = top;
        System.out.println(h);
        break;
      }
      Node p = top.pre;
      for (Board u : cur.neighbors()) {
        if (p == null || !p.cur.equals(u)) pq.insert(new Node(h + 1, h + 1 + u.manhattan(), top,
                u));
      }
//      if (cnt == 1) break;
    }
    ans = new Stack<>();
    while (k != null) {
//      System.out.println(k.cur);
      ans.push(k.cur);
      k = k.pre;
    }
  }

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return moves != -1;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    return moves;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (moves == -1) return null;
    return ans;
  }

  // test client (see below)
  public static void main(String[] args) {
    // create initial board from file
    In in = new In("txt.txt");
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
}
