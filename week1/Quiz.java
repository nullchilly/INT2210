public class Quiz {
  public static void main(String[] args) {
    String x = "Ho", y = "H";
    y += 'o';
    System.out.println(x == y);
    System.out.println(x.intern() == y.intern());
  }
}
