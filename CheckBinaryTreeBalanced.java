import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;


public class CheckBinaryTreeBalanced {
    public boolean isBalanced(TreeNode root) {
        return false;
    }
    
    public TreeNode findTreeNode(TreeNode root, int val){
    	if (root.val == val){
    		return root;
    	} else if (val < root.val){
    		return findTreeNode(root.left, val);
    	} else {
    		return findTreeNode(root.right, val);
    	}
    }
    public int getTreeHeight(TreeNode root){
    	return 1 + Math.max(getTreeHeight(root.left), getTreeHeight(root.right));
    }
    public void preorderTraversal(TreeNode root){
    	if (root == null)
    		return;
    	root.printValue();
    	preorderTraversal(root.left);
    	preorderTraversal(root.right);
    }
    public void preorderTraversal1(TreeNode root){
    	NodeStack stack = new NodeStack();
    	stack.push(root);
    	while (!stack.isEmpty()){
    		TreeNode current = stack.pop();
    		System.out.println(current.val);
    		if (current.right != null){
    			stack.push(current.right);
    		}
    		if (current.left != null){
    			stack.push(current.left);
    		}
    	}
    	
    }
    public static TreeNode findLowestCommonAncestor( TreeNode root, int value1, int value2 ){
    	while (root != null){
	    	int value = root.val;
	    	if (value < value1 && value < value2){
	    		root = root.right;
	    	}else if (value > value1 && value > value2){
	    		root = root.left;
	    	}else {
	    		return root;
	    	}
    	}
    	return null;
    }
    public static TreeNode heapifyBinaryTree( TreeNode root ){

        int size = traverse( root, 0, null ); // Count nodes 
        TreeNode[] nodeArray = new TreeNode[size];
        traverse( root, 0, nodeArray );       // Load nodes into array 

        // Sort array of nodes based on their values, using Comparator object
        Arrays.sort(nodeArray, new Comparator<TreeNode>(){
        	public int compare(TreeNode a, TreeNode b){
        		int av = a.val;
        		int bv = b.val;
        		return (av < bv ? -1: (av==bv? 0: 1));
        	}
        });
        // Reassign children for each node 
        for( int i = 0; i < size; i++ ){
            int left = 2*i + 1;
            int right = left + 1;
            nodeArray[i].setLeft( left >= size ? null : nodeArray[left] );
            nodeArray[i].setRight( right >= size ? null : nodeArray[right] );
        }
        return nodeArray[0]; // Return new root node 
    }
    
    private static int traverse(TreeNode node, int count, TreeNode[] treeArray){
    	if (node == null)
    		return 0;
    	if (treeArray != null){
    		treeArray[count++] = node;
    	}
    	count = traverse(node.left, count, treeArray);
    	count = traverse(node.right, count, treeArray);
    	return count;
    }
    
    class NodeStack{
    	Stack<TreeNode> treeNode;
    	
    	public NodeStack(){
    		treeNode = new Stack<TreeNode>();
    	}
    	public void push(TreeNode node){
    		treeNode.push(node);
    	}
    	public TreeNode pop(){
    		return (TreeNode)treeNode.pop();
    	}
    	public boolean isEmpty(){
    		return treeNode.isEmpty();
    	}
    }
}
