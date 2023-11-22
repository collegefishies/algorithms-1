# Dynamic Connectivity Problem

This week's algorithm is concerned with finding a solution
to the problem of being able to define a network of N connected
items by specifying only nearest neighbor connections, i.e.,
(6, 3), (1, 2), (3, 4) are connected pairs. After that definition,
then comes the question "What is `connected(6, 4)`?", that is, are 6 and 4
connected? Is there some way to go through the connections we know now
to communicate between all devices.

This is the dynamic connectivity problem.

It has applications in the internet, where all there are are pair wise connections,
you know your laptop is connected to your router, and your router knows its connected
to your ISP, and it's useful to know if it's connected to google's computer for 
performing search.

# Union-Find Datatype

The union-find datatype is an abstract datatype for holding this
information. Namely, retaining all the connected components of this
set in some manner, as well as specifying a function that returns the 
answer to `connected(p, q)`.

The `connected` function forms the "find" part of the algorithm. It finds whether
a connection exists between two components. The `union` part is a function that 
records the connected information, namely by, in some form, taking the union of 
the subsets that contain `p` and `q` respectively.

The above specifies the API, that is the interface of this abstract 
datatype, the below is concerned with specific implementations of 
a few algorithms.

# Implementations
## Quick Find

The quick find algorithm makes it easy to answer are `p` and `q` connected.
The connected information is held in an array `id[]` that stores the identifier 
of the connected component that houses a specified component. So if `id[p] == id[q]` 
then we know that `p` and `q` are indeed connected. This means that the bulk of the 
work goes into specifying `union`.

## Quick Union

The quick union algorithm takes a lazy approach to the unioning. Rather than doing 
lots of work, on being able to perform the union immediately, it records the information
of whether or not two items are connected by recording a tree of connectivity.

We still have an array `id[]`, but rather than it holding the id of the set that
it belongs to, it holds the parent in the connectivity tree. With this tree approach,
`connected` is defined to be `root(p) == root(q)`. It goes up the tree until it finds 
the root (which is it's own parent in this implementation). So this way it doesn't 
require walking through the entire id array updating the id's of all sets everytime
a new connection is formed.


# Assignment
# Percolation Specification FAQ

## Project Overview
Write a program to estimate the percolation threshold via Monte Carlo simulation.

### Installation
- **Java Programming Environment (Optional):**
  - Install the custom IntelliJ programming environment:
    - Step-by-step instructions for:
      - [Mac OS X](#)
      - [Windows](#)
      - [Linux](#)
  - After installation, `javac-algs4` and `java-algs4` commands will classpath in `algs4.jar`.
  - Required import statements for accessing classes in `algs4.jar`:
    ```java
    import edu.princeton.cs.algs4.StdRandom;
    import edu.princeton.cs.algs4.StdStats;
    import edu.princeton.cs.algs4.WeightedQuickUnionUF;
    ```

### Percolation Problem
- **Modeling:**
  - Use an n-by-n grid of sites, either open or blocked.
  - A system percolates if there is a full site in the bottom row connected to the top row.

### Task
- Write a program to estimate the percolation threshold `p*`.

### Percolation Data Type
- **API for `Percolation` class:**
  ```java
  public class Percolation {
      public Percolation(int n) // Constructor
      public void open(int row, int col)
      public boolean isOpen(int row, int col)
      public boolean isFull(int row, int col)
      public int numberOfOpenSites()
      public boolean percolates()
      public static void main(String[] args) // Optional
  }
  ```
- **Corner Cases:**
    - Throw `IllegalArgumentException` for invalid arguments.
- **Performance Requirements:**
    - Constructor: Time proportional to n².
    - Methods: Constant time plus constant number of `union()` and `find()` calls.

### Monte Carlo Simulation
- **Process:**
    1. Initialize all sites as blocked.
    2. Randomly open sites until the system percolates.
    3. The fraction of open sites at percolation estimates the threshold.

### PercolationStats Data Type
- **API for `PercolationStats` class:**
  ```java
  public class PercolationStats {
      public PercolationStats(int n, int trials)
      public double mean()
      public double stddev()
      public double confidenceLo()
      public double confidenceHi()
      public static void main(String[] args)
  }
  ```
    - **Exceptions:**
        - Throw `IllegalArgumentException` if `n ≤ 0` or `trials ≤ 0`.

### Examples
- Command-line execution examples:
  ```
  ~/Desktop/percolation> java-algs4 PercolationStats 200 100
  mean                    = 0.5929934999999997
  stddev                  = 0.00876990421552567
  95% confidence interval = [0.5912745987737567, 0.5947124012262428]
  ```

### Optional Analysis
- **Running Time and Memory Usage:**
    - Implement using QuickFindUF and WeightedQuickUnionUF.
    - Measure running time for various values of n and T.
    - Estimate memory usage using a 64-bit memory-cost model.

### Submission
- Submit a .zip file containing `Percolation.java` and `PercolationStats.java`.

## Additional Information
- Developed by Bob Sedgewick and Kevin Wayne.
- Copyright © 2008.
