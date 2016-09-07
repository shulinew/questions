package BrainTeaser;

/*
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of 
 * money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have 
 * security system connected and it will automatically contact the police if two adjacent houses were broken 
 * into on the same night. Given a list of non-negative integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HorseRob {
	public int rob(int[] nums){
		if (nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		
		int [] money = new int[nums.length];
		money[0] = nums[0];
		money[1] = Math.max(money[0], nums[1]);
		
		for (int i =2; i< nums.length;i++){
			money[i] = Math.max(money[i-2]+nums[i], money[i-1]);
		}
		return money[nums.length-1];
	}
	
	public int rob1(int[] nums){
		int prev1 = nums[0], prev2 = Math.max(nums[0], nums[1]), current = prev2;
		for (int i = 2; i< nums.length;i++){
		    current = Math.max(prev1 + nums[i], prev2);
			prev1 = prev2;
			prev2 = current;
		}
		return current;
	}
	
	public int rob2(int[] nums){
	    int prevNo = 0;
	    int prevYes = 0;
	    for (int n : nums) {
	        int temp = prevNo;
	        prevNo = Math.max(prevNo, prevYes);
	        prevYes = n + temp;
	    }
	    return Math.max(prevNo, prevYes);
	}
	public int rob3(int[] nums){
	    int[][] dp = new int[nums.length + 1][2];
	    for (int i = 1; i <= nums.length; i++) {
	        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
	        dp[i][1] = nums[i - 1] + dp[i - 1][0];
	    }
	    return Math.max(dp[nums.length][0], dp[nums.length][1]);
	}

}
