package dp;

import java.util.HashSet;
import java.util.Set;

public class MaxTreeProduct {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
//    [1,2,3,4,5,6]
    long result = 0, total = 0, subTree;
    public int maxProduct(TreeNode root) {
        total = sum(root);
        return (int) (result % (int) (1e9 + 7));

    }

    private long sum(TreeNode root) {
        if (root == null) return 0;
        subTree = root.val + sum(root.left) + sum(root.right);
        result = Math.max(result, subTree * (total - subTree));
        return subTree;
    }

    int MOD = (int) (1e9) + 7;

    public int maxProduct1(TreeNode root) {
        Set<Long> sums = new HashSet<>();
        int total = dfs(root, sums);
        long max = 0;
        for (long sum : sums) {
            max = Math.max(max, sum * (total - sum));
        }
        return (int) (max % MOD);
    }

    public int dfs(TreeNode root, Set<Long> sums) {
        if (root == null)
            return 0;
        root.val += dfs(root.left, sums);
        root.val += dfs(root.right, sums);
        sums.add((long) (root.val));
        return root.val;
    }
}
