package tree;

import java.util.LinkedList;
import java.util.Queue;

public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
    	if (root == null) return 0;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	int sum = 0;
    	while (!queue.isEmpty()){
    		int currentSize = queue.size();
    		for (int i = 0;i<currentSize;i++){
    			TreeNode peek = queue.poll();
    			if (peek.left != null){
    				queue.add(peek.left);
                    if(peek.left.left==null && peek.left.right==null){
                    	sum += peek.left.val;
                    }
    			}
    			if (peek.right != null){
    				queue.add(peek.right);
    			}
    		}
    	}
    	return sum;
    }
    /*
     * [3,9,20,null,null,15,7]
     */
    public static void main(String[] args){
//    	TreeTraversal test = new TreeTraversal();zz
//    	TreeNode root = new TreeNode(3);
//    	TreeNode node1 = new TreeNode(9);
//    	TreeNode node2 = new TreeNode(20);
//    	root.left = node1;
//    	root.right = node2;
//    	TreeNode node3 = new TreeNode(15);
//    	TreeNode node4 = new TreeNode(7);
//    	node2.left = node3;
//    	node2.right = node4;
//    	test.zigzagLevelOrder(root);
    	/*
    	 *  *        1
			        / \
			       2   5
			      / \   \
			     3   4   6
    	 */
    	FlatBinaryTreeToList flat = new FlatBinaryTreeToList();
    	TreeNode root = new TreeNode(1);
    	TreeNode node1 = new TreeNode(2);
    	TreeNode node2 = new TreeNode(3);
    	TreeNode node3 = new TreeNode(4);
    	TreeNode node4 = new TreeNode(5);
    	TreeNode node5 = new TreeNode(6);
    	root.left = node1;
    	root.right = node4;
    	node1.left = node2;
    	node1.right = node3;
//    	node2.left = node3;
    	node4.right = node5;
    	flat.flatten(root);
    	
    	
    }
}
