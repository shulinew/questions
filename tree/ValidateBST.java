package tree;

import java.util.Stack;

/*
 * 
    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.
    

I will show you all how to tackle various tree questions using iterative inorder traversal.
 First one is the standard iterative inorder traversal using stack. Hope everyone agrees with this solution.

Question : Binary Tree Inorder Traversal

public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if(root == null) return list;
    Stack<TreeNode> stack = new Stack<>();
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

Now, we can use this structure to find the Kth smallest element in BST.

Question : Kth Smallest Element in a BST

 public int kthSmallest(TreeNode root, int k) {
     Stack<TreeNode> stack = new Stack<>();
     while(root != null || !stack.isEmpty()) {
         while(root != null) {
             stack.push(root);    
             root = root.left;   
         } 
         root = stack.pop();
         if(--k == 0) break;
         root = root.right;
     }
     return root.val;
 }

We can also use this structure to solve BST validation question.

Question : Validate Binary Search Tree

public boolean isValidBST(TreeNode root) {
   if (root == null) return true;
   Stack<TreeNode> stack = new Stack<>();
   TreeNode pre = null;
   while (root != null || !stack.isEmpty()) {
      while (root != null) {
         stack.push(root);
         root = root.left;
      }
      root = stack.pop();
      if(pre != null && root.val <= pre.val) return false;
      pre = root;
      root = root.right;
   }
   return true;
}
 */
public class ValidateBST {
	public boolean isValidBSTIterative(TreeNode root){
		if (root == null) return true;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode previous = null;
		while (!stack.isEmpty() || root != null){
			while (root != null){
				stack.push(root);
				root = root.left;
			}
			TreeNode node = stack.pop();
			if (previous != null && previous.val >= node.val) 
				return false;
			previous = node;
			node = node.right;
		}
		return true;
	}
	public boolean isValidBST(TreeNode root){
		return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}
	private boolean helper(TreeNode root, long minVal, long maxVal){
		if (root == null) return true;
		if (root.val > maxVal || root.val < minVal) {
			return false;
		}
		return helper(root.left, Long.MIN_VALUE, root.val) && helper(root.right, root.val, Long.MAX_VALUE);
	}

}
