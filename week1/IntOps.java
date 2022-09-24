public class IntOps {
  public static void main(String[] args) {
    int a = Integer.parseInt(args[0]), b = Integer.parseInt(args[1]);
    int sum = a + b;
    int prod = a * b;
    int quot = a / b;
    int rem = a % b;
    
    System.out.println(sum);
    System.out.println(prod);
    System.out.println(quot);
    System.out.println(rem);
  }
}
