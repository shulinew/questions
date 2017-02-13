package tree;

import java.util.Stack;


/*
 * Given a binary tree, flatten it to a linked list in-place. 
 *       1
        / \
       2   5
      / \   \
     3   4   6
        1
	    \
	     2
	      \
	       3
	        \
	         4
	          \
	           5
	            \
	             6
     
 */
public class FlatBinaryTreeToList {
    public void flatten(TreeNode root) {
      if (root == null) return;
      Stack<TreeNode> stack = new Stack<TreeNode>();
      TreeNode node = root;
      stack.add(node);
      while(!stack.isEmpty()){
    	  node = stack.pop();
    	  if (node.right != null){
    		  stack.push(node.right);
    	  }
    	  if (node.left != null){
    		  stack.push(node.left);
    	  }
          if (!stack.isEmpty()) 
              node.right = stack.peek();
         node.left = null;  
      }  
    }
    
    private TreeNode prev = null;

    public void flatten2(TreeNode root) {
        if (root == null)
            return;
        flatten2(root.right);
        flatten2(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
    
    public void flatten1(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode temp = null;
        while(!stack.isEmpty() || node != null){
      	  if (node != null){
      		  if (node.right != null){
      			  stack.push(node.right);
      		  }
      		  temp = node;
      		  temp.left = null;
      		  temp.right = null;
      		  node = node.left;
      	  } else {
      		  node = stack.pop();
      	  }
        }
        
      }
    
    public void flatten3(TreeNode root) {
        if (root == null) return;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = null;
        
        flatten3(left);
        flatten3(right);
        
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }

}
