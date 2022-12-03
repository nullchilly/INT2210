import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
  private WordNet wordnet;
  public Outcast(WordNet wordnet) {
    if (wordnet == null) {
      throw new IllegalArgumentException();
    }
    this.wordnet = wordnet;
  }         // constructor takes a WordNet object

  public String outcast(String[] nouns) {
    int mx = -1;
    int id = -1;
    for (int i = 0; i < nouns.length; i++) {
      int cur = 0;
      for (int j = 0; j < nouns.length; j++) {
        cur += wordnet.distance(nouns[i], nouns[j]);
      }
      if (mx < cur) {
        mx = cur;
        id = i;
      }
    }
    return nouns[id];
  }   // given an array of WordNet nouns, return an outcast

  public static void main(String args[]) {
    String[] a = { "synsets.txt", "hypernyms.txt", "outcast5.txt", "outcast8.txt", "outcast11" +
            ".txt" };
    WordNet wordnet = new WordNet(a[0], a[1]);
    Outcast outcast = new Outcast(wordnet);
    for (int t = 2; t < a.length; t++) {
      In in = new In(a[t]);
      String[] nouns = in.readAllStrings();
      StdOut.println(a[t] + ": " + outcast.outcast(nouns));
    }
  }
}