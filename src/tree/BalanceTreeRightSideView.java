package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 *    1            <---
	 /   \
	2     3         <---
	 \     \
	  5     4       <---
	  return [1,3,4]
 */
public class BalanceTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> list = new ArrayList<Integer>();
    	while (root != null){
    		list.add(root.val);
    		if (root.right != null){
    			root = root.right;
    		}else {
    			root = root.left;
    		}
    	}
        return list;
    }
    
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }
    
    public void rightView(TreeNode current, List<Integer> result, int currDepth){
        if(current == null){
            return;
        }
        // only add one node from each level
        if(currDepth == result.size()){
            result.add(current.val);
        }
        
        rightView(current.right, result, currDepth + 1);
        rightView(current.left, result, currDepth + 1);
        
    }
    public List<Integer> rightSideView2(TreeNode root) {
        // reverse level traversal
        List<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (root == null) return result;
        
        queue.offer(root);
        while (queue.size() != 0) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) result.add(cur.val);
                if (cur.right != null) queue.offer(cur.right);
                if (cur.left != null) queue.offer(cur.left);
            }
            
        }
        return result;
    }
    
    public List<Integer> rightSideView3(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    // last element in current level
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }

}
