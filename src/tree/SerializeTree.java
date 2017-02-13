package tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on 
 * how your serialization/deserialization algorithm should work. You just need to ensure that a 
 * binary search tree can be serialized to a string and this string can be deserialized to the 
 * original tree structure.
 */
public class SerializeTree {
    private static final String SEP = ",";
    private static final String NULL = "NULL";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	StringBuilder sb = new StringBuilder();
    	if (root == null) return null;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
    	while (!stack.isEmpty()) {
    		root = stack.pop();
    		sb.append(root.val).append(SEP);
    		if (root.right != null) {
    			stack.push(root.right);
    		}
    		if (root.left != null) {
    			stack.push(root.left);
    		}
    	}
    	return sb.toString();
    }

    // Decodes your encoded data to tree.
    // pre-order traversal
    public TreeNode deserialize(String data) {
    	if (data == null) return null;
    	String[] strs = data.split(SEP);
    	if (strs.length == 0) return null;
    	Queue<Integer> queue = new LinkedList<Integer>();
    	for (String s: strs) {
    		if (s != null && s.length() != 0) {
    			try {
    				queue.offer(Integer.parseInt(s));
    			} catch (NumberFormatException e) {
    				return null;
    			}
    		}
    	}
    	return buildTree(queue);
    }
    // some notes:
    //   5
    //  3 6
    // 2   7
	//queue: 5,3,2,6,7
    private TreeNode buildTree(Queue<Integer> queue) {
    	if (queue == null || queue.isEmpty()) return null;
    	TreeNode root = new TreeNode(queue.poll()); 
    	Queue<Integer> subQueue = new LinkedList<Integer>();
    	while (!queue.isEmpty() && queue.peek() < root.val){
    		subQueue.offer(queue.poll());
    	}
    	root.left = buildTree(subQueue);
    	root.right = buildTree(queue);
    	return root;
    }
    
    /*
     * Serialize and de-serialize binary tree
     */
    public String serializeBinaryTree(TreeNode root) {
    	StringBuilder sb = new StringBuilder();
    	if (root == null) return null;
    	buildString(root, sb);
    	return sb.toString();
    }
    private void buildString(TreeNode node, StringBuilder sb) {
    	if (node == null) {
    		sb.append(NULL).append(SEP);
    	} else {
    		sb.append(node.val).append(SEP);
	    	buildString(node.left, sb);
	    	buildString(node.right,sb);
    	}
    }
    public TreeNode deserializeBinaryTree(String data) {
    	if (data == null) return null;
    	Deque<String> nodes = new LinkedList<String>();
    	nodes.addAll(Arrays.asList(data.split(SEP)));
    	return buildBinaryTree(nodes);
    }
    private TreeNode buildBinaryTree(Deque<String> queue) {
    	String str = (String)queue.remove();
    	if (NULL.equals(str)) {
    		return null;
    	} else {
    		TreeNode node = new TreeNode(Integer.valueOf(str));
    		node.left = buildBinaryTree(queue);
    		node.right = buildBinaryTree(queue);
    		return node;
    	}
    }

}
