package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/*
 *  Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
For example:
Given the below binary tree and sum = 22
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
    	if (root != null){
    		if (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val)){
    			return true;
    		}
    	}
    	if (root != null && sum == root.val  && root.left == null && root.right == null){
    		return true;
    	}
    	return false;
    }
    public boolean hasPathSum1(TreeNode root, int sum){
    	if (root == null){
    		return false;
    	}
    	if (sum == root.val  && root.left == null && root.right == null){
    		return true;
    	}
    	return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val) ;
    	
    	/*
    	 *         stack<TreeNode *> s;
        TreeNode *pre = NULL, *cur = root;
        int SUM = 0;
        while (cur || !s.empty()) {
            while (cur) {
                s.push(cur);
                SUM += cur->val;
                cur = cur->left;
            }
            cur = s.top();
            if (cur->left == NULL && cur->right == NULL && SUM == sum) {
                return true;
            }
            if (cur->right && pre != cur->right) {
                cur = cur->right;
            } else {
                pre = cur;
                s.pop();
                SUM -= cur->val;
                cur = NULL;
            }
        }
        return false;
    	 */
    }
    /*
     *        5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
        [
		   [5,4,11,2],
		   [5,8,4,5]
		]
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> result  = new ArrayList<List<Integer>>();
    	List<Integer> currentResult  = new ArrayList<Integer>();
    	pathSum(root,sum,currentResult,result);
    	return result;
    }

    private void pathSum(TreeNode root, int sum, List<Integer> currentResult, List<List<Integer>> result) {
    	if (root == null)
    		return;
    	currentResult.add(new Integer(root.val));
    	if (root.left == null && root.right == null && sum == root.val) {
    		result.add(new ArrayList<Integer>(currentResult));
//    		currentResult.remove(currentResult.size() - 1);//don't forget to remove the last integer
//    		return;
    	} else {
    		pathSum(root.left, sum - root.val, currentResult, result);
    		pathSum(root.right, sum - root.val, currentResult, result);
    	}
    	currentResult.remove(currentResult.size() - 1);
    }
    
    public void pathSumInner(TreeNode root, int sum, Stack<Integer>path, List<List<Integer>> resultList) {
        path.push(root.val);
        if(root.left == null && root.right == null && sum == root.val)
            resultList.add(new ArrayList<Integer>(path));
        if(root.left!=null) pathSumInner(root.left, sum-root.val, path, resultList);
        if(root.right!=null)pathSumInner(root.right, sum-root.val, path, resultList);
        path.pop();
    }
    
    public List<List<Integer>> pathSum3(TreeNode root, int sum) {
    	List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        if(root==null) return resultList;
        Stack<Integer> path = new Stack<Integer>();
        pathSumInner(root, sum, path, resultList);
        return resultList;
    }
    
    /*
     * You are given a binary tree in which each node contains an integer value.
		Find the number of paths that sum to a given value.
		The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
		The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000. 
		root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
		
		      10
		     /  \
		    5   -3
		   / \    \
		  3   2   11
		 / \   \
		3  -2   1
		
		Return 3. The paths that sum to 8 are:
		
		1.  5 -> 3
		2.  5 -> 2 -> 1
		3. -3 -> 11
     */
    /*
     * So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how many ways get to 
     * this prefix sum) , and whenever reach a node, we check if prefix sum - target exists in hashmap or not, if it 
     * does, we added up the ways of prefix sum - target into res.
	For instance : in one path we have 1,2,-1,-1,2, then the prefix sum will be: 1, 3, 2, 1, 3, let's say we want to 
	find target sum is 2, then we will have{2}, {1,2,-1}, {2,-1,-1,2} and {2}ways.

	I used global variable count, but obviously we can avoid global variable by passing the count from bottom up. The 
	time complexity is O(n). 
     */

    public int pathSumIII(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<Integer, Integer>();
        preSum.put(0,1);
        helperIII(root, 0, sum, preSum);
        return count;
    }
    int count = 0;
    public void helperIII(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return;
        }
        
        currSum += root.val;

        if (preSum.containsKey(currSum - target)) {
            count += preSum.get(currSum - target);
        }
        
        if (!preSum.containsKey(currSum)) {
            preSum.put(currSum, 1);
        } else {
            preSum.put(currSum, preSum.get(currSum)+1);
        }
        
        helperIII(root.left, currSum, target, preSum);
        helperIII(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
    }

    public int pathSumIIIRecursive(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSumIIIRecursive(root.left, sum) + pathSumIIIRecursive(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) 
            + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }
    
    public static void main(String [] args) {
    	PathSum pathSum = new PathSum();
    	TreeNode root = new TreeNode(5);
    	TreeNode node1 = new TreeNode(4);
    	TreeNode node2 = new TreeNode(8);
    	root.left = node1;
    	root.right = node2;
    	TreeNode node3 = new TreeNode(11);
    	TreeNode node4 = new TreeNode(7);
    	TreeNode node5 = new TreeNode(2);
    	node1.left = node3;
    	node3.left = node4;
    	node3.right =node5;
    	TreeNode node6 = new TreeNode(13);
    	TreeNode node7 = new TreeNode(4);
    	node2.left = node6;
    	node2.right = node7;
    	TreeNode node8 = new TreeNode(5);
    	TreeNode node9 = new TreeNode(1);
    	node7.left = node8;
    	node7.right = node9;
    	List<List<Integer>> results = pathSum.pathSum(root, 22);
    	for (List<Integer> result: results) {
    		for (Integer val: result) {
    			System.out.print(val + ",");
    		}
    		System.out.print("\n");
    	}

    }
    //https://leetcode.com/problems/path-sum-iii/description/
    //https://leetcode.com/tag/tree/
}
