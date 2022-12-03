import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

  private int moves = -1;
  private Stack<Board> ans;

  private class Node implements Comparable<Node> {
    private int height = 0, weight = 0, manhattan;
    private Node pre;
    private Board board;

    public Node(Node pre, Board board) {
      this.pre = pre;
      this.height = (pre != null ? pre.height : 0) + 1;
      this.board = board;
      this.manhattan = board.manhattan();
      this.weight = height + manhattan;
    }

    @Override
    public int compareTo(Node o) {
      return weight == o.weight ? manhattan - o.manhattan : weight - o.weight;
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) {
      throw new IllegalArgumentException();
    }
    MinPQ<Node> pq = new MinPQ<>();
    pq.insert(new Node(null, initial));
    Board twin = initial.twin();
    pq.insert(new Node(null, twin));
//    System.out.println(cur.toString() + " " + twin.toString());
//    System.out.println(cur.manhattan() + " " + twin.manhattan());
//    System.out.println("start");
    while (!pq.isEmpty()) {
      Node cur = pq.delMin();
//      System.out.println(cur.board);
      if (cur.board.isGoal()) {
        ans = new Stack<>();
        Node k = cur;
        while (k != null) {
          ans.push(k.board);
          k = k.pre;
        }
//        System.out.println(ans.peek());
//        System.out.println(initial);
        if (!ans.peek().equals(initial)) {
          moves = -1;
          break;
        }
        moves = ans.size() - 1;
        break;
      }
      Node p = cur.pre;
      for (Board u : cur.board.neighbors()) {
        if (p == null || !p.board.equals(u)) pq.insert(new Node(cur, u));
      }
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
    In in = new In("inf.txt");
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
