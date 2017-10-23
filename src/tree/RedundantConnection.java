package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 *  We are given a "tree" in the form of a 2D-array, with distinct values for each node.

In the given 2D-array, each element pair [u, v] represents that v is a child of u in the tree.

We can remove exactly one redundant pair in this "tree" to make the result a (rooted) tree.

You need to find and output such a pair. If there are multiple answers for this question, output the one appearing last in the 2D-array. There is always at least one answer. 
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: Original tree will be like this:
  1
 / \
2 - 3

Input: [[1,2], [1,3], [3,1]]
Output: [3,1]
Explanation: Original tree will be like this:
  1
 / \\
2   3
 */
public class RedundantConnection {
    Set<Integer> seen = new HashSet<Integer>();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnectionDfs(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge: edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                    dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        throw new AssertionError();
    }
    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true;
            for (int nei: graph[source]) {
                if (dfs(graph, nei, target)) return true;
            }
        }
        return false;
    }

    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(MAX_EDGE_VAL + 1);
        for (int[] edge: edges) {
            if (!dsu.union(edge[0], edge[1])) return edge;
        }
        throw new AssertionError();
    }

	class DSU {
	    int[] parent;
	    int[] rank;

	    public DSU(int size) {
	        parent = new int[size];
	        for (int i = 0; i < size; i++) parent[i] = i;
	        rank = new int[size];
	    }

	    public int find(int x) {
	        if (parent[x] != x) parent[x] = find(parent[x]);
	        return parent[x];
	    }

	    public boolean union(int x, int y) {
	        int xr = find(x), yr = find(y);
	        if (xr == yr) {
	            return false;
	        } else if (rank[xr] < rank[yr]) {
	            parent[xr] = yr;
	        } else if (rank[xr] > rank[yr]) {
	            parent[yr] = xr;
	        } else {
	            parent[yr] = xr;
	            rank[xr]++;
	        }
	        return true;
	    }
	    
	    public int[] findRedundantConnection1(int[][] edges) {
	        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    
	        int[] res = null;
	        for(int i=0; i<edges.length; i++){
	            int[] p = edges[i];
	            int a = searchRoot(p[0], map);
	            int b = searchRoot(p[1], map);
	            
	            if(a == b){
	                res = p;
	                continue;
	            }else{
	                map.put(a,b);
	            }
	        }
	        return res;
	    }
	    
	    private int searchRoot(int v, HashMap<Integer, Integer> map){
	        while(map.containsKey(v)){
	            v = map.get(v);
	        }
	        return v;
	    }  
	    public int[] findRedundantConnection(int[][] edges) {
	        int[] parent = new int[2001];
	        for (int i = 0; i < parent.length; i++) parent[i] = i;
	        
	        for (int[] edge: edges){
	            int f = edge[0], t = edge[1];
	            if (find(parent, f) == find(parent, t)) return edge;
	            else parent[find(parent, f)] = find(parent, t);
	        }
	        
	        return new int[2];
	    }
	    
	    private int find(int[] parent, int f) {
	        if (f != parent[f]) {
	          parent[f] = find(parent, parent[f]);  
	        }
	        return parent[f];
	    }
	}
	
	//Directed graph
	/*
	 * There are two cases for the tree structure to be invalid.
1) A node having two parents;
   including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
2) A circle exists
1) Check whether there is a node having two parents. 
    If so, store them as candidates A and B, and set the second edge invalid. 
2) Perform normal union find. 
    If the tree is now valid 
           simply return candidate B
    else if candidates not existing 
           we find a circle, return current edge; 
    else 
           remove candidate A instead of B.
	 */
	
	/*
	 * This problem is limited to a graph with N nodes and N edges. No node is singled out if a edge is removed. For example, [[1,2],[2,4],[3,4]], 4 nodes 3 edges, is not applicable to this problem. You cannot remove [3,4] to single out node 3.

There are 3 cases:

    No loop, but there is one node who has 2 parents.
    A loop, and there is one node who has 2 parents, that node must be inside the loop.
    A loop, and every node has only 1 parent.

Case 1: e.g. [[1,2],[1,3],[2,3]] ,node 3 has 2 parents ([1,3] and [2,3]). Return the edge that occurs last that is, return [2,3].
Case 2: e.g. [[1,2],[2,3],[3,1],[4,1]] , {1->2->3->1} is a loop, node 1 has 2 parents ([4,1] and [3,1]). Return the edge that is inside the loop, that is, return [3,1].
Case 3: e.g. [[1,2],[2,3],[3,1],[1,4]] , {1->2->3->1} is a loop, you can remove any edge in a loop, the graph is still valid. Thus, return the one that occurs last, that is, return [3,1].
	 [[4,2],[1,5],[5,2],[5,3],[2,4]]
and
[[2,1],[3,1],[4,2],[1,4]]
	 */
   public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                can2 = new int[] {edges[i][0], edges[i][1]};
                can1 = new int[] {parent[edges[i][1]], edges[i][1]};
                edges[i][1] = 0;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) {
                continue;
            }
            int child = edges[i][1], father = edges[i][0];
            if (root(parent, father) == child) { //Not valid tree
                if (can1[0] == -1) {
                    return edges[i];
                }
                return can1;
            }
            parent[child] = father;
        }
        return can2;
    }
    
    private int root(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }   
        return i;
    }
	public int[] findRedundantDirectedConnection2(int[][] edges) {
	    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	    
	    int[] res = new int[2];
	    
	    for(int[] edge : edges) {
	        if(!map.containsKey(edge[0])) {
	            map.put(edge[0], edge[0]);
	        }
	        if(!map.containsKey(edge[1])) {
	            map.put(edge[1], edge[1]);
	        }
	    }
	    
	    for(int[] edge : edges) {
	        int x = find(edge[0], map);
	        int y = find(edge[1], map);
	        
	        if(x != y && y == edge[1]) {
	            map.put(y, x);
	        } else {
	            res[0] = edge[0];
	            res[1] = edge[1];
	        }
	    }
	    
	    return res;
	}

	private int find(int id, Map<Integer, Integer> map) {
	    while(map.get(id) != id) {
	        id = map.get(id);
	    }
	    return id;
	}
	
	 public int[] findRedundantDirectedConnection1(int[][] edges) {
	        int n = edges.length;
	        int[] parent = new int[n+1], ds = new int[n+1];
	        Arrays.fill(parent, -1);
	        int first = -1, second = -1, last = -1;
	        for(int i = 0; i < n; i++) {
	            int p = edges[i][0], c = edges[i][1];
	            if (parent[c] != -1) {
	                first = parent[c];
	                second = i;
	                continue;
	            }
	            parent[c] = i;
	            
	            int p1 = find(ds, p);
	            if (p1 == c) last = i;
	            else ds[c] = p1;
	        }

	        if (last == -1) return edges[second]; // no cycle found by removing second
	        if (second == -1) return edges[last]; // no edge removed
	        return edges[first];
	    }
	    
	    private int find(int[] ds, int i) {
	        return ds[i] == 0 ? i : (ds[i] = find(ds, ds[i]));
	    }

	//[[4,2],[1,5],[5,2],[5,3],[2,4]]
	public static void main(String[] args) {
		RedundantConnection rc = new RedundantConnection();
		int[][] edges = {{4,2},{1,5},{5,2},{5,3},{2,4}};
//		int[][] edges = {{1,2},{2,3},{3,1},{1,4}};
		rc.findRedundantDirectedConnection1(edges);
		
		
	}
	
	
}
