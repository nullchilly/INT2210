public class HelloWorld {
  public static void main(String[] args) {
    int n = args.length;
    if (n == 0) {
      System.out.println("Hello World");
    } else System.out.println("Hello " + args[0]);
  }
}
