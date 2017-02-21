package tree;
/*
 *  Two elements of a binary search tree (BST) are swapped by mistake.
	
	Recover the tree without changing its structure. 
 */
public class RecoverBST {
	TreeNode firstElement = null;
	TreeNode secondElement = null;
	TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);
	public void recoverTree(TreeNode root) {
		traverse(root);
		int temp = firstElement.val;
		firstElement.val = secondElement.val;
		secondElement.val = temp;
	}
	private void traverse(TreeNode root) {
		if (root == null) return;
		traverse(root.left);
		if (firstElement == null && prevElement.val > root.val) {
			firstElement = prevElement;
		}
		if (firstElement != null && prevElement.val >= root.val) {
			secondElement = root;
		}
		prevElement = root;
		traverse(root.right);
	}

}
