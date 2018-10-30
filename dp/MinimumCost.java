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


    /* Simple DP solution - at each step we make a decision to take this step or not. If we take current step, then we take the min of taking and not taking previous step and add the current cost.
If we don't take the current step, then we have to take the previous step - so directly get the value from taking the previous step
Finally return the min of taking and not taking the final step*/


dp[i] specifies the minimum cost required to reach the ith step
Its given that we can either start from the 0th index step or the 1st index. That basically means that the minimum cost at 0th index step or 1st index step is 0.
i.e dp[0]=0 and dp[1]=0;
To go further with this, I am just using a simple formula-
dpi[i] = Minimum of (min cost of i-1 step + cost of i-1 step, min cost of i-2 step + cost of i-2 step)
= Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);

result would be stored in dp[cost.length];

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        
        int[] dp=new int[cost.length+1];
        dp[0]=0;
        dp[1]=0;
        
        for(int i=2;i<dp.length;i++){
            dp[i]=Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
        }
        return dp[cost.length];
    }
}


}