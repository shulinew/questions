package tree;
/*
 *  Given a binary tree, find the maximum path sum.

	For this problem, a path is defined as any sequence of nodes from some starting node to any node in 
	the tree along the parent-child connections. The path must contain at least one node and does not 
	need to go through the root.
 */
public class MaxSumPath {
	int maxValue;
	
	public int maxPathSum(TreeNode root) {
		maxValue = Integer.MIN_VALUE;
		maxPathDown(root);
		return maxValue;
	}
	private int maxPathDown(TreeNode root) {
		if (root == null) return 0;
		int left = Math.max(0,  maxPathDown(root.left));
		int right = Math.max(0,  maxPathDown(root.right));
		maxValue = Math.max(maxValue, left + right + root.val);
		return Math.max(left, right) + root.val;
	}

}
