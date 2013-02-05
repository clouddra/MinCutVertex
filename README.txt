Approach to the problem 

Find a cut vertex(articulation point).

2 situations in which a vertex is a cut vertex.

1) it is part of a cut edge (lowpoint > dfsnumber)
2) start of a cycle and not root (lowpoint == dfsnumber && !root)

Algorithm:
COlor each vertex as you visit them and update lowpoint and dfsnumber. 
If the vertex is fully explored(all neighbours traversed) return the lowpoint
The vertex is a cut vertex if it satisfy the above 2 conditions.
 

Complexity is O(V+E). All edges and vertices is traversed once.