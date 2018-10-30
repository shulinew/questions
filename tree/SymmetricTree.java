package tree;

import java.util.Stack;

/*
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric: 
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
    	if (root == null)
    		return true;
    	return isSymmetric(root.left, root.right);

        
    }
    private boolean isSymmetric(TreeNode left, TreeNode right){
    	if (left == null || right == null){
    		return left == right;
    	}
    	if (left.val == right.val){
    		return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    	}else{
    		return false;
    	}
    }
    public boolean isSymmetricInterative(TreeNode root){
    	if (root == null)
    		return true;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	if (root.left != null){
    		if (root.right == null)
    			return false;
    		stack.push(root.left);
    		stack.push(root.right);
    	}else if (root.right != null){
    		return false;
    	}
    	TreeNode left,right;
    	while(!stack.isEmpty()){
    		right = stack.pop();
    		left = stack.pop();
    		if (right.val != left.val){
    			return false;
    		}
    		if (left.left != null){
    			if (right.right != null){
    				stack.push(left.left);
    				stack.push(right.right);
    			}else{
    				return false;
    			}
    		}else if (right.right != null){
    			return false;
    		}
    		if (left.right != null){
    			if (right.left != null){
    				stack.push(left.right);
    				stack.push(right.left);
    			}else{
    				return false;
    			}
    		}else if (right.left != null){
    			return false;
    		}
    	}
    	return true;
    }
}
