import java.util.Scanner;

public class P3 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] cnt = new int[n];
    int[] p = new int[n];
    for (int i = 0; i < n - 1; i++) {
      int u = scanner.nextInt();
      int v = scanner.nextInt();
      if (p[v] == u || p[u] == v) continue;
      p[u] = v;
      cnt[u]++;
      cnt[v]++;
    }
    for (int i = 0; i < n; i++) {
      if (cnt[i] > 2) {
        System.out.println("no");
        return;
      }
    }
    System.out.println("yes");
  }
}

