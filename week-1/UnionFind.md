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


