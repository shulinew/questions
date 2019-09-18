/*
 On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you 
can either start from the step with index 0, or the step with index 1. 

Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].

*/



public class MinimumCost {
    /*
There is a clear recursion available: the final cost f[i] to climb the staircase from some step i is f[i] = cost[i] + min(f[i+1], f[i+2]). This motivates dynamic programming.

Algorithm

Let's evaluate f backwards in order. That way, when we are deciding what f[i] will be, we've already figured out f[i+1] and f[i+2].

We can do even better than that. At the i-th step, let f1, f2 be the old value of f[i+1], f[i+2], and update them to be the new values f[i], f[i+1]. 
We keep these updated as we iterate through i backwards. At the end, we want min(f1, f2).
*/
    public int minCostClimbingStairs(int[] cost) {
        int f1 = 0, f2 = 0;
        for (int i = cost.length - 1; i >= 0; --i) {
            int f0 = cost[i] + Math.min(f1, f2);
            f2 = f1;
            f1 = f0;
        }
        return Math.min(f1, f2);
    }

    public int minCostClimbingStairs1(int[] cost) {
        int length = cost.length;
        int[] dp = new int[length];
        if (cost.length <=1) return Math.min(cost[0], cost[1]);
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < length; i++){
            dp[i] = Math.min(dp[i-2]+cost[i], dp[i-1]+cost[i]);
        }
        return Math.min(dp[length-1], dp[length-2]);
    }


    /* Simple DP solution - at each step we make a decision to take this step or not. If we take current step, then we take the min of taking and not taking previous step and add the current cost.
If we don't take the current step, then we have to take the previous step - so directly get the value from taking the previous step
Finally return the min of taking and not taking the final step*/
}