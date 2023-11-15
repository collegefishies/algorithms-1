# 3-SUM in Quadratic Time

Design an algorithm for the 3-SUM problem that takes time proportional to `n^2`
in the worst case. You may assume that you can sort the `n` integers in time
proportional to `n^2` or better.

# Approach

We start with the brute force approach, which leads to `n^3`, our answer
would then just be `sum, sum, sum delta(a_i + a_j + a_k)`. The three sums then
cause it to be `O(n^3)`.

We then add in the assumption that the integers are sorted. This means we can 
utilize binary search, that is find any particular value for (almost) free 
`O(lg n)`.  So what we can do is do 2-sum's and find the third value.

```java
int[] a;
int count = 0;

int leftFind(int [] a, int val) {
    // returns the left most val that occurs. 
}
for (int i = 0; i < a.length; i++) {
    for (int j = i + 1; j < a.length; j++) {
        int twoSum = a[i] + a[j];
        int k = leftFind(a, twoSum);
        
        boolean isFound = k != -1 && k > j && k>i; // don't double count.
        while (isFound) {
            count += 1;
            // find all elements of the same value to increment the count
            k += 1; isFound = a[k] == twoSum;
        }
    }
}
```

Of course, the above algorithm isn't really quadratic it's more like `O(n^2 lg n)`
ignoring the inner most while loop (which could bring it to `O(n^3)`. We can 
remove the inner loop with a right most, binary search find algorithm. With that
inner loop gone, now it's definitely `O(n^2 lg n)`.



```java
int[] a;
int count = 0;

int leftFind(int [] a, int val) {
    // returns the left most val that occurs. 
}
int rightFind(int [] a, int val) {
    // returns the right most val that occurs. 
}

for (int i = 0; i < a.length; i++) {
    for (int j = i + 1; j < a.length; j++) {
        int twoSum = a[i] + a[j];
        int k = leftFind(a, twoSum);
        
        boolean isFound = k != -1 && k > j && k>i; // don't double count.
        if (isFound) {
            count += rightFind(a, twoSum) - leftFind(a, twoSum) + 1;
        }
    }
}
```
