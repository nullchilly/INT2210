import java.util.Scanner;

public class ConnectedCellsinaGrid {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int s = scanner.nextInt();
        System.out.print(s + " ");
      }
      System.out.println();
    }
  }
}
