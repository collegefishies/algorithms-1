# Problem
There is a social network with `n` members, and `m` friendships formed. Each 
friendship has a time stamp, and the input file is sorted from first friendship 
to last.

The goal is to determine at which point are all friends connected, and to do so
in `O(m lg[n])` in time, and `O(n)` in memory.

# Solution

It was hidden in the Princeton Algs 4 source code, that it's actually trivial to
count the number of disjoint sets in the Union-Find datatype.

What one can do, is on a `union` call, if check to see if the members are already
connected or not. If they are not, then we have merged two disjoint sets into one,
so the amount of disjoint sets has decreased by one.

So what we can do is run a simple while loop,

```java
WeightedQuickUnionUF unionFind = new WeightedQuickUnionUF(n);

int disjointSets = n;
int p, q;
String timeStamp;

while ( disjointSets != 1 ) { // O(m)
    p, q, timeStamp = getNextQuery(); // O(1)
    if ( ! unionFind.connected(p, q) ) { // O(lg n)
        unionFind.union(p, q); // O(lg n)
        disjointSets -= 1;
    }
} // Therfore, total O = O(m lg n).

System.out.println(timeStamp);

```
