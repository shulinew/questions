package tree;

/*
 *  Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree. 
Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
 */
public class MergeTwoBinaryTree {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    	TreeNode node = new TreeNode(0);
    	if (t1 != null && t2 != null) {
    		node.val = t1.val + t2.val;
        	node.left = mergeTrees(t1.left, t2.left);
        	node.right = mergeTrees(t1.right, t2.right);
    	} else if (t1 == null) {
    		node.val = t2.val;
        	node.left = t2.left;
        	node.right = t2.right;
    	} else if (t2 == null) {
    		node.val = t1.val;
        	node.left = t1.left;
        	node.right = t1.right;
    	} else {
    		return null;
    	}
    	
    	return node;
    }
    public TreeNode merge(TreeNode t1, TreeNode t2) {
    	int val = (t1 == null? 0:t1.val) + (t2==null? 0:t2.val);
    	TreeNode node = new TreeNode(val);
    	node.left = merge(t1 == null? null:t1.left, t2==null?null:t2.left);
    	node.right = merge(t1 == null?null:t1.right, t2==null?null:t2.right);
    	return node;
    }
    public TreeNode merge1(TreeNode t1, TreeNode t2) {
    	if (t1 == null && t2 == null) {
    		return null;
    	} else if (t1 == null) {
    		return t2;
    	} else if (t2 == null) {
    		return t1;
    	} 
    	TreeNode node = new TreeNode(t1.val + t2.val);
    	node.left = merge1(t1.left, t2.left);
    	node.right = merge1(t1.right, t2.right);
    	return node;
    }
}
