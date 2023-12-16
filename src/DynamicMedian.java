import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

class DynamicMedian<T extends Comparable<T>> {
    private MinPQ<T> big = new MinPQ<>();
    private MaxPQ<T> small = new MaxPQ<>();

    // API
    public void insert(T x) {
        // insert first element.
        if (small.size() == 0 && big.size() == 0)
            small.insert(x);

        // insert according to boundaries if they exist
        if (big.size() != 0 && x.compareTo(big.min()) < 0)
            small.insert(x);
        else
            big.insert(x);

        // ensure the two heaps are within size diff == 1.
        if (Math.abs(big.size() - small.size()) > 1) {
            if (big.size() > small.size()) {
                small.insert(big.min());
                big.delMin();
            } else {
                big.insert(small.max());
                small.delMax();
            }
        }
    }

    public T median() {
        if (big.size() > small.size())
            return big.min();
        else
            return small.max();
    }

    public void removeMedian() {
        if (big.size() > small.size())
            big.delMin();
        else
            small.delMax();
    }

    // private methods

    // test
    public static void main(String[] args) {
        int[] a = {1,5,6,1,89,9,1,3,5,7,9};
        DynamicMedian<Integer> med = new DynamicMedian<>();
        for (Integer x : a) {
            med.insert(x);
            System.out.println("Median is " + med.median());
        }
    }
}
