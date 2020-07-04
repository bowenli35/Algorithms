package Stack_Queue;

import java.util.NoSuchElementException;

public class Queue_LinkedList<Item> {

    private Node head;
    private Node tail;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(Item item) {
        validate(item);
        Node oldTail = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        if (isEmpty()) head = tail;
        else oldTail.next = tail;
    }

    public Item dequeue() {
        validate();
        Item item = head.item;
        head = head.next;
        if (isEmpty()) tail = null;
        return item;
    }

    private void validate(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void validate() {
        if (isEmpty()) throw new NoSuchElementException();
    }
}
