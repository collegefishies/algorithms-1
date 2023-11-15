public class SuccessorWithDelete {
    private int N;
    private UnionFindWithSpecificElement uf;

    public SuccessorWithDelete(int n) {
        N = n;
        uf = new UnionFindWithSpecificElement(N + 1);
    }

    public void delete(int x) {
        uf.union(x, x + 1);
    }

    public int successor(int x) {
        int successor = uf.find(x);
        if (successor == N) {
            return -1; // No successor
        } else {
            return successor;
        }
    }
}
