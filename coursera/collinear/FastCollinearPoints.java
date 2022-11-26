import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
  private LineSegment[] a;

  public FastCollinearPoints(Point[] points) {
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

    for (int i = 0; i < points.length; i++) {
      Arrays.sort(np, points[i].slopeOrder());
      int prev = 0;
      double preSlope = points[i].slopeTo(np[0]);
//      System.out.print(points[i].toString());
      Point low = np[0], high = np[0];
      for (int j = 0; j < np.length; j++) {
        double curSlope = points[i].slopeTo(np[j]);
//        System.out.print(np[j].toString() + "," + preSlope + "," + curSlope + " ");
        if (preSlope != curSlope) {
//          System.out.print(low.toString() + "," + high.toString() + prev + "->" + j + " ");
          if (j - prev >= 3 && points[i].compareTo(low) <= 0) {
//            System.out.print(points[i].toString()+","+np[prev].toString()+","+np[j - 1] + "," + preSlope+","+prev+"->"+(j-1) + " ");
            ans.add(new LineSegment(points[i], high));
          }
          prev = j;
          low = high = np[j];
        } else {
//          System.out.print(np[j] + "," + high.compareTo(np[j]) + " ");
          if (low.compareTo(np[j]) > 0) {
            low = np[j];
          }
          if (high.compareTo(np[j]) < 0) {
            high = np[j];
          }
          if (j == np.length - 1 && j - prev >= 2 && points[i].compareTo(low) <= 0) {
            ans.add(new LineSegment(points[i], high));
          }
        }
        preSlope = curSlope;
      }
//      System.out.println();
    }

    a = ans.toArray(new LineSegment[ans.size()]);
  }     // finds all line segments containing 4 or more

  // points
  public int numberOfSegments() {
    return a.length;
  }        // the number of line segments

  public LineSegment[] segments() {
    return a.clone();
  }                // the line segments

  public static void main(String[] args) {

    // read the n points from a file
    In in = new In("fast.txt");
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
