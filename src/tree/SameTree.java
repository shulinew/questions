package tree;
/*
 *  Given two binary trees, write a function to check if they are equal or not.
    Two binary trees are considered equal if they are structurally identical and the nodes have the same value. 
 */

public class SameTree {
	
    public boolean isSameTree(TreeNode p, TreeNode q) {
    	if (p != null && q != null){
    		if (p.val != q.val){
    			return false;
    		}else{
    			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    		}
    	} else if (p == null && q != null){
    		return false;
    	} else if (p != null && q == null){
    		return false;
    	}else{
    		return true;
    	}
        
    }
}
