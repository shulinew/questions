package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Given a binary tree, return the level order traversal of its nodes' values. 
 * (ie, from left to right, level by level).
For example:
Given binary tree {3,9,20,#,#,15,7},
 */
public class BinaryTreeTraverse {
    public List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	levelHelper(root,result,0);
    	return result;
        
    }
    private void levelHelper(TreeNode root, List<List<Integer>> result, int height){
    	if (root == null) return;
    	if (height >= result.size()){
    		result.add(new ArrayList<Integer>());
    	}
    	result.get(height).add(root.val);
    	levelHelper(root.left,result,height+1);
    	levelHelper(root.right,result, height+1);
    }
    
    public List<List<Integer>> levelOrder1(TreeNode root) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if (root == null)
    		return result;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	
    	int currentLevel = 0;
    	
    	while(!queue.isEmpty()){
    		List<Integer> level = new ArrayList<Integer>();
    		currentLevel = queue.size();
    		for(int i = 0; i<currentLevel;i++){
    			TreeNode peek = queue.poll();
    			level.add(peek.val);
    			if (peek.left != null){
    				queue.add(peek.left);
    			}
    			if (peek.right != null){
    				queue.add(peek.right);
    			}
    		}
    		result.add(level);
    	}
    	return result;
        
    }
    /*
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
	For example:
	Given binary tree {3,9,20,#,#,15,7},
     */
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if (root == null)
    		return result;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	
    	int currentLevel = 0;
    	
    	while(!queue.isEmpty()){
    		List<Integer> level = new ArrayList<Integer>();
    		currentLevel = queue.size();
    		for (int i = 0;i<currentLevel;i++){
    			if (queue.peek().left != null){
    				queue.offer(queue.peek().left);
    			}
    			if (queue.peek().right != null){
    				queue.offer(queue.peek().right);
    			}
    			level.add(queue.poll().val);
    		}
    		result.add(0,level);
    	}
    	return result;
        
    }
    public List<List<Integer>> levelOrderBottomDFS(TreeNode root) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	levelOrderBottomHelper(root,result,0);
    	return result;
    }
    private void levelOrderBottomHelper(TreeNode root, List<List<Integer>> result, int height){
    	if (root == null) return;
    	if (height >= result.size()){
    		result.add(0, new ArrayList<Integer>());
    	}

    	levelHelper(root.left,result,height+1);
    	levelHelper(root.right,result, height+1);
    	//
    	result.get(result.size() - height -1).add(root.val);
    }


}
