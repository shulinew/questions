package dp;

import java.util.Stack;

// https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/340027/Java-DP-easy-to-understand
/*
https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/340027/Java-DP-easy-to-understand

        Minimum Cost Tree From Leaf Values
        Sum of Subarray Minimums
        Online Stock Span
        Score of Parentheses
        Next Greater Element II
        Next Greater Element I
        Largest Rectangle in Histogram
        Trapping Rain Water

 */
public class MiniCostLeafTree {
    public int mctFromLeafValues(int[] arr) {
        int result = 0;
        int length = arr.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int i : arr) {
            while(stack.peek() <= i) {
                int mid = stack.pop();
                result += mid * Math.min(stack.peek(), i);
            }
            stack.push(i);
        }
        while (stack.size() > 2) {
            result += stack.pop() * stack.peek();
        }
        return result;
    }

    public int mctFromLeafValuesDP(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        return dfs(arr, 0, arr.length-1, dp);
    }
    private int dfs(int[] arr, int start, int end, int[][] dp) {
        if (start == end) return 0;
        if (dp[start][end] > 0) return dp[start][end];
        int result = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int left = dfs(arr, start, i, dp);
            int right = dfs(arr, i+1, end, dp);
            int maxLeft = 0;
            int maxRight = 0;
            for (int j = start; j <= i; j++) {
                maxLeft = Math.max(maxLeft, arr[j]);
            }
            for (int j = i + 1; j <= end; j++) {
                maxRight = Math.max(maxRight, arr[j]);
            }
            result = Math.min(result, left + right + maxLeft * maxRight);
        }
        dp[start][end] = result;
        return result;
    }

    public int mctFromLeafValuesDP1(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        int[][] max = new int[arr.length][arr.length];
        for(int i = 0; i < arr.length; i ++) {
            int localMax = 0;
            for(int j = i; j < arr.length; j ++) {
                if(arr[j] > localMax) {
                    localMax = arr[j];
                }
                max[i][j] = localMax;
            }
        }
        for(int len = 1; len < arr.length; len ++) {
            for(int left = 0; left + len < arr.length; left ++) {
                int right = left + len;
                dp[left][right] = Integer.MAX_VALUE;
                if(len == 1) {
                    dp[left][right] = arr[left]*arr[right];
                } else {
                    for(int k = left; k < right; k ++) {
                        dp[left][right] = Math.min(dp[left][right], dp[left][k] + dp[k+1][right] + max[left][k]*max[k+1][right]);
                    }
                }
            }
        }
        return dp[0][arr.length-1];
    }
}
