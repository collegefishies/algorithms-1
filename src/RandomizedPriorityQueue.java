import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

/*
## Question 2: Randomized Priority Queue
Describe how to add the methods `sample()` and `delRandom()` to our binary heap implementation. The two methods return a key that is chosen uniformly at random among the remaining keys, with the latter method also removing that key. The `sample()` method should take constant time; the `delRandom()` method should take logarithmic time. Do not worry about resizing the underlying array.
*/
public class RandomizedPriorityQueue<T> {
    private int index = 1;// (non -inclusive) Array goes from [1, index)
    private T[] heap;

    // api
    RandomizedPriorityQueue(int N) {
        heap = (T[]) new Object[N + 1];
    }

    public void insert(T x) {
        heap[index] = x;
        swim(index);
        index++;
    }

    public T min() {
        return (T) heap[1];
    }

    public T delMin() {
        if (size() == 0) throw new NoSuchElementException("Queue is empty.");
        T x = (T) heap[1];
        swap(heap, 1, index - 1);
        heap[index - 1] = null;
        index--;
        sink(1);
        return x;
    }

    public T sample() {
        if (size() == 0) throw new NoSuchElementException("No elements to sample from");
        int i = StdRandom.uniformInt(1, index);
        return heap[i];
    }

    public T delRandom() {
        if (size() == 0) throw new NoSuchElementException("No elements to sample from and delete.");
        int i = StdRandom.uniformInt(1, index);
        T x = heap[i];
        swap(heap, i, index - 1);
        heap[index - 1] = null;
        index--;
        sink(i);
        return x;
    }

    public int size() {
        return index - 1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // private
    private void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private void swim(int i) {
        while (i >= 2 && less(i, i/2)) {
            swap(heap, i, i / 2);
            i /= 2;
        }
    }

    private void sink(int i) {
        while (i < index) {
            // have no children
            if (2*i >= index && 2*i + 1 >= index) break;
            // have one child
            else if (2*i+1 >= index) {
                if(greater(i, 2*i))
                    swap(heap, i, 2*i);
                break;
            } // have two children
            else if (greater(i, 2*i) || greater(i, 2*i + 1)){
                //need to swap.
                if (less(2*i, 2*i+1)) {
                    swap(heap, i, 2*i);
                    i = 2*i;
                }
                else {
                    swap(heap,i, 2*i+1);
                    i = 2*i + 1;
                }

            } else break;
        }
    }

    private boolean less(int i, int j) {
        return ((Comparable<T>) heap[i]).compareTo(heap[j]) < 0;
    }
    private boolean greater(int i, int j) {
        return ((Comparable<T>) heap[i]).compareTo(heap[j]) > 0;
    }

    // test
    public static void main(String[] args) {
        int N = 30;
        int[] a = new int[N];
        for (int i = 0; i < N; a[i++] = i) ;
//        StdRandom.setSeed(42);
        RandomizedPriorityQueue<Integer> rpq = new RandomizedPriorityQueue<>(2 * N);

        //Check min-priority queue feature.
        StdRandom.shuffle(a);
        for (int x : a) rpq.insert(x);
        //Print out to ensure it acts like a priority queue
        StdOut.println("Printing from Priority Queue");
        while (!rpq.isEmpty()) StdOut.println(rpq.delMin());
        //print samples
        for (int x : a) rpq.insert(x);
        int trials = 1000000;
        double[] counts = new double[N+1];
        for (int i = 0; i < trials; i++) {
            counts[rpq.sample()] += 1;
        }
        StdOut.println("Relative Frequencies");
        for (int i = 1; i <= N; i++) {
            StdOut.println(counts[i]/trials);
        }

        StdOut.println("Sample randomly from Priority Queue");
        N /= 2;
        while ( N-- > 0) StdOut.println(rpq.delRandom());
        StdOut.println("Print minimums from Priority Queue");
        while (!rpq.isEmpty()) StdOut.println(rpq.delMin());


    }
}
