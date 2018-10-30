package array;

/*
	Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
	Solve it without division and in O(n).
	For example, given [1,2,3,4], return [24,12,8,6]. 
 */
public class ProductOfArrayExcludeOne {
    public int[] productExceptSelf(int[] nums) {
    	int length = nums.length;
    	int[] helper = new int[length];
    	helper[0] = 1;
    	for (int i = 1; i< length;i++){
    		helper[i] = helper[i-1]*nums[i-1];
    	}
    	int rightSoFar = 1;
    	for (int j = length-1; j>=0;j--){
    		helper[j] = rightSoFar * helper[j];
    		rightSoFar = nums[j] * rightSoFar;
    	}
    	return helper;
        
    }

}
