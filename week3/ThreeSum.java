import java.util.Arrays;
import java.util.HashMap;

public class Threesum {
  public static void main(String[] args) {
    int[] a = {-1, -2, 1, 2, 2, 0, -4};
    int n = a.length;
    Arrays.sort(a);
    HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();
    int ans = 0;
    for (int x : a) {
      cnt.put(x, cnt.containsKey(x) ? cnt.get(x) + 1 : 1);
    }
    for (int i = 0; i < n; i++) {
      if (i != 0 && a[i] == a[i - 1]) continue;
      for (int j = i + 1; j < n; j++) {
        if (a[j] == a[j - 1]) continue;
        int x = -(a[i] + a[j]);
        if (x < a[j]) continue;
        ans += cnt.containsKey(x) ? 1 : 0;
      }
    }
    System.out.println(ans); }
}