package tree;

import java.util.Stack;

/*
 * implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node 
 * of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of 
 * the tree. 
 */
public class BinaryTreeIterator {
	private Stack<TreeNode> stack = new Stack<TreeNode>();
    public BinaryTreeIterator(TreeNode root) {
    	pushAll(root);
        
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
    	return !stack.isEmpty();
        
    }

    /** @return the next smallest number */
    public int next() {
       TreeNode tempTree = stack.pop();
       pushAll(tempTree.right);
       return tempTree.val;
    }
    private void pushAll(TreeNode node){
    	while (node != null){
    		stack.push(node);
    		node = node.left;
    	}
    }

}

