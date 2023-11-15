
# Successor with Delete

Given a set of `n` integers `S = {0, 1, ..., n−1}` and a sequence of requests of
the following form:

- Remove `x` from `S`
- Find the successor of `x`: the smallest `y` in `S` such that `y ≥ x`.

Design a data type so that all operations (except construction) take logarithmic
time or better in the worst case.

# Interpretation

This isn't quite fully well defined. We will assume that these two bullets are
two API functions that can be called at any time. Since this is related to the 
union-find section we can assume we could use some sort of connected graph to account
for deletions.

Eventually, it occured to me that you can account for deletions via connections.
For example, say that we deleted 5. We account for this via connecting 5 to 6.
We could find the successor by following this path, that is we would make 6 the 
parent of 5, of course this would lead to `O(n)` time for successor calls rather
than logarithmic.

Fortunately, our last problem `2-UnionFindWithSpecificElement` solves exactly that
by adding a function that returns the largest element in the tree. Which is exactly
what the successor function is. We summarize this with some pseudo-code.

```java

public class SuccessorWithDelete {
    private UnionFindWithSpecificElement uf;
    
    public SuccessorWithDelete(int n){
        uf = new UnionFindWithSpecificElement(n);
    }
    
    public void delete(int x) {
        uf.union(x, x + 1);
    }
    
    public int successor(int x) {
        return uf.find(x);
    }
}
```

The only problem with this code, is that it fails when there is no successor. Say
for example, if you `n=6` and we delete `5`. `successor(5)` is undefined. Here, 
this would return the largest element in the set prior to deletion, i.e., `5`.
We'll just account for that case by making `n -> n + 1`, and if successor would 
return `n` (one larger than any element in the set), successor will instead
return `-1`.

```java

public class SuccessorWithDelete {
    private int N;
    private UnionFindWithSpecificElement uf;
    
    public SuccessorWithDelete(int n){
        N = n;
        uf = new UnionFindWithSpecificElement(N + 1);
    }
    
    public void delete(int x) {
        uf.union(x, x + 1);
    }
    
    public int successor(int x) {
        int s = uf.find(x);
        if (s == N) {
            return -1;
        } else {
            return s;
        }
    }
}
```
