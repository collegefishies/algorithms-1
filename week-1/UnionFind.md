# Dynamic Connectivity Problem

This week's algorithm is concerned with finding a solution
to the problem of being able to define a network of N connected
items by specifying only nearest neighbor connections, i.e.,
(6, 3), (1, 2), (3, 4) are connected pairs. After that definition,
then comes the question "what is connected(6, 4)?", that is, are 6 and 4
connected? Is there some way to go through the connections we know now
to communicate between all devices.

This is the dynamic connectivity problem.

# Union-Find Datatype

The union-find datatype is an abstract datatype for holding this
information. Namely, retaining all the connected components of this
set in some manner, as well as specifying a function that returns the 
answer to `connected(p, q)`.

The above specifies the API, that is the interface of this abstract 
datatype, the below is concerned with specific implementations of 
a few algorithms.

# Implementations
## Quick Find

