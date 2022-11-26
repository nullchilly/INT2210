import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }                         // constructs the point (x, y)

  public void draw() {
    StdDraw.point(x, y);
  }                               // draws this point

  public void drawTo(Point that) {
    StdDraw.line(x, y, that.x, that.y);
  }                   // draws the line segment from this point

  // to that point
  public String toString() {
    return "(" + x + ", " + y + ")";
  }                          // string representation

  public int compareTo(Point that) {
    return y == that.y ? x - that.x : y - that.y;
  }     // compare two points by y-coordinates,

  // breaking ties by x-coordinates
  public double slopeTo(Point that) {
    if (compareTo(that) == 0) {
      return Double.NEGATIVE_INFINITY;
    } else if (y == that.y) {
      return 0;
    } else if (x == that.x) {
      return Double.POSITIVE_INFINITY;
    }
    return (y - that.y) / (double) (x - that.x);
  }       // the slope between this point and that

  // point
  public Comparator<Point> slopeOrder() {
    return new Comparator<Point>() {
      @Override
      public int compare(Point x, Point y) {
        return Double.compare(slopeTo(x), slopeTo(y));
      }
    };
  }             // compare two points by slopes they make
  // with this point

  public static void main(String[] args) {
    Point p = new Point(1, 2);
    p.draw();
  }
}