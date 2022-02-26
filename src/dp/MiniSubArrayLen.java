/*
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
*/

public MiniSubArrayLen {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int sum;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            sum = 0;
            for (int j = i; j < len; j++) {
                sum += nums[j];
                if (sum >= s) {
                    minLen = Math.min(minLen, j-i+1);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}