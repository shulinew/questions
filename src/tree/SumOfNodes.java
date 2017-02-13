package tree;

import java.util.Stack;

/*
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a 
 * number.
	An example is the root-to-leaf path 1->2->3 which represents the number 123.
	Find the total sum of all root-to-leaf numbers.
	    1
	   / \
	  2   3
	  Return the sum = 12 + 13 = 25. 
 */
public class SumOfNodes {
    public int sumNumbers(TreeNode root) {
    	return sumNumberHelper(root, 0);       
    }
    private int sumNumberHelper(TreeNode root, int sum) {
    	if (root == null) return 0;
    	if (root.left == null && root.right == null) {
    		return sum*10 + root.val;
    	}
    	return sumNumberHelper(root.left, sum*10 + root.val) + sumNumberHelper(root.right, sum*10+root.val);
    }
    
    public int subNumberIterative(TreeNode root) {
    	if (root == null) return 0;
    	int sum = 0;
    	TreeNode current;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
    	while (!stack.isEmpty()) {
    		current = stack.pop();
    		if (current.right != null) {
    			current.right.val = current.val*10 + current.right.val;
    			stack.push(current.right);
    		}
    		if (current.left != null) {
    			current.left.val = current.val*10 + current.left.val;
    			stack.push(current.left);
    		}
    		if (current.left == null && current.right == null) {
    			sum += current.val;
    		}
    	}
    	return sum; 		
    }
}
