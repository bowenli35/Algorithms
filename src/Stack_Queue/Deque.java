package Stack_Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int count;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        validate(item);
        Node oldFirst = head;
        head = new Node();
        head.item = item;
        head.prev = null;
        head.next = oldFirst;
        if (isEmpty()) tail = head;
        else oldFirst.prev = head;
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        validate(item);
        Node oldLast = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        tail.prev = oldLast;
        if (isEmpty()) head = tail;
        else oldLast.next = tail;
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        validate();
        Item toRemove = head.item;
        head = head.next;
        count--;
        if (isEmpty()) tail = null;
        else head.prev = null;
        return toRemove;
    }

    // remove and return the item from the back
    public Item removeLast() {
        validate();
        Item toRemove = tail.item;
        tail = tail.prev;
        count--;
        if (isEmpty()) head = null;
        else tail.next = null;
        return toRemove;
    }

    private void validate(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void validate() {
        if (isEmpty()) throw new NoSuchElementException();
    }
    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        System.out.println(d.isEmpty());
        d.addFirst(1);
        System.out.println(d.isEmpty());
        d.addFirst(2);
        System.out.println(d.isEmpty());
        System.out.println(d.removeFirst());
        System.out.println(d.removeLast());
        System.out.println(d.size());
        System.out.println(d.isEmpty());
        d.addFirst(1);
        d.addLast(2);
        d.addFirst(0);
        d.addLast(3);
        System.out.println(d.removeFirst());
        System.out.println(d.removeLast());
        System.out.println(d.size());
    }
}