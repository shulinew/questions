
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
