package tree;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/*
 *  Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree 
 *  has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among 
 *  its two sub-nodes.
	
	Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the 
	whole tree.
	
	If no such second minimum value exists, output -1 instead. 
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
 */
public class FindSecondMinimumValue {
	public int findSecondMinimumValue(TreeNode root) {
		if (root == null) return -1;
		Set<Integer> set = new TreeSet<Integer>();
		helper(root, set);
		Iterator<Integer> iterator = set.iterator();
		int count = 0;
		while(iterator.hasNext()) {
			count++;
			int result = iterator.next();
			if (count == 2) {
				return result;
			}
		}
		return -1;
	}
	private void helper(TreeNode node, Set<Integer> set) {
		if (node == null) return;
		set.add(node.val);
		helper(node.left, set);
		helper(node.right, set);
		return;
	}
	
	public int findSecondMinimumValue1(TreeNode node) {
		if (node == null || (node.left == null && node.right == null)) {
			return -1;
		}
		int left = node.left.val;
		int right = node.right.val;
		
		if (left == node.val) {
			left = findSecondMinimumValue1(node.left);
		}
		if (right == node.val) {
			right = findSecondMinimumValue1(node.right);
		}
		if (left != -1 && right != -1) {
			return Math.min(left, right);
		} else if (left != -1) {
			return left;
		} else {
			return right;
		}
		
	}

}


