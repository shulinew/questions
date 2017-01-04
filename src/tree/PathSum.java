package tree;

import java.util.LinkedList;
import java.util.List;

/*
 *  Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
For example:
Given the below binary tree and sum = 22
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
    	if (root != null){
    		if (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val)){
    			return true;
    		}
    	}
    	if (root != null && sum == root.val  && root.left == null && root.right == null){
    		return true;
    	}
    	return false;
    }
    public boolean hasPathSum1(TreeNode root, int sum){
    	if (root == null){
    		return false;
    	}
    	if (sum == root.val  && root.left == null && root.right == null){
    		return true;
    	}
    	return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val) ;
    	
    	/*
    	 *         stack<TreeNode *> s;
        TreeNode *pre = NULL, *cur = root;
        int SUM = 0;
        while (cur || !s.empty()) {
            while (cur) {
                s.push(cur);
                SUM += cur->val;
                cur = cur->left;
            }
            cur = s.top();
            if (cur->left == NULL && cur->right == NULL && SUM == sum) {
                return true;
            }
            if (cur->right && pre != cur->right) {
                cur = cur->right;
            } else {
                pre = cur;
                s.pop();
                SUM -= cur->val;
                cur = NULL;
            }
        }
        return false;
    	 */
    }
    /*
     *        5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
        [
		   [5,4,11,2],
		   [5,8,4,5]
		]
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> result  = new LinkedList<List<Integer>>();
    	List<Integer> currentResult  = new LinkedList<Integer>();
    	pathSum(root,sum,currentResult,result);
    	return result;
    }

    private void pathSum(TreeNode root, int sum, List<Integer> currentResult,
    		List<List<Integer>> result) {

    	if (root == null)
    		return;
    	currentResult.add(new Integer(root.val));
    	if (root.left == null && root.right == null && sum == root.val) {
    		result.add(currentResult);
    		currentResult.remove(currentResult.size() - 1);//don't forget to remove the last integer
    		return;
    	} else {
    		pathSum(root.left, sum - root.val, currentResult, result);
    		pathSum(root.right, sum - root.val, currentResult, result);
    	}
    	currentResult.remove(currentResult.size() - 1);
    }
}
