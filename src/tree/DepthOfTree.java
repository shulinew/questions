package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthOfTree {
	/*
	 * Given a binary tree, find its minimum depth.
	The minimum depth is the number of nodes along the shortest path from the root node down to the nearest
	 leaf node.
	 */
    public int minDepth(TreeNode root) {
    	if (root == null){
    		return 0;
    	}
    	if (root.left == null) {
    	    return minDepth(root.right) + 1;
    	}
    	if (root.right == null) {
    	    return minDepth(root.left) + 1;
    	}
    	return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
    
    /*
     * Given a binary tree, find its maximum depth.
	The maximum depth is the number of nodes along the longest path from the root node down to the farthest
	 leaf node.
     */
    
    public int maxDepth(TreeNode root) {
    	if (root == null){
    		return 0;
    	}
    	if (root.left == null) {
    	    return maxDepth(root.right) + 1;
    	}
    	if (root.right == null) {
    	    return maxDepth(root.left) + 1;
    	}
    	return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    
    public int maxPathIterative(TreeNode root){
        if(root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<Integer> value = new Stack<Integer>();
        stack.push(root);
        value.push(1);
        int max = 0;
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int temp = value.pop();
            max = Math.max(temp, max);
            if(node.left != null) {
                stack.push(node.left);
                value.push(temp+1);
            }
            if(node.right != null) {
                stack.push(node.right);
                value.push(temp+1);
            }
        }
        return max;
    }
    
    public int maxPathBFS(TreeNode root){
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int count = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                TreeNode node = queue.poll();
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            count++;
        }
        return count;
    }

}
