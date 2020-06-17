package Stacks;

public class StackOfStrings {

    private int count = 0;
    private Node root = null;

    private class Node {
        String node;
        Node next;
    }

    public void push(String node) {
        Node old = root;
        root = new Node();
        root.node = node;
        root.next = old;
        count++;
    }

    public String pop() {
        String node = root.node;
        root = root.next;
        count--;
        return node;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return count;
    }
}
