package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tree.TreeNode;

/*
 * You need to find the largest value in each row of a binary tree.
 * Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
 */
public class LargestValueOfRow {
	
   public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res, 0);
        return res;
    }
    private void helper(TreeNode root, List<Integer> res, int depth){
        if(root == null){
            return;
        }
       //expand list size
        if(depth == res.size()){
            res.add(root.val);
        }
        else{
            res.set(depth, Math.max(res.get(depth), root.val));
        }
        helper(root.left, res, depth+1);
        helper(root.right, res, depth+1);
    }
    
    public int[] findValueMostElement(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return new int[0];
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(max);
        }
        
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        
        return result;
    }
    public int[] findLargestElement(TreeNode root) {
    	List<Integer> result = new ArrayList<Integer>();
    	if (root == null) return new int[0];
    	
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	
    	while(!queue.isEmpty()) {
    		int size = queue.size();
    		int max = Integer.MIN_VALUE;
    		for (int i =0;i < size; i++) {
    			TreeNode node = queue.poll();
    			max = Math.max(max, node.val);
    			if (node.left != null) {
    				queue.add(node.left);
    			}
    			if (node.right != null) {
    				queue.add(node.right);
    			}
    		}
    		result.add(max);
    	}
    	int[] res = new int[result.size()];
    	for (int i=0; i < result.size(); i++) {
    		res[i] = result.get(i);
    	}
    	return res;
    }

}
