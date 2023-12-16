import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

/*
## Question 2: Randomized Priority Queue
Describe how to add the methods `sample()` and `delRandom()` to our binary heap implementation. The two methods return a key that is chosen uniformly at random among the remaining keys, with the latter method also removing that key. The `sample()` method should take constant time; the `delRandom()` method should take logarithmic time. Do not worry about resizing the underlying array.
*/
public class RandomizedPriorityQueue<T> {
    private int N = 1;
    private T[] heap;

    // api
    RandomizedPriorityQueue(int N) {
        heap = (T[]) new Object[N + 1];
    }

    public void insert(T x) {
        heap[N] = x;
        swim(N);
        N++;
    }

    public T min() {
        return (T) heap[1];
    }

    public T delMin() {
        if (N == 1) throw new NoSuchElementException("Queue is empty.");
        T x = (T) heap[1];
        swap(heap, 1, N - 1);
        heap[N - 1] = null;
        N--;
        sink(1);
        return x;
    }

    public T sample() {
        int i = StdRandom.uniformInt(1, N);
        return heap[i];
    }

    public T delRandom() {
        int i = StdRandom.uniformInt(1, N);
        T x = heap[i];
        swap(heap, i, N);
        heap[N--] = null;
        return x;
    }

    public int size() {
        return N - 1;
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
        while (i < N) {
            // have no children
            if (2*i >= N && 2*i + 1 >= N) break;
            // have one child
            else if (2*i+1 >= N) {
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
        int N = 20;
        int[] a = new int[N];
        for (int i = 0; i < N; a[i++] = i) ;
        StdRandom.setSeed(42);
        RandomizedPriorityQueue<Integer> rpq = new RandomizedPriorityQueue<>(2 * N);

        //Check min-priority queue feature.
        StdRandom.shuffle(a);
        for (int x : a) rpq.insert(x);
        while (!rpq.isEmpty()) StdOut.println(rpq.delMin());

    }
}
