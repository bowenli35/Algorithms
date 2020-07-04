package Stack_Queue;

import java.util.NoSuchElementException;

public class Queue_Array<Item> {

    private Item[] queue;
    private int size;

    public Queue_Array() {
        queue = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Item item) {
        validate(item);
        if (size == queue.length) resize(2 * size);
        queue[size++] = item;
    }

    public Item dequeue() {
        validate();
        Item item = queue[--size];
        int length = queue.length;
        if (size > 0 && size == length / 4) resize(length / 2);
        return item;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    private void validate(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void validate() {
        if (isEmpty()) throw new NoSuchElementException();
    }

}
