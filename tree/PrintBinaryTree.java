package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * Print a binary tree in an m*n 2D string array following these rules:

    The row number m should be equal to the height of the given binary tree.
    The column number n should always be an odd number.
    The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
    Each unused space should contain an empty string "".
    Print the subtrees following the same rules.
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
 
 Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
 
 Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 */
public class PrintBinaryTree {
	public List<List<String>> printTree1(TreeNode root) {
	    List<List<String>> res = new LinkedList<List<String>>();
	    int height = root == null ? 1 : getHeight(root);
	    int rows = height, columns = (int) (Math.pow(2, height) - 1);
	    List<String> row = new ArrayList<String>();
	    for(int i = 0; i < columns; i++) {
	    	row.add("");
	    }
	    for(int i = 0; i < rows; i++) {
	    	res.add(new ArrayList<String>(row));
	    }
	    populateRes(root, res, 0, rows, 0, columns - 1);
	    return res;
	}

	private void populateRes(TreeNode root, List<List<String>> res, int row, int totalRows, int i, int j) {
	    if (row == totalRows || root == null) return;
	    res.get(row).set((i+j)/2, Integer.toString(root.val));
	    populateRes(root.left, res, row+1, totalRows, i, (i+j)/2 - 1);
	    populateRes(root.right, res, row+1, totalRows, (i+j)/2+1, j);
	}
	
    public List<List<String>> printTree2(TreeNode root) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (root == null) {
            return res;
        }
        
        int rows = getHeight(root);
        int cols = (int)Math.pow(2, rows) - 1;
        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<String>();
            for (int j = 0; j < cols; j++) {
                row.add("");
            }
            res.add(row);
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<int[]> indexQ = new LinkedList<int[]>();
        queue.offer(root);
        indexQ.offer(new int[] { 0, cols - 1 });
        int row = -1;
        while (!queue.isEmpty()) {
            row++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                int[] indices = indexQ.poll();
                
                if (cur == null) {
                    continue;
                }
                
                int left = indices[0];
                int right = indices[1];
                int mid = left + (right - left) / 2;
                res.get(row).set(mid, String.valueOf(cur.val));
                
                queue.offer(cur.left);
                queue.offer(cur.right);
                indexQ.offer(new int[] { left, mid - 1 });
                indexQ.offer(new int[] { mid + 1, right });
            }
        }
        
        return res;
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
    
    int height = 0, width = 0;
    Map<String, String> map = new HashMap<String, String>();
    
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (root == null) return res;
        
        measure(root, 0);
        mark(root, 0, 0, width - 1);
        
        for (int i = 0; i < height; i++) {
            List<String> row = new ArrayList<String>();
            for (int j = 0; j < width; j++) {
                if (map.containsKey(i + "," + j)) {
                    row.add(map.get(i + "," + j));
                }
                else {
                    row.add("");
                }
            }
            res.add(row);
        }
        
        return res;
    }
    
    private int measure(TreeNode root, int h) {
        if (root == null) return 0;
        
        height = Math.max(height, h + 1);
        
        int w = Math.max(measure(root.left, h + 1), measure(root.right, h + 1)) * 2 + 1;
        width = Math.max(width, w);
        
        return w;
    }
    
    private void mark(TreeNode root, int y, int l, int r) {
        if (root == null) return;
        
        int x = (r + l) / 2;
        map.put(y + "," + x, root.val + "");
        
        mark(root.left, y + 1, l, x - 1);
        mark(root.right, y + 1, x + 1, r);
    }
}
