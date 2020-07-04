package Stack_Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] deque;
    private int index;

    // construct an empty randomized queue
    public RandomizedQueue() {
        deque = (Item[]) new Object[1];
        index = 0;
    }
    // is the randomized queue empty?
    public boolean isEmpty() {
        return index == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return index;
    }

    // add the item
    public void enqueue(Item item) {
        validate(item);
        if (index == deque.length) resize(2 * index);
        deque[index++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        validate();
        int random = StdRandom.uniform(index);
        Item item = deque[random];
        if (random != index - 1) deque[random] = deque[index - 1];
        deque[index - 1] = null;
        index--;
        int length = deque.length;
        if (index > 0 && index == length / 4) resize(length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validate();
        int random = StdRandom.uniform(index);
        return deque[random];
    }
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < index; i++) {
            temp[i] = deque[i];
        }
        deque = temp;
    }

    private void validate(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void validate() {
        if (isEmpty()) throw new NoSuchElementException();
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] copy = (Item[]) new Object[deque.length];
        private int n = index;

        public RandomizedQueueIterator(){
            for (int i = 0; i < copy.length; i++){
                copy[i] = deque[i];
            }
        }
        @Override
        public boolean hasNext() {
            return n != 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int random = StdRandom.uniform(n);
            Item item = copy[random];
            if (random != n - 1) copy[random] = copy[n - 1];
            copy[n - 1] = null;
            n--;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> deque = new RandomizedQueue<Integer>();
        System.out.println(deque.isEmpty());
        deque.enqueue(1);
        System.out.println(deque.dequeue());
        deque.enqueue(1);
        deque.enqueue(2);
        deque.enqueue(3);
        System.out.println(deque.sample());
        System.out.println(deque.sample());
        System.out.println(deque.sample());
    }

}