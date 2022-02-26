package dp;/*
Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

 Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/135704/Detail-explanation-of-DP-solution
*/

public class BuySellStockIII {
    public int maxProfit(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for(int i:prices){                              // Assume we only have 0 money at first
            release2 = Math.max(release2, hold2+i);     // The maximum if we've just sold 2nd stock so far.
            hold2    = Math.max(hold2,    release1-i);  // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1+i);     // The maximum if we've just sold 1nd stock so far.
            hold1    = Math.max(hold1,    -i);          // The maximum if we've just buy  1st stock so far. 
        }
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }

    /*
    [3,3,5,0,0,3,1,4] 6
    [1,2,3,4,5] 4
    [1,2] 1
     */
    public int maxProfitLessMemory(int[] prices) {
        if (prices.length == 0) return 0;
        int hold1 = -prices[0], hold2 = -prices[0];
        int cash1 = 0, cash2 = 0;
        for (int i = 1; i < prices.length; i++) {
            cash2 = Math.max(cash2, hold2 + prices[i]);
            hold2 = Math.max(hold2, cash1 - prices[i]);
            cash1 = Math.max(cash1, hold1 + prices[i]);
            hold1 = Math.max(hold1, -prices[i]);
        }
        return cash2;
    }
    /*
    This uses state machine.
     */
    public int maxProfitState(int[] prices) {
        int s1 = -prices[0], s2 = Integer.MIN_VALUE, s3 = Integer.MIN_VALUE, s4 = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            s1 = Math.max(s1, -prices[i]);
            s2 = Math.max(s2, s1 + prices[i]);
            s3 = Math.max(s3, s2 - prices[i]);
            s4 = Math.max(s4, s3 + prices[i]);
        }
        return Math.max(s4, 0);

//        for (int i = 1; i < prices.length; i++) {
//            cash1 = Math.max(cash1, hold1 + prices[i]);
//            hold1 = Math.max(hold1, -prices[i]);
//            cash2 = Math.max(cash2, hold2 + prices[i]);
//            hold2 = Math.max(hold2, cash1 - prices[i]);
//        }
    }

    public int maxProfitEasyUnderstand(int[] prices) {
        int k = 2;
        if (prices.length == 0) return 0;
        int[][] dp = new int[k+1][prices.length];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int maxVal = 0;
                for (int m = 0; m < j; m++) {
                    maxVal = Math.max(maxVal, prices[j] - prices[m] + dp[i-1][m]);
                }
                dp[i][j] = Math.max(dp[i][j-1], maxVal);
            }
        }
        return dp[k][prices.length-1];
    }

    public int maxProfitEasyUnderstandImproveMent(int[] prices) {
        int k = 2;
        if (prices.length == 0) return 0;
        int[][] dp = new int[k+1][prices.length];
        for (int i = 1; i < dp.length; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j]+maxDiff);
                maxDiff = Math.max(dp[i-1][j] - prices[j], maxDiff);
            }
        }
        return dp[k][prices.length-1];
    }

    public int maxProfitDP(int[] prices) {
        if (prices.length == 0) return 0;
        int k = 2;
        int[][] dp = new int[k+1][prices.length];
        int maxProfit = 0;
        for (int i = 1; i <= k; i++) {
            int tempMaxProfit = dp[i-1][0] - prices[0];
            for (int j = 1; j < prices.length;j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + tempMaxProfit);
                tempMaxProfit = Math.max(tempMaxProfit, dp[i-1][j] - prices[j]);
                maxProfit = Math.max(maxProfit, dp[i][j]);
            }
        }
        return maxProfit;
    }

    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[k+1][prices.length];
        if (k > prices.length / 2) {
            return quickSolve(prices);
        }
        int maxProfit = 0;
        for (int i = 1; i <= k; i++) {
            int tempMaxProfit = dp[i-1][0] - prices[0];
            for (int j = 1; j < prices.length;j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + tempMaxProfit);
                tempMaxProfit = Math.max(tempMaxProfit, dp[i-1][j] - prices[j]);
                maxProfit = Math.max(maxProfit, dp[i][j]);
            }
        }
        return maxProfit;

    }
    private int quickSolve(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i-1] > 0) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }
}