package Stacks;

public class FixedCapacityStackOfStrings {

    private String[] s;
    private int n;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(String item) {
        s[n++] = item;
    }

    public String pop() {
        String item = s[--n];
        s[n] = null;
        return item;
    }
}
