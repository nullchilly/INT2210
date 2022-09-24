import java.util.Scanner;
// import Calculator;

public class App {
  public static void main(String[] args) {
    System.out.println("Welcome to Calculator App ^^");

    // Using scanner.nextInt() to get input as an integer from the keyboard
    Scanner scanner = new Scanner(System.in);

    // Using calculator to perform operations
    Calculator calculator = new Calculator();

    /* Your code here */
    while (true) {
      int type = scanner.nextInt();
      switch(type) {
        case 0:
          System.out.println("Exit");
          return;
        case 1:
          System.out.print("Add 2 numbers: ");
          int x = scanner.nextInt(), y = scanner.nextInt();
          System.out.println(calculator.add(x, y));
          break;
        case 2:
          System.out.print("Subtract 2 numbers: ");
          x = scanner.nextInt(); y = scanner.nextInt();
          System.out.println(calculator.subtract(x, y));
          break;
        case 3:
          System.out.print("Multiply 2 numbers: ");
          x = scanner.nextInt(); y = scanner.nextInt();
          System.out.println(calculator.multiple(x, y));
          break;
        case 4:
          System.out.print("Divide 2 numbers: ");
          x = scanner.nextInt(); y = scanner.nextInt();
          System.out.println(calculator.divide(x, y));
          break;
      }
    }
  }
}