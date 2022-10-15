import java.util.Scanner;

public class CountingSort1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] cnt = new int[100];
    for (int i = 0; i < n; i++) {
      int cur = scanner.nextInt();
      cnt[cur] = cnt[cur] + 1;
    }
    for (int i = 0; i < cnt.length; i++) {
      System.out.print(cnt[i] + " ");
    }
  }
}