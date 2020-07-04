package Stack_Queue;

import java.util.NoSuchElementException;

public class Stacks_Array<Item> {

    private Item[] stacks;
    private int n;

    public Stacks_Array() {
        stacks = (Item[]) new Object[1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(Item item) {
        validate(item);
        if (n == stacks.length) resize(2 * n);
        stacks[n++] = item;
    }

    public Item pop() {
        validate();
        Item item = stacks[--n];
        int length = stacks.length;
        if (n > 0 && n == length / 4) resize(length / 2);
        return item;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            temp[i] = stacks[i];
        }
        stacks = temp;
    }

    private void validate(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void validate() {
        if (isEmpty()) throw new NoSuchElementException();
    }
}
