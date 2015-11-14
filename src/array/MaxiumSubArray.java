package array;
/*
 *  Find the contiguous subarray within an array (containing at least one number) which has the largest product.
	For example, given the array [2,3,-2,4],
	the contiguous subarray [2,3] has the largest product = 6. 
 */
public class MaxiumSubArray {
    public int maxProduct(int[] nums) {
    	if (nums.length == 1)
    		return nums[0];
    	int maxSoFar = nums[0];
    	int maxEndHere = nums[0];
    	int minEndHere = nums[0];
    	for (int i = 1; i<nums.length;i++){
    		if (nums[i] >= 0){
	    		maxEndHere = Math.max(nums[i], maxEndHere*nums[i]);
	    		minEndHere = Math.min(minEndHere*nums[i], nums[i]);
    		}else{
    			int temp = minEndHere;
    			minEndHere = Math.min(maxEndHere*nums[i], nums[i]);
    			maxEndHere = Math.max(temp*nums[i], nums[i]);
    		}
    		maxSoFar = Math.max(maxSoFar, maxEndHere);
    	}
        return maxSoFar;
    }
}
