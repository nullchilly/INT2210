import java.util.Scanner;

public class JavaStringReverse {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String str = scanner.nextLine();
    System.out.println(str.equals(new StringBuilder(str).reverse().toString()) ? "Yes" : "No");
  }
}