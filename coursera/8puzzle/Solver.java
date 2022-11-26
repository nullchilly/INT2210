import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Iterator;

public class Solver {

  private class Boardy extends Comparator {
    int depth = 0;
    int weight = 0;
    Boardy pre;

    public Boardy(int depth, int weight, Boardy pre) {
      this.depth = depth;
      this.weight = weight;
      this.pre = pre;
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    MinPQ<Boardy> pq = new MinPQ<>();
    Board cur = initial;
    while (true) {
      Boardy top = pq.delMin();
      int h = top.depth, w = top.weight;
      Boardy p = top.pre;
      for (Board u : cur.neighbors()) {
        pq.insert(new Boardy(h + 1, w + u.manhattan(), u));
      }
    }
  }

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return false;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    return 0;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    return null;
  }

  // test client (see below)
  public static void main(String[] args) {

    // create initial board from file
    In in = new In(args[0]);
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
