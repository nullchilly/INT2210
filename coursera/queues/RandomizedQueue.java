import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] a;
  private int n = 0;

  // construct an empty randomized queue
  public RandomizedQueue() {
    a = (Item[]) new Object[1];
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return n == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return n;
  }

  private void resize(int sz) {
    Item[] na = (Item[]) new Object[sz];
    for (int i = 0; i < n; i++) {
      na[i] = a[i];
    }
    a = na;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    a[n++] = item;
    if (n == a.length) {
      resize(n * 2);
    }
  }

  // remove and return a random item
  public Item dequeue() {
    if (n == 0) {
      throw new NoSuchElementException();
    }
    int i = StdRandom.uniformInt(n);
    Item item = a[i];
    a[i] = a[--n];
    if (n <= a.length / 4) {
      resize(a.length / 2);
    }
    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (n == 0) {
      throw new NoSuchElementException();
    }
    return a[StdRandom.uniformInt(n)];
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private final int[] id = new int[n];
    private int cur = 0;

    private RandomizedQueueIterator() {
      for (int i = 0; i < n; i++) {
        id[i] = i;
      }
      StdRandom.shuffle(id);
    }
    public boolean hasNext() {
      return cur < n;
    }
    public void remove() {
      throw new UnsupportedOperationException();
    }
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return a[id[cur++]];
    }
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    for (int i = 0; i < 10; i++) {
      rq.enqueue(i);
    }
    for (Integer cur : rq) {
      System.out.print(cur + " ");
    }
  }
}
