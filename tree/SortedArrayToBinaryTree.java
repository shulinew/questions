package tree;

import java.util.LinkedList;
import java.util.Queue;

public class SortedArrayToBinaryTree {
    public TreeNode sortedArrayToBST(int[] nums) {
    	return buildBinaryTree(0, nums.length - 1, nums);
    }
    private TreeNode buildBinaryTree(int low, int high, int[] nums){
    	if (low > high) return null;
    	if (low == high) return new TreeNode(nums[low]);
    	int mid = (high - low) / 2 + low;
    	TreeNode root = new TreeNode(nums[mid]);
    	root.left = buildBinaryTree(low, mid -1, nums);
    	root.right = buildBinaryTree(mid + 1, high, nums);
    	return root;
    }
    
    public TreeNode sortedArrayToBST1(int[] nums) {
        int len = nums.length;
        if(len==0){
            return null;
        }
        
        TreeNode root = new TreeNode(0);
        len--;
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        
        int layer = 1;
        while(len>0){
            layer *= 2;
            for(int i=0;i<layer&&len>0;i++,len-=2){
                TreeNode cur = q.poll();
                cur.left = new TreeNode(0);
                if(len>1){
                    cur.right = new TreeNode(0);
                    q.add(cur.left);
                    q.add(cur.right);
                }
            }
        }
        
        int i=0;
        TreeNode cur = root;
        while(cur!=null){
            if(cur.left==null){
                cur.val = nums[i++];
                cur = cur.right;
            }else {
                TreeNode pre = cur.left;
                while(pre.right!=null && pre.right!=cur){
                    pre = pre.right;
                }
                
                if(pre.right==null){
                    pre.right = cur;
                    cur = cur.left;
                }else {
                    pre.right = null;
                    
                    // pre must has a value now
                    cur.val = nums[i++];
                    cur = cur.right;
                }
            }
        }
        
        return root;
    }
}
