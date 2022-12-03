import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Topological;

import java.util.HashMap;

public class WordNet {
  //  private String[] a = new String[1000005];
  private HashMap<Integer, String> a = new HashMap<>();
  private HashMap<String, SET<Integer>> nouns = new HashMap<>();
  private SAP sap;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) {
    if (synsets == null || hypernyms == null) {
      throw new IllegalArgumentException();
    }
    In sc = new In(synsets);
    int n = 0;
    while (!sc.isEmpty()) {
      String[] list = sc.readLine().split(",");
      int id = Integer.parseInt(list[0]);
      a.put(n++, list[1]);
      for (String noun : list[1].split(" ")) {
//        System.out.print(noun + " ");
        if (!nouns.containsKey(noun)) {
          nouns.put(noun, new SET<>());
        }
        nouns.get(noun).add(id);
      }
//      System.out.println();
    }
    sc.close();
    sc = new In(hypernyms);
    Digraph G = new Digraph(n);
    boolean[] vis = new boolean[n];
    while (!sc.isEmpty()) {
      String line = sc.readLine();
      String[] ids = line.split(",");
      int u = Integer.parseInt(ids[0]);
      vis[u] = ids.length > 1;
      for (int i = 1; i < ids.length; i++) {
        int v = Integer.parseInt(ids[i]);
//        System.out.println(v + " " + u);
        G.addEdge(u, v);
      }
    }
    int cnt = 0;
    for (int i = 0; i < n; i++) if (!vis[i]) cnt++;
    if (cnt != 1 || !(new Topological(G)).hasOrder()) throw new IllegalArgumentException();
    sap = new SAP(G);
    sc.close();
  }

  // returns all WordNet nouns
  public Iterable<String> nouns() {
    return this.nouns.keySet();
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    if (word == null) {
      throw new IllegalArgumentException();
    }
    return nouns.containsKey(word);
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) {
      throw new IllegalArgumentException();
    }
    return sap.length(nouns.get(nounA), nouns.get(nounB));
  }

  // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) {
      throw new IllegalArgumentException();
    }
    return a.get(sap.ancestor(nouns.get(nounA), nouns.get(nounB)));
  }

  // do unit testing of this class
  public static void main(String[] args) {
    WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
    System.out.println(wordnet.distance("chaperone", "suction_stop"));
  }
}