package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 *  Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values. 
 1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
 */
public class FindDuplicateSubTree {
    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        List<TreeNode> result = new LinkedList<TreeNode>();
        postorder(root, new HashMap<String, Integer>(), result);
        return result;
    }

    public String postorder(TreeNode current, Map<String, Integer> map, List<TreeNode> res) {
        if (current == null) return "#";  
        String serial = current.val + "," + postorder(current.left, map, res) + "," + postorder(current.right, map, res);
        if (map.getOrDefault(serial, 0) == 1) res.add(current);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }
    
   public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
	    Map<String, List<TreeNode>> map = new HashMap<String, List<TreeNode>>();
	    List<TreeNode> duplicates = new ArrayList<TreeNode>();
	    serialize(root, map);
	    for (List<TreeNode> group : map.values()) {
	        if (group.size() > 1) duplicates.add(group.get(0));
	    }
	    return duplicates;
	}
	
	private String serialize(TreeNode node, Map<String, List<TreeNode>> map) {
	    if (node == null) return "";
	    String s = "(" + serialize(node.left, map) + node.val + serialize(node.right, map) + ")";
	    if (!map.containsKey(s)){
	    	map.put(s, new ArrayList<TreeNode>());
	    }
	    map.get(s).add(node);
	    return s;
	}

}
