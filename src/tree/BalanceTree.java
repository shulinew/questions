package tree;

import array.TreeNode;

/*
 * Given a binary tree, determine if it is height-balanced.
	For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
	 the two subtrees of every node never differ by more than 1. 
 */
public class BalanceTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
        	return true;
        return getHeight(root) != -1;
    }
    private int getHeight(TreeNode root){
    	if (root == null)
    		return 0;
    	int leftHeight = getHeight(root.getLeft());
    	if (leftHeight == -1)
    		return -1;
    	int rightHeight = getHeight(root.getRight());
    	if (rightHeight == -1)
    		return -1;
    	if (Math.abs(rightHeight-leftHeight) > 1){
    		return -1;
    	}
    	return Math.max(rightHeight, leftHeight) +1;
    }

}
