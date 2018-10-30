package tree;

/*
 *  Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

    The root is the maximum number in the array.
    The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
    The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.

Construct the maximum tree by the given array and output the root node of this tree. 
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
 */
public class MaximumTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
    	if (nums.length == 0) {
    		return null;
    	} 
    	return constructTree(nums, 0, nums.length-1);
    }
    private TreeNode constructTree(int[] nums, int start, int end) {
    	if (start >= end) return null;
    	int maxId = findMax(nums, start, end);
    	TreeNode root = new TreeNode(nums[maxId]);
    	root.left = constructTree(nums, start, maxId-1);
    	root.right = constructTree(nums, maxId + 1, end);
    	return root;
    	
    }
    private int findMax(int[] nums, int start, int end) {
    	int id = start;
    	for (int i = start; i <= end; i++) {
    		if (nums[id] < nums[i]) {
    			id = i;
    		}
    	}
    	return id;
    }
}
