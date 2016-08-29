package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Given a binary tree, return the inorder traversal of its nodes' values.
	For example:
	Given binary tree {1,#,2,3},
 */
public class TreeTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> treeInorderList = new ArrayList<Integer>();
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode node = root;
    	while (!stack.isEmpty() || node != null){
    		if (node != null){
    			stack.push(node);
    			node = node.left;
    		}else{
    			node = stack.pop();
    			treeInorderList.add(node.val);
    			node = node.right;
    		}
    	}
    	return treeInorderList;       
    }
    public List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> treePreorderTraversalList = new ArrayList<Integer>();
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode node = root;
    	while(!stack.isEmpty() || node != null){
    		if (node != null){
    			treePreorderTraversalList.add(node.val);
    			if (node.right != null){
    				stack.push(node.right);
    			}
    			node = node.left;
    		}else{
    			node = stack.pop();
    		}
    	}
    	return treePreorderTraversalList;
    	
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
    	List<Integer> treePostorderTraversalList = new ArrayList<Integer>();
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode node = root;
    	TreeNode lastVisited = null;
    	while(!stack.isEmpty() || node != null){
    		if (node != null){
    			stack.push(node);
    			node = node.left;
    		}else{
    			TreeNode peekNode = stack.peek();
    			if (peekNode.right != null && lastVisited != peekNode.right){
    				node = peekNode.right;
    			}else{
    				treePostorderTraversalList.add(peekNode.val);
    				lastVisited = stack.pop();
    			}
    		}
    	}
    	return treePostorderTraversalList;
        
    }
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
    }

}
