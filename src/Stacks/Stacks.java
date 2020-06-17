package Stacks;

import java.util.Iterator;

public class Stacks<Item> {

    private Node first;
    private int n;

    private class Node {
        Item node;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node old = first;
        first = new Node();
        first.next = old;
        n++;
    }

    public Item pop() {
        Item item = first.node;
        first = first.next;
        n--;
        return item;
    }
}
