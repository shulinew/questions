package tree;

import java.util.ArrayList;
import java.util.List;

/*
 *  Given a binary tree, return all root-to-leaf paths.
  For example, given the following binary tree: 
 */
public class BinaryTreePath {
    public List<String> binaryTreePaths(TreeNode root) {
    	List<String> result = new ArrayList<String>();
    	if (root == null) return result;
    	searchBinaryTree(root, "", result);
    	return result;
        
    }
    private void searchBinaryTree(TreeNode root, String path, List<String> result){
    	if (root.right == null && root.left == null){
    		result.add(path+root.val);
    	}
    	if (root.left!= null){
    		searchBinaryTree(root.left, path + root.val +"->", result);
    	}
    	if (root.right!= null){
    		searchBinaryTree(root.right, path + root.val +"->", result);
    	}
    }
    
    public List<String> binaryTreePaths1(TreeNode root) {
    	List<String> result = new ArrayList<String>();
    	if (root == null) return result;
    	if (root.left == null && root.right == null){
    		result.add(root.val+"");
    		return result;
    	}
    	for (String path:binaryTreePaths1(root.left)){
    		result.add(root.val +"->"+path);
    	}
    	for (String path:binaryTreePaths1(root.right)){
    		result.add(root.val +"->"+path);
    	}
    	return result;
    }

}
