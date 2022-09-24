import java.util.Arrays;
import java.util.HashMap;

public class Twosum {
  public static void main(String[] args) {
    int[] a = { -1, -2, 1, 2, 3 };
    int n = a.length;
    Arrays.sort(a);
    HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();
    int ans = 0;
    for (int i = 0; i < n; i++) {
      cnt.put(a[i], cnt.containsKey(a[i]) ? cnt.get(a[i]) + 1 : 1);
      if ((i == 0 || a[i] != a[i - 1]) && cnt.containsKey(-a[i])) {
        ans += cnt.get(-a[i]);
      }
    }
    System.out.println(ans);
  }
}