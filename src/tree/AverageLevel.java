package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array. 

 *Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of node
*/
public class AverageLevel {
	public List<Double> averageLevel(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		
		queue.add(root);
		List<Double> average = new ArrayList<Double>();
		while (!queue.isEmpty()) {
			double sum = 0;
			int count = queue.size();
			
			for(int i = 0;i < count; i++) {
				TreeNode node = queue.poll();
				sum += node.val;
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null){
					queue.offer(node.right);
				}
			}
			average.add(sum/count);
		}
		return average;
	}
	
	class Node {
        double sum;
        int count;
        Node (double d, int c) {
            sum = d;
            count = c;
        }
    }
    public List<Double> averageOfLevelsDfs(TreeNode root) {
        List<Node> temp = new ArrayList<Node>();
        helper(root, temp, 0);
        List<Double> result = new LinkedList<Double>();
        for (int i = 0; i < temp.size(); i++) {
            result.add(temp.get(i).sum / temp.get(i).count);
        }
        return result;
    }
    public void helper(TreeNode root, List<Node> temp, int level) {
        if (root == null) return;
        if (level == temp.size()) {
            Node node = new Node((double)root.val, 1);
            temp.add(node);
        } else {
            temp.get(level).sum += root.val;
            temp.get(level).count++;
        }
        helper(root.left, temp, level + 1);
        helper(root.right, temp, level + 1);
    }

}
