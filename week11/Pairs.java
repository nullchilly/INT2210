import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Pairs {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int k = scanner.nextInt();
    int[] a = new int[n];
    HashMap<Integer, Integer> m = new HashMap<>();
    int ans = 0;
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
      int neg = a[i] - k;
      int pos = a[i] + k;
      ans += m.getOrDefault(neg, 0) + m.getOrDefault(pos, 0);
      m.put(a[i], m.getOrDefault(a[i], 0) + 1);
    }
    System.out.println(ans);
  }
}
