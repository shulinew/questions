package array;

/*
 * If we are given preorder and postorder traversal, can we construct the binary tree? Why or why not?
Given preorder, inorder, and postorder traversal, how can you verify if these traversals are referring to the exact same binary tree?
Remember from my earlier post: Serialization/Deserialization of a Binary Tree? It is trivial to see this as an alternative method to serialize/deserialize a binary tree.
 */
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode (int x){
		val = x;
	}
	public void printValue(){
		System.out.println(val);
	}
	
	public void setLeft(TreeNode node){
		this.left = node;
	}
	public void setRight(TreeNode node){
		this.right = node; 
	}
}
