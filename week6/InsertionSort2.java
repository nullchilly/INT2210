import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Bruh2 {

  /*
   * Complete the 'insertionSort2' function below.
   *
   * The function accepts following parameters:
   *  1. INTEGER n
   *  2. INTEGER_ARRAY arr
   */

  public static void insertionSort2(int n, List<Integer> a) {
    for (int i = 1; i < n; i++) {
      for (int j = i; j >= 1; j--) {
        if (a.get(j) < a.get(j - 1)) {
          int tmp = a.get(j);
          a.set(j, tmp);
          a.set(j, a.get(j - 1));
          a.set(j - 1, tmp);
        }
      }
      for (int j = 0; j < n; j++) {
        System.out.print(a.get(j) + " ");
      }
      System.out.println();
    }
  }

}

public class InsertionSort2 {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

    Bruh2.insertionSort2(n, arr);

    bufferedReader.close();
  }
}