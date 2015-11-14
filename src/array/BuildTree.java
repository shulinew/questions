package array;

import java.util.Stack;

public class BuildTree {
	
	public TreeNode buildTreeInterative(int[] inorder, int[] postorder){
	    if (inorder.length == 0 || postorder.length == 0) return null;
	    int ip = inorder.length - 1;
	    int pp = postorder.length - 1;

	    Stack<TreeNode> stack = new Stack<TreeNode>();
	    TreeNode prev = null;
	    TreeNode root = new TreeNode(postorder[pp]);
	    stack.push(root);
	    pp--;

	    while (pp >= 0) {
	        while (!stack.isEmpty() && stack.peek().val == inorder[ip]) {
	            prev = stack.pop();
	            ip--;
	        }
	        TreeNode newNode = new TreeNode(postorder[pp]);
	        if (prev != null) {
	            prev.left = newNode;
	        } else if (!stack.isEmpty()) {
	            TreeNode currTop = stack.peek();
	            currTop.right = newNode;
	        }
	        stack.push(newNode);
	        prev = null;
	        pp--;
	    }

	    return root;
	}
    public TreeNode buildTreeFromPostOrder(int[] inorder, int[] postorder) {
    	TreeNode tree = heperToBuildFromPostorder(inorder, postorder, postorder.length-1, 0, inorder.length -1);
    	return tree;
        
    }
    private TreeNode heperToBuildFromPostorder(int[] inorder, int[] postorder, int rootPos, int start, int end){
    	if (rootPos < 0 || start > end)
    		return null;
    	TreeNode node = new TreeNode(postorder[rootPos]);
    	int pos = 0;
    	for (int i = start;i<= end;i++){
    		if (inorder[i] == postorder[rootPos]){
    			pos = rootPos;
    			break;
    		}
    	}
    	node.left = heperToBuildFromPostorder(inorder, postorder,rootPos + pos - end, start, pos-1);
    	node.right = heperToBuildFromPostorder(inorder, postorder, rootPos -1, pos+1, end);
    	return node;
    }
    
    public TreeNode buildTreeFromPreOrder(int[] preorder, int[] inorder) {
    	if (preorder.length == 0 || inorder.length ==0)
    		return null;
    	TreeNode tree = buildTreeFromPreorder(preorder, inorder, 0 , 0, inorder.length-1);
    	
    	return tree;
        
    }
    private TreeNode buildTreeFromPreorder(int [] preorder, int [] inorder, int rootPos, int start, int end){
    	if (rootPos >= preorder.length || start > end)
    		return null;
    	TreeNode tree = new TreeNode(preorder[rootPos]);
    	int pos = 0;
    	for (int i = start; i<= end;i++){
    		if (inorder[i] == preorder[rootPos]){
    			pos = i;
    			break;
    		}
    	}
    	tree.left = buildTreeFromPreorder(preorder, inorder, rootPos + 1,start, pos-1);
    	tree.right = buildTreeFromPreorder(preorder, inorder, rootPos+ end-pos ,pos+1, end);
    	
    	return tree;
    	
    }

}
