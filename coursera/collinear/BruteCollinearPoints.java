import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

  private LineSegment[] a;

  public BruteCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException();
    }

    Point[] np = new Point[points.length];

    for (int i = 0; i < points.length; i++) {
      Point p = points[i];
      if (p == null) {
        throw new IllegalArgumentException();
      }
      np[i] = p;
    }
    Arrays.sort(np);
    Point pre = null;
    for (int i = 0; i < np.length; i++) {
      if (pre != null && pre.compareTo(np[i]) == 0) {
        throw new IllegalArgumentException();
      }
      pre = np[i];
    }

    List<LineSegment> ans = new ArrayList<>();

    for (int i = 0; i < np.length; i++) {
      for (int j = i + 1; j < np.length; j++) {
        for (int k = j + 1; k < np.length; k++) {
          for (int l = k + 1; l < np.length; l++) {
            if (np[i].slopeTo(np[j]) == np[j].slopeTo(np[k]) && np[j].slopeTo(np[k]) == np[k].slopeTo(np[l])) {
              ans.add(new LineSegment(np[i], np[l]));
            }
          }
        }
      }
    }
    a = ans.toArray(new LineSegment[ans.size()]);
  }    // finds all line segments containing 4 points

  public int numberOfSegments() {
    return a.length;
  }        // the number of line segments

  public LineSegment[] segments() {
    return a.clone();
  }                // the line segments

  public static void main(String[] args) {

    // read the n points from a file
    In in = new In("brute.txt");
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
      System.out.println(x + " " + y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}