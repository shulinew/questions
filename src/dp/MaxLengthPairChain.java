package dp;/*
 You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order. 
Input: [[1,2], [2,3], [3,4]]
Output: 2

Explanation: The longest chain is [1,2] -> [3,4]
*/

import java.util.Arrays;

public class MaxLengthPairChain {
    //dp[i] = pairs[i][0] > pairs[i-1][1] ? dp[i-1] + 1 : dp[i-1]
//    public int findLongestChain(int[][] pairs) {
//        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
//        int length = pairs.length;
//        int[] dp = new int[length];
//        Arrays.fill(dp, 1);
//        for (int i = 1; i < length; i++) {
//            dp[i] = pairs[i][0] > pairs[i-1][1] ? dp[i-1] + 1 : dp[i-1];
//        }
//
//    }

// same as 435
    public int findLongestChain1(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int N = pairs.length;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int j = 1; j < N; ++j) {
            for (int i = 0; i < j; ++i) {
                if (pairs[i][1] < pairs[j][0])
                    dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }

        int ans = 0;
        for (int x: dp) if (x > ans) ans = x;
        return ans;
    }

    public int findLongestChainDP(int[][] pairs) {
        if (pairs == null) return 0;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < pairs.length; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], pairs[i][0] - pairs[j][1] > 0 ? dp[j] + 1 : dp[j]);
            }
        }
        return dp[pairs.length-1];
    }

    public int findLongestChainGreedy(int[][] pairs) {
        int result = 0;
        int end = Integer.MIN_VALUE;
        // Why it's different to sort Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        Arrays.sort(pairs, (a, b) -> (a[1] - b[1]));
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i][0] > end) {
                end = pairs[i][1];
                result++;
            }
        }
        return result;
    }
}