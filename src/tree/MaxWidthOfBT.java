package tree;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).


Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).

 */
public class MaxWidthOfBT {
/*
 *
The current solution is very simple. We make use of a function construct(nums, l, r), which returns the maximum binary tree consisting of numbers within the indices lll and rrr 
in the given nums array(excluding the rthr^{th}r​th​​ element).


    Start with the function call construct(nums, 0, n). Here, nnn refers to the number of 
    elements in the given numsnumsnums array.

    Find the index, maximax_imax​i​​, of the largest element in the current range of indices (l:r−1)(l:r-1)(l:r−1). Make this largest element, $nums[maxi]nums[max_i]nums[max​i​​] as 
    the local root node.

    Determine the left child using construct(nums, l, max_i). Doing this recursively finds the largest element in the subarray left to the current largest element.

    Similarly, determine the right child using construct(nums, max_i + 1, r).

    Return the root node to the calling function.

 */
	/*
	 * 

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) return null;
        return build(nums, 0, nums.length - 1);
    }
    
    private TreeNode build(int[] nums, int start, int end) {
        if (start > end) return null;
        
        int idxMax = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[idxMax]) {
                idxMax = i;
            }
        }
        
        TreeNode root = new TreeNode(nums[idxMax]);
        
        root.left = build(nums, start, idxMax - 1);
        root.right = build(nums, idxMax + 1, end);
        
        return root;
    }
    */
	
	/*
	 * Regardless whether these nodes exist:

    Always make the id of left child as parent_id * 2;
    Always make the id of right child as parent_id * 2 + 1;

So we can just:

    Record the id of left most node when first time at each level of the tree during an pre-order run.(you can tell by check the size of the container to hold the first nodes);
    At each node, compare the distance from it the left most node with the current max width;

	 */
    public int widthOfBinaryTreeDfs(TreeNode root) {
        List<Integer> lefts = new ArrayList<Integer>(); // left most nodes at each level;
        return dfs(root, 1, 0, lefts);
    }

    private int dfs(TreeNode n, int id, int d, List<Integer> lefts) { // d : depth
        if (n == null) return 0;
        if (d >= lefts.size()) lefts.add(id);   // add left most node
        return Math.max(id + 1 - lefts.get(d), dfs(n.left, id*2, d+1, lefts) + dfs(n.right, id*2+1, d+1, lefts));
    }
    
    public int widthOfBinaryTreeBfs(TreeNode root) {
        if (root == null) return 0;
        int max = 0;
        Queue<Map.Entry<TreeNode, Integer>> q = new LinkedList<Map.Entry<TreeNode, Integer>>();
        q.offer(new AbstractMap.SimpleEntry<TreeNode, Integer>(root, 1));

        while (!q.isEmpty()) {
            int l = q.peek().getValue(), r = l; // right started same as left
            for (int i = 0, n = q.size(); i < n; i++) {
                TreeNode node = q.peek().getKey();
                r = q.poll().getValue();
                if (node.left != null) q.offer(new AbstractMap.SimpleEntry<TreeNode, Integer>(node.left, r * 2));
                if (node.right != null) q.offer(new AbstractMap.SimpleEntry<TreeNode, Integer>(node.right, r * 2 + 1));
            }
            max = Math.max(max, r + 1 - l);
        }

        return max;
    }
    
    public int widthOfBinaryTree1(TreeNode root) {
    	List<Integer> leftNodes = new ArrayList<Integer>();
    	int[] result = new int[1];
    	dfs(root, 1, 0, leftNodes, result);
    	return result[0];
    }
    private void dfs(TreeNode node, int id, int depth, List<Integer> leftNodes, int[] result) {
    	if (node == null) return;
    	if (depth >= leftNodes.size()) {
    		leftNodes.add(id);
    	}
    	result[0] = Integer.max(id - leftNodes.get(depth) + 1, result[0]);
    	dfs(node.left, id*2, depth+1, leftNodes, result);
    	dfs(node.right, id*2+1, depth+1, leftNodes, result);
    }
    
    public int widthOfBinaryTreeLevel(TreeNode root) {
        if(root == null) return 0;
        ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
        ArrayDeque<Integer>  count = new ArrayDeque<Integer>();
        queue.offer(root);
        count.offer(0);
        int max = 1;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            int left = 0;
            int right = 0;
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int index = count.poll();
                if(i == 0)  left = index;
                if(i == size-1)  right = index;
                if(node.left != null) {
                    queue.offer(node.left);
                    count.offer(index*2);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                    count.offer(index*2 + 1);
                }
            }
            max = Math.max(max,right - left + 1);
        }
        return max;
    }
}
