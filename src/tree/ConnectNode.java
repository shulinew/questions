package tree;

/*
 * Populate each next pointer to point to its next right node. If there is no next right node, the 
 * next pointer should be set to NULL. Initially, all next pointers are set to NULL.

Note:
    Only use constant extra space.
    Assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
      	1
       /  \
      2    3
     / \  / \
    4  5  6  7
            1 -> NULL
	       /  \
	      2 -> 3 -> NULL
	     / \  / \
	    4->5->6->7 -> NULL

 */
public class ConnectNode {
	
	public void connect(TreeNode root){
		TreeNode levelStart = root;
		while (levelStart != null) {
			TreeNode current = levelStart;
			while (current != null) {
				if (current.left != null) {
					current.left.next = current.right;
				}
				if (current.right != null && current.next != null) {
					current.right.next = current.next.left;
				}
				current = current.next;
			}
			levelStart = levelStart.left;
		}
	}
	
/*
 * The given tree could be any binary tree
 */
	public void connect1(TreeNode root) {
		TreeNode current = root;
		TreeNode previous = null;
		TreeNode headNextLevel = null;
		
		while (current != null) {
			while (current != null) {
				if (current.left != null) {
					if (previous != null) {
						previous.next = current.left;
					} else {
						headNextLevel = current.left;
					}
					previous = current.left;
				}
				if (current.right != null) {
					if (previous != null) {
						previous.next = current.right;
					} else {
						headNextLevel = current.right;
					}
					previous = current.right;
				}
				current = current.next;
			}
			current = headNextLevel;
			headNextLevel = null;
			previous = null;
		}
	}

}
