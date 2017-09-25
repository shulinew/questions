package tree;

import java.util.List;
import java.util.Stack;

/*
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself. 
 */
public class SubTree {
//    public boolean isSubtree(TreeNode s, TreeNode t) {
//    	if (s == null || t == null) return false;
//    	List<TreeNode> nodes = new ArrayList<TreeNode>();
//    	findNodes(s, t.val, nodes);
//    	for (TreeNode node: nodes) {
//        	if (node == null) return false;
//        	if (isSameTree(node, t)) return true;
//    	}
//    	return false;
//    }
	public boolean isSubTree(TreeNode s, TreeNode t) {
		if (s == null) return false;
		if (isSameTree(s, t)) return true;
		return (isSubTree(s.left, t) || isSubTree(s.right, t));
	}
    private void findNodes(TreeNode node, int val, List<TreeNode> nodes) {
    	if (node == null) return;
    	if (node.val == val) {
    		nodes.add(node);
    	} 
    	findNodes(node.left, val, nodes);
    	findNodes(node.left, val, nodes);
    }
    private boolean isSameTree(TreeNode s, TreeNode t) {
    	if (s == null && t == null) return true;
    	if (s == null && t != null || s != null && t == null) {
    		return false;
    	}
    	return (s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right));
    }
    
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String spreorder = generatepreorderString(s); 
        String tpreorder = generatepreorderString(t);
        
        return spreorder.contains(tpreorder) ;
    }
    public String generatepreorderString(TreeNode s){
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stacktree = new Stack<TreeNode>();
        stacktree.push(s);
        while(!stacktree.isEmpty()){
           TreeNode popelem = stacktree.pop();
           if(popelem==null)
              sb.append(",#"); // Appending # inorder to handle same values but not subtree cases
           else      
              sb.append(","+popelem.val);
           if(popelem!=null){
                stacktree.push(popelem.right);    
                stacktree.push(popelem.left);  
           }
        }
        return sb.toString();
    }
    //O(n+m)
    
    public boolean isSubtree3(TreeNode s, TreeNode t) {
        return serialize(s).contains(serialize(t)); // Java uses a naive contains algorithm so to ensure linear time, 
                                                    // replace with KMP algorithm
    }

    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        serialize(root, res);
        return res.toString();
    }

    private void serialize(TreeNode cur, StringBuilder res) {
        if (cur == null) {res.append(",#"); return;}
        res.append("," + cur.val);
        serialize(cur.left, res);
        serialize(cur.right, res);
    }
    public static void main(String[] args) {
    	TreeNode s = new TreeNode(1);
    	s.right = new TreeNode(1);
    	s.right.right = new TreeNode(1);
    	s.right.right.right = new TreeNode(1);
    	s.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right.right.right.right.right.right= new TreeNode(1);
    	s.right.right.right.right.right.right.right.right.right.right.right.left = new TreeNode(2);
    	
    	s.right.right = new TreeNode(1);
    	s.right.right.right = new TreeNode(1);
    	s.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right.right.right.right.right = new TreeNode(1);
    	s.right.right.right.right.right.right.right.right.right.right.right= new TreeNode(1);
    	s.right.right.right.right.right.right.right.right.right.right.right.left = new TreeNode(2);
    	//
    	TreeNode t = new TreeNode(1);
    	t.right = new TreeNode(1);
    	t.right.right = new TreeNode(1);
    	t.right.right.right = new TreeNode(1);
    	t.right.right.right.right = new TreeNode(1);
    	t.right.right.right.right.right.right = new TreeNode(1);
    	t.right.right.right.right.right.right.right = new TreeNode(1);
    	t.right.right.right.right.right.right.right.right = new TreeNode(1);
    	t.right.right.right.right.right.right.right.right.right = new TreeNode(1);
    	t.right.right.right.right.right.right.right.right.right.right = new TreeNode(1);
    	t.right.right.right.right.right.right.right.right.right.right.right= new TreeNode(1);
    	t.right.right.right.right.right.right.right.right.right.right.right.left = new TreeNode(2);
    }
}
