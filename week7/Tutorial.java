import java.util.ArrayList;
import java.util.Scanner;

public class Tutorial {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(), V = sc.nextInt();
    ArrayList<Integer> a = new ArrayList<Integer>();
    for (int i = 0; i < V; i++) {
      a.add(sc.nextInt());
    }

    for (int i = 0; i < a.size(); i++) {
      if (a.get(i) == n)
        System.out.println(i);
    }
  }
}