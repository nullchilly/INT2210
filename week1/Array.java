public class Array {
  public static void main(String[] args) {
    // String[] a = new String[3];
    String[] a = { "Lee", "Quang", "Dong" };
    for (String x : a) {
      x = "10";
    }
    for (String x : a) {
      System.out.println(x);
    }
  }
}
