package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined 
 * as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is 
 * the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order. 
 *  
 *  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order. 
  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once. 
https://leetcode.com/problems/most-frequent-subtree-sum/description/
 */
public class FindFrequentTreeSum {
	public int[] findFrequentTree(TreeNode root) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Integer maxFrequency = 0;
		postOrder(root, map, maxFrequency);
		List<Integer> list = new ArrayList<Integer>();
		for (Integer sum: map.keySet()) {
			if (map.get(sum) == maxFrequency) {
				list.add(sum);
			}
		}
		int [] results = new int[list.size()];
		for (int i = 0; i < list.size(); i++){
			results[i] = list.get(i);
		}
		return results;
	
	}
	private int postOrder(TreeNode node, Map<Integer, Integer> map, Integer frequency) {
		if (node == null) return 0;
		int sum = node.val + postOrder(node.left, map, frequency) + postOrder(node.right, map, frequency);
		int count = map.getOrDefault(sum, 0) + 1;
		map.put(sum, count);
		frequency = Math.max(frequency, count);
		return sum;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(2);
		root.right = new TreeNode(-3);
		int [] results = new FindFrequentTreeSum().findFrequentTree(root);
	}
}
