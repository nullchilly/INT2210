import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Bruh {

  /*
   * Complete the 'insertionSort1' function below.
   *
   * The function accepts following parameters:
   *  1. INTEGER n
   *  2. INTEGER_ARRAY arr
   */

  public static void insertionSort1(int n, List<Integer> a) {
    int k = a.get(n - 1), i;
    for (i = n - 1; i >= 0; i--) {
      if (i == 0 || a.get(i - 1) < k) {
        a.set(i, k);
        break;
      }
      a.set(i, a.get(i - 1));
      for (int j = 0; j < n; j++) {
        System.out.print(a.get(j) + " ");
      }
      System.out.println();
    }
    for (int j = 0; j < n; j++) {
      System.out.print(a.get(j) + " ");
    }
  }
}

public class InsertionSort1 {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

    Bruh.insertionSort1(n, arr);

    bufferedReader.close();
  }
}
