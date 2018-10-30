package tree;

/*
 * Given a binary tree, return the tilt of the whole tree.
	The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and 
	the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.
Input: 

Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
https://leetcode.com/problems/binary-tree-tilt/description/
 */
public class FindTilt {
	int sum = 0;
//	public int findTilt(TreeNode root) {
//		if (root == null ) return 0;
//		int tilt = Math.abs(sum(root.left) - sum(root.right));
//		return tilt;
//	}
//	private int sum(TreeNode node) {
//		if (node == null) return 0;
//		int sum = node.val + sum(node.left) + sum(node.right);
//		return sum;
//	}
	public int findTilt(TreeNode root) {
		postOrder(root);
		return sum;
	}
	private int postOrder(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = postOrder(node.left);
		int right = postOrder(node.right);
		sum += Math.abs(left - right);
		return node.val + right + left;
	}
	
	public int findTilt1(TreeNode root) {
		int [] result = new int[1];
		postOrder1(root, result);
		return result[0];
	}
	private int postOrder1(TreeNode node, int[] result) {
		if (node == null) return 0;
		int left = postOrder1(node.left, result);
		int right = postOrder1(node.right, result);
		result[0] += Math.abs(left - right);
		return left + right + node.val;
	}
}
