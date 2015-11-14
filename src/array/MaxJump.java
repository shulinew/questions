package array;
/*
 *     Given an array of non-negative integers, you are initially positioned at the first index of the array.
		Each element in the array represents your maximum jump length at that position.
		Determine if you are able to reach the last index.
		For example:
 */
public class MaxJump {
    public boolean canJump(int[] nums) {
    	int max = 0;
    	for (int i = 0; i< nums.length;i++){
    		if (i > max)
    			return false;
    		max = Math.max(nums[i]+ i, max);
    	}
    	return true;
    }
}
