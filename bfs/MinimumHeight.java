package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 *  For a undirected graph with tree characteristics, we can choose any node as the root. The result 
 *  graph is then a rooted tree. Among all possible rooted trees, those with minimum height are 
 *  called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and 
 *  return a list of their root labels.
 */
public class MinimumHeight {
	/*
	 * For a path graph of n nodes, find the minimum height trees is trivial. Just designate the 
	 * middle point(s) as roots.
	Despite its triviality, let design a algorithm to find them.
	Suppose we don't know n, nor do we have random access of the nodes. We have to traversal. It is 
	very easy to get the idea of two pointers. One from each end and move at the same speed. When 
	they meet or they are one step away, (depends on the parity of n), we have the roots we want.
	
	This gives us a lot of useful ideas to crack our real problem.
	
	For a tree we can do some thing similar. We start from every end, by end we mean vertex of degree
	 1 (aka leaves). We let the pointers move the same speed. When two pointers meet, we keep only 
	 one of them, until the last two pointers meet or one step away we then find the roots.
	
	It is easy to see that the last two pointers are from the two ends of the longest path in the 
	graph.
	
	The actual implementation is similar to the BFS topological sort. Remove the leaves, update the 
	degrees of inner vertexes. Then remove the new leaves. Doing so level by level until there are 2 
	or 1 nodes left. What's left is our answer!
	
	The time complexity and space complexity are both O(n).
	Note that for a tree we always have V = n, E = n-1.
	 */
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
	    if (n == 1) return Collections.singletonList(0);

	    List<Set<Integer>> adj = new ArrayList<Set<Integer>>(n);
	    for (int i = 0; i < n; ++i) adj.add(new HashSet<Integer>());
	    for (int[] edge : edges) {
	        adj.get(edge[0]).add(edge[1]);
	        adj.get(edge[1]).add(edge[0]);
	    }

	    List<Integer> leaves = new ArrayList<Integer>();
	    for (int i = 0; i < n; ++i)
	        if (adj.get(i).size() == 1) leaves.add(i);

	    while (n > 2) {
	        n -= leaves.size();
	        List<Integer> newLeaves = new ArrayList<Integer>();
	        for (int i : leaves) {
	            int j = adj.get(i).iterator().next();
	            adj.get(j).remove(i);
	            if (adj.get(j).size() == 1) newLeaves.add(j);
	        }
	        leaves = newLeaves;
	    }
	    return leaves;
	}
	
	/*
	 * Longest Path

	It is easy to see that the root of an MHT has to be the middle point (or two middle points) of the longest path of the tree.
	Though multiple longest paths can appear in an unrooted tree, they must share the same middle point(s).
	
	Computing the longest path of a unrooted tree can be done, in O(n) time, by tree dp, or simply 2 tree traversals (dfs or bfs).
	The following is some thought of the latter.
	
	Randomly select a node x as the root, do a dfs/bfs to find the node y that has the longest distance from x.
	Then y must be one of the endpoints on some longest path.
	Let y the new root, and do another dfs/bfs. Find the node z that has the longest distance from y.
	
	Now, the path from y to z is the longest one, and thus its middle point(s) is the answer. Java Solution
	
	Tree DP

	Alternatively, one can solve this problem directly by tree dp.
	Let dp[i] be the height of the tree when the tree root is i.
	We compute dp[0] ... dp[n - 1] by tree dp in a dfs manner.
	
	Arbitrarily pick a node, say node 0, as the root, and do a dfs.
	When we reach a node u, and let T be the subtree by removing all u's descendant (see the right figure below).
	We maintain a variable acc that keeps track of the length of the longest path in T with one endpoint being u.
	Then dp[u] = max(height[u], acc)
	Note, acc is 0 for the root of the tree.


	 */
	
	/*
	 * For leaf nodes, their degree = 1, which means each of them is only connected to one node.
		In our loop, each time we delete the leaf nodes from our graph(just by putting their degrees 
		to 0), and meanwhile we add the new leaf nodes after deleting them(just add their connected 
		nodes with degree as 2) to the queue.
		So basically in the end, the nodes in the queue would be connected to no other nodes but each 
		other. They should be the answer.
	 */
	
	public List<Integer> findMiniHeight1(int n, int [][] edges) {
		List<List<Integer>> myGraph = new ArrayList<List<Integer>>();
		List<Integer> res = new ArrayList<Integer>();
		if (n==1) {
			res.add(0);
			return res;
		}
	    int[] degree = new int[n];
	    for(int i=0; i<n; i++) {
	    	myGraph.add(new ArrayList<Integer>());
	    }
	    for(int i=0; i<edges.length; i++) {
	    	myGraph.get(edges[i][0]).add(edges[i][1]);
	    	myGraph.get(edges[i][1]).add(edges[i][0]);
	    	degree[edges[i][0]]++;
	    	degree[edges[i][1]]++;
	    }
	    Queue<Integer> myQueue = new ArrayDeque<Integer>();
	    
	    for(int i=0; i<n; i++) {
	    	if (degree[i]==0) 
	    		return res;
	    	else if (degree[i]==1) {
	    		myQueue.offer(i);
	    	}
	    }
	    
	    while (!myQueue.isEmpty()) {
	    	res = new ArrayList<Integer>();
	    	int count = myQueue.size();
	    	
	    	for(int i=0; i<count; i++){
	    		int curr = myQueue.poll();
	    		res.add(curr);
	    		degree[curr]--;
	    		for(int k=0; k<myGraph.get(curr).size(); k++) {
	    			int next = myGraph.get(curr).get(k);
	    			if (degree[next]==0) continue;
	    			if (degree[next]==2) {
	    				myQueue.offer(next);
	    			}
					degree[next]--;
	    		}
	    	}      	
	    }
	    return res;
	}


}
