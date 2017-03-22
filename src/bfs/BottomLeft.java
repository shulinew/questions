package bfs;

import java.util.LinkedList;
import java.util.Queue;

import tree.TreeNode;

/*
 * Given a binary tree, find the leftmost value in the last row of the tree. 
 */
public class BottomLeft {
   public int findBottomLeftValue(TreeNode root) {
        return findBottomLeftValue(root, 1, new int[]{0,0});
    }

    private int findBottomLeftValue(TreeNode root, int depth, int[] res) {
    	if (res[1] < depth) {
    		res[0] = root.val;
    		res[1] = depth;
    	}
    	if (root.left != null) {
    		findBottomLeftValue(root.left, depth+1, res);
    	}
    	if (root.right != null) {
    		findBottomLeftValue(root.right, depth+1, res);
    	}
    	return res[0];
    }
    public int findBottomLeftMostValue(TreeNode root) {
    	if (root == null) return 0;
    	int result = 0;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	
    	while (!queue.isEmpty()){
    		int size = queue.size();
    		result = queue.peek().val;
    		for (int i = 0; i< size; i++) {
    			TreeNode node = queue.poll();
    			if (node.left != null) {
    				queue.add(node.left);
    			}
    			if (node.right != null) {
    				queue.add(node.right);
    			}
    		}
    	}
    	return result;
    }

}
