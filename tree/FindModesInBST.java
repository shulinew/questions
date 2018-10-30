package tree;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently 
 * occurred element) in the given BST.
 *  Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than or equal to the node's key.
    The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
    Both the left and right subtrees must also be binary search trees.

For example:
Given BST [1,null,2,2],
https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
 */
public class FindModesInBST {
	/*
	 * I've seen several solutions claimed to be O(1) space, but I disagree. They traverse the tree 
	 * in in-order and keep track of the current set of modes (among other things). But that's not 
	 * O(1) space, not even when disregarding recursion stack space (as explicitly allowed) and 
	 * result space (not mentioned but reasonable). The set's contents aren't on stack space, so it 
	 * can't be disregarded that way. And if the values are for example 1,2,3,4,...,n-2,n-1,n-1
	 *  (unique values followed by one double value), the set grows to (n) and it can't be 
	 *  disregarded because the result only has size 1.
	I think the way to do it properly is to do two passes. One to find the highest number of 
	occurrences of any value, and then a second pass to collect all values occurring that often. 
	Any other ideas?
	Here's a (two-pass) solution that I think can rightfully be called O(1) space. Both passes 
	keep track of the current value etc, and the second pass additionally collects the modes in the result array. 
	I took the value handling out of the in-order traversal into its own function for clarity. Also, this way you could 
	very easily replace my recursive in-order traversal with for example Morris traversal. Then you wouldn't even need to 
	disregard the recursion stack space in order to claim O(1) extra space usage.
	 */
    public int[] findMode(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        inorder(root);
        return modes;
    }

    private int currVal = 0;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;
    
    private int[] modes;

    private void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (modes != null)
                modes[modeCount] = currVal;
            modeCount++;
        }
    }
    private void inorder(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                handleValue(node.val);
                node = node.right;
            } else {
                TreeNode prev = node.left;
                while (prev.right != null && prev.right != node)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = node;
                    node = node.left;
                } else {
                    prev.right = null;
                    handleValue(node.val);
                    node = node.right;
                }
            }
        }
    }
    
    public int[] findModeI(TreeNode root) {
        if (root == null) return new int[0];
        
        List<Integer> list = new ArrayList<Integer>();
        traverse(root, list);
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) res[i] = list.get(i);
        return res;
    }
    ////
    int count = 1;
    int max = 0;
    Integer pre = null;
    private void traverse(TreeNode root, List<Integer> list) {
    	if (root == null) return;
    	traverse(root.left, list);
    	if (pre != null) {
    		if (root.val == pre) {
    			count++;
    		} else {
    			count = 1;
    		}
    	}
    	if (count > max) {
    		max = count;
    		list.clear();
    		list.add(root.val);
    	} else if (count == max) {
    		list.add(root.val);
    	}
    	pre = root.val;
    	traverse(root.right, list);
    }
    


}
