package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Follow up:
	What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */
public class KthSmallest {
	
    public int kthSmallest(TreeNode root, int k) {
        int left = nodeCount(root.left);  // this value can be saved in the root node
        if(left + 1 == k) {
            return root.val;
        } else if (left + 1 < k) {
            return kthSmallest(root.right, k - left - 1);
        } else {
            return kthSmallest(root.left, k);
        }
    }
    
    private int nodeCount(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }
    /*
     * Recursive inorder traversal method
     */
    public int kthSmallestRecursive(TreeNode root, int k){
    	List<Integer> result = new ArrayList<Integer>();
    	inorderTraversal(root, result);
    	return result.get(k-1);
    }
    private void inorderTraversal(TreeNode root, List<Integer> list){
    	inorderTraversal(root.left, list);
    	list.add(root.val);
    	inorderTraversal(root.right, list);
    }
    

    /*
     * iterative method 
     */
    public int kthSmallestIterative(TreeNode root, int k){
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	List<Integer> result = new ArrayList<Integer>();
    	int i = 0;
    	TreeNode current = root;
    	while (current != null || i < k || !stack.isEmpty()){
    		if (current != null){
    			stack.push(current);
    			current = current.left;
    		} else{
    			current = stack.pop();
    			result.add(current.val);
    			current = current.right;
    			i++;
    		}
    	}
    	return result.get(k - 1);
    }
    public int kthSmallestIterative1(TreeNode root, int k){
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	while (root != null){
    		stack.push(root);
    		root = root.left;
    	}
    	while (k != 0){
    		TreeNode node = stack.pop();
    		k--;
    		if (k == 0) {
    			return node.val;
    		}
    		TreeNode node1 = node.right;
    		while(node1 != null){
    			stack.push(node1);
    			node1 = node1.left; 
    		}
    	}
    	return -1;
    }
    
    
    public int kthSmallest3(TreeNode root, int k) {
    	int count = 0;
        if (root == null) {
            return -1;
        } else {
            int ret = kthSmallest3(root.left, k);
            ++count;
            return ret != -1 ? ret : count == k ? root.val : kthSmallest(root.right, k);
        }
    }
    
    public int kthSmallest4(TreeNode root, int k) {
        int count = nodeCount(root.left);
        if (k <= count) {
            return kthSmallest4(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest4(root.right, k-1-count); // 1 is counted as current node
        }
        
        return root.val;

    }

    
    private static int number = 0;
    private static int count = 0;

    public int kthSmallest5(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }
    
    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) helper(n.right);
    }

    
    /*
     * Go inorder and decrease k at each node. Stop the whole search as soon as k is zero, and then the k-th element is immediately returned all the way to the recursion top 
     * and to the original caller.

	Try the left subtree first. If that made k zero, then its answer is the overall answer and we return it right away. Otherwise, decrease k for the current node, 
	and if that made k zero, then we return the current node's value right away. Otherwise try the right subtree and return whatever comes back from there.
int kthSmallest(TreeNode* root, int& k) {
    if (root) {
        int x = kthSmallest(root->left, k);
        return !k ? x : !--k ? root->val : kthSmallest(root->right, k);
    }
}
     */

}
