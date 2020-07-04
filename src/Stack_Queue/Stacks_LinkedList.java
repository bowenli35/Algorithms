package Stack_Queue;

import java.util.NoSuchElementException;

public class Stacks_LinkedList<Item> {

    private Node head;

    private class Node {
        Item item;
        Node next;
    }

    public Stacks_LinkedList() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(Item item) {
        validate(item);
        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;
    }

    public Item pop() {
        validate();
        Item item = head.item;
        head = head.next;
        return item;
    }

    private void validate(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void validate() {
        if (isEmpty()) throw new NoSuchElementException();
    }
}
