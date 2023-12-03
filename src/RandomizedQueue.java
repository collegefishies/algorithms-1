import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] arr = (Item[]) new Object[1];

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("No enqueueing null items.");
        if (size == arr.length) resize(2 * arr.length);
        arr[size] = item;
        size++;
    }

    public Item dequeue() {
        if (size == 0)
            throw new NoSuchElementException("RandomizedQueue is empty.");
        int i = StdRandom.uniformInt(size);
        Item temp = arr[size - 1];
        arr[size - 1] = arr[i];
        arr[i] = temp;
        Item x = arr[size - 1];
        arr[size - 1] = null;
        size--;
        if (size == arr.length / 4) resize(arr.length / 2);
        return x;
    }

    public Item sample() {
        if (size == 0)
            throw new NoSuchElementException("No elements to sample from.");
        int i = StdRandom.uniformInt(size);
        return arr[i];
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            public boolean hasNext() {
                return !isEmpty();
            }

            public Item next() {
                if (isEmpty()) throw new NoSuchElementException("RandomizeQueue is empty.");
                return dequeue();
            }
        };
    }

    public static void main(String[] args) {
        int n = 2;
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        for (int i = 0; i < n; i++)
            q.enqueue(i);
        StdOut.println("Size is now " + q.size());
        StdOut.println("Sampling: ");
        for (int i = 0; i < 10 * n; i++)
            StdOut.print(q.sample() + " ");
        StdOut.println();
        StdOut.println("Dequeueing: ");
        for (int x : q) StdOut.print(x + " ");
        StdOut.println();
        StdOut.println("Size is now " + q.size() + ". IsEmpty? " + q.isEmpty());
    }


    private void resize(int newSize) {
        Item[] newArr = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++)
            newArr[i] = arr[i];
        arr = newArr;
    }
}
