/*
A professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
*/

public class HouseRobberII {
    public int rob(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        int max = Math.max(dp[0], dp[1]);
        for (int i = 2; i < length; i++) {    
            if (length % 2 != 0 && i == length-1) {
                 dp[i] = Math.max(dp[i-2], dp[i] - dp[0]);
            } else {
                dp[i] = dp[i-2] + nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}