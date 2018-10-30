package tree;


public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	public TreeNode next;
	
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
	public TreeNode getLeft(){
		return this.left;
	}
	public TreeNode getRight() {
		return this.right;
	}
	public int getValue(){
		return this.val;
	}

}
