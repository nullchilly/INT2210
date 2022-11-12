import java.util.*;

public class MissingNumbers {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    HashMap<Integer, Integer> ma = new HashMap<>();
    HashMap<Integer, Integer> mb = new HashMap<>();
    int ans = 0;
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
      ma.put(a[i], ma.getOrDefault(a[i], 0) + 1);
    }
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
      mb.put(a[i], mb.getOrDefault(a[i], 0) + 1);
    }
    Iterator<Map.Entry<Integer, Integer>> entries = ma.entrySet().iterator();
    while (entries.hasNext()) {
      Map.Entry<Integer, Integer> entry = entries.next();
      if (!Objects.equals(mb.getOrDefault(entry.getKey(), 0), entry.getValue())) {
        System.out.println(entry.getKey());
      }
    }
  }
}
