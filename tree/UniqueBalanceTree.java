package tree;

import java.util.ArrayList;
import java.util.List;

/*
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * For example,
	Given n = 3, there are a total of 5 unique BST's. 
	1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
Given a sequence 1…n, to construct a Binary Search Tree (BST) out of the sequence, we could enumerate each number
 i in the sequence, and use the number as the root, naturally, the subsequence 1…(i-1) on its left side would 
 lay on the left branch of the root, and similarly the right subsequence (i+1)…n lay on the right branch of the 
 root. We then can construct the subtree from the subsequence recursively. Through the above approach,
  we could ensure that the BST that we construct are all unique, since they have unique roots.

The problem is to calculate the number of unique BST. To do so, we need to define two functions:

G(n): the number of unique BST for a sequence of length n.

F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST, and the sequence ranges 
from 1 to n.

As one can see, G(n) is the actual function we need to calculate in order to solve the problem. And G(n) can be
 derived from F(i, n), which at the end, would recursively refer to G(n).

First of all, given the above definitions, we can see that the total number of unique BST G(n), is the sum of BST F(i) using each number i as a root.
i.e.

G(n) = F(1, n) + F(2, n) + ... + F(n, n). 

Particularly, the bottom cases, there is only one combination to construct a BST out of a sequence of length 1 (only a root) or 0 (empty tree).
i.e.

G(0)=1, G(1)=1. 

Given a sequence 1…n, we pick a number i out of the sequence as the root, then the number of unique BST with 
the specified root F(i), is the cartesian product of the number of BST for its left and right subtrees. For 
example, F(3, 7): the number of unique BST tree with number 3 as its root. To construct an unique BST out of the 
entire sequence [1, 2, 3, 4, 5, 6, 7] with 3 as the root, which is to say, we need to construct an unique BST 
out of its left subsequence [1, 2] and another BST out of the right subsequence [4, 5, 6, 7], and then combine 
them together 
(i.e. cartesian product). The tricky part is that we could consider the number of unique BST out of sequence 
[1,2] as G(2), and the number of of 
unique BST out of sequence [4, 5, 6, 7] as G(4). Therefore, F(3,7) = G(2) * G(4).

i.e.

F(i, n) = G(i-1) * G(n-i)	1 <= i <= n 

Combining the above two formulas, we obtain the recursive formula for G(n). i.e.

G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0) 

In terms of calculation, we need to start with the lower number, since the value of G(n) depends on the values of G(0) … G(n-1).
 */
public class UniqueBalanceTree {
	public int numOfTree(int n){
		int [] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i<= n; i++) {
			for (int j = 1; j <= i; j++){
				dp[i] += dp[j-1] * dp[i-j];
			}
		}
		return dp[n];
	}
	
	/**
	 * Taking 1~n as root respectively:
	 *      1 as root: # of trees = F(0) * F(n-1)  // F(0) == 1
	 *      2 as root: # of trees = F(1) * F(n-2) 
	 *      3 as root: # of trees = F(2) * F(n-3)
	 *      ...
	 *      n-1 as root: # of trees = F(n-2) * F(1)
	 *      n as root:   # of trees = F(n-1) * F(0)
	 *
	 * So, the formulation is:
	 *      F(n) = F(0) * F(n-1) + F(1) * F(n-2) + F(2) * F(n-3) + ... + F(n-2) * F(1) + F(n-1) * F(0)
	 */
	/*
	 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
	 */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0){
            return new ArrayList<TreeNode>();
        }
    	return generateHelper(1, n);    
    }
    private List<TreeNode> generateHelper(int start, int end){
    	List<TreeNode> list = new ArrayList<TreeNode>();
    	if (start > end) {
    		list.add(null);
    		return list;
    	}
    	if (start == end) {
    		list.add(new TreeNode(start));
    		return list;
    	}
    	List<TreeNode> left, right;
    	for (int i = start; i <= end; i++){
    		left = generateHelper(start, i-1);
    		right = generateHelper(i+1, end);
    		for (TreeNode leftNode: left){
    			for (TreeNode rightNode: right){
    				TreeNode root = new TreeNode(i);
    				root.left = leftNode;
    				root.right = rightNode;
    				list.add(root);
    			}
    		}
    	}
    	return list;
    }
    
    public List<TreeNode> generateTreeIterative(int n){
    	List<TreeNode>[] list = new ArrayList[n+1];
    	list[0] = new ArrayList<TreeNode>();
    	if (n == 0){
    		return list[0];
    	}
    	list[0].add(null);
    	for (int i = 1; i <= n;i++){
    		list[i] = new ArrayList<TreeNode>();
    		for (int j = 0; j < i; j++){
    			for (TreeNode leftNode: list[j]){
    				for (TreeNode rightNode: list[i-j-1]){
    					TreeNode node = new TreeNode(j+1);
    					node.left = leftNode;
    					node.right = clone(rightNode, j+1);
    					list[i].add(node);
    				}
    			}
    		}
    	}
    	return list[n];
    }
    private TreeNode clone(TreeNode node, int offset){
    	if (node == null) return null;
    	TreeNode root = new TreeNode(node.val + offset);
    	root.left = clone(node.left, offset);
    	root.right = clone(node.right, offset);
    	return root;
    }
    
    /*
     * public class Solution {
	TreeNode deepCopy(TreeNode root){
		if(root == null) return null;
		TreeNode tmp = new TreeNode(1);
		tmp.left = deepCopy(root.left);
		tmp.right = deepCopy(root.right);
		return tmp;
	}
	int cur = 0;
	void setValue(TreeNode root){
		if(root.left != null){
			setValue(root.left);
		}
		root.val = cur++;
		if(root.right != null){
			setValue(root.right);
		}
		
	}
    public List<TreeNode> generateTrees(int n) {
    	if(n <= 0){
    		List<TreeNode> res =new ArrayList<TreeNode>();
    		res.add(null);
    		return res;
    	}
    	
        List<TreeNode>[] dp = new ArrayList [n+1];
        for(int i = 0; i < n+1; ++i){
        	dp[i] =  new ArrayList<TreeNode>();
        }
        
        dp[0].add(null);
        
        for(int i = 1; i <= n; ++i){
        	for(int j = 0; j < i; ++j){
        		for(int k = 0; k < dp[j].size(); ++k){
        			for(int l = 0; l < dp[i-1-j].size(); ++l){
        				TreeNode tmp = new TreeNode(1);
        				tmp.left = deepCopy(dp[j].get(k));
        				tmp.right = deepCopy(dp[i-1-j].get(l));
        				dp[i].add(tmp);
        			}
        		}
        	}
        }
        
        for(int i = 0; i < dp[n].size(); ++i){
        	cur = 1;
        	setValue(dp[n].get(i));
        }
        return dp[n];
    }
}
     */
}
