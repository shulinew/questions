package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
    public List<Integer> preorderTraversal1(TreeNode root){
    	if (root == null) return null;
    	List<Integer> list = new ArrayList<Integer>();
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
    	while(!stack.isEmpty()){
    		TreeNode node = stack.pop();
    		list.add(node.val);
    		if (node.right != null){
    			stack.push(node.right);
    		}
    		if (node.left != null){
    			stack.push(node.left);
    		}
    	}
    	return list;
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
    public List<Integer> breadFirstTraversal(TreeNode root){
    	List<Integer> list = new ArrayList<Integer>();
    	if (root == null) return list;
    	
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	while (!queue.isEmpty()){
    		TreeNode node = queue.poll();
    		list.add(node.val);
    		if (node.left != null){
    			queue.add(node.left);
    		}
    		if (node.right != null){
    			queue.add(node.right);
    		}
    	}
    	return list;
    }
    
    /*
     * [3,9,20,null,null,15,7]
     *  3
	   / \
	  9  20
	    /  \
	   15   7
   return [
		  [3],
		  [20,9],
		  [15,7]
		]
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if (root == null) return result;
    	
    	int currentLevel = 0;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	while(!queue.isEmpty()){
    		currentLevel = queue.size();
    		List<Integer> level = new ArrayList<Integer>();
    		for (int i = 0; i < currentLevel; i++){
    			TreeNode node = queue.poll();
    			level.add(node.val);
    			if (node.left != null){
    				queue.add(node.left);
    			}
    			if (node.right != null){
    				queue.add(node.right);
    			}
    		}
    		result.add(level);
    	}
    	List<List<Integer>> result1 = new ArrayList<List<Integer>>();
    	for (int i = 0; i < result.size(); i++){
    		List<Integer> level = result.get(i);
    		if (i %2 == 0){
    			result1.add(level);
    		} else {
    			int j = level.size()-1;
    			List<Integer> zigzagLevel = new ArrayList<Integer>();
    			while ( j >=0){
    				zigzagLevel.add(level.get(j));
    				j--;
    			}
    			result1.add(zigzagLevel);
    		}
    	}
    	return result1;       
    }
    
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) 
    {
        List<List<Integer>> sol = new ArrayList<List<Integer>>();
        travel(root, sol, 0);
        return sol;
    }
    
    private void travel(TreeNode curr, List<List<Integer>> sol, int level)
    {
        if(curr == null) return;
        
        if(sol.size() <= level)
        {
            List<Integer> newLevel = new LinkedList<Integer>();
            sol.add(newLevel);
        }
        
        List<Integer> collection  = sol.get(level);
        if(level % 2 == 0) collection.add(curr.val);
        else collection.add(0, curr.val);
        
        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) 
    {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean order = true;
        int size = 1;

        while(!q.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for(int i = 0; i < size; ++i) {
                TreeNode n = q.poll();
                if(order) {
                    tmp.add(n.val);
                } else {
                    tmp.add(0, n.val);
                }
                if(n.left != null) q.add(n.left);
                if(n.right != null) q.add(n.right);
            }
            res.add(tmp);
            size = q.size();
            order = order ? false : true;
        }
        return res;
    }
}
