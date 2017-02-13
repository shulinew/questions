package tree;

import list.ListNode;

/*
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height 
 * balanced BST.
 */
public class ConstructBinaryTree {
    public TreeNode sortedListToBST(ListNode head) {
    	if (head == null) return null;
    	return constructBST(head, null); 
    }
    private TreeNode constructBST(ListNode head, ListNode tail) {
    	ListNode slow = head;
    	ListNode fast = head;
    	if (head == tail) return null;
    	while (fast != tail && fast.next != tail) {
    		fast = fast.next.next;
    		slow = slow.next;
    	}
    	TreeNode thread = new TreeNode(slow.val);
    	thread.left = constructBST(head, slow);
    	thread.right = constructBST(slow.next, null);
    	return thread;
    }
    
    private ListNode current;
    //TODO: need to check
    public TreeNode sortedListToBST1(ListNode head) {
    	current = head;
    	int height = 0;
    	while (current != null) {
    		height++;
    		current = current.next;
    	}
    	current = head;
    	return constructBST(height);
    }
    private TreeNode constructBST(int height) {
    	if (height == 0) return null;
    	if (height == 1) {
    		TreeNode root = new TreeNode(current.val);
    		current = current.next;
    		return root;
    	}
    	TreeNode root = new TreeNode(0);
    	root.left = constructBST(height/2);
    	root.val = current.val;
    	current = current.next;
    	root.right = constructBST((height - 1)/2);
    	return root;
    }
}
