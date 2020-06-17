package Queue;

import Stacks.StackOfStrings;

public class QueueOfStrings {

    private int n = 0;
    private Node head;
    private Node Last;

    private class Node {
        String node;
        Node next;
    }

    public void enqueue(String node) {
        Node old = Last;
        Last = new Node();
        Last.node = node;
        Last.next = null;
        if(isEmpty()) head = Last;
        else old.next = Last;
        n++;
    }

    public String dequeue() {
        String node = head.node;
        head = head.next;
        if(isEmpty()) Last = null;
        n--;
        return node;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return n;
    }
}
