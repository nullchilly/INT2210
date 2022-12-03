import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private int n = 0;

  private class Node {
    private Node prev;
    private Node next;
    private Item item;
  }

  private Node first, last;

  // construct an empty deque
  public Deque() { }

  // is the deque empty?
  public boolean isEmpty() {
    return n == 0;
  }

  // return the number of items on the deque
  public int size() {
    return n;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    n++;
    Node newFirst = new Node();
    newFirst.item = item;
    if (first != null) {
      newFirst.next = first;
      first.prev = newFirst;
    } else {
      first = newFirst;
      last = newFirst;
    }
    first = newFirst;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    n++;
    Node newLast = new Node();
    newLast.item = item;
    if (last != null) {
      newLast.prev = last;
      last.next = newLast;
    } else {
      last = newLast;
      first = newLast;
    }
    last = newLast;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (first == null) {
      throw new NoSuchElementException();
    }
    Item item = first.item;
    if (n == 1) {
      first = last = null;
    } else {
      Node newFirst = first.next;
      newFirst.prev = null;
      first = newFirst;
      if (n == 2) last = first;
    }
    n--;
    return item;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (last == null) {
      throw new NoSuchElementException();
    }
    Item item = last.item;
    if (n == 1) {
      first = last = null;
    } else {
      Node newLast = last.prev;
      newLast.next = null;
      last = newLast;
      if (n == 2) first = last;
    }
    n--;
    return item;
  }

  private class DequeIterator implements Iterator<Item> {
    Node cur = first;

    @Override
    public boolean hasNext() {
      return cur != null;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Item item = cur.item;
      cur = cur.next;
      return item;
    }
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(1);
    deque.addFirst(2);
    deque.removeFirst();
    Iterator<Integer> i = deque.iterator();
    while (i.hasNext()) {
      Object cur = i.next();
      System.out.print(cur + " ");
    }
  }
}